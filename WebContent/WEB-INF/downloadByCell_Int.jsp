<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.catapp.entity.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<script type="text/javascript">
	$(function() {
		var filter = $('#phenotype');
		filter.on('change', function() {
			if (this.selectedIndex)
				return; //not `Select All`
			filter.find('option:gt(0)').prop('selected', true);
			filter.find('option').eq(0).prop('selected', false);
		});
	});
</script>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" /> -->
<title>CAT-APP Upload</title>

<div class="row">
	<form action="DownloadbyCellLineServlet" method="post">
		<div class="col-lg-6 col-md-offset-2">
			<div class="form-group">
				<label> Please select a cellLine:</label> <select name="cellLine"
					id="cellLine" onclick='selectphenotypesForDownloadCell()'>
					<option value="--Select--">--Select--</option>
					<c:forEach var="item" items="${cell}">
						<option value="${item.key}">${item.value}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<label> Please select a phenoType:</label> <select name="phenotype"
					multiple="multiple" id="phenotype" onchange='selectTimepoint()'>
<option value="--Select--">--Select--</option>

				</select>
			</div>

			<div class="form-group">
				<label> Please select a time-point:</label> <select name="timepoint"
					id="timepoint">
					<c:forEach var="item" items="${time}">
						<option value="${item.key}">${item.value}</option>
					</c:forEach>
				</select>
			</div>



			<div class="form-group">
				<button type="submit" id="download" class="btn btn-primary">Download</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>
