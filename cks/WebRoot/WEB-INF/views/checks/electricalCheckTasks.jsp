<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>历史巡检单</title>
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
        <div id="preloader"><div id="status"><div class="spinner"></div></div></div>

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

                                            <h4 class="mt-0 header-title">历史巡检单记录</h4>
                                            <p class="text-muted m-b-30 font-14">用于记录已经提交的巡检单</p>
                                            <div class="table-rep-plugin">         
									       	<div class="table-responsive b-0" data-pattern="priority-columns">
                                            <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%"  style="overflow:auto;">
                                                <thead>
                                                <tr style="background-color: #b0ece3">
                                                	
													<th>设备编号</th>
													<th>设备名称</th>
													<th>设备类型</th>
                                                    <th>设备状态(上一次)</th>
                                                    <th>巡检人员</th>
                                                    <th>巡检定位</th>
                                                    <th>巡检时间</th>
                                                    
                                                    <th>设备状态</th>
                                                    <th>规定日期的保养日期</th>
                                                    <th>距离规定的保养日期剩余天数(/天)</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>


											<tbody>
												<c:forEach items="${list}" var="electricalCheckT">
													<tr>
														<td>${electricalCheckT.epId}</td>
														<td>${electricalCheckT.epName}</td>
														<td>${fns:getDictLabel(electricalCheckT.epType,'epType','')}</td>
														<td>${fns:getDictLabel(electricalCheckT.statusOldVal,'status_type','')}</td>
														<td>${electricalCheckT.userIdName}</td>
														
														<td>${electricalCheckT.checksLoc}</td>
														<td>${electricalCheckT.checksTime}</td>
														<td>${fns:getDictLabel(electricalCheckT.statusNewVal,'status_type','')}</td>
														<td>${electricalCheckT.maintenanceDate}</td>
														<td>${electricalCheckT.distanceDays}</td>
														<td><a class="nav-link active" href="${proPath}/electricalCheck/displayOneById.action?checksId=${electricalCheckT.checksId}">查看</a></td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
 									</div>
 									
                                        </div>
                                    </div>
                                </div> <!-- end col -->
 
                            </div> <!-- end row -->
                        </div><!-- container -->


                    </div> <!-- Page content Wrapper -->

                </div> <!-- content -->

			<%@include file="/menu/footer.jsp"%>

            </div>
            <!-- End Right content here -->

        </div>
  </div>       
        <!-- END wrapper -->



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

<script type="text/javascript">





</script>



    </body>
</html>