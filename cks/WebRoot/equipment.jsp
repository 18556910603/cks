<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>设备管理页</title>
<meta content="Admin Dashboard" name="description" />
<meta content="ThemeDesign" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>
<c:set var="proPath" value="${pageContext.request.contextPath}" />

<link rel="shortcut icon" href="${proPath}/assets/images/faviicon.png">

<!-- DataTables -->
<link
	href="${proPath}/assets/plugins/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${proPath}/assets/plugins/datatables/buttons.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<!-- Responsive datatable examples -->
<link
	href="${proPath}/assets/plugins/datatables/responsive.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />

<link href="${proPath}/assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="${proPath}/assets/css/icons.css" rel="stylesheet"
	type="text/css">
<link href="${proPath}/assets/css/style.css" rel="stylesheet"
	type="text/css">

</head>

<body class="fixed-left">
	<!-- Loader -->
	<div id="preloader">
		<div id="status">
			<div class="spinner"></div>
		</div>
	</div>
	<!-- Begin page -->
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

										<h4 class="mt-0 header-title">设备基础信息表</h4>
										<p class="text-muted m-b-30 font-14">用于设备状态表信息</p>
										<div class="table-rep-plugin">
											<div class="table-responsive b-0"
												data-pattern="priority-columns">
												<table id="datatable-buttons"
													class="table table-striped table-bordered" cellspacing="0"
													width="100%" style="overflow: auto;">
													<thead>
														<tr style="background-color: #b0ece3;">
															<th>设备编号</th>
															<th>设备名称</th>
															<th>设备型号</th>
															<th>设备区域</th>
															<th>安装地点</th>
															<th>设备类型</th>
															<th>保养日期</th>
															<th>生产商</th>
															<!-- 															<th>供应商</th> -->
															<th>购买时间</th>
															<th>启用时间</th>
															<th>
															操作
															</th>
															<th style="padding-bottom: .55rem;">
															<button type="button" class="btn btn-primary waves-effect waves-light" style="line-height: 1.1;"
															data-toggle="modal" data-target="#myModal" onclick="add()">新增</button>															
															</th>
																
														</tr>
													</thead>
													<tbody>
														<c:forEach var="equipment" items="${equipments}">
															<tr>
																<td>${equipment.epId }</td>
																<td>${equipment.epName }</td>
																<td>${equipment.epModelNum }</td>
																<td>${equipment.epLoc }</td>
																<td>${equipment.epLocation }</td>
																<td>
																	${fns:getDictLabel(equipment.epType,'epType',equipment.epType)}
																</td>
																<td>${equipment.maintenanceDate }</td>
																<td>${equipment.epProducer }</td>
																<%-- 																<td>${equipment.epProvider }</td> --%>
																<td>${equipment.epBuyingTime }</td>
																<td>${equipment.epUpTime }</td>
																<td colspan="2">
																	<div class="modal-footer">
																		<button type="button" class="btn btn-success "
																			data-toggle="modal" data-target="#myModal"
																			onclick="get_edit_info('${equipment.epId}')">修改</button>
																		<button type="button" class="btn btn-danger"
																			onclick="delete_info('${equipment.epId}')">删除</button>
																		<button type="button" class="btn btn-warning "
																			data-toggle="modal" data-target="#myModal"
																			onclick="get_qrcode_info('${equipment.epId}')">二维码</button>
																		<button type="button" class="btn btn-primary "
																			onclick="window.location='getEquip.action?epId=${equipment.epId}'">查看</button>
																	</div>
																</td>
																<td style="display:none"></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>

										</div>
									</div>
								</div>
								<!-- end col -->
							</div>
						</div>


						<div class="row">

							<div class="col-sm-6 col-md-3 m-t-30">
								<form method="post" action="" class="form-horizontal"
									role="form" id="form_data" onsubmit="return check_form()"
									style="margin: 20px;">

									<input type="act" id="act" name="act" style="display: none;"
										value="add">

									<!-- sample modal content -->
									<div id="myModal" class="modal fade" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title mt-0" id="myModalLabel">设备信息</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
												</div>
												<div class="modal-body">
													<div id="headDiv">
														<div class="row">
															<div class="col-md-6">
																<div class="p-20">
																	<div class="form-group" id="epIdDiv">
																		<label>设备编号</label> <select class="form-control"
																			id="epId" name="epId" onchange="equipChange()">
																			<c:forEach items="${equipments}" var="equip">
																				<option value="${equip.epId}">${equip.epId}</option>
																			</c:forEach>
																		</select>
																	</div>

																	<div class="form-group">
																		<label>设备名称</label>
																		<div>
																			<input type="text" class="form-control" name="epName"
																				id="epName" />
																		</div>
																	</div>

																	<div class="form-group">
																		<label>设备型号</label>
																		<div>
																			<input type="text" class="form-control"
																				name="epModelNum" id="epModelNum" />
																		</div>
																	</div>
																	<div class="form-group">
																		<label>设备区域</label>
																		<div>
																			<select name="epLoc" id="epLoc" class="form-control">
																				<option value="">--请选择--</option>
																				<option value="B1层">B1层</option>
																				<option value="B2层">B2层</option>
																				<option value="B3层">B3层</option>
																				<option value="B4层">B4层</option>
																				<option value="B5层">B5层</option>
																			</select>
																		</div>
																	</div>
																	<div class="form-group">
																		<label>安装地点</label>
																		<div>
																			<input type="text" class="form-control"
																				name="epLocation" id="epLocation" />
																		</div>
																	</div>
																	<div class="form-group">
																		<label>设备类型</label>
																		<div>
																			<select class="form-control" id="epType"
																				name="epType">
																				<option value="">--请选择--</option>
																				<c:forEach items="${fns:getDictList('epType')}"
																					var="dict">
																					<option value="${dict.value}">${dict.label}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																	<div class="form-group">
																		<label>生产商</label>
																		<div>
																			<input type="text" class="form-control"
																				name="epProducer" id="epProducer" />
																		</div>
																	</div>
																</div>
															</div>

															<div class="col-md-6">
																<div class="p-20">
																	<div class="form-group">
																		<label>供应商</label>
																		<div>
																			<input type="text" class="form-control"
																				name="epProvider" id="epProvider" />
																		</div>
																	</div>
																	<div class="form-group">
																		<label>购买时间</label>
																		<div>
																			<input class="form-control" type="date"
																				name="epBuyingTime" id="epBuyingTime" />
																		</div>
																	</div>

																	<div class="form-group">
																		<label>启用时间</label>
																		<div>
																			<input type="date" class="form-control"
																				name="epUpTime" id="epUpTime" />
																		</div>
																	</div>
																	<div class="form-group">
																		<label>保养时间</label>
																		<div>
																			<input class="form-control" type="date"
																				name="maintenanceDate" id="maintenanceDate" />
																		</div>
																	</div>
																	<div class="form-group">
																		<label>设备负责人</label> <select class="form-control"
																			name="epUserId" id="userId">
																			<c:forEach items="${epUsers}" var="epuser">
																				<option value="${epuser.epUserId}">${epuser.user.userName}</option>
																			</c:forEach>

																		</select>

																	</div>

																	<div class="form-group">
																		<label>联系人电话</label>
																		<div>
																			<input type="text" class="form-control" name="mobile"
																				id="mobile" readonly="readonly" />
																		</div>
																	</div>
																</div>
															</div>

														</div>
														<!-- end col -->
													</div>
													<!-- end row -->
															<div class="form-group" id="qrCodeDiv">
																<label>设备二维码</label>
																<div>
																	<img name="qrCode" id="qrCode">
																</div>
															</div>													
												</div>
												<div class="modal-footer" id="footerDiv">
													<button type="button"
														class="btn btn-secondary waves-effect"
														data-dismiss="modal">取消</button>
													<button type="submit"
														class="btn btn-primary waves-effect waves-light">提交</button>
													<span id="tip"></span>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
								</form>
								<!-- /.modal -->
							</div>
						</div>
						<!-- end row -->




					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->


			</div>
		</div>




	</div>
	<!-- end row -->

	<%@include file="/menu/footer.jsp"%>

	<!-- End Right content here -->

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
		$("#epId").bind("change", function() {
			
			var act = $.trim($('#act').val());
			// if(act=='add'){ 
			var nSel = document.getElementById("epId");
			var index = nSel.selectedIndex; // 选中索引
			var text = nSel.options[index].text;
			var value = nSel.options[index].value;

			var arr = new Array();
			<c:forEach items="${equipments}" var= "Equip">
			var module = {
				"epId" : "${Equip.epId}",
				"epName" : "${Equip.epName}",
				"epModelNum" : "${Equip.epModelNum}",
				"epLoc" : "${Equip.epLoc}",
				"epLocation" : "${Equip.epLocation}",
				"epType" : "${Equip.epType}",
				"epProducer" : "${Equip.epProducer}",
				"epProvider" : "${Equip.epProvider}",
				"epBuyingTime" : "${Equip.epBuyingTime}",
				"epUpTime" : "${Equip.epUpTime}",
				"epUserId" : "${Equip.epUserId}"
			};
			arr.push(module);
			</c:forEach>
			var epUserId = "";
			for (var i = 0; i < arr.length; i++) {
				if (value == arr[i].epId) {
					$("#epName").val(arr[i].epName);
					$("#epModelNum").val(arr[i].epModelNum);
					$("#epLoc").val(arr[i].epLoc);
					$("#epLocation").val(arr[i].epLocation);
					$("#epType").val(arr[i].epType);
					$("#epProducer").val(arr[i].epProducer);
					$("#epProvider").val(arr[i].epProvider);
					$("#epBuyingTime").val(arr[i].epBuyingTime);
					$("#epUpTime").val(arr[i].epUpTime);
					epUserId = arr[i].epUserId;
				}
			}
			var arrEpUser = new Array();
			<c:forEach items="${epUsers}" var= "epUser">
			var module = {
				"userId" : "${epUser.epUserId}",
				"mobile" : "${epUser.user.mobile}",
				"epUserId" : "${epUser.epUserId}"
			};
			arrEpUser.push(module);

			</c:forEach>
			for (var i = 0; i < arrEpUser.length; i++) {
				if (epUserId == arrEpUser[i].epUserId) {
					$("#userId").val(arrEpUser[i].epUserId);
					$("#mobile").val(arrEpUser[i].mobile);

				}
			}

		});

// 		$("#epLoc").bind("change", function() {
// 			

// 			var nSel = document.getElementById("epLoc");
// 			var index = nSel.selectedIndex; // 选中索引
// 			var text = nSel.options[index].text;
// 			var value = nSel.options[index].value;

// 			var arr = new Array();
// 			<c:forEach items="${epUsers}" var= "epUser">
// 			var module = {
// 				"epUserLoc" : "${epUser.epUserLoc}",
// 				"epUserId" : "${epUser.epUserId}",
// 				"userName" : "${epUser.user.userName}"
// 			};
// 			arr.push(module);
// 			</c:forEach>

// 			$("#userId").empty();
// 			$("#userId").html("<option value=''>--请选择--</option>");
// 			for (var i = 0; i < arr.length; i++) {
// 				if (value == arr[i].epUserLoc) {
// 					var partId = arr[i].epUserId;
// 					var partName = arr[i].userName;
// 					var $option = $("<option>").attr({
// 						"value" : partId
// 					}).text(partName);
// 					$("#userId").append($option);
// 					;
// 				}
// 				$("#userId").change();
// 			}
// 		});

		$("#epType").bind("change", function() {
			
			var nSel = document.getElementById("epType");
			var index = nSel.selectedIndex; // 选中索引
			var text = nSel.options[index].text;
			var value = nSel.options[index].value;

			var arr = new Array();
			<c:forEach items="${epUsers}" var= "epUser">
			var module = {
				"epUserType" : "${epUser.epUserType}",
				"epUserId" : "${epUser.epUserId}",
				"userName" : "${epUser.user.userName}"
			};
			arr.push(module);
			</c:forEach>
			$("#userId").empty();
			$("#userId").html("<option value=''>--请选择--</option>");
			for (var i = 0; i < arr.length; i++) {
				if (value == arr[i].epUserType) {

					var partId = arr[i].epUserId;
					var partName = arr[i].userName;
					var $option = $("<option>").attr({
						"value" : partId
					}).text(partName);
					$("#userId").append($option);
					;
				}
				$("#userId").change();
			}
		});

		$("#userId").bind("change", function() {
			

			var nSel = document.getElementById("userId");
			var index = nSel.selectedIndex; // 选中索引
			var text = nSel.options[index].text;
			var value = nSel.options[index].value;

			var arr = new Array();
			<c:forEach items="${epUsers}" var= "epUser">
			var module = {
				"epUserId" : "${epUser.epUserId}",
				"mobile" : "${epUser.user.mobile}"
			};
			arr.push(module);
			</c:forEach>

			for (var i = 0; i < arr.length; i++) {
				if (value == arr[i].epUserId) {
					$("#mobile").val(arr[i].mobile);
				}
			}
		});

		function add() {

			$('#headDiv').css('display', 'block');
			$('#footerDiv').css('display', 'block');
			$('#epIdDiv').css('display', 'none');
			$('#qrCodeDiv').css('display', 'none');
			$("#epId").val('');
			$("#epName").val('');
			$("#epModelNum").val('');
			$("#epLoc").val('');
			$("#epLocation").val('');
			$("#epType").val('');
			$("#epProducer").val('');
			$("#epProvider").val('');
			$("#epBuyingTime").val('');
			$("#epUpTime").val('');
			$("#userId").val('');
			$("#mobile").val('');
			$("#act").val('add');
		}

		// 编辑表单
		function get_edit_info(epId) {
			
			$("#act").val('update');
			$('#headDiv').css('display', 'block');
			$('#footerDiv').css('display', 'block');
			$('#qrCodeDiv').css('display', 'none');
			if (!epId) {
				alert('Error！');
				return false;
			}
			// var form_data = new Array();
			$
					.ajax({
						/*  url: "${proPath}/status/editOne.action",
						 data:{"statusId":statusId}, */
						url : "${proPath}/editEquip.action",
						data : {
							"epId" : epId
						},
						type : "post",
						async : false,
						beforeSend : function() {
							// $("#tip").html("<span style='color:blue'>正在处理...</span>");
							return true;
						},
						success : function(data) {
							if (data) {
								// 赋值

								$("#epId").val(data.equipment.epId);
								$("#epName").val(data.equipment.epName);
								$("#epModelNum").val(data.equipment.epModelNum);
								$("#epLoc").val(data.equipment.epLoc);
								$("#epLocation").val(data.equipment.epLocation);
								$("#epType").val(data.equipment.epType);
								$("#epProducer").val(data.equipment.epProducer);
								$("#epProvider").val(data.equipment.epProvider);
								$("#epBuyingTime").val(
										data.equipment.epBuyingTime);
								$("#epUpTime").val(data.equipment.epUpTime);
								
								$("#maintenanceDate").val(data.equipment.maintenanceDate); 
								$("#userId").val(data.epUser.epUserId);
								$("#mobile").val(data.epUser.user.mobile);

							} else {
								$("#tip")
										.html(
												"<span style='color:red'>失败，请重试</span>");
								//  alert('操作失败');
							}

						},
						error : function() {
							alert('请求出错');
						},
						complete : function() {
							// $('#tips').hide();
						}
					});

			return false;
		}

		//得到二维码
		function get_qrcode_info(epId) {
			
			$("#act").val('qrcode');
			$('#headDiv').css('display', 'none');
			$('#footerDiv').css('display', 'none');
			$('#qrCodeDiv').css('display', 'block');
			if (!epId) {
				alert('Error！');
				return false;
			}
			// var form_data = new Array();
			$
					.ajax({
						url : "${proPath}/qrcode.action",
						data : {
							"epId" : epId
						},
						type : "post",
						async : false,
						beforeSend : function() {
							// $("#tip").html("<span style='color:blue'>正在处理...</span>");
							return true;
						},
						success : function(data) {
							if (data) {
								// 赋值

								$("#qrCode").attr("src", data);

							} else {
								$("#tip")
										.html(
												"<span style='color:red'>失败，请重试</span>");
								alert('操作失败');
							}

						},
						error : function() {
							alert('请求出错');
						},
						complete : function() {
							// $('#tips').hide();
						}
					});

			return false;
		}

		// 提交表单
		function check_form() {
			
			var Url;
			var epId = $.trim($('#epId').val());
			var epType = $('#epType').val();
			var epName = $.trim($('#epName').val());
			var act = $.trim($('#act').val());
			if (act == 'update') {
				if (!epId) {
					alert('设备编号ID不能为空！');
					return false;
				}
				Url = "${proPath}/update.action";
			} else {
				if (!epType) {
					alert('设备类型不能为空！');
					return false;
				}
				if (!epName) {
					alert('设备名称不能为空！');
					return false;
				}
				Url = "${proPath}/add.action";
			}
			var form_data = $('#form_data').serialize();
			//        var submitData=decodeURIComponent(form_data,true);
			//     // 异步提交数据到action/add_action.php页面

			$.ajax({
						url : Url,
						data : form_data,
						type : "post",
						async : false,
						beforeSend : function() {
							$("#tip").html(
									"<span style='color:blue'>正在处理...</span>");
							return true;
						},
						success : function(data) {

							if (data.success == true) {
								$("#tip").html(
										"<span style='color:blueviolet'>"
												+ data.msg + "</span>");
								// document.location.href='system_notice.php'
								alert(data.msg);

							} else {
								$("#tip")
										.html(
												"<span style='color:red'>失败，请重试</span>");
								alert('操作失败');
							}
						},
						error : function() {
							alert(data.success);
							alert('请求出错');
						},
						complete : function() {
							location.reload(true);
							//                     $('#acting_tips').hide();
						}
					});

			//     return false;
		}

		//删除
		function delete_info(epId) {
			alertify
					.confirm(
							"你确定要删除吗？",
							function(ev) {
								ev.preventDefault();
								alertify.success("你已点击了确定");
								if (!epId) {
									alert('Error！');
									return false;
								}
								$
										.ajax({
											url : "${proPath}/delete.action",
											data : {
												"epId" : epId
											},
											type : "post",
											async : false,
											beforeSend : function() {
												// $("#tip").html("<span style='color:blue'>正在处理...</span>");
												return true;
											},
											success : function(data) {

												if (data > 0) {
													$("#tip")
															.html(
																	"<span style='color:blueviolet'>恭喜，删除成功！</span>");

													// document.location.href='world_system_notice.php'
													location.reload();
												} else {
													$("#tip")
															.html(
																	"<span style='color:red'>失败，请重试</span>");
													alert('操作失败');
												}

											},
											error : function() {
												alert('请求出错');
											},
											complete : function() {
												// $('#tips').hide();
											}
										});
							}, function(ev) {
								ev.preventDefault();
								alertify.error("你已点击了取消");
							});
			return false;
		}
	</script>

</body>

</html>