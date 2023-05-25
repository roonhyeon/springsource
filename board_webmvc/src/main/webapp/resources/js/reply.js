/**
 *  댓글 처리 자바스크립트 모듈
 */
let replyService = (function () {
  // reply: 댓글 작성 자바스크립트 객체
  // callback: function

  function add(reply, callback) {
    console.log("add 함수");

    fetch("/replies/new", {
      method: "post",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(reply),
    })
      // 비동기식 함수
      .then((response) => {
        // 결과가 도착하게 되면 자동 호출(비동기 호출)
        // 서버가 200번이 아닌 경우 실행됨 => catch에 넘겨준다.
        if (!response.ok) {
          throw new Error("입력 오류");
        }
        // 서버가 200번인 경우 실행됨 => return 값은 바로 다음에 오는 then에 넘겨준다.
        return response.text(); //success 문자열 받음
      })
      // 처음 then()이 return을 하면 호출됨
      .then((data) => {
        // 넘겨받은 함수를 호출하게 된다.
        if (callback) {
          callback(data);
        }
      })
      // 위에서 넘어온 입력 오류 받아서 출력해줌
      .catch((error) => console.log(error));
  } // add 종료

  function getList(param, callback) {
    let bno = param.bno;
    let page = param.page;

    fetch("/replies/pages/" + bno + "/" + page)
      .then((response) => {
        if (!response.ok) {
          throw new Error("리스트 없음");
        }
        return response.json();
      })
      .then((data) => {
        // data가 도착해서 함수가 호출되면 넘겨받은 함수 호출
        if (callback) {
          callback(data);
        }
      })
      .catch((error) => console.log(error));
  } // getList 종료

  // milisecond가 timeVal에 넘겨진다.
  function displayTime(timeVal) {
    const today = new Date(); // 오늘 날짜

    // 오늘 날짜-댓글 작성 날짜
    let gap = today.getTime() - timeVal;

    // 댓글 작성 날짜 Date 객체 생성
    let dateObj = new Date(timeVal);

    let str = "";
    // 초 단위로 바꾸기 위한 코드
    // 작성 날짜를 보여줄 때 24시간 안에 작성했느냐? 넘었느냐?
    // 24시간 안이라면 시분초, 넘었다면 년/월/일 형태로 출력
    if (gap < 1000 * 60 * 60 * 24) {
      let hh = dateObj.getHours(); // 1~9시 10~12시
      let mi = dateObj.getMinutes();
      let ss = dateObj.getSeconds();

      // 시분초 한자리를 두자리로 처리
      return [
        (hh > 9 ? "" : "0") + hh,
        ":",
        (mi > 9 ? "" : "0") + mi,
        ":",
        (ss > 9 ? "" : "0") + ss,
      ].join("");
    } else {
      const yy = dateObj.getFullYear();
      const mm = dateObj.getMonth() + 1; // 월은 0부터 시작이므로 +1을 함
      const dd = dateObj.getDate();
      return [
        yy,
        "/",
        (mm > 9 ? "" : "0") + mm,
        "/",
        (dd > 9 ? "" : "0") + dd,
      ].join("");
    }
  }

  function get(rno, callback) {
    fetch("/replies/" + rno)
      .then((response) => {
        if (!response.ok) {
          throw new Error("가져올 댓글 없음");
        }
        return response.json();
      })
      .then((data) => {
        if (callback) {
          callback(data);
        }
      })
      .catch((error) => console.log(error));
  }

  function update(reply, callback) {
    fetch("/replies/" + reply.rno, {
      method: "put",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(reply),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("수정 실패");
        }
        return response.text();
      })
      .then((data) => {
        if (callback) {
          callback(data);
        }
      })
      .catch((error) => console.log(error));
  }

  // 외부에서 접근 가능한 함수 지정
  return {
    add: add,
    getList: getList,
    displayTime: displayTime,
    get: get,
    update: update,
  };
})();
