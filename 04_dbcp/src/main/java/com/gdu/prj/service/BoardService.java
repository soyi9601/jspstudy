package com.gdu.prj.service;

import com.gdu.prj.common.ActionForward;

import jakarta.servlet.http.HttpServletRequest;

public interface BoardService {

  // Dao 쪽에 쓰이는 언어는 대체로 db 언어로 작성함. service는 사용자 요청에 붙기 때문에 사용자 친화적인 언어로 작성함. 수업중에는! 나중에는 같은 단어로 쓰는게 효율적
  ActionForward addBoard(HttpServletRequest request);
  ActionForward getBoardList(HttpServletRequest request);
  ActionForward getBoardByNo(HttpServletRequest request);
  ActionForward editBoard(HttpServletRequest request);    // 수정(편집)
  ActionForward modifyBoard(HttpServletRequest request);  // 진짜 수정
  ActionForward removeBoard(HttpServletRequest request);
  ActionForward removeBoards(HttpServletRequest request);
}
