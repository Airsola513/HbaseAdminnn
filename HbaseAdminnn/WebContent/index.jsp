<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap3/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="bootstrap3/css/bootstrap-theme.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="bootstrap3/js/jquery-1.11.2.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="bootstrap3/js/bootstrap.min.js"></script>
<style type="text/css">
.Box1{
	width: 300px;
}
</style>
</head>
<body>
 <div class="container Box1">
<!--action这里暂时不要使用命名空间，因为碰到静态资源加上命名空间导致出错  -->
      <form class="form-signin" action="main" method="post">
        <h2 class="form-signin-heading">Hbase UI</h2>
        <label for="inputEmail" class="sr-only">请输入 Hbase 集群IP</label>
        <input  id="inputEmail" class="form-control" placeholder="请输入 Hbase 集群IP" required="" autofocus="" name="IP">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="请输入暗号" required="" name="ID">
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->

</body>
</html>