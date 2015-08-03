/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('nonprofitSettingsController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session, $state, $rootScope, $sharedData, AUTH_EVENTS, AuthService) {
	
	$scope.nonprofit={};
	$scope.nonprofit.id = Session.userId;
	var modalInstance=null;
	
	$scope.deleteNonprofit = function()
	{
		
		$http.post('rest/protected/nonprofit/delete',
				$scope.nonprofit).then(function(response) {
					if(response.data.code=="200"){	
						AuthService.guestSession()
						$scope.currentUser = null;
						$state.go("treeSeed.index");
						$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
					}
					else if(response.data.code=="400")
					{
						console.log("ERROR");
					}
		});
	}
	
	$scope.openModalConfirmation = function() {

	    modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/delete_confirmation.html',
			scope: $scope,
	    })
	};
	
	$scope.closeModal = function() {		
		modalInstance.close();
		$scope.deleteNonprofit(); 
	};
	
	$scope.closeModalWithoutEdit = function() {	
		modalInstance.close();
	};
	
});

	
treeSeedAppControllers.controller('nonProfitRegistrationController', function($http, $scope, $upload, $state, AuthService, AUTH_EVENTS, $rootScope){

	
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
		   
		  var credentials = {
			    email: $scope.nonprofit.userGeneral.email,
			    password: $scope.nonprofit.userGeneral.password
		   };
		 
		  AuthService.login(credentials).then(function (user) {
		    	
		    	if(user.code=="200"){
		    		if(user.type=="nonprofit"){
		    			$scope.setCurrentUser(user.idUser, user.firstName, user.img );
		    			$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
		    			$state.go('treeSeed.nonProfit', {nonProfitId: response.nonProfitId});
		        	}else if(user.type=="donor"){
		        		$scope.setCurrentUser(user.idUser, user.firstName+" "+user.lastName, user.img );
		        	}	    		
		    	}

		    });

		   
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
			console.log(status);
			console.log("No data found");
		});

		$scope.pageChangeHandler = function(num) {
			$scope.searchNonProfit(num);
		};

	};

})

treeSeedAppControllers.controller('getNonProfitProfileController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session, $upload, USER_ROLES) {

	$scope.campaignsLoaded=false;
	$scope.postsLoaded = false;
	$scope.nonprofit = {};
	$scope.nonprofit.id = $stateParams.nonProfitId;
	$scope.requestObject = {};
	$scope.isOwner = true;	
	
	

	$scope.init = function() {

		$scope.requestObject.idUser= Session.userId;
		$scope.requestObject.id = $scope.nonprofit.id;
		
		$http.post('rest/protected/nonprofit/getNonProfitProfile',
				$scope.requestObject).success(function(mydata, status) {
					$scope.nonprofit = mydata.nonprofit;
					if(mydata.owner==true){
						$scope.isOwner=true;
					}else{
						$scope.isOwner=false;
					}
			
		}).error(function(mydata, status) {
			alert(status);
		});	

	}

	$scope.init();

	//Mission Edit
  	$scope.misionEditClicked = function() {
  		$scope.misionInEdition = true;
  		$scope.error = false;
  		
	};

	$scope.misionCancelEditing = function(){
		$scope.misionInEdition = false;
	};

	$scope.misionSaveEditing = function(){
		$scope.editNonProfit();
		$scope.misionInEdition = false;
	};

	//Name Edit
	$scope.nameEditClicked = function() {
  		$scope.nameInEdition = true;
  		$scope.error = false;
  		
	};

	$scope.nameCancelEditing = function(){
		$scope.nameInEdition = false;
	};

	$scope.nameSaveEditing = function(){
		$scope.editNonProfit();
		$scope.nameInEdition = false;
	};

	//Description Edit
	$scope.descriptionEditClicked = function() {
  		$scope.descriptionInEdition = true;
  		$scope.error = false;
  		
  		
	};

	$scope.descriptionCancelEditing = function(){
		$scope.descriptionInEdition = false;
	};

	$scope.descriptionSaveEditing = function(){
		
		$scope.editNonProfit();
		$scope.descriptionInEdition = false;
	};

	//Reason Edit
	$scope.reasonEditClicked = function() {
  		$scope.reasonInEdition = true;
  		$scope.error = false;
  		
	};

	$scope.reasonCancelEditing = function(){
		$scope.reasonInEdition = false;
	};

	$scope.reasonSaveEditing = function(){
		$scope.editNonProfit();
		$scope.reasonInEdition = false;
	};
	
	//Webpage Edit
  	$scope.webPageEditClicked = function() {
  		$scope.webPageInEdition = true;
  		$scope.error = false;
  		
	};

	$scope.webPageCancelEditing = function(){
		$scope.webPageInEdition = false;
	};

	$scope.webPageSaveEditing = function(){
		$scope.editNonProfit();
		$scope.webPageInEdition = false;
	};
	
	//Email Edit
  	$scope.emailEditClicked = function() {
  		$scope.emailInEdition = true;
  		$scope.error = false;
  		
	};

	$scope.emailCancelEditing = function(){
		$scope.emailInEdition = false;
	};

	$scope.emailSaveEditing = function(){
		$scope.editNonProfit();
		$scope.emailInEdition = false;
	};
	//Finish controller for edit buttons
	
	$scope.requestObjectEdit={};
	$scope.requestObjectEdit.nonProfit={}
	$scope.requestObjectEdit.coverImage=null;
	$scope.requestObjectEdit.profileImage=null;
	
	$scope.coverImageContainer=null;
	$scope.profileImageContrainer=null;
	
	
	$scope.editNonProfit = function(){

		$scope.requestObjectEdit.email = $scope.nonprofit.userGeneral.email;
		$scope.requestObjectEdit.name = $scope.nonprofit.name;
		$scope.requestObjectEdit.description= $scope.nonprofit.description;
		$scope.requestObjectEdit.reason= $scope.nonprofit.reason;
		$scope.requestObjectEdit.mision= $scope.nonprofit.mision;
		$scope.requestObjectEdit.webPage= $scope.nonprofit.webPage;
		$scope.requestObjectEdit.mainPicture= $scope.nonprofit.mainPicture; 
		$scope.requestObjectEdit.profilePicture= $scope.nonprofit.profilePicture;
		$scope.requestObjectEdit.id= $scope.nonprofit.id; 
		$scope.requestObjectEdit.idUser= Session.id;
		
		$http({
			   method : 'POST',
			   url : 'rest/protected/nonprofit/editNonProfit',
			   headers : {
			    'Content-Type' : undefined
			   },
			   transformRequest : function(data) {
			     var formData = new FormData();

			           formData.append('data', new Blob([angular.toJson(data.data)], {
			               type: "application/json"
			           }));	
			           formData.append("fileCover", data.fileCover);
			           formData.append("fileProfile", data.fileProfile);
			           console.log("Obj: "+JSON.stringify(data.data));
			           return formData;
			   },
			   data : {
				   data : $scope.requestObjectEdit,
				   fileCover : $scope.coverImageContainer,
				   fileProfile : $scope.profileImageContrainer
			   }

			  }).
			  success(function (data, status, headers, config) {
				  if(data.code=="400"){
		    		$scope.error = true;
		    		$scope.nonprofit.userGeneral.email = data.nonprofit.userGeneral.email;
				  }
				  
				  $scope.nonprofit.mainPicture =  data.nonprofit.mainPicture;
				  $scope.nonprofit.profilePicture = data.nonprofit.profilePicture;
				  $scope.currentUser.userImage = data.nonprofit.profilePicture;
			  });
		
	};
	
	$scope.$on('profilePicture', function(event, args){
		
		if($scope.imageCover==true){
			$scope.coverImageContainer = args;
		}else{
			$scope.profileImageContrainer= args
		}
		
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
	
	var modalInstance=null;
	
	$scope.openModalImage = function(type) {

		if(type == 'cover'){
			$scope.imageCover=true;
			console.log("es cover")
			console.log($scope.imageCover)
			
		}else if(type=='profile'){
			$scope.imageCover=false;
			console.log("es profile")
			console.log($scope.imageCover)		
		}
		
		modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/drag_drop.html',
			//controller : 'getNonProfitProfileController',
			scope: $scope,
			resolve : {
				setCurrentUser : function() {
					return $scope.image;
				}
			}

		})
	};

	
	$scope.closeModal = function() {
		
		modalInstance.close();
		$scope.editNonProfit(); 
		 
	};
	
	$scope.closeModalWithoutEdit = function() {		
		modalInstance.close();
	};
	
	
	$scope.loadCampaigns=function(){
		if(!$scope.campaignsLoaded){
			$scope.$broadcast('loadCampaigns');
			$scope.campaignsLoaded = true
		}
	};
	
	$scope.loadPosts=function(){
		if(!$scope.postsLoaded){
			$scope.$broadcast('loadPosts');
			$scope.postsLoaded = true;
		}
	};
	
	
	
	/*************************
	 * DONATION CALL
	 *************************/
	
	$scope.showDonation = function() {
		
		var modalUrl = '';
	    var controller = '';
		if(Session.userRole == USER_ROLES.guest){
			modalUrl = 'layouts/components/guestDonationModal.html';
			controller = 'guestDonationController';
		}else{
			modalUrl = 'layouts/components/donationModal.html';
			controller = 'donorDonationController'
		}
		
		
		var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl :modalUrl,
			controller : controller,
			size : 'sm',//,
			resolve : {
				setCurrentUser : function() {
					return $scope.setCurrentUser;
				},
				nonprofitId: function(){
					return  $scope.nonprofit.id;
				}
			} 
			// resolve : lazyService.load(['https://js.stripe.com/v2/'])
		});
		
	};//end showDonation
	
	
})
;

 