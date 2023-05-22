/**
 * 폼 모든 요소가 비어있는지 확인
 */
const form = document.querySelector("#registerForm");

form.addEventListener("submit", (e) => {
  if (!form.checkValidity()) {
    e.preventDefault();
    e.stopPropagation();
  }
  form.classList.add("was-validated");
});
