<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>成績参照結果</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container mt-4">

  <!-- ★ mode = student（学生番号検索） -->
	<c:if test="${mode == 'student'}">
	
	    <h3 class="mb-4">
	        氏名：${student.name}（${student.no}）
	    </h3>
	
	    <!-- ▼ 成績が存在しない場合：普通の文字だけ -->
	    <c:if test="${empty scores}">
	        <p>成績情報が存在しませんでした</p>
	    </c:if>
	
	    <!-- ▼ 成績が存在する場合のみ表示 -->
	    <c:if test="${not empty scores}">
	        <table class="table table-bordered">
	            <thead class="table-light">
	                <tr>
	                    <th>科目名</th>
	                    <th>科目コード</th>
	                    <th>回数</th>
	                    <th>点数</th>
	                </tr>
	            </thead>
	
	            <tbody>
	                <c:forEach var="s" items="${scores}">
	                    <tr>
	                        <td>${s.subjectName}</td>
	                        <td>${s.subjectCd}</td>
	                        <td>${s.no}</td>
	                        <td>${s.point}</td>
	                    </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	    </c:if>
	</c:if>



	<!-- ★ mode = subject（科目検索） -->
	<c:if test="${mode == 'subject'}">
	
	    <!-- ▼ 成績が存在しない場合：普通の文字だけ -->
	    <c:if test="${empty scores}">
	        <p>学生情報が存在しませんでした</p>
	    </c:if>
	
	    <!-- ▼ 成績が存在する場合のみ表示 -->
	    <c:if test="${not empty scores}">
	        <h4 class="mb-3">科目：${scores[0].subjectName}</h4>
	
	        <table class="table table-bordered">
	            <thead class="table-light">
	                <tr>
	                    <th>入学年度</th>
	                    <th>クラス</th>
	                    <th>学生番号</th>
	                    <th>氏名</th>
	                    <th>1回</th>
	                    <th>2回</th>
	                </tr>
	            </thead>
	
	            <tbody>
	                <c:forEach var="s" items="${scores}">
	                    <tr>
	                        <td>${s.entYear}</td>
	                        <td>${s.classNum}</td>
	                        <td>${s.studentNo}</td>
	                        <td>${s.studentName}</td>
	
	                        <td>
	                            <c:choose>
	                                <c:when test="${s.point1 != 0}">${s.point1}</c:when>
	                                <c:otherwise>-</c:otherwise>
	                            </c:choose>
	                        </td>
	
	                        <td>
	                            <c:choose>
	                                <c:when test="${s.point2 != 0}">${s.point2}</c:when>
	                                <c:otherwise>-</c:otherwise>
	                            </c:choose>
	                        </td>
	                    </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	    </c:if>
	
	</c:if>





    <a href="TestList.action" class="btn btn-secondary mt-3">戻る</a>

</body>
</html>
