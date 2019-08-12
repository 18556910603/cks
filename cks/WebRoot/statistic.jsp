<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<!-- xuyaya--系统主页 -->
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>统计结果总览</title>
    <meta content="Admin Dashboard" name="description" />
    <meta content="ThemeDesign" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="shortcut icon" href="assets/images/faviicon.png">
    <!-- 公共css -->
   <%@ include file="/common/comcss.jspf"%>
</head>

<body class="fixed-left">
    <!-- Loader -->
    <div id="preloader">
        <div id="status">
            <div class="spinner"></div>
        </div>
    </div>
    <!-- Begin page -->
    <div id="wrapper">
<!-- xyy 引入公共左边菜单 -->
 <!-- ========== Left Sidebar Start ========== -->
	<%@include file="/menu/leftSidebar.jsp" %>
	<!-- Left Sidebar End -->
        <!-- Start right Content here -->
        <div class="content-page">
            <!-- Start content -->
            <div class="content">
<!--引入上边菜单 -->
                    <!-- Top Bar Start -->
	<%@include file="/menu/topbar.jsp" %>
	
                    <!-- Top Bar End -->
                <div class="page-content-wrapper ">
             <div class="container-fluid">
            
		<!--内容开始：第一个栏目 -->
                        <div class="row">                       
                            <div class="col-md-4 col-xl-4">                                
                                <div class="card m-b-30 card-body text-center">
                                        <h3 class="card-title font-50 mt-0">今日已检(台)</h4>
                                         <br>
                                         <br>
                                         <p class="card-text" style="font-size:270px ;color: #0f9cf3">${tMainJspValue.todayCks}</p>
                                         <br>
                                         <br>
                                                                               
                                </div>
                            </div>
                            <div class="col-md-8 col-xl-8">
                            <div class="row">
                            <div class="col-md-3 col-xl-3">
                                <div class="card m-b-30 card-body text-center">
                                         <h6 class="card-title font-50 mt-0">今日待检(台)</h4>
                                         <br>                                       
                                         <br>
                                         <p class="card-text" style="font-size:100px;color: #0f9cf3">16</p>
                                         <br>
                                         <br>

                                </div>
                            </div>
                            <div class="col-md-3 col-xl-3">
                                <div class="card m-b-30">
                                <div class="card-body">
                                     <h6 class="card-title font-50 mt-0">今日已检分布(台)</h4>
                                     <p class=" card-text text-primary">共计<span class="badge badge-success pull-right">${tMainJspValue.todayCks}</span></p>
                                </div>
                                     <ul class="list-group list-group-flush">
                                     <c:forEach items="${fns:getDictList('epType')}" var="dict">
									 <li class="list-group-item">${dict.label}<span class="badge badge-primary pull-right"></span></li>
									 </c:forEach>

                                    
<!--                                      <li class="list-group-item">生产设备<span class="badge badge-primary pull-right">3</span></li>
                                     <li class="list-group-item">电气设备<span class="badge badge-primary pull-right">3</span></li>
 -->                                     </ul> 
                                </div>
                            </div>
                            
                            <div class="col-md-3 col-xl-3">
                                <div class="card m-b-30">
                                <div class="card-body">
                                     <h6 class="card-title font-50 mt-0">今日待检分布(台)</h4>
                                     <p class=" card-text text-primary">共计<span class="badge badge-success pull-right">8</span></p>
                                </div>
                                     <ul class="list-group list-group-flush">
                                     <li class="list-group-item">消防设备<span class="badge badge-primary pull-right">3</span></li>
                                     <li class="list-group-item">生产设备<span class="badge badge-primary pull-right">3</span></li>
                                     <li class="list-group-item">电气设备<span class="badge badge-primary pull-right">3</span></li>
                                     </ul> 
                                </div>
                            </div>
                            
                             <div class="col-md-3 col-xl-3">
                                <div class="card m-b-30 card-body text-center">
                                         <h6 class="card-title font-50 mt-0">设备总数(台)</h4>
                                         <br>
                                         <br>
                                         <p class="card-text" style="font-size:100px;color: #0f9cf3">${sum}</p>
                                         <br>
                                         <br>
                                </div>                            
                             </div>
                            
                            <div class="col-md-3 col-xl-3">
                                <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">正常运行设备(台)</h4>
                                       <br>
                                       <br>
                                         <p class="card-text" style="font-size:100px;color: #5CDF41">${tMainJspValue.normalOperation}</p>
                                       <br>
                                       <br>
                                </div>
                            </div>
                           <div class="col-md-3 col-xl-3">
                                 <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">异常运行设备(台)</h4>
                                       <br>
                                       <br>
                                         <p class="card-text" style="font-size:100px;color:#F45345">${tMainJspValue.abnormalOperation}</p>
                                       <br>
                                       <br>
                                </div>
                            </div>
                            
                            <div class="col-md-3 col-xl-3">
                                 <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">停机维修设备(台)</h4>
                                       <br>
                                       <br>
                                         <p class="card-text" style="font-size:100px;color: #F3E75F">${tMainJspValue.repairOperation}</p>
                                      <br>
                                      <br>
                                </div>
                            </div>
                             <div class="col-md-3 col-xl-3">
                                 <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">报废/停用设备(台)</h4>
                                       <br>
                                       <br>          
                                         <p class="card-text" style="font-size:100px;color: #0f9cf3">${tMainJspValue.scrapOperation}</p>
                                       <br>
                                       <br>
                                </div>
                            </div> 
                              </div> 
                            </div>                                 

                        </div>
                        <!-- end row -->

                    </div>
                    <!-- container -->

                </div>
                <!-- Page content Wrapper -->

            </div>
<!--内容结束 -->
            <!-- content -->
	<%@include file="/menu/footer.jsp" %>


        </div>
        <!-- End Right content here -->

    </div>
    <!-- END wrapper -->

<!-- 公共js -->

    <!--Morris Chart-->
    <script src="${proPath}/assets/plugins/morris/morris.min.js"></script>
    <script src="${proPath}/assets/plugins/raphael/raphael-min.js"></script>

    <script src="${proPath}/assets/pages/dashborad.js"></script>

    <!-- App js -->
    <script src="${proPath}/assets/js/app.js"></script>
    <!-- Chart JS -->
    <script src="${proPath}/assets/plugins/chart.js/chart.min.js"></script>
    <script src="${proPath}/assets/pages/chartjs.init.js"></script>
</body>

</html>