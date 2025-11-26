<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
</head>
<body id="page-top">
	<div id="wrapper">
		<!-- side bar -->
		<c:import url="/WEB-INF/views/template/sidebar.jsp"></c:import>
		<!-- side bar -->
		
		<!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
       			
       			<!-- topbar -->
       			<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>
            	<!-- topbar -->
            	
            	<!-- Begin Page Content -->
                <div class="container-fluid">
                	<!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">공지사항</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row justify-content-center">
                    	<table class="table col-sm-8 mt-5">
                    		<thead class="thead-dark">
                    			<tr>
                    				<th>Num</th>
                    				<th>Title</th>
                    				<th>Writer</th>
                    				<th>Date</th>
                    				<th>Hit</th>
                    			</tr>
                    		</thead>
                    		<tbody>
                    			<c:forEach items="${list}" var="dto">
                    				<tr>
                    					<td>${dto.boardNum}</td>
                    					<td>${dto.boardTitle}</td>
                    					<td>${dto.boardWriter}</td>
                    					<td>${dto.boardDate}</td>
                    					<td>${dto.boardHit}</td>
                    				</tr>
                    			</c:forEach>
                    		</tbody>
                    	</table>
            		</div>
            		<div class="row justify-content-center">
                    	<nav aria-label="Page navigation example">
						  <ul class="pagination ">
						    <li class="page-item">
						      <a class="page-link" href="./list?page=${pager.begin-1}" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <c:forEach begin="${pager.begin}" end="${pager.end}" var="i">
						    <li class="page-item"><a class="page-link" href="./list?page=${i}">${i}</a></li>
						    </c:forEach>
						    <li class="page-item">
						      <a class="page-link" href="./list?page=${pager.end+1}" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						  </ul>
						</nav>
                    </div>
                    
                
                </div>
                <!-- /.container-fluid -->
            </div> 
            <!-- End of Main Content -->
            
            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->
        </div>
	
	</div>
	
<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>	
</body>
</html>