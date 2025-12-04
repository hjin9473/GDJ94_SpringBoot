<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 수정</title> <c:import url="/WEB-INF/views/template/head.jsp"></c:import>
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
						<h1 class="h3 mb-0 text-gray-800">회원 정보 수정</h1> </div>
					<div class="container-fluid">
						
		        <form action="./update" method="post" enctype="multipart/form-data">
		            
                            <input type="hidden" name="username" value="${dto.username}">
		            
							<div class="mb-3">
								<label for="username" class="form-label">아이디</label> 
								<input type="text" class="form-control" id="username" name="username" 
                                       value="${dto.username}" readonly>
							</div>
							
							<div class="mb-3">
								<label for="password" class="form-label">비밀번호</label> 
								<input type="password" class="form-control" id="password"	name="password" placeholder="변경할 비밀번호를 입력하세요 (미입력시 유지)">
							</div>
                            
							<div class="mb-3">
								<label for="name" class="form-label">이름</label>
								<input type="text" class="form-control" id="name" name="name" required value="${dto.name}">
							</div>
                            
							<div class="mb-3">
								<label for="email" class="form-label">이메일</label>
								<input type="email" class="form-control" id="email" name="email" required value="${dto.email}">
							</div>
                            
							<div class="mb-3">
								<label for="phone" class="form-label">전화번호</label>
								<input type="text" class="form-control" id="phone" name="phone" value="${dto.phone}">
							</div>
                            
							<div class="mb-3">
								<label for="birth" class="form-label">생일</label>
								<input type="date" class="form-control" id="birth" name="birth" 
       					value="<fmt:formatDate value='${dto.birth}' pattern='yyyy-MM-dd'/>"> 
       				</div>
                            
                            <div class="mb-3">
								<label for="attach" class="form-label">프로필 사진 변경</label>
								<input type="file" class="form-control" id="attach" name="attach">
							</div>
							
							<div class="d-grid gap-2">
								<button type="submit" class="btn btn-primary">수정 완료</button> <a href="./mypage" class="btn btn-secondary">취소</a> </div>
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