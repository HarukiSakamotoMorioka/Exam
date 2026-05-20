<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
                科目情報削除
            </h2>

            <p class="fs-5 mt-4">
			    「${subject.name}（${subject.cd}）」を削除してもよろしいですか？
			</p>
			
			<form action="SubjectDelete.action" method="post" class="mt-4">
			    <input type="hidden" name="cd" value="${subject.cd}">
			    <input type="submit" value="削除" class="btn btn-danger px-4">
			</form>



            <!-- ▼ 戻るリンク -->
            <div class="mt-4 px-4 position-relative">
                <ul class="nav mb-auto px-4">
                    <li class="nav-item position-absolute start-0">
                        <a href="SubjectList.action">戻る</a>
                    </li>
                </ul>
            </div>

        </section>
    </c:param>
</c:import>
