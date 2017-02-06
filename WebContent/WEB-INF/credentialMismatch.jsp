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
    
</head>

<body>
<jsp:include page="header.jsp" />
    <!-- Navigation -->
    <%
String login_msg=(String)request.getAttribute("error");  
if(login_msg!=null)
out.println("<font color=red size=4px>"+login_msg+"</font>");
%>

    
    <!-- Content Section -->
<section id="login">
<div class="container">

<div class="row" style="margin-top:20px">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		
			<fieldset>
				<h2 align="center"><font color="red">Incorrect information</font></h2>
				<hr class="colorgraph">
				<form action="LoginServlet" method="post">
				<div class="form-group">
                    <input type="email" name="email" id="email" class="form-control input-lg" placeholder="Email Address">
				</div>
				<div class="form-group">
                    <input type="password" name="password" id="password" class="form-control input-lg" placeholder="Password">
				</div>
				
					<div class="checkbox">
    				<label><input type="checkbox" id="rememberMe"> Remember me</label>
  				</div>
                    <input type="checkbox" name="remember_me" id="remember_me" checked="checked" class="hidden">
					<button type="button" class="btn btn-link pull-right" data-toggle="modal" data-target="#exampleModal" data-whatever="">Forgot Password?</button>
				
				<hr class="colorgraph">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
                        <input type="submit" class="btn btn-lg btn-success btn-block" value="Sign In">
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
