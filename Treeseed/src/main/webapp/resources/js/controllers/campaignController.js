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
	$scope.requestObject1 = {};
	$scope.requestObject2 = {};
	$scope.itemPerPage = [ 10, 25, 50, 100 ];
	$scope.currentPage = 1;
	$scope.totalItems = 5;
	//$scope.campaign.creationDate = "";
	//$scope.campaign.dueDate = "";
	
	$scope.requestObject = {};
	$scope.requestObject.name = '';
	$scope.requestObject.nonprofitName = '';
	$scope.requestObject.causeId = '';
	$scope.requestObject.fechaInicio = "";
	$scope.requestObject.fechaFin = "";
	
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
		     //$scope.campaign.cause =  response.data.catalogs[0];
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
			$scope.requestObject.fechaInicio = startDate.getTime();
		}
		
		if(endDate != "")
		{
			$scope.requestObject.fechaFin = endDate.getTime();
		}
	
		$scope.requestObject.pageNumber = page;
		$scope.requestObject.name = $scope.campaign.name;
		$scope.requestObject.nonprofitName = $scope.campaign.nonProfit.name;
		$scope.requestObject.causeId = $scope.campaign.cause;

		$http.post('rest/protected/campaign/advanceGet',
			$scope.requestObject).success(function(mydata, status) {
			$scope.campaigns = mydata.campaigns;
		    console.log(mydata.campaigns);
			$scope.totalItems = mydata.totalElements;
			
		}).error(function(mydata, status) {
			console.log(status);
			console.log("No data found");
		});

		$scope.pageChangeHandler = function(num) {
			$scope.searchNonProfit(num);
		};

	};

});
 