<%--
  Created by IntelliJ IDEA.
  User: intern1
  Date: 5/3/2017
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/angular.js"></script>
    <script src="js/angular-animate.js"></script>
    <script src="js/angular-route.js"></script>
    <script src="js/ngStorage.js"></script>
    <script src="js/ng-img-crop.js"></script>

    <script src="js/ui-bootstrap-tpls-2.5.0.min.js"></script>
    <script src="main.js"></script>

    <script src="js/fileUploadDirective.js"></script>

    <script src="admin/service/AdminService.js"></script>
    <script src="admin/service/RoleService.js"></script>
    <script src="login/service/LoginService.js"></script>
    <script src="login/service/LogoutService.js"></script>

    <script src="service/HttpService.js"></script>
    <script src="service/JqueryUIDatePicker.js"></script>
    <script src="service/AuthService.js"></script>

    <script src="admin/controller/FormController.js"></script>
    <script src="login/controller/LoginController.js"></script>
    <script src="test/controller/TestController.js"></script>

    <script src="admin/modal/controller/EditAdminController.js"></script>
    <script src="admin/modal/controller/DetailAdminController.js"></script>
    <script src="admin/modal/controller/ProfilePicController.js"></script>

    <link rel="stylesheet" href="css/jquery-ui.css"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/ng-img-crop.css"/>



</head>
<body ng-app="MyApp">

<div ng-view></div>

<br/>

</body>
</html>


