<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">学生登録</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生登録</h2>

            <form action="StudentCreateExecute.action" method="post" class="mt-4">

                <!-- 入学年度 -->
                <div class="mb-3">
                    <label class="form-label">入学年度</label>
                    <select name="ent_year" class="form-select">
                        <option value="">選択してください</option>
                        <c:forEach var="y" items="${entYearList}">
                            <option value="${y}" <c:if test="${param.ent_year == y}">selected</c:if>>
                                ${y}
                            </option>
                        </c:forEach>
                    </select>

                    <c:if test="${not empty entYearError}">
                        <div class="text-danger">${entYearError}</div>
                    </c:if>
                </div>

                <!-- 学生番号 -->
                <div class="mb-3">
                    <label class="form-label">学生番号</label>
                    <input type="text" name="no" class="form-control"
                           value="${param.no}" placeholder="学生番号を入力してください">

                    <c:if test="${not empty noError}">
                        <div class="text-danger">${noError}</div>
                    </c:if>

                    <c:if test="${not empty noDupError}">
                        <div class="text-danger">${noDupError}</div>
                    </c:if>
                </div>

                <!-- 氏名 -->
                <div class="mb-3">
                    <label class="form-label">氏名</label>
                    <input type="text" name="name" class="form-control"
                           value="${param.name}" placeholder="氏名を入力してください">

                    <c:if test="${not empty nameError}">
                        <div class="text-danger">${nameError}</div>
                    </c:if>
                </div>

                <!-- クラス -->
                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <select name="class_num" class="form-select">
                        <c:forEach var="c" items="${classList}">
                            <option value="${c}" <c:if test="${param.class_num == c}">selected</c:if>>
                                ${c}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- ボタン -->
                <div class="mt-4">
                    <button type="submit" class="btn btn-primary me-2">登録して終了</button>
                    <a href="StudentList.action" class="btn btn-secondary">戻る</a>
                </div>

            </form>
        </section>
    </c:param>
</c:import>
