<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page session="false"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="com.catapp.entity.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<head>
<title>Response Curves</title>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1"></meta>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" />
<!-- <link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
 --><!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
 --><!-- <link href="https://opensource.keycdn.com/fontawesome/4.7.0/font-awesome.min.css" rel="stylesheet" />
 -->
<link href="/CAT-APP-PROJECT/resources/css/bootstrap.min.css" rel="stylesheet"/>
<!-- MetisMenu CSS -->
<link href="/CAT-APP-PROJECT/resources/css/metisMenu.min.css" rel="stylesheet"/>
<!-- Custom CSS -->
<link href="/CAT-APP-PROJECT/resources/css/sb-admin-2.css" rel="stylesheet"/>
<link href="/CAT-APP-PROJECT/resources/css/font-awesome.css" rel="stylesheet" />
<!-- Custom Fonts -->
<link href="/CAT-APP-PROJECT/resources/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" href="resources/images/favicon.png"/>
<style type="text/css">
<style> 

/*  a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}
*/
a:active {
	color: black;
	text-decoration: none;
} 

.Highlighted_rows {
	background-color: #add2ed; /* EPA lightblue */
	border-width: 8px;
	border-color: #157fcc; /* EPA deep blue */
	border-style: solid;
	border-radius: 4px;
	height: 50px;
	text-align: center;
	text-shadow: 2px 2px 5px SkyBlue;
	font-size: 14;
	font-weight: bold;
	padding: 0px;
}

.c-invis {
	display: none;
}

.row_number {
	display: none;
}
/*font colours*/
Papaya {
	color: Black;
	font-weight: bold;
}

Gray {
	color: #9c9d9d;
}

data {
	color: Black;
}

data2 {
	color: Black;
	font-size: 20px;
}

#wrapper {
	/* background: RGBA(24, 161, 196, 0.2); */
	background: url('/CAT-APP-PROJECT/resources/images/bgLight.jpg') repeat
		center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	background-size: cover;
	-o-background-size: cover;
}
</style>

</head>
<body>

	<!-- Left Menu -->
	<div id="wrapper">
		<%
			String isAdmin = ((User) request.getSession().getAttribute("user")).getIs_admin();
			if (null == isAdmin || "" == isAdmin.trim()) {
		%>
		<jsp:include page="../header.jsp" />
		<%
			}
			if ("Y".equalsIgnoreCase(isAdmin)) {
		%>
		<jsp:include page="../header.jsp" />
		<%
			}
		%>
		
			<div class="w3-sidebar w3-bar-block w3-card-2 w3-animate-left"
				id="leftMenu" >
				
				<div id="Column-A" style="min-height: 600px; padding: 3px;">
					<sql:setDataSource var="snapshot"
						driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
						url="jdbc:sqlserver://IRUSYN1LAP\\SQLEXPRESS;databaseName=CATAPP;integratedSecurity=true" />
					<div id="data-Chemicals-Search">
						<%
							String search_term = "87";
						%>

						<sql:query dataSource="${snapshot}" var="Chemicals_result">
SELECT * from chemical_cas_concawe_mapping;
</sql:query>

						<c:set var="Chemicals_first_row_id"
							value="${Chemicals_result.rows[0].entity_id}" />
						<c:if test="${Chemicals_first_row_id != null}">

							<input type="text" id="cas_Input" onkeyup="Search_cas()"
								placeholder="Search cas.." title="Type in a name"
								style="width: 80px;"/> <input type="text" id="name_Input"
								onkeyup="Search_name()" placeholder="Search for names.."
								title="Type in a name" style="width: 150px;"/>

									<table id="table_1" style="display: block; height: 90%;">
										<tr>
											<th style="width: 90px;">CAS</th>
											<th>Substance Name</th>
										</tr>

										<%
											int i = 0;
										%>
										<script>
											var column3_data = [ "a", "b", "c" ];
										</script>
										<c:forEach var="Chemicals" items="${Chemicals_result.rows}">
											<!-- var="user"	// can be any variable name. -->
											<!-- "result" is the object name from search results. -->

											<tr>
												<td class="row_number"><%=i%></td>
												<td>${Chemicals.cas_number}</td>
												<td>${fn:substring(Chemicals.name, 0, 20)}</td>

												<div class="c-invis">
													<script>
														column3_data[
													<%=i%>
														] = "<br><h4><Papaya>CATAPP sample ID: </Papaya><data>${Chemicals.cat_app_id}"
																+ "</data><br></h4>"
																+ "<h4><Papaya>Concawe ID: </Papaya><data>${Chemicals.concawe_id} </data><br></h4>"
																+ "<h4><Papaya>Category: </Papaya><data>${Chemicals.category} </data><br></h4>"
																+ "<h4><Papaya>CAS Number: </Papaya><data>${Chemicals.cas_number}</data><br></h4>"
																+ " <h4><Papaya>Ec Number: </Papaya><data>${Chemicals.ec_number}</data><br></h4>"
																+
																/* " <h4><Papaya>Substance Name: </Papaya><data>${Chemicals.substance_name}</data><br></h4>" + */
																" <h4><Papaya>Chemical Name: </Papaya><data>${Chemicals.name}</data><br></h4>"
																+ " <h4><Papaya>Source: </Papaya><data>${Chemicals.source_description}</data><br></h4>";
														/* " <h4><Papaya>Substance Description: <br></Papaya></h4> <data>"  */
														/* " <h4 style = 'text-align: justfy'>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ${Chemicals.substance_description}</h4></data><br>" */;
													</script>
												</div>
												<%
													i = i + 1;
												%>

											</tr>
										</c:forEach>
										<%
											i = 0;
										%>
									</table>

						</c:if>

					</div>
					<!-- End of Chemicals Search -->
				</div>
				<!-- end of class="col-lg-3" id="Column-A" -->
			</div>

			<div class="w3-sidebar w3-bar-block w3-card-2 w3-animate-right"
				style="right: 0;  border-radius: 10px; position: absolute; z-index: 2;"
				id="rightMenu">
				<!-- right menu -->
				<div id="Column-B" style="padding-left: 9px">
					<br><span style="font-size: 13px; font-weight: bold;">
							<table id="table_2">
								<tr>
									<td><div id="chem_properties">
											Chemical & physical properties<br>
										</div></td>
								</tr>

								<tr>
									<td><div id="cardio90">
											iCardio. 90 min peak frequency <br>
										</div></td>
								</tr>
								<tr>
									<td><div id="cardio24">
											iCardio. 24 hr peak frequency<br>
										</div></td>
								</tr>
								<tr>
									<td><div id="cardio_Total_Cell_24h">
											iCardio. 24 hr total cells<br>
										</div></td>
								</tr>
								<tr>
									<td><div id="HUVEC_TC">
											HUVEC total cells <br>
										</div></td>
								</tr>
								<tr>
									<td><div id="HUVEC_TA">
											HUVEC tube area<br>
										</div></td>
								</tr>
								<tr>
									<td><div id="HUVEC_Mito_24h">
											HUVEC 24hr mitochondria<br>
										</div></td>
								</tr>
								<tr>
									<td><div id="cardio90_Fly">
											Fly & Fly iCardio. 90 min pk fr <br>
										</div></td>
								</tr>

							</table> <span style="color: #909191;"> iMacrophages test<br/>
									iHepatocytes test<br/> iEndothelial Cell test<br/>
											iNeuron test<br/> iSkeletal Myoblast test<br/>
													iLymphoblast test<br/> ===========<br/> A-375 Skin
															Melanoma<br/> A549 Lung Carcinoma<br/> HepG2
																	Hepatocyte Carcinoma<br/> HepaRG Hepatocyte
																		Carcinoma<br/> MCF7 Breast Adenocarcinoma<br/>

																				HT-29 Colon Adenocarcinoma<br/> LN-229
																					Glioblastoma<br/> HEK10205f Human Epidermal
																						Keratinocytes; Foetal<br/> HLMVEC Human Lung
																							Microvascular Endothelial Cells<br/> HMePC
																								Human Mammary Epithelial Cell<br/> SH-SY5Y
																									Neuroblastoma<br/></span>
					</span>
				</div>
				<!-- end of class="col-lg-6" id="Column-B" -->
			</div>


			<div id="central_area" style="height: 90vh;  margin-left:22%; margin-right:20%; z-index: 1;">
  	        <button  onclick="openLeftMenu()" id="button-open-left"
  		style="background-color: #3892d3; display: none; position: relative; z-index: 1;">
  		<span class="fa fa-chevron-right"></span> Open left menu</button>
  		
  	<button class="btn btn-info btn-sm" id="button-close-left"
  		onclick="closeLeftMenu()" style="background-color: #3892d3; position: relative; z-index: 1;">
  		<span class="fa fa-chevron-left"></span> Close</button>
  		
  	<button class="btn btn-info btn-sm pull-right" id="button-open-right"  	
  		onclick="openRightMenu()" style="background-color: #3892d3; display: none; position: relative; z-index: 1;">
  		<span class="fa fa-chevron-left"></span> Open right menu</button>
  		
  	<button class="btn btn-info btn-sm pull-right" onclick="closeRightMenu()" id="button-close-right"
  		style="background-color: #3892d3; position: relative; z-index: 1;">
  		<span class="fa fa-chevron-right"></span> Close</button>
  		
  	<div id="colCenter" style="height: 96%; border-radius: 25px; padding: 9px; 
  		z-index: 1; border:5px solid #157fcc;">
  	
  	
  	<!--  
<div style="background-color: #157fcc; width: 100%; height: 50px; position: absolute; top: 94px; 
	padding: -9px; margin: -9px; border-radius: 15px; z-index: 1; " id="blocking-piece">
</div>-->

		<div id="inside-C" style=""> 
				<br></br><br>
				<data2>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Chemical details<br>				
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Assay methods<br>
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Dose response curves<br>
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Point of departure<br>
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Project files<br><br>
				
				&nbsp; &nbsp; &nbsp;  More to come: <br>
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Assay images<br>
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Benchmark Dose Software processing<br>
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Toxpie comparison for chemicals and assays<br>
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Comparison to commonly known chemicals<br>
				
				</data2>
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			</div><!--  end of id="inside-C" -->
			</div>
			<!--  end of id="Column-C" -->
		</div>
	</div>
	

	<!-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
	<!-- <script
		src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script> -->
	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<!-- <script type="text/javascript"
		src="http://www.kunalbabre.com/projects/table2CSV.js"> -->
		
	</script>
	<script src="/CAT-APP-PROJECT/resources/js/Response/Response.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<!--  -->
		<script src="/CAT-APP-PROJECT/resources/js/jquery.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/metisMenu.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>
	<!-- METISMENU SCRIPTS -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery.metisMenu.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="/CAT-APP-PROJECT/resources/js/sb-admin-2.js"></script>	
</body>


</html>