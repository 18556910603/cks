<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>			
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>巡检-设备报修单页面</title>
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
        
        <link href="${proPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="${proPath}/assets/css/icons.css" rel="stylesheet" type="text/css">
        <link href="${proPath}/assets/css/style.css" rel="stylesheet" type="text/css">
        
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
					<form class="" action="" onsubmit="" id="form_data" method="post">
						<div class="row">
							<div class="col-lg-6">
								<div class="card m-b-30">
									<div class="card-body">
										<h4 class="mt-0 header-title">设备报修单</h4>
										<p class="text-muted m-b-30 font-14">已经处理的设备报修工单</p>
<%-- 										<form class="" action="${proPath}/electricalCheck/add.action" onsubmit="" id="form_data" method="post"> --%>
										
<!------------------故障报修 -------------------------------------------------------------------------------------------->

										<div>
                                           <div class="form-group" >
                                               <label>故障报修</label>
                                               <div>
												<hr style="height:1px;border:none;border-top:1px solid #E9ECEF;" />
                                               </div>
                                           </div>	

                                           <div class="form-group" >
                                               <label>设备编号</label>
                                                <div><small class="text-muted xy">扫码获取设备编号</small></div>
                                               <div>
                                                   <div class="input-group"  >
<!--                                                        <input type="text" class="form-control"   placeholder="设备编号"  readonly> -->
<!-- 													   <input type="text" class="form-control"  name="epid"  id="epid"  placeholder="设备编号" value="JDY20180928-044" readonly="readonly" style="background-color: white" onchange="showPic();" > -->
							  							   <input type="text" class="form-control"  name="epId"  id="epId"  placeholder="设备编号" value="${mHsequipmentRepairT.epId}" readonly="readonly" style="background-color: white" onchange="showEquipment();" >
                                                       <div class="input-group-append bg-custom b-0"><span class="input-group-text"><i class="mdi mdi-qrcode-scan" id='scanQRCode'></i></span></div>
                                                   </div>
                                               </div>
                                           </div>											

											<div class="form-group">
												<label>设备名称</label>
												<div>
													<input type="text" class="form-control" name="epName"  id="epName"   placeholder="设备名称" value="${mHsequipmentRepairT.epHomeEquname}"  readonly="readonly" style="background-color: white"/>
												</div>
											</div>

											<div class="form-group">
												<label>规格型号</label>
												<div>
													<input type="text" class="form-control" name="epModelNum"  id="epModelNum"   placeholder="规格型号"  value="${mHsequipmentRepairT.epModelNum}" readonly="readonly" style="background-color: white"/>
												</div>
											</div>
											<div class="form-group">
												<label>安装地点</label>
												<div>
													<input  type="text" class="form-control"   name="epLocation"  id="epLocation" placeholder="安装地点" value="${mHsequipmentRepairT.epLocation}" readonly="readonly" style="background-color: white"/>
												</div>
											</div>
											<div class="form-group">
												<label>设备类型</label>
												<div>
													<input  type="text" class="form-control"  name="epTypeName"  id="epTypeName"  placeholder="设备类型" value="${mHsequipmentRepairT.epHomeEqutype}" readonly="readonly" style="background-color: white"/>
												</div>
											</div>
											<div class="form-group">
												<label>报修时间</label>
												<div>
													<input  type="text" class="form-control"   name="eprCkTime"  id="eprCkTime" placeholder="报修时间" value="${mHsequipmentRepairT.eprCkTime}" readonly="readonly" style="background-color: white"/>
												</div>
											</div>
											<div class="form-group">
												<label>故障情况拍照</label>
												 <div><small class="text-muted ye">1. 启用了「仅允许拍照上传」功能，该功能只支持微信服务号</small></div>
												<div align="center" class="form-control"
													  id="chooseImage">
												故障情况拍照
												</div>
												<div id="faceImgDiv"  style="display:none"></div> 
												
												
<!-- 												拍照签到的图片值 -->
												<input type="text" class="form-control" placeholder="拍照签到"
														value="${mHsequipmentRepairT.eprCkPhoto}" name="eprCkPhoto" id="eprCkPhoto" style="display:none"/>													
											</div>
											<div class="form-group">
												<label>故障情况描述</label>
												<div>
													<textarea required class="form-control" rows="5"  name="eprCkDescribe"  id="eprCkDescribe" style="background-color: white" readonly="readonly" >${mHsequipmentRepairT.eprCkDescribe}</textarea>
<%-- 													<input  type="text" class="form-control"   name="eprCkDescribe"  id="eprCkDescribe" placeholder="故障情况描述"style="background-color: white" value="${mHsequipmentRepairT.eprCkDescribe}" /> --%>
												</div>
											</div>
											<div class="form-group">
												<label>报修人</label>
												<div>
													<input  type="text" class="form-control"   name="eprCkIdName"  id="eprCkIdName"   value="${mHsequipmentRepairT.userIdName}"     placeholder="报修人"  readonly="readonly" style="background-color: white"/>
												</div>
											</div>
											
											
											<div class="form-group" style="display:none	">
												<label>报修人ID</label>
												<div>
													<input  type="text" class="form-control"   name="userId"  id="userId" value="${mHsequipmentRepairT.userId}"  placeholder="报修人ID" />
												</div>
											</div>											
											
											
										</div>
<!------------------维修派工-------------------------------------------------------------------------------------------->
									 <div style="display:none" id="leaderDispatch" >
                                           <div class="form-group" >
                                               <label>维修派工</label>
                                               <div>
												<hr style="height:1px;border:none;border-top:1px solid #E9ECEF;" />
                                               </div>
                                           </div>
                                           
											<div class="form-group">
												<label>故障类别</label>
												<div>
													<select class="form-control" id="epReKind" name="epReKind" style="background-color: white" readonly="readonly">
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('epReKind')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>                                           
											
											<div class="form-group">
												<label>维修责任人</label>
												<div>
													<select class="form-control" id="epReId" name="epReId" style="background-color: white" readonly="readonly">
														<option value="">----请选择----</option>
														<c:forEach items="${repairUserList}" var="dict">
															<option value="${dict.userId}">${dict.loginName}</option>
														</c:forEach>
													</select>												
												</div>
											</div>	
									</div>	
								</div>
									</div>
								</div>
								
							<div class="col-lg-6" id="repairALL" style="display:none">
								<div class="card m-b-30">
									<div class="card-body">
<!-- 										<h4 class="mt-0 header-title">设备报修单</h4> -->
										<p class="text-muted m-b-30 font-14"></p>

<!------------------维修实施-------------------------------------------------------------------------------------------->									
									<div style="display:none"id="repair">										
                                           <div class="form-group" >
                                               <label>维修实施</label>
                                               <div>
												<hr style="height:1px;border:none;border-top:1px solid #E9ECEF;" />
                                               </div>
                                           </div>											
											<div class="form-group">
												<label>维修情况说明</label>
												<div>
													<textarea required class="form-control" rows="5"  name="epReDescribe"  id="epReDescribe" readonly="readonly"style="background-color: white" >${mHsequipmentRepairT.epReDescribe}</textarea>
<%-- 													<input  type="text" class="form-control"   name="epReDescribe"  id="epReDescribe" value="${mHsequipmentRepairT.epReDescribe}" placeholder="维修情况说明" /> --%>
												</div>
											</div>
											<div class="form-group">
												<label>维修完成时间</label>
												<div>
													<input  type="text" class="form-control"   name="epReTime"  id="epReTime" readonly="readonly"value="${mHsequipmentRepairT.epReTime}"style="background-color: white"  placeholder="维修完成时间" />
												</div>
											</div>
									</div>
<!------------------维修确认-------------------------------------------------------------------------------------------->
									<div style="display:none"  id="confirm">
                                           <div class="form-group" >
                                               <label>维修确认</label>
                                               <div>
												<hr style="height:1px;border:none;border-top:1px solid #E9ECEF;" />
												 <div><small class="text-muted ye">维修人员已确认修复故障</small></div>
												 <div><small class="text-muted ye">请进行设备检查确认</small></div>
                                               </div>
                                           </div>
											<div class="form-group">
												<label>设备状态</label>
												<div>
													<select class="form-control" id="epReStatus" name="epReStatus"  disabled="disabled" style="background-color: white">
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('status_type')}" var="dict">
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
														value="${mHsequipmentRepairT.maintenanceDate}" name="maintenanceDate" id="maintenanceDate" />
												</div>
											</div>
											<div class="form-group">
												<label>距离规定的保养日期剩余天数</label><br /> 
												<div>
													<input type="text" class="form-control" placeholder="剩余天数"   style="background-color: white;color: red" readonly="readonly"
														value="${mHsequipmentRepairT.distanceDays}" name="distanceDays" id="distanceDays" />
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
													<select class="form-control" id="clear" name="clear" disabled="disabled" style="background-color: white">
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
													<select class="form-control" id="line" name="line" disabled="disabled" style="background-color: white">
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
													<select class="form-control" id="oil" name="oil" disabled="disabled" style="background-color: white">
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
												<textarea required class="form-control" rows="5"  name="maintainDescribe"  id="maintainDescribe"  style="background-color: white;" readonly="readonly">${mHsequipmentRepairT.maintainDescribe}</textarea>
												</div>
											</div>											
											<div class="form-group">
												<label>实际保养时间</label><br /> <small class="text-muted">1.
													自动获取当前时间</small>
												<div>
													<input type="text" class="form-control" placeholder="实际保养时间"   style="background-color: white" readonly="readonly"
														value="${mHsequipmentRepairT.maintainTime}" name="maintainTime" id="maintainTime" />
												</div>
											</div>											
										</div>														
									</div>
								</div>
											<div class="form-group"style="display:none">
												<div>
												
													<button type="button" id="rpAdd" onclick="rpAdd()"
														class="btn btn-primary waves-effect waves-light" style="display:none">
														维修记录新增</button>												
												
													<button type="button" id="submit" onclick="save()"
														class="btn btn-primary waves-effect waves-light">
														提交</button>
														
													<span style="color: red;"  id="tip"></span>

												</div>
											</div>


										

									</div>
								</div>								
								
								
							</div>
						</form>	
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
        	 var eprCkPhoto='${mHsequipmentRepairT.eprCkPhoto}';       
        	 var arr= new Array();
        	 arr= eprCkPhoto.trim().split(',');
        	 $("#faceImgDiv").show();                                                                       
        	 for(var k=0;k<arr.length-1;k++){
        		 arr[k]="${returnUrl}"+arr[k];
    		  $("#faceImgDiv").append('<img alt="" src="'+arr[k]+'"   class="form-control">');        		 
        	 } 
    		 $("#epReKind").attr("disabled","disabled");
    		 $("#epReId").attr("disabled","disabled");        	
        }   
        function initDiv(){
        	//获取下一个节点
        	 var epAcNowid='${mHsequipmentRepairT.epAcNowid}'; 
        	 
        	 if(epAcNowid=='1'){
        		//1:报修申请 
//         		  $("#eprCkDescribe").removeAttr("readonly");//去除input元素的readonly属性
        		 
        	 }else if(epAcNowid=='2'){
        		//2.主管派单
        		 $("#leaderDispatch").show();     
        		 $("#epReKind").val('${mHsequipmentRepairT.epReKind}');
        		 $("#epReId").val('${mHsequipmentRepairT.epReId}');
        	 }else if(epAcNowid=='3'){
        		 //3.维修中（维修确认）
        		 $("#leaderDispatch").show();     
        		 $("#epReKind").val('${mHsequipmentRepairT.epReKind}');
        		 $("#epReId").val('${mHsequipmentRepairT.epReId}');
        		 $("#repairALL").show(); 
        		 $("#repair").show(); 
        	 }else if(epAcNowid=='4'){
        		 //4.发起人确认
        		 $("#leaderDispatch").show();     
        		 $("#epReKind").val('${mHsequipmentRepairT.epReKind}');
        		 $("#epReId").val('${mHsequipmentRepairT.epReId}');
        		 $("#repairALL").show(); 
        		 $("#repair").show();          		 
        		 $("#confirm").show();      
        		 $("#epReStatus").val('${mHsequipmentRepairT.epReStatus}');
        		 var maintainId='${mHsequipmentRepairT.maintainId}'; 
        		 if(maintainId){
        			 $("#clear").val('${mHsequipmentRepairT.clear}');
        			 $("#line").val('${mHsequipmentRepairT.line}');
        			 $("#oil").val('${mHsequipmentRepairT.oil}');
        			 $("#maintenanceDetail").show(); 
        		 }
        		 
        		 
        		 
        		 
        	 }else if(epAcNowid=='5'){
        		 
        	 }
        	
        }         
        //查询设备基础信息
        function showEquipment(epId)
        {
        	var epId = $.trim($('#epId').val());
            if(!epId)
            {
                alert('Error！');
                return false;
            }
            $.ajax(
                    {
                        url: "${proPath}/electricalCheck/equipOne.action",
                        data:{"epId":epId},
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
                         	   $("#epName").val(data.epName);
                         	   $("#epModelNum").val(data.epModelNum);
                         	   $("#epLocation").val(data.epLocation);
                         	   $("#epTypeName").val(data.epTypeName);
                         	   $("#submit").removeAttr("disabled");
                         	   $("#tip").html("<span style='color:red'></span>");
                            }
                            else
                            {
                                $("#tip").html("<span style='color:red'>当前设备不存在，请重试</span>");
                                $("#submit").attr("disabled","disabled");
                                
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
        
        function save()
        {
     	       var form_data = $('#form_data').serialize();
     	       form_data=form_data+"&equipmentrepairId="+'${mHsequipmentRepairT.equipmentrepairId}';
     	    $.ajax(
     	            {
     	                url: "${proPath}/equipmentRepair/submitToDo.action",
     					data:form_data,
     	                type: "post",
     	                async:false,
     	                beforeSend:function()
     	                {
     	                    $("#tip").html("<span style='color:blue'>正在处理...</span>");
     	                    return true;
     	                },
     	                success:function(data)
     	                {
     	                    if(data.success==true)
     	                    {
     	                      $("#tip").html("<span style='color:red'></span>");
     	                        alert('成功');
     	                        window.location.href="${proPath}/electricalCheck/tips.action";
     	                    }
     	                    else
     	                    {
     	                        $("#tip").html("<span style='color:red'>失败，请重试</span>");
     	                        alert('操作失败');
     	                    }
     	                },
     	                error:function()
     	                {
     	                    alert('请求出错');
     	                },
     	                complete:function()
     	                {
//      	                	location.reload(true);
     	                }
     	            });
        }      
                
                
                
                
                
                
                
                
                
         

        </script>

    

    </body>
</html>