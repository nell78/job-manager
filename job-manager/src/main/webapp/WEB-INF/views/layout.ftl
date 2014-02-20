<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트관리</title>
<#if env.acceptsProfiles("production")>
	<script src="${rc.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
<#else>
	<script src="${rc.contextPath}/resources/js/jquery-1.11.0.js"></script>
</#if>
</head>
<body>
<h1>프로젝트관리</h1>
<div id="menu">
<a href="${rc.contextPath}/">처음 화면</a>
<a href="${rc.contextPath}/dice">주사위</a>
<a href="${rc.contextPath}/seasons">계절</a>
<a href="${rc.contextPath}/memo/list">메모</a>
<a href="${rc.contextPath}/people">사람들</a>
<a href="${rc.contextPath}/securitySample/intro">보안 예제</a>
<a href="${rc.contextPath}/dbconsole/">DB 콘솔</a>
</div>
<hr>
<#if submenuDiv??>
	<div id="submenu">
	${submenuDiv}
	</div>
	<hr>
</#if>
<div id="main">
${mainDiv}
</div>
<hr>
<div id="copyright">
&copy; 2014 Song Younghwan
</div>
</body>
</html>
