<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>设备基本信息表</title>
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
<link rel="stylesheet" type="text/css"
	href="${proPath}/assets/css/swiper.min.css" /> 
<link type="text/css" rel="stylesheet" href="${proPath}/assets/css/app.css">

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
<!--  <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script> -->

<style type="text/css">
	.card-bodys {
    -webkit-box-flex: 1;
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    padding: 0.5rem;
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
							<div class="col-lg-6">
								<div class="row">
									<div class="col-md-12">
										<div class="card m-b-30">
											<div class="card-body">
												<h4 class="mt-0 header-title">设备详情页</h4>
												<div class="row">
													<div class="col-md-6">
														<div class="p-20">
															<form method="post" action="">
																<div class="form-group" id="epIdDiv">
																	<label>设备编号</label>&nbsp ${equipment.epId }
																	<%--    <input type="text"  class="form-control" name="epId" id="epId" value="${equipment.epId }"/> --%>
																</div>

																<div class="form-group">
																	<label>设备名称</label>&nbsp ${equipment.epName }
																</div>

																<div class="form-group">
																	<label>设备型号</label>&nbsp ${equipment.epModelNum }
																</div>

																<div class="form-group">
																	<label>设备区域</label>&nbsp ${equipment.epLoc }
																</div>

																<div class="form-group">
																	<label>安装地点</label>&nbsp ${equipment.epLocation }
																</div>

																<div class="form-group">
																	<label>设备类型</label>&nbsp
																	${fns:getDictLabel(equipment.epType,'epType',equipment.epType)}
																</div>

																<div class="form-group">
																	<label>生产商</label>&nbsp ${equipment.epProducer }
																</div>
																

																<div class="form-group">
																	<label>设备资料</label><a href="" title="设备资料" name="epProvider"id="epProvider" target="_blank">&nbsp<img src="/assets/images/pdf.png" style="height:25px;weight:20px" >.pdf</a>
																</div>  

															</form>
														</div>
													</div>
													<div class="col-md-6">
														<div class="p-20">
															<form action="#">


																<div class="form-group">
																	<label>启用时间</label>&nbsp ${equipment.epUpTime }
																</div>

																<div class="form-group">
																	<label>设备负责人</label>&nbsp ${epUser.user.userName }
																</div>

																<div class="form-group">
																	<label>联系人电话</label>&nbsp ${epUser.user.mobile }
																</div>

																<div class="form-group">
																	<label>设备地址</label>&nbsp ${equipment.address }
																</div>
																


																<div class="form-group" id="qrCodeDiv">
																	<label>设备二维码</label>
																	<div>
																		<img src="/code/${equipment.qrCode}" name="qrCode"
																			id="qrCode">
																	</div>
																</div>
																<div class="form-group" style="padding-bottom:3%">
																<label></label>
																</div>
															</form>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<%-- <div class="col-lg-6">
								<div class="row">
									<div class="col-md-12">
										<div class="card m-b-30">
											<div class="card-bodys">
												<%@include file="/menu/map.jsp"%>
											</div>
										</div>
									</div>
								</div>
							</div> --%>
							<div class="col-lg-6">
								<div class="row">
									<div class="col-md-12">
										<div class="card m-b-30">
											<div class="card-bodys" style="padding-bottom:57px;">
<!-- 												<img alt="位置平面图" id="wzImg"  style="width:680px;height:450px;padding-top:50px;"> -->
    											    <img src="assets/images/small/img-2.jpg" id="wzImg"  class="img-fluid" alt="Responsive image" style="width:680px;height:450px;padding-top:50px;">
											</div>
										</div>
									</div>
									
								</div>
							</div>
							
							
						</div>
						<div class="row">
							<div class="col-lg-12 col-md-12  m-b-30 ">

								<!-- <div  id="allmap"></div> -->
								<div class="col-12">
									<div class="card m-b-30">
										<div class="card-body">
											<h4 class="mt-0 header-title">设备维修历史</h4>

											<div class="table-rep-plugin">
												<div class="table-responsive b-0"
													data-pattern="priority-columns">
													<table id="datatable-buttons"
														class="table table-striped table-bordered" cellspacing="0"
														width="100%" style="overflow: auto;">
														<thead>
															<tr style="background-color: #b0ece3;">
																<th>维修工单号</th>
																<th>报修人</th>
																<th>报修时间</th>
																<th>故障情况描述</th>
																<th>维修责任人</th>
																<th>故障类别</th>
																<th>维修情况说明</th>
																<th>维修完成时间</th>


															</tr>
														</thead>
														<tbody>
															<c:forEach var="equipmentRepair"
																items="${equipmentRepairs}">
																<tr data-toggle="modal" data-target="#myModal" style="cursor:pointer;"
																	onclick="get_view_info('${equipmentRepair.equipmentrepairId}')">
																	<td>${equipmentRepair.equipmentrepairId}</td>
																	<td>${equipmentRepair.userIdName }</td>
																	<td>${equipmentRepair.eprCkTime }</td>
																	<td>${equipmentRepair.eprCkDescribe }</td>
																	<td>${equipmentRepair.epReIdName }</td>
																	<td>${equipmentRepair.epReKind}</td>
																	<td>${equipmentRepair.epReDescribe }</td>
																	<td>${equipmentRepair.epReTime }</td>

																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<!-- end col -->
										<div class="row">
											<div class="col-sm-6 col-md-3 m-t-30">

												<form method="post" action="" class="form-horizontal"
													role="form" id="form_data" onsubmit="return check_form()">

													<input type="act" id="act" name="act"
														style="display: none;" value="add">

													<!-- sample modal content -->

													<div id="myModal" class="modal fade" tabindex="-1"
														role="dialog" aria-labelledby="myModalLabel"
														aria-hidden="true">
														<div class="modal-dialog">
															<div class="modal-content">

																<div class="modal-header">
																	<h5 class="modal-title mt-0" id="myModalLabel">设备维修详情</h5>
																	<button type="button" class="close"
																		data-dismiss="modal" aria-hidden="true">×</button>
																</div>
																<div class="modal-body">

																	<form class="form-horizontal" role="form" method="post"
																		onsubmit="check_form()" id="editform">

																		<div>
																			<div class="form-group">
																				<label>故障报修</label>
																				<div>
																					<hr
																						style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																				</div>
																			</div>

																			<div class="form-group">
																				<label>报修工单号</label>
																				<div>
																					<input type="text" class="form-control"
																						name="equipmentrepairId" id="equipmentrepairId"
																						placeholder="报修工单号" readonly="readonly">
																				</div>
																			</div>

																			<div class="form-group">
																				<label>设备编号</label>
																				<div>
																					<input type="text" class="form-control" name="epId"
																						id="epId" placeholder="设备编号" readonly="readonly">
																				</div>
																			</div>

																			<div class="form-group">
																				<label>设备名称</label>
																				<div>
																					<input type="text" class="form-control"
																						name="epHomeEquname" id="epHomeEquname"
																						readonly="readonly" />
																				</div>
																			</div>

																			<div class="form-group">
																				<label>规格型号</label>
																				<div>
																					<input type="text" class="form-control"
																						name="epModelNum" id="epModelNum"
																						readonly="readonly" />
																				</div>
																			</div>

																			<div class="form-group">
																				<label>安装地点</label>
																				<div>
																					<input type="text" class="form-control"
																						name="epLocation" id="epLocation"
																						readonly="readonly" />
																				</div>
																			</div>

																			<div class="form-group">
																				<label>设备类型</label>
																				<div>
																					<input type="text" class="form-control"
																						name="epTypeName" id="epTypeName"
																						placeholder="设备类型" readonly="readonly" />
																				</div>
																			</div>

																			<div class="form-group">
																				<label>报修时间</label>
																				<div>
																					<input type="text" class="form-control"
																						name="eprCkTime" id="eprCkTime" placeholder="报修时间"
																						readonly="readonly" />
																				</div>
																			</div>

																			<div class="form-group">
																				<label>故障情况拍照</label> <input type="text"
																					class="form-control" placeholder="拍照签到"
																					name="eprCkPhoto" id="eprCkPhoto"
																					style="display: none" />

																				<div id="list">
																					<ul>
																						<li>
																							<div class="po-cmt">
																								<div class="po-hd">
																									<div class="post">
																										<p>
																										<div id="faceImgDiv" style="display: none">
																										</div>
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
																			</div>

																			<div class="form-group">
																				<label>故障情况描述</label>
																				<div>
																					<input type="text" class="form-control"
																						name="eprCkDescribe" id="eprCkDescribe"
																						placeholder="故障情况描述" readonly="readonly" />
																				</div>
																			</div>

																			<div class="form-group">
																				<label>报修人</label>
																				<div>
																					<input type="text" class="form-control"
																						name="eprCkIdName" id="eprCkIdName"
																						placeholder="报修人员" readonly="readonly" />
																				</div>
																			</div>


																			<div class="form-group" style="display: none">
																				<label>报修人ID</label>
																				<div>
																					<input type="text" class="form-control"
																						name="userId" id="userId" placeholder="报修人ID"
																						readonly="readonly" />
																				</div>
																			</div>


																		</div>
																		<!------------------维修派工-------------------------------------------------------------------------------------------->
																		<div id="leaderDispatch">
																			<div class="form-group">
																				<label>维修派工</label>
																				<div>
																					<hr
																						style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																				</div>
																			</div>

																			<div class="form-group">
																				<label>故障类别</label>
																				<div>
																					<input type="text" class="form-control"
																						name="epReKind" id="epReKind" placeholder="暂无内容"
																						readonly="readonly" />
																				</div>
																			</div>

																			<div class="form-group">
																				<label>维修责任人</label>
																				<div>
																					<input type="text" class="form-control"
																						name="epReId" id="epReId" placeholder="暂无内容"
																						readonly="readonly" />
																				</div>
																			</div>
																		</div>
																		<!------------------维修实施-------------------------------------------------------------------------------------------->
																		<div id="repair">
																			<div class="form-group">
																				<label>维修实施</label>
																				<div>
																					<hr
																						style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																				</div>
																			</div>
																			<div class="form-group">
																				<label>维修情况说明</label>
																				<div>
																					<input type="text" class="form-control"
																						name="epReDescribe" id="epReDescribe"
																						placeholder="暂无内容" readonly="readonly" />
																				</div>
																				<div class="form-group">
																					<label>维修完成时间</label>
																					<div>
																						<input type="text" class="form-control"
																							name="epReTime" id="epReTime" placeholder="暂无内容"
																							readonly="readonly" />
																					</div>
																				</div>
																			</div>
																			<!------------------维修确认-------------------------------------------------------------------------------------------->
																			<div id="confirm">
																				<div class="form-group">
																					<label>维修确认</label>
																					<div>
																						<hr style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																						<div>
																							<small class="text-muted ye">维修人员已确认修复故障</small>
																						</div>
																						<div>
																							<small class="text-muted ye">请进行设备检查确认</small>
																						</div>
																					</div>
																				</div>
																				<div class="form-group">
																					<label>设备状态</label>
																					<div>
																						<input type="text" class="form-control"
																							name="epReStatus" id="epReStatus"
																							placeholder="暂无内容" readonly="readonly" />
																					</div>
																				</div>
																	</form>

																	<div class="modal-footer">
																		<button type="button"
																			class="btn btn-secondary waves-effect"
																			data-dismiss="modal">取消</button>
																	</div>

																</div>
																<!-- /.modal-content -->
															</div>
															<!-- /.modal-dialog -->
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

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
	
	
	
	    <!-- jQuery  -->
		
        <!-- App js -->
        <script src="${proPath}/assets/js/app.js"></script>
                <!-- Dropzone js -->
        <script
		src="${proPath}/assets/plugins/dropzone/dist/dropzone.js"></script>
        <!-- Parsley js -->
        
        <script type="text/javascript"
		src="${proPath}/assets/plugins/parsleyjs/parsley.min.js"></script>
		
	    <script type="text/javascript" src="${proPath}/assets/js/swiper.min.js"></script> 
<!-- 	    <script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=TbrOXNWjGz1nrANZgpHd1NaM7b4pTYQy"></script> -->

<!-- <script type="text/javascript">
	// 百度地图API功能
	
	var address= '${equipment.address}';
	var map = new BMap.Map("allmap");          
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
	var local = new BMap.LocalSearch(map, {
		renderOptions:{map: map}
	});
	local.search(address);

	setTimeout(function(){
		map.setZoom(14);   
	}, 2000);  //2秒后放大到14级
	map.enableScrollWheelZoom(true);
</script> -->
	
	
<script type="text/javascript">
 
	function get_view_info(equipmentrepairId)
	{
		$("#act").val('update');
		$("#faceImgDiv").html("");
	    if(!epId)
	    {
	        alert('Error！');
	        return false;
	    }
	    // var form_data = new Array();
	    $.ajax(
	            {
	               /*  url: "${proPath}/status/editOne.action",
	                data:{"statusId":statusId}, */
	                url: "${proPath}/editEquipRepair.action",
	                data:{"equipmentrepairId":equipmentrepairId},
	                type: "post",
	                async:false,
	                beforeSend:function()
	                {
	                    // $("#tip").html("<span style='color:blue'>正在处理...</span>");
	                    return true;
	                },
	                success:function(data)
	                {
	                    if(data)
	                    {
	                        // 赋值
	                        $("#equipmentrepairId").val(data.equipmentRepairT.equipmentrepairId);
	                        $("#epId").val(data.equipmentRepairT.epId);
	                        $("#epHomeEquname").val(data.equipmentRepairT.epHomeEquname);
	                        $("#epModelNum").val(data.equipmentRepairT.epModelNum);
	                        $("#epLocation").val(data.equipmentRepairT.epLocation);
	                        $("#epTypeName").val(data.equipmentRepairT.epHomeEqutype);
	                        $("#eprCkTime").val(data.equipmentRepairT.eprCkTime);
	                        $("#eprCkDescribe").val(data.equipmentRepairT.eprCkDescribe);
	                        $("#eprCkIdName").val(data.equipmentRepairT.userIdName);
	                        $("#userId").val(data.equipmentRepairT.userId);                        
	                        $("#epReId").val(data.equipmentRepairT.epReIdName);
	                        $("#epReKind").val(data.epReKind);
	                        $("#epReDescribe").val(data.equipmentRepairT.epReDescribe);
	                        $("#epReTime").val(data.equipmentRepairT.epReTime);
	                        $("#epReStatus").val(data.epReStatus);
	                   	    var eprCkPhoto=data.equipmentRepairT.eprCkPhoto;      
	                   	    var arr= new Array();
	                   	    arr= eprCkPhoto.trim().split(',');
	                   	    $("#faceImgDiv").show();                                                                
	                   	    for(var k=0;k<arr.length-1;k++){
	                   		 arr[k]="${returnUrl}"+arr[k];
	               		    $("#faceImgDiv").append('<img alt="" class="list-img" src="'+arr[k]+'"  style="height: 80px;">');
	                   	    } 
	                       }
	                    else
	                    {
	                        $("#tip").html("<span style='color:red'>失败，请重试</span>");
	                      //  alert('操作失败');
	                    }
	                    
	   
	                },
	                error:function()
	                {
	                    alert('请求出错');
	                },
	                complete:function()
	                {
	                    // $('#tips').hide();
	                }
	            });

	    return false;
	}
	
	function getWzImg(){
		 var id="${equipment.epId }";
		 var arr=id.split("-")
		 var src="/pdf/wz/"+arr[1]+".png";
		 $('#wzImg').attr('src',src);
	}
	
	function getpdf(){
		 var epProvider="${equipment.epProvider }";
		 var src="/pdf/"+epProvider;
		 var pdf = document.getElementById("epProvider");
		 pdf.href=src;
	}

</script>

 <script>
 $(function(){
	 
	    getWzImg();
	    getpdf();
           /*调起大图 S*/
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
	    
	    
	  });
	  /*调起大图 E*/
	  

</script> 
    </body>
</html>