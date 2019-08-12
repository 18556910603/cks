<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<!-- xuyaya--系统主页 -->
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>巡检结果总览</title>
    <meta content="Admin Dashboard" name="description" />
    <meta content="ThemeDesign" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="shortcut icon" href="assets/images/faviicon.png">
    
    <!-- 公共css -->
   <%@ include file="/common/comcss.jspf"%>
   <!--Chartist Chart CSS -->
    <link href="${proPath}/assets/plugins/chartist/css/chartist.min.css" rel="stylesheet" type="text/css">
     
     <style type="text/css">
    	.row {
    	    display: -webkit-box;
    	    display: -webkit-flex;
    	    display: -ms-flexbox;
    	    display: flex;
    	    flex-wrap: wrap;
    	}
    	.row > [class*='col-'] {
    	    display: flex;
    	    flex-direction: column;
    	}
    	
    	.card-body {
    -webkit-box-flex: 1;
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    padding: 0.8rem;
}

.list-group-item:last-child {
    border-radius: 0;
    padding: 3px 20px;
}

.list-group-item:first-child {
    border-radius: 0;
    padding: 3px 20px;
}
.m-b-15 {
    margin-bottom: 5px;
}
.m-t-20 {
    margin-top: 5px;
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
            
		<!--内容开始：第一个栏目 -->
		                   <div class="row">                       
                            <div class="col-md-2 col-xl-2">                                
                                <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">今日报修工单数</h6>
                                         <p class="card-text" style="font-size:50px ;color: #0f9cf3">${ownerStaticJspValue.todayRepCount}</p>                                                                              
                                </div>
                            </div>
<!--                             <div class="col-md-8 col-xl-8">
                            <div class="row"> -->
                            <div class="col-md-2 col-xl-2" id="firstDiv">
                                <div class="card m-b-30 card-body text-center">
                                         <h6 class="card-title font-50 mt-0">今日完成工单数</h6>
                                         <p class="card-text" style="font-size:50px;color: #0f9cf3">${ownerStaticJspValue.todayComRepCount}</p>

                                </div>
                            </div>
                            <div class="col-md-2 col-xl-2" id="secondDiv">
                                <div class="card m-b-30">
                                <div class="card-body">
                                     <h6 class="card-title font-50 mt-0">今日完成报修类型分布</h4>
                                     <p class=" card-text text-primary">共计<span class="badge badge-success pull-right" id="comRep"></span></p>
                                </div>
                                     <ul class="list-group list-group-flush">
									 <li class="list-group-item" style="font-size:12px;">家用电器<span class="badge badge-primary pull-right">${ownerStaticJspValue.privateRepCount}</span></li>
                                     </ul> 
                                     <ul class="list-group list-group-flush">
									 <li class="list-group-item" style="font-size:12px;">公共设备<span class="badge badge-primary pull-right">${ownerStaticJspValue.publicRepCount}</span></li>
                                     </ul> 
                                </div>
                            </div>
                            
                            <div class="col-md-2 col-xl-2">
                                <div class="card m-b-30">
                                <div class="card-body">
                                     <h6 class="card-title font-50 mt-0">今日未完成报修类型分布</h4>
                                     <p class=" card-text text-primary">共计<span class="badge badge-success pull-right" id="comUpRep"></span></p>
                                </div>
                                     <ul class="list-group list-group-flush">
									 <li class="list-group-item" style="font-size:12px;">家用电器<span class="badge badge-primary pull-right">${ownerStaticJspValue.privateUnRepCount}</span></li>
                                     </ul> 
                                     <ul class="list-group list-group-flush">
									 <li class="list-group-item"style="font-size:12px;">公共设备<span class="badge badge-primary pull-right">${ownerStaticJspValue.publicUnRepCount}</span></li>
                                     </ul> 
                                </div>
                            </div>                            
                            
                            <div class="col-md-2 col-xl-2">
                                <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">本周发起的报修工单数</h4>
                                        <p class="card-text" style="font-size:50px;color: #5CDF41">${ownerStaticJspValue.weekRepCount}</p>                                    
                                </div>
                            </div>
                           <div class="col-md-2 col-xl-2">
                                 <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">本周完成的报修工单数</h4>
                                        <p class="card-text" style="font-size:50px;color:#F45345">${ownerStaticJspValue.weekComRepCount}</p>
                                </div>
                            </div>
                            
                            <%-- <div class="col-md-2 col-xl-2">
                                 <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">本周进行中报修工单数</h4>
                                        <p class="card-text" style="font-size:50px;color: #F3E75F">${ownerStaticJspValue.weekUnCRepCount}</p>
                                </div>
                            </div>  --%>
                            </div> 
                            <!-- </div>  -->                                

                        </div>

						<div class="row">
							<div class="col-md-8 col-xl-8">
								<div class="card card-sec m-b-30" style="height: 415px">
									<div class="card-body">
										<h5 class="mt-0">本月设备类型报修(/次数)</h5>
										<div id="overlapping-bars" class="ct-chart ct-golden-section"></div>
									</div>
								</div>
							</div>

                                <div class="col-md-4 col-xl-4" >
                                    <div class="card m-b-30" style="height: 415px">
                                        <div class="card-body">

                                            <h5 class="mt-0">本周小区报修百分比分布</h5>
                                            <ul class="list-inline widget-chart m-t-20 m-b-15 text-center">                 
                                                <li >
                                                    <h6 class=""style="color:#5CDF41 "><b id="LPerc">${ownerStaticJspValue.weekLRepCount}(单)</b></h6>
                                                    <p class="card-text" style="font-size:15px ">翡翠公园</p>
                                                </li>
                                                <li >
                                                    <h6 class=""style="color:#F45345 "><b id="TPerc">${ownerStaticJspValue.weekTRepCount}(单)</b></h6>
                                                    <p  class="card-text" style="font-size:15px ">浪花苑</p>
                                                </li>
                                                <li >
                                                    <h6 class=""style="color:#F3E75F "><b id="RPerc">${ownerStaticJspValue.weekRRepCount}(单)</b></h6>
                                                    <p class="card-text" style="font-size:15px">荣华世家</p>
                                                </li>
                                            </ul>

                                            <div id="simple-pie" class="ct-chart ct-golden-section simple-pie-chart-chartist"></div>

                                        </div>
                                    </div>
                                </div> <!-- end col -->
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

        <!-- jQuery  -->

        <!--Chartist Chart-->
        <script src="${proPath}/assets/plugins/chartist/js/chartist.min.js"></script>
        <script src="${proPath}/assets/plugins/chartist/js/chartist-plugin-tooltip.min.js"></script>
        <script src="${proPath}/assets/pages/chartist.init.js"></script>

        <!-- App js -->
        <script src="${proPath}/assets/js/app.js"></script>
        
        
       <!--Morris Chart-->
       <script src="${proPath}/assets/plugins/morris/morris.min.js"></script>
       <script src="${proPath}/assets/plugins/raphael/raphael-min.js"></script>
       
       <script src="${proPath}/assets/plugins/chartist/js/chartist.min.js"></script>
       <script src="${proPath}/assets/plugins/chartist/js/chartist-plugin-tooltip.min.js"></script>
       <script src="${proPath}/assets/pages/chartist.init.js"></script>
       
       <script src="${proPath}/assets/pages/dashborad.js"></script>

   <script type="text/javascript"> 
    $(function() {
       var epNames=	${epNames};
       var counts=	${counts};
       var data = {
        		  labels: epNames,
        		  series: [
                         counts
        		  ]
        		};

        		var options = {
        		  seriesBarDistance: 10
        		};

        		var responsiveOptions = [
        		  ['screen and (max-width: 640px)', {
        		    seriesBarDistance: 5,
        		    axisX: {
        		      labelInterpolationFnc: function (value) {
        		        return value[0];
        		      }
        		    }
        		  }]
        		];

        		new Chartist.Bar('#overlapping-bars', data, options, responsiveOptions);

          
    var privateRepCount=${ownerStaticJspValue.privateRepCount};
    var publicRepCount=${ownerStaticJspValue.publicRepCount};
    var privateUnRepCount=${ownerStaticJspValue.privateUnRepCount};
    var publicUnRepCount=${ownerStaticJspValue.publicUnRepCount};
    var comRep=parseInt(privateRepCount)+parseInt(publicRepCount);
    var comUpRep=parseInt(privateUnRepCount)+parseInt(publicUnRepCount);
    $("#comRep").text(comRep);
    $("#comUpRep").text(comUpRep);
    
    var weekLRepCount=${ownerStaticJspValue.weekLRepCount};
    var weekTRepCount=${ownerStaticJspValue.weekTRepCount};
    var weekRRepCount=${ownerStaticJspValue.weekRRepCount};
    var LPerc=Math.round(weekLRepCount/(weekLRepCount+weekTRepCount+weekRRepCount)*100);
    var TPerc=Math.round(weekTRepCount/(weekLRepCount+weekTRepCount+weekRRepCount)*100);
    var RPerc=100-LPerc-TPerc;
    var data = {
    		 series: [ LPerc, TPerc, RPerc ]
       };
   
       var sum = function(a, b) { return a + b };

       new Chartist.Pie('#simple-pie', data, {
         labelInterpolationFnc: function(value) {
           return Math.round(value / data.series.reduce(sum) * 100) + '%';
         }
       });
    });
    </script>
</body>

</html>