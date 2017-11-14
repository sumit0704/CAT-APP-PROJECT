<div class="col-lg-6">
	<div class="panel panel-default">
		<!-- <div class="panel-heading">Select Data</div> -->
		<!-- .panel-heading -->
		<div class="panel-body">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" id="sacl">Select cell lines</a>

					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse">
					<div class="panel-body">
						<div class=" scrollerdiv pre-scrollable">


							<c:forEach var="item" items="${cell}">

								<input type="checkbox" name="cellLine" value="${item.value}">${item.value}
										</input>

							</c:forEach>



						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseTwo" id="sacl">Select assay</a>

					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse">
					<div class="panel-body">
						<div class=" scrollerdiv pre-scrollable">
							<table class="table table-hover,table-fixed">

								<c:forEach var="item" items="${cell}">
									<tr class="cell">
										<td><input type="radio" name="cell" id="cell"
											onclick="displayPhenoData()"
											value=<c:out value="${item.key}"/>> &nbsp; <c:out
												value="${item.value}" /></td>
									</tr>

									</li>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>


			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseThree" id="sacl">Select phenotypes</a>

					</h4>
				</div>
				<div id="collapseThree" class="panel-collapse collapse">
					<div class="panel-body">
						<div class=" scrollerdiv pre-scrollable">
							<table class="table table-hover,table-fixed">

								<c:forEach var="item" items="${cell}">
									<tr class="cell">
										<td><input type="radio" name="cell" id="cell"
											onclick="displayPhenoData()"
											value=<c:out value="${item.key}"/>> &nbsp; <c:out
												value="${item.value}" /></td>
									</tr>

									</li>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseFour" id="sacl">Select timepoint</a>

					</h4>
				</div>
				<div id="collapseFour" class="panel-collapse collapse">
					<div class="panel-body">
						<div class=" scrollerdiv pre-scrollable">
							<table class="table table-hover,table-fixed">

								<c:forEach var="item" items="${cell}">
									<tr class="cell">
										<td><input type="radio" name="cell" id="cell"
											onclick="displayPhenoData()"
											value=<c:out value="${item.key}"/>> &nbsp; <c:out
												value="${item.value}" /></td>
									</tr>

									</li>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>