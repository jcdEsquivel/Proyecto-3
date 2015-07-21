/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('campaignSearchController', function($scope,
		$http, $location, $modal, $log, $timeout) {

	$scope.campaign = {};
	$scope.campaign.id="";
	$scope.campaign.name = "";
	$scope.campaign.rangeDate = "";
	$scope.campaign.nonProfit = {};
	$scope.campaign.nonProfit.name = "";
	$scope.campaign.nonProfit.cause = "";
	$scope.campaign.cause={id:'',name:''};
	$scope.requestObject1 = {};
	$scope.requestObject2 = {};
	$scope.itemPerPage = [ 10, 25, 50, 100 ];
	$scope.currentPage = 1;
	$scope.totalItems = 5;
	
	$scope.requestObject = {};
	$scope.requestObject.name = '';
	$scope.requestObject.nonprofitName = '';
	$scope.requestObject.causeId = '';
	$scope.requestObject.startDate = "";
	$scope.requestObject.dueDate = "";
	
	$scope.requestObject.pageNumber = 1;
	$scope.requestObject.pageSize = 10;
	$scope.requestObject.direction = "DESC";
	$scope.requestObject.sortBy = [];
	$scope.requestObject.searchColumn = "ALL";
	$scope.requestObject.searchTerm = "";

	$scope.init = function() {

		$scope.requestObject1.lenguage = $scope.selectLang;
		$scope.requestObject1.type = "cause";
		$http.post('rest/protected/catalog/getAllCatalog',
		$scope.requestObject1).then(function(response) {
			$scope.selectSortOptionsCause =  response.data.catalogs;
		});
	}

	$scope.init();
	
	$scope.getDateFormat = function(date)
	{
		if(date === undefined || date === ""){
            return "";
        }
		var parts = date.split('-');
		return new Date(parts[0],parts[1]-1,parts[2]);	
	}

	$scope.searchCampaign = function(page) {

		var dates = $scope.campaign.rangeDate.split(' - ');
		
		console.log(dates);
		
		var startDate = $scope.getDateFormat(dates[0]);
		var endDate = $scope.getDateFormat(dates[1]);
		
		console.log("sd " + startDate);
		console.log("ed " + endDate);
		
		if (startDate != "")
		{
			$scope.requestObject.startDate = startDate.getTime();
		}
		
		if(endDate != "")
		{
			$scope.requestObject.dueDate = endDate.getTime();
		}
	
		$scope.requestObject.pageNumber = page;
		$scope.requestObject.name = $scope.campaign.name;
		$scope.requestObject.nonprofitName = $scope.campaign.nonProfit.name;
		$scope.requestObject.causeId = $scope.campaign.cause.id;
		//console.log(JSON.stringify($scope.requestObject));

		$http.post('rest/protected/campaing/advanceGet',
			$scope.requestObject).success(function(mydata, status) {
			$scope.campaigns = mydata.campaigns;
			$scope.totalItems = mydata.totalElements;
			
		}).error(function(mydata, status) {
			console.log(status);
			console.log("No data found");
		});

	

	};
	
	$scope.pageChangeHandler = function(num) {
		$scope.searchCampaign(num);
	};

});

treeSeedAppControllers.controller('campaingCreateController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $rootScope,Session) {
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
			$state.go('treeSeed.nonProfit', {nonProfitId: Session.userId});
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