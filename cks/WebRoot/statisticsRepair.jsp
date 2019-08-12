<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<!-- xuyaya--系统主页 -->
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no, minimal-ui">
<title>本周维修情况统计</title>
<meta content="Admin Dashboard" name="description" />
<meta content="ThemeDesign" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta name="format-detection" content="telephone=no" />
<meta http-equiv="keywords" content="" />
<meta name="author" content="" />
<meta name="copyright" content="" />
<meta name="description" content="" />
<meta name="HandheldFriendly" content="true" />
<meta name="robots" content="all" />
<meta http-equiv="imagetoolbar" content="no" />
<meta http-equiv="pragma" Content="no-cach" />

<link rel="shortcut icon" href="${proPath}/assets/images/faviicon.png">
 
<link rel="stylesheet" type="text/css"
	href="${proPath}/assets/css/swiper.min.css" /> 
 <link type="text/css"  media="all" rel="stylesheet" href="${proPath}/assets/css/dropload.css">
<link type="text/css"  media="all" rel="stylesheet" href="${proPath}/assets/css/style2.css">
<link type="text/css" rel="stylesheet" href="${proPath}/assets/css/app.css">
	
<!-- 公共css -->
<%@ include file="/common/comcss.jspf"%>
<!--Chartist Chart CSS -->
<link href="${proPath}/assets/plugins/chartist/css/chartist.min.css"
	rel="stylesheet" type="text/css">
 <style type="text/css">
 .row {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	flex-wrap: wrap;
}

.row>[class*='col-'] {
	display: flex;
	flex-direction: column;
} 

.table>tbody>tr>td,.table>tfoot>tr>td,.table>thead>tr>td {
	padding: 3px 9px;
}

.card-body {
    padding: 1rem;
    }
.m-b-15 {
    margin-bottom: 5px;
}
.khfxWarp {
    padding-top: 0rem;
}

</style> 


</head>

<body class="fixed-left" >
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

						<!--内容开始：第一个栏目 -->
						<div class="row">
						    <div class="col-md-8 col-xl-8">
						    <div class="row">
							<div class="col-md-4 col-xl-4" >
								<div class="card m-b-30 card-body text-center" style="height:130px">
									<h6 class=" font-50 mt-0">本周发起的维修工单数</h6>
									<p  style="font-size: 60px; color: #0f9cf3">${repairCounts.weekCounts}</p>
								</div>
							</div>
							<div class="col-md-4 col-xl-4" >
								<div class="card m-b-30 card-body text-center"style="height:130px">
									<h6 class=" font-50 mt-0">本周完成的维修工单数</h6>
									<p style="font-size: 60px; color: #0f9cf3">${repairCounts.endCounts}</p>
								</div>
							</div>
							<div class="col-md-4 col-xl-4" >
								<div class="card m-b-30 card-body text-center"style="height:130px">
									<h6 class=" font-50 mt-0">本周进行中的维修工单数</h6>
									<p  style="font-size: 60px; color: #0f9cf3">${repairCounts.underwayCounts}</p>
								</div>
							</div>
						</div>
						<div class="row" >
							<div class="col-md-12 col-xl-12" >							
<%--  								<div class="card card-sec m-b-30" style="height:370px">
									<div class="card-body" >
										<h5 class="mt-0 m-b-15">本周维修工单</h5>
										<div class="table-responsive" style="font-size: 10px;">
											<table class="table table-hover mb-0">
												<thead>
													<tr class="titles" style="background-color: #b0ece3;">
														<th>设备</th>
														<th>安装地点</th>
														<th>类型</th>
														<th>报修时间</th>
														<th>故障描述</th>
														<th>工单号</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="equipmentRepairT"
														items="${equipmentRepairTs}">
														<tr data-toggle="modal" data-target="#myModal" style="cursor:pointer;"
															onclick="get_view_info('${equipmentRepairT.equipmentrepairId}')">
															<td class="c-table__cell">
																<div class="user-wrapper">
																	<div class="img-user">
																		<img src="${proPath}/assets/images/shebei.png" alt=""
																			style="width: 30px; height: 30px"
																			class="rounded-circle">
																	</div>
																	<div class="text-user">
																		<h12>${equipmentRepairT.epId}</h12>
																		<p>${equipmentRepairT.epHomeEquname}
																		<p>
																	</div>
																</div>
															</td>
															<td class="c-table__cell">${equipmentRepairT.epLocation}</td>
															<td class="c-table__cell"><span
																class="badge badge-warning">${equipmentRepairT.epHomeEqutype}</span></td>
															<td class="c-table__cell"><h12>${equipmentRepairT.eprCkTime}<h12>
																<p>${equipmentRepairT.userIdName}</p></td>
															<td class="c-table__cell">${equipmentRepairT.eprCkDescribe}</td>
															<td class="c-table__cell">${equipmentRepairT.equipmentrepairId}</td>
															
														</tr>
													</c:forEach>													
												</tbody>
											</table>
										</div>
									</div>
								</div>
 --%>						
                          
 							 <div class="card card-sec m-b-30" style="height:370px;"
                                    <div class="card-body">
                                      <h5 class="mt-0 m-b-15" style="padding: 0.8rem;" >本周维修工单(${size})</h5>
                                          <div class="table-responsive" style="font-size: 10px;" >                                                                     			
							              <article class="khfxWarp">
								          <section class="khfxPane" style="display: block"></section>
								          </article>		
									      </div>
								 													
						      </div>
						    </div>
						</div>
					</div>
						<div class="col-md-4 col-xl-4">
							<!-- <div class="col-xl-4"> -->
								<div class="card m-b-30">
									<div class="card-body" >

										<h5 class="mt-0 header-title">本周维修设备类型分布</h5>
										<ul class="list-inline widget-chart m-t-20 m-b-15 text-center">
											<li>
												<h5 class="" style="color: #5CDF41">
													<b>${repairCounts.electricalEpCounts}(台)</b>
												</h5>
												<p class="card-text" style="font-size: 10px">电气设备</p>
											</li>
											<li>
												<h5 class="" style="color: #F45345">
													<b>${repairCounts.productEpCounts}(台)</b>
												</h5>
												<p class="card-text" style="font-size: 10px">生产设备</p>
											</li>
											<li>
												<h5 class="" style="color: #F3E75F">
													<b>${repairCounts.fireEpCounts}(台)</b>
												</h5>
												<p class="card-text" style="font-size: 10px">消防设备</p>
											</li>
											<li>
												<h5 class="" style="color: #0f9cf3">
													<b>${repairCounts.pumpEpCounts}(台)</b>
												</h5>
												<p class="card-text" style="font-size: 10px">泵-变频</p>
											</li>
											<li>
												<h5 class="" style="color: #0f9cf3">
													<b>${repairCounts.publicEpCounts}(台)</b>
												</h5>
												<p class="card-text" style="font-size: 10px">公共设备</p>
											</li>											
										</ul>
									</div>
									<div id="simple-pie"
										class="ct-chart ct-golden-section simple-pie-chart-chartist" style="margin-bottom:60px" ></div>
								</div>
							</div>

							
						</div>
						
                        
						<!-- <div class="row" style="height: 300px;"> -->
                        </div>

						<div class="row">
							<div class="col-sm-6 col-md-3 m-t-30">

								<form method="post" action="" class="form-horizontal"
									role="form" id="form_data" onsubmit="return check_form()"
									>

									<input type="act" id="act" name="act" style="display: none;"
										value="add">

									<!-- sample modal content -->

									<div id="myModal" class="modal fade" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">

												<div class="modal-header">
													<h5 class="modal-title mt-0" id="myModalLabel">设备维修详情</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
												</div>
												<div class="modal-body">

													<form class="form-horizontal" role="form" method="post"
														onsubmit="check_form()" id="editform">

														<div>
															<div class="form-group">
																<label>故障报修</label>
																<div>
																	<hr
																		style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																</div>
															</div>

															<div class="form-group">
																<label>报修工单号</label>
																<div>
																	<input type="text" class="form-control"
																		name="equipmentrepairId" id="equipmentrepairId"
																		placeholder="报修工单号" readonly="readonly">
																</div>
															</div>

															<div class="form-group">
																<label>设备编号</label>
																<div>
																	<input type="text" class="form-control" name="epId"
																		id="epId" placeholder="设备编号" readonly="readonly">
																</div>
															</div>

															<div class="form-group">
																<label>设备名称</label>
																<div>
																	<input type="text" class="form-control"
																		name="epHomeEquname" id="epHomeEquname"
																		readonly="readonly" />
																</div>
															</div>

															<div class="form-group">
																<label>规格型号</label>
																<div>
																	<input type="text" class="form-control"
																		name="epModelNum" id="epModelNum" readonly="readonly" />
																</div>
															</div>

															<div class="form-group">
																<label>安装地点</label>
																<div>
																	<input type="text" class="form-control"
																		name="epLocation" id="epLocation" readonly="readonly" />
																</div>
															</div>

															<div class="form-group">
																<label>设备类型</label>
																<div>
																	<input type="text" class="form-control"
																		name="epTypeName" id="epTypeName" placeholder="设备类型"
																		readonly="readonly" />
																</div>
															</div>

															<div class="form-group">
																<label>报修时间</label>
																<div>
																	<input type="text" class="form-control"
																		name="eprCkTime" id="eprCkTime" placeholder="报修时间"
																		readonly="readonly" />
																</div>
															</div>

															<div class="form-group">
																<label>故障情况拍照</label>
																<input type="text" class="form-control"
																	placeholder="拍照签到" name="eprCkPhoto" id="eprCkPhoto"
																	style="display: none" />															
                                                             
															   <div id="list">
																<ul>
																	<li>															
																		<div class="po-cmt">
																			<div class="po-hd">																			 
																				<div class="post">
																					<p>	
																					    <div id="faceImgDiv"  style="display:none">	</div>																	
																					</p>
																				</div>
																			</div>
																		  <div class="r"></div>
																		</div>
																	</li>
																</ul>
															</div>
															
															<div class="big_img">
                                                            <div class="swiper-container2">
                                                            <div class="swiper-wrapper"> </div>
                                                            </div>
                                                            </div>
														   </div>
														
															<div class="form-group">
																<label>故障情况描述</label>
																<div>
																	<input type="text" class="form-control"
																		name="eprCkDescribe" id="eprCkDescribe"
																		placeholder="故障情况描述" readonly="readonly" />
																</div>
															</div>

															<div class="form-group">
																<label>报修人</label>
																<div>
																	<input type="text" class="form-control"
																		name="eprCkIdName" id="eprCkIdName" placeholder="报修人员"
																		readonly="readonly" />
																</div>
															</div>


															<div class="form-group" style="display: none">
																<label>报修人ID</label>
																<div>
																	<input type="text" class="form-control" name="userId"
																		id="userId" placeholder="报修人ID" readonly="readonly" />
																</div>
															</div>


														</div>
														<!------------------维修派工-------------------------------------------------------------------------------------------->
														<div id="leaderDispatch">
															<div class="form-group">
																<label>维修派工</label>
																<div>
																	<hr
																		style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																</div>
															</div>

															<div class="form-group">
																<label>故障类别</label>
																<div>
																	<input type="text" class="form-control" name="epReKind"
																		id="epReKind" placeholder="暂无内容" readonly="readonly" />
																</div>
															</div>

															<div class="form-group">
																<label>维修责任人</label>
																<div>
																	<input type="text" class="form-control" name="epReId"
																		id="epReId" placeholder="暂无内容" readonly="readonly" />
																</div>
															</div>
														</div>
														<!------------------维修实施-------------------------------------------------------------------------------------------->
														<div id="repair">
															<div class="form-group">
																<label>维修实施</label>
																<div>
																	<hr
																		style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																</div>
															</div>
															<div class="form-group">
																<label>维修情况说明</label>
																<div>
																	<input type="text" class="form-control"
																		name="epReDescribe" id="epReDescribe"
																		placeholder="暂无内容" readonly="readonly" />
																</div>
																<div class="form-group">
																	<label>维修完成时间</label>
																	<div>
																		<input type="text" class="form-control"
																			name="epReTime" id="epReTime" placeholder="暂无内容"
																			readonly="readonly" />
																	</div>
																</div>
															</div>
															<!------------------维修确认-------------------------------------------------------------------------------------------->
															<div id="confirm">
																<div class="form-group">
																	<label>维修确认</label>
																	<div>
																		<hr
																			style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
																		<div>
																			<small class="text-muted ye">维修人员已确认修复故障</small>
																		</div>
																		<div>
																			<small class="text-muted ye">请进行设备检查确认</small>
																		</div>
																	</div>
																</div>
																<div class="form-group">
																	<label>设备状态</label>
																	<div>
																		<input type="text" class="form-control"
																			name="epReStatus" id="epReStatus" placeholder="暂无内容"
																			readonly="readonly" />
																	</div>
																</div>
													</form>

													<div class="modal-footer">
														<button type="button"
															class="btn btn-secondary waves-effect"
															data-dismiss="modal">取消</button>
													</div>
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
			             				</div>
									</div>
								</form>
							</div>
  				       </div>
					</div>
					<!-- container -->

		           </div>
				<!-- Page content Wrapper -->

			</div>
			<!--内容结束 -->
			<!-- content -->
			<%@include file="/menu/footer.jsp"%>


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

	
	<script type="text/javascript" src="${proPath}/assets/js/swiper.min.js"></script>
<%--  	<script src="${proPath}/assets/js/dropload.js"></script> --%>
<%--     <script src="${proPath}/assets/js/khData.js"></script> --%>
	 

	<script type="text/javascript">    
  $(function() {  
    var electricalEpCounts=${repairCounts.electricalEpCounts} ;
    var productEpCounts= ${repairCounts.productEpCounts} ;
    var fireEpCounts=${repairCounts.fireEpCounts};
    var pumpEpCounts=${repairCounts.pumpEpCounts};
    var publicEpCounts=${repairCounts.publicEpCounts};
    
    var electricalPerc=Math.round(electricalEpCounts/(electricalEpCounts+productEpCounts+fireEpCounts+pumpEpCounts+publicEpCounts)*100);
    var productPerc=Math.round(productEpCounts/(electricalEpCounts+productEpCounts+fireEpCounts+pumpEpCounts+publicEpCounts)*100);
    var fireEpPerc=Math.round(fireEpCounts/(electricalEpCounts+productEpCounts+fireEpCounts+pumpEpCounts+publicEpCounts)*100);
    var pumpEpPerc=Math.round(pumpEpCounts/(electricalEpCounts+productEpCounts+fireEpCounts+pumpEpCounts+publicEpCounts)*100);
    var publicEpPerc=100-electricalPerc-productPerc-fireEpPerc-pumpEpPerc;
    
    
    var data = {
         series: [electricalPerc, productPerc, fireEpPerc, pumpEpPerc, publicEpPerc]
       };

       var sum = function(a, b) { return a + b };

       new Chartist.Pie('#simple-pie', data, {
         labelInterpolationFnc: function(value) {
           return Math.round(value / data.series.reduce(sum) * 100) + '%';
         }
       });
 
}); 


</script>
<script>
$(document).ready(function(){
  /*调起大图 S*/

   var mySwiper = new Swiper('.swiper-container2', {
        loop: false,
        pagination: '.swiper-pagination2',
        })
    $("#list").on("click", ".post img", 
    function() {
        var imgBox = $(this).parents(".post").find("img");
        var i = $(imgBox).index(this);
        $(".big_img .swiper-wrapper").html("")

        for(var j = 0 ,c = imgBox.length; j < c ;j++){
         $(".big_img .swiper-wrapper").append('<div class="swiper-slide"><div class="cell"><img src="' + imgBox.eq(j).attr("src") + '" / ></div></div>');
        }
        mySwiper.updateSlidesSize();
        mySwiper.updatePagination();
        $(".big_img").css({
            "z-index": 1001,
            "opacity": "1"
        });
        mySwiper.slideTo(i, 0, false);
        return false;
    });
  
    $(".big_img").on("click", 
    function() {
        $(this).css({
            "z-index": "-1",
            "opacity": "0"
        });

    });
  });

/*加载事件 */
 $(function () {
	var size=${size};
	var arr = new Array();
	<c:forEach items="${equipmentRepairTs}" var= "equipmentRepairT">
	var module = {
		"equipmentrepairId" : "${equipmentRepairT.equipmentrepairId}",
		"epId" : "${equipmentRepairT.epId}",
		"epHomeEquname" : "${equipmentRepairT.epHomeEquname}",
		"eprCkTime" : "${equipmentRepairT.eprCkTime}",
		"epModelNum" : "${equipmentRepairT.epModelNum}",
		"epLocation" : "${equipmentRepairT.epLocation}",
		"epTypeName" : "${equipmentRepairT.epHomeEqutype}",
		"eprCkDescribe" : "${equipmentRepairT.eprCkDescribe}",
		"eprCkIdName" : "${equipmentRepairT.userIdName}",
		"userId" : "${equipmentRepairT.userId}",
		"epReId" : "${equipmentRepairT.epReIdName}"
	};
	arr.push(module);
	</c:forEach>	
    var itemIndex = 0;   
    var tabLoadEndArray = [false, false, false];
    var tabLenghtArray = [size, 0, 0];
    var tabScroolTopArray = [0, 0, 0];
    
    // dropload
   var dropload = $('.khfxWarp').dropload({ 
        scrollArea: window,
        domDown: {
            domClass: 'dropload-down',
            domLoad: '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
            domRefresh: '<div class="dropload-refresh">上拉加载更多</div>',
            domNoData: '<div class="dropload-noData">已无数据</div>'
        },
        loadDownFn: function (me) {
            setTimeout(function () {
                if (tabLoadEndArray[itemIndex]) {
                    me.resetload();
                    me.lock();
                    me.noData();
                    me.resetload();
                    return;
                }
                var result = '';
                for (var index = 0; index < 10; index++) {
                    if (tabLenghtArray[itemIndex] > 0) {
                        tabLenghtArray[itemIndex]--;
                    } else {
                        tabLoadEndArray[itemIndex] = true;
                        break;
                    }
                    var i=tabLenghtArray[itemIndex];
                    var j=i+1;
                    if (itemIndex == 0) {
                        result
                        += ''
                        + '    <hgroup class="khfxRow"  data-toggle="modal" data-target="#myModal" style="cursor:pointer;" id="'+i+'">'
                        + '      <header><span> 安装地点:'+arr[i].epLocation+' </span>'+arr[i].eprCkTime +'&nbsp'+ arr[i].eprCkIdName+'<header>'
                        + '      <div class="mid">'
                        + '        <img class="photo" src="assets/images/shebei.png" style="width: 20px; height: 20px">'
                        + '        <span><label>编号：</label>'+arr[i].epId+'</span> '
                        + '        <span style="overflow: hidden; text-overflow: ellipsis;white-space: nowrap;" title="'+arr[i].eprCkDescribe+'"><label>故障描述：</label>'+arr[i].eprCkDescribe +'</span>'
                        + '        <span><label>序号：</label>' +j + '</span> '
                        + '        <span><label>名称：</label>'+arr[i].epHomeEquname+'</span> '
                        + '      </div>'   
                        + '    </hgroup>';
                    }               
                }
                $('.khfxPane').eq(itemIndex).append(result);
                $(document).on('click', '.khfxRow', function() {
                	   var id= this.id;
                    get_view_info(arr[id].equipmentrepairId);
                    });  

                me.resetload();
            }, 500);
        }
    });

}); 

 ;(function($){
	    'use strict';
	    var win = window;
	    var doc = document;
	    var $win = $(win);
	    var $doc = $(doc);
	    $.fn.dropload = function(options){
	        return new MyDropLoad(this, options);
	    };
	    var MyDropLoad = function(element, options){
	        var me = this;
	        me.$element = element;
	        // 上方是否插入DOM
	        me.upInsertDOM = false;
	        // loading状态
	        me.loading = false;
	        // 是否锁定
	        me.isLockUp = false;
	        me.isLockDown = false;
	        // 是否有数据
	        me.isData = true;
	        me._scrollTop = 0;
	        me._threshold = 0;
	        me.init(options);
	    };

	    // 初始化
	    MyDropLoad.prototype.init = function(options){
	        var me = this;
	        
	        me.opts = $.extend(true, {}, {
	            scrollArea : me.$element, 
	            // 滑动区域
	            domUp : {  
	            	// 上方DOM
	                domClass   : 'dropload-up',
	                domRefresh : '<div class="dropload-refresh">↓下拉刷新</div>',
	                domUpdate  : '<div class="dropload-update">↑释放更新</div>',
	                domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
	            },
	            domDown : {                                                          // 下方DOM
	                domClass   : 'dropload-down',
	                domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
	                domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
	                domNoData  : '<div class="dropload-noData">暂无数据</div>'
	            },
	            autoLoad : true,                                               // 自动加载
	            distance : 50,                                                       // 拉动距离
	            threshold : '',                                                      // 提前加载距离
	            loadUpFn : '',                                                       // 上方function
	            loadDownFn : ''                                                      // 下方function
	        }, options);

	        // 如果加载下方，事先在下方插入DOM
	        if(me.opts.loadDownFn != ''){
	            me.$element.append('<div class="'+me.opts.domDown.domClass+'">'+me.opts.domDown.domRefresh+'</div>');
	            me.$domDown = $('.'+me.opts.domDown.domClass);
	        }

	        // 计算提前加载距离
	        if(!!me.$domDown && me.opts.threshold === ''){
	            // 默认滑到加载区2/3处时加载
	            me._threshold = Math.floor(me.$domDown.height()*1/3);
	        }else{
	            me._threshold = me.opts.threshold;
	        }

	        // 判断滚动区域
	        if(me.opts.scrollArea == win){
	            me.$scrollArea = $win;
	            // 获取文档高度
	            me._scrollContentHeight = $doc.height();
	            // 获取win显示区高度  —— 这里有坑
	            me._scrollWindowHeight = doc.documentElement.clientHeight;
	        }else{
	            me.$scrollArea = me.opts.scrollArea;
	            me._scrollContentHeight = me.$element[0].scrollHeight;
	            me._scrollWindowHeight = me.$element.height();
	        }
	        fnAutoLoad(me);

	        // 窗口调整
	        $win.on('resize',function(){
	            if(me.opts.scrollArea == win){
	                // 重新获取win显示区高度
	                me._scrollWindowHeight = win.innerHeight;
	            }else{
	                me._scrollWindowHeight = me.$element.height();
	            }
	        });

	        // 绑定触摸
	        me.$element.on('touchstart',function(e){
	            if(!me.loading){
	                fnTouches(e);
	                fnTouchstart(e, me);
	            }
	        });
	        me.$element.on('touchmove',function(e){
	            if(!me.loading){
	                fnTouches(e, me);
	                fnTouchmove(e, me);
	            }
	        });
	        me.$element.on('touchend',function(){
	            if(!me.loading){
	                fnTouchend(me);
	            }
	        });
	      
	        // 加载下方
	    /*    loadDown(me);   */     
	        // 滚动页面触发加载数据
	       
	        me.$scrollArea.on('scroll',function(){
	            me._scrollTop = me.$scrollArea.scrollTop();          
	            // 滚动页面触发加载数据
	            if(me.opts.loadDownFn != '' && !me.loading && !me.isLockDown && (me._scrollContentHeight - me._threshold) <= (me._scrollWindowHeight + me._scrollTop)){
	                loadDown(me);
	            }
	        });
	    };

	    // touches
	    function fnTouches(e){
	        if(!e.touches){
	            e.touches = e.originalEvent.touches;
	        }
	    }

	    // touchstart
	    function fnTouchstart(e, me){
	        me._startY = e.touches[0].pageY;
	        // 记住触摸时的scrolltop值
	        me.touchScrollTop = me.$scrollArea.scrollTop();
	    }

	    // touchmove
	    function fnTouchmove(e, me){
	        me._curY = e.touches[0].pageY;
	        me._moveY = me._curY - me._startY;

	        if(me._moveY > 0){
	            me.direction = 'down';
	        }else if(me._moveY < 0){
	            me.direction = 'up';
	        }

	        var _absMoveY = Math.abs(me._moveY);

	        // 加载上方
	        if(me.opts.loadUpFn != '' && me.touchScrollTop <= 0 && me.direction == 'down' && !me.isLockUp){
	            e.preventDefault();

	            me.$domUp = $('.'+me.opts.domUp.domClass);
	            // 如果加载区没有DOM
	            if(!me.upInsertDOM){
	                me.$element.prepend('<div class="'+me.opts.domUp.domClass+'"></div>');
	                me.upInsertDOM = true;
	            }
	            
	            fnTransition(me.$domUp,0);

	            // 下拉
	            if(_absMoveY <= me.opts.distance){
	                me._offsetY = _absMoveY;
	                // todo：move时会不断清空、增加dom，有可能影响性能，下同
	                me.$domUp.html(me.opts.domUp.domRefresh);
	            // 指定距离 < 下拉距离 < 指定距离*2
	            }else if(_absMoveY > me.opts.distance && _absMoveY <= me.opts.distance*2){
	                me._offsetY = me.opts.distance+(_absMoveY-me.opts.distance)*0.5;
	                me.$domUp.html(me.opts.domUp.domUpdate);
	            // 下拉距离 > 指定距离*2
	            }else{
	                me._offsetY = me.opts.distance+me.opts.distance*0.5+(_absMoveY-me.opts.distance*2)*0.2;
	            }

	            me.$domUp.css({'height': me._offsetY});
	        }
	    }

	    // touchend
	    function fnTouchend(me){
	        var _absMoveY = Math.abs(me._moveY);
	        if(me.opts.loadUpFn != '' && me.touchScrollTop <= 0 && me.direction == 'down' && !me.isLockUp){
	            fnTransition(me.$domUp,300);

	            if(_absMoveY > me.opts.distance){
	                me.$domUp.css({'height':me.$domUp.children().height()});
	                me.$domUp.html(me.opts.domUp.domLoad);
	                me.loading = true;
	                me.opts.loadUpFn(me);
	            }else{
	                me.$domUp.css({'height':'0'}).on('webkitTransitionEnd mozTransitionEnd transitionend',function(){
	                    me.upInsertDOM = false;
	                    $(this).remove();
	                });
	            }
	            me._moveY = 0;
	        }
	    }

	    // 如果文档高度不大于窗口高度，数据较少，自动加载下方数据
	    function fnAutoLoad(me){
	        if(me.opts.autoLoad){
	            if((me._scrollContentHeight - me._threshold) <= me._scrollWindowHeight){
	                loadDown(me);
	            }	            
	            loadDown(me);
	        }
	    }

	    // 重新获取文档高度
	    function fnRecoverContentHeight(me){
	        if(me.opts.scrollArea == win){
	            me._scrollContentHeight = $doc.height();
	        }else{
	            me._scrollContentHeight = me.$element[0].scrollHeight;
	        }
	    }

	    // 加载下方
	    function loadDown(me){
	        me.direction = 'up';
	        me.$domDown.html(me.opts.domDown.domLoad);
	        me.loading = true;
	        me.opts.loadDownFn(me);
	    }

	    // 锁定
	    MyDropLoad.prototype.lock = function(direction){
	        var me = this;
	        // 如果不指定方向
	        if(direction === undefined){
	            // 如果操作方向向上
	            if(me.direction == 'up'){
	                me.isLockDown = true;
	            // 如果操作方向向下
	            }else if(me.direction == 'down'){
	                me.isLockUp = true;
	            }else{
	                me.isLockUp = true;
	                me.isLockDown = true;
	            }
	        // 如果指定锁上方
	        }else if(direction == 'up'){
	            me.isLockUp = true;
	        // 如果指定锁下方
	        }else if(direction == 'down'){
	            me.isLockDown = true;
	            // 为了解决DEMO5中tab效果bug，因为滑动到下面，再滑上去点tab，direction=down，所以有bug
	            me.direction = 'up';
	        }
	    };

	    // 解锁
	    MyDropLoad.prototype.unlock = function(){
	        var me = this;
	        // 简单粗暴解锁
	        me.isLockUp = false;
	        me.isLockDown = false;
	        // 为了解决DEMO5中tab效果bug，因为滑动到下面，再滑上去点tab，direction=down，所以有bug
	        me.direction = 'up';
	    };

	    // 无数据
	    MyDropLoad.prototype.noData = function(flag){
	        var me = this;
	        if(flag === undefined || flag == true){
	            me.isData = false;
	        }else if(flag == false){
	            me.isData = true;
	        }
	    };

	    // 重置
	    MyDropLoad.prototype.resetload = function(){
	        var me = this;
	        if(me.direction == 'down' && me.upInsertDOM){
	            me.$domUp.css({'height':'0'}).on('webkitTransitionEnd mozTransitionEnd transitionend',function(){
	                me.loading = false;
	                me.upInsertDOM = false;
	                $(this).remove();
	                fnRecoverContentHeight(me);
	            });
	        }else if(me.direction == 'up'){
	            me.loading = false;
	            // 如果有数据
	            if(me.isData){
	                // 加载区修改样式
	                me.$domDown.html(me.opts.domDown.domRefresh);
	                fnRecoverContentHeight(me);
	                fnAutoLoad(me);
	            }else{
	                // 如果没数据
	                me.$domDown.html(me.opts.domDown.domNoData);
	            }
	        }
	    };

	    // css过渡
	    function fnTransition(dom,num){
	        dom.css({
	            '-webkit-transition':'all '+num+'ms',
	            'transition':'all '+num+'ms'
	        });
	    }
	})(window.Zepto || window.jQuery);




 
function get_view_info(equipmentrepairId)
{
	$("#act").val('update');
	$("#faceImgDiv").html("");
    if(!epId)
    {
        alert('Error！');
        return false;
    }
    // var form_data = new Array();
    $.ajax(
            {
               /*  url: "${proPath}/status/editOne.action",
                data:{"statusId":statusId}, */
                url: "${proPath}/editEquipRepair.action",
                data:{"equipmentrepairId":equipmentrepairId},
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
                        $("#equipmentrepairId").val(data.equipmentRepairT.equipmentrepairId);
                        $("#epId").val(data.equipmentRepairT.epId);
                        $("#epHomeEquname").val(data.equipmentRepairT.epHomeEquname);
                        $("#epModelNum").val(data.equipmentRepairT.epModelNum);
                        $("#epLocation").val(data.equipmentRepairT.epLocation);
                        $("#epTypeName").val(data.equipmentRepairT.epHomeEqutype);
                        $("#eprCkTime").val(data.equipmentRepairT.eprCkTime);
                        $("#eprCkDescribe").val(data.equipmentRepairT.eprCkDescribe);
                        $("#eprCkIdName").val(data.equipmentRepairT.userIdName);
                        $("#userId").val(data.equipmentRepairT.userId);                        
                        $("#epReId").val(data.equipmentRepairT.epReIdName);
                        $("#epReKind").val(data.epReKind);
                        $("#epReDescribe").val(data.equipmentRepairT.epReDescribe);
                        $("#epReTime").val(data.equipmentRepairT.epReTime);
                        $("#epReStatus").val(data.epReStatus);
                   	    var eprCkPhoto=data.equipmentRepairT.eprCkPhoto;      
                   	    var arr= new Array();
                   	    arr= eprCkPhoto.trim().split(',');
                   	    $("#faceImgDiv").show();                                                                   
                   	    for(var k=0;k<arr.length-1;k++){
                   		 arr[k]="${returnUrl}"+arr[k];
               		    $("#faceImgDiv").append('<img alt="" class="list-img" src="'+arr[k]+'"  style="height: 80px;">');
                   	    } 
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
   

};
</script>
    
</body>

</html>