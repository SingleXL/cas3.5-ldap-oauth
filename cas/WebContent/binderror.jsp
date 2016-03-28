<%@ page session="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=no,width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" />
<!--[if IE 8 ]><link href="${pageContext.request.contextPath}/assets/css/gv_ie8.css" rel="stylesheet" type="text/css" media="screen" /><![endif]-->
<!--[if IE 9 ]><link href="${pageContext.request.contextPath}/assets/css/gv_ie8.css" rel="stylesheet" type="text/css" media="screen" /><![endif]-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style_gv.css" />
<script src='${pageContext.request.contextPath}/assets/js/jquery.js'></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/assets/imgs/favicon.ico" type="image/x-icon" rel=icon>
<!-- ${pageContext.request.contextPath} -->
<title>庚商企业中心认证系统</title>
<style type="text/css">
        @media (min-width:700px){
           #btn2{
                display:none;
            }
            #btn1{
                display:block;
            }

        }
        @media (max-width:700px) {
            #btn1{
                display:none;
            }
            #btn2{
                display:block;
            }
            .image{
                display:none;
            }
        }
        img{
            max-width: 100%;
        }
        body{
            background:url('${pageContext.request.contextPath}/assets/imgs/bg1.jpg') no-repeat;
            background-size: 100% 100%;
            -moz-background-size: 100% 100%; 
            -o-background-size: 100% 100%; 
            -webkit-background-size: 100% 100%; 
            filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='${pageContext.request.contextPath}/assets/imgs/bg1.jpg', sizingMethod='scale')\9;
        }
        .header{
            height:26%;
            position: relative;
        }
        .logo{
            position:absolute;
            height:70px;
            width:190px;
            left:10%;
            bottom:0px;
        }
    .mybutton{
        width:100%;
        background:#65A6CF;
        border-radius: 5px;
        border:none;
        padding:6px;
        margin-top:15px;
        margin-left: 3px;
        font-weight: bold;
    }
    .container-narrow{
        text-align: center;
        margin-top: 30px;
    }
    footer{
        width:100%;
        height:30px;
        bottom:0px;
        position:absolute;
        margin-bottom: 10px;
    }
    .footercontent{
        width:100%;
        text-align: center;
        font-size: 12px;
    }   
    .content-one table input{
        height:30px;
        margin-bottom: 0px;
    }
    .content-one{
        margin-top: -9px;
    }
    

    table input{
        margin: 10px;
        margin-left: 3px !important;
    }
    .content-one p{
        margin-top: 17px;
    }
	.error {
		border: 1px #C82121 solid;
	}
</style>
</head>
<body>
    <div class="header">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/assets/imgs/logo.png" height="38" width="190" alt="" />
            <img src="${pageContext.request.contextPath}/assets/imgs/logo-en.png" height="10" width="192" alt="" />
        </div>
    </div>
    <div class="container-narrow">
		<div class="content-one">
        <div class="col-md-12">
			<img src="${pageContext.request.contextPath}/assets/imgs/sys.png" style="margin-bottom:8px" />

            <form method="post" action="bindUserToQQ" id="fm1" htmlEscape="true">
				<table align="center" style="margin-top:12px;">
				
				<tr>
                        <td colspan="2" >
                           &nbsp;  
                        </td>
                    </tr>
				
				<tr>
                        <td colspan="2" style="text-align: center;color:red;font-weight: bold;">
                        	<a style="text-align: center;color:red;font-weight: bold;" href="${pageContext.request.contextPath}/login">发生错误，重新登录绑定</a>
                        </td>
                    </tr>
				
					<tr>
                        <td colspan="2" >
                           &nbsp;  
                        </td>
                    </tr>
				</table>
            </form>
            </div>
		</div>
    </div>
    <footer>
    	<div class="footercontent" >Copyright © 2011 上海庚商网络信息技术有限公司 </div>
    </footer>
</body>
</html>
