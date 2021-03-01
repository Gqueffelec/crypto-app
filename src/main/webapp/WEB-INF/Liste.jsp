<div>
	<table class="table">
		<thead class="thead-light">
			<tr>
				<td>Name</td>
				<td>Count</td>
				<td>Buying Price</td>
				<td>Actual Price</td>
				<td>Delta</td>
				<td></td>
			</tr>
		</thead>
		<tbody id="assetslisting">

		</tbody>
	</table>
</div>
<div class="modal fade" id="updateAsset" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Updated an Asset</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div id="updateAssetData">
					<label for="name">Number of Currency</label> <input type="number"
						name="numberofaction" class="numberofaction">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="updateAssetButton"
					disabled>Update</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="removeAsset" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Remove an Asset</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div id="removeCheck">
					<p>Are you sure ?</p>
					<label for="name">Yes</label> <input type="checkbox" name="yes"
						class="yes">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="removeAssetButton"
					disabled>Remove</button>
			</div>
		</div>
	</div>
</div>