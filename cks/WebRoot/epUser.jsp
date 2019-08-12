<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>巡检员管理页</title>
<meta content="Admin Dashboard" name="description" />
<meta content="ThemeDesign" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<link rel="shortcut icon" href="assets/images/faviicon.png">

<!-- DataTables -->
<link href="assets/plugins/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/datatables/buttons.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<!-- Responsive datatable examples -->
<link href="assets/plugins/datatables/responsive.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />

<!-- Sweet Alert -->
<link href="assets/plugins/sweet-alert2/sweetalert2.min.css"
	rel="stylesheet" type="text/css">
<!-- Alertify css -->
<link href="assets/plugins/alertify/css/alertify.css" rel="stylesheet" type="text/css">

<link href="assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="assets/css/style.css" rel="stylesheet" type="text/css">
<!-- 公共css -->
<%@ include file="/common/comcss.jspf"%>

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
							<div class="col-12">
								<div class="card m-b-30">
									<div class="card-body">
										<h4 class="mt-0 header-title">巡检人员基本信息表</h4>
                                        <p class="text-muted m-b-30 font-14">						               
                                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal"onclick="add()">新增巡检员</button>                 
				                        </p>
										<table id="datatable" class="table table-bordered">
							 			    <thead>
												<tr style="background-color: #b0ece3;">
													<th>巡检员编号</th>
													<th>巡检员姓名</th>
													<th>巡检员联系方式</th>
													<th>设备类型</th>
													<th>负责区域</th>												
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="epUser" items="${epUsers}">
													<tr>
														<td>${epUser.epUserId }</td>
														<td>${epUser.user.userName }</td>
														<td>${epUser.user.mobile  }</td>
														<td>
															${fns:getDictLabel(epUser.epUserType,'epType',epUser.epUserType)}</td>
														<td>${epUser.epUserLoc }</td>													
														<td>																					                                      
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-success " data-toggle="modal" data-target="#myModal"  onclick="get_edit_info('${epUser.epUserId}')">修改</button>                                                                     
                                                            <button type="button" class="btn btn-danger"  onclick="delete_info('${epUser.epUserId}')">删除</button>                                   
                                                    	</div>
                                                    	</td>
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
                                                
                                                <form method="post" action="" class="form-horizontal" role="form" id="form_data" onsubmit="return check_form()" style="margin: 20px;">
                                                
                                                 <input type="act" id="act" name="act" style="display: none;" value="add">
                                                
                                                 <!-- sample modal content -->
                                               
                                                    <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title mt-0" id="myModalLabel">设备信息</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                </div>
                                                <div class="modal-body">
            
                                                <form class="form-horizontal" role="form" method="post" onsubmit="check_form()" id="editform">
													                 
                                                <div class="form-group" id="epUserIdDiv">
                                                    <label>巡检员编号</label>                                                     
                                                   <select class="form-control" id="epUserId" name="epUserId"  onchange="epUserChange()">
												  <c:forEach items="${epUsers}" var="epUser">
												  <option value="${epUser.epUserId}" >${epUser.epUserId}</option>
												  </c:forEach>
								                   </select>
                                               
                                                </div>
                                                
                                               
                                                <input type="hidden"  class="form-control" name="userId" id="userId" />
                                                 
                                                
                                                <div class="form-group">
                                                    <label>巡检员姓名</label>
                                                    <div>
                                                        <input type="text"  class="form-control" name="userName" id="userName"/>
                                                    </div>
                                                </div>	

                                                <div class="form-group">
                                                    <label>巡检员联系方式</label>
                                                    <div>
                                                        <input type="text" class="form-control" name="mobile" id="mobile" />
                                                    </div>
                                                </div>
                                              
                                                 <div class="form-group">
                                                    <label>设备类型</label>
                                                    <div>                          
  	                                                <select class="form-control" id="epUserType" name="epUserType"> 
  	                                                	<option value="">--请选择--</option>                                             
														<c:forEach items="${fns:getDictList('epType')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
                                                    </div>
                                                </div>             
                                                 <div class="form-group">
                                                    <label>设备区域</label>
                                                    <div>
                                                    <select name="epUserLoc" id="epUserLoc" class="form-control">	                                               
	                                                <option value="">--请选择--</option>
	                                                <option value="B1层" >B1层</option>
                                                    <option value="B2层" >B2层</option>
                                                    <option value="B3层" >B3层</option>
                                                    <option value="B4层" >B4层</option>
                                                    <option value="B5层" >B5层</option>
	                                                </select>
	                                               </div>                                                 
                                                </div>
  				                          </form>   				                          
                                                </div>  
                                                                                                          
                                                                <div class="modal-footer">
                                                                    <button type="submit" class="btn btn-primary" >提交</button>
                                                                    <button type="button" class="btn btn-secondary waves-effect" data-dismiss="modal">取消</button>

                                                                  <span id="tip"></span>
                                                                </div>
                                                            </div><!-- /.modal-content -->
                                                        </div><!-- /.modal-dialog -->
                                                    </div><!-- /.modal -->                                                
											 </form>
															<%-- <a href="remove.action?epId=${epUserment.epId }"
															onclick="return isRemove()">删除</a> --%>
														
															<%-- <a href="searchfindEpUser.action?epId=${epUserment.epId }">查看设备巡检人</a> --%>
									
						</div>
					</div>
					</div>
					<!-- end row -->
					</div>
					<!-- container -->


				</div>
				<!-- Page content Wrapper -->
		
			</div>
			<!-- content -->
	
			<footer class="footer">
				© 2018 Upcube - Crafted with <i class="mdi mdi-heart text-danger"></i>
				by Themesdesign.
			</footer>

		</div>
		<!-- End Right content here -->

	</div>
	<!-- END wrapper -->


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

    <script src="/assets/pages/dashborad.js"></script>

    <!-- App js -->
    <script src="assets/js/app.js"></script>
    

<script type="text/javascript">
$("#epUserId").bind("change",function(){
   var act = $.trim($('#act').val());
   // if(act=='add'){ 
   var nSel = document.getElementById("epUserId");
   var index = nSel.selectedIndex; // 选中索引
   var text = nSel.options[index].text;  	  
   var value= nSel.options[index].value;
   
	var arr = new Array();
	<c:forEach items="${epUsers}" var= "epUser">
	    var module = {"epUserId":"${epUser.epUserId}","userId":"${epUser.userId}","userName":"${epUser.user.userName}","mobile":"${epUser.user.mobile}","epUserType":"${epUser.epUserType}","epUserLoc":"${epUser.epUserLoc}"};
	    arr.push(module);
	</c:forEach>
	for(var i = 0;i<arr.length;i++) {
	   if(value==arr[i].epUserId){
       $("#epUserId").val(arr[i].epUserId);
       $("#userId").val(arr[i].userId);
       $("#userName").val(arr[i].userName);
       $("#mobile").val(arr[i].mobile);
       $("#epUserType").val(arr[i].epUserType); 
       $("#epUserLoc").val(arr[i].epUserLoc); 
       											
//  }
	}
  }
});



function add(){
	$('#epUserIdDiv').css('display','none'); 
 /*    $("#statusId").val(''); */
 	  $("#epUserId").val('');
 	  $("#userId").val('');
      $("#userName").val('');
      $("#mobile").val('');
      $("#epUserType").val(''); 
      $("#epUserLoc").val('');
      $("#act").val('add');
}


// 编辑表单
function get_edit_info(epUserId)
{
	$("#act").val('update');
	$('#epUserIdDiv').css('display','block'); 
    if(!epUserId)
    {
        alert('Error！');
        return false;
    }
    // var form_data = new Array();
    $.ajax(
            {
               /*  url: "${proPath}/status/editOne.action",
                data:{"statusId":statusId}, */
                url: "${proPath}/editepUser.action",
                data:{"epUserId":epUserId},
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
                      /*   $("#statusId").val(data.statusId); */
                        $("#epUserId").val(data.epUserId);
                        $("#userId").val(data.userId);
                        $("#userName").val(data.user.userName);
                        $("#mobile").val(data.user.mobile);
                        $("#epUserType").val(data.epUserType);
                        $("#epUserLoc").val(data.epUserLoc);
                       
 
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

// 提交表单
function check_form()
{

	var Url;
    var epUserId = $.trim($('#epUserId').val());
    var mobile = $.trim($('#mobile').val());
    var userName = $.trim($('#userName').val());
    var act = $.trim($('#act').val());
    var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(19[0-9]{1}))+\d{8})$/;
    if (myreg.test(mobile) == false) {
		alert("请输入有效的手机号!");
		return false;
	}
    if(act=='update'){
    	if(!epUserId)
        {
            alert('设备负责人编号ID不能为空！');
            return false;
        }
    	Url="${proPath}/updateEpUser.action";
    }else{
    	
    	if(!userName)
        {
            alert('姓名不能为空！');
            return false;
        }
    	if(!mobile)
        {
            alert('联系方式不能为空！');
            return false;
        }
    	Url="${proPath}/addEpUser.action";
    }
    
       var form_data = $('#form_data').serialize();
//        var submitData=decodeURIComponent(form_data,true);
//     // 异步提交数据到action/add_action.php页面
    $.ajax(
            {
                url: Url,
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
                        // document.location.href='system_notice.php'
                    	 alert('操作成功');
                    }
                    else
                    {
                        $("#tip").html("<span style='color:red'>"+data.msg+"</span>");
                        alert(data.msg);
                    }
                },
                error:function()
                {
                	alert('请求出错');
                },
                complete:function()
                {
                	location.reload(true);
//                     $('#acting_tips').hide();
                }
            });

//     return false;
}



//删除
function delete_info(epUserId)
{
	alertify.confirm("你确定要删除吗？", function (ev) {
        ev.preventDefault();
        alertify.success("你已点击了确定");
	
    if(!epUserId)
    {
        alert('Error！');
        return false;
    }
    $.ajax(
            {
                url: "${proPath}/deleteEpUser.action",
                data:{"epUserId":epUserId},
                type: "post",
                async:false,
                beforeSend:function()
                {
                    // $("#tip").html("<span style='color:blue'>正在处理...</span>");
                    return true;
                },
                success:function(data)
                {
                    if(data > 0)
                    {
                        alert('操作成功');
                        $("#tip").html("<span style='color:blueviolet'>恭喜，删除成功！</span>");

                        // document.location.href='world_system_notice.php'
                         location.reload();
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
                    // $('#tips').hide();
                }
            });
    }, function(ev) {
                ev.preventDefault();
                alertify.error("你已点击了取消");
            });
  
    return false;
}
</script>  

</body>

</html>