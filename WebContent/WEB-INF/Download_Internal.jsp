<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<title>Download Internal</title>

<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
 -->
<style type="text/css">
#page-wrapper {
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
<body onload="myFunction()">

	<sql:setDataSource var="snapshot"
		driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
		url="jdbc:sqlserver://IRUSYN1LAP\\SQLEXPRESS;databaseName=CATAPP;integratedSecurity=true" />


	<script>
$(document).ready(function()
		{
    var selected_cell_lines;
    // alert("still ready function.");
    <c:forEach var="element" items="${selected_cell_lines}" varStatus="status">
    	<sql:query dataSource="${snapshot}" var="result">
    		select distinct assay_type from file_info where cell_line_id = '${element}'; 
    	</sql:query>
    	<c:forEach var="row" items="${result.rows}">  
    		// alert("assay: ${element}_${row.assay_type}");
			$("#${element}_${row.assay_type}").removeAttr("disabled");
    		$("#${element}_${row.assay_type}_B").css({'color': 'Black ', 'font-size': '105%', 'font-weight': 'bold' });
    	</c:forEach> 
    	$("#${element}_assays").show();
   	</c:forEach>
});
</script>




	<h4 id="assay_head"
		style="color: Blue; display: none; margin-left: 40px;">
		<a href="#" id="assay_title" onclick="click_assay_title()"><b>Please
					select assays:</b></a> <a href="#" id="assay_button"
			onclick="click_assay_button()" style="display: none;">
			<button type="button" class="btn btn-xs btn-primary">
				Next <span class="fa fa-chevron-right"></span>
			</button>
		</a>
	</h4>

	<div id="assay_list" style="display: none; margin-left: 40px;">
		<!-- "_" is used to seperate cell line tags and phenotype tags -->
		<div id="CM_assays" class="all_assays"
			style="display: none; color: Gray; margin-left: 20px;">
			<span style="color: black; font-weight: bold;">iCell
				cardiomyocyte assays:</span><br> <input type="checkbox" disabled
				class="all_assays" id="CM_Ca2" name="CM_Ca2" value='CM_Ca2'>
			<span id="CM_Ca2_B">Ca2+ flux<br></span> <input type="checkbox"
				disabled class="all_assays" id="CM_Hoechst" name="CM_Hoechst"
				value='CM_Hoechst'> <span id="CM_Hoechst_B">Nuclei
				staining<br>
			</span> <input type="checkbox" disabled class="all_assays" id="CM_Mito"
				name="CM_Mito" value='CM_Mito'> <span id="CM_Mito_B">Mitochondrial
				Integrity<br>
			</span> <input type="checkbox" disabled class="all_assays" id="CM_Seq"
				name="CM_Seq" value='CM_Seq'> <span id="CM_Seq_B">
				TompOseq<br>
			</span> <br>
		</div>
		<div id="HEP_assays" class="all_assays"
			style="display: none; color: Gray; margin-left: 20px;">
			<span style="color: black; font-weight: bold;">iCell
				hepatocyte assays:</span><br> <input type="checkbox" disabled
				class="all_assays" id="HEP_Hoechst" name="HEP_Hoechst"
				value='HEP_Hoechst'> <span id="HEP_Hoechst_B"> Nuclei
				staining<br>
			</span> <input type="checkbox" disabled class="all_assays" id="HEP_Mito"
				name="HEP_Mito" value='HEP_Mito'> <span id="HEP_Mito_B">
				Mitochondrial Integrity<br>
			</span> <input type="checkbox" disabled class="all_assays"
				id="HEP_CalceinAM" name="HEP_CalceinAM" value='HEP_CalceinAM'>
			<span id="HEP_CalceinAM_B"> Cell Viability<br></span> <input
				type="checkbox" disabled class="all_assays" id="HEP_LipidTOX"
				name="HEP_LipidTOX" value='HEP_LipidTOX'> <span
				id="HEP_LipidTOX_B"> Lipid Accumulation<br></span> <!-- <input
				type="checkbox" disabled class="all_assays" id="HEP_Seq"
				name="HEP_Seq" value='HEP_Seq'> <span id="HEP_Seq_B">
				TompOseq<br>
			</span>  --><br>
		</div>
		<div id="ENDO_assays" class="all_assays"
			style="display: none; color: Gray; margin-left: 20px;">
			<span style="color: black; font-weight: bold;">ENDO assays:</span><br>
			<input type="checkbox" disabled class="all_assays" id="ENDO_Cyto"
				name="ENDO_Cyto" value='ENDO_Cyto'> <span id="ENDO_Cyto_B">
				Cyto<br>
			</span> <input type="checkbox" disabled class="all_assays" id="ENDO_CTG"
				name="ENDO_CTG" value='ENDO_CTG'> <span id="ENDO_CTG_B">
				ATP Content<br>
			</span> <input type="checkbox" disabled class="all_assays" id="ENDO_TubForm"
				name="ENDO_TubForm" value='ENDO_TubForm'> <span
				id="ENDO_TubForm_B"> Tube Formation<br></span> <input
				type="checkbox" disabled class="all_assays" id="ENDO_Seq"
				name="ENDO_Seq" value='ENDO_Seq'> <span id="ENDO_Seq_B">
				TompOseq<br>
			</span> <br>
		</div>
		<div id="HUV_assays" class="all_assays"
			style="display: none; color: Gray; margin-left: 20px;">
			<span style="color: black; font-weight: bold;">HUVEC assays:</span><br>
			<input type="checkbox" disabled class="all_assays" id="HUV_Cyto"
				name="HUV_Cyto" value='HUV_Cyto'> <span id="HUV_Cyto_B">
				Cyto<br>
			</span> <input type="checkbox" disabled class="all_assays" id="HUV_CTG"
				name="HUV_CTG" value='HUV_CTG'> <span id="HUV_CTG_B">
				ATP Content<br>
			</span> <input type="checkbox" disabled class="all_assays" id="HUV_TubForm"
				name="HUV_TubForm" value='HUV_TubForm'> <span
				id="HUV_TubForm_B"> Tube Formation<br></span> <input
				type="checkbox" disabled class="all_assays" id="HUV_Seq"
				name="HUV_Seq" value='HUV_Seq'> <span id="HUV_Seq_B">
				TempOseq<br>
			</span> <br>
		</div>
		<div id="Neur_assays" class="all_assays"
			style="display: none; color: Gray; margin-left: 20px;">
			<span style="color: black; font-weight: bold;">iCell neuron
				assays:</span><br> <input type="checkbox" disabled class="all_assays"
				id="Neur_Neur" name="Neur_Neur" value='Neur_Neur'> <span
				id="Neur_Neur_B"> Neurons<br></span> <input type="checkbox"
				disabled class="all_assays" id="Neur_CTG" name="Neur_CTG"
				value='Neur_CTG'> <span id="Neur_CTG_B"> ATP Content<br></span>
			<input type="checkbox" disabled class="all_assays" id="Neur_Seq"
				name="Neur_Seq" value='Neur_Seq'> <span id="Neur_Seq_B">
				TompOseq<br>
			</span> <br>
		</div>
		<div id="Macro_assays" class="all_assays"
			style="display: none; color: Gray; margin-left: 20px;">
			<span style="color: black; font-weight: bold;">iCell
				macrophage assays:</span><br> <input type="checkbox" disabled
				class="all_assays" id="Macro_Hoechst" name="Macro_Hoechst"
				value='Macro_Hoechst'> <span id="Macro_Hoechst_B">
				Nuclei staining<br>
			</span> <input type="checkbox" disabled class="all_assays" id="Macro_Mito"
				name="Macro_Mito" value='Macro_Mito'> <span
				id="Macro_Mito_B"> Mitochondrial Integrity<br></span> <input
				type="checkbox" disabled class="all_assays" id="Macro_CalceinAM"
				name="Macro_CalceinAM" value='Macro_CalceinAM'> <span
				id="Macro_CalceinAM_B">Cell Viability<br></span> <input
				type="checkbox" disabled class="all_assays" id="Macro_Phag"
				name="Macro_Phag" value='Macro_Phag'> <span
				id="Macro_Phag_B"> Phagocytosis<br></span> <input
				type="checkbox" disabled class="all_assays" id="Macro_Cyto"
				name="Macro_Cyto" value='Macro_Cyto'> <span
				id="Macro_Cyto_B"> Cytokines<br></span> <input type="checkbox"
				disabled class="all_assays" id="Macro_MacroOut"
				name="Macro_MacroOut" value='Macro_MacroOut'> <span
				id="Macro_MacroOut_B">Macroite Outgrowth<br></span> <input
				type="checkbox" disabled class="all_assays" id="Macro_Seq"
				name="Macro_Seq" value='Macro_Seq'> <span id="Macro_Seq_B">
				TompOseq<br>
			</span> <br>
		</div>
		<c:forEach var="element" items="${England_cell_lines}"
			varStatus="status">
			<div id="${element}_assays" class="all_assays"
				style="display: none; color: Gray; margin-left: 20px;">
				<span style="color: black; font-weight: bold;">Cell line
					${element} assays:</span><br> <input type="checkbox" disabled
					class="all_assays" id="${element}_CMFDA" name="${element}_CMFDA"
					value='${element}_CMFDA'> <span id="${element}_CMFDA_B">Cell
					membrane integrity<br>
				</span> <input type="checkbox" disabled class="all_assays"
					id="${element}_ROS" name="${element}_ROS" value='${element}_ROS'>
				<span id="${element}_ROS_B">Reactive Oxygen Species<br></span>
				<input type="checkbox" disabled class="all_assays"
					id="${element}_CASP" name="${element}_CASP" value='${element}_CASP'>
				<span id="${element}_CASP_B">Apoptosis<br></span> <input
					type="checkbox" disabled class="all_assays" id="${element}_PROT"
					name="${element}_PROT" value='${element}_PROT'> <span
					id="${element}_PROT_B">Protein synthesis inhibition<br></span>
				<input type="checkbox" disabled class="all_assays"
					id="${element}_ATP" name="${element}_ATP" value='${element}_ATP'>
				<span id="${element}_ATP_B">ATP Quantitation Assay<br></span> <br>
			</div>
		</c:forEach>
	</div>
	<!-- end of div for assays. -->

</body>
<!-- jQuery -->
<script src="/CAT-APP-PROJECT/resources/js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<!-- JQUERY SCRIPTS -->
<script src="/CAT-APP-PROJECT/resources/js/jquery-1.10.2.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script src="/CAT-APP-PROJECT/resources/js/jquery.metisMenu.js"></script>
<!-- CUSTOM SCRIPTS -->
<script src="/CAT-APP-PROJECT/resources/js/custom.js"></script>
<script src="/CAT-APP-PROJECT/resources/js/Download.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="/CAT-APP-PROJECT/resources/js/catAppDown.js"></script>
<!-- <script src="/CAT-APP-PROJECT/resources/js/easyPaginate.js"></script> -->
<script type="text/plain"
	src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>



<script src="/CAT-APP-PROJECT/resources/js/metisMenu.min.js"></script>
<!-- CUSTOM SCRIPTS -->
<script src="/CAT-APP-PROJECT/resources/js/sb-admin-2.js"></script>

</html>