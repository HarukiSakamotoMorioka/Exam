<<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

            

            <form action="SubjectCreate.action" method="post" class="px-4">

                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <input type="text" name="subjectCode" class="form-control"
                           placeholder="科目コードを入力してください"
                           value="${param.subjectCode}">
                </div>
                
			
			<!-- ▼ 科目コード文字数エラー -->
			<c:if test="${not empty errorLengthMsg}">
			    <div class="error-yellow">${errorLengthMsg}</div>
			</c:if>
			
			<!-- ▼ 科目コード重複エラー -->
			<c:if test="${not empty errorDuplicateMsg}">
			    <div class="error-yellow">${errorDuplicateMsg}</div>
			</c:if>
                

                <div class="mb-3">
                    <label class="form-label">科目名</label>
                    <input type="text" name="subjectName" class="form-control"
                           placeholder="科目名を入力してください"
                           value="${param.subjectName}" required>
                </div>

                <div class="mt-4">
                    <input type="submit" value="登録" class="btn btn-primary me-2">
                    <input type="button" value="戻る" class="btn btn-secondary"
                           onclick="location.href='SubjectList.action'">
                </div>

            </form>
        </section>
    </c:param>
</c:import>
