<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

    <%@page import="java.io.File"%>
    <%@page import="java.io.IOException"%>
    <%@page import="java.awt.image.BufferedImage"%>
    <%@page import="javax.imageio.ImageIO"%>
    <%@page import="java.io.ByteArrayOutputStream"%>

    <%@page import="java.math.BigInteger"%>
    <%@page import="javax.xml.bind.DatatypeConverter"%>
    <%@page import="java.awt.image.BufferedImage"%>
    <%@page import="sun.misc.BASE64Decoder"%>
    <%@page import="java.awt.image.BufferedImage"%>
	
<head>
<script type="text/javascript">
<script src="/CAT-APP-PROJECT/resources/js/Response/Column_3.js"/>
<script src="/CAT-APP-PROJECT/resources/js/Response/customScript_download.js"/>
</script>

<style type="text/css">
.nav.nav-tabs  {
    border-bottom: 6px solid #add2ed; 
    zIndex: 1;
}
.nav.nav-tabs>li>a {
	height: 30px;
	margin-top: 0px;
	padding-top: 6px;
	color: white;
	font-weight: bold;
	background-color: #3892d3;
}
.nav.nav-tabs>li.active>a, .nav.nav-tabs>li.active>a:hover, 
	nav.nav-tabs>li.active>a:focus, .nav.nav-tabs>li>a:hover {
    color: #157fcc;
    background-color: #add2ed;
}
</style>

</head>




<ul class="nav nav-tabs" style="position: relative; z-index: 2; background-color: #157fcc; ">
  <li class="active"><a data-toggle="tab" href="#home">Response curve</a></li>
  <li><a data-toggle="tab" href="#menu1" id="menue_property">Chemical properties</a></li>
  <li><a data-toggle="tab" href="#menu2">Assay</a></li>
  <li><a data-toggle="tab" href="#menu3">Assay data</a></li>
  <li><a data-toggle="tab" href="#menu4">Credit</a></li>
</ul>
<div style="background-color: #157fcc; width: 105%; height: 50px; position: relative; top: -41px; 
	padding: -9px; margin: -9px; margin-left: -14px; z-index: 1; " id="blocking-piece">
</div>


<div class="tab-content" style="position: relative; top: -32px; ">
  <div id="home" class="tab-pane fade in active">
   
<Papaya>
<p style="text-align: right; font-size: 11px;">(chemical #: ${chemical}, endpoint #: ${endpoint})</p>


<c:choose>
    <c:when test="${endpoint == '1'}">
       <c:set var="endpoint_string" scope="page" value="Peak_freq_90min" />
       <c:set var="endpoint_string2" scope="page" value="Cardio_peak_freq_90min" />
       <c:set var="endpoint_name" scope="page" value="iCardiomyocyte Peak frequency 90 minutes" />
    </c:when>
    <c:when test="${endpoint == '2'}">
       <c:set var="endpoint_string" scope="page" value="Peak_freq_24hr" />
       <c:set var="endpoint_string2" scope="page" value="Cardio_peak_freq_24hr" />
       <c:set var="endpoint_name" scope="page" value="iCardiomyocyte Peak frequency 24 hours" />
    </c:when>
    <c:when test="${endpoint == '3'}">
       <c:set var="endpoint_string" scope="page" value="Cardio_Total_Cell_24h" />
       <c:set var="endpoint_string2" scope="page" value="Cardio_Total_Cell_24h" />
       <c:set var="endpoint_name" scope="page" value="iCardiomyocyte total cells 24 hours" />
    </c:when>
    <c:when test="${endpoint == '4'}">
       <c:set var="endpoint_string" scope="page" value="HUVEC_total_cell" />
       <c:set var="endpoint_string2" scope="page" value="HUVEC_total_cell" />
       <c:set var="endpoint_name" scope="page" value="HUVEC viable cell 24 hours" />
    </c:when>
    <c:when test="${endpoint == '5'}">
       <c:set var="endpoint_string" scope="page" value='HUVEC_tube_area' />
       <c:set var="endpoint_string2" scope="page" value='HUVEC_tube_area' />
       <c:set var="endpoint_name" scope="page" value="HUVEC tube area 18 hours" />
    </c:when>
    <c:when test="${endpoint == '6'}">
       <c:set var="endpoint_string" scope="page" value="HUVEC_Mito_24h" />
       <c:set var="endpoint_string2" scope="page" value="HUVEC_Mito_24h" />
       <c:set var="endpoint_name" scope="page" value="HUVEC mitochondria 24 hours" />
    </c:when>
    <c:when test="${endpoint == '7'}">
       <c:set var="endpoint_string" scope="page" value="Peak_freq_90min_Fly_Fly" />
       <c:set var="endpoint_string2" scope="page" value="Cardio_peak_freq_90min" />
       <c:set var="endpoint_name" scope="page" value="Fly & Fly iCardiomyocyte Peak frequency 90 minutes" />
    </c:when>
</c:choose>



<%
String endpoint_string = (String)pageContext.getAttribute("endpoint_string");
String chemical_string = (String)request.getAttribute("chemical");
String image_path = "C:\\Users\\ssingh\\Desktop\\4_R\\Demonstration\\" + endpoint_string;
image_path = image_path + "\\Figs\\" + endpoint_string + chemical_string + ".png";
System.out.println("String: " + image_path);
%>



<p style="text-align: center">${endpoint_name}</p>

<%
    //write image
    try{
      // String imgName="C:\\4_R\\Demonstration\\Peak_Freq_24hr\\Figs\\Peak_freq_24hr1.png";
      String imgName = image_path;
      BufferedImage bImage = ImageIO.read(new File(imgName));//give the path of an image
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // ImageIO.write( bImage, "jpg", baos );
      ImageIO.write( bImage, "png", baos );
      baos.flush();
      byte[] imageInByteArray = baos.toByteArray();
      byte[] imageByte;
      String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);
      
  
      BASE64Decoder decoder = new BASE64Decoder();
      imageByte = decoder.decodeBuffer(b64);
      
      
      baos.close();                                   
     
      
        
      %>
        <div id = "draggable" style="text-align: center;">
         <img  id="response-curve" src="data:image/jpg;base64, <%=b64%>" />
      </div>
      
<%-- 
      <form method="get" action="data:image/jpg, <%=imageByte(String)%>">
<button type="submit"><span class="glyphicon glyphicon-download-alt" 
	style="padding-left: 5px; position: absolute; top: 220px; left: 23%; color: white;"></span>Download!</button>
</form>
     --%> 
      
           
        <% 
    }catch(IOException e){
      	System.out.println("Error: "+e);
    	} 
%>

<div style="position: absolute; background-color: #157fcc; color: white; width: 40px; height: 70px; top: 25px; 
	left:0%; margin: 8px; padding: 5; border-radius: 10px;"> 
																							<!-- Picture div-->
<a href="#" ><span class="glyphicon glyphicon-plus" onclick="magnify()"
	style="padding-left: 7px; position: absolute; top: 14px; left:6px; color: white;"></span></a>
<a href="#" ><span class="glyphicon glyphicon-minus" onclick="shrink()"
	style="padding-left: 5px; position: absolute; top: 42px; left: 7px; color: white;"></span></a>
	
</div>




</div> <!-- end of id="home" -->
  
  <div id="menu1" class="tab-pane fade">
    ${chemical_properties}
    
  </div>
  

  <div id="menu2" class="tab-pane fade" style="position: relative; top: -32px; ">
  
    <papaya>${endpoint_data}
    </papaya>
  </div>		<!-- end of Assay div -->
  
  <div id="menu3" class="tab-pane fade" style="padding: 15px;">
  																					<!-- Assay data div -->
	<papaya>
	<sql:setDataSource var="snapshot"
	driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
	url="jdbc:sqlserver://IRUSYN1LAP\\SQLEXPRESS;databaseName=CATAPP;integratedSecurity=true" />
	
	<sql:query dataSource="${snapshot}" var="Assay_data_result">
		SELECT * from assay_data where (phenotype = '${endpoint_string2}' AND catapp_id = '${chemical}');
	</sql:query>
	<br>
	
	<button class="btn btn-info btn-md" onclick="$('#download_assay_data').table2CSV()" 
  		style="background-color: #3892d3; position: absolute; right: 30px; top: 30px; z-index: 1;">
  		<span class="glyphicon glyphicon-download-alt"></span> &nbsp; &nbsp; Export as CSV</button>
	
	
	
	Catapp Sample ID: ${Assay_data_result.rows[0].catapp_id}<br>
	Chemical name: ${Assay_data_result.rows[0].chem_name}<br>
	Assay: ${Assay_data_result.rows[0].phenotype}<br>
	<table border=0>
	<tr><td>Point of departure: &nbsp; </td><td> ${fn:substring(Assay_data_result.rows[0].pod_sd1, 0, 6)} 
		 (Log percentage dilution unit, at 1 standard deviation of the controls.)</td><tr>
	<tr><td>Dose 1000x: </td><td> ${fn:substring(Assay_data_result.rows[0].Dose_1000x, 0, 6)}</td><tr>
	<tr><td>Dose 100x: </td><td> ${fn:substring(Assay_data_result.rows[0].Dose_100x, 0, 6)}</td><tr>
	<tr><td>Dose 10x: </td><td> ${fn:substring(Assay_data_result.rows[0].Dose_10x, 0, 6)}</td><tr>
	<tr><td>Dose 1x: </td><td> ${fn:substring(Assay_data_result.rows[0].Dose_1x, 0, 6)}</td><tr>
	
	</table>
	
<sql:query dataSource="${snapshot}" var="Control_data_result">
SELECT * from assay_controls where phenotype = '${endpoint_string2}';
</sql:query>
	<br>
	
	Control values:<br>
<c:forEach var="row" items="${Control_data_result.rows}">  		
   ${fn:substring(row.control_value, 0, 6)},
</c:forEach>    
   
   <br>
   

   

	<table id="download_assay_data" style="font-size: 0.1px; z-index: -2;" > <!--  style="display: none;" -->
   		<tr><td>downloaded_assay_data</td>
   		<tr><td>Catapp_Sample_ID</td><td> ${Assay_data_result.rows[0].catapp_id}</td><tr>
		<tr><td>Chemical_name</td><td>${Assay_data_result.rows[0].chem_name}</td><tr>
		<tr><td>Assay</td><td>${Assay_data_result.rows[0].phenotype}</td><tr>
   		<tr><td>Point_of_departure</td><td> ${fn:substring(Assay_data_result.rows[0].pod_sd1, 0, 6)} 
		 	</td><td> (Log_percentage_dilution_unit_at_1_standard_deviation_of_controls)</td><tr>
		<tr><td>Dose_1000x</td><td> ${fn:substring(Assay_data_result.rows[0].Dose_1000x, 0, 6)}</td><tr>
		<tr><td>Dose_100x</td><td> ${fn:substring(Assay_data_result.rows[0].Dose_100x, 0, 6)}</td><tr>
		<tr><td>Dose_10x</td><td> ${fn:substring(Assay_data_result.rows[0].Dose_10x, 0, 6)}</td><tr>
		<tr><td>Dose 1x </td><td> ${fn:substring(Assay_data_result.rows[0].Dose_1x, 0, 6)}</td><tr>  		 
		<c:forEach var="row" items="${Control_data_result.rows}">  		
   			<tr><td>Control_values</td><td>${fn:substring(row.control_value, 0, 6)}</td><tr>
			</c:forEach>    
   	</table>
    </papaya>
       

  </div>		<!-- end of Assay data div -->
  
  
  
  <div id="menu4" class="tab-pane fade" style="position: relative; top: -32px; ">
  																				<!-- Credit div -->
    <papaya><h3></h3><br><br>
    <p style="text-indent: 50px;">The assays were conducted in Dr. Ivan Rusyn lab at Texas A&M University. </p>
    <p>The R program for dose-response curves was written by Dr. Fred Wright at the North Carolina State 
    University, and minor modifications were made in Dr. Ivan Rusyn lab. </p></papaya>
  </div>
</div>


</Papaya>