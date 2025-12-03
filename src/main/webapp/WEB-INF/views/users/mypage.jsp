<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
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
						<h1 class="h3 mb-0 text-gray-800">내 정보</h1>
					</div>
					<div class="container-fluid">

						<c:set var="user" value="${sessionScope.usersDTO}" />

						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">회원 정보 상세</h6>
							</div>
							<div class="card-body">

								<div class="mb-4 text-center">
									<c:choose>
										<c:when
											test="${not empty user.profileDTOs and not empty user.profileDTOs[0].fileName}">
											<img src="/files/profile/${user.profileDTOs[0].fileName}"
												alt="Profile Image" class="img-profile rounded-circle"
												style="width: 150px; height: 150px; object-fit: cover;">
										</c:when>
										<c:otherwise>
											<img src="/img/undraw_profile.svg"
												alt="Default Profile Image"
												class="img-profile rounded-circle"
												style="width: 150px; height: 150px; object-fit: cover;">
										</c:otherwise>
									</c:choose>
								</div>

								<div class="mb-3 row">
									<label class="col-sm-2 col-form-label font-weight-bold">아이디</label>
									<div class="col-sm-10">
										<input type="text" readonly class="form-control-plaintext"
											value="${user.username}">
									</div>
								</div>

								<div class="mb-3 row">
									<label class="col-sm-2 col-form-label font-weight-bold">이름</label>
									<div class="col-sm-10">
										<input type="text" readonly class="form-control-plaintext"
											value="${user.name}">
									</div>
								</div>

								<div class="mb-3 row">
									<label class="col-sm-2 col-form-label font-weight-bold">이메일</label>
									<div class="col-sm-10">
										<input type="text" readonly class="form-control-plaintext"
											value="${user.email}">
									</div>
								</div>

								<div class="mb-3 row">
									<label class="col-sm-2 col-form-label font-weight-bold">전화번호</label>
									<div class="col-sm-10">
										<input type="text" readonly class="form-control-plaintext"
											value="${user.phone}">
									</div>
								</div>

								<div class="mb-3 row">
									<label class="col-sm-2 col-form-label font-weight-bold">생일</label>
									<div class="col-sm-10">
										<input type="text" readonly class="form-control-plaintext"
											value="${user.birth}">
									</div>
								</div>

								<div class="mt-4 text-right">
									<a href="./update" class="btn btn-primary">정보 수정</a>
									<button type="button" class="btn btn-danger"
										onclick="confirmDelete()">회원 탈퇴</button>
								</div>

								<form id="deleteForm" action="./delete" method="post"
									style="display: none;">
									<input type="hidden" name="username" value="${user.username}">
								</form>

								<script>
									function confirmDelete() {
										if (confirm("정말로 회원 탈퇴를 진행하시겠습니까? 모든 정보가 삭제됩니다.")) {
											// 확인 시 POST 요청으로 폼 제출
											document.getElementById(
													'deleteForm').submit();
										}
									}
								</script>
							</div>
						</div>

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