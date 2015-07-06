/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');
treeSeedAppControllers.controller('donorRegistrationController', function($http, $scope, $upload, $state){
	
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
		
	$scope.$on('profilePicture', function(event, args){
		$scope.image = args;
		$scope.uploadImage=true;	
		
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
		  
		} else {
		  alert("File not supported!");
		}
		
	});
	
	$scope.create = function(event) {

		if($scope.uploadImage==true){
			$scope.upload = $upload.upload({
				url : 'rest/protected/users/registerDonor',
				data : {
					email:$scope.requestObject.donor.userGeneral.email,
					password:$scope.requestObject.donor.userGeneral.password,
					name:$scope.requestObject.donor.name,
					lastName:$scope.requestObject.donor.lastName,
					country:$scope.requestObject.donor.country
				},
				file : $scope.image,
			}).success(function(){
				$state.go('treeSeed.donor');
			})			
			
		}else{
			this.onError = true;	
		}
		
	};
    
	$scope.requestObject.lenguaje = 
	$scope.requestObject.type = 'Country';
	
	$scope.getCountries = function(){
        return $http.post('rest/protected/users/getAllCountries',$scope.requestObject)
                    .then(function(response){
                     $scope.selectSortOptions = response.data.catalogs;
                     $scope.requestObject.donor.country = response.data.catalogs[0].id;
                    }); 
	 };
	 $scope.getCountries();  
});

	