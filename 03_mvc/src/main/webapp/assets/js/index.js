/**
 * 
 */
// servlet 을 개별로 만드는 것은 MVC 패턴이 아니다.
// 하나의 servlet 이 모든 요청을 받아서 해결한다.

const getContextPath = ()=>{
  const host = location.host;     /* localhost:8080   // 호스트(host) */
  const url = location.href;      /* http://localhost:8080/mvc/getDate.do */
  const begin = url.indexOf(host) + host.length;
  const end = url.indexOf('/', begin + 1);    // begin + 1 : begin 다음 슬래시부터 반환해라
  return url.substring(begin, end);   // begin 포함, end 미포함
}

// getContextPath 를 하는 이유는 서버 배포하기 위해서! 서버 환경에 따라서 주소가 안나올 수 있음.
const getDateTime = ()=>{
  const type = document.getElementById('type');
  if(type.value === 'date') {
    location.href = getContextPath() + '/getDate.do';
  } else if(type.value === 'time') {
    location.href = getContextPath() + '/getTime.do';
  } else if(type.value === 'datetime') {
    location.href = getContextPath() + '/getDateTime.do';
  }
}
// prefix <-> suffix
document.getElementById('btn').addEventListener('click', getDateTime);