<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>主管分配不定期保养单</title>
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
<!-- Dropzone css -->
<link href="${proPath}/assets/plugins/dropzone/dist/dropzone.css"
	rel="stylesheet" type="text/css">

<link href="${proPath}/assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="${proPath}/assets/css/icons.css" rel="stylesheet"
	type="text/css">
<link href="${proPath}/assets/css/style.css" rel="stylesheet"
	type="text/css">
<link href="${proPath}/assets/css/fileinput.min.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
.xy {
	color: #868E96 !important;
}
/*黄色提醒*/
.ye {
	color: #F2AD4E !important;
	font-weight: bold;
}
/*按钮禁止以后置灰色 */
button:disabled {
	color: #e3e3e3 !important;
	border: 1px solid #e3e3e3 !important;
}
</style>
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
					   <form class="" action="" onsubmit="" id="form_data"method="post">
						<div class="row">
							<div class="col-lg-6">
								<div class="card m-b-30">
									<div class="card-body">

										<h4 class="mt-0 header-title">主管分配临时保养单</h4>
										<p class="text-muted m-b-30 font-14">用于主管分配对设备指定不定期保养.</p>
											<!------------------设备信息 -------------------------------------------------------------------------------------------->
											<div class="form-group">
												<label>设备信息</label>
												<div>
													<hr
														style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
												</div>
											</div>


											<div class="form-group">
												<label>设备编号</label>
												<div>
													<small class="text-muted">扫码获取设备编号</small>
												</div>
												<div>
													<div class="input-group">
														<input type="text" class="form-control" name="epId"
															id="epId" placeholder="设备编号" value=""       
															style="background-color: white" 
															onchange="showEquipment(this.value);">

														<div class="input-group-append bg-custom b-0">
															<span class="input-group-text"><i
																class="mdi mdi-qrcode-scan" id='scanQRCode'></i></span>
														</div>
													</div>
													<!-- input-group -->
												</div>
											</div>
											<div class="form-group">
												<label>设备名称</label>
												<div>
													<input type="text" class="form-control" name="epName" style="background-color: white" readonly="readonly"
														id="epName" placeholder="设备名称" />
												</div>
											</div>

											<div class="form-group">
												<label>设备状态</label>
												<div>
													<input type="text" class="form-control" name="statusName" style="background-color: white" readonly="readonly"
														id="statusName" placeholder="设备状态" />
												</div>
											</div>

											<div class="form-group" style="display: none">
												<label>设备状态值</label>
												<div>
													<input type="text" class="form-control" name="statusOldVal" style="background-color: white" readonly="readonly"
														id="statusOldVal" placeholder="设备状态值" />
												</div>
											</div>

											<div class="form-group">
												<label>规格型号</label>
												<div>
													<input type="text" class="form-control" name="epModelNum" style="background-color: white" readonly="readonly"
														id="epModelNum" placeholder="规格型号" />
												</div>
											</div>
											<div class="form-group">
												<label>安装地点</label>
												<div>
													<input type="text" class="form-control" name="epLocation" style="background-color: white" readonly="readonly"
														id="epLocation" placeholder="安装地点" />
												</div>
											</div>
											<div class="form-group">
												<label>设备类型</label>
												<div>
													<input type="text" class="form-control" name="epTypeName" style="background-color: white" readonly="readonly"
														id="epTypeName" placeholder="设备类型" />
												</div>
											</div>
											<div class="form-group" style="display: none">
												<label>设备类型值</label>
												<div>
													<input type="text" class="form-control" name="epType" style="background-color: white" readonly="readonly"
														id="epType" placeholder="设备类型值" />
												</div>
											</div>

									</div>
								</div>
							</div>
							<!-- end col -->
							
							<div class="col-lg-6">
								<div class="card m-b-30">
									<div class="card-body">

<!-- 										<h4 class="mt-0 header-title"></h4> -->
										<p class="text-muted m-b-30 font-14"></p>
											<!------------------巡检人员 -------------------------------------------------------------------------------------------->
											<div class="form-group">
												<label>人员信息</label>
												<div>
													<hr
														style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
												</div>
											</div>
											<div class="form-group">
												<label>保养人员</label>
												<div>
													<select class="form-control" id="userId" name="userId" style="background-color: white">
														<option value="">----请选择----</option>
														<c:forEach items="${userList}" var="dict">
															<option value="${dict.userId}">${dict.loginName}</option>
														</c:forEach>
													</select>												
												</div>
											</div>
											<div class="form-group">
												<label>派单主管</label>
												<div>
													<input type="text" class="form-control" placeholder="派单主管"
														value="${SessionContainer.user.loginName}" name="leaderId" style="background-color: white" readonly="readonly"
														id="leaderId" />
												</div>
											</div>											
											<div class="form-group">
												<div>
													<button type="button" id="submit" onclick="save()"
														class="btn btn-primary waves-effect waves-light">
														提交</button>
															<span style="color: red;" id="tip"></span>
												</div>
											</div>
										

									</div>
								</div>
							</div>							
						</div>
					 </form>
						<!-- end row -->

					</div>
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
	<!-- Parsley js -->
	<script type="text/javascript"
		src="${proPath}/assets/plugins/parsleyjs/parsley.min.js"></script>

	<!-- App js -->
	<script src="${proPath}/assets/js/app.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js">
		
	</script>
	<script type="text/javascript">
		wx.config({
			debug : false,
			appId : '${ret.appId}',
			timestamp : '${ret.timestamp}',
			nonceStr : '${ret.nonceStr}',
			signature : '${ret.signature}',
			jsApiList : [ 'checkJsApi', 'scanQRCode', 'chooseImage',
					'previewImage', 'uploadImage', 'downloadImage',
					'getNetworkType', 'openLocation', 'getLocation' ]
		// 必填，需要使用的JS接口列表
		});//end_config

		wx.error(function(res) {
			alert("出错了：" + res.errMsg);
		});

		wx.ready(function() {
			wx.checkJsApi({
				jsApiList : [ 'scanQRCode' ],
				success : function(res) {
					// 以键值对的形式返回，可用的api值true，不可用为false
					// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
				}
			});

			//扫描二维码
			document.querySelector('#scanQRCode').onclick = function() {
				wx.scanQRCode({
					needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
					scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
					success : function(res) {
						var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果  
						document.getElementById("epId").value =result.substr((result.indexOf('epId=')+5) ,(result.length-1)) ;//将扫描的结果赋予到jsp对应值上  
// 						alert("扫描成功::扫描码=" + result);
						showEquipment(result);
					}
				});
			};
		});//end_ready     
		
		
		// 编辑表单
		function showEquipment(epId) {
			var epId = $.trim($('#epId').val());
			if (!epId) {
				alert('Error！');
				return false;
			}
			$.ajax({
				url : "${proPath}/electricalMaintain/equipOne.action",
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
					if (data.success == true) {
						$("#epName").val(data.obj.epName);
						$("#statusName").val(data.obj.statusName);
						$("#statusOldVal").val(data.obj.statusVal);
						$("#epModelNum").val(data.obj.epModelNum);
						$("#epLocation").val(data.obj.epLocation);
						$("#epTypeName").val(data.obj.epTypeName);
						$("#epType").val(data.obj.epType);
						
						//规定保养时间
						$("#maintenanceDate").val(data.obj.maintenanceDate);
						//距离规定的保养日期还剩 0 天
						$("#distanceDays").val(data.obj.distanceDays);
						$("#submit").removeAttr("disabled");
						$("#tip").html("<span style='color:red'></span>");
					} else {
						$("#epName").val('');
						$("#statusName").val('');
						$("#statusOldVal").val('');
						$("#epModelNum").val('');
						$("#epLocation").val('');
						$("#epTypeName").val('');
						$("#epType").val('');			
	
						 //规定保养时间
						 $("#maintenanceDate").val('');
						//距离规定的保养日期还剩 0 天
						$("#distanceDays").val('');
						$("#tip").html("<span style='color:red'>"+data.msg+"</span>");
						$("#submit").attr("disabled", "disabled");
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

		function save() {
			var epId = $.trim($('#epId').val());
			if (!epId) {
				alert('设备编号ID不能为空！');
				return false;
			}
			var form_data = $('#form_data').serialize();
			$
					.ajax({
						url : "${proPath}/electricalMaintain/save.action",
						data : form_data,
						type : "post",
						async : false,
						beforeSend : function() {
							$("#tip").html("<span style='color:blue'>正在处理...</span>");
							$("#submit").attr("disabled","disabled");
							return true;
						},
						success : function(data) {
							if (data.success == true) {
								$("#tip").html("");
								window.location.href = "${proPath}/electricalCheck/tips.action";
							} else {
								$("#tip").html("<span style='color:red'>失败，请重试</span>");
								alert('操作失败');
							}
						},
						error : function() {
							alert('请求出错');
						},
						complete : function() {
							//         	                	location.reload(true);
						}
					});

			//        	     return false;       	   

		}

	</script>



</body>
</html>