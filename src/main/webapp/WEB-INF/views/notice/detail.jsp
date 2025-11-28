<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 상세</title>
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/template/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>

				<div class="container-fluid">
					<h1 class="h3 mb-4 text-gray-800">공지사항 상세</h1>

					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
								${dto.boardTitle}</h6>
						</div>
						<div class="card-body">
							<p>
								<strong>작성자:</strong> ${dto.boardWriter}
							</p>
							<p>
								<strong>작성일:</strong> ${dto.boardDate}
							</p>
							<p>
								<strong>조회수:</strong> ${dto.boardHit}
							</p>
							<hr>
							<p>${dto.boardContents}</p>
						</div>
					</div>

					<div class="d-flex justify-content-end">
						<a href="./update?boardNum=${dto.boardNum}"
							class="btn btn-primary mr-2">수정하기</a>
						<button type="button" class="btn btn-danger" data-toggle="modal"
							data-target="#deleteModal">삭제하기</button>
						<a href="javascript:history.back()" class="btn btn-secondary ml-2">목록으로</a>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
		aria-labelledby="deleteModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="deleteModalLabel">정말 삭제하시겠습니까?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">${dto.boardNum}번 게시물을 삭제합니다.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">취소</button>

					<form action="./delete" method="post">
						<input type="hidden" name="boardNum" value="${dto.boardNum}">
						<button type="submit" class="btn btn-danger">삭제하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>
</body>
</html>