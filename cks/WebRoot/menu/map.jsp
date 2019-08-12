<!-- © 2018  志品（福州）技术工程有限公司-->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
 	
<style type="text/css">	
	body {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{width:100%;height:500px;}
		p{margin-left:2px; font-size:14px;}
		

</style>
  <script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=TbrOXNWjGz1nrANZgpHd1NaM7b4pTYQy"></script>

</head>
<body >
      <div id="allmap"></div>
      
      <script type="text/javascript">
	// 百度地图API功能
	
	var address= '${equipment.address}';
	var map = new BMap.Map("allmap");          
	map.centerAndZoom(new BMap.Point(120.58, 31.30), 11);
	var local = new BMap.LocalSearch(map, {
		renderOptions:{map: map}
	});
	local.search(address);

	setTimeout(function(){
		map.setZoom(14);   
	}, 2000);  //2秒后放大到14级
	map.enableScrollWheelZoom(true);
</script>
      
</body>

	
</html>