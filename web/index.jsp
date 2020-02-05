
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: mickaborscha
  Date: 2/4/2020
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <script src="js/bootstrap.js"></script>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href='css/bootstrap.min.css'>

  <link rel="stylesheet" href="css/main.css">

  <title>Banking</title>
</head>
<body>
<div>
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
      <img src="img/logo1.png" width="30" height="30" class="d-inline-block align-top" alt="logo">
      YourBanking
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-item nav-link" href="#">Home</a>
        <a class="nav-item nav-link" href="#">Features</a>
        <a class="nav-item nav-link" href="#">Pricing</a>
        <form>
        <select id="lang">
          <option value="ua">UA</option>
          <option value="eng">ENG</option>
          <script>
            //TODO
            $('#lang').on('change', function() {
              $.get("/bank_project_war/UserController", {"command" : "language", lang : this.value });
            });

          </script>


          <input type='submit' name='submit'/>
        </select>
        </form>
      </div>
    </div>
  </nav>
</div>
<!--Main container-->
<div class="container-fluid back">
  <div class="container">
    <div class="row justify-content-end">
      <div class="col-4  form-cust">
        <!--Form-->
        <ul class="nav nav-tabs" id="myTab" role="tablist">
          <li class="nav-item">
            <a class="nav-link active" id="login-tab" data-toggle="tab" href="#login" role="tab" aria-controls="login" aria-selected="true"><fmt:message key="label.login" /></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="register-tab" data-toggle="tab" href="#register" role="tab" aria-controls="register" aria-selected="false"><fmt:message key="label.register" /></a>
          </li>
        </ul>
        <div class="tab-content" id="myTabContent">
          <!-- Login-->
          <div class="tab-pane fade show active" id="login" role="tabpanel">
            <form action="UserController" method="post">
              <input type="hidden" name="command"  value="login">
              <div class="form-group">
                <label for="logEmail"><fmt:message key="label.email" /></label>
                <input type="email" name="email" class="form-control" id="logEmail" aria-describedby="emailHelp">
              </div>
              <div class="form-group">
                <label for="logPassword"><fmt:message key="label.password" /></label>
                <input type="password" name="password" class="form-control" id="logPassword">
              </div>
              <%
                String loginException = "";
                if (request.getAttribute("loginException") != null){
                  loginException = (String)request.getAttribute("loginException");
                }
              %>
              <div class= <%=loginException.isEmpty() ? "alert alert-danger collapse" :"alert alert-danger collapse.show"%> role="alert" ><%=loginException%></div>
              <button type="submit" class="btn btn-primary" id="loginBtn"><fmt:message key="label.button.login" /></button>
            </form>
          </div>

          <!-- Registration-->
          <div class="tab-pane fade" id="register" role="register">
            <form action="UserController" method="post">
              <input type="hidden" name="command"  value="register">
              <div class="form-group">
                <label for="name"><fmt:message key="label.name" /></label>
                <input type="text" class="form-control" id="name" name="name" >
              </div>
              <div class="form-group">
                <label for="surName"><fmt:message key="label.surname" /></label>
                <input type="text" class="form-control" id="surName" name="surname">
              </div>
              <div class="form-group">
                <label for="phone"><fmt:message key="label.telephone" /></label>
                <input type="text" id="phone" class="form-control" name="telephone">
                <small id="phoneHelp" class="form-text text-muted">
                  <fmt:message key="label.telephone.pattern" />
                </small>
              </div>
              <div class="form-group">
                <label for="regEmail"><fmt:message key="label.email" /></label>
                <input type="email" class="form-control" id="regEmail" aria-describedby="emailHelp" name="email">
              </div>
              <div class="form-group">
                <label for="regPassword"><fmt:message key="label.password" /></label>
                <input type="password" class="form-control" id="regPassword" name="password">
              </div>
              <div class="form-group">
                <label for="regPasswordConfirmation"><fmt:message key="label.confirmPassword" /></label>
                <input type="password" class="form-control" id="regPasswordConfirmation" name="confirmedPassword">
              </div>
              <%
                String registerException = "";
                if (request.getAttribute("registerException") != null){
                  registerException = (String)request.getAttribute("registerException");
                }
              %>
              <div class=<%=registerException.isEmpty() ? "alert alert-danger collapse" :"alert alert-danger collapse.show"%> role="alert"><%=registerException%></div>
              <button type="submit" class="btn btn-primary" id="registrationBtn"><fmt:message key="label.register" /></button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


<!-- Footer -->
<footer class="page-footer font-small">
  <div class="footer-copyright text-center py-2 bg-info text-white fixed-bottom">© 2020 Copyright:
    Mykyta
  </div>
</footer>
</body>
</html>
