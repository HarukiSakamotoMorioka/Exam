<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">クラス登録</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                クラス新規登録
            </h2>

            <form action="ClassCreateExecute.action"
                  method="post"
                  class="mt-4">

                <!-- クラス番号 -->
                <div class="mb-3">
                    <label class="form-label">クラス番号</label>

                    <input type="text"
                           name="classNum"
                           class="form-control"
                           value="${param.classNum}"
                           placeholder="クラス番号を入力してください"
                           required>

                    <c:if test="${not empty classNumError}">
                        <div class="text-warning small mt-1">
                            ${classNumError}
                        </div>
                    </c:if>
                </div>

                <!-- ボタン -->
                <div class="mt-4">
                    <button type="submit" class="btn btn-secondary me-2">
                        登録して終了
                    </button>

                    <a href="ClassList.action" class="btn btn-link">
                        戻る
                    </a>
                </div>

            </form>

        </section>

    </c:param>

</c:import>
