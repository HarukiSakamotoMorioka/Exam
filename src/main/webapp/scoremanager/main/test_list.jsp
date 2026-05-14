<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
    .block-box {
        border: 1px solid #999 !important;
        padding: 12px 10px !important;
        margin-bottom: 15px;
    }
    .form-label {
        margin-bottom: 3px !important;
        font-size: 14px;
    }
    .form-select-sm, .form-control-sm {
        height: 32px !important;
        padding: 2px 6px !important;
        font-size: 14px;
    }
    .search-btn {
        margin-top: 22px;
        height: 32px;
        width: 100%;
        padding: 0;
    }
</style>

<c:import url="/common/base.jsp">
<c:param name="title">成績参照</c:param>

<c:param name="content">

<section class="mt-4 ms-4" style="max-width: 1050px;">

    <!-- タイトル -->
    <h2 class="h4 mb-3 bg-secondary bg-opacity-10 py-2 px-3">成績参照</h2>

    <!-- エラー表示 -->
    <c:if test="${not empty errors.input}">
        <div class="alert alert-warning">${errors.input}</div>
    </c:if>

    <c:if test="${not empty errors.notfound}">
        <div class="alert alert-warning">${errors.notfound}</div>
    </c:if>

    <!-- 科目情報 -->
    <div class="fw-bold mb-2">科目情報</div>

    <form method="get" action="TestList.action">
        <div class="row block-box rounded">

            <!-- 入学年度 -->
            <div class="col-3">
                <label class="form-label">入学年度</label>
                <select class="form-select form-select-sm" name="f1">
                    <option value="0">----</option>
                    <c:forEach var="year" items="${ent_year_set}">
                        <option value="${year}" <c:if test="${year == f1}">selected</c:if>>
                            ${year}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- クラス -->
            <div class="col-3">
                <label class="form-label">クラス</label>
                <select class="form-select form-select-sm" name="f2">
                    <option value="0">----</option>
                    <c:forEach var="num" items="${class_num_set}">
                        <option value="${num}" <c:if test="${num == f2}">selected</c:if>>
                            ${num}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- 科目 -->
            <div class="col-4">
                <label class="form-label">科目</label>
                <select class="form-select form-select-sm" name="f3">
                    <option value="0">----</option>
                    <c:forEach var="sub" items="${subject_set}">
                        <option value="${sub.cd}" <c:if test="${sub.cd == f3}">selected</c:if>>
                            ${sub.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- 検索 -->
            <div class="col-2">
                <button class="btn btn-secondary btn-sm search-btn">検索</button>
            </div>

        </div>
    </form>

    <!-- 学生情報 -->
    <div class="fw-bold mt-4 mb-2">学生情報</div>

    <form method="get" action="TestList.action">
        <div class="row block-box rounded">

            <!-- 学生番号 -->
            <div class="col-4">
                <label class="form-label">学生番号</label>
                <input type="text" class="form-control form-control-sm"
                       name="student_no"
                       placeholder="学生番号を入力してください"
                       value="${student_no}">
            </div>

            <!-- 検索 -->
            <div class="col-2">
                <button class="btn btn-secondary btn-sm search-btn">検索</button>
            </div>

        </div>
    </form>

    <!-- 説明 -->
    <div class="text-primary mt-2">
        科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
    </div>

    <!-- 成績一覧 -->
    <c:if test="${scores != null}">
        <h5 class="mt-4">検索結果</h5>

        <c:choose>
            <c:when test="${scores.size() > 0}">
                <div>検索結果：${scores.size()}件</div>

                <table class="table table-hover mt-2">
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

