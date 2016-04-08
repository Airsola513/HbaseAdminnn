<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap3/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="bootstrap3/css/bootstrap-theme.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="bootstrap3/js/jquery-1.11.2.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="bootstrap3/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<!-- <style type="text/css">
th,td{
text-align:center;

}
</style> -->
<body>


    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Hbase UI</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav><br><br><br><br>
    
    <a href=""><h4>BUG版</h4></a>
    <div class="container" >
     <a href=""><h4>已存在的表:</h4></a>
     <ul class="nav navbar-nav" id="addTables">
           <c:forEach items="${tableMap}" var="table">
           <li class="${table.key}"><a onclick="show_data('${table.key}','${table.value}')">${table.key}</a></li> 
						</c:forEach>
						
          </ul>
     
    </div>
    <br>
   
 <div class="container">
	<div class="row">
			<div class="col-sm-3">
			<form id="newTable">
				<div class="input-group">
					<input type="text" class="form-control" size="20" name="tablename" id="tablenameid" value=""
						placeholder="表名,列族名"> <span class="input-group-btn">
						<button class="btn btn-default" type="submit">创建表</button>
					</span>
				</div>
				</form>
			</div>
			<!-- /.col-lg-6 -->
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
			<form id="refluse_tables">
				<div class="input-group">
					<input type="text" class="form-control" size="20" value="" name="tablenames" id="deleteid" 
						placeholder="如果你想操作表，请输入表名..."> <span class="input-group-btn">
						<button class="btn btn-default" type="submit">查 找</button>
					</span>
				</div>
				</form>
				<!-- /input-group -->
			</div>
			<!-- /.col-lg-6 -->
		</div>
</div><br>

 <!-- <footer class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer> -->

<div class="container">
<table class="table table-bordered" >
<caption>单列族表结构(暂时是单版本的，版本数是在创表的时候指定的。时间戳随之也暂时没有展现)</caption>
  <thead>
    <tr id="createinfo">
      <th rowspan="2" >主键(key)</th>
      <!-- <th colspan="3">列族名：<div id="infos" style="display:inline-block;"></div></th> --><!-- 这里的3需要参数化，3也就表示该列族下有三个列 -->
    </tr>
      <tr id="addrow"> 
    
     
    </tr>
  </thead>
  <tbody id="addfullrow">
   <!--  <tr >
      <td>1</td>这里相当于一行 
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr> -->
   
  </tbody>
</table>
</div>
<div class="container">
<form class="form-inline" id="add_cell">
  <div class="form-group">
    <label class="sr-only" for="exampleInputEmail3">主键</label>
    <input type="text" class="form-control" id="exampleInputName2" placeholder="主键" required="" name="key0">
  </div>
  <div class="form-group">
    <label class="sr-only" for="exampleInputPassword3">列名</label>
    <input type="text" class="form-control" id="exampleInputName3" placeholder="列名" required="" name="Qualifier0">
  </div>
   <div class="form-group">
    <label class="sr-only" for="exampleInputPassword3">值</label>
    <input type="text" class="form-control" id="exampleInputName4" placeholder="值" required="" name="value0">
  </div>
   <div class="form-group" style="display: none">
    <label class="sr-only" for="exampleInputPassword3">表名</label>
    <input type="text" class="form-control" id="get_tablename" placeholder="表名"  name="tablename0" value="0">
  </div>
   <div class="form-group" style="display: none">
    <label class="sr-only" for="exampleInputPassword3">列族名</label>
    <input type="text" class="form-control" id="get_Family" placeholder="列族名"  name="Family0" value="0">
  </div>
 
  <button type="submit" class="btn btn-default disabled" id="controlid">插入数据</button>
</form>

</div>



<script type="text/javascript">
$(document).ready(function(){
	   //全局变量，用来存放页面的数据量
	   var fulllength="";
	   
	   $('#newTable').submit(function(e){
		   var ip = $('#tablenameid').val();
		   $.post('${pageContext.request.contextPath}/addTable?='+ip,$(this).serialize(),function(response){
			  // console.log(response);
			   var v=response.split(',');
			  // console.log(response);
			   if(v.length==2){
				   $('#addTables').append("<li class=\""+v[0]+"\"><a onclick=\"show_data('"+v[0]+"','"+v[1]+"')\">"+v[0]+"</a></li>" );
				   $('#infos').text(v[1]);
			   }else{
				   v=response.split('，');
				   $('#addTables').append("<li class=\""+v[0]+"\"><a onclick=\"show_data('"+v[0]+"','"+v[1]+"')\">"+v[0]+"</a></li>" );
				  /*  $('#addTables').append("<li class=\""+response+"\"><a onclick=\"show_data('"+v[0]+"')\">"+v[0]+"</a></li>" ); */
				   $('#infos').text(v[1]);
			   }
			
			
		   });
		   e.preventDefault();
	   }); 
	  
	   $('#refluse_tables').submit(function(e){
		   var tableid = $('#deleteid').val();
		   $.post('${pageContext.request.contextPath}/refluse_tables',$(this).serialize(),function(response){
			   if(response){  //删除成功，返回true就remove相应的li
				   $("li").remove("."+tableid+"");
			   }
			  
		   });
		   e.preventDefault();
	   }); 
	   
	   $('#add_cell').submit(function(e){
		  // var ip = $('#tablenameid').val();
		  
		    var ip2 = $("input[name='Family0']").val();
		    var ip1 = $("input[name='tablename0']").val();
		   $.post('${pageContext.request.contextPath}/add_cell',$(this).serialize(),function(response){
			   console.log(localStorage.getItem("num")+"+++++++++++++++++++++++++");
			   if(response&localStorage.getItem("num")<10){
				   
				   //重新请求数据，这种方法效率不好，由于..........种种原因                         改到这里--------------------------------------
				  // alert("asssssssssssssssssssssssssss");
				   
				   // console.log(result5+ip1+ip2+"000000000000000000000000000000000000");
				   show_data(ip1,ip2); 
			   }
		   });
		   e.preventDefault();
	   }); 
   });
   
   
    /* 增加表数据 接收到的数据是List<ArrayList<String>>，接下来就是渲染的事情。包括这几步：1.去重取列名 2.与当前列名匹配当前行的每个cell，要么放空，要么放值 */
function show_data(s,s1) {
	   $("tr").remove(".inn");   //重新请求另一个表数据之前要删除上一个表的数据
	   $("#controlid").attr("class","btn btn-default");
	   var show_data_tablename=s;
	   var show_data_Family=s1;
	   ///重新取得一张表的数据
	   $.post('${pageContext.request.contextPath}/getDateForTable?table='+s,$(this).serialize(),function(response){
			
			var list = response.arrayList;
			fulllength=response.arrayList.length;
			//console.log(fulllength+"-----------------------------------");
			//每次请求数据都是存着当次的页面大小，还没做分页·，要做成10条一页，要不不可以（有原因，原因就在增加cell时）
			localStorage.removeItem("num");
			localStorage.setItem("num",fulllength);
			var option = "";
			var info;
			var res=[];
			var ress=[];  //所有的列名
			var i1=0;
			$.each(list, function(i, n){ //i为序数，n为ArrayList<String>也就一行数据
			/* option += '<option value="'+ n.String +'">'+ n.String +'</option>'; */
			$.each(n,function(k,l){
				//console.log(l); //12	1458121344451	cfInfo	address	bitch  每个cell
				var v=l.split('\t');
				info = v[2];
				res[i1++]=v[3];//把所有的列名做成一个数组，以用来去重
			});
			});
			//console.log(show_data_Family+"函数外");
			ress=unique(res);     //列名去重函数
			$.each(list, function(i5, n5){ //i为序数，n为ArrayList<String>也就一行数据
				
				
				/* List<ArrayList<String>> */
				//console.log(n5);
				 $.each(unique(res),function(za,zb){   //zb里面没有重复,7size
					 var myBoolean=new Boolean(true);
					 $.each(n5,function(k5,l5){ //这就是一个一个cell
						 var v5=l5.split('\t');
						 if(option==''){
							 option+='<tr class=\"inn\"><td>"'+v5[0]+'"</td>';
						 }
						 
						 if(zb==v5[3]){
							 myBoolean=false;
							 option+='<td>"'+v5[4]+'"</td>';
							/*  break; */

						 }
					 });
					 if(myBoolean){
						 option+='<td></td>'; 
					 }
					 
				}); 
				
				
				
				
				option+='</tr>';
				$('#addfullrow').append(option);
				option='';
				//alert(option);
				});
			$("th").remove(".inn");   //以下的连个追加都是追加th，所以下次重新追加都要删了这些原先元素
			
			$('#createinfo').append("<th class=\"inn\" colspan="+ress.length+">列族名：<div id=\"infos\" style=\"display:inline-block;\"></div></th>")
			$('#infos').text(info);
			//这条数据是当取一些数据出来发现这个表是空的，也就解析不到列族名。但是列族名是存在的，所以从别的地方传进来
			$('#infos').text(s1);
		  //alert(ress);
			$.each(unique(res),function(z1,z2){
				
				 $('#addrow').append("<th class=\"inn\">"+z2+"</th>" );

				
			});
			
		   });
	   $("#get_tablename").attr("value",show_data_tablename);
	   $("#get_Family").attr("value",show_data_Family);
	  
	   //去重函数
	   function unique(arr) {
		    var result = [], hash = {};
		    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
		        if (!hash[elem]) {
		            result.push(elem);
		            hash[elem] = true;
		        }
		    }
		    return result;
	
		}
}
   
   
   
   
   </script>


</body>
</html>