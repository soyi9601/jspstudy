package common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data       // getter + setter + toString + equals 등등 들어있음  / builder 는 안들어있음
@Builder
public class ActionForward {
  private String view;
  private boolean isRedirect;   // redirect 할거면 true -> 즉 boolean 의 기본값이 false 므로 redirect 를 하지 않고 forward 를 하겠다는 뜻.
}
