<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>业主报修详情表</title>
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
<link href="${proPath}/assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="${proPath}/assets/css/icons.css" rel="stylesheet"
	type="text/css">
<link href="${proPath}/assets/css/style.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="${proPath}/assets/css/swiper.min.css" />
<link type="text/css" rel="stylesheet"
	href="${proPath}/assets/css/app.css">


</head>
    <body class="fixed-left">

	<!-- Loader -->
	<div id="preloader">
		<div id="status">
			<div class="spinner"></div>		</div>
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
							<div class="col-lg-12">
								<div class="card m-b-30">
									<div class="card-body">

										<h4  class="mt-0 header-title">业主报修详情表</h4>

										<form method="post" action="" class="form-horizontal"
											role="form" id="form_data" style="margin: 20px;">

											<div class="row">
												<div class="col-md-6">
													<div class="p-20">


														<div class="form-group" id="epIdDiv">
															<label>报修时间:</label>&nbsp ${mEquipmentRepairO.eprCkTime}
														</div>

														<div class="form-group" id="epIdDiv">
															<label>设备类型:</label>&nbsp
															${mEquipmentRepairO.epHomeEqutype }
														</div>

														<div class="form-group">
															<label>设备名称:</label>&nbsp
															${mEquipmentRepairO.epHomeEquname }
														</div>
														
														<div class="form-group ">
														<label >故障情况拍照</label> 
															<input type="text" class="form-control"value="${mEquipmentRepairO.eprCkPhoto}" placeholder="拍照签到" name="eprCkPhoto" id="eprCkPhoto" style="display: none" />
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
														  </div>
														

														<div class="form-group">
															<label>故障描述:</label>&nbsp
															${mEquipmentRepairO.eprCkDescribe }
														</div>


														<div class="form-group">
															<label>故障等级:</label>&nbsp
															${mEquipmentRepairO.eprCkLevel}
														</div>
														<div class="form-group">
															<label>业主地址:</label>&nbsp ${mEquipmentRepairO.epLocation
															}
														</div>

														<div class="form-group">
															<label>业主姓名&nbsp/&nbsp电话:</label>&nbsp
															${mEquipmentRepairO.userIdName }:&nbsp
															${mEquipmentRepairO.mobile }
														</div>
														
													</div>
												</div>
												<div class="col-md-6">
													<div class="p-20">
														<!------------------维修派工-------------------------------------------------------------------------------------------->
														<div id="leaderDispatch">
<!-- 															<div class="form-group">
																<label for="example-text-input"
																	class="col-sm-3 col-form-label">维修派工</label>
																<div class="col-sm-9">
																	<hr
																		style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																</div>
															</div> -->
															<div class="form-group  row">
																<label for="example-text-input"
																	class="col-sm-3 col-form-label">维修责任人</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control" name="epReId"
																		id="epReId" value="${mEquipmentRepairO.epReIdName}"
																		placeholder="暂无内容" readonly="readonly"
																		style="background-color: white" />
																</div>
															</div>
															<div class="form-group  row">
																<label for="example-text-input"
																	class="col-sm-3 col-form-label">维修责任人电话</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control" name="reMobile"
																		id="reMobile" value="${mEquipmentRepairO.reMobile}"
																		placeholder="暂无内容" readonly="readonly"
																		style="background-color: white" />
																</div>
															</div>
														</div>
														<!------------------维修实施-------------------------------------------------------------------------------------------->
														<div id="repair">
<!-- 															<div class="form-group ">
																<label for="example-text-input"
																	class="col-sm-3 col-form-label">维修实施</label>
																<div class="col-sm-9">
																	<hr
																		style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																</div>
															</div> -->
															<div class="form-group">
																<label>维修情况说明</label>
																<div >
																	<textarea required class="form-control" rows="3"
																		name="epReDescribe" id="epReDescribe"
																		style="background-color: white" readonly="readonly"
																		placeholder="暂无内容">${mEquipmentRepairO.epReDescribe}</textarea>
																</div>
															</div>
															<div class="form-group">
																<label>维修完成时间</label>
																<div>
																	<input type="text" class="form-control" name="epReTime"
																		id="epReTime" value="${mEquipmentRepairO.epReTime}"
																		readonly="readonly" placeholder="暂无内容" />
																</div>
															</div>
														</div>
														<!------------------维修确认-------------------------------------------------------------------------------------------->
														<div id="confirm">
<!-- 															<div class="form-group">
																<label>维修确认</label>
																<div>
																	<hr
																		style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
 																	<div>
																		<small class="text-muted ye">维修人员已确认修复故障</small>
																	</div>
																	<div>
																		<small class="text-muted ye">请进行设备检查确认</small>
																	</div>
																</div>
															</div> -->
															<div class="form-group">
																<label>设备状态</label>
																<div>
																	<input type="text" class="form-control"
																		name="epReStatus" id="epReStatus"
																		value="${fns:getDictLabel(mEquipmentRepairO.epReStatus,'status_type',mEquipmentRepairO.epReStatus)}"
																		readonly="readonly" placeholder="暂无内容" />
																</div>
															</div>
														</div>

														<div class="form-group">
															<label>维修单号:</label>&nbsp
															${mEquipmentRepairO.equipmentrepairId }
														</div>

														<div class="form-group">
															<label>流转状态:</label>&nbsp
															${fns:getDictLabel(mEquipmentRepairO.epAcStatus,'ep_ac_status',mEquipmentRepairO.epAcStatus)}
														</div>
													</div>
												</div>
											</div>
										</form>

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
        <script type="text/javascript" src="${proPath}/assets/js/swiper.min.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {
            $('form').parsley();
            //初始化图片预览
             init();

            });
       //初始化图片预览
        function init(){
        	 var eprCkPhoto='${mEquipmentRepairO.eprCkPhoto}';       
        	 var arr= new Array();
        	 arr= eprCkPhoto.trim().split(',');
        	 $("#faceImgDiv").show();                                                                       
        	 for(var k=0;k<arr.length;k++){
        		 arr[k]="${returnUrl}"+arr[k];
              	 $("#faceImgDiv").append('<img alt="" class="list-img" src="'+arr[k]+'"  style="width:80px;height: 80px;">'); 
        	 }
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

        </script>


        <!-- App js -->
        <script src="${proPath}/assets/js/app.js"></script>

    </body>
</html>