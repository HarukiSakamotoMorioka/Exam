<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

            <!-- 登録完了メッセージ -->
            <div class="alert alert-success px-4" role="alert">
                登録が完了しました
            </div>

            <div class="mt-4 px-4">
			    <ul class="nav nav-pills flex-column mb-auto px-4">
					<li class="nav-item mx-3 mb-3"><a href="TestRegist.action">戻る</a></li>
					<li class="nav-item mx-3 mb-3"><a href="TestList.action">科目一覧</a></li>

				</ul>
			</div>


        </section>
    </c:param>
</c:import>