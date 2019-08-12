<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>设备历史状态表</title>
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

        <!-- DataTables -->
        <link href="${proPath}/assets/plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <link href="${proPath}/assets/plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <!-- Responsive datatable examples -->
        <link href="${proPath}/assets/plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />

        <link href="${proPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="${proPath}/assets/css/icons.css" rel="stylesheet" type="text/css">
        <link href="${proPath}/assets/css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body class="fixed-left">

        <!-- Loader -->
        <div id="preloader"><div id="status"><div class="spinner"></div></div></div>

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
                                <div class="col-12">
                                    <div class="card m-b-30">
                                        <div class="card-body">

                                            <h4 class="mt-0 header-title">设备历史状态表</h4>
                                            <p class="text-muted m-b-30 font-14">用于设备历史状态信息</p>
                                            <div class="table-rep-plugin">         
									       	<div class="table-responsive b-0" data-pattern="priority-columns">
                                            <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%"  style="overflow:auto;">
                                                <thead>
                                                <tr style="background-color: #b0ece3;">
                                                    <th>设备编号</th>
                                                    <th>设备名称</th>
                                                    <th>规格型号</th>
                                                    <th>安装地点</th>
                                                    <th>设备类型</th>
                                                    <th>设备状态</th>   
                                                    <th>创建人</th>     
                                                    <th>创建时间</th>   
                                                    <th>更新人</th>     
                                                    <th>更新时间</th>                                                       
                                                </tr>
                                                </thead>


											<tbody>
												<c:forEach items="${list}" var="statusT">
													<tr>
														<td>${statusT.epId}</td>
														<td>${statusT.epName}</td>
														<td>${statusT.epModelNum}</td>
														<td>${statusT.eplocation}</td>
														<td>${statusT.epTypeName}</td>
														<td>${statusT.statusValName}</td>	
														<td>${statusT.createdByName}</td>			
														<td>${statusT.creationDate}</td>		
														<td>${statusT.lastUpdatedByName}</td>		
														<td>${statusT.lastUpdatedDate}</td>																							
													</tr>
												</c:forEach>

											</tbody>
										</table>
 									</div>
 									
                                        </div>
                                    </div>
                                </div> <!-- end col -->
                            </div> <!-- end row -->
                        </div><!-- container -->


                    </div> <!-- Page content Wrapper -->

                </div> <!-- content -->

			<%@include file="/menu/footer.jsp"%>

            </div>
            <!-- End Right content here -->
 			</div>
        </div>
        <!-- END wrapper -->



        <!-- jQuery  -->


        <!-- Required datatable js -->
        <script src="${proPath}/assets/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Buttons examples -->
        <script src="${proPath}/assets/plugins/datatables/dataTables.buttons.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/buttons.bootstrap4.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/jszip.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/pdfmake.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/vfs_fonts.js"></script>
        <script src="${proPath}/assets/plugins/datatables/buttons.html5.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/buttons.print.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/buttons.colVis.min.js"></script>
        <!-- Responsive examples -->
        <script src="${proPath}/assets/plugins/datatables/dataTables.responsive.min.js"></script>
        <script src="${proPath}/assets/plugins/datatables/responsive.bootstrap4.min.js"></script>

        <!-- Datatable init js -->
        <script src="${proPath}/assets/pages/datatables.init.js"></script>


        <!-- App js -->
        <script src="${proPath}/assets/js/app.js"></script>

<script type="text/javascript">
$("#epId").bind("change",function(){
    var act = $.trim($('#act').val());
    if(act=='add'){
   var nSel = document.getElementById("epId");
   var index = nSel.selectedIndex; // 选中索引
   var text = nSel.options[index].text;  	  
   var value= nSel.options[index].value;
   
	var arr = new Array();
	<c:forEach items="${EquipList}" var= "Equip">
	    var module = {"epid":"${Equip.epid}","epname":"${Equip.epname}","epmodelnum":"${Equip.epmodelnum}","eplocation":"${Equip.eplocation}","eptype":"${Equip.eptype}"};
	    arr.push(module);
	</c:forEach>
	for(var i = 0;i<arr.length;i++) {
	   if(value==arr[i].epid){
       $("#epName").val(arr[i].epname);
       $("#epModelNum").val(arr[i].epmodelnum);
       $("#eplocation").val(arr[i].eplocation);
       $("#epType").val(arr[i].eptype);   }
	}
  }
});






function add(){
    $("#statusId").val('');
    $("#epId").val('');
    $("#epName").val('');
    $("#epModelNum").val('');
    $("#eplocation").val('');
    $("#epType").val('');
    $("#statusVal").val('');
	$("#act").val('add');
}


// 编辑表单
function get_edit_info(statusId)
{
	$("#act").val('update');
	
    if(!statusId)
    {
        alert('Error！');
        return false;
    }
    // var form_data = new Array();
    $.ajax(
            {
                url: "${proPath}/status/editOne.action",
                data:{"statusId":statusId},
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
                        $("#statusId").val(data.statusId);
                        $("#epId").val(data.epId);
                        $("#epName").val(data.epName);
                        $("#epModelNum").val(data.epModelNum);
                        $("#eplocation").val(data.eplocation);
                        $("#epType").val(data.epType);
                        $("#statusVal").val(data.statusVal);
//                         $("#statusVal").val(data.statusVal);
                        // 将input元素设置为readonly
//                         $('#user_id').attr("readonly","readonly")
//                        location.reload();
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
    var epId = $.trim($('#epId').val());
    if(!epId)
    {
        alert('设备编号ID不能为空！');
        return false;
    }
    var act = $.trim($('#act').val());
    if(act=='update'){
    	Url="${proPath}/status/update.action";
    }else{
    	Url="${proPath}/status/add.action";
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
                        alert(data.msg);
                      
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
                	location.reload(true);
//                     $('#acting_tips').hide();
                }
            });

//     return false;
}



//删除
function delete_info(statusId)
{
	
    if(!statusId)
    {
        alert('Error！');
        return false;
    }
    $.ajax(
            {
                url: "${proPath}/status/delete.action",
                data:{"statusId":statusId},
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

    return false;
}




// 提交表单 
// function delete_info(id)
// {
//     if(!id)
//     {
//         alert('Error！');
//         return false;
//     }
//     $.ajax(
//             {
//                 url: "${proPath}/status/delete.action",
//                 data:{"statusId":statusId},
//                 type: "post",
//                 async:false,
//                 beforeSend:function()
//                 {
//                     // $("#tip").html("<span style='color:blue'>正在处理...</span>");
//                     return true;
//                 },
//                 success:function(data)
//                 {
//                  if(data > 0)
//                  {
//                      alert('操作成功');
//                      $("#tip").html("<span style='color:blueviolet'>恭喜，删除成功！</span>");

//                      // document.location.href='world_system_notice.php'
//                       location.reload();
//                  }
//                     else
//                     {
//                         $("#tip").html("<span style='color:red'>失败，请重试</span>");
//                       //  alert('操作失败');
//                     }
                    
   
//                 },
//                 error:function()
//                 {
//                     alert('请求出错');
//                 },
//                 complete:function()
//                 {
//                     // $('#tips').hide();
//                 }
//             });

//     return false;
// }


</script>



    </body>
</html>