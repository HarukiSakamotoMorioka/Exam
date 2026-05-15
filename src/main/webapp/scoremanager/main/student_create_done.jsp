<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">学生登録完了</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

            <!-- 完了メッセージ -->
            <div class="alert alert-success mx-4 mt-3">
                登録が完了しました
            </div>

            <!-- リンク -->
            <div class="mt-4 px-4">
                <a href="StudentList.action" class="me-4">戻る</a>
                <a href="StudentList.action">学生一覧</a>
            </div>
        </section>
    </c:param>
</c:import>