<%@page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="me-4">

            <!-- タイトル -->
            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                学生情報変更
            </h2>

            <!-- 完了メッセージ -->
            <div class="alert alert-success text-center py-3 fs-5">
                変更が完了しました
            </div>

            <!-- ボタン -->
            <div class="mt-4">

                <a href="StudentUpdate.action"
                   class="btn btn-secondary me-3">

                    戻る

                </a>

                <a href="StudentList.action"
                   class="btn btn-primary">

                    学生一覧

                </a>

            </div>

        </section>

    </c:param>

</c:import>