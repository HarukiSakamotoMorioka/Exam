<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">クラス削除</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                クラス削除確認
            </h2>

            <p>以下のクラスを削除します。よろしいですか？</p>

            <div class="border rounded p-3 mb-3">
                <strong>クラス番号：</strong> ${classNum}
            </div>

            <form action="ClassDeleteExecute.action" method="post">

                <input type="hidden" name="classNum" value="${classNum}">

                <button type="submit" class="btn btn-danger">
                    削除する
                </button>

                <a href="ClassList.action" class="btn btn-link ms-2">
                    戻る
                </a>

            </form>

        </section>

    </c:param>

</c:import>
