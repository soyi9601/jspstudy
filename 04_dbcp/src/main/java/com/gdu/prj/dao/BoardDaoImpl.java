package com.gdu.prj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.gdu.prj.dto.BoardDto;

/*
 * view - filter - controller - service - dao - db
 * Dao 는 db 에 접근하여 정보를 가져올 수 있음
 * Filter 는 없으면 생략가능
 */

public class BoardDaoImpl implements BoardDao {

  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  // 만들어서 바로 반납하기 때문에 선언만 해주면 됨.
  
  // Connection Pool 관리를 위한 DataSource 객체 선언
  private DataSource dataSource;
  
  // SingletonPattern
  private static BoardDao boardDao = new BoardDaoImpl();
  private BoardDaoImpl() {
    // META-INF\context.xml 파일에 명시된 Resource 를 이용해 DataSource 객체를
    try {
      Context context = new InitialContext();
      Context env = (Context)context.lookup("java:comp/env");
      dataSource = (DataSource)env.lookup("jdbc/myoracle");
      // Context env = (Context)context.lookup("java:comp/env/jdbc/myoracle") 하나로 합친 것.
    } catch (NamingException e) {
      System.out.println("관련 자원을 찾을 수 없습니다.");
    }
  }
  public static BoardDao getInstance() {
    return boardDao;
  }
  
  
  @Override
  public int insertBoard(BoardDto board) {
    int insertCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "INSERT INTO BOARD_T(BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, CURRENT_DATE, CURRENT_DATE)";
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle());
      ps.setString(2, board.getContents());
      insertCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
      // this.close(); 라고 호출할 수도 있음.
    }
    return insertCount;
  }

  @Override
  public int updateBoard(BoardDto board) {    // 최소 3가지를 수정할 수 있다. (board_no, title, contents) - title과 contents 에 수정할 것이 없다면 내용이 없는게 아니라 이전 내용에서 바뀐 것이 없을 것
    int updateCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "UPDATE BOARD_T SET TITLE = ?, CONTENTS = ?, MODIFIED_AT = CURRENT_DATE WHERE BOARD_NO = ?";     // ? 는 변수 처리한 것.
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle());
      ps.setString(2, board.getContents());
      ps.setInt(3, board.getBoard_no());    // board_no 는 숫자이기 때문에 setInt ! 타입 잘 생각하기
      updateCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return updateCount;
  }

  @Override
  public int deleteBoard(int board_no) {    // 삭제할 게시물의 번호만 갖고 있으면 됨
    int deleteCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "DELETE BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      deleteCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return deleteCount;
  }
  
  @Override
  public int deleteBoards(String param) {
    int deleteCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "DELETE FROM BOARD_T WHERE BOARD_NO IN(" + param + ")";
      ps = con.prepareStatement(sql);
      deleteCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return deleteCount;
  }

  @Override
  public List<BoardDto> selectBoardList(Map<String, Object> map) {
    List<BoardDto> boardList = new ArrayList<>();
    try {
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT FROM BOARD_T ORDER BY BOARD_NO DESC";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();     // rs = resultset
      // select 결과가 몇건이던지 간에 rs.next 는 갯수만큼 호출해야함. 있는만큼 호출하기 위해 반복문으로 돌림. 한줄씩 나와야함
      // 부르는 순서는 sql 쿼리문에 보고 순서대로 불러줘야함
      while(rs.next()) {
        BoardDto board = BoardDto.builder()
                            .board_no(rs.getInt(1))
                            .title(rs.getString(2))
                            .contents(rs.getString(3))
                            .modified_at(rs.getDate(4))
                            .created_at(rs.getDate(5))
                          .build();
        boardList.add(board);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return boardList;
  }

  @Override
  public int getBoardCount() {
    int boardCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "SELECT COUNT(*) FROM BOARD_T";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      // 전체 카운트는 row 가 무조건 1개임. 0개여도 1개의 row 나옴. 그럴 때는 if 문으로 처리할 수 있음.
      // row 에서 1개 나온 결과를 boardCount 에 넣을 수 있음.
      if(rs.next()) {
        boardCount = rs.getInt(1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return boardCount;
  }

  @Override
  public BoardDto selectBoardByNo(int board_no) {   // BOARD_NO 가 일치하는 것만 나올 것.
    BoardDto board = null;
    try {
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT FROM BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      rs = ps.executeQuery();
      // board_no 는 PK 라서 중복이 없음. where 절에서 board_no 를 검색했을 때 해당결과가 없는 0 이거나 1개 있음. row 는 0개이거나 1개일 것
      if(rs.next()) {
        board = BoardDto.builder()
                    .board_no(rs.getInt(1))
                    .title(rs.getString(2))
                    .contents(rs.getString(3))
                    .modified_at(rs.getDate(4))
                    .created_at(rs.getDate(5))
                    .build();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return board;
  }

  @Override
  public void close() {

    try {
      if(rs != null) rs.close();
      if(ps != null) ps.close();
      if(con != null) con.close();    // Connection 반납으로 동작
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
