<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>成績削除</title>
</head>
<body>
 
<h2>成績削除確認</h2>
 
<p>この成績を削除しますか？</p>
 
ScoreDelete.action
    <input type="hidden" name="id" value="${param.id}">
    <input type="submit" value="削除する">
</form>
 
TestList.actionキャンセル</a>
 
</body>
</html>