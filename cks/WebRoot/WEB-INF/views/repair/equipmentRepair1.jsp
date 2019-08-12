<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <title>巡检-设备报修单页面</title>
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
    <link href="${proPath}/assets/css/fileinput.min.css" rel="stylesheet" type="text/css">

    <style type="text/css">
        .xy {color:#868E96 !important;}
        /*黄色提醒*/
        .ye {
            color:#F2AD4E !important;
            font-weight:bold;
        }
        /*按钮禁止以后置灰色 */
        button:disabled {
            color:#e3e3e3 !important;
            border:1px solid #e3e3e3 !important;
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
                    <form class="" action="" onsubmit="" id="form_data" method="post">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="card m-b-30">
                                    <div class="card-body">

                                        <h4 class="mt-0 header-title">巡检报修单</h4>
                                        <p class="text-muted m-b-30 font-14">用于巡检报修提交的工单</p>

                                        <!------------------故障报修 -------------------------------------------------------------------------------------------->

                                        <div>
                                            <div class="form-group" >
                                                <label>故障报修</label>
                                                <div>
                                                    <hr style="height:1px;border:none;border-top:1px solid #E9ECEF;" />
                                                </div>
                                            </div>

                                            <div class="form-group" >
                                                <label>设备编号</label>
                                                <div><small class="text-muted xy">扫码获取设备编号</small></div>
                                                <div>
                                                    <div class="input-group"  >
                                                        <!--                                                        <input type="text" class="form-control"   placeholder="设备编号"  readonly> -->
                                                        <!-- 													   <input type="text" class="form-control"  name="epid"  id="epid"  placeholder="设备编号" value="JDY20180928-044" readonly="readonly" style="background-color: white" onchange="showPic();" > -->
                                                        <input type="text" class="form-control" value="00817"  name="epId"  id="epId"  placeholder="设备编号" value=""  style="background-color: white" onchange="showEquipment();" style="background-color: white"  >
                                                        <div class="input-group-append bg-custom b-0"><span class="input-group-text"><i class="mdi mdi-qrcode-scan" id='scanQRCode'></i></span></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label>设备名称</label>
                                                <div>
                                                    <input type="text" class="form-control" name="epName"  id="epName"   placeholder="设备名称"  style="background-color: white"  />
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label>规格型号</label>
                                                <div>
                                                    <input type="text" class="form-control" name="epModelNum"  id="epModelNum"   placeholder="规格型号"  style="background-color: white" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>安装地点</label>
                                                <div>
                                                    <input  type="text" class="form-control"   name="epLocation"  id="epLocation" placeholder="安装地点"  style="background-color: white" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>设备类型</label>
                                                <div>
                                                    <input  type="text" class="form-control"  name="epTypeName"  id="epTypeName"  placeholder="设备类型"  style="background-color: white" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end col -->

                            <div class="col-lg-6">
                                <div class="card m-b-30">
                                    <div class="card-body">

                                        <!-- 										<h4 class="mt-0 header-title">巡检报修单</h4> -->
                                        <p class="text-muted m-b-30 font-14"></p>

                                        <!------------------故障报修 -------------------------------------------------------------------------------------------->

                                        <div>
                                            <div class="form-group">
                                                <label>报修时间</label>
                                                <div>
                                                    <input  type="text" class="form-control"   name="eprCkTime"  id="eprCkTime" placeholder="报修时间"  style="background-color: white"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>故障情况拍照</label>
                                                <div><small class="text-muted ye">1. 启用了「仅允许拍照上传」功能，该功能只支持微信服务号</small></div>
                                                <div align="center" class="form-control"
                                                     id="chooseImage">
                                                    故障情况拍照
                                                    <%--<input id="inputs" type="file" name="image" multiple accept="image/*" capture="user">111--%>
                                                </div>
                                                <div id="faceImgDiv"  style="display:none"></div>


                                                <!-- 												拍照签到的图片值 -->
                                                <%--<input type="text" class="form-control" placeholder="拍照签到"style="display:none"--%>
                                                       <%--value="" name="eprCkPhoto" id="eprCkPhoto" />--%>
                                                <input type="file" multiple id="inputs" accept="image/*" capture="user"/>

                                            </div>
                                            <div class="form-group">
                                                <label>故障情况描述</label>
                                                <div>
                                                    <textarea required class="form-control" rows="5"  name="eprCkDescribe"  id="eprCkDescribe"  ></textarea>
                                                    <!-- 													<input  type="text" class="form-control"   name="eprCkDescribe"  id="eprCkDescribe" placeholder="故障情况描述" /> -->
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>报修人</label>
                                                <div>
                                                    <input  type="text" class="form-control"   name="eprCkIdName"  id="eprCkIdName"   value="${SessionContainer.user.loginName}"     placeholder="报修人" readonly="readonly" style="background-color: white"/>
                                                </div>
                                            </div>


                                            <div class="form-group" style="display:none	">
                                                <label>报修人ID</label>
                                                <div>
                                                    <input  type="text" class="form-control"   name="userId"  id="userId" value="${SessionContainer.user.userId}"  placeholder="报修人ID" />
                                                </div>
                                            </div>


                                        </div>
                                        <!------------------维修派工-------------------------------------------------------------------------------------------->
                                        <div style="display:none">
                                            <div class="form-group" >
                                                <label>维修派工</label>
                                                <div>
                                                    <hr style="height:1px;border:none;border-top:1px solid #E9ECEF;" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>故障类别</label>
                                                <div>
                                                    <input  type="text" class="form-control"   name="epReKind"  id="epReKind" placeholder="故障类别" />
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label>维修责任人</label>
                                                <div>
                                                    <input  type="text" class="form-control"   name="epReId"  id="epReId" placeholder="维修责任人" />
                                                </div>
                                            </div>
                                        </div>
                                        <!------------------维修实施-------------------------------------------------------------------------------------------->
                                        <div style="display:none">
                                            <div class="form-group" >
                                                <label>维修实施</label>
                                                <div>
                                                    <hr style="height:1px;border:none;border-top:1px solid #E9ECEF;" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>维修情况说明</label>
                                                <div>
                                                    <input  type="text" class="form-control"   name="epReDescribe"  id="epReDescribe" placeholder="维修情况说明" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>维修完成时间</label>
                                                <div>
                                                    <input  type="text" class="form-control"   name="epReTime"  id="epReTime" placeholder="维修完成时间" />
                                                </div>
                                            </div>
                                        </div>
                                        <!------------------维修确认-------------------------------------------------------------------------------------------->
                                        <div style="display:none">
                                            <div class="form-group" >
                                                <label>维修确认</label>
                                                <div>
                                                    <hr style="height:1px;border:none;border-top:1px solid #E9ECEF;" />
                                                    <div><small class="text-muted ye">维修人员已确认修复故障</small></div>
                                                    <div><small class="text-muted ye">请进行设备检查确认</small></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label>设备状态</label>
                                                <div>
                                                    <select class="form-control" id="epReStatus" name="epReStatus">
                                                        <c:forEach items="${fns:getDictList('status_type')}" var="dict">
                                                            <option value="${dict.value}">${dict.label}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div>
                                                <!-- 													<div class="alert alert-danger" style="background-color: white;"> -->
                                                <!-- 														<strong></strong> -->
                                                <!-- 													</div> -->
                                                <button type="button" id="submit" onclick="save()"
                                                        class="btn btn-primary waves-effect waves-light">
                                                    提交</button>

                                                <span style="color: red;"  id="tip"></span>

                                            </div>
                                        </div>




                                    </div>
                                </div>
                            </div>




                        </div>






                        <!-- end row -->
                    </form>
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
<!-- App js -->
<script src="${proPath}/assets/js/app.js"></script>
<!-- Dropzone js -->
<script src="${proPath}/assets/plugins/dropzone/dist/dropzone.js"></script>
<script src="${proPath}/assets/js/fileinput.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        // var form = new FormData();
        // var logo_file = document.getElementById("inputs");
        // console.log("logo_file:"+logo_file);
        // form.append("logos",logo_file.files[0]);

        // var formData = new FormData();
        $("#inputs").change(function () {
            var form = new FormData();
            var logo_file = document.getElementById("inputs");
            console.log("logo_file:"+logo_file.files[0]);
            form.append("logos",logo_file.files[0]);
            var fil = this.files;
            // formData.append("multifiles",fil);
            alert("获取图片名称"+this.files[0].name);
            console.log(fil.length);
            for (var i = 0; i < fil.length; i++) {
                console.log(fil[i]);
                console.log('--------------------------');
                reads(fil[i]);
            }

            $.ajax({
                type: "post",
                url: "${proPath}/electricalCheck/getPhotos.action",
                data: form,
                contentType: false, // 注意这里应设为false
                processData: false,    //false
                cache: false,    //缓存
                success: function(data){
                    console.log(data);
                    console.log("wl-tpsc-success");
                },
                error:function () {
                    console.log("error ajax");
                }
            })

            //$filePath=URL.createObjectURL(this.files[0]);
            //这是获取到图片元素，因为我这里的页面结构是统一的，input表单的上一个元素是img，
            //所以这个地方只是为了获取你的img元素，然后设置src属性即可
            //$(this).prev().attr('src',$filePath);
            //alert("formdata:"+form.get("multifiles"))
        });

    });

    function reads(fil){
        var reader = new FileReader();
        reader.readAsDataURL(fil);

        reader.onload = function(){
            var data=reader.result;
            alert("111");
            //document.getElementById("dd").attr('src',reader.result);
            //document.getElementById("dd").innerHTML += "<img src='"+reader.result+"'>";
            //$('#inputs').after("<img src='"+reader.result+"'>");
            alert(data);
            $("#faceImgDiv").show();
            // imgContainer.append('<img src="'+data+'">');
            document.getElementById("faceImgDiv").innerHTML += "<img src='"+reader.result+"'>";
        };
    }s
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




    });//end_ready
    // 5 图片接口
    // 5.1 拍照、本地选图
    var images = {
        localId: [],
        serverId: []
    };
    var imgContainer=$("#faceImgDiv"); //通过Jquery选择器获取的Dom对象作为图片容器
    var str='';

    document.querySelector('#chooseImage').onclick = function () {
        var constraints = {
            video: {width: 500, height: 500},
            audio: true
        };
        //获得video摄像头区域
        var video = document.getElementById("video");
        //这里介绍新的方法，返回一个 Promise对象
        // 这个Promise对象返回成功后的回调函数带一个 MediaStream 对象作为其参数
        // then()是Promise对象里的方法
        // then()方法是异步执行，当then()前的方法执行完后再执行then()内部的程序
        // 避免数据没有获取到
        var promise = navigator.mediaDevices.getUserMedia(constraints);
        promise.then(function (MediaStream) {
            video.srcObject = MediaStream;
            video.play();
        });
    };





    //时间
    document.getElementById('eprCkTime').value = CurentTime() ;
    //获取当前时间

    $(document).ready(function() {
        $('form').parsley();
    });
    //查询设备基础信息
    function showEquipment(epId)
    {
        var epId = $.trim($('#epId').val());
        if(!epId)
        {
            alert('Error！');
            return false;
        }
        $.ajax(
            {
                url: "${proPath}/equipmentRepair/equipOne.action",
                data:{"epId":epId},
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
                        $("#epName").val(data.obj.epName);
                        $("#epModelNum").val(data.obj.epModelNum);
                        $("#epLocation").val(data.obj.epLocation);
                        $("#epTypeName").val(data.obj.epTypeName);
                        $("#submit").removeAttr("disabled");
                        $("#tip").html("<span style='color:red'></span>");
                        if(data.success==true){
                            $("#submit").removeAttr("disabled");
                        }else{
                            $("#submit").attr("disabled","disabled");
                            alert(data.msg);
                        }

                    }
                    else
                    {
                        $("#tip").html("<span style='color:red'>当前设备不存在，请重试</span>");
                        $("#submit").attr("disabled","disabled");

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

    function save()
    {
        var epId = $.trim($('#epId').val());
        if(!epId)
        {
            alert('设备编号ID不能为空！');
            return false;
        }
        var form_data = $('#form_data').serialize();
        $.ajax(
            {
                url: "${proPath}/equipmentRepair/submit.action",
                data:form_data,
                type: "post",
                async:false,
                beforeSend:function()
                {
                    $("#tip").html("<span style='color:blue'>正在处理...</span>");
                    $("#submit").attr("disabled","disabled");
                    return true;
                },
                success:function(data)
                {
                    if(data.success==true)
                    {
//      	                        $("#tip").html("<span style='color:blueviolet'>" +data.msg+ "</span>");
//      	                        alert(data.msg);
                        window.location.href="${proPath}/electricalCheck/tips.action";
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
//      	                	location.reload(true);
                }
            });

//     	     return false;





    }











</script>



</body>
</html>