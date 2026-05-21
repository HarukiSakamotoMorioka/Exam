<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">クラス管理</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                クラス一覧
            </h2>

            <!-- 新規登録リンク（地味） -->
            <a href="ClassCreate.action">新規登録</a>

            <table class="table table-bordered mt-3">
                <thead class="table-light">
                    <tr>
                        <th>クラス番号</th>
                        <th style="width: 200px;">操作</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="c" items="${classList}">
                        <tr>
                            <td>${c}</td>
                            <td>
                                <a href="ClassUpdate.action?oldNum=${c}">変更</a> |
                                <a href="ClassDelete.action?classNum=${c}">削除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </section>
    </c:param>
</c:import>
