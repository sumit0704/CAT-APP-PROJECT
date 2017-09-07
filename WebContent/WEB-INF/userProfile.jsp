<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page import="com.catapp.entity.User"%>
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
<link href="/CAT-APP-PROJECT/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/CAT-APP-PROJECT/resources/css/full-width-pics.css"
	rel="stylesheet">

<link href="/CAT-APP-PROJECT/resources/css/circleAnimation.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
<style>
#result {
	margin-left: 5px;
}

#PasswordEnter .short {
	color: #FF0000;
}

#PasswordEnter .weak {
	color: #E66C2C;
}

#PasswordEnter .good {
	color: #2D98F3;
}

#PasswordEnter .strong {
	color: #006400;
}

.user-row {
	margin-bottom: 14px;
}

.user-row:last-child {
	margin-bottom: 0;
}

.dropdown-user {
	margin: 13px 0;
	padding: 5px;
	height: 100%;
}

.dropdown-user:hover {
	cursor: pointer;
}

.table-user-information>tbody>tr {
	border-top: 1px solid rgb(221, 221, 221);
}

.table-user-information>tbody>tr:first-child {
	border-top: 0;
}
</style>
</head>

<body>

	<!-- 
<script type="text/javascript">
	$(document).ready(function() {
	    var today = new Date();
		 document.getElementById('date').innerHTML = today
	});
	
	
</script> -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery.js"></script>
	<script type="text/javascript">
$(document).ready(function() {
    $("#subPassChng").click(function() {
        var password = $("#repassword").val();
       
        $.ajax({
            url: 'ChangePassFromUserController',
            type: 'POST',
            data: {
            	password: password
                
            },
            success: function(data) {
            	$('#chgPassword').modal('hide');
            	$('#passSuccess').append("Password successfully saved");
            	return false;
            },
            failure: function(data) {
            	$('#chgPassword').modal('hide');
            	$('#passSuccess').append("Please try again");
                return false;
            }
        })
    });
});
</script>
	
	<div class="container">
		<form action="UpdateUserServlet" method="post">
			<div class="row">
				<div class="col-md-5  toppad  pull-right col-md-offset-3 ">
					<br>
					<div id="date"></div>
				</div>
				<div
					class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


					<div class="panel panel-info">
						<div class="panel-heading">

							<h3 class="panel-title" align="center">Update Profile</h3>
						</div>
						<div class="panel-body">
							<div class="row">



								<div class=" col-md-12 col-lg-12 ">
									<table class="table table-user-information">
										<tbody>
											<tr>
												<td>First name</td>
												<td><input type="text" name="firstName"
													value="<%=((User)request.getSession().getAttribute("user")).getFirst_name().toString() %>" /></td>
											</tr>
											<tr>
												<td>Last name</td>
												<% String lastName = ((User)request.getSession().getAttribute("user")).getLast_name(); %>

												<% if (lastName==null || lastName.isEmpty()) { %>
												<td><input type="text" name="lastName" value="" /></td>

												<% } else { %>
												<td><input type="text" name="lastName"
													value="<%=lastName.toString() %>" /></td>
												<% } %>
											
											<tr>
											<tr>
												<td>Institution</td>
												<% String inst = ((User)request.getSession().getAttribute("user")).getInstitution(); %>

												<% if (inst==null || inst.isEmpty()) { %>
												<td><input type="text" name="instName" value="" /></td>

												<% } else { %>
												<td><input type="text" name="instName"
													value="<%=inst.toString() %>" /></td>
												<% } %>
											
											<tr>
											<tr>
												<td>Privileges</td>
												<% String supervisor = ((User)request.getSession().getAttribute("user")).getIs_admin(); %>

												<% if (supervisor==null || supervisor.isEmpty()) { %>
												<td>No Admin Privileges</td>

												<% } else { %>
												<td>Admin Privileges</td>
												<% } %>
											
											<tr>
											<tr>
												<td>Phone number</td>
												<% String phNo = ((User)request.getSession().getAttribute("user")).getPhone_number(); %>

												<% if (phNo==null || phNo.isEmpty()) { %>
												<td><input type="text" name="phNo" value="" /></td>

												<% } else { %>
												<td><input type="text" name="phNo"
													value="<%=phNo.toString() %>" /></td>
												<% } %>
											
											<tr>
												<td>Email</td>
												<% String email = ((User)request.getSession().getAttribute("user")).getEmail().toString(); %>

												<% if (email==null || email.isEmpty()) { %>
												<td>...</td>

												<% } else { %>
												<td><%=email %></td>
												<% } %>
											</tr>
											<!-- <tr>
                        <td >Password</td>
                        <td><a href="" data-toggle="modal" data-target="#chgPassword" data-whatever="" >Change password</a>
                        </td>
                        
                           
                      </tr> -->

										</tbody>
									</table>


								</div>

							</div>
						</div>

						<!--             <div class="modal fade" id="chgPassword" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  					<div class="modal-dialog" role="document">
    						
    					<div class="modal-content">
      						<div class="modal-header">
        						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        						<h4 class="" id="exampleModalLabel">Change Password</h4>
      								</div>
      								<div class="modal-body">
        							
          							<div class="form-group" id="PasswordEnter">
                    
                  					  <input type="password" id="password" name="password" class="form-control input-lg" placeholder="Desired Password *" required="required">
                    					<span id="result"></span>
				    					</div>
				    				<span class="hint">Create a strong password with more than 8 characters, lowercase, uppercase and special characters</span>
				   					 <div class="form-group">
                    				<input type="password" id="repassword" name="repassword" class="form-control input-lg" placeholder="Re-enter Password *" required="required" onkeyup="checkPass(); return false;">
				    				<span id="confirmMessage" class="confirmMessage"></span>
				    					</div>
          							
        
      						</div>
      						<div class="modal-footer">
        						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        						<input type="submit" class="btn btn-primary" value="Submit" id="subPassChng"/>
      						</div>
      						
    				</div>
    				
  				</div>
		</div> -->

						<div class="panel-footer">
							<span id="passSuccess"></span>
						</div>




					</div>
				</div>
			</div>
			<div style="margin-left: 45%">
				<button type="submit" class="btn btn-primary" id="subProfileChange">Change</button>
			</div>
		</form>
	</div>
	<!-- jQuery -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/passwordStrength.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/homepage.js"></script>


</body>

</html>