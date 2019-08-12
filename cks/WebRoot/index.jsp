<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>登陆页面</title>
        <meta content="Admin Dashboard" name="description" />
        <meta content="ThemeDesign" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <link rel="shortcut icon" href="assets/images/faviicon.png">
   	 	<!-- 公共css -->
   		<%@ include file="/common/comcss.jspf"%>
		<style type="text/css">
		.card-body {
		    -webkit-box-flex: 1;
		    -ms-flex: 1 1 auto;
		    flex: 1 1 auto;
		    /* padding: 10.5rem; */
		    padding-top: 2.15rem;
		    padding-right: 3.15rem;
		    padding-bottom: 2.15rem;
		    padding-left: 3.15rem;
		}
		.wrapper-page {
		  margin: 7.5% auto;
		  max-width: 620px;
		  position: relative;
		}	
		body {
		    margin: 0;
		    font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
		    font-size: 1rem;
		    font-weight: 400;
		    /* line-height: 1.5; */
		    color: #212529;
		    text-align: left;
		    background-color: #fff;
		}		
.card {
    position: relative;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-direction: column;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 1px solid rgba(0,0,0,.125);
    border-radius: .25rem;
    margin: -20px 0px 0px 0px;
}		
		
			
</style>
    </head>


    <body>

        <!-- Begin page -->
        <div class="accountbg"></div>
        <div class="wrapper-page">

            <div class="card">
            
            
            
                <div class="card-body">

                    <h3 class="text-center mt-0 m-b-15">
<%--                         <a href="index.jsp" class="logo logo-admin"><img src="${proPath}/assets/images/logo.png" height="30" alt="logo"></a> --%>
						<h4 class="card-subtitle text-muted font-30 text-center ">OMMS<font class="card-subtitle font-20  text-muted">扫码运维管理系统</font></h4>
                    </h3>

                    <h4 class="text-muted text-center font-14"><b>苏州技术团队</b></h4>

                    <div class="p-3">
<!--                      <form class="form-horizontal m-t-20"  id="formID" > -->
<%--                      <form class="form-horizontal m-t-20"  id="formID" method="post" action="${proPath}/account/login.action"> --%>
						  <form class="form-horizontal m-t-20" action="${proPath}/user/login.action" method="post"id="form_data" >
                            <div class="form-group row">
                                <div class="col-12">
                                    <input class="form-control" type="text" required="" placeholder="用户名" name="loginName">
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-12">
                                    <input class="form-control" type="password" required="" placeholder="密码" name="password" >
                                    <div style="color:red">${requestScope.msg}</div>
                                </div>
                            </div>
<%-- value="${rememberMe ? 'checked' : ''}" --%>
                            <div class="form-group row">
                                <div class="col-12">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="customCheck1" checked="true">
                                        <label class="custom-control-label" for="customCheck1">记住我</label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group text-center row m-t-20">
                                <div class="col-12">
<!--                                     <button class="btn btn-info btn-block waves-effect waves-light"  id="submit" type="submit">登录</button> -->
                                    <button class="btn btn-info btn-block waves-effect waves-light"  type="submit" >登录</button>
                                </div>
                            </div>

                            <div class="form-group m-t-10 mb-0 row">
                                <div class="col-sm-7 m-t-20">
                                    <a href="pages-recoverpw.html" class="text-muted"><i class="mdi mdi-lock    "></i> 忘记密码?</a>
                                </div>
                                <div class="col-sm-5 m-t-20">
                                    <a href="pages-register.html" class="text-muted"><i class="mdi mdi-account-circle"></i> 注册</a>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>



<!-- 公共js -->
   <%@ include file="/common/common.jspf"%>
   
        <script type="text/javascript">
        $(document).ready(function() {
//         	 $('form').parsley();
             init();
            });
//        //初始化图片预览
        function init(){
        	var session='${SessionContainer}';
    	    if(!session){
//     	    	alert('没有！');
    	    }else{
    	    	$("form").submit() ;
    	    	
    	    }
        };   
        </script>



    </body>
</html>