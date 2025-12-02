<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
						
		        <form action="./register" method="post">
		            
							<div class="mb-3">
								<label for="username" class="form-label">아이디</label> 
								<input type="text" class="form-control" id="username" name="username" required>
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">비밀번호</label> 
								<input type="password" class="form-control" id="password"	name="password" required>
							</div>
							<div class="mb-3">
								<label for="name" class="form-label">이름</label>
								<input type="text" class="form-control" id="name" name="name" required>
							</div>
							<div class="mb-3">
								<label for="email" class="form-label">이메일</label>
								<input type="email" class="form-control" id="email" name="email" required>
							</div>
							<div class="mb-3">
								<label for="phone" class="form-label">전화번호</label>
								<input type="text" class="form-control" id="phone" name="phone">
							</div>
							<div class="mb-3">
								<label for="birth" class="form-label">생일</label>
								<input type="date" class="form-control" id="birth" name="birth">
							</div>
							
							<div class="d-grid gap-2">
								<button type="submit" class="btn btn-primary">회원가입</button>
								<a href="/" class="btn btn-secondary">취소</a>
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