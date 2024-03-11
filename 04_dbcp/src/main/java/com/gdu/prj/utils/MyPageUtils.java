package com.gdu.prj.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MyPageUtils {
  
  // PAGING 처리

  private int total;    // 전체 게시글 개수                      (DB에서 구한다.)
  private int display;  // 한 페이지에 표시할 게시물 개수        (요청 파라미터로 받는다. 전달이 안되면 기본값 n 으로 주기)
  private int page;     // 현재 페이지 번호                      (요청 파라미터로 받는다.)
  private int begin;    // 한 페이지에 표시할 게시글의 시작 번호 (계산한다.)
  private int end;      // 한 페이지에 표시할 게시글의 종료 번호 (계산한다.)
  
  private int pagePerBlock = 10; // 한 블록에 표시할 페이지 링크의 개수      (임의로 결정)
  private int totalPage;         // 전체 페이지 개수                         (계산한다.)
  private int beginPage;         // 한 블록에 표시할 페이지 링크의 시작 번호 (계산한다.)
  private int endPage;           // 한 블록에 표시할 페이지 링크의 종류 번호 (계산한다.)
  
  public void setPaging(int total, int display, int page) {
    
    this.total = total;
    this.display = display;
    this.page = page;
    
    // board_no 를 가지고 total 번호를 쓰지 않아야함. board_no가 삭제되면 페이지 번호가 꼬이기 때문
    begin = (page - 1) * display + 1;
    end = begin + display -1;
    
    totalPage = (int)Math.ceil((double)total / display);
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
    // endPage = beginPage + pagePerBlock - 1;
    // endPage = Math.min(totalPage, endPage);
    endPage = Math.min(totalPage, beginPage + pagePerBlock - 1);   // 51 페이지까지 있는데 60페이지까지 나올 수 있기 때문에 작은 페이지까지만 나와라 하고 보정을 해줘야함.
    
  }
  
  public String getPaging(String requestURI, String sort, int display) {
    
    StringBuilder builder = new StringBuilder();
    
    // <
    if(beginPage == 1) {
      builder.append("<div class=\"dont-click\">&lt;</div>");
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (beginPage - 1) + "&sort="+ sort +"&display="+ display +"\">&lt;</a></div>");
    }
    
    // 1 2 3 4 5 6 7 8 9 10
    for(int p = beginPage; p <= endPage; p++) {
      if(p == page) {
        builder.append("<div><a class=\"current-page\" href=\"" + requestURI + "?page=" + p + "&sort=" + sort + "&display=" + display + "\">"+ p +"</a></div>");
      } else {
        builder.append("<div><a href=\"" + requestURI + "?page=" + p + "&sort=" + sort + "&display=" + display + "\">"+ p +"</a></div>");
      }
    }
    
    
    // >
    if(endPage == totalPage) {
      builder.append("<div class=\"dont-click\">&gt;</div>");
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (endPage + 1) + "&sort=" + sort + "&display=" + display + "\">&gt;</a></div>");
    }
    
    return builder.toString();
  }
  
}
