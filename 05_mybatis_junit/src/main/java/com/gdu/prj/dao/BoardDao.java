package com.gdu.prj.dao;

import java.util.List;
import java.util.Map;

import com.gdu.prj.dto.BoardDto;

public interface BoardDao {
  int insertBoard(BoardDto board);
  int updateBoard(BoardDto board);
  int deleteBoard(int board_no);
  List<BoardDto> selectBoardList(Map<String, Object> params);
  int getBoardCount();
  BoardDto selectBoardByNo(int board_no);
}