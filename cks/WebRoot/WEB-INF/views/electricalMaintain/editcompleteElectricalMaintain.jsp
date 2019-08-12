<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>已提交的临时保养单明细</title>
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

										<h4 class="mt-0 header-title">已完成临时保养单明细</h4>
										<p class="text-muted m-b-30 font-14">用于展示已提交的临时保养单明细.</p>

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
															id="epId" placeholder="设备编号" value="${mElectricalMaintainT.epId}"       
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
														id="epName" placeholder="设备名称"value="${mElectricalMaintainT.epName}" />
												</div>
											</div>

											<div class="form-group">
												<label>设备状态</label>
												<div>
													<input type="text" class="form-control" name="statusName" style="background-color: white" readonly="readonly"
														id="statusName" placeholder="设备状态" value="${fns:getDictLabel(mElectricalMaintainT.status,'status_type','')}"/>
												</div>
											</div>


											<div class="form-group">
												<label>规格型号</label>
												<div>
													<input type="text" class="form-control" name="epModelNum" style="background-color: white" readonly="readonly"
														id="epModelNum" placeholder="规格型号"value="${mElectricalMaintainT.epModelNum}" />
												</div>
											</div>
											<div class="form-group">
												<label>安装地点</label>
												<div>
													<input type="text" class="form-control" name="epLocation" style="background-color: white" readonly="readonly"
														id="epLocation" placeholder="安装地点"value="${mElectricalMaintainT.epLocation}"  />
												</div>
											</div>
											<div class="form-group">
												<label>设备类型</label>
												<div>
													<input type="text" class="form-control" name="epTypeName" style="background-color: white" readonly="readonly"
														id="epTypeName" placeholder="设备类型" value="${fns:getDictLabel(mElectricalMaintainT.epType,'epType','')}"/>
												</div>
											</div>
<!-- 											人员信息 -->
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
													<input type="text" class="form-control" name="userIdName" style="background-color: white" readonly="readonly"
														id="userIdName" placeholder="保养人员"value="${mElectricalMaintainT.userIdName}"  />											
												</div>
											</div>
											<div class="form-group">
												<label>派单主管</label>
												<div>
													<input type="text" class="form-control" placeholder="派单主管"
														value="${mElectricalMaintainT.leaderIdName}" name="leaderIdName" style="background-color: white" readonly="readonly"
														id="leaderIdName" />
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
														value="${mElectricalMaintainT.maintenanceDate}" name="maintenanceDate" id="maintenanceDate" />
												</div>
											</div>
											<div class="form-group">
												<label>距离规定的保养日期剩余天数</label><br /> 
												<div>
													<input type="text" class="form-control" placeholder="剩余天数"   style="background-color: white;color: red" readonly="readonly"
														value="${mElectricalMaintainT.distanceDays}" name="distanceDays" id="distanceDays" />
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
											
									<div id='maintenanceDetail'  >																		
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
												<textarea required class="form-control" rows="5"  name="maintainDescribe"  id="maintainDescribe"  style="background-color: white; " readonly="readonly" >${mElectricalMaintainT.maintainDescribe}</textarea>
												</div>
											</div>											
											<div class="form-group">
												<label>实际保养时间</label><br /> <small class="text-muted">1.
													自动获取当前时间</small>
												<div>
													<input type="text" class="form-control" placeholder="实际保养时间"   style="background-color: white" readonly="readonly"
														value="${mElectricalMaintainT.maintainTime}" name="maintainTime" id="maintainTime" />
												</div>
											</div>
											
											<div class="form-group">
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
		 $("#clear").attr("disabled","disabled");    
		 $("#clear").val('${mElectricalMaintainT.clear}');
		 $("#line").attr("disabled","disabled");    
		 $("#line").val('${mElectricalMaintainT.line}');
		 $("#oil").attr("disabled","disabled");    
		 $("#oil").val('${mElectricalMaintainT.oil}');
     	
    	
    	
    	
    	
    }   
	</script>



</body>
</html>