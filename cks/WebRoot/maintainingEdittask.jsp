<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>业主报修待办任务处理页</title>
<meta content="Admin Dashboard" name="description" />
<meta content="ThemeDesign" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>
<c:set var="proPath" value="${pageContext.request.contextPath}" />

 <link rel="shortcut icon" href="assets/images/faviicon.png">
 
 <link rel="stylesheet" type="text/css"
	href="${proPath}/assets/css/swiper.min.css" /> 
 <link type="text/css" rel="stylesheet" href="${proPath}/assets/css/app.css">
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
 		<style type="text/css">
		.xy {color:#868E96 !important;}
		/*黄色提醒*/ 
		.ye {
		color:#F2AD4E !important;
		font-weight:bold;
		}
		/*按钮禁止以后置灰色 */
		button:disabled {
		color:#e3e3e3 !important;
		border:1px solid #e3e3e3 !important;
		}
		.po-cmt {
         padding-left: 10px;

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
										
						<div class="row">
							<div class="col-lg-8">
							  <div class="row">
							    <div class="col-md-12">
								  <div class="card m-b-30">
									<div class="card-body" >
										<h4 class="mt-0 header-title">待办任务处理</h4>
										   <form class="" action="" onsubmit="" id="form_data" method="post">
											<div class="row">
											   <div class="col-md-6">
												   <div class="p-20">									  	           
														<div class="form-group row">
															<label for="example-text-input" class="col-sm-3 col-form-label">报修单编号</label>
															<div class="col-sm-9" >
															 <select class="form-control" style="font-size: 11px" id="equipmentrepairId" name="equipmentrepairId" onchange="repairChange()" value="${mEquipmentRepairO.equipmentrepairId}">
																<c:forEach items="${equipmentrepairs}"
																	var="mEquipmentRepairO">
																	<option value="${mEquipmentRepairO.equipmentrepairId}">${mEquipmentRepairO.equipmentrepairId}</option>
																</c:forEach>
															</select>
															</div>
														</div>
														<div class="form-group row">
															<label for="example-text-input" class="col-sm-3 col-form-label">报修时间</label>
															<div class="col-sm-9">
																<input type="text" class="form-control" name="eprCkTime"
																	id="eprCkTime" value="${mEquipmentRepairO.eprCkTime}"
																	readonly="readonly" style="background-color: white" />
															</div>
														</div>

														<div class="form-group row">
															<label for="example-text-input" class="col-sm-3 col-form-label">报修地址</label>
															<div class="col-sm-9">
																<input type="text" class="form-control"
																	name="epLocation" id="epLocation"
																	value="${mEquipmentRepairO.epLocation}"
																	readonly="readonly" style="background-color: white" />
															</div>
														</div>

														<div class="form-group row">
															<label for="example-text-input" class="col-sm-3 col-form-label">设备名称</label>
															<div class="col-sm-9">
																<input type="text" class="form-control"
																	name="epHomeEquname" id="epHomeEquname"
																	value="${mEquipmentRepairO.epHomeEquname}"
																	readonly="readonly" style="background-color: white" />
															</div>
														</div>
														<div class="form-group row">
															<label for="example-text-input" class="col-sm-3 col-form-label">故障描述</label>
															<div class="col-sm-9">
																<textarea required class="form-control" rows="2"
																	name="eprCkDescribe" id="eprCkDescribe"
																	style="background-color: white" readonly="readonly">${mEquipmentRepairO.eprCkDescribe}</textarea>
															</div>
														</div>
														<div class="form-group row">
															<label for="example-text-input" class="col-sm-3 col-form-label">故障等级</label>
															<div class="col-sm-9">
																<input type="text" class="form-control"
																	name="eprCkLevel" id="eprCkLevel"
																	value="${mEquipmentRepairO.eprCkLevel}"
																	readonly="readonly" style="background-color: white" />
															</div>
														</div>
														<div class="form-group row">
															<label for="example-text-input" class="col-sm-3 col-form-label">故障情况拍照</label> 
															<input type="text" class="form-control" placeholder="拍照签到" name="eprCkPhoto" id="eprCkPhoto" style="display: none" />
															<div id="list">
																<ul>
																	<li>
																		<div class="po-cmt">
																			<div class="po-hd">
																				<div class="post">
																					<p>
																					<div id="faceImgDiv" style="display: none"></div>
																					<div id="changeImgDiv" style="display: none"></div>
																					</p>
																				</div>
																			</div>
																			<div class="r"></div>
																		</div>
																	</li>
																</ul>
															</div>

															<div class="big_img">
																<div class="swiper-container2">
																	<div class="swiper-wrapper"></div>
																</div>
															</div>
<!-- 															<div id="faceImgDiv" style="display: none"></div>
															<div id="changeImgDiv" style="display: none"></div> -->
														</div>
													</div>
												</div>
												<div class="col-md-6">
													<div class="p-20">
														<div class="form-group row">
															<label  for="example-text-input" class="col-sm-3 col-form-label">业主姓名</label>
															<div class="col-sm-9">
																<input type="text" class="form-control"
																	name="userIdName" id="userIdName"
																	value="${mEquipmentRepairO.userIdName}"
																	readonly="readonly" style="background-color: white" />
															</div>
														</div>

														<div class="form-group row">
															<label  for="example-text-input" class="col-sm-3 col-form-label">业主电话</label>
															<div class="col-sm-9">
																<input type="text" class="form-control" name="mobile"
																	id="mobile" value="${mEquipmentRepairO.mobile}"
																	readonly="readonly" style="background-color: white" />
															</div>
														</div>

														<!------------------维修派工-------------------------------------------------------------------------------------------->
														<div style="display: none" id="leaderDispatch">
<!-- 															<div class="form-group">
																<label  for="example-text-input" class="col-sm-3 col-form-label">维修派工</label>
																<div class="col-sm-9">
																	<hr
																		style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																</div>
															</div> -->
															<div class="form-group row" >
																<label  for="example-text-input" class="col-sm-3 col-form-label">维修责任人</label>
																<div class="col-sm-9">
																	<select class="form-control" id="epReId" name="epReId"
																		value="${mEquipmentRepairO.epReId}">
																		<option value="">---请选择---</option>
																		<c:forEach items="${repairUserList}" var="dict">
																			<option value="${dict.userId}">${dict.userName}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</div>
														<!------------------维修实施-------------------------------------------------------------------------------------------->
														<div style="display: none" id="repair">
<!-- 															<div class="form-group ">
																<label  for="example-text-input" class="col-sm-3 col-form-label">维修实施</label>
																<div class="col-sm-9">
																	<hr style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																</div>
															</div> -->
															<div class="form-group row">
																<label  for="example-text-input" class="col-sm-3 col-form-label">维修情况说明</label>
																<div class="col-sm-9">
																	<%-- <textarea required class="form-control" rows="3"  name="epReDescribe"  id="epReDescribe" style="background-color: white" >${mEquipmentRepairO.epReDescribe}</textarea> --%>
																	<input type="text" class="form-control"
																		name="epReDescribe" id="epReDescribe"
																		value="${mEquipmentRepairO.epReDescribe}" />
																</div>
															</div>
															<div class="form-group row">
																<label  for="example-text-input" class="col-sm-3 col-form-label">维修完成时间</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control" name="epReTime"
																		id="epReTime" value="${mEquipmentRepairO.epReTime}"
																		placeholder="维修完成时间" />
																</div>
															</div>
														</div>
														<!------------------维修确认-------------------------------------------------------------------------------------------->
														<div style="display: none" id="confirm">
															<div class="form-group row">
																<label  for="example-text-input" class="col-sm-3 col-form-label">维修确认</label>
																<div class="col-sm-9">
																	<hr
																		style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																	<div>
																		<small class="text-muted ye">维修人员已确认修复故障</small>
																	</div>
																	<div>
																		<small class="text-muted ye">请进行设备检查确认</small>
																	</div>
																</div>
															</div>
															<div class="form-group row">
																<label  for="example-text-input" class="col-sm-3 col-form-label">设备状态</label>
																<div class="col-sm-9">
																	<select class="form-control" id="epReStatus"
																		name="epReStatus"
																		value="${mEquipmentRepairO.epReStatus}">
																		<c:forEach items="${fns:getDictList('status_type')}"
																			var="dict">
																			<option value="${dict.value}">${dict.label}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</div>

														<div class="form-group">
															<div class="col-sm-9">
																<button type="button" id="rpAdd" onclick="repAdd()"
																	class="btn btn-primary waves-effect waves-light"
																	style="display: none">维修记录新增</button>

																<button type="button" id="submit" onclick="save()"
																	class="btn btn-primary waves-effect waves-light">
																	提交</button>

																<span style="color: red;" id="tip"></span>

															</div>
														</div>
													</div>
												</div>
											</div>
										</form>
                                       </div>
									</div>
								</div>
						    </div>
						</div>	
							<div class="col-lg-4">
								<div class="row">
									<div class="col-md-12">
										<div class="card m-b-30">
											<div class="card-bodys">
												<%@include file="/menu/map2.jsp"%>
											</div>
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
				<!-- content -->
			</div>
			<!-- End Right content here -->
			<%@include file="/menu/footer.jsp"%>
		</div>
		<!-- END wrapper -->
	</div>


	<!-- jQuery  -->

        <!-- Parsley js -->
        <script type="text/javascript" src="${proPath}/assets/plugins/parsleyjs/parsley.min.js"></script>
        <!-- App js -->
        <script src="${proPath}/assets/js/app.js"></script>
                <!-- Dropzone js -->
        <script src="${proPath}/assets/plugins/dropzone/dist/dropzone.js"></script>
        <script type="text/javascript" src="${proPath}/assets/js/swiper.min.js"></script> 
        <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>

 

<script type="text/javascript">
$(document).ready(function(){
	
    $('form').parsley();
    //初始化图片预览
     init();
    //根据不同的流程节点显示
  	 initDiv();
  	
    //图片轮播
});
	function init() {
		
		var eprCkPhoto = '${mEquipmentRepairO.eprCkPhoto}';

		var arr = new Array();
		arr = eprCkPhoto.trim().split(',');

		$("#faceImgDiv").show();
		for (var k = 0; k < arr.length; k++) {
			arr[k] = "${returnUrl}" + arr[k];
			
			$("#faceImgDiv").append('<img alt=""  class="list-img" src="'+arr[k]+'"   style="width:80px;height: 80px;">');
		}
		
	}
	function initDiv() {
		//获取下一个节点
		var epAcNextid = '${mEquipmentRepairO.epAcNextid}';

		//1:报修申请 
		if (epAcNextid == '1') {
			//2.主管派单
		} else if (epAcNextid == '2') {
			$("#leaderDispatch").show();
			$("#chooseImage").hide();
			//3.维修中（维修确认）
		} else if (epAcNextid == '3') {
			//3.维修中（维修确认）
			$("#leaderDispatch").show();
			$("#epReId").val('${mEquipmentRepairO.epReId}');
			$("#epReId").attr("disabled", "disabled");
			$("#repair").show();
			//时间
			var d = new Date();
			var timenow = d.getFullYear() + '-' + (d.getMonth() + 1) + '-'
					+ d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes()
					+ ':' + d.getSeconds();
			$("#epReTime").val(timenow);
			$("#rpAdd").show();
			//4.发起人确认
		} else if (epAcNextid == '4') {
			$("#leaderDispatch").show();
			$("#epReId").val('${mEquipmentRepairO.epReId}');
			$("#epReId").attr("disabled", "disabled");
			$("#epReDescribe").attr("readonly", "readonly");
			$("#repair").show();
			$("#confirm").show();
			//5.结束
		} else if (epAcNextid == '5') {

		}

	}

	$("#equipmentrepairId").bind("change",function() {
						var nSel = document.getElementById("equipmentrepairId");
						var index = nSel.selectedIndex; // 选中索引
						var text = nSel.options[index].text;
						var value = nSel.options[index].value;

						var arr = new Array();
						var arrImg = new Array();
						<c:forEach items="${equipmentrepairs}" var= "EquipmentRepair">
						var module = {
							"equipmentrepairId" : "${EquipmentRepair.equipmentrepairId}",
							"eprCkTime" : "${EquipmentRepair.eprCkTime}",
							"epLocation" : "${EquipmentRepair.epLocation}",
							"epHomeEquname" : "${EquipmentRepair.epHomeEquname}",
							"eprCkDescribe" : "${EquipmentRepair.eprCkDescribe}",
							"eprCkPhoto" : "${EquipmentRepair.eprCkPhoto}",
							"eprCkLevel" : "${EquipmentRepair.eprCkLevel}",
							"userIdName" : "${EquipmentRepair.userIdName}",
							"mobile" : "${EquipmentRepair.mobile}",
							"epReDescribe" : "${EquipmentRepair.epReDescribe}"
						};
						arr.push(module);
						</c:forEach>
						$("#changeImgDiv").html("");
						for (var i = 0; i < arr.length; i++) {
							if (value == arr[i].equipmentrepairId) {
								$("#eprCkTime").val(arr[i].eprCkTime);
								$("#epLocation").val(arr[i].epLocation);
								$("#epHomeEquname").val(arr[i].epHomeEquname);
								$("#eprCkDescribe").val(arr[i].eprCkDescribe);
								$("#eprCkLevel").val(arr[i].eprCkLevel);
								$("#epReDescribe").val(arr[i].epReDescribe);
								$("#userIdName").val(arr[i].userIdName);
								$("#mobile").val(arr[i].mobile);
								$("#faceImgDiv").hide();
								$("#changeImgDiv").show();
 								arrImg = arr[i].eprCkPhoto.trim().split(',');
								for (var k = 0; k < arrImg.length; k++) {
									arrImg[k] = "${returnUrl}" + arrImg[k];
									$("#changeImgDiv").append('<img alt="" class="list-img" src="'+arrImg[k]+'"  style="width:80px;height: 80px;">');
								} 
							}
						}

					});

	function save() {
		var form_data = $('#form_data').serialize();
		$.ajax({
			url : "${proPath}/submitOwnerToDo.action",
			data : form_data,
			type : "post",
			async : false,
			beforeSend : function() {
				$("#tip").html("<span style='color:blue'>正在处理...</span>");
				return true;
			},
			success : function(data) {
				if (data.success == true) {
					$("#tip").html("<span style='color:red'></span>");
					alert('操作成功');
				} else {
					$("#tip").html("<span style='color:red'>失败，请重试</span>");
					alert('操作失败');
				}
			},
			error : function() {
				alert('请求出错');
			},
			complete : function() {
				//location.reload(true);
			}
		});
	}

	function repAdd() {
		var form_data = $('#form_data').serialize();
		$.ajax({
			url : "${proPath}/ownerRpAdd.action",
			data : form_data,
			type : "post",
			async : false,
			beforeSend : function() {
				$("#tip").html("<span style='color:blue'>正在处理...</span>");
				return true;
			},
			success : function(data) {
				if (data.success == true) {
					$("#tip").html("<span style='color:red'></span>");
					alert('维修记录新增成功!');
				} else {
					$("#tip").html("<span style='color:red'>失败，请重试</span>");
					alert('维修记录新增失败!');
				}
			},
			error : function() {
				alert('请求出错');
			},
			complete : function() {
				//location.reload(true);

			}
		});
	}
	 var mySwiper = new Swiper('.swiper-container2', {
	        loop: false,
	        pagination: '.swiper-pagination2',
	        })
	    $("#list").on("click", ".post img", 
	    function() {
	        var imgBox = $(this).parents(".post").find("img");
	        var i = $(imgBox).index(this);
	        $(".big_img .swiper-wrapper").html("")

	        for(var j = 0 ,c = imgBox.length; j < c ;j++){
	         $(".big_img .swiper-wrapper").append('<div class="swiper-slide"><div class="cell"><img src="' + imgBox.eq(j).attr("src") + '" / ></div></div>');
	        }
	        mySwiper.updateSlidesSize();
	        mySwiper.updatePagination();
	        $(".big_img").css({
	            "z-index": 1001,
	            "opacity": "1"
	        });
	        mySwiper.slideTo(i, 0, false);
	        return false;
	    });
	  
	    $(".big_img").on("click", 
	    function() {
	        $(this).css({
	            "z-index": "-1",
	            "opacity": "0"
	        });

	    });
	/*调起大图 E*/
	
</script>        
   

</script>  

</body>

</html>