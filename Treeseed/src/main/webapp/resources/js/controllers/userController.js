
var treeSeedAppControllers = angular.module('treeSeed.controller');


treeSeedAppControllers.controller('nonProfitRegistrationController', function($http, $scope){
	
	$scope.requestObject = {};
	$scope.requestObject.pageNumber = "";
	$scope.requestObject.pageSize = 0;
	$scope.requestObject.direction = "";
	$scope.requestObject.sortBy = [];
	$scope.requestObject.searchColumn = "";
	$scope.requestObject.searchTerm = "";
	/*
	$scope.requestObject.Nonprofit = {};
	$scope.requestObject.Nonprofit.UserGeneral = {};
	$scope.requestObject.Nonprofit.UserGeneral.email = "";
	$scope.requestObject.Nonprofit.UserGeneral.password = "";
	$scope.requestObject.Nonprofit.name = "";
	$scope.requestObject.Nonprofit.mision = "";
	$scope.requestObject.Nonprofit.reason = "";
	$scope.requestObject.Nonprofit.description = "";
	$scope.requestObject.Nonprofit.webPage = "";
	$scope.requestObject.Nonprofit.profilePicture = "";
	$scope.requestObject.Nonprofit.mainPicture = "";
	$scope.requestObject.Nonprofit.cause = "";
	$scope.requestObject.Nonprofit.country = "";
	$scope.requestObject.Nonprofit.banKAccount = "";
	*/
	
	$scope.requestObject.UserGeneral = {};
	$scope.requestObject.UserGeneral.email = "";
	$scope.requestObject.UserGeneral.password = "";
	$scope.create = function(event) {
	
	if(this.registerNonProfit.$valid){
		this.onError = false;
		
		$http.post('rest/protected/users/nonProfit/create', $scope.requestObject)
		.success(function(response) {

			if(response.code === 200){
				$modalInstance.close();
			}
			
		});
		
		}else{
			this.onError = true;
		}
	};
});

 