<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">更新完了</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                更新完了
            </h2>

            <p class="fs-5">クラスの更新が完了しました。</p>

            <a href="ClassList.action" class="btn btn-primary mt-3">
                クラス一覧へ
            </a>

        </section>

    </c:param>

</c:import>
