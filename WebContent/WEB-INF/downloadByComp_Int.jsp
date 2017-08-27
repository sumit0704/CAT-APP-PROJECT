<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page pageEncoding="UTF-8" %>


<style>

.scrollerdiv {
  height: 150px; 
  overflow-y: scroll; 
}
</style>
<div class="row">
		<form action="DownloadbyCompoundServlet" method="post">
	<div class="col-lg-8 col-md-offset-2">
		<div class="form-group" align="left">


			<!-- /input-group -->

			<%-- <c:forEach var="chemicals" items="${chemicals}">
				<li class="chemical">&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="radio" name="chemicals" id="chemicals"
					value=<c:out value="${chemicals.key}"/>
					> &nbsp;<c:out
						value="${chemicals.value}" />

				</li>
			</c:forEach> --%>
			
			<div class="input-group custom-search-form" >
							<label> Please select a compound:</label><input type="text" class="form-control" id="chemsearch"
								onkeyup="filterchem()" placeholder="Search Compounds...">

						</div> <!-- /input-group -->
					</li>
					&nbsp;
					<div class=" scrollerdiv pre-scrollable" >
					<c:forEach var="chemicals" items="${chemicals}">
						<li class="chemical">&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="radio" name="chemicals" id="chemicals"
							value=<c:out value="${chemicals.key}"/>> &nbsp;<c:out
								value="${chemicals.value}" />

						</li>
					</c:forEach>
					</div>
					<%-- </div>


			<label> Please select a Compound:</label> <select name="compound">
				<c:forEach var="item" items="${chemicals}">
					<option value="${item.key}">${item.value}</option>
				</c:forEach>
			</select> --%>

		</div>
		
		<div class="form-group" align="left">
			<label> Please select a cellLine:</label> <select name="cellLines" id="cellLines" onchange='selectphenotypesForDownload()'>
				<c:forEach var="item" items="${cell}">
					<option value="${item.key}">${item.value}</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group" align="left">
			<label> Please select a phenoType:</label> <select name="phenotypes" id="phenotypes">
				<option value='0'>---Select One---</option>
			</select>
		</div>

		
		
		<div class="form-group">
		<input type="submit" class="btn btn-primary"
		name="download" style="border-radius: 5px;" value="Download">
		</div>
	</div>
	</form>
</div>