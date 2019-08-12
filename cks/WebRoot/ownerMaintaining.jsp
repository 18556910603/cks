<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>业主设备报修</title>
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
    *{margin: 0;padding: 0;}
    li{list-style-type: none;}
    a,input{outline: none;-webkit-tap-highlight-color:rgba(0,0,0,0);}
    #choose{display: none;}
    canvas{width: 100%;border: 1px solid #000000;}
    #upload{display: block;margin: 10px;height: 60px;text-align: center;line-height: 60px;border: 1px solid;border-radius: 5px;cursor: pointer;}
    .touch{background-color: #ddd;}
    .img-list{margin: 10px 5px;}
    .img-list li{position: relative;display: inline-block;width: 100px;height: 100px;margin: 5px 5px 20px 5px;border: 1px solid rgb(100,149,198);background: #fff no-repeat center;background-size: cover;}
    .progress{position: absolute;width: 100%;height: 20px;line-height: 20px;bottom: 0;left: 0;background-color:rgba(100,149,198,.5);}
    .progress span{display: block;width: 0;height: 100%;background-color:rgb(100,149,198);text-align: center;color: #FFF;font-size: 13px;}
    .size{position: absolute;width: 100%;height: 15px;line-height: 15px;bottom: -18px;text-align: center;font-size: 13px;color: #666;}
    .tips{display: block;text-align:center;font-size: 13px;margin: 10px;color: #999;}
    .pic-list{margin: 10px;line-height: 18px;font-size: 13px;}
    .pic-list a{display: block;margin: 10px 0;}
    .pic-list a img{vertical-align: middle;max-width: 30px;max-height: 30px;margin: -4px 0 0 10px;}
  </style>

    </head>
    
    <body class="fixed-left" onbeforeunload="goodbye()">

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
							<div class="col-lg-12">
								<div class="card m-b-30">
									<div class="card-body">

							   <h4 class="mt-0 header-title">业主报修单</h4>
							  <form  method="post" action="" class="form-horizontal"
								role="form" id="form_data" >                                            
							   
                                 <div class="row">
                                     <div class="col-md-6">
                                       <div class="p-20">
                                             <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">设备类型</label>
                                                    <div class="col-sm-9">
                                                    <select class="form-control" id="epHomeEqutype" name="epHomeEqutype" onchange="findtemplate(this.options[this.options.selectedIndex].value);">
														<option value="">--请选择--</option>                                             
														<c:forEach items="${fns:getDictList('ep_home_equtype')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>    
                                                   </div>
                                              </div>	
                                              <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">设备名称</label>
                                                    <div class="col-sm-9">
                                                    <select class="form-control" name="epHomeEquname" id="epHomeEquname">
														<option value="">--请选择---</option>									                                     
													</select>
                                                   </div>                                                                                                    
                                                </div>	
 
                                                 <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">所属小区</label>
                                                    <div class="col-sm-9">
                                                    <input class="form-control" type="text" value="${fns:getDictLabel(owner.ownLoc,'own_loc',owner.ownLoc)}" name="ownLoc" id="ownLoc"readonly="readonly">
                                                   </div>                                                                                                    
                                                </div>	
                                                                                               
                                                <div class="form-group row">
                                                    <label for="example-text-input" class="col-sm-3 col-form-label">业主地址</label>
                                                    <div class="col-sm-9">
                                                    <input class="form-control" type="text" value="${owner.ownLocation }" name="ownLocation" id="ownLocation" readonly="readonly">
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
												<input  type="text" class="form-control"   value="<%=now %>" name="eprCkTime"  id="eprCkTime"  readonly="readonly"/>
													
												</div>											
											</div>
											<div class="form-group row">
												<label  for="example-text-input" class="col-sm-3 col-form-label">报修人</label>												
												<div class="col-sm-9">
													<input  type="text"  class="form-control"  value="${SessionContainer.user.userName}" name="userId"  id="userId"/>
												</div>
											</div>
                                        </div>
                                     </div>
                                 <div class="col-md-6">
                                     <div class="p-20">
                                        
                                         	<div class="form-group row">
												<label  for="example-text-input" class="col-sm-3 col-form-label">报修人电话</label>												
												<div class="col-sm-9">
													<input  type="text"  class="form-control"  value="${SessionContainer.user.mobile}" name="mobile"  id="mobile"/>
												</div>
											</div>		
											<div class="form-group row">
												<label  for="example-text-input" class="col-sm-3 col-form-label">故障情况拍照</label>
												<div  class="col-sm-9">
												 <!--  <input type="file" accept="image/*"  capture="camera" name="files"  id="files" mutiple /> -->
												 <input type="file" id="choose" accept="image/*"  capture="camera" name="files" multiple>
                                                 <ul class="img-list"></ul>
                                                 <a id="upload">上传图片</a>
                                                 <span class="tips">只允许上传jpg、png及gif</span>
												</div> 
											</div>	
											<div class="form-group row">
												<label  for="example-text-input" class="col-sm-3 col-form-label">故障情况描述</label>
												<div  class="col-sm-9">
													<input  type="text" class="form-control"   placeholder="暂无内容" name="eprCkDescribe"  id="eprCkDescribe"/>
												</div>
											</div>		
											<div class="form-group row">
												<label  for="example-text-input" class="col-sm-3 col-form-label">故障等级</label>
												<div  class="col-sm-9">		
												    <select class="form-control" id="eprCkLevel" name="eprCkLevel">
														<option value="">--请选择--</option>                                             
														<c:forEach items="${fns:getDictList('epr_ck_level')}" var="dict">
															<option value="${dict.value}">${dict.label}</option>
														</c:forEach>
													</select>
												
												</div>
											</div>
  									        <div class="form-group row">
												
												 <div  class="col-sm-9">
												 </div>
												 <div  class="col-sm-3">
												  
													<button type="reset" onclick="javascript:window.location.reload(true);" class="btn btn-secondary waves-effect  m-l-5">清空</button>
													  <button type="button" id="submit" onclick="save()" class="btn btn-primary waves-effect pull-right  waves-light">提交</button>
													<span style="color: red;"  id="tip"></span>
												 </div>
												
											</div>
										</form>
									</div>
								 </div>
							  </div>
							</div>
						  </div>
					  <!-- end col -->
					   </div>
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
            
            function findtemplate(v) {
                $.ajax({
                    type : "post",
                    async : false,
                    url : "${proPath}/getPostTemplateJson.action",
                    data : {
                        'epHomeEqutype' : v
                    },
                    dataType : "json",
                    success : function(msg) {
                        $("#epHomeEquname").empty();
                        $("#epHomeEquname").html("<option value=''>--请选择--</option>");
                        if (msg.length > 0) {
                            for (var i = 0; i < msg.length; i++) {
                                    var partId = msg[i].value;
                                    var partName = msg[i].label;
                                    var $option = $("<option>").attr({
                                        "value" : partId
                                    }).text(partName);
                                    $("#epHomeEquname").append($option);
                            }
                            
                            $("#epHomeEquname").change();

                        }
                    },
                    error : function(json) {
                        $.jBox.alert("网络异常！");
                    }
                });
            } 
            
           function save() {
         	    var epHomeEquname = $.trim($('#epHomeEquname').val());
         	    if(!epHomeEquname)
         	    {
         	        alert('设备名称不能为空！');
         	        return false;
         	    }
         	   var form_data = $('#form_data').serialize();
         	    $.ajax(
         	            {
         	                url: "${proPath}/ownerAddRepair.action",
         					data: form_data,
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
         	                }
         	            });

//         	     return false;       	   
                }
            
            
        </script>
        <script>
  var filechooser = document.getElementById("choose");
  //    用于压缩图片的canvas
  var canvas = document.createElement("canvas");
  var ctx = canvas.getContext('2d');
  //    瓦片canvas
  var tCanvas = document.createElement("canvas");
  var tctx = tCanvas.getContext("2d");
  var maxsize = 100 * 1024;
  $("#upload").on("click", function() {
        filechooser.click();
      })
      .on("touchstart", function() {
        $(this).addClass("touch")
      })
      .on("touchend", function() {
        $(this).removeClass("touch")
      });
  filechooser.onchange = function() {
    if (!this.files.length) return;
    var files = Array.prototype.slice.call(this.files);
    if (files.length > 9) {
      alert("最多同时只可上传9张图片");
      return;
    }
    files.forEach(function(file, i) {
      if (!/\/(?:jpeg|png|gif)/i.test(file.type)) return;
      var reader = new FileReader();
      var li = document.createElement("li");
//          获取图片大小
      var size = file.size / 1024 > 1024 ? (~~(10 * file.size / 1024 / 1024)) / 10 + "MB" : ~~(file.size / 1024) + "KB";
      li.innerHTML = '<div class="progress"><span></span></div><div class="size">' + size + '</div>';
      $(".img-list").append($(li));
      reader.onload = function() {
        var result = this.result;
        var img = new Image();
        img.src = result;
        $(li).css("background-image", "url(" + result + ")");
        //如果图片大小小于100kb，则直接上传
        if (result.length <= maxsize) {
          img = null;
          upload(result, file.type, $(li));
          return;
        }
//      图片加载完毕之后进行压缩，然后上传
        if (img.complete) {
          callback();
        } else {
          img.onload = callback;
        }
        function callback() {
          var data = compress(img);
          upload(data, file.type, $(li));
          img = null;
        }
      };
      reader.readAsDataURL(file);
    })
  };
  //    使用canvas对大图片进行压缩
  function compress(img) {
    var initSize = img.src.length;
    var width = img.width;
    var height = img.height;
    //如果图片大于四百万像素，计算压缩比并将大小压至400万以下
    var ratio;
    if ((ratio = width * height / 4000000) > 1) {
      ratio = Math.sqrt(ratio);
      width /= ratio;
      height /= ratio;
    } else {
      ratio = 1;
    }
    canvas.width = width;
    canvas.height = height;
//        铺底色
    ctx.fillStyle = "#fff";
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    //如果图片像素大于100万则使用瓦片绘制
    var count;
    if ((count = width * height / 1000000) > 1) {
      count = ~~(Math.sqrt(count) + 1); //计算要分成多少块瓦片
//            计算每块瓦片的宽和高
      var nw = ~~(width / count);
      var nh = ~~(height / count);
      tCanvas.width = nw;
      tCanvas.height = nh;
      for (var i = 0; i < count; i++) {
        for (var j = 0; j < count; j++) {
          tctx.drawImage(img, i * nw * ratio, j * nh * ratio, nw * ratio, nh * ratio, 0, 0, nw, nh);
          ctx.drawImage(tCanvas, i * nw, j * nh, nw, nh);
        }
      }
    } else {
      ctx.drawImage(img, 0, 0, width, height);
    }
    //进行最小压缩
    var ndata = canvas.toDataURL('image/jpeg', 0.1);
    console.log('压缩前：' + initSize);
    console.log('压缩后：' + ndata.length);
    console.log('压缩率：' + ~~(100 * (initSize - ndata.length) / initSize) + "%");
    tCanvas.width = tCanvas.height = canvas.width = canvas.height = 0;
    return ndata;
  }
  //    图片上传，将base64的图片转成二进制对象，塞进formdata上传
  function upload(basestr, type, $li) {
    var text = window.atob(basestr.split(",")[1]);
    var buffer = new Uint8Array(text.length);
    var pecent = 0, loop = null;
    for (var i = 0; i < text.length; i++) {
      buffer[i] = text.charCodeAt(i);
    }
    var blob = getBlob([buffer], type);
    var xhr = new XMLHttpRequest();
    var formdata = getFormData();
    formdata.append('imagefile', blob);
    xhr.open('post', '${proPath}/upload.action');
    xhr.onreadystatechange = function() {
      if (xhr.readyState == 4 && xhr.status == 200) {
        var jsonData = JSON.parse(xhr.responseText);
        var imagedata = jsonData[0] || {};
        var text = imagedata.path ? '上传成功' : '上传失败';
        console.log(text + '：' + imagedata.path);
        clearInterval(loop);
        //当收到该消息时上传完毕
        $li.find(".progress span").animate({'width': "100%"}, pecent < 95 ? 200 : 0, function() {
          $(this).html(text);
        });
        if (!imagedata.path) return;
        $(".pic-list").append('<a href="' + imagedata.path + '">' + imagedata.name + '（' + imagedata.size + '）<img src="' + imagedata.path + '" /></a>');
      }
    };
    //数据发送进度，前50%展示该进度
    xhr.upload.addEventListener('progress', function(e) {
      if (loop) return;
      pecent = ~~(100 * e.loaded / e.total) / 2;
      $li.find(".progress span").css('width', pecent + "%");
      if (pecent == 50) {
        mockProgress();
      }
    }, false);
    //数据后50%用模拟进度
    function mockProgress() {
      if (loop) return;
      loop = setInterval(function() {
        pecent++;
        $li.find(".progress span").css('width', pecent + "%");
        if (pecent == 99) {
          clearInterval(loop);
        }
      }, 100)
    }
    xhr.send(formdata);
  }
  /**
   * 获取blob对象的兼容性写法
   * @param buffer
   * @param format
   * @returns {*}
   */
  function getBlob(buffer, format) {
    try {
      return new Blob(buffer, {type: format});
    } catch (e) {
      var bb = new (window.BlobBuilder || window.WebKitBlobBuilder || window.MSBlobBuilder);
      buffer.forEach(function(buf) {
        bb.append(buf);
      });
      return bb.getBlob(format);
    }
  }
  /**
   * 获取formdata
   */
  function getFormData() {
    var isNeedShim = ~navigator.userAgent.indexOf('Android')
        && ~navigator.vendor.indexOf('Google')
        && !~navigator.userAgent.indexOf('Chrome')
        && navigator.userAgent.match(/AppleWebKit\/(\d+)/).pop() <= 534;
    return isNeedShim ? new FormDataShim() : new FormData()
  }
  /**
   * formdata 补丁, 给不支持formdata上传blob的android机打补丁
   * @constructor
   */
  function FormDataShim() {
    console.warn('using formdata shim');
    var o = this,
        parts = [],
        boundary = Array(21).join('-') + (+new Date() * (1e16 * Math.random())).toString(36),
        oldSend = XMLHttpRequest.prototype.send;
    this.append = function(name, value, filename) {
      parts.push('--' + boundary + '\r\nContent-Disposition: form-data; name="' + name + '"');
      if (value instanceof Blob) {
        parts.push('; filename="' + (filename || 'blob') + '"\r\nContent-Type: ' + value.type + '\r\n\r\n');
        parts.push(value);
      }
      else {
        parts.push('\r\n\r\n' + value);
      }
      parts.push('\r\n');
    };
    // Override XHR send()
    XMLHttpRequest.prototype.send = function(val) {
      var fr,
          data,
          oXHR = this;
      if (val === o) {
        // Append the final boundary string
        parts.push('--' + boundary + '--\r\n');
        // Create the blob
        data = getBlob(parts);
        // Set up and read the blob into an array to be sent
        fr = new FileReader();
        fr.onload = function() {
          oldSend.call(oXHR, fr.result);
        };
        fr.onerror = function(err) {
          throw err;
        };
        fr.readAsArrayBuffer(data);
        // Set the multipart content type and boudary
        this.setRequestHeader('Content-Type', 'multipart/form-data; boundary=' + boundary);
        XMLHttpRequest.prototype.send = oldSend;
      }
      else {
        oldSend.call(this, val);
      }
    };
  }

</script>

<script type="text/javascript">   

window.onbeforeunload=function(e){

	$.ajax(
	            {
	                url: "${proPath}/clearSession.action",
					data: "",
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
	               //     alert(data.msg);   
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
	                	//location.reload(true);
	                }
	            });

//	     return false;       	   
    }

	

</script>
 
      

        <!-- App js -->
        <script src="${proPath}/assets/js/app.js"></script>
    </body>
</html>