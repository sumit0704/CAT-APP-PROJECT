<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<div class="row">
<form action="DownloadbyCellLineServlet" method="post">
	<div class="col-lg-6 col-md-offset-2">
	<div class="form-group">
			<label> Please select a cellLine:</label> <select name="cellLine" id="cellLine"  onchange='selectphenotypesForDownloadCell()'>
				<c:forEach var="item" items="${cell}">
					<option value="${item.key}">${item.value}</option>
				</c:forEach>
			</select>
	</div>
		
		<div class="form-group">
			<label> Please select a phenoType:</label> <select name="phenotype" id="phenotype">
					<option value="0">---Select One---</option>
				
			</select>
		</div>
		
		<div class="form-group">
			<label> Please select a time-point:</label> <select name="timepoint" id="timepoint">
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