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
                学生情報削除
            </h2>

            <!-- 学生情報 -->
            <div class="mb-3">

                <label class="form-label">
                    入学年度
                </label>

                <div class="form-control-plaintext">
                    ${student.entYear}
                </div>

            </div>

            <div class="mb-3">

                <label class="form-label">
                    学生番号
                </label>

                <div class="form-control-plaintext">
                    ${student.no}
                </div>

            </div>

            <div class="mb-3">

                <label class="form-label">
                    氏名
                </label>

                <div class="form-control-plaintext">
                    ${student.name}
                </div>

            </div>

            <div class="mb-4">

                <label class="form-label">
                    クラス
                </label>

                <div class="form-control-plaintext">
                    ${student.classNum}
                </div>

            </div>

            <!-- 確認メッセージ -->
            <p class="fs-5 text-danger">
                この学生情報を削除してもよろしいですか？
            </p>

            <!-- 削除フォーム -->
            <form action="StudentDeleteExecute.action"
                  method="post"
                  class="mt-4">

                <input type="hidden"
                       name="no"
                       value="${student.no}">

                <input type="submit"
                       value="削除"
                       class="btn btn-danger px-4">

                <a href="StudentList.action"
                   class="btn btn-secondary ms-3">

                    戻る

                </a>

            </form>

        </section>

    </c:param>

</c:import>