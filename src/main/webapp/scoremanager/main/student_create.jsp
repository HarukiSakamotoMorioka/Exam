```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">
        学生登録
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                学生情報登録
            </h2>

            <form action="StudentCreateExecute.action"
                  method="post"
                  class="mt-4">

                <!-- 入学年度 -->
                <div class="mb-3">

                    <label class="form-label">
                        入学年度
                    </label>

                    <select name="ent_year"
                            class="form-select">

                        <option value="">
                            --------
                        </option>

                        <c:forEach var="y" items="${entYearList}">

                            <option value="${y}"
                                <c:if test="${param.ent_year == y}">
                                    selected
                                </c:if>>

                                ${y}

                            </option>

                        </c:forEach>

                    </select>

                    <!-- 入学年度エラー -->
                    <c:if test="${not empty entYearError}">
                        <div class="text-warning small mt-1">
                            ${entYearError}
                        </div>
                    </c:if>

                </div>

                <!-- 学生番号 -->
                <div class="mb-3">

                    <label class="form-label">
                        学生番号
                    </label>

                    <input type="text"
                           name="no"
                           class="form-control"
                           value="${param.no}"
                           placeholder="学生番号を入力してください"
                           required>

                    <!-- 重複エラー -->
                    <c:if test="${not empty noDupError}">
                        <div class="text-warning small mt-1">
                            ${noDupError}
                        </div>
                    </c:if>

                </div>

                <!-- 氏名 -->
                <div class="mb-3">

                    <label class="form-label">
                        氏名
                    </label>

                    <input type="text"
                           name="name"
                           class="form-control"
                           value="${param.name}"
                           placeholder="氏名を入力してください"
                           required>

                </div>

                <!-- クラス -->
                <div class="mb-3">

                    <label class="form-label">
                        クラス
                    </label>

                    <select name="class_num"
                            class="form-select">

                        <c:forEach var="c" items="${classList}">

                            <option value="${c}"
                                <c:if test="${param.class_num == c}">
                                    selected
                                </c:if>>

                                ${c}

                            </option>

                        </c:forEach>

                    </select>

                </div>

                <!-- ボタン -->
                <div class="mt-4">

                    <button type="submit"
                            class="btn btn-secondary me-2">

                        登録して終了

                    </button>

                    <a href="StudentList.action"
                       class="btn btn-link">

                        戻る

                    </a>

                </div>

            </form>

        </section>

    </c:param>

</c:import>
```
