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
<jsp:include page="headerAdminHome.jsp" />
   
   <div id="container-fluid" class="container-fluid" style="margin-left:10%;margin-right:10%">
		<div class="row">
		<div class="col-sm-3 col-md-3 col-lg-3">
		
			<div style="margin-left: 20%;margin-top:20%">
			<a href="#"><img class="img-responsive img-center" src="/CAT-APP-PROJECT/images/user.png" alt="" width="150" height="150" /></a>
			</div>
		</div>
		<div class="col-sm-9 col-md-9 col-lg-9">
		<div class="data" style="float: left;margin-top:3%" >
		<% String user=(String)request.getSession().getAttribute("email");%>
		<h1>Welcome user <%=user %> </h1>
		<h3>Now explore the projects. Click any of the projects to check out details</h3>
		
		
		</div>
		</div>
	</div>
	<div class="row" style="margin-top:5%">
		<div class="col-sm-4 col-md-4 col-lg-4">
			<div class="thumbnail">
      		
      		<div class="caption">
        		<h3>Thumbnail label</h3>
        		<p>CAT-APP</p>
        		<div class="btn-group">
        		<p><button type="button" class="btn btn-primary" role="button">Download</button> </p>
        		<p><button type="button" class="btn btn-primary" role="button">Upload</button> </p>
      			</div></div>
    		</div>
		</div>
		
		<div class="col-sm-4 col-md-4 col-lg-4">
			<div class="thumbnail">
      		
      		<div class="caption">
        		<h3>Analytical data</h3>
        		<p>...</p>
        		<div class="btn-group">
        		<p><button type="button" class="btn btn-primary" role="button">Download</button> </p>
        		<p><button type="button" class="btn btn-primary" role="button">Upload</button> </p>
      			</div></div>
    		</div>
		</div>
		
		<div class="col-sm-4 col-md-4 col-lg-4">
			<div class="thumbnail">
      		
      		<div class="caption">
        		<h3>ECO-TOX</h3>
        		<p>...</p>
        		<div class="btn-group">
        		<p><button type="button" class="btn btn-primary" role="button">Download</button> </p>
        		<p><button type="button" class="btn btn-primary" role="button">Upload</button> </p>
      			</div></div>
    		</div>
		</div>
		
	</div>

</div>
 

    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2017</p>
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
    <script src="/CAT-APP-PROJECT/js/userHome.js"></script>

</body>

</html>
