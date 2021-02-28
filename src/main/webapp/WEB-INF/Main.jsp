
<div class="container-fluid h-75">
	<div class="row justify-content-center h-15">
		<h1>Welcome to CryptApp</h1>
	</div>
	<div class="row justify-content-center h-10">
		<h4>Your personnal Crypto Currency Manager</h4>
	</div>
	<div class="row align-items-center justify-content-around h-25 ">
		<div class="col-xs align-self-center">
			<c:url value="CRUDCurrency" var="crypto"></c:url>
			<a href="${crypto }">
				<button type="button" class="btn btn-info custom">To
					CryptoCurrency Management</button>
			</a>
		</div>
		<div class="col-xs align-self-center">
			<c:url value="Assets" var="asset"></c:url>
			<a href="${asset}">
				<button type="button" class="btn btn-info custom">To my
					Assets Management</button>
			</a>
		</div>
		<div class="col-xs align-self-center">
			<c:url value="list" var="listAsset"></c:url>
			<a href="${listAsset}">
				<button type="button" class="btn btn-info custom">List All
					My Assets</button>
			</a>
		</div>
	</div>
</div>