var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('campaingCreateController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $rootScope) {
	$scope.percent = 0;
	$scope.stateInput1 = false;
	$scope.stateInput2 = false;
	$scope.stateInput3 = false;
	$scope.stateInput4 = false;
	$scope.stateInput5 = false;
	$scope.mindate = new Date();

	$scope.campaing = {};
	$scope.uploadImage = false;
	$scope.campaing.name = "";
	$scope.campaing.description = "";
	$scope.campaing.date = new Date();
	$scope.campaing.amount = "";
	$scope.image = "";

	$scope.hola = function() {
		alert("hola");
	}
	$scope.create = function(event) {

		this.onError = false;

		$scope.upload = $upload.upload({
			url : 'rest/protected/campaing/create',
			data : {
				email : $scope.campaing.name,
				description : $scope.campaing.description,
				date : $scope.campaing.date,
				amount : $scope.campaing.amount
			},
			file : $scope.image,
		}).success(function(response) {

			$state.go('treeSeed.campaing', {
				campaing : response.campaingId
			});
		})

	};

	$scope.$on('profilePicture', function(event, args) {
		$scope.image = args;
		var file = args;
		var imageType = /image.*/;

		if (file.type.match(imageType)) {
			var reader = new FileReader();

			reader.onload = function(e) {
				var img = new Image();
				img.src = reader.result;
				fileDisplayArea.src = img.src;
			}
			reader.readAsDataURL(file);

			$scope.uploadImage = true;

		} else {
			$scope.uploadImage = false;
			alert("File not supported!");
		}
		$scope.progressControl(5, $scope.uploadImage);
	});

	$scope.progressControl = function(input, valid) {

		switch (input) {
		case 1:
			if (valid) {
				if (!$scope.stateInput1) {
					$scope.percent = $scope.percent + 20;
					$scope.stateInput1 = true;
				}

			} else {
				if ($scope.stateInput1) {
					$scope.percent = $scope.percent - 20;
					$scope.stateInput1 = false;
				}
			}
			break;
		case 2:
			if (valid) {
				if (!$scope.stateInput2) {
					$scope.percent = $scope.percent + 20;
					$scope.stateInput2 = true;
				}

			} else {
				if ($scope.stateInput2) {
					$scope.percent = $scope.percent - 20;
					$scope.stateInput2 = false;
				}
			}
			break;
		case 3:
			if (valid) {
				if (!$scope.stateInput3) {
					$scope.percent = $scope.percent + 20;
					$scope.stateInput3 = true;
				}

			} else {
				if ($scope.stateInput3) {
					$scope.percent = $scope.percent - 20;
					$scope.stateInput3 = false;
				}
			}
			break;
		case 4:
			if (valid) {
				if (!$scope.stateInput4) {
					$scope.percent = $scope.percent + 20;
					$scope.stateInput4 = true;
				}

			} else {
				if ($scope.stateInput4) {
					$scope.percent = $scope.percent - 20;
					$scope.stateInput4 = false;
				}
			}
			break;
		case 5:
			if (valid) {
				if (!$scope.stateInput5) {
					$scope.percent = $scope.percent + 20;
					$scope.stateInput5 = true;
				}

			} else {
				if ($scope.stateInput5) {
					$scope.percent = $scope.percent - 20;
					$scope.stateInput5 = false;
				}
			}
			break;
		}
	}

});