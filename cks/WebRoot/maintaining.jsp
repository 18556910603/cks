<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>设备报修</title>
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
								<div class="card m-b-30">
									<div class="card-body">
									  <h4 class="mt-0 header-title">设备报修单</h4>
										<p class="text-muted m-b-30 font-14">用于提交设备巡检表单</p>
										<form class="" action="${proPath}/electricalCheck/add.action" onsubmit="" id="form_data" method="post">
                                           <div class="form-group row" >
                                               <label for="example-text-input" class="col-sm-3 col-form-label">设备编号</label>
                                                <div class="col-sm-9">                                              
                                                   <div class="input-group"  >   
													   <input type="text" class="form-control"  name="epId"  id="epId"  value="${equipment.epId }  " placeholder="设备编号" >
                                                       <div class="input-group-append bg-custom b-0"><span class="input-group-text"><i class="mdi mdi-qrcode-scan"></i></span></div>
                                                   </div>
                                               </div>
                                           </div>			   
                                                <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">设备名称</label>
                                                    <div class="col-sm-9">
                                                    <input class="form-control" type="text" value="${equipment.epName }" name="epName" id="epName" readonly="readonly">
                                                   </div>                                                                                                    
                                                </div>	

                                                <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">设备型号</label>
                                                    <div class="col-sm-9">
                                                    <input class="form-control" type="text" value="${equipment.epModelNum }" name="epModelNum" id="epModelNum"readonly="readonly">
                                                   </div>                                                                                                    
                                                </div>	
 
                                                 <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">设备区域</label>
                                                    <div class="col-sm-9">
                                                    <input class="form-control" type="text" value="${equipment.epLoc }" name="epLoc" id="epLoc"readonly="readonly">
                                                   </div>                                                                                                    
                                                </div>	
                                                                                               
                                                <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">安装地点</label>
                                                    <div class="col-sm-9">
                                                    <input class="form-control" type="text" value="${equipment.epLocation }" name="epLocation" id="epLocation"readonly="readonly">
                                                   </div>                                                                                                    
                                                </div>	
                                                 
                                                 <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">设备类型</label>
                                                    <div class="col-sm-9">
                                                    <input class="form-control" type="text" value="${fns:getDictLabel(equipment.epType,'epType',equipment.epType)}" name="epType" id="epType"readonly="readonly">
                                                   </div>                                                                                                    
                                                </div>	
                                                
                                                
                                                 <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">生产商</label>
                                                    <div class="col-sm-9">
                                                    <input class="form-control" type="text" value="${equipment.epProducer }" name="epProducer" id="epProducer"readonly="readonly">
                                                   </div>                                                                                                    
                                                </div>	
                                                 <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">设备负责人</label>
                                                    <div class="col-sm-9">
                                                    <input class="form-control" type="text" value=" ${epUser.user.userName } " name="userId" id="userId" readonly="readonly">
                                                   </div>                                                                                                    
                                                </div>	
                                                <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">联系人电话</label>
                                                    <div class="col-sm-9">
                                                    <input class="form-control" type="text" value=" ${epUser.user.mobile } " name="mobile" id="mobile"readonly="readonly">
                                                   </div>                                                                                                    
                                                </div>	
  											 <div class="form-group row">
												<label for="example-text-input" class="col-sm-3 col-form-label">报修时间</label>											
												<div class="col-sm-9">
				                                  <%
                                                 Date d=new Date();
                                                 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                                 String now=sdf.format(d);
                                                 %>
												<%-- 	<input  type="text" class="form-control"   value="<%=now %>" name="maintainTime"  id="maintainTime"  readonly="readonly"/> --%>
													<input  type="datetime-local" class="form-control"  name="maintainTime"  id="maintainTime" />
												</div>											
											</div>
											<div class="form-group row">
												<label  for="example-text-input" class="col-sm-3 col-form-label">报修人</label>												
												<div class="col-sm-9">
													<input  type="text"  class="form-control"   placeholder="报修人员" value="${SessionContainer.user.userName}" name="user"  id="user"/>
												</div>
											</div>
											
											<div class="form-group">
												<label >故障情况拍照</label>
												<div>
													<input  type="file" id='image' accept="image/*" capture='camera' class="form-control"  name="maintainInfo"  id="maintainInfo" multiple/>
												</div>
											</div>	
											<div class="form-group">
												<label>故障情况描述</label>
												<div>
													<input  type="text" class="form-control"   placeholder="暂无内容" name="maintainInfo"  id="maintainInfo"/>
												</div>
											</div>		
											<div class="form-group">
												<label>故障等级</label>
												<div>
													
												    <select class="form-control" id="level" name="level">
														<option value="">--请选择--</option>                                             
														<c:forEach items="${fns:getDictList('epr_ck_level')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												
												</div>
											</div>
											
											<div class="form-group">
												<label>故障类别</label>
												<div>
												    <select class="form-control" id="epReKind" name="epReKind">
														<option value="">--请选择--</option>                                             
														<c:forEach items="${fns:getDictList('epReKind')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>												
												</div>
											</div>
											
											<div class="form-group">
												<label>维修情况说明</label>
												<div>
													<input  type="text" class="form-control"   name="epReDescribe"  id="epReDescribe" placeholder="维修情况说明" />
												</div>
											</div>
											<div class="form-group">
												<label for="example-datetime-local-input" >维修完成时间</label>
												<div >
													<input  type="datetime-local" class="form-control"   name="epReTime"  id="epReTime" placeholder="维修完成时间" />
												</div>
											</div>
												
											<div class="form-group">
												<label>设备状态</label>
												<div>
													<select class="form-control" id="statusOldVal" name="statusOldVal">
														<option value="">--请选择--</option>                                             
														<c:forEach items="${fns:getDictList('status_type')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>										
										    <div class="form-group">
												<div>
													<div class="alert alert-danger" style="background-color: white;">
														<strong>${message}</strong>
													</div>
													<button type="submit" id="submit"
														class="btn btn-primary waves-effect waves-light">
														提交</button>
													<button type="reset"
														class="btn btn-secondary waves-effect m-l-5">清空</button>


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

        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
            
            //时间
           function show_checksLoc(){
        	   $('#checksLoc').show();
           }
           function hide_checksLoc(){
        	   $('#checksLoc').hide();
           } 



           function check_form()
           {
               var epId = $.trim($('#epid').val());
               if(!epId)
               {
                   alert('设备编号ID不能为空！');
                   return false;
               }
                  var form_data = $('#form_data').serialize();
               $.ajax(
                       {
                           url: "${proPath}/electricalCheck/add.action",
           				   data:form_data,
                           type: "post",
                           async:false,
                           beforeSend:function()
                           {
                               return true;
                           },
                           success:function(data)
                           {
                               if(data.success==true)
                               {
                                   alert(data.msg);
                               	location.reload(true);
                               }
                               else
                               {
                                   alert('操作失败');
                               }
                           },
                           error:function()
                           {
                               alert('请求出错');
                           },
                           complete:function()
                           {
//                            	location.reload(true);
                           }
                       });

           }

        </script>

        <!-- App js -->
        <script src="${proPath}/assets/js/app.js"></script>

    </body>
</html>