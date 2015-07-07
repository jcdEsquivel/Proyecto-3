
var treeSeedAppControllers = angular.module('treeSeed.controller');


treeSeedAppControllers.controller('nonProfitRegistrationController', function($http, $scope, $upload, $state){
	
	
	$scope.nonprofit={};
	$scope.uploadImage=false;
	$scope.nonprofit.country ="";
	$scope.nonprofit.cause = "";
	$scope.nonprofit.name = "";
	$scope.nonprofit.userGeneral = {};
	$scope.nonprofit.userGeneral.email ="";
	$scope.nonprofit.userGeneral.password = "";
	$scope.requestObject1={};
	$scope.requestObject2={};
	
	
	
	$scope.init = function(){
		$scope.requestObject1.lenguage=$scope.selectLang;
		$scope.requestObject1.type = "country";
		$http.post('rest/protected/catalog/getAllCatalog',$scope.requestObject1)
		    .then(function(response){
		     $scope.selectSortOptionsCountry =  response.data.catalogs;
		     $scope.nonprofit.country =  response.data.catalogs[0];
		});
		$scope.requestObject2.lenguage=$scope.requestObject1.lenguage;
		$scope.requestObject2.type = "cause";
		$http.post('rest/protected/catalog/getAllCatalog',$scope.requestObject2)
		    .then(function(response){
		     $scope.selectSortOptionsCause =  response.data.catalogs;
		     $scope.nonprofit.cause =  response.data.catalogs[0];
		});
	}
	
	$scope.init();
		
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
	
		this.onError = false;
		
		
		
		if($scope.uploadImage==true){
			   $scope.upload = $upload.upload({
			    url : 'rest/protected/users/registerNonProfit',
			    data : {
			    	email:$scope.nonprofit.userGeneral.email,
					password:$scope.nonprofit.userGeneral.password,
					name:$scope.nonprofit.name,
					cause:$scope.nonprofit.cause.id,
					country:$scope.nonprofit.country.id
			    },
			    file : $scope.image,
			   }).success(function(response){
			    $state.go('treeSeed.nonProfit');
			   }) 
		}
	
	};
	
	
});

 