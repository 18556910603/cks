<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>			
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>业主报修已完成任务详情页面</title>
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
<link rel="stylesheet" type="text/css"
	href="${proPath}/assets/css/swiper.min.css" />
<link type="text/css" rel="stylesheet"
	href="${proPath}/assets/css/app.css">

<link href="${proPath}/assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="${proPath}/assets/css/icons.css" rel="stylesheet"
	type="text/css">
<link href="${proPath}/assets/css/style.css" rel="stylesheet"
	type="text/css">
<link href="${proPath}/assets/css/fileinput.min.css" rel="stylesheet"
	type="text/css">

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
							<div class="col-lg-12">
								<div class="card m-b-30">
									<div class="card-body">
										<h4 class="mt-0 header-title">业主报修单</h4>
										
<%--               <form class="" action="${proPath}/electricalCheck/add.action" onsubmit="" id="form_data" method="post"> --%>
										<form class="" action="" onsubmit="" id="form_data" method="post">
<!------------------故障报修 -------------------------------------------------------------------------------------------->

											<div class="row">
												<div class="col-md-6">
													<div class="p-20">

														<div>
														
															<div class="form-group  row">
																<label for="example-text-input"
																	class="col-sm-3 col-form-label">报修单编号</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control"
																		name="equipmentrepairId" id="equipmentrepairId"
																		placeholder="报修单编号"
																		value="${mHsequipmentRepairO.equipmentrepairId}"
																		readonly="readonly" style="background-color: white" />
																</div>
															</div>

															<div class="form-group  row">
																<label for="example-text-input"
																	class="col-sm-3 col-form-label">设备名称</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control" name="epName"
																		id="epName" placeholder="设备名称"
																		value="${mHsequipmentRepairO.epHomeEquname}"
																		readonly="readonly" style="background-color: white" />
																</div>
															</div>
															<div class="form-group  row">
																<label for="example-text-input"
																	class="col-sm-3 col-form-label">设备类型</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control"
																		name="epTypeName" id="epTypeName" placeholder="设备类型"
																		value="${mHsequipmentRepairO.epHomeEqutype}"
																		readonly="readonly" style="background-color: white" />
																</div>
															</div>

															<div class="form-group  row">
																<label for="example-text-input"
																	class="col-sm-3 col-form-label">设备地址</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control"
																		name="epLocation" id="epLocation" placeholder="安装地点"
																		value="${mHsequipmentRepairO.epLocation}"
																		readonly="readonly" style="background-color: white" />
																</div>
															</div>

															<div class="form-group  row">
																<label for="example-text-input"
																	class="col-sm-3 col-form-label" >报修时间</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control"
																		name="eprCkTime" id="eprCkTime" placeholder="报修时间"
																		value="${mHsequipmentRepairO.eprCkTime}"
																		readonly="readonly" style="background-color: white" />
																</div>
															</div>
															
															<div class="form-group row">
															<label for="example-text-input" class="col-sm-3 col-form-label">故障情况拍照</label> 
															<input type="text" class="form-control"value="${mHsequipmentRepairO.eprCkPhoto}" placeholder="拍照签到" name="eprCkPhoto" id="eprCkPhoto" style="display: none" />
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
														                                                    
														<div class="form-group  row">
															<label for="example-text-input"
																class="col-sm-3 col-form-label">故障情况描述</label>
															<div class="col-sm-9">
																<textarea required class="form-control" rows="3"
																	name="eprCkDescribe" id="eprCkDescribe"
																	style="background-color: white" readonly="readonly">${mHsequipmentRepairO.eprCkDescribe}</textarea>
															</div>
														</div>
														  

														</div>
													</div>
												</div>
											<div class="col-md-6">
                                                  <div class="p-20">


														<div class="form-group  row">
															<label for="example-text-input"
																class="col-sm-3 col-form-label">故障等级</label>
															<div class="col-sm-9">
																<input type="text" class="form-control"
																	name="eprCkLevel" id="eprCkLevel"
																	value="${mHsequipmentRepairO.eprCkLevel}"
																	readonly="readonly" style="background-color: white" />
															</div>
														</div>
														<div class="form-group  row">
															<label for="example-text-input"
																class="col-sm-3 col-form-label">业主姓名</label>
															<div class="col-sm-9">
																<input type="text" class="form-control"
																	name="userIdName" id="userIdName"
																	value="${mHsequipmentRepairO.userIdName}"
																	readonly="readonly" style="background-color: white" />
															</div>
														</div>

														<div class="form-group  row">
														<label for="example-text-input"
															class="col-sm-3 col-form-label">业主电话</label>
														<div class="col-sm-9">
															<input type="text" class="form-control" name="mobile"
																id="mobile" value="${mHsequipmentRepairO.mobile}"
																readonly="readonly" style="background-color: white" />
														</div>
													</div>

													<!------------------维修派工-------------------------------------------------------------------------------------------->
													<div style="display: none" id="leaderDispatch">
														<div class="form-group">
															<label for="example-text-input"
																class="col-sm-3 col-form-label">维修派工</label>
															<div>
																<hr
																	style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
															</div>
														</div>

														<div class="form-group  row">
															<label for="example-text-input"
																class="col-sm-3 col-form-label">维修责任人</label>
															<div class="col-sm-9">
																<select class="form-control" id="epReId" name="epReId">
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
														<div class="form-group">
															<label for="example-text-input"
																class="col-sm-3 col-form-label">维修实施</label>
															<div>
																<hr
																	style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
															</div>
														</div>
														<div class="form-group  row">
															<label for="example-text-input"
																class="col-sm-3 col-form-label">维修情况说明</label>
															<div class="col-sm-9">
																<textarea required class="form-control" rows="3"
																	name="epReDescribe" id="epReDescribe"
																	readonly="readonly" style="background-color: white">${mHsequipmentRepairO.epReDescribe}</textarea>
																<%-- 													<input  type="text" class="form-control"   name="epReDescribe"  id="epReDescribe" value="${mHsequipmentRepairT.epReDescribe}" placeholder="维修情况说明" /> --%>
															</div>
														</div>
														<div class="form-group  row">
															<label for="example-text-input"
																class="col-sm-3 col-form-label">维修完成时间</label>
															<div class="col-sm-9">
																<input type="text" class="form-control" name="epReTime"
																	id="epReTime" readonly="readonly"
																	value="${mHsequipmentRepairO.epReTime}"
																	style="background-color: white" placeholder="维修完成时间" />
															</div>
														</div>
													</div>
													<!------------------维修确认-------------------------------------------------------------------------------------------->
													<div style="display: none" id="confirm">
														<div class="form-group">
															<label for="example-text-input"
																class="col-sm-3 col-form-label">维修确认</label>
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
														</div>
														<div class="form-group  row">
															<label for="example-text-input"
																class="col-sm-3 col-form-label">设备状态</label>
															<div class="col-sm-9">
																<select class="form-control" id="epReStatus"
																	name="epReStatus"
																	value="${mHsequipmentRepairO.epReStatus}"
																	style="background-color: white">
																	<option value="">----请选择----</option>
																	<c:forEach items="${fns:getDictList('status_type')}"
																		var="dict">
																		<option value="${dict.value}">${dict.label}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
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
        <!-- App js -->
        <script src="${proPath}/assets/js/app.js"></script>
                <!-- Dropzone js -->
        <script src="${proPath}/assets/plugins/dropzone/dist/dropzone.js"></script>
        <script src="${proPath}/assets/js/fileinput.min.js"></script>
        <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
        <script type="text/javascript" src="${proPath}/assets/js/swiper.min.js"></script> 
        <script type="text/javascript">
        $(document).ready(function() {
            $('form').parsley();
            //初始化图片预览
             init();
           //根据不同的流程节点显示
          	 initDiv();
            });
       //初始化图片预览
        function init(){
        	 var eprCkPhoto='${mHsequipmentRepairO.eprCkPhoto}';       
        	 var arr= new Array();
        	 arr= eprCkPhoto.trim().split(',');
        	 $("#faceImgDiv").show();                                                                       
        	 for(var k=0;k<arr.length;k++){
        		 arr[k]="${returnUrl}"+arr[k];
              	 $("#faceImgDiv").append('<img alt=""  class="list-img" src="'+arr[k]+'"  style="width:80px;height: 80px;">'); 
        	 } 
        	 
        	 
        }   
        function initDiv(){
        	//获取下一个节点
        	 var epAcNowid='${mHsequipmentRepairO.epAcNowid}'; 
        	 
        	 if(epAcNowid=='1'){
        		//1:报修申请 
//         		  $("#eprCkDescribe").removeAttr("readonly");//去除input元素的readonly属性
        		 
        	 }else if(epAcNowid=='2'){
        		//2.主管派单
        		 $("#leaderDispatch").show(); 
        		 $("#epReId").val('${mHsequipmentRepairO.epReId}');
        		 $("#epReId").attr("readonly","readonly");
        	 }else if(epAcNowid=='3'){
        		 //3.维修中（维修确认）
        		 $("#leaderDispatch").show();  
        		 $("#epReId").val('${mHsequipmentRepairO.epReId}');
        		 $("#epReId").attr("readonly","readonly");
        		 $("#repair").show(); 
        	 }else if(epAcNowid=='4'){
        		 //4.发起人确认
        		 $("#leaderDispatch").show();  
        		 $("#epReId").val('${mHsequipmentRepairO.epReId}');
        		 $("#epReId").attr("readonly","readonly");
        		 $("#repair").show();          		 
        		 $("#confirm").show();
        		 $("#epReStatus").attr("readonly","readonly");
        	 }else if(epAcNowid=='5'){
        		 $("#leaderDispatch").show(); 
        		 $("#repair").show();          		 
        		 $("#confirm").show();  
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

    

    </body>
</html>