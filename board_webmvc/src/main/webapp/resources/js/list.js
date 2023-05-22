/**
 *
 */

checkModal(result);

history.replaceState({}, null, null);

function checkModal(result) {
  if (result === "" || history.state) return;

  if (parseInt(result) > 0) {
    document.querySelector(".modal-body").innerHTML =
      "게시글 " + result + " 번이 등록되었습니다.";
  } else {
    document.querySelector(".modal-body").innerHTML = "처리가 완료되었습니다.";
  }

  $("#registerModal").modal("show");
}

/* 
하단의 페이지 번호 클릭 시 
a 태그 기본 기능 중지
a 태그 href 값 가져온 후 operForm의 page 요소의 value 값으로 세팅
actionForm 전송
*/
const form = document.getElementById("operForm");
const page = document.querySelector(".pagination");

page.addEventListener("click", (e) => {
  e.preventDefault();

  // href 값 가져오기
  let href = e.target.getAttribute("href");

  // operForm 안의 page value 수정
  form.firstElementChild.value = href;

  form.submit();
});

// 상단의 amount 수정 시 operForm의 amount 요소의 value 값으로 세팅
// actionForm 전송
const amount = document.getElementById("amount");
amount.addEventListener("change", (e) => {
  const val = e.target.value;

  const amount = document.querySelector("#operForm input:nth-child(2)");
  amount.value = val;

  form.submit();
});

// 제목 클릭 시 a태그 기능 중지
// actionForm의 action은 /board/read로 변경
// actionForm의 bno 태그를 추가해서 actionForm 전송
const move = document.querySelectorAll(".move");

move.forEach((move) => {
  move.addEventListener("click", (e) => {
    e.preventDefault();

    const href = e.target.getAttribute("href");

    const bno = "<input type='hidden' name='bno' value='" + href + "'>";
    form.insertAdjacentHTML("beforeend", bno);

    form.action = "/board/read";

    // console.log(form);
    form.submit();
  });
});

// 뒤로 가기 이벤트 감지 => 새로고침 하기
window.onpageshow = function (event) {
  // persisted == true면 webpage가 로딩될 때 캐시에서 읽어온다.
  if (event.persisted) {
    location.reload(); // 새로고침 코드
  }
};

// 검색 클릭 시
// type, keyword 입력 여부 확인
// 입력이 안된 경우: 경고창 보여주기
// 입력이 다 된 경우: 폼 submit
const searchForm = document.querySelector("#searchForm");
searchForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const type = document.querySelector("#type");
  const keyword = document.querySelector("#keyword");

  if (type.value === "") {
    alert("타입을 확인해주세요.");
    type.focus();
    return;
  } else if (keyword.value === "") {
    alert("키워드를 확인해주세요.");
    keyword.focus();
    return;
  }

  searchForm.submit();
});
