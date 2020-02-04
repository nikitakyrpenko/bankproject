<%--
  Created by IntelliJ IDEA.
  User: mickaborscha
  Date: 2/4/2020
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
        <select id="lang">
          <option>UA</option>
          <option>ENG</option>
        </select>
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
            <a class="nav-link active" id="login-tab" data-toggle="tab" href="#login" role="tab" aria-controls="login" aria-selected="true">Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="register-tab" data-toggle="tab" href="#register" role="tab" aria-controls="register" aria-selected="false">Registration</a>
          </li>
        </ul>
        <div class="tab-content" id="myTabContent">
          <!-- Login-->
          <div class="tab-pane fade show active" id="login" role="tabpanel">
            <form action="UserController" method="post">
              <div class="form-group">
                <label for="logEmail">Email address</label>
                <input type="email" name="email" class="form-control" id="logEmail" aria-describedby="emailHelp">
              </div>
              <div class="form-group">
                <label for="logPassword">Password</label>
                <input type="password" name="password" class="form-control" id="logPassword">
              </div>
              <button type="submit" class="btn btn-primary" id="loginBtn">Log in</button>
            </form>
          </div>

          <!-- Registration-->
          <div class="tab-pane fade" id="register" role="register">
            <form>
              <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name">
              </div>
              <div class="form-group">
                <label for="surName">Surname</label>
                <input type="text" class="form-control" id="surName">
              </div>
              <div class="form-group">
                <label for="phone">Phone number</label>
                <input type="text" id="phone" class="form-control">
                <small id="phoneHelp" class="form-text text-muted">
                  Your Phonne number must be in format +380 000 000 000
                </small>
              </div>
              <div class="form-group">
                <label for="regEmail">Email address</label>
                <input type="email" class="form-control" id="regEmail" aria-describedby="emailHelp">
              </div>
              <div class="form-group">
                <label for="regPassword">Password</label>
                <input type="password" class="form-control" id="regPassword">
              </div>
              <div class="form-group">
                <label for="regPasswordConfirmation">Confirm your password</label>
                <input type="password" class="form-control" id="regPasswordConfirmation">
              </div>
              <button type="submit" class="btn btn-primary" id="registrationBtn">Registration</button>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>
