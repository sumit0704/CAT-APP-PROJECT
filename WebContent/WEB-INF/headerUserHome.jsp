<%@page pageEncoding="UTF-8" %>
<!-- Navigation -->
<%@ page import="com.catapp.entity.User"%>
<meta http-equiv="Content-Type" content="text/html; charset= UTF-8">
<nav class="navbar navbar-inverse navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="BackToHomeServlet">Cat-App Database</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown" style="font-color:white;"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <% String fName=((User)request.getSession().getAttribute("user")).getFirst_name().toString();%>

				<span>Welcome <%=fName %><span /> <i class="fa fa-caret-down"></i></a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="UserDisplayController"><i
						class="fa fa-user fa-fw"></i> User Profile</a></li>
				<li class="divider"></li>
				<li><a href="LogoutServlet"><i class="fa fa-sign-out fa-fw"></i>
						Logout</a></li>
			</ul>
		<li><a href="BackToHomeServlet"> Home</a></li>
		<li><a href="redirect?destination=contact"> Contact us</a></li>
		<li><a href="redirect?destination=about"> About us</a></li>




		<!-- /.dropdown-user -->
		</li>
		<!-- /.dropdown -->




	</ul>
	<!-- /.navbar-top-links -->



	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<!--  <li class="text-center user-image-back">
                       
                        <img src="/CAT-APP-PROJECT/resources/images/find_user.png" class="img-responsive" />
                     
                    </li> -->
				<!-- <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                            </div>
                            /input-group
                        </li> -->


				<li><a href="#" class="active"><i
						class="fa fa-bar-chart-o fa-fw"></i> Cat-App<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="#">Download</a></li>
						<li><a href="#">Upload</a></li>

					</ul> <!-- /.nav-second-level --></li>



				<li><a href="#" class="active"><i class="fa fa-tint fa-fw"></i>
						Analytical<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="#">Download</a></li>
						<li><a href="#">Upload</a></li>

					</ul> <!-- /.nav-second-level --></li>

				<li><a href="#" class="active"><i class="fa fa-leaf fa-fw"></i>
						Eco-Tox<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">


					</ul> <!-- /.nav-second-level --></li>




			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>

<script src="/CAT-APP-PROJECT/resources/js/jquery.min.js"></script>
