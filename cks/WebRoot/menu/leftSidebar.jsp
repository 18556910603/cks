<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body >
        <!-- ========== Left Sidebar Start ========== -->
        <div class="left side-menu" id="leftDiv">
            <button type="button" class="button-menu-mobile button-menu-mobile-topbar open-left waves-effect">
                <i class="ion-close"></i>
            </button>

            <!-- LOGO -->
            <div class="topbar-left">
                <div class="text-center">
                    <!--<a href="main.jsp" class="logo">Admiry</a>-->
<%--                     <a href="${proPath}/main.jsp" class="logo"><img src="${proPath}/assets/images/logo.png" height="33" alt="logo"></a> --%>
 				<h4 class="font-16 text-white">OMMS</h4>
                </div>
            </div>

            <div class="sidebar-inner slimscrollleft">

                <div class="user-details">
                    <div class="text-center">
                        <img src="${proPath}/assets/images/users/avatar-6.jpg" alt="" class="rounded-circle">
                    </div>
                    <div class="user-info">
<!--                   		  <#if (SessionContainer.fontUser)??> -->
<%--                   		  <h4 class="font-16 text-white">欢迎您 ：【${(SessionContainer.account.accLogin)!""}】</h4></#if> --%>
                            <h4 class="font-16 text-white">欢迎您 ：</h4>
                            <h4 class="font-16 text-white">${SessionContainer.user.userName}${SessionContainer.user.identity}</h4>
                            <span class="text-white"><i class="fa fa-dot-circle-o text-success"></i> 在线</span>
                    </div>
                </div>

            <div id="sidebar-menu">

                </div>
                <div class="clearfix"></div>
            </div>
            <!-- end sidebarinner -->
        </div>
        <!-- Left Sidebar End -->
</body>
   <!-- 公共js -->
 	<%@ include file="/common/common.jspf"%>
    <script src="${proPath}/assets/js/sidebar-menu.js"></script>
	<script type="text/javascript">
    var isFirstMenu;
        menulist=${SessionContainer.menutree}; 
        type=${SessionContainer.user.type};
        $(window).load(function(){
                var showlist = $("<ul class=\"sidebar-menu\"></ul>");
                $("<li></li>").appendTo(showlist);
            isFirstMenu=menulist.extend.children.length;
            showall(menulist.extend.children, showlist);
            $("#sidebar-menu").append(showlist);
            if(type=="2"){
            	 $("#leftDiv").css("background","#089188");
            	 $("#sidebar-menu").css("background","#089188");
            } 
        });
         function showall(menu_list, parent) {
            for (var menu in menu_list) {
                if (menu_list[menu].children.length > 0) {
                    var li = $("<li></li>");
                    if(isFirstMenu==0){
                        li = $("<li></li>");
                    }else{
                        li = $("<li class=\"has_sub\"></li>");
                        isFirstMenu=isFirstMenu-1;
                    }
                    $(li).append("<a href=\"javascript:void(0);\" class=\"waves-effect\"><i class=\"ti-folder\"></i> <span>"+menu_list[menu].text+"</span><span class=\"pull-right\"><i class=\"mdi mdi-chevron-right\"></i></span></a>");
                    var nextParent=$("<ul class=\"list-unstyled\"></ul>");
                    $(nextParent).appendTo(li);
                    $(li).appendTo(parent);
                    showall(menu_list[menu].children, nextParent);
                    
                }
                else {
                	var text=menu_list[menu].text.trim();
/*               	var count=${count};
                 	if(text=='业主-待办任务'){  
                  	  $("<li><a href='"+menu_list[menu].url+"' class=\"waves-effect\"><i class=\"ti-file\"><span class='badge badge-danger float-right' id='tongzhi-content'>"+count+"</span></i>"
                              +menu_list[menu].text
                              +"</a></li>").appendTo(parent);
                	}
                	else{
                	  $("<li><a href='"+menu_list[menu].url+"' class=\"waves-effect\"><i class=\"ti-file\"></i>"
                        +menu_list[menu].text
                        +"</a></li>").appendTo(parent);
                	}                	                	
 */               

                  $("<li><a href='"+menu_list[menu].url+"' data-ajax=\"false\"  rel=\"external\" class=\"waves-effect\"><i class=\"ti-file\"></i>"
                           +menu_list[menu].text+"</a></li>").appendTo(parent);

                }
            }
        }
         
       //消息局部定时刷新
/*          setTimeout(function(){
             Push();
         },200);
         setInterval(function(){
             Push();
         },3000);
         function Push(){
             $.ajax({
                 type:"POST",
                 url:"getsum.action?dt=" + new Date().getTime(),//why getTime and wont use
                 data:{},
                 beforeSend:function(){},
                 success: function(data){
                     var obj=eval("("+data+")");//eval使用前要先加括号，才能得到完整的json数据
                     if(obj.msg!=0){
                         $("#tongzhi-content").html(obj.msg);//更新提示内容中的数量部分
                         $('##tongzhi-content').panel('open').panel('refresh');
                     }else{
                     }
                 }
             });
         } */
         
	</script> 
</html>