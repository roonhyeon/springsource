/** 1. 검색 기준(title, writer) 선택 여부 확인
 *  2. 검색어가 입력되었는지 확인
 *  3. 두 조건을 모두 만족한 경우 submit
 *  4. submit하면 /search.do로 가기
 *  각각의 Action, Service, DAO 메서드 작성
 */
// document.querySelector(".btn-secondary").addEventListener("submit",()=>location.href=path);

const form=document.querySelector("form");
form.addEventListener("submit",(e)=>{
	e.preventDefault();
	const keyword=document.querySelector("[name='keyword']");
	const sel=document.querySelector("[name='criteria']");
	// select 요소의 선택된 value 가져오기
	console.log(sel.value);
	
	if(keyword.value===""){
		alert("검색어를 확인해주세요.");
		keyword.select();
		return;
	} else if(sel.value==="검색기준선택"){
		alert("검색 기준을 확인해주세요.")
		return;
	}
	form.submit();
})