<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績参照</c:param>

    <c:param name="content">

        <style>
            .score-ref-area {
                max-width: 900px;
            }

            .score-ref-title {
                font-size: 22px;
                font-weight: normal;
                background-color: rgba(108, 117, 125, 0.12);
                padding: 10px 24px;
                margin-bottom: 18px;
            }

            .block-title {
                font-size: 16px;
                font-weight: bold;
                margin-bottom: 8px;
            }

            .mini-label {
                display: block;
                font-size: 12px;
                margin-bottom: 4px;
            }

            .compact-select,
            .compact-input {
                height: 32px;
                font-size: 13px;
                padding-top: 4px;
                padding-bottom: 4px;
            }

            .subject-row,
            .student-row {
                display: flex;
                flex-wrap: wrap;
                align-items: flex-end;
                gap: 12px;
                margin-bottom: 18px;
            }

            .field-year {
                width: 150px;
            }

            .field-class {
                width: 120px;
            }

            .field-subject {
                width: 170px;
            }

            .field-student {
                width: 220px;
            }

            .search-btn {
                min-width: 56px;
                height: 32px;
                font-size: 12px;
                padding: 0 14px;
                background-color: #6c757d;
                border-color: #6c757d;
                color: #fff;
            }

            .search-btn:hover {
                background-color: #5c636a;
                border-color: #565e64;
                color: #fff;
            }

            .guide-text {
                font-size: 12px;
                margin-top: 8px;
            }

            .result-title {
                font-size: 16px;
                font-weight: bold;
                margin-top: 18px;
                margin-bottom: 8px;
            }

            .result-count {
                font-size: 13px;
                margin-bottom: 6px;
            }

            .score-table {
                font-size: 13px;
            }

            .score-table th,
            .score-table td {
                vertical-align: middle;
            }
        </style>

        <section class="container mt-4 score-ref-area">

            <!-- タイトル -->
            <h2 class="score-ref-title">成績参照</h2>

            <!-- エラー表示 -->
            <c:if test="${not empty errors.input}">
                <div class="alert alert-warning py-2 px-3">${errors.input}</div>
            </c:if>

            <c:if test="${not empty errors.notfound}">
                <div class="alert alert-warning py-2 px-3">${errors.notfound}</div>
            </c:if>

            <!-- 科目情報 -->
            <div class="block-title">科目情報</div>

            <form method="get" action="TestList.action" class="mb-3">
                <div class="subject-row">

                    <div class="field-year">
                        <label class="mini-label">入学年度</label>
                        <select class="form-select compact-select" name="f1">
                            <option value="0">----------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>
                                    ${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="field-class">
                        <label class="mini-label">クラス</label>
                        <select class="form-select compact-select" name="f2">
                            <option value="0">----------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>
                                    ${num}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="field-subject">
                        <label class="mini-label">科目</label>
                        <select class="form-select compact-select" name="f3">
                            <option value="0">----------</option>
                            <c:forEach var="sub" items="${subject_set}">
                                <option value="${sub.cd}" <c:if test="${sub.cd == f3}">selected</c:if>>
                                    ${sub.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <button type="submit" class="btn search-btn">検索</button>
                    </div>

                </div>
            </form>

            <!-- 学生情報 -->
            <div class="block-title">学生情報</div>

            <form method="get" action="TestList.action" class="mb-2">
                <div class="student-row">

                    <div class="field-student">
                        <label class="mini-label">学生番号</label>
                        <input type="text"
                               class="form-control compact-input"
                               name="student_no"
                               placeholder="学生番号を入力してください"
                               value="${student_no}">
                    </div>

                    <div>
                        <button type="submit" class="btn search-btn">検索</button>
                    </div>

                </div>
            </form>

            <!-- 下部説明文 -->
            <div class="text-primary guide-text">
                科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
            </div>

            <!-- 成績一覧 -->
            <c:if test="${scores != null}">
                <div class="result-title">検索結果</div>

                <c:choose>
                    <c:when test="${scores.size() > 0}">
                        <div class="result-count">検索結果：${scores.size()}件</div>

                        <table class="table table-hover table-sm score-table mt-2">
                            <tr>
                                <th>学生番号</th>
                                <th>科目コード</th>
                                <th>回数</th>
                                <th>点数</th>
                                <th>クラス</th>
                            </tr>

                            <c:forEach var="t" items="${scores}">
                                <tr>
                                    <td>${t.studentNo}</td>
                                    <td>${t.subjectCd}</td>
                                    <td>${t.no}</td>
                                    <td>${t.point}</td>
                                    <td>${t.classNum}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>

                    <c:otherwise>
                        <div class="text-muted">成績情報が存在しませんでした</div>
                    </c:otherwise>
                </c:choose>
            </c:if>

        </section>

    </c:param>
</c:import>