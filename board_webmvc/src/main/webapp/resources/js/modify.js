/**
 * 1. 삭제 버튼 클릭 시 operForm submit to /board/remove
 * 2. 목록 버튼 클릭 시 operForm submit to /board/list
 * 3. bno 제거하고 보내기
 */
const form = document.querySelector("#operForm");
document.querySelector(".btn-danger").addEventListener("click", () => {
  form.action = "/board/remove";
  form.submit();
});
document.querySelector(".btn-secondary").addEventListener("click", () => {
  // bno 제거하고 전송
  form.firstElementChild.remove();
  form.action = "/board/list";
  form.submit();
});
