<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<!-- xuyaya--系统主页 -->
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

<title>业主报修首页</title>
<meta content="Admin Dashboard" name="description" />
<meta content="ThemeDesign" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="assets/images/faviicon.png">
<link rel="stylesheet" type="text/css"
	href="assets/dist/swipeslider.css">
<!-- 公共css -->
<%@ include file="/common/comcss.jspf"%>

</head>
<body class="fixed-left">
	<div id="wrapper">
		<!-- xyy 引入公共左边菜单 -->
		<!-- ========== Left Sidebar Start ========== -->
		<%@include file="/menu/leftSidebar.jsp"%>
		<!-- Left Sidebar End -->
		<!-- Start right Content here -->
		<div class="content-page">
			<!-- Start content -->
			<div class="content">
				<!--引入上边菜单 -->
				<!-- Top Bar Start -->
				<%@include file="/menu/topbar.jsp"%>
				<!-- Top Bar End -->
				<div class="page-content-wrapper ">
					<div class="container-fluid">
					    <div>
						<div class="jq22-container" >
							<article class="container">
								<section>
									<figure id="full_feature" class="swipslider">
										<ul class="sw-slides">
											<li class="sw-slide"><img src="assets/images/owner1.jpg"
												alt="Summer beach concept"></li>
											<li class="sw-slide"><img src="assets/images/owner3.jpg"
												alt="Tiny Tina"></li>
										</ul>
									</figure>
								</section>
							</article>
                          </div>
                          <h3 style="text-align:center">欢迎进入OMMS扫码运维管理系统</h3>
                         <div class="row">
                            <div class="col-md-4 col-xl-3">
                            </div>                       
                            <div class="col-md-4 col-xl-2">
                            <div class="card m-b-30 card-body text-center">
                            <div align="center">
                            <img src="assets/images/weixiu.png"style="width: 50px;height:50px ">
                            </div>                           
                                                        维修申报
                            </div>
                            </div>
                            <div class="col-md-4 col-xl-2"> 
                            <div class="card m-b-30 card-body text-center">
                            <div align="center">
                            <img  src="assets/images/daiban.png"style="width: 50px;height:50px ">
                             </div>                                
                                                             待办处理
                            </div>
                            </div>
                            <div class="col-md-4 col-xl-2">
                            <div class="card m-b-30 card-body text-center">
                            <div align="center">
                            <img src="assets/images/lishi.png"style="width: 50px;height:50px ">
                            </div>                                
                                                         历史维修单
                            </div>
                            </div>
					  </div>
					  </div>
					<!-- container -->
          
				</div>
				<!-- Page content Wrapper -->

			</div>
			<!--内容结束 -->
			<!-- content -->
			
			<%@include file="/menu/footer.jsp"%>


		</div>
		<!-- End Right content here -->

	</div>
	<!-- END wrapper -->


	<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>

	<script type="text/javascript"
		src="${proPath}/assets/dist/swipeslider.min.js"></script>
	<script type="text/javascript">
		$(window).load(function() {
		      $('#full_feature').swipeslider();
		      $('#content_slider').swipeslider({
		        transitionDuration: 600,
		        autoPlayTimeout: 10000,
		        sliderHeight: '300px'
		      });
		      $('#responsiveness').swipeslider();
		      $('#customizability').swipeslider({
		        transitionDuration: 1500, 
		        autoPlayTimeout: 4000, 
		        timingFunction: 'cubic-bezier(0.38, 0.96, 0.7, 0.07)',
		        sliderHeight: '30%'});
		    });
	</script>


</body>

</html>