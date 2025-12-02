/**
 * 파일 추가 버튼 클릭 시
 */

const fileAdd = document.getElementById("fileAdd");
const files = document.getElementById("files");

files.addEventListener("click", (e) => {
	let d = e.target;
	if (d.classList.contains("del")) {
		d.parentElement.remove();
	}
})

fileAdd.addEventListener("click", () => {
	console.log("파일 추가 버튼 클릭됨");

	let div = document.createElement("div");
	div.classList.add("file-input-group");

	let input = document.createElement("input");
	input.type = "file";
	input.name = "attach";

	let button = document.createElement("button");
	button.type = "button"
	button.innerText = "X";

	button.classList.add("del");

	const max = 5;
	const currentFiles = files.children.length;
	if (currentFiles >= max) {
		alert("최대 " + max + "개까지만 파일 첨부가 가능합니다.");
		return;
	}

	div.append(input);
	div.append(button);

	files.append(div);

});