<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<!-- xuyaya--系统主页 -->
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>维修结果总览</title>
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
    	} */
    	
   .list-group-item {
    border-radius: 0;
    padding: 2px 20px;
   }
        
        .list-group-item:first-child {
           border-radius: 0;
        padding: 2px 20px;
               }
               
        .list-group-item:last-child {
        border-radius: 0;
        border-bottom-right-radius: 0px;
        border-bottom-left-radius: 0px;
        padding: 2px 20px;
          }

    .card-bodys {
    -webkit-box-flex: 1;
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    padding: 0.5rem;
        }
   	.m-b-30 {
    margin-bottom: 10px;
}
.ct-charts.simple-pie-chart-chartist .ct-label {
    color: #ffffff;
    fill: #ffffff;
    font-size: 10px;
}
.card-body {
    -webkit-box-flex: 1;
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    padding: 0.5rem;
}

.card-title {
    margin-bottom: .25rem;
}

.header-title {
    border-bottom: 1px solid rgba(0,0,0,0.05);
    padding-bottom: 0.25rem;
    margin-bottom: 0.25rem;
    position: relative;
    font-size: 18px;
    z-index: 1;
}
.m-b-15 {
    margin-bottom: 1px;
}
.m-t-20 {
    margin-top: 1px;
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
                            <div class="col-md-4 col-xl-4">                                
                                <div class="card m-b-30 card-body text-center">
                                        <h5 class="card-title font-50 mt-0">今日已检(台)</h5>
                                         <p class="card-text" style="font-size:100px ;color: #0f9cf3">${tMainJspValue.todayCks}</p>                                                                              
                                </div>
                            </div>
                            <div class="col-md-8 col-xl-8">
                            <div class="row">
                            <div class="col-md-3 col-xl-3" id="firstDiv">
                                <div class="card m-b-30 card-body text-center">
                                         <h6 class="card-title font-50 mt-0">今日待检(台)</h6>
                                         <p class="card-text" style="font-size:40px;color: #0f9cf3">${todayUncks}</p>

                                </div>
                            </div>
                            <div class="col-md-3 col-xl-3" id="secondDiv">
                                <div class="card m-b-30">
                                <div class="card-bodys" >
                                     <h6 class="card-title font-50 mt-0">今日已检分布(台)</h4>
                                     <p class=" card-text text-primary">共计<span class="badge badge-success pull-right">${tMainJspValue.todayCks}</span></p>
                                </div>
                                     <ul class="list-group list-group-flush">
                                     <c:forEach items="${StaticJspValues}" var="staticJspValue">
									 <li class="list-group-item" style="font-size:10px; padding: 2px 20px">${fns:getDictLabel(staticJspValue.epType,'epType',staticJspValue.epType)}<span class="badge badge-primary pull-right">${staticJspValue.todayChecked}</span></li>
									 </c:forEach>
                                     </ul> 
                                </div>
                            </div>
                            
                            <div class="col-md-3 col-xl-3">
                                <div class="card m-b-30">
                                <div class="card-bodys">
                                     <h6 class="card-title font-50 mt-0">今日待检分布(台)</h4>
                                     <p class=" card-text text-primary">共计<span class="badge badge-success pull-right">${todayUncks}</span></p>
                                </div>
                                     <ul class="list-group list-group-flush">
                                     <c:forEach items="${StaticJspValues}" var="staticJspValue">
									 <li class="list-group-item" style="font-size:10px; padding: 2px 20px">${fns:getDictLabel(staticJspValue.epType,'epType',staticJspValue.epType)}<span class="badge badge-primary pull-right">${staticJspValue.todayUnChecked}</span></li>
									 </c:forEach>
                                     </ul> 
                                </div>
                            </div>
                            
                             <div class="col-md-3 col-xl-3">
                                <div class="card m-b-30 card-body text-center">
                                         <h6 class="card-title font-50 mt-0">设备总数(台)</h4>
                                         <p class="card-text" style="font-size:40px;color: #0f9cf3">${sum}</p>
                                </div>                            
                             </div>
                            
                            <div class="col-md-3 col-xl-3">
                                <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">正常运行设备(台)</h4>
                                        <p class="card-text" style="font-size:40px;color: #5CDF41">${tMainJspValue.normalOperation}</p>                                    
                                </div>
                            </div>
                           <div class="col-md-3 col-xl-3">
                                 <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">异常运行设备(台)</h4>
                                        <p class="card-text" style="font-size:40px;color:#F45345">${tMainJspValue.abnormalOperation}</p>
                                </div>
                            </div>
                            
                            <div class="col-md-3 col-xl-3">
                                 <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">停机维修设备(台)</h4>
                                        <p class="card-text" style="font-size:40px;color: #F3E75F">${tMainJspValue.repairOperation}</p>
                                </div>
                            </div>
                             <div class="col-md-3 col-xl-3">
                                 <div class="card m-b-30 card-body text-center">
                                        <h6 class="card-title font-50 mt-0">报废停用设备(台)</h4>       
                                        <p class="card-text" style="font-size:40px;color: #0f9cf3">${tMainJspValue.scrapOperation}</p>
                                </div>
                            </div> 
                              </div> 
                            </div>                                 

                        </div>

						<div class="row">

                            <div class="col-xl-8" >
                                <div class="card card-sec m-b-30" style="height: 340px">
                                    <div class="card-body">
                                        <h4 class="mt-0 m-b-15 header-title">每日巡检数</h4>
											  <div id="chart-with-area" class="ct-chart ct-golden-section"></div>

                                    </div>
                                </div>
                            </div>

                                <div class="col-xl-4" >
                                    <div class="card m-b-30" >
                                        <div class="card-body">

                                            <h5 class="mt-0">设备状态百分比分布</h5>
                                            <ul class="list-inline widget-chart m-t-20 m-b-15 text-center">                 
                                                <li >
                                                    <h6 class=""style="color:#5CDF41 "><b id="normalPerc"></b></h6>
                                                    <p class="card-text" style="font-size:10px ">正常运行</p>
                                                </li>
                                                <li >
                                                    <h6 class=""style="color:#F45345 "><b id="unnormalPerc"></b></h6>
                                                    <p  class="card-text" style="font-size:10px ">异常运行</p>
                                                </li>
                                                <li >
                                                    <h6 class=""style="color:#F3E75F "><b id="repairPerc"></b></h6>
                                                    <p class="card-text" style="font-size:10px">停机维修</p>
                                                </li>
                                                <li >
                                                    <h6 class=""style="color: #0f9cf3 "><b id="scrapPerc"></b></h6>
                                                    <p class="card-text" style="font-size:10px ">报废停用</p>
                                                </li>
                                            </ul>

                                            <div id="simple-pie" class="ct-charts ct-golden-section simple-pie-chart-chartist" ></div>

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
       
       <script src="${proPath}/assets/pages/dashborad.js"></script>

   
<script type="text/javascript">    
    $(function() {  

    var dayCountList=	${dayCountList};
    var myDate = new Date(); //获取今天日期
    myDate.setDate(myDate.getDate() - 6);
    var dateArray = []; 
    var dateTemp; 
    var flag = 1; 
    for (var i = 0; i < 7; i++) {
        dateTemp =(myDate.getMonth()+1)+"月"+myDate.getDate()+"日";
        dateArray.push(dateTemp);
        myDate.setDate(myDate.getDate() + flag);
    }
    new Chartist.Line('#chart-with-area', {
    	  labels: dateArray,
    	  series: [
                  dayCountList
    	  ]
    	}, {
    	  low: 0,
    	  showArea: true,
    	  plugins: [
    	    Chartist.plugins.tooltip()
    	  ]
    	});  
    
    
  
});
    
    var normalOperation=${tMainJspValue.normalOperation} ;
    var abnormalOperation= ${tMainJspValue.abnormalOperation} ;
    var repairOperation=${tMainJspValue.repairOperation};
    var scrapOperation=${tMainJspValue.scrapOperation};
    var normalPerc=Math.floor(normalOperation/(normalOperation+abnormalOperation+repairOperation+scrapOperation)*10000)/100;
    var unnormalPerc=Math.floor(abnormalOperation/(normalOperation+abnormalOperation+repairOperation+scrapOperation)*10000)/100;
    var repairPerc=Math.floor(repairOperation/(normalOperation+abnormalOperation+repairOperation+scrapOperation)*10000)/100;
    var scrapPerc=Math.floor(scrapOperation/(normalOperation+abnormalOperation+repairOperation+scrapOperation)*10000)/100;
    $("#normalPerc").text(normalPerc+"%");
    $("#unnormalPerc").text(unnormalPerc+"%");
    $("#repairPerc").text(repairPerc+"%");
    $("#scrapPerc").text(scrapPerc+"%");
    var data = {
         series: [normalPerc,unnormalPerc, repairPerc, scrapPerc ]
       };

       var sum = function(a, b) { return a + b };

       new Chartist.Pie('#simple-pie', data, {
         labelInterpolationFnc: function(value) {
           return Math.floor(value / data.series.reduce(sum) * 10000) /100+ '%';
         }
       });
    
    </script>   
</body>

</html>