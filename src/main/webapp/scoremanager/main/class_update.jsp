<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">クラス変更</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                クラス番号の変更
            </h2>

            <form action="ClassUpdateExecute.action"
                  method="post"
                  class="mt-4">

                <input type="hidden" name="oldNum" value="${oldNum}">

                <div class="mb-3">
                    <label class="form-label">新しいクラス番号</label>

                    <input type="text"
                           name="newNum"
                           class="form-control"
                           value="${oldNum}"
                           required>
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-secondary me-2">
                        更新して終了
                    </button>

                    <a href="ClassList.action" class="btn btn-link">
                        戻る
                    </a>
                </div>

            </form>

        </section>

    </c:param>

</c:import>
