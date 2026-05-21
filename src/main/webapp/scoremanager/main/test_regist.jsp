<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
 
<c:param name="content">
 
<section class="me-4">
 
    <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
 
    <!-- エラー表示 -->
    <c:if test="${not empty errors.input}">
        <div class="alert alert-warning">${errors.input}</div>
    </c:if>
 
    <!-- 検索フォーム -->
    <form method="get" action="TestRegist.action">
        <div class="row border mx-3 mb-3 py-2 align-items-center rounded">
 
            <!-- 入学年度 -->
            <div class="col-2">
                <label class="form-label">入学年度</label>
                <select class="form-select" name="entYear">
                    <option value="">--------</option>
                    <c:forEach var="year" items="${ent_year_set}">
                        <option value="${year}" <c:if test="${year == selEntYear}">selected</c:if>>${year}</option>
                    </c:forEach>
                </select>
            </div>
 
            <!-- クラス -->
            <div class="col-2">
                <label class="form-label">クラス</label>
                <select class="form-select" name="classNum">
                    <option value="">--------</option>
                    <c:forEach var="num" items="${class_num_set}">
                        <option value="${num}" <c:if test="${num == selClassNum}">selected</c:if>>${num}</option>
                    </c:forEach>
                </select>
            </div>
 
            <!-- 科目 -->
            <div class="col-4">
			    <label class="form-label">科目</label>
			    <select class="form-select" name="subjectCd">
			        <option value="">--------</option>
			        <c:forEach var="sub" items="${subjects}">
			            <option value="${sub.cd}" <c:if test="${sub.cd == selSubjectCd}">selected</c:if>>
			                ${sub.name}
			            </option>
			        </c:forEach>
			    </select>
			</div>



 
            <!-- 回数 -->
            <div class="col-2">
                <label class="form-label">回数</label>
                <select class="form-select" name="num">
				    <option value="">--------</option>
				    <c:forEach var="n" items="${num_set}">
				        <option value="${n}" <c:if test="${n == selNum}">selected</c:if>>${n}</option>
				    </c:forEach>
				</select>

            </div>
 
            <div class="col-2 text-center">
                <button type="submit" class="btn btn-secondary">検索</button>
            </div>
 
        </div>
    </form>
 
    <!-- 検索後：学生一覧に点数入力 -->
    <c:if test="${testListStudent != null}">
	    <!-- ▼ 科目名と回数の表示（1行でスッキリ） -->
		<div class="mx-3 mb-3 p-2 bg-light border rounded">
		    <h5 class="mb-0">
		        科目：
		        <c:forEach var="sub" items="${subjects}">
		            <c:if test="${sub.cd == selSubjectCd}">
		                ${sub.name}
		            </c:if>
		        </c:forEach>
		        （${selNum}回）
		    </h5>
		</div>

        <form method="post" action="TestRegistExecute.action">
            <input type="hidden" name="subjectCd" value="${selSubjectCd}">
            <input type="hidden" name="num" value="${selNum}">
            <input type="hidden" name="entYear" value="${selEntYear}">
            <input type="hidden" name="classNum" value="${selClassNum}">
 
            <table class="table table-hover">
                <tr>
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>点数</th>
                </tr>
			<c:forEach var="s" items="${studentList}">
			    <tr>
			        <td>${s.entYear}</td>
			        <td>${s.classNum}</td>
			        <td>${s.no}</td>
			        <td>${s.name}</td>
			        <td>
			            <input type="hidden" name="studentNo" value="${s.no}">
			            <input type="number" class="form-control" name="point_${s.no}"
			                   min="0" max="100" placeholder="0〜100">
			        </td>
			    </tr>
			</c:forEach>

            </table>
 
            <div class="text-center mt-3">
                <button type="submit" class="btn btn-primary">登録</button>
            </div>
        </form>
    </c:if>
 
</section>
 
</c:param>
</c:import>