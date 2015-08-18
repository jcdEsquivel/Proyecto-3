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
				$scope.nonprofit).success(function(response) {
					if(response.code=="200"){	
						AuthService.guestSession()
						$scope.currentUser = null;
						$state.go("treeSeed.index");
						$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
					}
					else{
						$scope.errorServer(response.code);
					}
				}).error(function(status) {
					$scope.errorServer(status);
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
		}, function(status){
			 $scope.errorServer(status.status);
		});
		$scope.requestObject2.lenguage=$scope.requestObject1.lenguage;
		$scope.requestObject2.type = "cause";
		$http.post('rest/protected/catalog/getAllCatalog',$scope.requestObject2)
		    .then(function(response){
		     $scope.selectSortOptionsCause =  response.data.catalogs;
		     $scope.nonprofit.cause =  response.data.catalogs[0];
		}, function(status){
			 $scope.errorServer(status.status);
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
		  
		   if(response.code == 200){
		   
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
		    	}else{
					$scope.errorServer(user.code);
				}
			}, function(status){
				 $scope.errorServer(status.status);
			});	
		  
	   }else{
		   $scope.errorServer(response.code)
	   }
	   }); 
	
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
		
		$scope.requestObject.country = $scope.country;
		
		$scope.requestObject1.lenguage = $scope.selectLang;
		$scope.requestObject1.type = "country";
		$http.post('rest/protected/catalog/getAllCatalog',
				$scope.requestObject1).then(function(response) {
			$scope.selectSortOptionsCountry = response.data.catalogs;
			
			
		}, function(status){
			 $scope.errorServer(status.status);
		});
		$scope.requestObject2.lenguage = $scope.requestObject1.lenguage;
		$scope.requestObject2.type = "cause";
		$http.post('rest/protected/catalog/getAllCatalog',
				$scope.requestObject2).then(function(response) {
			$scope.selectSortOptionsCause = response.data.catalogs;

		}, function(status){
			 $scope.errorServer(status.status);
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
			
					if(mydata.code==200){
						$scope.nonprofits = mydata.nonprofits;
						$scope.totalItems = mydata.totalElements;
					}
					else{
						$scope.errorServer(mydata.code);
					}
					
		}).error(function(status) {
			$scope.errorServer(status);
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
	$scope.reportsLoaded = false;
	$scope.nonprofit = {};
	$scope.nonprofit.id = $stateParams.nonProfitId;
	$scope.requestObject = {};
	$scope.isOwner = true;	
	$scope.showDonationButton = true;
	

	$scope.init = function() {
		//Show donate button
		if(Session.userRole == USER_ROLES.nonprofit){
			$scope.showDonationButton = false;
		}
		
		$scope.requestObject.idUser= Session.userId;
		$scope.requestObject.id = $scope.nonprofit.id;
		
		$http.post('rest/protected/nonprofit/getNonProfitProfile',
				$scope.requestObject).success(function(mydata, status) {
					if(mydata.code==200){
						$scope.nonprofit = mydata.nonprofit;
						if(mydata.owner==true){
							$scope.isOwner=true;
						}else{
							$scope.isOwner=false;
						}
					}
					else{
						$scope.errorServer(mydata.code);
					}
				}).error(function(status) {
					$scope.errorServer(status);
				});	

	}

	$scope.init();

	//Mission Edit
  	$scope.misionEditClicked = function() {
  		$scope.misionInEdition = true;
  		$scope.error = false;
  		$scope.nonprofitEdit ={
  				mision: $scope.nonprofit.mision
  		}
  		
	};

	$scope.misionCancelEditing = function(){
		$scope.misionInEdition = false;
	};

	$scope.misionSaveEditing = function(){
		$scope.nonprofit.mision= $scope.nonprofitEdit.mision;
		$scope.editNonProfit();
		$scope.misionInEdition = false;
		
	};

	//Name Edit
	$scope.nameEditClicked = function() {
		$scope.nameInEdition = true;
  		$scope.error = false;
  		$scope.nonprofitEdit ={
  				name: $scope.nonprofit.name
  		}

	};

	$scope.nameCancelEditing = function(){
		$scope.nameInEdition = false;
		
	};

	$scope.nameSaveEditing = function(){
		if($scope.nonprofitEdit.name){
			$scope.nonprofit.name = $scope.nonprofitEdit.name;
		}
		
		$scope.editNonProfit();
		$scope.nameInEdition = false;
	};

	//Description Edit
	$scope.descriptionEditClicked = function() {
  		$scope.descriptionInEdition = true;
  		$scope.error = false;
  		$scope.nonprofitEdit ={
  				description: $scope.nonprofit.description
  		}
	};

	$scope.descriptionCancelEditing = function(){
		$scope.descriptionInEdition = false;
	};

	$scope.descriptionSaveEditing = function(){
		$scope.nonprofit.description = $scope.nonprofitEdit.description;
		$scope.editNonProfit();
		$scope.descriptionInEdition = false;
	};

	//Reason Edit
	$scope.reasonEditClicked = function() {
  		$scope.reasonInEdition = true;
  		$scope.error = false;
  		$scope.nonprofitEdit ={
  				reason: $scope.nonprofit.reason
  		}
	};

	$scope.reasonCancelEditing = function(){
		$scope.reasonInEdition = false;
	};

	$scope.reasonSaveEditing = function(){
		$scope.nonprofit.reason = $scope.nonprofitEdit.reason;
		$scope.editNonProfit();
		$scope.reasonInEdition = false;
	};
	
	//Webpage Edit
  	$scope.webPageEditClicked = function() {
  		$scope.webPageInEdition = true;
  		$scope.error = false;
  		$scope.nonprofitEdit ={
  				webPage: $scope.nonprofit.webPage
  		}
	};

	$scope.webPageCancelEditing = function(){
		$scope.webPageInEdition = false;
	};

	$scope.webPageSaveEditing = function(){
		$scope.nonprofit.webPage = $scope.nonprofitEdit.webPage;
		$scope.editNonProfit();
		$scope.webPageInEdition = false;
	};
	
	//Email Edit
  	$scope.emailEditClicked = function() {
  		$scope.emailInEdition = true;
  		$scope.error = false;
  		$scope.nonprofitEdit ={
  				email: $scope.nonprofit.userGeneral.email
  		}
	};

	$scope.emailCancelEditing = function(){
		$scope.emailInEdition = false;
	};

	$scope.emailSaveEditing = function(){
		$scope.nonprofit.userGeneral.email = $scope.nonprofitEdit.email;
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
		
		console.log($scope.nonprofit.name);
		console.log($scope.requestObjectEdit.name)
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
				 if(data.code==200){
					  $scope.nonprofit.mainPicture =  data.nonprofit.mainPicture;
					  $scope.nonprofit.profilePicture = data.nonprofit.profilePicture;
					  $scope.currentUser.userImage = data.nonprofit.profilePicture;

				  }else if(data.code==400){
					  	$scope.error = true;
				  		$scope.nonprofit.userGeneral.email = data.nonprofit.userGeneral.email;
				  }else{
	    			$scope.errorServer(data.code);
				  }
			  }).error(function(status) {
				$scope.errorServer(status);
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
			
		}else if(type=='profile'){
			$scope.imageCover=false;
				
		}
		
		modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/drag_drop.html',
			scope: $scope,
			resolve : {
				setCurrentUser : function() {
					return $scope.image;
				},
				errorFunction: function(){
					return $scope.errorServer;
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
		
		
		
			/*var modalInstance = $modal.open({
				animation : $scope.animationsEnabled,
				templateUrl : 'layouts/components/page_donationSummary.html',
				controller : 'summaryDonationController',
				size : 'md',//,
				resolve : {
					nonprofitId: function(){
						return  $scope.nonprofit.id;
					},
					amount: function(){
						return $scope.nonprofit.name;
					},
					plan: function(){
						return $scope.nonprofit.name;
					},
					titleFace: function(){
						return $scope.nonprofit.name;
					},
					descriptionFace: function(){
						return $scope.nonprofit.description;
					},
					pictureFace: function(){
						return $scope.nonprofit.profilePicture;
					}
				} */
			
			var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl :modalUrl,
			controller : controller,
			size : 'md',//,
			resolve : {
				setCurrentUser : function() {
					return $scope.setCurrentUser;
				},
				nonprofitId: function(){
					return  $scope.nonprofit.id;
				},
				titleFace: function(){
					return $scope.nonprofit.name;
				},
				descriptionFace: function(){
					return $scope.nonprofit.description;
				},
				pictureFace: function(){
					return $scope.nonprofit.profilePicture;
				},
				errorFunction: function(){
					return $scope.errorServer;
				},
				fatherId: function(){
					return $scope.getFatherId();
				}
			}
			// resolve : lazyService.load(['https://js.stripe.com/v2/'])
		});
		
	};//end showDonation
	


	$scope.loadReports=function(){
		if(!$scope.reportsLoaded){
			$scope.$broadcast('loadReports');
			$scope.reportsLoaded = true;
		}
	}
	
});

treeSeedAppControllers.controller('nonprofitDashboardController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session, $upload, USER_ROLES) {
	
	$scope.requestObject={};
	$scope.showList1 = true;
	$scope.showList2 = true;
	$scope.donations;
	$scope.subscriptions;
	
	$scope.requestObject.idUser=$scope.currentUser.idUser;
	$scope.requestObject.id=Session.id;
	
	$http.post('rest/protected/nonprofit/getdashboard',
			$scope.requestObject).success(function(mydata) {
				if(mydata.code==200){
					$scope.donations = mydata.dashboardDonations;
					$scope.subscriptions = mydata.dashboardSubscription;
					if($scope.donations.length==0){
						$scope.showList1=false;
					}
					if($scope.subscriptions.length==0){
						$scope.showList2=false;
					}
				}else{
					$scope.errorServer(status);
				}
		
	}).error(function(status) {
		$scope.errorServer(status);
	});	
});

 