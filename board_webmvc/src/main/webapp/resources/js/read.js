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

function showReplyPage(total) {}

function showList(page) {
  // 현재 게시물에 대한 댓글 가져오기
  // reply.js의 function getList를 부르는 과정
  // page: page || 1: page 변수값이 존재하면 page 값 사용하고, 없으면 1
  replyService.getList({ bno: bno, page: page || 1 }, (total, result) => {
    console.log("read.js에서 확인");
    console.log(total); // 해당 게시물의 댓글 총 개수
    console.log(result);
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
      str += "<div class='btn-group btn-group-sm'>";
      str += "<button class='btn btn-warning' type='button'>수정</button>";
      str += "<button class='btn btn-danger' type='button'>삭제</button>";
      str += "</div>";
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

// 수정 버튼 클릭 시 모달 창 띄우기
// document.querySelectorAll(".btn-warning").forEach((updateBtn) => {
//   updateBtn.addEventListener("click", () => {});
// });

// 이벤트 전파: 자식의 이벤트는 부모에게 전달된다. => ul(li의 부모)에 이벤트를 걸면 된다.
chat.addEventListener("click", (e) => {
  // 어느 li에서 수정 버튼 클릭 시의 모달 이벤트가 발생했느냐?
  // e.target: 이벤트 발생 대상
  // 현재 버튼에서 위로 제일 가까운, 이벤트 발생 대상을 감싸고 있는 부모 li를 찾아주세요.
  let li = e.target.closest("li");
  console.log("이벤트 발생", li);

  // rno 가져오기(data-* 속성값 가져오기: dataset)
  let rno = li.dataset.rno;
  console.log("rno ", rno);

  // 이벤트를 부모가 감지를 하기 때문에
  if (e.target.classList.contains("btn-warning")) {
    // 댓글 하나 가져오기(reply.js의 get function 가져오기)
    replyService.get(rno, (result) => {
      console.log(result);

      // 댓글 모달 창 안에 가져온 댓글 내용 보여주기
      document.querySelector(".modal-body #rno").value = result.rno;
      document.querySelector(".modal-body #reply").value = result.reply;
      document.querySelector(".modal-body #replyer").value = result.replyer;

      $("#replyModal").modal("show");
    });
  } else if (e.target.classList.contains("btn-danger")) {
    // 삭제 버튼 클릭 시
    replyService.remove(rno, (result) => {
      if (result === "success") {
        alert("삭제 성공");
      }
    });
  }
});

// 모달 창 수정 버튼이 클릭되면 댓글 수정
document
  .querySelector(".modal-footer .btn-primary")
  .addEventListener("click", () => {
    // 모달 창 안에 있는 rno, reply 가져온 후 자바스크립트 객체 생성
    const updateReply = {
      rno: document.querySelector(".modal-body #rno").value,
      reply: document.querySelector(".modal-body #reply").value,
    };

    // replyService.update 호출
    replyService.update(updateReply, (result) => {
      alert(result);

      // 모달 창 닫기
      if (result === "success") {
        $("#replyModal").modal("hide");
      }
    });
  });
