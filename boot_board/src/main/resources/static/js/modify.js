fetch("/board/getAttachList?bno=" + bno)
  .then((response) => {
    if (!response.ok) {
      throw new Error("첨부물 없음");
    }
    return response.json();
  })
  .then((data) => {
    console.log(data);
    showUploadedFile(data);
  })
  .catch((error) => console.log(error));

// 수정 버튼 클릭해서 form submit이 일어나면 첨부파일 목록 수집하기
const modifyForm = document.querySelector("#modifyForm");

modifyForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const lis = document.querySelectorAll(".uploadResult ul li");

  let str = "";
  lis.forEach((item, idx) => {
    str += "<input type='hidden' name='attachList[" + idx + "].uuid' value='" + item.dataset.uuid + "'/>";
    str += "<input type='hidden' name='attachList[" + idx + "].uploadPath' value='" + item.dataset.path + "'/>";
    str += "<input type='hidden' name='attachList[" + idx + "].fileName' value='" + item.dataset.filename + "'/>";
    str += "<input type='hidden' name='attachList[" + idx + "].fileType' value='" + item.dataset.type + "'/>";
  });

  // 수집한 태그 폼에 추가
  modifyForm.insertAdjacentHTML("beforeend", str);
  console.log("수정폼");
  console.log(modifyForm);

  modifyForm.submit();
});

// 수정, 삭제 클릭 시 동작하는 폼

/**
 * 1. 삭제 버튼 클릭 시 operForm submit to /board/remove
 * 2. 목록 버튼 클릭 시 operForm submit to /board/list
 * 3. bno 제거하고 보내기
 */
const form = document.querySelector("#operForm");

const btnDan = document.querySelector(".btn-danger");
if (btnDan) {
  btnDan.addEventListener("click", () => {
    form.action = "/board/remove";
    form.submit();
  });
}

document.querySelector(".btn-secondary").addEventListener("click", () => {
  // bno 제거하고 전송
  form.firstElementChild.remove();
  form.action = "/board/list";
  form.submit();
});
