package com.gdu.prj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.gdu.prj.dto.BoardDto;

/*
 * view - controller - service - dao - db
 * Dao 는 db 에 접근하여 정보를 가져올 수 있음
 */

public class BoardDaoImpl implements BoardDao {

  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  // 만들어서 바로 반납하기 때문에 선언만 해주면 됨.
  
  @Override
  public int insertBoard(BoardDto board) {
    return 0;
  }

  @Override
  public int updateBoard(BoardDto board) {
    return 0;
  }

  @Override
  public int deleteBoard(int board_no) {
    return 0;
  }

  @Override
  public List<BoardDto> selectBoardList(Map<String, Object> map) {
    return null;
  }

  @Override
  public int getBoardCount() {
    return 0;
  }

  @Override
  public BoardDto selectBoardByNo(int board_no) {
    return null;
  }

  @Override
  public void close() {
    // TODO Auto-generated method stub

  }

}
