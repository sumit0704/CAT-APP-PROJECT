<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="/CAT-APP-PROJECT/resources/js/Download.js"></script>

<style>
.scrollerdiv {
	height: 150px;
	overflow-y: scroll;
}
</style>
<div>


	<div class="form-group" id="step1">
		<label> Please select a cell line:</label> <select name="cellline"
			id="cellline">
			<c:forEach var="item" items="${cell}">
				<option value="${item.key}">${item.value}</option>
			</c:forEach>
		</select>
	</div>
	<div id="file_display" class="col-md-6">

		<form name="DFile" action="DownloadFileServlet"
			onSubmit="return validateInput(event);">
			
			<div id="file_button" style="margin-left: 80px;">
				<br></br> <input type="submit" class="btn btn-primary"
					name="download" style="border-radius: 5px;" value="Download">
			</div>
		</form>
	</div>

</div>