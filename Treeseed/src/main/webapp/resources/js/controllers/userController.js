
var treeSeedAppControllers = angular.module('treeSeed.controller');


treeSeedAppControllers.controller('nonProfitRegistrationController', function($http, $scope, $upload, $state){
$scope.uploadImage=false;
	
	$scope.requestObject = {};
	$scope.requestObject.pageNumber = "";
	$scope.requestObject.pageSize = 0;
	$scope.requestObject.direction = "";
	$scope.requestObject.sortBy = [];
	$scope.requestObject.searchColumn = "";
	$scope.requestObject.searchTerm = "";
	
	$scope.requestObject.nonprofit = {};
	$scope.requestObject.nonprofit.userGeneral = {};
	$scope.requestObject.nonprofit.userGeneral.email = "";
	$scope.requestObject.nonprofit.userGeneral.password = "";
	$scope.requestObject.nonprofit.name = "";
	$scope.requestObject.nonprofit.cause = "";
	$scope.requestObject.nonprofit.country = "";
	
	
	
	$scope.$on('profilePicture', function(event, args){
		$scope.image = args;
		$scope.uploadImage=true;
	});
	
	$scope.create = function(event) {
	
	if(this.registerNonProfit.$valid){
		this.onError = false;
		
		if($scope.uploadImage==true){
			$scope.upload = $upload.upload({
				url : 'rest/protected/users/registerNonProfit',
				data : {
					email:$scope.requestObject.nonprofit.userGeneral.email,
					password:$scope.requestObject.nonprofit.userGeneral.password,
					name:$scope.requestObject.nonprofit.name,
					cause:$scope.requestObject.nonprofit.cause,
					country:$scope.requestObject.nonprofit.country
				},
				file : $scope.image,
			}).success(function(){
				$state.go('treeSeed.nonProfit');
			})			
			
		}else{
			this.onError = true;
			
		}
		
		}else{
			this.onError = true;
		}
	
	};
});

 