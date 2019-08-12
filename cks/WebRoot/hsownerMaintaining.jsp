<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>业主报修已完成任务列表</title>
<meta content="Admin Dashboard" name="description" />
<meta content="ThemeDesign" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

 <link rel="shortcut icon" href="assets/images/faviicon.png">
  <!-- 公共css -->
 <%@ include file="/common/comcss.jspf"%>

<!-- DataTables -->
<link href="assets/plugins/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/datatables/buttons.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
	
<!-- Responsive datatable examples -->
<link href="assets/plugins/datatables/responsive.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />

<!-- Alertify css -->
<link href="assets/plugins/alertify/css/alertify.css" rel="stylesheet" type="text/css">

<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="assets/css/style.css" rel="stylesheet" type="text/css">


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
					
					<div class="row">
							<div class="col-12">
								<div class="card m-b-30">
									<div class="card-body">									
									<input type="hidden" id="refreshed" value="no">
										<h4 class="mt-0 header-title">业主报修单</h4>
										<div class="table-rep-plugin">
											<div class="table-responsive b-0"
												data-pattern="priority-columns">
												<table id="datatable-buttons"
													class="table table-striped table-bordered" cellspacing="0"
													width="100%" style="overflow: auto;">
													<thead>
														<tr style="background-color: #b0ece3;">
															<th>序号</th>
															<th>报修时间</th>
															<th>报修地点</th>
															<th>设备名称</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="equipmentRepair"
															items="${equipmentRepairs}" varStatus="status">
															<tr>
																<td>${requestScope.offset+status.index+1}</td>
																<td>${equipmentRepair.eprCkTime}</td>
																<td><a class="nav-link active"
																	href="displayOById.action?hsEquipmentrepairId=${equipmentRepair.hsEquipmentrepairId}">${equipmentRepair.epLocation}</a>
																</td>
																<td>${equipmentRepair.epHomeEquname}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
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
			</div>
			<!-- content -->
	
			<%@include file="/menu/footer.jsp"%>

		</div>
		<!-- End Right content here -->

	</div>
	<!-- END wrapper -->


    <!-- jQuery  -->

	<!-- Required datatable js -->
	<script src="assets/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="assets/plugins/datatables/dataTables.bootstrap4.min.js"></script>
	<!-- Buttons examples -->
	<script src="assets/plugins/datatables/dataTables.buttons.min.js"></script>
	<script src="assets/plugins/datatables/buttons.bootstrap4.min.js"></script>
	<script src="assets/plugins/datatables/jszip.min.js"></script>
	<script src="assets/plugins/datatables/pdfmake.min.js"></script>
	<script src="assets/plugins/datatables/vfs_fonts.js"></script>
	<script src="assets/plugins/datatables/buttons.html5.min.js"></script>
	<script src="assets/plugins/datatables/buttons.print.min.js"></script>
	<script src="assets/plugins/datatables/buttons.colVis.min.js"></script>
	<!-- Responsive examples -->
	<script src="assets/plugins/datatables/dataTables.responsive.min.js"></script>
	<script src="assets/plugins/datatables/responsive.bootstrap4.min.js"></script>

	<!-- Datatable init js -->
	<script src="assets/pages/datatables.init.js"></script>

    <!-- Alertify js -->
   <script src="assets/plugins/alertify/js/alertify.js"></script>
   <script src="assets/pages/alertify-init.js"></script>

<!-- 公共js -->

    <!--Morris Chart-->
    <script src="assets/plugins/morris/morris.min.js"></script>
    <script src="assets/plugins/raphael/raphael-min.js"></script>
    <script src="assets/pages/dashborad.js"></script>

    <!-- App js -->
    <script src="assets/js/app.js"></script>
    
 

<script type="text/javascript">

</script>  

</body>

</html>