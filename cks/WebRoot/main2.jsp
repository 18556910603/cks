<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<!-- xuyaya--系统主页 -->
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>系统主页</title>
    <meta content="Admin Dashboard" name="description" />
    <meta content="ThemeDesign" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="shortcut icon" href="assets/images/faviicon.png">
    
    <!-- 公共css -->
   <%@ include file="/common/comcss.jspf"%>
   <!--Chartist Chart CSS -->
    <link href="${proPath}/assets/plugins/chartist/css/chartist.min.css" rel="stylesheet" type="text/css">
   
   
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
	<%@include file="/menu/leftSidebar.jsp" %>
	<!-- Left Sidebar End -->
        <!-- Start right Content here -->
        <div class="content-page">
            <!-- Start content -->
            <div class="content">
<!--引入上边菜单 -->
                    <!-- Top Bar Start -->
	<%@include file="/menu/topbar.jsp" %>
                    <!-- Top Bar End -->
                <div class="page-content-wrapper ">
             <div class="container-fluid">            
                  <div id="ownerDiv"  style="display: none">
                   <div class="row">
                     <div class="col-md-12 col-xl-12">
                       <div align="center">
                        <img src="${proPath}/assets/images/owner1.png" style="width: 1000px;height: 350px" class="img-fluid" alt="Responsive image">
                       </div>
                      </div>
                  </div>
                   <div class="row">
                     <div class="col-md-12">
                     <div class="card-body">
                       <h3 style="text-align:center">您好！欢迎进入系统</h3>
                        </div> 
                      </div>
                    </div>
                    <div class="row">
                            <div class="col-md-4 col-xl-3" id="firstblankDiv" style="display: none">
                            </div> 
                            <div class="col-md-4 col-xl-2" id="secondblankDiv" style="display: none">
                            </div>                                                                              
                            <div class="col-md-4 col-xl-2" style="display: none" id="statisticsDiv">
                            <div class="card m-b-30 card-body text-center" onclick="javascript:location.href='${proPath}/ownerStatistics.action' ">
                            <div align="center">
                            <img src="${proPath}/assets/images/tongji.png"style="width: 50px;height:50px ">
                            </div>                           
                                                        结果统计              
                            </div>                            
                            </div>                       
                            <div class="col-md-4 col-xl-2" style="display: none" id="repairDiv">
                            <div class="card m-b-30 card-body text-center" onclick="javascript:location.href='${proPath}/ownerRepair.action'">
                            <div align="center">
                            <img src="${proPath}/assets/images/weixiu.png"style="width: 50px;height:50px ">
                            </div>                           
                                                         维修申报
                            </div>
                            </div>
                            <div class="col-md-4 col-xl-2"> 
                            <div class="card m-b-30 card-body text-center" onclick="javascript:location.href='${proPath}/toTask.action'">
                            <div align="center">
                            <img  src="${proPath}/assets/images/daiban.png"style="width: 50px;height:50px ">
                            </div>                                
                                                       待办处理
                            </div>
                            </div>
                            <div class="col-md-4 col-xl-2" style="display: none" id="completedDiv">
                            <div class="card m-b-30 card-body text-center" onclick="javascript:location.href='${proPath}/hsRepair.action'">
                            <div align="center">
                            <img src="${proPath}/assets/images/yiwancheng.png"style="width: 50px;height:50px ">
                            </div>                                
                                                        已完成任务
                            </div>
                            </div>
                            <div class="col-md-4 col-xl-2" style="display: none" id="hsrepairDiv">
                            <div class="card m-b-30 card-body text-center" onclick="javascript:location.href='${proPath}/hownerRepair.action'">
                            <div align="center">
                            <img src="${proPath}/assets/images/lishi.png"style="width: 50px;height:50px ">
                            </div>                                
                                                        历史维修单
                            </div>
                            </div>
                            <div class="col-md-4 col-xl-2">
                            <div class="card m-b-30 card-body text-center" onclick="javascript:location.href='${proPath}/ownerInformation.action'">
                            <div align="center">
                            <img src="${proPath}/assets/images/wode.png"style="width: 50px;height:50px ">
                            </div>                                
                                                        我的信息
                            </div>
                            </div>
                            
					  </div>
					 </div>
                    
                  </div>
                    <!-- container -->

                </div>
                <!-- Page content Wrapper -->

            </div>
<!--内容结束 -->
            <!-- content -->
	<%@include file="/menu/footer.jsp" %>


        </div>
        <!-- End Right content here -->

    </div>
    <!-- END wrapper -->

<!-- 公共js -->

    <!--Morris Chart-->
    <script src="${proPath}/assets/plugins/morris/morris.min.js"></script>
    <script src="${proPath}/assets/plugins/raphael/raphael-min.js"></script>


        <script src="${proPath}/assets/plugins/chartist/js/chartist.min.js"></script>
        <script src="${proPath}/assets/plugins/chartist/js/chartist-plugin-tooltip.min.js"></script>

    <script src="${proPath}/assets/pages/dashborad.js"></script>

    <!-- App js -->
    <script src="${proPath}/assets/js/app.js"></script>
     	
   	<script type="text/javascript">    
    $(function() {  
    var monthList=	${monthList};
    "use strict";  
    //BAR CHART   每个月报修设备台数
    var bar = new Morris.Bar({  
        element: 'bar-chart',  
        resize: true,  
        data: monthList,
        barColors: ['#00a65a'],  
        xkey: "month",  
        ykeys: ['ckCounts'],  
        ymax: 'auto 20',  
        units: '',  
        labels: ['巡检报修/(次数)'],    
        hideHover: 'true',  
        hoverFillColor:'#00a65a'  
    }); 
    
    
    var userNameList=${userNameList};
    var todayCksList=${todayCksList};
    new Chartist.Line('#chart-with-area', {
    	  labels: userNameList,
    	  series: [
				  todayCksList
    	  ]
    	}, {
    	  low: 0,
    	  showArea: true,
    	  plugins: [
    	    Chartist.plugins.tooltip()
    	  ]
    	});  
    
    
    
    var tUserEpTypeCountsList=${tUserEpTypeCountsList};
    var bar = new Morris.Bar({  
        element: 'bar-chart2',  
        resize: true,  
        data: tUserEpTypeCountsList,
        barColors: ['#00a65a', '#3c8dbc', '#f56954'],  
        xkey: "userName",  
        ykeys: ['electricalEp', 'productEp', 'fireEp','testEp'],  
        ymax: 'auto 20',  
        units: '',  
        labels: ['电气设备/(台数)', '生产设备/(台数)', '消防设备/(台数)', '泵-变频/(台数)'],      
        hideHover: 'true',  
        hoverFillColor:'#00a65a'  
    }); 
 // LINE CHART  

    //DONUT CHART  
    
 var abnormalOperation=${tMainJspValue.abnormalOperation} ;
 var normalOperation= ${tMainJspValue.normalOperation} 
 var maintenanceState=${tMainJspValue.maintenanceState}
    var donut = new Morris.Donut({  
        element: 'status-chart',  
        resize: true,  
        colors: ["#3c8dbc", "#f56954", "#00a65a"],  
        data: [  
            {label: "异常状态/(台数)", value: abnormalOperation},  
            {label: "正常运行/(台数)", value: normalOperation},  
            {label: "维修中/(台数)", value: maintenanceState}  
        ],  
        hideHover: 'true'  
    });  
});
 
    
    </script> 
    <script type="text/javascript"> 

	$(window).load(function() {
	 var identity='${SessionContainer.user.identity}';  
     var type='${SessionContainer.user.type}';
     if(type=='1'){
    	 $('#checksDiv').show(); 
     }
     else 
     {
    	 $('#checksDiv').hide(); 
    	 $('#ownerDiv').show(); 
     }
     if(identity.trim()=="【业主】"){
   	 $('#hsrepairDiv').show();
   	 $('#secondblankDiv').show();
   	 $('#repairDiv').show();
     }
     else if(identity=="【业主-维修人员】"){
     $('#completedDiv').show(); 
     $('#firstblankDiv').show();
     }    
     else if(identity=="【业主维修主管】"){
     $('#completedDiv').show();
     $('#statisticsDiv').show(); 
     $('#secondblankDiv').show();
     }
    
 	});

	

   </script>    
</body>

</html>