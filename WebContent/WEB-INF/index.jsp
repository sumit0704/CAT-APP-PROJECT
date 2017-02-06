<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CAT-APP</title>

    <!-- Bootstrap Core CSS -->
    <link href="/CAT-APP-PROJECT/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/CAT-APP-PROJECT/css/full-width-pics.css" rel="stylesheet">
	<link href="/CAT-APP-PROJECT/css/login.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
	<style type="text/css">
	.bs-example{
    	margin: 20px;
    }
</style>
</head>

<body>
<jsp:include page="header.jsp" />
    <!-- Navigation -->
    <%
String login_msg=(String)request.getAttribute("error");  
if(login_msg!=null)
out.println("<font color=red size=4px>"+login_msg+"</font>");
%>

    <!-- Full Width Image Header with Logo -->
    <!-- Image backgrounds are set within the full-width-pics.css file. -->
    <div class="container">
    <header class="jumbotron hero-spacer" style="margin-top: 15px">
            
            
            <div class="container">

				<div class="row" style="margin-top:20px">
   					 <div class=" col-sm-5 col-md-5 col-lg-5">
   					 <img class="img-responsive img-center" src="/CAT-APP-PROJECT/images/logo.JPG" alt="">
   					 </div>
   					 <div class=" col-sm-7 col-md-7 col-lg-7">
   					 <h1 align="center">About CAT-APP</h1>
   					 </div>
   					</div>
   					</div>
		<a href="#login" class="btn btn-lg btn-primary btn-block" style="margin-left: 40%;margin-top:20%;height: 40px; width:300px">Sign in or <font color="purple"><b>Request Access</b></font></a>
        </header>
        
</div>
        <hr>

    <!-- Content Section -->
<section id="login">
<div class="container">

<div class="row" style="margin-top:20px">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		
			<fieldset>
				<h2 align="center">Please Sign In</h2>
				<hr class="colorgraph">
				<form action="LoginServlet" method="post">
				<div class="form-group">
                    <input type="email" name="email" id="email" class="form-control input-lg" placeholder="Email Address" required="required">
				</div>
				<div class="form-group">
                    <input type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" required="required">
				</div>
				
					<div class="checkbox">
    				<label><input type="checkbox" id="rememberMe"> Remember me</label>
  				</div>
                    <input type="checkbox" name="remember_me" id="remember_me" checked="checked" class="hidden">
					<!-- 
					<a href="#" class="btn btn-link pull-right">Forgot Password?</a>
					 -->
					<button type="button" class="btn btn-link pull-right" data-toggle="modal" data-target="#exampleModal" data-whatever="">Forgot Password?</button>


						
					
					
					
				<hr class="colorgraph">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
                        <input type="submit" class="btn btn-lg btn-success btn-block" value="Sign in">
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<a href="#register" class="btn btn-lg btn-primary btn-block" onclick="showRegistration()">Request Access</a>
					</div>
				</div>
				</form>
			</fieldset>
		
	</div>
</div>

</div>

		
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  					<div class="modal-dialog" role="document">
    					<form action="RequestAccessServlet" method="post">		
    					<div class="modal-content">
      						<div class="modal-header">
        						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        						<h4 class="" id="exampleModalLabel">Forget Password?</h4>
      								</div>
      								<div class="modal-body">
        							
          							<div class="form-group">
            							<label for="recipient-name" class="control-label">Enter email address:</label>
            							<input type="email" class="form-control" id="forgotEmail" name="forgotEmail">
          							</div>
          							
        
      						</div>
      						<div class="modal-footer">
        						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        						<input type="submit" class="btn btn-primary" value="Submit"/>
      						</div>
      						
    				</div>
    				</form>
  				</div>
		</div>
		
    </section>

 <section id = "register">
 <div class="container" id="registration" style="display: none">

<div class="row" style="margin-top:20px">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form action="SubmittedRequestServlet" method="post">
			<fieldset>
				<h2 align="center">Request access</h2>
				<hr class="colorgraph">
				
				
					<div class="form-group">
                    	<input type="text" name="firstName" id="firstName" class="form-control input-lg" placeholder="First Name">
					</div>
					<div class="form-group">
                   	 	<input type="text" name="lastName" id="lastName" class="form-control input-lg" placeholder="Last Name">
					</div>
				
				
					<div class="form-group">
                    	<input type="email" name="email" id="email" class="form-control input-lg" placeholder="Email Address">
				</div>
					<div class="form-group">
                    	<input type="number" name="contactNo" id="contactNo" class="form-control input-lg" placeholder="Contact No">
				</div>
				
				
					<div class="form-group">
                    	<input type="text" name="sFirstName" id="sFirstName" class="form-control input-lg" placeholder="Supervisor First Name">
					</div>
					<div class="form-group">
                   	 	<input type="text" name="sLastName" id="sLastName" class="form-control input-lg" placeholder="Supervisor Last Name">
					</div>
				
				
					<div class="form-group">
                    	<input type="email" name="sEmail" id="sEmail" class="form-control input-lg" placeholder="Supervisor Email Address">
				</div>
					<div class="form-group">
                    	<input type="number" name="sContactNo" id="sContactNo" class="form-control input-lg" placeholder="Supervisor Contact No">
				</div>
				
				<div class="form-group">
					<div class="dropdown">
                		<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" style="width:100%">Security question 1
                    		<span class="caret"></span>
                		</button>
                		<ul class="dropdown-menu">
                    		<li><a href="#">HTML</a></li>
                    		<li><a href="#">CSS</a></li>
                    		<li><a href="#">JavaScript</a></li>
                		</ul>
            		</div>
				</div>
				<div class="form-group">
                    	<input type="text" name="ans1" id="ans1" class="form-control input-lg" placeholder="Answer1">
				</div>
				
				<div class="form-group">
					<div class="dropdown">
                		<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" style="width:100%">Security question 2
                    		<span class="caret"></span>
                		</button>
                		<ul class="dropdown-menu">
                    		<li><a href="#">HTML</a></li>
                    		<li><a href="#">CSS</a></li>
                    		<li><a href="#">JavaScript</a></li>
                		</ul>
            		</div>
				</div>
				<div class="form-group">
                    	<input type="text" name="ans2" id="ans2" class="form-control input-lg" placeholder="Answer2">
				</div>
				<div class="form-group">
					<div class="dropdown">
                		<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" style="width:100%">Security question 3
                    		<span class="caret"></span>
                		</button>
                		<ul class="dropdown-menu">
                    		<li><a href="#">HTML</a></li>
                    		<li><a href="#">CSS</a></li>
                    		<li><a href="#">JavaScript</a></li>
                		</ul>
            		</div>
				</div>
				<div class="form-group">
                    	<input type="text" name="ans3" id="ans3" class="form-control input-lg" placeholder="Answer3">
				</div>
				<div class="form-group"> Select your project(s)
					<label class="checkbox-inline">
  						<input type="checkbox" id="catAppP" value="CAT-APP"> CAT-APP
					</label>
					<label class="checkbox-inline">
  						<input type="checkbox" id="analyticalP" value="Analytical"> Analytical
					</label>
					<label class="checkbox-inline">
  						<input type="checkbox" id="ecoToxP" value="ECO-TOX"> ECO-TOX
					</label>
				</div>
				
				
				<hr class="colorgraph">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
                        <input type="submit" class="btn btn-lg btn-success btn-block" value="Request Access" style="margin-left: 50%">
					</div>
					
				</div>
				
			</fieldset>
		</form>
	</div>
</div>

</div>
 
 </section>   

    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container -->
    </footer>

    <!-- jQuery -->
    <script src="/CAT-APP-PROJECT/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/CAT-APP-PROJECT/js/bootstrap.min.js"></script>
    <script src="/CAT-APP-PROJECT/js/homepage.js"></script>

</body>

</html>
