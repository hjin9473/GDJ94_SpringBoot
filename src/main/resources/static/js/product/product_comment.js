/**
 * */
console.log("product_comment.js loaded");

// 댓글 목록이 삽입될 테이블 <tbody> (data-product-num을 가지고 있음)
const list = document.getElementById("list");
let num = list.getAttribute("data-product-num");

// ===================================================
// 1. 댓글 추가를 위한 DOM 요소 선택 (null 체크 없이 변수만 선언)
//    - detail.jsp에 ID가 'commentAddBtn'인 버튼과 'boardContents'인 textarea가 있다고 가정합니다.
// ===================================================
const commentAddBtn = document.getElementById("commentAddBtn");
const boardContents = document.getElementById("boardContents");
// 'commentForm'은 전체 폼이 아닌 개별 요소만 참조하므로 필요하지 않습니다.
// 'username'은 임시 값 대신, 로그인된 사용자 ID를 가져온다고 가정하고 처리합니다.


// ===================================================
// 2. 댓글 리스트를 가져오는 함수 (기존 코드 유지)
// ===================================================
function getCommentList(){
    // 기존 리스트 비우기
    list.innerHTML = ''; 
    
    fetch(`./commentList?productNum=${num}`)
        .then(r => r.json())
        .then(r => {
            r.forEach(dto =>{
                let tr = document.createElement("tr");
                let td = document.createElement("td");
                td.innerText=dto.username;
                tr.append(td);
                td = document.createElement("td");
                td.innerText=dto.boardContents;
                tr.append(td);
                td = document.createElement("td");
                td.innerText=dto.boardDate;
                tr.append(td);
                list.append(tr);
            });
        })
        .catch(e => console.log(e));
}

// 페이지 로드 시 댓글 리스트 가져오기
getCommentList();

// ===================================================
// 3. 댓글 추가 기능을 위한 이벤트 리스너 (⭐ 안정화 및 로직 실행 ⭐)
// ===================================================

// commentAddBtn 요소가 존재할 때만 이벤트 리스너를 추가하여 'Uncaught TypeError' 방지
if (commentAddBtn) {
    commentAddBtn.addEventListener("click", () => {
        
        // 유효성 검사
        if (!boardContents || boardContents.value.trim() === "") {
            alert("댓글 내용을 입력해 주세요.");
            return;
        }

        // 실제 사용자 이름은 서버 세션/시큐리티에서 가져오는 것이 원칙이지만,
        // 클라이언트에서 임시로 값을 넣어 전송합니다.
        const currentUsername = "LoggedInUser"; 

        // 댓글 데이터 준비 (productNum, boardContents, username)
        const commentData = {
            productNum: num,
            boardContents: boardContents.value,
            username: currentUsername 
        };

        // 서버로 POST 요청 (Controller의 @PostMapping("commentAdd") 호출)
        fetch("./commentAdd", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(commentData) // JSON 형태로 데이터 전송
        })
        .then(r => r.json())
        .then(result => {
            if (result === 1) { // 1은 성공 (Controller에서 1을 반환할 것으로 예상)
                alert("댓글 등록 성공!");
                boardContents.value = ''; // 입력창 초기화
                getCommentList(); // 댓글 리스트 갱신
            } else {
                alert("댓글 등록 실패!");
            }
        })
        .catch(e => console.error("AJAX Error:", e));
    });
} else {
    // 버튼을 찾지 못했다는 경고 (디버깅용)
    console.warn("Error: 'commentAddBtn' element not found. Please add the HTML input form to detail.jsp.");
}