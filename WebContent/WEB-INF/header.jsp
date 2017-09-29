
<!-- Navigation -->
<%@ page import="com.catapp.entity.User"%>

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
		<li class="dropdown"><a class="dropdown-toggle navbar-brand"
			data-toggle="dropdown" href="#"> <% String fName=((User)request.getSession().getAttribute("user")).getFirst_name().toString();%>

				<span>Welcome <%=fName %><span /> <i class="fa fa-caret-down"></i></a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="UserDisplayController"><i
						class="fa fa-user fa-fw"></i> User Profile</a></li>

				<li class="divider"></li>
				<li><a href="LogoutServlet"><i class="fa fa-sign-out fa-fw"></i>
						Logout</a></li>
			</ul> 
		<li ><a class="navbar-brand" href="BackToHomeServlet"> Home</a></li>
		<li ><a class="navbar-brand" href="redirect?destination=contact"> Contact us</a></li>
		<li ><a class="navbar-brand" href="redirect?destination=about"> About us</a></li>
		
	</ul>
</nav>

<script src="/CAT-APP-PROJECT/resources/js/jquery.min.js"></script>