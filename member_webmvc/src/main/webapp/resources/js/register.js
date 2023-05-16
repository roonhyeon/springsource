/** 
 * 1. form의 모든 요소가 비어있지 않은지 확인
 */
/*const form=document.querySelector("form");
form.addEventListener("submit",(e)=>{
	e.preventDefault();
	const userid=document.getElementById("userid");
	const password=document.getElementById("password");
	const name=document.getElementById("name");
	const gender=document.querySelector("input[type=radio][name=gender]:checked");
	const email=document.getElementById("email");
	if(userid.value===""){
		alert("아이디를 확인해주세요.");
		userid.select();
		return;
	}else if(password.value===""){
		alert("비밀번호를 확인해주세요.");
		password.select();
		return;
	}else if(name.value===""){
		alert("이름를 확인해주세요.");
		name.select();
		return;
	}else if(!gender){
		alert("성별을 확인해주세요.");
		return;
	}else if(email.value===""){
		alert("이메일을 확인해주세요.");
		email.select();
		return;
	}
	form.submit();
})*/

const form=document.querySelector("form");
form.addEventListener("submit",(e)=>{
	if(!form.checkValidity()){
		e.preventDefault();
		e.stopPropagation();
	}
	form.classList.add("was-validated");
})

document.querySelector(".btn-danger").addEventListener("click",()=>{
	// 사용자가 입력한 userid 가져오기
	const userid=document.querySelector("#userid").value;
	fetch("dupId.do", {
		method:"post",
		body: new URLSearchParams({userid:userid})
	})
	.then((response)=>response.text())
	.then((result)=>{
		console.log(result);
		if(result.trim()==="true"){
			alert("아이디를 사용할 수 있습니다.");
		}else{
			alert("아이디를 사용할 수 없습니다.");
		}
	});
})

