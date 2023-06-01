/**
 * 1. 기존 비밀번호, 새 비밀번호, 새 비밀번호 확인 => 내용이 비어 있지 않도록 확인
 * 2. 새 비밀번호와 새 비밀번호 확인이 동일한지 확인
 */
const form=document.querySelector("form");
form.addEventListener("submit",(e)=>{
	e.preventDefault();
	const current=document.getElementById("floatingPassword1");
	const newnew=document.getElementById("floatingPassword2");
	const confirm=document.getElementById("floatingPassword3");
	if(current.value===""){
		alert("기존 비밀번호를 확인해주세요.");
		current.select();
		return;
	}else if(newnew.value===""){
		alert("새 비밀번호를 확인해주세요.");
		newnew.select();
		return;
	}else if(confirm.value===""){
		alert("새 비밀번호 확인을 확인해주세요.");
		confirm.select();
		return;
	} 
	if(newnew.value!==confirm.value){
		alert("새 비밀번호와 새비밀번호 확인이 일치하지 않습니다.");
		confirm.select();
		return;
	}
	form.submit();
})