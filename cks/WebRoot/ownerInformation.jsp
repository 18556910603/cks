<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>我的基本信息</title>
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
      
     <style>
     #parent{width:550px; height:10px; border:2px solid #09F;}#son {width:0;height:100%;border-radius:10px;background-color:#e94829;text-align:center;line-height:20px;font-size:15px;color:white;font-weight:bold;} </style>

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
								
									<div class="card-body" >

										<h4 class="mt-0 header-title">个人信息</h4>
										<form class="" action="" onsubmit="" id="form_data" method="post"  enctype="multipart/form-data">
                                            	
                                         
  										  <div id="firstLoginDiv" > 首次登陆请先完善信息</div>
  	
  										  <br> 
											<div class="form-group row">
												<label  for="example-text-input" class="col-sm-3 col-form-label">姓名</label>												
												<div class="col-sm-9">
													<input  type="text"  class="form-control"  value="${SessionContainer.user.userName}" name="userName"  id="userName"/>
												</div>
											</div>
											
											<div class="form-group row">
												<label  for="example-text-input" class="col-sm-3 col-form-label">电话</label>												
												<div class="col-sm-9">
													<input  type="text"  class="form-control"  value="${SessionContainer.user.mobile}" name="mobile"  id="mobile"/>
												</div>
											</div>
											
                                                 <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">所属小区</label>
                                                    <div class="col-sm-9">
                                                    <select class="form-control"  id="ownLoc" name="ownLoc" value="${owner.ownLoc }" readonly="readonly">
                                                    <option value="">----请选择----</option>
													<c:forEach items="${fns:getDictList('own_loc')}" var="dict">
													<option value="${dict.value}">${dict.label}</option>
													</c:forEach>
                                                    </select>                                                  
                                                   </div>                                                                                                    
                                                </div>	                                                
                                                                                               
                                                <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">具体地址</label>
                                                    <div class="col-sm-9">
                                                   <input class="form-control" type="text" value="${owner.ownLocation }" name="ownLocation" id="ownLocation" >
                                                   </div>                                                                                                    
                                                </div>	
											
										        <div class="form-group">
												<div>
												
												  
  										 	       <button type="button" id="submitDiv" onclick="save()" class="btn btn-primary waves-effect waves-light">提交</button>
												   <button type="button" id="updateDiv" onclick="update()" class="btn btn-primary waves-effect waves-light">编辑</button>
												   
													<span style="color: red;"  id="tip"></span>
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
            
            window.onload = function(){
            	$("#ownLoc").val(${owner.ownLoc });
            	var ownLoc = $.trim($('#ownLoc').val());
         	    var userName = $.trim($('#userName').val());
         	    var mobile= $.trim($('#mobile').val());
         	    var ownLocation = $.trim($('#ownLocation').val());
            	if(!ownLoc||!userName||!mobile||!ownLocation){
            		$('#submitDiv').css('display','block');
            		 $('#updateDiv').css('display','none');
            		 $('#firstLoginDiv').css('display','block');
            		 
            	}
            	if(ownLoc&&mobile&&userName&&ownLocation){
                     $('#submitDiv').css('display','none');
                     $('#updateDiv').css('display','block');
                     $('#firstLoginDiv').css('display','none');
                     $('input').attr("readonly","readonly");
            	}
            }

            function save()
            {
         	    var userName = $.trim($('#userName').val());
         	    var mobile= $.trim($('#mobile').val());
         	    var ownLoc = $.trim($('#ownLoc').val());
         	    var ownLocation = $.trim($('#ownLocation').val());
         	    
         	    if(!userName)
         	    {
         	        alert('姓名不能为空！');
         	        return false;
         	    }
         	    if(!mobile)
         	    {
         	        alert('电话不能为空！');
         	        return false;
         	    }
         	    if(!ownLoc)
         	    {
         	        alert('小区名称不能为空！');
         	        return false;
         	    }
         	   if(!ownLocation)
        	    {
        	        alert('地址不能为空！');
        	        return false;
        	    }
         	       var form_data = $('#form_data').serialize();
         	    $.ajax(
         	            {
         	                url: "${proPath}/ownerAddInformation.action",
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
         	                        $("#tip").html("<span style='color:blueviolet'>" +data.msg+ "</span>");
         	                        $('#submitDiv').css('display','none');
         	                        $('#updateDiv').css('display','block');
         	                        $('#firstLoginDiv').css('display','none');
         	                        $('input').attr("readonly","readonly");
         	                       
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
//          	                	location.reload(true);
         	                }
         	            });

//         	     return false;       	   
              }
            function update()
            {
            	$('input').removeAttr("readonly");
            	$('#ownLoc').removeAttr("readonly");
         		$('#firstLoginDiv').css('display','none');
                $('#submitDiv').css('display','block');
                $('#updateDiv').css('display','none');
                $("#tip").html("<span style='color:blueviolet'></span>");
            }
            
           
        </script>
     
       

        <!-- App js -->
        <script src="${proPath}/assets/js/app.js"></script>
    </body>
</html>