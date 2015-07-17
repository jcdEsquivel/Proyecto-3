var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('campaingCreateController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $rootScope) {
	$scope.percent = 0;
	$scope.maxCarac= 1000;
	$scope.stateInput1 = false;
	$scope.stateInput2 = false;
	$scope.stateInput3 = false;
	$scope.stateInput4 = false;
	$scope.stateInput5 = false;
	$scope.stateInput6 = false;
	$scope.minDate1 = new Date();
	$scope.minDate2 = new Date();

	$scope.campaign = {};
	$scope.uploadImage = false;
	$scope.campaign.name = "";
	$scope.campaign.description = "";
	$scope.campaign.startDate;
	$scope.campaign.dueDate;
	$scope.campaign.amountGoal = "";
	$scope.image = "";
	
	$scope.minDate=function(){
		$scope.mindate2 = $scope.mindate1;
	}

	$scope.create = function(event) {

		this.onError = false;

		$scope.upload = $upload.upload({
			url : 'rest/protected/campaing/create',
			data : {
				name : $scope.campaign.name,
				description : $scope.campaign.description,
				date1 : $scope.campaign.startDate,
				date2 : $scope.campaign.dueDate,
				amount : $scope.campaign.amountGoal,
				idNonprofit: $scope.currentUser.idUser				
			},
			file : $scope.image,
		}).success(function(response) {

			/*$state.go('treeSeed.campaing', {
				campaign : response.campaignId
			});*/
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
					$scope.percent = $scope.percent + 10;
					$scope.stateInput3 = true;
				}

			} else {
				if ($scope.stateInput3) {
					$scope.percent = $scope.percent - 10;
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
		case 6:
			if (valid) {
				if (!$scope.stateInput6) {
					$scope.percent = $scope.percent + 10;
					$scope.stateInput6 = true;
				}

			} else {
				if ($scope.stateInput6) {
					$scope.percent = $scope.percent - 10;
					$scope.stateInput6 = false;
				}
			}
			
			break;
		}
	}

});