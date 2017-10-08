<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<div>playerTable:</div>
<table border="1px">
	<tr>
		<td>id</td>
		<td>playerNo</td>
		<td>status</td>
		<td>gameStatus</td>
		<td>roleType</td>
		<td>userId</td>
	</tr>
	<c:forEach var="player" items="${gameInfo.playerList}">
		<tr>
			<td><c:out value="${player.id}"></c:out></td>
			<td><c:out value="${player.playerNo}"></c:out></td>
			<td><c:out value="${player.status}"></c:out></td>
			<td><c:out value="${player.gameStatus}"></c:out></td>
			<td><c:out value="${player.roleType}"></c:out></td>
			<td><c:out value="${player.userDO.id}"></c:out></td>
		</tr>
	</c:forEach>
</table>
<br>
<div>stageTable:</div>
<table border="1px">
	<tr>
		<td>status</td>
		<td>type</td>
	</tr>
	<c:forEach var="stage" items="${gameInfo.currentRound.allStageList}">
		<tr>
			<td><c:out value="${stage.status}"></c:out></td>
			<td><c:out value="${stage.stageTypeDesc}"></c:out></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>

