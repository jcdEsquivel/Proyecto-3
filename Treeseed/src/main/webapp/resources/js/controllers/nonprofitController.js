/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

	
treeSeedAppControllers.controller('nonProfitRegistrationController', function($http, $scope, $upload, $state){

	
	$scope.nonprofit={};
	$scope.uploadImage=false;
	$scope.nonprofit.name = "";
	$scope.nonprofit.userGeneral = {};
	$scope.nonprofit.userGeneral.email ="";
	$scope.nonprofit.userGeneral.password = "";
	$scope.nonprofit.country="";
	$scope.selectSortOptionsCountry="";
	$scope.nonprofit.cause = "";
	$scope.selectSortOptionsCause="";
	$scope.confirm_password=$scope.nonprofit.userGeneral.password;
	$scope.requestObject1={};
	$scope.requestObject2={};
	$scope.confirmPassword = "";
	$scope.image = "";
	
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
	
	$scope.refresh=function(){
		$scope.nonprofit.country =$scope.nonprofit.countrySelect;
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
		
			   $scope.upload = $upload.upload({
			    url : 'rest/protected/nonprofit/register',
			    data : {
			    	email:$scope.nonprofit.userGeneral.email,
					password:$scope.nonprofit.userGeneral.password,
					name:$scope.nonprofit.name,
					cause:$scope.nonprofit.cause.id,
					country:$scope.nonprofit.country.id
			    },
			    file : $scope.image,
			   }).success(function(response){
				   console.log(response.nonProfitId)
				   $state.go('treeSeed.nonProfit', {nonProfitId: response.nonProfitId});
			   }) 
	
	};
	
});


treeSeedAppControllers.controller('nonProfitSearchController', function($scope,
		$http, $location, $modal, $log, $timeout) {

	$scope.nonprofit = {};
	$scope.nonprofit.id="";
	$scope.nonprofit.country = "";
	$scope.nonprofit.cause = "";
	$scope.requestObject1 = {};
	$scope.requestObject2 = {};
	$scope.itemPerPage = [ 10, 25, 50, 100 ];
	$scope.currentPage = 1;
	$scope.totalItems = 5;
	$scope.requestObject = {};
	$scope.requestObject.pageNumber = 1;
	$scope.requestObject.pageSize = 10;
	$scope.requestObject.direction = "DESC";
	$scope.requestObject.sortBy = [];
	$scope.requestObject.searchColumn = "ALL";
	$scope.requestObject.searchTerm = "";

	$scope.init = function() {
		$scope.requestObject1.lenguage = $scope.selectLang;
		$scope.requestObject1.type = "country";
		$http.post('rest/protected/catalog/getAllCatalog',
				$scope.requestObject1).then(function(response) {
			$scope.selectSortOptionsCountry = response.data.catalogs;
		});
		$scope.requestObject2.lenguage = $scope.requestObject1.lenguage;
		$scope.requestObject2.type = "cause";
		$http.post('rest/protected/catalog/getAllCatalog',
				$scope.requestObject2).then(function(response) {
			$scope.selectSortOptionsCause = response.data.catalogs;

		});
	}

	$scope.init();

	$scope.searchNonProfit = function(page) {

		$scope.requestObject.pageNumber = page;
		$scope.requestObject.name = $scope.name;
		$scope.requestObject.country = $scope.nonprofit.country.id;
		$scope.requestObject.cause = $scope.nonprofit.cause.id;

		$http.post('rest/protected/nonprofit/advanceGet',
				$scope.requestObject).success(function(mydata, status) {
			$scope.nonprofits = mydata.nonprofits;
			$scope.totalItems = mydata.totalElements;
			console.log($scope.nonprofits[1].id)
		}).error(function(mydata, status) {
			alert(status);
		});

		$scope.pageChangeHandler = function(num) {
			$scope.searchNonProfit(num);
		};

	};

})

treeSeedAppControllers.controller('getNonProfitProfileController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams) {

	
	$scope.nonprofit = {};
	$scope.nonprofit.id = $stateParams.nonProfitId;
	$scope.requestObject = {};

	//Controllers for Edit Buttons
	$scope.isOwner = true;
	
	$scope.init = function() {
		 $scope.requestObject.id = $scope.nonprofit.id;

			$http.post('rest/protected/nonprofit/getNonProfitProfile',
					$scope.requestObject).success(function(mydata, status) {
				$scope.nonprofit = mydata.nonprofit;
				console.log(mydata);
			}).error(function(mydata, status) {
				alert(status);
			});	
			
			
		
	}

	$scope.init();

	//Mission Edit
  	$scope.missionEditClicked = function() {
  		$scope.missionInEdition = true;
  		$scope.missionEdit = $scope.nonprofit.mision;
	};

	$scope.missionCancelEditing = function(){
		$scope.missionInEdition = false;
	};

	$scope.missionSaveEditing = function(){
		$scope.nonprofit.mission = $scope.missionEdit;
		$scope.missionInEdition = false;
	};

	//Name Edit
	$scope.nameEditClicked = function() {
  		$scope.nameInEdition = true;
  		$scope.nameEdit = $scope.nonprofit.name;
	};

	$scope.nameCancelEditing = function(){
		$scope.nameInEdition = false;
	};

	$scope.nameSaveEditing = function(){
		$scope.nonprofit.name = $scope.nameEdit;
		$scope.nameInEdition = false;
	};

	//Description Edit
	$scope.descriptionEditClicked = function() {
  		$scope.descriptionInEdition = true;
  		$scope.descriptionEdit = $scope.nonprofit.description;
	};

	$scope.descriptionCancelEditing = function(){
		$scope.descriptionInEdition = false;
	};

	$scope.descriptionSaveEditing = function(){
		$scope.nonprofit.description = $scope.descriptionEdit;
		$scope.descriptionInEdition = false;
	};

	//Webpage Edit
	$scope.reasonEditClicked = function() {
  		$scope.reasonInEdition = true;
  		$scope.reasonEdit = $scope.nonprofit.reason;
	};

	$scope.reasonCancelEditing = function(){
		$scope.reasonInEdition = false;
	};

	$scope.reasonSaveEditing = function(){
		$scope.nonprofit.reason = $scope.reasonEdit;
		$scope.reasonInEdition = false;
	};
	//Finish controller for edit buttons
	

})
;

 