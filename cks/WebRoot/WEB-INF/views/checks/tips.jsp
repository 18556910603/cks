<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>巡检表单提交成功后提醒</title>
        <meta content="Admin Dashboard" name="description" />
        <meta content="ThemeDesign" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
		<c:set var="proPath" value="${pageContext.request.contextPath}" />
		
        <link rel="shortcut icon" href="${proPath}/assets/images/faviicon.png">

        <!-- prism css -->
        <link href="${proPath}/assets/plugins/prism/prism.css" rel="stylesheet" type="text/css">

        <link href="${proPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="${proPath}/assets/css/icons.css" rel="stylesheet" type="text/css">
        <link href="${proPath}/assets/css/style.css" rel="stylesheet" type="text/css">

    </head>


    <body class="fixed-left">

        <!-- Loader -->
        <div id="preloader"><div id="status"><div class="spinner"></div></div></div>

        <!-- Begin page -->
        <div id="wrapper">

            <!-- ========== Left Sidebar Start ========== -->
 			<%@include file="/menu/leftSidebar.jsp"%>
            <!-- Left Sidebar End -->

            <!-- Start right Content here -->

            <div class="content-page">
                <!-- Start content -->
                <div class="content">

                    <!-- Top Bar Start -->
					<%@include file="/menu/topbar.jsp"%>
                    <!-- Top Bar End -->

				<div class="page-content-wrapper ">

					<div class="container-fluid">

						<div class="row">
							<div class="col-lg-6">
								<div class="card m-b-30">
									<div class="card-body" >

										<h4 class="mt-0 header-title">志品.巡检系统</h4>
										<p class="text-muted m-b-30 font-14"><font style="color:darkcyan">表单提交成功！</font></p>

                                            <div class="" style="text-align:center;text-align:left">
                                                <h8 style="color:darkcyan">点击 <span class="badge badge-default"><a class="nav-link active" href="${proPath}/equipmentRepair/view.action">【结果统计】</a></span>查看巡检统计</h8><br />
                                                <h8 style="color:darkcyan">点击 <span class="badge badge-default"><a class="nav-link active" href="${proPath}/electricalCheck/view.action">【电气设备巡检表】</a></span>添加电气设备巡检记录</h8><br />
                                                <h8 style="color:darkcyan">点击 <span class="badge badge-default"><a class="nav-link active" href="${proPath}/equipmentRepair/view.action">【设备报修】</a></span>进行设备报修</h8><br />
                                            </div>
									</div>
								</div>
							</div>
							<!-- end col -->

						</div>
						<!-- end row -->

					</div>
					<!-- container -->


				</div>
				<!-- Page content Wrapper -->

			</div> <!-- content -->

				<%@include file="/menu/footer.jsp"%>

            </div>
            <!-- End Right content here -->

        </div>
        <!-- END wrapper -->


        <!-- jQuery  -->

        <!-- Prism js -->
        <script src="${proPath}/assets/plugins/prism/prism.js"></script>

        <!-- App js -->
        <script src="${proPath}/assets/js/app.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {
            });
        function addElectricalCheck(){
			$.ajax({
				url : "${proPath}/electricalCheck/view.action",
				data : {
					"epId" : ''
				},
				type : "post",
				async : false,
				beforeSend : function() {
					return true;
				},
				success : function(data) {

				},
				error : function() {
					alert('请求出错');
				},
				complete : function() {
					// $('#tips').hide();
				}
			});
        	
        }   
   
                
                
         

        </script>
    </body>
</html>