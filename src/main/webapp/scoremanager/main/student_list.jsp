<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                学生情報
            </h2>

            <div class="my-2 text-end px-4">
                <a href="${pageContext.request.contextPath}/scoremanager/main/StudentCreate.action">新規登録</a>
            </div>

            <%-- 絞り込みフォーム --%>
            <form method="get" action="StudentList.action">

                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

                    <%-- 入学年度 --%>
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">
                            入学年度
                        </label>

                        <select class="form-select"
                                id="student-f1-select"
                                name="ent_year">

                            <option value="">--------</option>

                            <c:forEach var="year" items="${entYearList}">
                                <option value="${year}"
                                    <c:if test="${year == selectedYear}">
                                        selected
                                    </c:if>>
                                    ${year}
                                </option>
                            </c:forEach>

                        </select>
                    </div>

                    <%-- クラス --%>
                    <div class="col-4">
                        <label class="form-label" for="student-f2-select">
                            クラス
                        </label>

                        <select class="form-select"
                                id="student-f2-select"
                                name="class_num">

                            <option value="">--------</option>

                            <c:forEach var="num" items="${classList}">
                                <option value="${num}"
                                    <c:if test="${num == selectedClass}">
                                        selected
                                    </c:if>>
                                    ${num}
                                </option>
                            </c:forEach>

                        </select>
                    </div>

                    <%-- 在学中チェック --%>
                    <div class="col-2 form-check text-center">

                        <label class="form-check-label"
                               for="student-f3-check">

                            在学中

                            <input class="form-check-input"
                                   type="checkbox"
                                   id="student-f3-check"
                                   name="is_attend"
                                   value="on"

                                   <c:if test="${isAttend}">
                                       checked
                                   </c:if> />

                        </label>

                    </div>

                    <%-- 絞込みボタン --%>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary"
                                id="filter-button">
                            絞込み
                        </button>
                    </div>

                </div>

            </form>

            <%-- 学生一覧表示 --%>
            <c:choose>

                <c:when test="${studentList.size() > 0}">

                    <div class="px-3 mb-2">
                        検索結果：${studentList.size()}件
                    </div>

                    <table class="table table-hover">

                        <tr>
                            <th>入学年度</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>クラス</th>
                            <th class="text-center">在学中</th>
                            <th></th>
                            <th></th>
                        </tr>

                        <c:forEach var="student" items="${studentList}">

                            <tr>

                                <td>${student.entYear}</td>

                                <td>${student.no}</td>

                                <td>${student.name}</td>

                                <td>${student.classNum}</td>

                                <td class="text-center">

                                    <c:choose>

                                        <c:when test="${student.attend}">
                                            ○
                                        </c:when>

                                        <c:otherwise>
                                            ✕
                                        </c:otherwise>

                                    </c:choose>

                                </td>

                                <%-- 変更 --%>
                                <td>
                                    <a href="StudentUpdate.action?no=${student.no}">
                                        変更
                                    </a>
                                </td>

                                <%-- 削除 --%>
                                <td>
                                    <a href="StudentDelete.action?no=${student.no}">
                                        削除
                                    </a>
                                </td>

                            </tr>

                        </c:forEach>

                    </table>

                </c:when>

                <c:otherwise>

                    <div class="px-3">
                        学生情報が存在しませんでした
                    </div>

                </c:otherwise>

            </c:choose>

        </section>

    </c:param>

</c:import>