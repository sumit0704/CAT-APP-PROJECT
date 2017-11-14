<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="/CAT-APP-PROJECT/resources/js/Download.js"></script>

<style>
.scrollerdiv {
	height: 150px;
	overflow-y: scroll;
}
</style>
<!-- script type="text/javascript">
	$(function() {
		var filter = $('#phenotypes');
		filter.on('change', function() {
			if (this.selectedIndex)
				return; //not `Select All`
			filter.find('option:gt(0)').prop('selected', true);
			filter.find('option').eq(0).prop('selected', false);
		});
	});
</script> -->
<div class="row">
	<form action="DownloadbyCompoundServlet" method="post">
		<div class="col-lg-8 col-md-offset-2">
			<div class="form-group" align="left">

				<div class="input-group custom-search-form">
					<label> Please select a compound:</label><input type="text"
						class="form-control" id="chemsearch" onkeyup="filterchem()"
						placeholder="Search Compounds...">

				</div>
				<!-- /input-group -->
				</li> &nbsp;
				<div class=" scrollerdiv pre-scrollable">
					<c:forEach var="chemicals" items="${chemicals}">
						<li class="chemical">&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="radio" name="chemicals" id="chemicals"
							value=<c:out value="${chemicals.key}"/>> &nbsp;<c:out
								value="${chemicals.value}" />

						</li>
					</c:forEach>
				</div>


			</div>

			<div class="form-group" align="left">
				<label> Please select a cellLine:</label> <select name="cellLines"
					id="cellLines" onchange='selectphenotypesForDownload()'>
					<option value="--Select--">--Select--</option>
					<c:forEach var="item" items="${cell}">
						<option value="${item.key}">${item.value}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group" align="left">
				<label> Please select a phenoType:</label> <select name="phenotypes"
					multiple="multiple" id="phenotypes">


				</select>
			</div>



			<div class="form-group" style="margin-left: -1px;">
				<input type="submit" class="btn btn-primary" name="download"
					style="border-radius: 5px;" value="Download"> <input
					type="submit" class="btn btn-default" name="json"
					style="border-radius: 5px;" value="Download Json">
			</div>
		</div>
		</form>
</div>
