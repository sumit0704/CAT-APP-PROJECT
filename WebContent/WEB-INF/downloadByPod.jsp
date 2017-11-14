<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="/CAT-APP-PROJECT/resources/js/Download.js"></script>

<style>
.scrollerdiv {
	height: 150px;
	overflow-y: scroll;
}
</style>
<script type="text/javascript">
	$(function() {
		var filter = $('#phenotypes');
		filter.on('change', function() {
			if (this.selectedIndex)
				return; //not `Select All`
			filter.find('option:gt(0)').prop('selected', true);
			filter.find('option').eq(0).prop('selected', false);
		});
	});
</script>
<div class="row">

	<div class="col-lg-6">

		<div class="form-group" id="step1">
			<label> Please select a cell line:</label> <select name="cellline"
				id="cellline" onchange="getAssaysForCellLines()">
				<c:forEach var="item" items="${cell}">
					<option value="${item.key}">${item.value}</option>
				</c:forEach>
			</select> <a href="#" onclick="getAssaysForCellLines()" id="arrowCell"><span
				class="fa fa-chevron-right"></span></a>

			<!--  <button type="submit">submit</button>-->
		</div>



		<!--  ${assay}-->
		<div id="step2" style="display: none" class="form-group">
			<div id="CM_assays" class="all_assays" style="display: none;">
				<label>Please select an assay name:</label> <select
					id='CM_assay_select' name="assay" onchange='getNextAttribute()'>

				</select> <a href="#" onclick="getNextAttribute()"><span
					class="fa fa-chevron-right"></span></a>

			</div>

		</div>

		<div id="step3" style="display: none" class="form-group">
			<div id="Ph_div" class="all_assays" style="display: none;">
				<label>Please select a Phenotype:</label> <select id='phenotype'
					name='phenotype' onchange='getTimePoints()'>

				</select> <a href="#" onclick="getTimePoints()"><span
					class="fa fa-chevron-right"></span></a>

			</div>

		</div>


		<div id="step4" class="form-group" style="display: none;">
			<div id="timepoints_4" class="all_time_div" style="display:;">

				<label>Please select a time point:</label> <select
					id="timepoints_4_select" name="timepoints_4_select"
					onchange="step4_to_5()">

				</select> <a href="#" onclick="step4_to_5()"><span
					class="fa fa-chevron-right"></span></a>

			</div>

		</div>
		<div id="step5" class="form-group" style="display: none; float: left;">
			<div class="form-group" id="dilution_1" onchange="step5_to_6()">
				<label>Select a concentration: </label> <input type="radio"
					name="dilution" id="11" value="x1" /> 1x &nbsp;&nbsp;&nbsp; <input
					type="radio" name="dilution" id="21" value="x10" /> 10x
				&nbsp;&nbsp;&nbsp; <input type="radio" name="dilution" id="31"
					value="x100" /> 100x &nbsp;&nbsp;&nbsp; <input type="radio"
					name="dilution" id="41" value="x1000" /> 1000x <a href="#"
					onclick="step5_to_6()" id="dilution_button"> <span
					class="fa fa-chevron-right"></span></a>


			</div>
			<div class="form-group" id="dilution_0"
				style="color: LightSteelBlue; display: none;">
				<label class="sr-only" for="form-facebook">Dilution not
					applicable</label> <label style="color: LightSteelBlue;"> Dilution
					not applicable: &nbsp;</label> <a href="#"><span
					class="fa fa-chevron-right" style="color: LightSteelBlue;"></span></a>


			</div>
		</div>


		<div id="step6" class="form-group" style="display: none;">
			<div id="file_button" style="margin-left: 80px; display: none;">
				<br></br> <input type="submit" class="btn btn-primary"
					name="download" style="border-radius: 5px;" value="Download">
				<!-- <input type="submit" class="btn btn-default" name="json"
											style="border-radius: 5px;" value="Convert to Json"> -->
			</div>


		</div>
		</form>
	</div>
</div>