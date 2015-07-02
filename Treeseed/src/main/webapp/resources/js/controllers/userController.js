
var treeSeedAppControllers = angular.module('treeSeed.controller');


treeSeedAppControllers.controller('nonProfitRegistrationController', function($http, $scope){
	
	$scope.requestObject = {};
	$scope.requestObject.pageNumber = "";
	$scope.requestObject.pageSize = 0;
	$scope.requestObject.direction = "";
	$scope.requestObject.sortBy = [];
	$scope.requestObject.searchColumn = "";
	$scope.requestObject.searchTerm = "";
	
	$scope.requestObject.userGeneral = {};
	$scope.requestObject.userGeneral.email = "";
	$scope.requestObject.userGeneral.password = "";
	$scope.requestObject.userGeneral.nonProfit = {};
	$scope.requestObject.userGeneral.nonProfit.name = "";
	$scope.requestObject.userGeneral.nonProfit.mision = "";
	$scope.requestObject.userGeneral.nonProfit.reason = "";
	$scope.requestObject.userGeneral.nonProfit.description = "";
	$scope.requestObject.userGeneral.nonProfit.webPage = "";
	$scope.requestObject.userGeneral.nonProfit.profileImage = "";
	$scope.requestObject.userGeneral.nonProfit.mainImage = "";
	$scope.requestObject.userGeneral.nonProfit.cause = "";
	$scope.requestObject.userGeneral.nonProfit.country = "";
	$scope.requestObject.userGeneral.nonProfit.bankAccount = "";
	
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

 