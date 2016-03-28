<%@ page session="true"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<meta charset="UTF-8">
<meta name="viewport"
	content="user-scalable=no,width=device-width,initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" />
<script src='${pageContext.request.contextPath}/assets/js/jquery.js'></script>
<script
	src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/assets/imgs/favicon.ico"
	type="image/x-icon" rel=icon>
<head>


<style type="text/css">
a, a:hover, a:link {
	text-decoration: none;
}

body {
	background:
		url('${pageContext.request.contextPath}/assets/imgs/bg1.jpg')
		no-repeat;
	background-size: 100% 100%;
}

header {
	height: 24%;
	position: relative;
}

.logo {
	position: absolute;
	height: 70px;
	width: 190px;
	left: 10%;
	bottom: 0px;
}

.row {
	box-sizing: border-box;
	margin-top: 2%;
	margin-bottom: 4%;
}

.row>div>a {
	color: #272822;
}

.row>div>a:hover {
	color: #0494B3;
}

.row>div:nth-child(1) {
	height: 250px;
	padding: 5px;
}

.row>div:nth-child(2) {
	height: 250px;
	padding: 5px;
}

.row>div:nth-child(3) {
	height: 250px;
	padding: 5px;
}

.system {
	margin-left: auto;
	margin-right: auto;
	padding-top: 15px;
	border-radius: 6px;
}

footer {
	width: 100%;
	height: 30px;
	bottom: 0px;
	position: absolute;
}

}
.footercontent {
	width: 100%;
	text-align: center;
	font-size: 12px;
}

h4 {
	font-weight: bold;
	color: #1538E5;
}

h4:hover {
	font-weight: bold;
	color: #74788C;
}

img {
	border-radius: 6px
}
</style>

</head>
<body>
	<header>
		<div class="logo">
			<img src="${pageContext.request.contextPath}/assets/imgs/logo.png"
				height="38" width="190" alt="" /> <img
				src="${pageContext.request.contextPath}/assets/imgs/logo-en.png"
				height="10" width="192" alt="" />
		</div>
	</header>
	<div class="container">
		<p style="color: green; font-weight: bold; text-align: center;">登录成功
			选择进入</p>
		<div class="row">

			<div class="col-sm-6 col-md-4 col-lg-4">
				<div class="system">
					<a href="http://xmgl.gvsun.net:2334/gvsunxmgl/messageList"
						style="text-align: center;" target="_blank">
						<div style="width: 100%; height: 150px;">
							<img
								src="${pageContext.request.contextPath}/assets/imgs/select_2.jpg"
								width="300" height="150">
						</div>
						<h4>项目管理平台</h4>
					</a>
				</div>
			</div>

			<div class="col-sm-6 col-md-4 col-lg-4">
				<div class="system">
					<a href="http://www.gvsun.net:2334/financial/reimburse/listTem"
						style="text-align: center;" target="_blank">
						<div style="width: 100%; height: 150px;">
							<img
								src="${pageContext.request.contextPath}/assets/imgs/select_4.jpg"
								width="300" height="150">
						</div>
						<h4>运营管理平台</h4>
					</a>
				</div>
			</div>

			<div class="col-sm-6 col-md-4 col-lg-4">
				<div class="system">
					<a href="http://www.gvsun.net:8880/gvsun/cms/index"
						style="text-align: center;" target="_blank">
						<div style="width: 100%; height: 150px;">
							<img
								src="${pageContext.request.contextPath}/assets/imgs/select_1.jpg"
								width="300" height="150">
						</div>
						<h4>教学管理平台</h4>
					</a>
				</div>
			</div>

			<div class="col-sm-6 col-md-4 col-lg-4">
				<div class="system">
					<a href="http://www.gvsun.net3:8080/GvsunMessage/"
						style="text-align: center;">
						<div style="width: 100%; height: 150px;">
							<img
								src="${pageContext.request.contextPath}/assets/imgs/select_3.jpg"
								width="300" height="150">

						</div>
						<h4>信息化中心</h4>
					</a>
				</div>
			</div>

		</div>
	</div>

</body>
</html>