<%@ page language="java" contentType="text/html;
charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<script src="https://use.fontawesome.com/releases/v5.15.2/js/all.js"
	data-auto-replace-svg="nest"></script>
</head>

<body class="h-100">
	<nav class="navbar static-top navbar-expand-lg navbar-light bg-light">
		<c:url value="/index.html" var="index">
		</c:url>
		<a class="navbar-brand" href="${index }"> CryptApp Total Assets :
			<c:choose>
				<c:when test="${total > 0}">
					<span class="navbar-text text-success" id="totalAssets"> <c:out
							value="${total }">
						</c:out>
					</span>
				</c:when>
				<c:otherwise>
					<span class="navbar-text text-danger" id="totalAssets"> <c:out
							value="${total }">
						</c:out>
					</span>
				</c:otherwise>
			</c:choose>

		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item">
					<button type="button" class="btn" data-toggle="modal"
						data-target="#newAsset" id="newAssetButton">
						<i class='fas fa-plus' style='font-size: 36px'></i>
					</button>
				</li>
				<li class="nav-item"><c:url value="/list" var="recap">
					</c:url> <a class="nav-link" href="${recap }">Recap</a></li>
			</ul>
		</div>
	</nav>
	<div class="modal fade" id="newAsset" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add a new Asset</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div id="idselectorAsset">
						<label for="id">Name</label> <select name="id" class="id">
							<c:forEach items="${list}" var="crypto">
								<option value="${ crypto.id}">${ crypto.name}</option>
							</c:forEach>
						</select>
						<button type="button" class="btn btn-primary" id="selectIdAsset">Select</button>
					</div>
					<div id="createAssetData">
						<label for="name">Number of Currency</label> <input type="number"
							name="numberofaction" class="numberofaction">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="createAsset"
						disabled>Create</button>
				</div>
			</div>
		</div>
	</div>