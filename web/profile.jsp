<%@ page contentType="text/html"  pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href='css/bootstrap.min.css'>

    <link rel="stylesheet" href="css/main.css">

    <title>Users Information</title>
</head>
<body>
<div class="row h-100">
    <nav class="nav flex-column col-2  align-items-center navbar-light bg-light">
     <a class="navbar-brand a-cust" href="#">
      <img src="img/man.png" width="50" height="50" class="align-center " alt="profile">
      <small>Name Surname</small>
      </a>
      <hr class="hr-gray">
      <a class="nav-link" href="#">Active</a>
      <a class="nav-link" href="#">Link</a>
      <a class="nav-link" href="#">Link</a>
    </nav>
    <div class="col">
<!--Credit/Deposit navigation pills-->
    <div class="row bg-light ">
       <div class="col">
        <ul class="nav nav-pills" id="pills-tab" role="tablist">
          <li class="nav-item">
            <a class="nav-link active" id="pills-Credit-tab" data-toggle="pill" href="#pills-credit" role="tab" aria-controls="pills-credit" aria-selected="true">Credit</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="pills-Deposit-tab" data-toggle="pill" href="#pills-deposit" role="tab" aria-controls="pills-deposit" aria-selected="false">Deposite</a>
          </li>
        </ul>
        </div>
        </div>
        <div class="row justify-content-center">
           <div class="col-7">
        <div class="tab-content" id="pills-tabContent">
<!--Credit Card Panel-->
        <div class="tab-pane fade show active" id="pills-credit" role="tabpanel" aria-labelledby="pills-Credit-tab">
<!--New card-->
                      <div class="card card-cust">
                            <h6 class="card-header">Account Information</h6>
                            <div class="row row-cust">
                                <div class="col-4 ">Expiration date: </div>
                                <div class="col-6" id="crExpiration">Your text</div>
                            </div>
                            <hr>
                            <div class="row row-cust">
                                <div class="col-4 ">Holder: </div>
                                <div class="col-6" id="crHolder">Your text</div>
                            </div>
                            <hr>
                            <div class="row row-cust">
                                <div class="col-4">Balance: </div>
                                <div class="col-6" id="crBalance">Your text</div>
                            </div>
                            <hr>
                            <div class="row row-cust">
                                <div class="col-4">Limit: </div>
                                <div class="col-6" id="crLimit">Your text</div>
                            </div>
                            <hr>
                            <div class="row row-cust">
                                <div class="col-4">Rate: </div>
                                <div class="col-6" id="crRate">Your text</div>
                            </div>
                            <hr>
                            <div class="row row-cust">
                                <div class="col-4">Charge: </div>
                                <div class="col-6" id="crCharge">Your text</div>
                            </div>
                            <hr>
                            <div class="row justify-content-center">
                                <div class="col-4">
                                    <a href="#" class="btn btn-primary btn-cust">Go somewhere</a>
                                </div>
                            </div>
                        </div>
              <!--Card ends here ^-->
                  </div>
           
<!--Deposit Cards Panel-->
          <div class="tab-pane fade" id="pills-deposit" role="tabpanel" aria-labelledby="pills-Deposit-tab">
<!--Deposit Card-->
              <div class="card card-cust">
                    <h6 class="card-header">Account Information</h6>
                           <div class="row row-cust">
                                <div class="col-4 ">Expiration date: </div>
                                <div class="col-6" id="depExpiration">Your text</div>
                            </div>
                            <hr>
                            <div class="row row-cust">
                                <div class="col-4 ">Holder: </div>
                                <div class="col-6" id="depHolder">Your text</div>
                            </div>
                            <hr>
                            <div class="row row-cust">
                                <div class="col-4">Balance: </div>
                                <div class="col-6" id="depBalance">Your text</div>
                            </div>
                            <hr>
                            <div class="row row-cust">
                                <div class="col-4">Deposit rate: </div>
                                <div class="col-6" id="depRate">Your text</div>
                            </div>
                            <hr>
                            <div class="row row-cust">
                                <div class="col-4">Deposit amount: </div>
                                <div class="col-6" id="depAmount">Your text</div>
                            </div>
                            <hr>
                            <div class="row justify-content-center">
                                <div class="col-4">
                                    <a href="#" class="btn btn-primary btn-cust">Go somewhere</a>
                                </div>
                            </div>
                        </div>
                  </div>
            </div>
        </div>
     </div>
       <div class="row">
        <footer class="page-footer font-small">
            <div class="btn-toolbar fixed-bottom bg-info" role="toolbar" aria-label="Toolbar with button groups">
              <div class="btn-group mr-2" role="group" aria-label="First group">
                <button type="button" class="btn btn-secondary">1</button>
                <button type="button" class="btn btn-secondary">2</button>
                <button type="button" class="btn btn-secondary">3</button>
                <button type="button" class="btn btn-secondary">4</button>
              </div>
            </div>
</footer>
        </div>
         
        
        
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>