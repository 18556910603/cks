<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>历史巡检单明细</title>
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

										<h4 class="mt-0 header-title">历史巡检单明细</h4>
										<p class="text-muted m-b-30 font-14">用于展示已经提交的巡检单明细.</p>

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
															id="epId" placeholder="设备编号" value="${mElectricalCheckT.epId}"       
															style="background-color: white" readonly="readonly">

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
														id="epName" placeholder="设备名称"value="${mElectricalCheckT.epName}" />
												</div>
											</div>

											<div class="form-group">
												<label>设备状态</label>
												<div>
													<input type="text" class="form-control" name="statusName" style="background-color: white" readonly="readonly"
														id="statusName" placeholder="设备状态" value="${fns:getDictLabel(mElectricalCheckT.statusOldVal,'status_type','')}"/>
												</div>
											</div>

											<div class="form-group" style="display: none">
												<label>设备状态值</label>
												<div>
													<input type="text" class="form-control" name="statusOldVal" style="background-color: white" readonly="readonly"
														id="statusOldVal" placeholder="设备状态值" value="${mElectricalCheckT.statusOldVal}"/>
												</div>
											</div>

											<div class="form-group">
												<label>规格型号</label>
												<div>
													<input type="text" class="form-control" name="epModelNum" style="background-color: white" readonly="readonly"
														id="epModelNum" placeholder="规格型号"value="${mElectricalCheckT.epModelNum}" />
												</div>
											</div>
											<div class="form-group">
												<label>安装地点</label>
												<div>
													<input type="text" class="form-control" name="epLocation" style="background-color: white" readonly="readonly"
														id="epLocation" placeholder="安装地点"value="${mElectricalCheckT.epLocation}"  />
												</div>
											</div>
											<div class="form-group">
												<label>设备类型</label>
												<div>
													<input type="text" class="form-control" name="epTypeName" style="background-color: white" readonly="readonly"
														id="epTypeName" placeholder="设备类型" value="${fns:getDictLabel(mElectricalCheckT.epType,'epType','')}"/>
												</div>
											</div>
											<div class="form-group" style="display: none">
												<label>设备类型值</label>
												<div>
													<input type="text" class="form-control" name="epType" style="background-color: white" readonly="readonly"
														id="epType" placeholder="设备类型值" />
												</div>
											</div>
											<!------------------巡检人员 -------------------------------------------------------------------------------------------->
											<div class="form-group">
												<label>巡检信息</label>
												<div>
													<hr
														style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
												</div>
											</div>

											<div class="form-group">
												<label>巡检人员</label>
												<div>
													<input type="text" class="form-control" placeholder="巡检人员"
														  name="userName" style="background-color: white" readonly="readonly"
														id="userName"value="${mElectricalCheckT.userIdName}" />
												</div>
											</div>
											<div class="form-group" style="display: none">
												<label>巡检人员ID</label>
												<div>
													<input type="text" class="form-control"
														placeholder="巡检人员id"
														value="" name="userId"
														id="userId" />
												</div>
											</div>
											<div class="form-group">
												<label>巡检定位</label>

												<div id="checksLocDiv" >
													<div class="card-link"  style="color: blue;">清空地址</div>
													<input type="text" class="form-control" placeholder="" style="background-color: white" readonly="readonly"
														name="checksLoc" id="checksLoc" value="${mElectricalCheckT.checksLoc}"/><br />
												</div>


												<div align="center" class="form-control"
													  id="getLocation">
													<i class="dripicons-location"></i>获取当前位置
												</div>
											</div>

											<div class="form-group">
												<label>拍照签到</label>
												<div>
													<small class="text-muted ye">1.
														图片已设置仅允许拍照上传，防止上传历史照片作假</small>
												</div>
												<div align="center" class="form-control"
													  id="chooseImage">
												拍照签到
												</div>
												<div id="faceImgDiv"  style="display:none"  ></div> 
												
												
<!-- 												拍照签到的图片值 -->
												<input type="text" class="form-control" placeholder="拍照签到"style="display:none"
														value="" name="checksPhoto" id="checksPhoto" />									
											</div>

											<div class="form-group">
												<label>巡检时间</label><br /> <small class="text-muted">1.
													自动获取当前时间</small>
												<div>
													<input type="text" class="form-control" placeholder="巡检时间"   style="background-color: white" readonly="readonly"
														value="${mElectricalCheckT.checksTime}" name="checksTime" id="checksTime" />
												</div>
											</div>

											<div class="form-group">
												<label>巡检注意事项</label>
												<div>
													<small class="text-muted ye">1. 当巡检出现异常时,请第一时间填写<font
														color='blue' style="font-weight: bold;">报修单</font>,并说明故障内容
													</small><br /> <small class="text-muted ye">2.
														对重点设备要上传照片的,请大家按要求上传</small>
												</div>
											</div>
		
										

									</div>
								</div>
							</div>
							
                                <div class="col-lg-6">
                                    <div class="card m-b-30">
                                        <div class="card-body">

<!--                                             <h4 class="mt-0 header-title"></h4> -->
                                            <p class="text-muted m-b-30 font-14"></p>
											<!------------------检查分类 -------------------------------------------------------------------------------------------->
											<div class="form-group">
												<label>检查分类</label>
												<div>
													<hr
														style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
												</div>
											</div>
											<div class="form-group">
												<label>主变主体</label>
												<div>
													<select class="form-control" id="mainPart" name="mainPart" style="background-color: white">
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('mainPart')}"
															var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>油箱油位</label>
												<div>
													<select class="form-control" id="oilTank" name="oilTank" style="background-color: white">
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('oilTank')}"
															var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>运行声音</label>
												<div>
													<select class="form-control" id="voice" name="voice" style="background-color: white">
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('voice')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<!------------------检查汇总 -------------------------------------------------------------------------------------------->
											<div class="form-group">
												<label>检查汇总</label>
												<div>
													<hr
														style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
												</div>
											</div>
											<div class="form-group">
												<label>设备状态</label>
												<div>
													<select class="form-control" id="statusNewVal" onchange="gradeChange()"
														name="statusNewVal" style="background-color: white">
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('status_type')}"
															var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											
						<!-- 后面加入保养计划-->
											<div class="form-group">
												<label>保养计划</label>
												<div>
													<hr
														style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
												</div>
											</div>											
											<div class="form-group">
												<label>规定保养时间</label><br /> 
												<small class="text-muted">1. 即下一次保养的规定日期</small>
												<div>
													<input type="text" class="form-control" placeholder="保养时间"   style="background-color: white" readonly="readonly"
														value="${mElectricalCheckT.maintenanceDate}" name="maintenanceDate" id="maintenanceDate" />
												</div>
											</div>
											<div class="form-group">
												<label>距离规定的保养日期剩余天数</label><br /> 
												<div>
													<input type="text" class="form-control" placeholder="剩余天数"   style="background-color: white;color: red" readonly="readonly"
														value="${mElectricalCheckT.distanceDays}" name="distanceDays" id="distanceDays" />
												</div>
											</div>						
											<div class="form-group">
												<label>保养注意事项</label>
												<div>
													<small class="text-muted ye">1.距离规定的保养日期3天/超过规定保养日期,还未完成保养工作的设备,请抓紧时间对其做保养工作</small><br /> 
													<small class="text-muted ye">
													2. 请确保设备<font color='blue' style="font-weight: bold;">正常运行</font>后,填写保养内容,完成保养工作
													</small><br /> 
													<small class="text-muted ye">
													3. 检查出设备<font color='blue' style="font-weight: bold;">有异常的设备</font>,应该先走报修流程,确认设备正常巡行后进行保养工作
													</small><br /> 
												</div>
											</div>		
											
									<div id='maintenanceDetail' style="display: none" >																		
						<!-- 加入保养明细-->
											<div class="form-group">
												<label>保养明细</label>
												<div>
													<hr
														style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
												</div>
											</div>												
											<div class="form-group">
												<label>设备清洁、无油垢、灰尘</label>
												<div>
													<select class="form-control" id="clear" name="clear"  style="background-color: white; " >
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('maintainType')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>											
											<div class="form-group">
												<label>电气线路有无损坏</label>
												<div>
													<select class="form-control" id="line" name="line"  style="background-color: white; " >
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('maintainType')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>												
											<div class="form-group">
												<label>加机油</label>
												<div>
													<select class="form-control" id="oil" name="oil"  style="background-color: white; " >
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('maintainType')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>												
											<div class="form-group">
												<label>保养情况描述</label>
												<div>
												<textarea required class="form-control" rows="5"  name="maintainDescribe"  id="maintainDescribe"  style="background-color: white; "   readonly="readonly">${mElectricalCheckT.maintainDescribe}</textarea>
												</div>
											</div>											
											<div class="form-group">
												<label>实际保养时间</label><br /> <small class="text-muted">1.
													自动获取当前时间</small>
												<div>
													<input type="text" class="form-control" placeholder="实际保养时间"   style="background-color: white" readonly="readonly"
														value="${mElectricalCheckT.maintainTime}" name="maintainTime" id="maintainTime" />
												</div>
											</div>											
										</div>	


                                        </div>
                                    </div>
                                </div> <!-- end col -->								
							
							
							
							
							<!-- end col -->
						</div>
						<!-- end row -->
					</form>
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
    $(document).ready(function() {
        $('form').parsley();
        //初始化图片预览
         init();
        });
   //初始化图片预览
    function init(){
    	 var eprCkPhoto='${mElectricalCheckT.checksPhoto}';       
    	 var arr= new Array();
    	 arr= eprCkPhoto.trim().split(',');
    	 $("#faceImgDiv").show();                                                                       
    	 for(var k=0;k<arr.length-1;k++){
    		 arr[k]="${returnUrl}"+arr[k];
		  $("#faceImgDiv").append('<img alt="" src="'+arr[k]+'"   class="form-control">');        		 
    	 } 
		 $("#mainPart").attr("disabled","disabled");
		 $("#oilTank").attr("disabled","disabled");    
		 $("#voice").attr("disabled","disabled");
		 $("#statusNewVal").attr("disabled","disabled");    
		 $("#clear").attr("disabled","disabled");    
		 $("#line").attr("disabled","disabled");    
		 $("#oil").attr("disabled","disabled");    
		 
		 $("#mainPart").val('${mElectricalCheckT.mainPart}');
		 $("#oilTank").val('${mElectricalCheckT.oilTank}');
		 $("#voice").val('${mElectricalCheckT.voice}');
		 $("#statusNewVal").val('${mElectricalCheckT.statusNewVal}');
		 $("#clear").val('${mElectricalCheckT.clear}');
		 $("#line").val('${mElectricalCheckT.line}');
		 $("#oil").val('${mElectricalCheckT.oil}');	
		 var maintainId='${mElectricalCheckT.maintainId}';    
		 if(maintainId){
			 $("#maintenanceDetail").show();                
		 }
		 
		 
		 
		 
		 
		 
    }   
   
	</script>



</body>
</html>