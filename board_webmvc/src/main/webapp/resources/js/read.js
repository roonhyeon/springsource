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

// 댓글 보여줄 영역 가져오기
let chat = document.querySelector(".chat");

showList(1);

function showList(page) {
  // 현재 게시물에 대한 댓글 가져오기
  // reply.js의 function getList를 부르는 과정
  // page: page || 1: page 변수값이 존재하면 page 값 사용하고, 없으면 1
  replyService.getList({ bno: bno, page: page || 1 }, (result) => {
    // console.log(result);
    // 도착한 데이터를 화면에 보여주기
    if (result == null || result.length == 0) {
      chat.innerHTML = "";
      return;
    }
    let str = "";
    for (let idx = 0; idx < result.length; idx++) {
      str +=
        "<li class='list-group-item border-bottom' data-rno='" +
        result[idx].rno +
        "'>";
      str += "<div class='d-flex justify-content-between'>";
      str +=
        "<strong class='primary-font'>" + result[idx].replyer + "</strong>";
      str +=
        "<small class='text-muted text-right'>" +
        replyService.displayTime(result[idx].replydate) +
        "</small>";
      str += "</div>";
      str += "<p>" + result[idx].reply + "</p>";
      str += "</li>";
    }
    chat.innerHTML = str;
  });
}

// 댓글 작업 호출 => 댓글 작성 버튼 클릭 시
// form submit 막기, reply, replyer 가져오기
const formform = document.querySelector("#replyForm");
formform.addEventListener("submit", (e) => {
  e.preventDefault();

  const reply = document.querySelector("#reply");
  const replyer = document.querySelector("#replyer");

  replyService.add(
    { bno: bno, reply: reply.value, replyer: replyer.value },
    // 이 function을 reply.js에서 callback으로 받는다.
    (result) => {
      // alert(result);
      // 댓글 작성 칸 reset for writing another reply
      reply.value = "";
      replyer.value = "";
    }
  );
});

// 댓글 하나 가져오기(reply.js의 get function 가져오기)
replyService.get(2, (result) => {
  console.log(result);
});
