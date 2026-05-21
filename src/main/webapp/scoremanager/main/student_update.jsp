<%@page language="java" contentType="text/html; charset=UTF-8"
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

            <!-- フォーム -->
            <form action="StudentUpdateExecute.action"
                  method="post">

                <!-- 入学年度 -->
                <div class="mb-3">

                    <label class="form-label">
                        入学年度
                    </label>

                    <div class="form-control-plaintext">
                        ${student.entYear}
                    </div>

                    <!-- hidden -->
                    <input type="hidden"
                           name="ent_year"
                           value="${student.entYear}">

                </div>

                <!-- 学生番号 -->
                <div class="mb-3">

                    <label class="form-label">
                        学生番号
                    </label>

                    <div class="form-control-plaintext">
                        ${student.no}
                    </div>

                    <!-- hidden -->
                    <input type="hidden"
                           name="no"
                           value="${student.no}">

                </div>

                <!-- 氏名 -->
                <div class="mb-3">

                    <label class="form-label">
                        氏名
                    </label>

                    <input type="text"
                           name="name"
                           class="form-control"
                           maxlength="30"
                           required
                           value="${student.name}">

                </div>

                <!-- クラス -->
                <div class="mb-3">

                    <label class="form-label">
                        クラス
                    </label>

                    <select name="class_num"
                            class="form-select">

                        <c:forEach var="num"
                                   items="${classList}">

                            <option value="${num}"
                                <c:if test="${num == student.classNum}">
                                    selected
                                </c:if>>

                                ${num}

                            </option>

                        </c:forEach>

                    </select>

                </div>

                <!-- 在学中 -->
                <div class="mb-4 form-check">

                    <input class="form-check-input"
                           type="checkbox"
                           name="is_attend"
                           id="attend"

                        <c:if test="${student.attend}">
                            checked
                        </c:if>
                    >

                    <label class="form-check-label"
                           for="attend">

                        在学中

                    </label>

                </div>

                <!-- ボタン -->
                <div class="mt-4">

                    <button type="submit"
                            class="btn btn-primary me-3">

                        変更

                    </button>

                    <a href="StudentList.action"
                       class="btn btn-secondary">

                        戻る

                    </a>

                </div>

            </form>

        </section>

    </c:param>

</c:import>