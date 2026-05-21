
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <!-- ▼ 画面タイトル -->
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
                科目情報変更
            </h2>

            <form action="SubjectUpdate.action" method="post" class="px-4">

                <!-- ▼ 科目コード（変更不可） -->
                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <input type="text" name="subjectCode" class="form-control"
                           value="${subject.cd}" readonly>
                </div>
                           <!-- ▼ エラーメッセージ（必要に応じて） -->
	            <c:if test="${not empty errorMsg}">
	                <div class="error-yellow">${errorMsg}</div>
	            </c:if>
                

                <!-- ▼ 科目名 -->
                <div class="mb-3">
                    <label class="form-label">科目名</label>
                    <input type="text" name="subjectName" class="form-control"
                           value="${subject.name}" required>
                </div>

                <div class="mt-4">
                    <input type="submit" value="変更" class="btn btn-primary me-2">
                    <input type="button" value="戻る" class="btn btn-secondary"
                           onclick="location.href='SubjectList.action'">
                </div>

             </form>
        </section>
    </c:param>
</c:import>
