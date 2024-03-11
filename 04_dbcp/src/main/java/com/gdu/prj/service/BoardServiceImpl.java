package com.gdu.prj.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.gdu.prj.common.ActionForward;
import com.gdu.prj.dao.BoardDao;
import com.gdu.prj.dao.BoardDaoImpl;
import com.gdu.prj.dto.BoardDto;
import com.gdu.prj.utils.MyPageUtils;

import jakarta.servlet.http.HttpServletRequest;

/*
 * view - filter - controller - service - dao - db
 * service 는 dao 정보만 가져옴
 * Filter 는 없으면 생략가능
 */

public class BoardServiceImpl implements BoardService {

  // service 는 dao 를 호출한다.
  private BoardDao boardDao = BoardDaoImpl.getInstance();
  // BoardDao boardDao = new BoardDaoImpl(); <- 이렇게 해놨는데 오류 뜸. 이유는 BoardDaoImpl 에서 또 생성함. 순서의 차이
  // BoardDaoImpl 에서 SingletonPattern 작업을 해놨기 때문에 getInstance 로 불러옴
  
  // 목록 보기는 MyPageUtils 객체가 필요하다.
  private MyPageUtils myPageUtils = new MyPageUtils();
  
  @Override
  public ActionForward addBoard(HttpServletRequest request) {
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    BoardDto board = BoardDto.builder()
                          .title(title)
                          .contents(contents)
                        .build();
    int insertCount = boardDao.insertBoard(board);
    // redirect 경로는 URLMapping 으로 작성한다. 새로운 list 를 구현해야함.
    // db 를 다녀왔다가 service 가 돌고나서 list 를 만들어줘야하기 때문에 jsp로 이동하는 것이 아님. -> controller 확인하기.
    String view = null;
    if(insertCount == 1) {
      view = request.getContextPath() + "/board/list.brd";
    } else if(insertCount == 0) {
      view = request.getContextPath() + "/main.brd";
    }
    // INSERT 이후 이동은 redirect -> 요청 정보를 가지고 가지 않아도 되기 때문에 redirect 사용함.
    // redirect 는 contextPath 부터 시작해야하고, forward 는 contextPath 생략가능.
    return new ActionForward(view, true);
  }

  @Override
  public ActionForward getBoardList(HttpServletRequest request) {
    
    // 전체 게시글 개수
    int total = boardDao.getBoardCount();
    
    // 한 페이지에 표시할 게시글 개수
    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("20"));
    // optional 로 전달이 되면 display 를 쓰고 전달이 안되면 20개로 쓴다.
    
    // 현재 페이지 번호
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));
    // page 번호가 전달이 안되면 '1'을 쓴다.
    
    // 정렬 방식
    Optional<String> optSort = Optional.ofNullable(request.getParameter("sort"));
    String sort = optSort.orElse("DESC");
    
    // 페이징 처리에 필요한 변수 값 계산하기
    myPageUtils.setPaging(total, display, page);
    
    // 목록을 가져올 때 필요한 변수를 Map 에 저장함
    Map<String, Object> params = Map.of("begin", myPageUtils.getBegin(), 
                                        "end", myPageUtils.getEnd(),
                                        "sort", sort);
    
    // 목록 가져오기
    List<BoardDto> boardList = boardDao.selectBoardList(params);
    // 이 모든 정보는 request 에 저장됨. 저장해서 forward 를 통하여 전달
    
    // 페이지 링크 가져오기
    String paging = myPageUtils.getPaging(request.getRequestURI(), sort, display);
    
    // JSP에 전달할 데이터들    
    request.setAttribute("total", total);
    request.setAttribute("boardList", boardList); // request 에 저장
    request.setAttribute("paging", paging);
    request.setAttribute("display", display);
    request.setAttribute("sort", sort);
    
    return new ActionForward("/board/list.jsp", false);
    // actionForward 는 contextPath 를 적지 않음.
    // contextPath 는 webapp
  }

  @Override
  public ActionForward getBoardByNo(HttpServletRequest request) {
    Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
    int board_no = Integer.parseInt(opt.orElse("0"));
    BoardDto board = boardDao.selectBoardByNo(board_no);
    String view = null;
    if(board != null) {
      view = "/board/detail.jsp";
      request.setAttribute("board", board);
    } else {
      view = "/index.jsp";
    }
    return new ActionForward(view, false);
  }

  @Override
  public ActionForward editBoard(HttpServletRequest request) {
    String param = request.getParameter("board_no");
    int board_no = 0;
    if(!param.isEmpty()) {
      board_no = Integer.parseInt(param);
    } 
    BoardDto board = boardDao.selectBoardByNo(board_no);
    // editBoard 는 수정을 하는게 아니라 편집화면으로 넘어가는 것. 수정은 modifyBoard 가 할 일. update 가 아니라 select 로 가져옴
    String view = null;
    if(board != null) {
      view = "/board/edit.jsp";
      request.setAttribute("board", board);
    } else {
      view = "/index.jsp";
    }    
    return new ActionForward(view, false);  
  }

  @Override
  public ActionForward modifyBoard(HttpServletRequest request) {
    int board_no = Integer.parseInt(request.getParameter("board_no"));
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    BoardDto board = BoardDto.builder()
                          .title(title)
                          .contents(contents)
                          .board_no(board_no)
                        .build();
    int updateCount = boardDao.updateBoard(board);
    String view = null;
    if(updateCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/detail.brd?board_no=" + board_no;
    }
    /*
    else {
      view = request.getContextPath() + "/board/list.brd";
    }
    */
    return new ActionForward(view, true);
  }

  @Override
  public ActionForward removeBoard(HttpServletRequest request) {
    String param = request.getParameter("board_no");
    int board_no = 0;
    if(!param.isEmpty()) {
      board_no = Integer.parseInt(param);
    } 
    int deleteCount = boardDao.deleteBoard(board_no);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/list.brd";
    }
    // Insert, Delete, Update 는 redirect 해줘야함
    return new ActionForward(view, true);
  }
  
  @Override
  public ActionForward removeBoards(HttpServletRequest request) {
    String param = request.getParameter("param");
    int deleteCount = boardDao.deleteBoards(param);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "main.brd";
    } else {
      view = request.getContextPath() + "/board/list.brd";
    }
    return new ActionForward(view, true);
  }

}
