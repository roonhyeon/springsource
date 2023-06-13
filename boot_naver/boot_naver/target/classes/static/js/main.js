/**
 *  검색버튼 클릭 시 사용자 입력값 가져오기
 *  fetch("/api/restaurant/search")
 */

document.querySelector("#searchButton").addEventListener("click", () => {
  const query = document.querySelector("#searchBox").value;
  //기존 값 제거
  document.querySelector("#wish_image").removeAttribute("src");
  document.querySelector("#wish_title").innerHTML = "";
  document.querySelector("#wish_category").innerHTML = "";
  document.querySelector("#wish_address").innerHTML = "";
  document.querySelector("#wish_road_address").innerHTML = "";
  document.querySelector("#wish_link").removeAttribute("href");

  fetch("/api/restaurant/search?query=" + query)
    .then((response) => {
      if (!response.ok) {
        throw new Error("잘못된 요청");
      }
      return response.json();
    })
    .then((data) => {
      //console.log(data);
      //search result 에 정보 보여주기
      // img src 변경 - data 안에 imageLink
      if (data.imageLink) {
        document.querySelector("#wish_image").src = data.imageLink;
      }
      // 다른 정보들도 보여주기
      document.querySelector("#wish_title").innerHTML = data.title;
      document.querySelector("#wish_category").innerHTML = data.category;
      document.querySelector("#wish_address").innerHTML = data.address;
      document.querySelector("#wish_road_address").innerHTML = data.roadAddress;
      if (data.homePageLink) {
        document.querySelector("#wish_link").href = data.homePageLink;
      }
      //style 속성 변경
      document.querySelector("#search-result").style.visibility = "visible";
    })
    .catch((error) => console.log(error));
});

// 위시리스트 추가 클릭 시
// 검색 결과를 자바스크립트 객체로 생성
// fetch() + post
document.querySelector("#wishButton").addEventListener("click", () => {
  const wishItem = {
    title: document.querySelector("#wish_title").innerHTML,
    category: document.querySelector("#wish_category").innerHTML,
    address: document.querySelector("#wish_address").innerHTML,
    roadAddress: document.querySelector("#wish_road_address").innerHTML,
    homePageLink: document.querySelector("#wish_link").href,
    imageLink: document.querySelector("#wish_image").src,
  };

  fetch("/api/restaurant", {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(wishItem),
  })
    .then((response) => response.json())
    .then((data) => console.log(data))
    .catch((error) => console.log(error));
});
