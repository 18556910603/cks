<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>推送成功告警明细</title>
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

        <!-- DataTables -->
        <link href="${proPath}/assets/plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <link href="${proPath}/assets/plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <!-- Responsive datatable examples -->
        <link href="${proPath}/assets/plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />

        <link href="${proPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="${proPath}/assets/css/icons.css" rel="stylesheet" type="text/css">
        <link href="${proPath}/assets/css/style.css" rel="stylesheet" type="text/css">
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

		<!-- ========== Left Sidebar Start ========== -->
		<%@include file="/menu/leftSidebar.jsp"%>

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
                                <div class="col-12">
                                    <div class="card m-b-30">
                                        <div class="card-body">

                                            <h4 class="mt-0 header-title">设备告警查询信息表</h4>
                                            <p class="text-muted m-b-30 font-14">设备告警详情</p>
                                            <div class="table-rep-plugin">
                                            <form class=""  action="timeSearch.action"   onsubmit=""  method="post" >
                                            <div class="form-group row">
                                                <label for="example-datetime-local-input" class="col-sm-1 col-form-label">起始时间</label>
                                                <div class="col-sm-2">
                                                    <input class="form-control" type="datetime-local" id="startTs" name="startTs" value="${start}">
                                                </div>
                                                <label for="example-datetime-local-input" class="col-sm-1 col-form-label">结束时间</label>
                                                <div class="col-sm-2">
                                                    <input class="form-control" type="datetime-local" id="endTs" name="endTs" value="${end}">
                                                </div>
                                                <button type="submit" class="btn btn-outline-primary waves-effect waves-light col-sm-1">查询</button>
                                            </div>					               
                                            </form>                                                             
									       	<div class="table-responsive b-0" data-pattern="priority-columns">
												<table id="datatable-buttons"
													class="table table-striped table-bordered" cellspacing="0"
													width="100%" style="overflow: auto;">
													<thead>
														<tr style="background-color: #b0ece3;">
															<th>设备编号</th>
															<th>告警时间</th>
															<th>告警点位</th>
															<th>告警类型</th>
															<th>告警内容</th>
															<th>上限数值</th>
															<th>下限数值</th>
															<th>告警数值</th>
															<th>告警位置</th>
															<th>恢复时间</th>
														</tr>
													</thead>
													<tbody>													
														<c:forEach var="document" items="${documents}">
															<tr>
																<td>${document.nodeName }</td>
																<td>${document.eventTime }</td>
																<td>${document.source }</td>
																<td>${document.level }</td>
																<td>${document.text }</td>
																<td>${document.highLimit}</td>
																<td>${document.lowLimit}</td>
																<td>${document.value}</td>
																<td>${document.path }</td>
																<td>${document.recoverTime }</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
 									        </div>
                                        </div>
                                    </div>
                                </div> <!-- end col -->
                            </div>
                        </div>
                   </div> <!-- end row -->
					<!-- container -->
		      </div>
				<!-- content -->
		 </div>
			<!-- End Right content here -->
			<%@include file="/menu/footer.jsp"%>
	</div>
		<!-- END wrapper -->
</div>
	<!-- jQuery  -->
        <!-- Required datatable js -->
        <script src="${proPath}/assets/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Buttons examples -->
        <script src="${proPath}/assets/plugins/datatables/dataTables.buttons.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/buttons.bootstrap4.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/jszip.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/pdfmake.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/vfs_fonts.js"></script>
        <script src="${proPath}/assets/plugins/datatables/buttons.html5.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/buttons.print.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/buttons.colVis.min.js"></script>
        <!-- Responsive examples -->
        <script src="${proPath}/assets/plugins/datatables/dataTables.responsive.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/responsive.bootstrap4.min.js"></script>

        <!-- Datatable init js -->
        <script src="${proPath}/assets/pages/datatables.init.js"></script>


        <!-- App js -->
        <script src="${proPath}/assets/js/app.js"></script>


</body>
</html>