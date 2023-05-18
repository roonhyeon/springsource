/**
 * 1. 수정 버튼 클릭 시 operForm submit to /board/read
 * 2. 목록 버튼 클릭 시 operForm submit to /board/list
 * 3. bno 제거하고 보내기
 */
const form = document.querySelector("#operForm");
document.querySelector(".btn-info").addEventListener("click", () => {
  form.action = "/board/modify";
  form.submit();
});
document.querySelector(".btn-secondary").addEventListener("click", () => {
  // bno 제거하고 전송
  form.firstElementChild.remove();
  form.action = "/board/list";
  form.submit();
});
