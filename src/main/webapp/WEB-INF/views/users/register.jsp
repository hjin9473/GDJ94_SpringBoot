<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
						<h1 class="h3 mb-0 text-gray-800">회원가입</h1>
					</div>
					<div class="container-fluid">

						<form:form modelAttribute="usersDTO" action="./register"
							method="post" novalidate="novalidate"
							enctype="multipart/form-data">

							<div class="mb-3">
								<label for="username" class="form-label">아이디</label>
								<form:input path="username" cssClass="form-control"
									id="username" required="required" />
								<form:errors path="username" cssClass="text-danger"></form:errors>
							</div>

							<div class="mb-3">
								<label for="password" class="form-label">비밀번호</label>
								<form:password path="password" cssClass="form-control"
									id="password" required="required" />
								<form:errors path="password" cssClass="text-danger"></form:errors>
							</div>

							<div class="mb-3">
								<label for="name" class="form-label">이름</label>
								<form:input path="name" cssClass="form-control" id="name"
									required="required" />
								<form:errors path="name" cssClass="text-danger"></form:errors>
							</div>

							<div class="mb-3">
								<label for="email" class="form-label">이메일</label>
								<form:input path="email" cssClass="form-control" id="email"
									required="required" />
								<form:errors path="email" cssClass="text-danger"></form:errors>
							</div>

							<div class="mb-3">
								<label for="phone" class="form-label">전화번호</label>
								<form:input path="phone" cssClass="form-control" id="phone" />
							</div>

							<div class="mb-3">
								<label for="birth" class="form-label">생일</label>
								<form:input path="birth" cssClass="form-control" id="birth"
									type="date" />
							</div>

							<div class="mb-3">
								<label for="attach" class="form-label">프로필 사진</label> <input
									type="file" class="form-control" id="attach" name="attach">
							</div>

							<div class="d-grid gap-2">
								<button type="submit" class="btn btn-primary">회원가입</button>
								<a href="./list" class="btn btn-secondary">취소</a>
							</div>
						</form:form>

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