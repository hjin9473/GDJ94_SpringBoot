<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 수정</title>
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/template/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">

				<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>
				<div class="container-fluid">
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">공지사항 수정하기</h1>
						<a href="#"
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
							class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
					</div>
					<div class="container-fluid">
					
					<form action="./update" method="post">
						
						<input type="hidden" name="boardNum" value="${dto.boardNum}">
						
						<div class="mb-3">
						  <label for="boardTitle" class="form-label">제목</label>
						  <input type="text" class="form-control" id="boardTitle" name="boardTitle" value="${dto.boardTitle}" required>
						</div>
						
						<div class="mb-3">
						  <label for="boardWriter" class="form-label">작성자</label>
						  <input type="text" class="form-control" id="boardWriter" name="boardWriter" value="${dto.boardWriter}" readonly>
						</div>
						
						<div class="mb-3">
						  <label for="boardContents" class="form-label">내용</label>
						  <textarea class="form-control" id="boardContents" name="boardContents" rows="10" required>${dto.boardContents}</textarea>
						</div>
						
						<div class="d-grid gap-2">
							<button type="submit" class="btn btn-warning">수정하기</button>
							<a href="javascript:history.back()" class="btn btn-secondary">취소</a>
						</div>
					</form>
					</div>

				</div>
				</div>
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2021</span>
					</div>
				</div>
			</footer>
			</div>

	</div>

	<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>
</body>
</html>