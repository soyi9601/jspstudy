package com.gdu.prj.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ActionForward {
  private String view;
  private boolean isRedirect;
  // isRedirect true = redirect, false = forward
  // boolean 의 기본값은 false
}
