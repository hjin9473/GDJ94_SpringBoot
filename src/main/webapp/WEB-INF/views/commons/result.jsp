<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>처리 결과</title>
</head>
<body>
    <script>
        // 1. Controller에서 전달한 메시지(msg)를 alert 창으로 표시
        alert("${msg}");
        
        // 2. Controller에서 전달한 URL(url)로 페이지 이동
        location.href = "${url}";
    </script>
</body>
</html>