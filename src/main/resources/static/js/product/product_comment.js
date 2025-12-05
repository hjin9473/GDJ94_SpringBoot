/**
 * */
console.log("dsffsdfsdf");

const list = document.getElementById("list");
let num = list.getAttribute("data-product-num");

// ===================================================
// 1. 댓글 추가를 위한 DOM 요소 선택
// (detail.jsp에 댓글 입력 폼이 있다고 가정)
// ===================================================
const commentAddBtn = document.getElementById("commentAddBtn"); // 등록 버튼
const commentForm = document.getElementById("commentForm");     // 댓글 폼 요소
const boardContents = document.getElementById("boardContents"); // 댓글 내용 입력 필드
// ... (username 필드도 필요)


// ===================================================
// 2. 댓글 리스트를 가져오는 함수 (기존 코드)
// ===================================================
function getCommentList(){
    // 기존 리스트 비우기
    list.innerHTML = ''; 
    
    fetch(`./commentList?productNum=${num}`)
        .then(r => r.json())
        .then(r => {
            r.forEach(dto =>{
                let tr = document.createElement("tr");
                // ... (댓글 데이터 표시 로직)
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
// 3. 댓글 추가 기능을 위한 이벤트 리스너 (⭐ 누락된 로직 ⭐)
// ===================================================
commentAddBtn.addEventListener("click", () => {
    
    // 댓글 데이터 준비 (productNum, boardContents, username)
    const commentData = {
        productNum: num,
        boardContents: boardContents.value,
        username: "testUser" // 실제 로그인된 유저 ID를 사용해야 함
    };

    // 서버로 POST 요청
    fetch("./commentAdd", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(commentData) // JSON 형태로 데이터 전송
    })
    .then(r => r.json())
    .then(result => {
        if (result === 1) {
            alert("댓글 등록 성공!");
            boardContents.value = ''; // 입력창 초기화
            getCommentList(); // 댓글 리스트 갱신
        } else {
            alert("댓글 등록 실패!");
        }
    })
    .catch(e => console.log("AJAX Error:", e));
});