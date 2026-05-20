<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績管理 - 登録完了</c:param>

    <c:param name="content">

        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
            成績管理
        </h2>

        <!-- 完了メッセージ -->
        <div class="alert alert-success mx-4">
            登録が完了しました
        </div>

        <!-- ボタン配置 -->
        <div class="text-center mt-4">
            <!-- 戻るボタン（成績登録画面へ戻る） -->
            <a href="${pageContext.request.contextPath}/scoremanager/main/TestRegist.action"
               class="btn btn-secondary mx-2">
                戻る
            </a>

            <!-- 成績参照ボタン -->
            <a href="${pageContext.request.contextPath}/scoremanager/main/TestList.action"
               class="btn btn-primary mx-2">
                成績参照
            </a>
        </div>

    </c:param>
</c:import>
