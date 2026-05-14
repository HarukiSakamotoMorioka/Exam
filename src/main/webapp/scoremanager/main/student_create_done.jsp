<%@ page contentType="text/html; charset=UTF-8" %>

<!-- ✅ 共通ヘッダー -->
<%@ include file="../common/header.jsp" %>

<div style="display:flex;">

    <!-- ✅ 左メニュー -->
    <%@ include file="../common/sidebar.jsp" %>

    <!-- ✅ メイン -->
    <div style="padding:20px; width:100%;">

        <!-- ① タイトル -->
        <div style="background:#e6e6e6; padding:10px; font-size:18px;">
            成績管理
        </div>

        <!-- ② メッセージ -->
        <div style="background:#8fbc8f; padding:10px; margin-top:10px;">
            登録が完了しました
        </div>

        <!-- ③④ リンク -->
        <div style="margin-top:20px;">
            <a href="test_regist.jsp">戻る</a>
            <a href="test_list.jsp">成績参照</a>
        </div>

    </div>

</div>
