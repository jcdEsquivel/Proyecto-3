/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');
treeSeedAppControllers.controller('donorRegistrationController', function($http, $scope, $location){
	
	$scope.requestObject = {};
	$scope.requestObject.donor = {};
	$scope.requestObject.donor.name = "";
	$scope.requestObject.donor.lastName = "";
	$scope.requestObject.donor.profilePicture = "";
	$scope.requestObject.donor.description = "";
	$scope.requestObject.donor.webPage = "";
	$scope.requestObject.donor.country = "";
	$scope.requestObject.donor.type = "";
	
	$scope.requestObject.donor.userGeneral = {};
	$scope.requestObject.donor.userGeneral.email = "";
	$scope.requestObject.donor.userGeneral.password = "";
		
	$scope.create = function() {
		
		//if(this.createUserForm.$valid){
			//this.onError = false;
			
			$http.post('rest/protected/users/registerDonor',$scope.requestObject)
			.success(function(response) {
				if(response.code === 200){
					//$modalInstance.close();
					$location.path('/donor');
				}
			});
			
		//}else{
			//this.onError = true;
		//}
	};
	
	 $scope.searcher = {};
	 $scope.searcher.first = '';
	
	$scope.getCountries = function(){
        return $http.post('rest/protected/users/getAllCountries')
                    .then(function(response){
                     $scope.selectSortOptions = response.data;
                     $scope.searcher.first = response.data[0].id;
                    }); 
	 };
	 
	 $scope.getCountries();
  
});

	