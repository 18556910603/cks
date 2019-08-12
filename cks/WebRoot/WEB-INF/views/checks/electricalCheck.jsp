<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>设备巡检</title>
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

										<h4 class="mt-0 header-title">设备巡检表</h4>
										<p class="text-muted m-b-30 font-14">用于提交设备巡检表单.</p>

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
														<!--                                                        <input type="text" class="form-control"   placeholder="设备编号"  readonly> -->
														<!-- 													   <input type="text" class="form-control"  name="epid"  id="epid"  placeholder="设备编号" value="JDY20180928-044" readonly="readonly" style="background-color: white" onchange="showPic();" > -->
														<input type="text" class="form-control" name="epId"
															id="epId" placeholder="设备编号" value=""       
															style="background-color: white" 
															onchange="showEquipment(this.value);">

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
														id="epName" placeholder="设备名称" />
												</div>
											</div>

											<div class="form-group">
												<label>设备状态</label>
												<div>
													<input type="text" class="form-control" name="statusName" style="background-color: white" readonly="readonly"
														id="statusName" placeholder="设备状态" />
												</div>
											</div>

											<div class="form-group" style="display: none">
												<label>设备状态值</label>
												<div>
													<input type="text" class="form-control" name="statusOldVal" style="background-color: white" readonly="readonly"
														id="statusOldVal" placeholder="设备状态值" />
												</div>
											</div>

											<div class="form-group">
												<label>规格型号</label>
												<div>
													<input type="text" class="form-control" name="epModelNum" style="background-color: white" readonly="readonly"
														id="epModelNum" placeholder="规格型号" />
												</div>
											</div>
											<div class="form-group">
												<label>安装地点</label>
												<div>
													<input type="text" class="form-control" name="epLocation" style="background-color: white" readonly="readonly"
														id="epLocation" placeholder="安装地点" />
												</div>
											</div>
											<div class="form-group">
												<label>设备类型</label>
												<div>
													<input type="text" class="form-control" name="epTypeName" style="background-color: white" readonly="readonly"
														id="epTypeName" placeholder="设备类型" />
												</div>
											</div>
											<div class="form-group" style="display: none">
												<label>设备类型值</label>
												<div>
													<input type="text" class="form-control" name="epType" style="background-color: white" readonly="readonly"
														id="epType" placeholder="设备类型值" />
												</div>
											</div>
											<!------------------巡检人员 -------------------------------------------------------------------------------------------->
											<div class="form-group">
												<label>巡检信息</label>
												<div>
													<hr
														style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
												</div>
											</div>

											<div class="form-group">
												<label>巡检人员</label>
												<div>
													<input type="text" class="form-control" placeholder="巡检人员"
														value="${SessionContainer.user.loginName}" name="userName" style="background-color: white" readonly="readonly"
														id="userName" />
												</div>
											</div>
											<div class="form-group" style="display: none">
												<label>巡检人员ID</label>
												<div>
													<input type="text" class="form-control"
														placeholder="巡检人员id"
														value="${SessionContainer.user.userId}" name="userId"
														id="userId" />
												</div>
											</div>
											<div class="form-group">
												<label>巡检定位</label>

												<div id="checksLocDiv" style="display: none">
													<div class="card-link" onclick="hide_checksLoc()"
														style="color: blue;">清空地址</div>
													<input type="text" class="form-control" placeholder="" style="background-color: white" readonly="readonly"
														name="checksLoc" id="checksLoc" /><br />
												</div>


<!-- onclick="show_checksLoc()"  -->
												<div align="center" class="form-control"
													  id="getLocation">
													<i class="dripicons-location"></i>获取当前位置
												</div>
											</div>

											<div class="form-group">
												<label>拍照签到</label>
												<div>
													<small class="text-muted ye">1.
														图片已设置仅允许拍照上传，防止上传历史照片作假</small>
												</div>
												<!-- 												<div class="form-control"> -->
												<%-- 													<form enctype="multipart/form-data"> --%>
												<!-- 														<input id="file-zh" name="files" type="file" multiple> -->
												<!-- 														<p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过2.0M</p> -->
												<%-- 													</form> --%>

												<!-- 												    <input id="myFile" type="file" name="myFile" class="fileloading"> -->
												
												
<!-- 												<input type="text" class="form-control" placeholder="拍照签到" -->
<!-- 													name="checksPhoto" id="checksPhoto" /> -->

												<div align="center" class="form-control"
													  id="chooseImage">
												拍照签到
												</div>
												<div id="faceImgDiv"  style="display:none"  ></div> 
												
												
<!-- 												拍照签到的图片值 -->
												<input type="text" class="form-control" placeholder="拍照签到"style="display:none"
														value="" name="checksPhoto" id="checksPhoto" />									
											</div>

											<div class="form-group">
												<label>巡检时间</label><br /> <small class="text-muted">1.
													自动获取当前时间</small>
												<div>
													<input type="text" class="form-control" placeholder="巡检时间"   style="background-color: white" readonly="readonly"
														value="" name="checksTime" id="checksTime" />
												</div>
											</div>

											<div class="form-group">
												<label>巡检注意事项</label>
												<div>
													<small class="text-muted ye">1. 当巡检出现异常时,请第一时间填写<font
														color='blue' style="font-weight: bold;">报修单</font>,并说明故障内容
													</small><br /> <small class="text-muted ye">2.
														对重点设备要上传照片的,请大家按要求上传</small>
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
											<!------------------检查分类 -------------------------------------------------------------------------------------------->
											<div class="form-group">
												<label>检查分类</label>
												<div>
													<hr
														style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
												</div>
											</div>
											<div class="form-group">
												<label>主变主体</label>
												<div>
													<select class="form-control" id="mainPart" name="mainPart">
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('mainPart')}"
															var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>油箱油位</label>
												<div>
													<select class="form-control" id="oilTank" name="oilTank">
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('oilTank')}"
															var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>运行声音</label>
												<div>
													<select class="form-control" id="voice" name="voice">
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('voice')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<!------------------检查汇总 -------------------------------------------------------------------------------------------->
											<div class="form-group">
												<label>检查汇总</label>
												<div>
													<hr
														style="height: 1px; border: none; border-top: 1px solid #E9ECEF;" />
												</div>
											</div>
											<div class="form-group">
												<label>设备状态</label>
												<div>
													<select class="form-control" id="statusNewVal" onchange="gradeChange()"
														name="statusNewVal">
														<option value="">----请选择----</option>
														<c:forEach items="${fns:getDictList('status_type')}"
															var="dict">
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
														value="" name="maintenanceDate" id="maintenanceDate" />
												</div>
											</div>
											<div class="form-group">
												<label>距离规定的保养日期剩余天数</label><br /> 
												<div>
													<input type="text" class="form-control" placeholder="剩余天数"   style="background-color: white;color: red" readonly="readonly"
														value="" name="distanceDays" id="distanceDays" />
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
													<select class="form-control" id="clear" name="clear">
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
													<select class="form-control" id="line" name="line">
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
													<select class="form-control" id="oil" name="oil">
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
												<textarea required class="form-control" rows="5"  name="maintainDescribe"  id="maintainDescribe"  ></textarea>
												</div>
											</div>											
											<div class="form-group">
												<label>实际保养时间</label><br /> <small class="text-muted">1.
													自动获取当前时间</small>
												<div>
													<input type="text" class="form-control" placeholder="实际保养时间"   style="background-color: white" readonly="readonly"
														value="" name="maintainTime" id="maintainTime" />
												</div>
											</div>											
										</div>



											<div class="form-group">
												<div>
													<button type="button" id="submit" onclick="save()"
														class="btn btn-primary waves-effect waves-light">
														提交</button>
<!-- 													<button type="reset" -->
<!-- 														class="btn btn-secondary waves-effect m-l-5">清空</button> -->

													<span style="color: red;" id="tip"></span>

												</div>
											</div>
                                            

                                        </div>
                                    </div>
                                </div> <!-- end col -->							
							
							
							<!-- end col -->
						</div>
						</form>	
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
	<script type="text/javascript"
		src="${proPath}/assets/plugins/parsleyjs/parsley.min.js"></script>

	<!-- App js -->
	<script src="${proPath}/assets/js/app.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js">
		
	</script>
	<script type="text/javascript">
		wx.config({
			debug : false,
			appId : '${ret.appId}',
			timestamp : '${ret.timestamp}',
			nonceStr : '${ret.nonceStr}',
			signature : '${ret.signature}',
			jsApiList : [ 'checkJsApi', 'scanQRCode', 'chooseImage',
					'previewImage', 'uploadImage', 'downloadImage',
					'getNetworkType', 'openLocation', 'getLocation' ]
		// 必填，需要使用的JS接口列表
		});//end_config

		wx.error(function(res) {
			alert("出错了：" + res.errMsg);
		});

		wx.ready(function() {
			wx.checkJsApi({
				jsApiList : [ 'scanQRCode' ],
				success : function(res) {
					// 以键值对的形式返回，可用的api值true，不可用为false
					// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
				}
			});

			//扫描二维码
			document.querySelector('#scanQRCode').onclick = function() {
				wx.scanQRCode({
					needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
					scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
					success : function(res) {
						var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果  
						document.getElementById("epId").value =result.substr((result.indexOf('epId=')+5) ,(result.length-1)) ;//将扫描的结果赋予到jsp对应值上  
// 						alert("扫描成功::扫描码=" + result);
						showEquipment(result);
					}
				});
			};

			// 7.2 获取当前地理位置
			document.querySelector('#getLocation').onclick = function () {
				wx.getLocation({
				  success: function (res) {
					  var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
						var longitude = res.longitude ; // 经度，浮点数，范围为180 ~ -180。
						var speed = res.speed; // 速度，以米/每秒计
						var accuracy = res.accuracy; // 位置精度
						//获取微信坐标系
						var x = res.longitude;
                  		var y = res.latitude;
    					//转换百度坐标
                  		 var url = "http://api.map.baidu.com/geoconv/v1/?coords=" + x + "," + y + "&from=1&to=5&ak=TbrOXNWjGz1nrANZgpHd1NaM7b4pTYQy";
                         $.get(url, function(data) {
                             if(data.status === 0) {
                                   lng = data.result[0].x;
                                    lat = data.result[0].y;
                                    submitOrderInfoClick(lat,lng);
                              }
                          }, 'jsonp');						
						
						//获取具体地址
						submitOrderInfoClick(latitude,longitude);
				  },
				  cancel: function (res) {
					alert('用户拒绝授权获取地理位置');
				  }
				});
			};			
			
			
			
			
		});//end_ready     
		//这个事件是得到微信经纬度去后台获取百度经纬度，google经纬度或者其它的经纬度，要知道微信经纬度是gps的和我们要使用的地图定位不一样所以这里我们需要转化	//转化 1.可以通过页面转化得到你要的地图经纬度-并且得到地址详情	//转化 2.和我一样不喜欢写前台，可以直接在后台中得到 （目的都是一样的）
		function submitOrderInfoClick(latitude,longitude) {
			//alert(latitude);
			//alert(longitude);
			var url = "${proPath}/electricalCheck/details.action";
			$.post(url, {
				latitude : latitude,
				longitude : longitude
			}, function(data) {
				if (data != null) {
					$('#checksLoc').val(data.msg);
					$('#checksLocDiv').show();
				} else {
					alert("获取地理位置失败是否重新获取");
				}
			});
		}
		
		// 5 图片接口
		// 5.1 拍照、本地选图
		var images = {
			localId: [],
			serverId: []
		  };
		var imgContainer=$("#faceImgDiv"); //通过Jquery选择器获取的Dom对象作为图片容器
		var str='';
		
		document.querySelector('#chooseImage').onclick = function () {
			wx.chooseImage({
				//count: 1, // 默认9
				//sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
				sourceType: [ 'camera'], // 可以指定来源是相册还是相机，默认二者都有
				success: function (res) {
					var localIds = res.localIds; 
					images.localId = res.localIds;// 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
// 					alert('已选择 ' + res.localIds.length + ' 张图片');
					 var i = 0, length = images.localId.length; 
		              function upload() { 
		                  wx.uploadImage({ 
		                      localId: images.localId[i], 
		                      success: function(res) { 
		                          i++; 
// 		                          alert('已上传：' + i + '/' + length); 
		                          images.serverId.push(res.serverId);
		                          // res.serverId 就是 media_id，根据它去微信服务器读取图片数据：
		                          var indata = {"media_id":res.serverId};
		                          $.post("${proPath}/electricalCheck/getPhoto.action", indata, function(data){
		          					  $("#faceImgDiv").show();
		          					  var src="${returnUrl}"+data.msg;
					                   imgContainer.append('<div class="form-control"> <a class="image-popup-vertical-fit" href="'+src+'" title=""> <img class="img-responsive form-control" src="'+src+'" > </a> </div>'         					  );		          					  
		          					  
// 					                   imgContainer.append('<img alt="" src="'+src+'"   class="form-control">');
// 	                        	      $("#faceImg").attr("src", "${returnUrl}"+data.msg);//显示图片到页面上
									  str=str+data.msg+',';
	                        	      $("#checksPhoto").val(str);
		                            },'json');
		                           
		                          if (i < length) { 
		                              upload(); 
		                          } 
		                      }, 
		                      fail: function(res) { 
		                          alert(JSON.stringify(res)); 
		                      } 
		                  }); 
		              } 
		              upload(); 					
			  }
			});
		  };	
		

		
		//时间
		document.getElementById('checksTime').value = CurentTime() ;
		    //获取当前时间

		
		function show_checksLoc() {
			$('#checksLocDiv').show();
		}
		function hide_checksLoc() {
			$('#checksLoc').val('');
			$('#checksLocDiv').hide();
		}
		// 编辑表单
		function showEquipment(epId) {
			var epId = $.trim($('#epId').val());
			if (!epId) {
				alert('Error！');
				return false;
			}
			$.ajax({
				url : "${proPath}/electricalCheck/equipOne.action",
				data : {
					"epId" : epId
				},
				type : "post",
				async : false,
				beforeSend : function() {
					// $("#tip").html("<span style='color:blue'>正在处理...</span>");
					return true;
				},
				success : function(data) {
					if (data.success==true) {
						$("#epName").val(data.obj.epName);
						$("#statusName").val(data.obj.statusName);
						$("#statusOldVal").val(data.obj.statusVal);
						$("#epModelNum").val(data.obj.epModelNum);
						$("#epLocation").val(data.obj.epLocation);
						$("#epTypeName").val(data.obj.epTypeName);
						$("#epType").val(data.obj.epType);
						
						 //规定保养时间
						 $("#maintenanceDate").val(data.obj.maintenanceDate);
						//距离规定的保养日期还剩 0 天
						$("#distanceDays").val(data.obj.distanceDays);
						//实际保养时间
						$("#maintainTime").val('');
						//明细 
						$("#clear").val('');
						//电气线路有无损坏
						$("#line").val('');
						$("#oil").val('');
						//描述
						$("#maintainDescribe").val('');
						
						
						$("#submit").removeAttr("disabled");
						$("#tip").html("<span style='color:red'></span>");
					} else {
						$("#epName").val('');
						$("#statusName").val('');
						$("#statusOldVal").val('');
						$("#epModelNum").val('');
						$("#epLocation").val('');
						$("#epTypeName").val('');
						$("#epType").val('');			
	
						 //规定保养时间
						 $("#maintenanceDate").val('');
						//距离规定的保养日期还剩 0 天
						$("#distanceDays").val('');
						//实际保养时间
						$("#maintainTime").val('');	
						  //获取当前时间		
						$("#clear").val('');
						//电气线路有无损坏
						$("#line").val('');
						$("#oil").val('');
						//描述
						$("#maintainDescribe").val('');
												  
						  
						
						$("#tip").html(
								"<span style='color:red'>"+data.msg+"</span>");
						$("#submit").attr("disabled", "disabled");
					}
					//显示保养计划
					gradeChange();
					
				},
				error : function() {
					alert('请求出错');
				},
				complete : function() {
					// $('#tips').hide();
				}
			});

			return false;
		}

		function save() {
			var epId = $.trim($('#epId').val());
			if (!epId) {
				alert('设备编号ID不能为空！');
				return false;
			}
			var form_data = $('#form_data').serialize();
			$
					.ajax({
						url : "${proPath}/electricalCheck/save.action",
						data : form_data,
						type : "post",
						async : false,
						beforeSend : function() {
							$("#tip").html("<span style='color:blue'>正在处理...</span>");
							 $("#submit").attr("disabled","disabled");
							return true;
						},
						success : function(data) {
							if (data.success == true) {
								$("#tip").html("");
								window.location.href = "${proPath}/electricalCheck/tips.action";
							} else {
								$("#tip")
										.html(
												"<span style='color:red'>失败，请重试</span>");
								alert('操作失败');
							}
						},
						error : function() {
							alert('请求出错');
						},
						complete : function() {
							//         	                	location.reload(true);
						}
					});

			//        	     return false;       	   

		}
		   //选设备状态正常并且日期在三天内或者超出日期了，出现
	       function gradeChange(){
	           var objS = document.getElementById("statusNewVal");
	           var value = objS.options[objS.selectedIndex].value;
	           //状态正常
	           if(value=='1'){
	        	   //日期
	        	  var distanceDays= $("#distanceDays").val();
	        	   if(distanceDays&&distanceDays<=3){
						//实际保养时间
					   $("#maintainTime").val(CurentDate());
	        		   $('#maintenanceDetail').show();
	        	   }else{
	        		   $('#maintenanceDetail').hide();
	        	   }
	           }else{
	        	   $('#maintenanceDetail').hide();
	           }
	          }

	</script>



</body>
</html>