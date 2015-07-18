var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('donorRegistrationController', function($http, $scope, $upload, $state, AuthService, AUTH_EVENTS, $rootScope){
	
	$scope.requestObject = {};
	$scope.requestObject.donor = {};
	$scope.requestObject.donor.name = "";
	$scope.requestObject.donor.lastName = "";
	$scope.requestObject.donor.profilePicture = "";
	$scope.requestObject.donor.description = "";
	$scope.requestObject.donor.webPage = "";
	$scope.requestObject.donor.country = "";
	$scope.requestObject.donor.type = "";
	$scope.requestObject.donor.facebookId = "";
	$scope.requestObject.donor.facebookToken = "";
	
	$scope.requestObject.donor.userGeneral = {};
	$scope.requestObject.donor.userGeneral.email = "";
	$scope.requestObject.donor.userGeneral.password = "";
	$scope.confirm_password = $scope.requestObject.donor.userGeneral.password;
	$scope.requestObject.donor.userGeneral.confirmPassword = "";	
	$scope.image = "";
	var app_id = '319610508162843';
	$scope.facebookFail = false;
	
	$scope.init = function()
	{
		(function(d, s, id){
		    var js, fjs = d.getElementsByTagName(s)[0];
		    if (d.getElementById(id)) {return;}
		    js = d.createElement(s); js.id = id;
		    js.src = "//connect.facebook.net/en_US/sdk.js";
		    fjs.parentNode.insertBefore(js, fjs);
		  }(document, 'script', 'facebook-jssdk'));
		
		window.fbAsyncInit = function() {

		  	FB.init({
		  		
		    	appId      : app_id,
		    	status     : true,
		    	cookie     : true, 
		    	xfbml      : true, 
		    	version    : 'v2.1'
		    		
		  	});

		  	FB.getLoginStatus(function(response) {
		  		if (response.status === 'connected') {
		  		    //console.log(response.authResponse.accessToken);
		  		    $scope.requestObject.donor.facebookToken = response.authResponse.accessToken;
		  		  }
		    	statusChangeCallback(response, function() {});
		  	});
	  	};	
		  
	}
	
	var statusChangeCallback = function(response, callback) {
    	if (response.status === 'connected') {
      		//getFacebookData();
    	} else {
     		callback(false);
    	}
  	}

  	var checkLoginState = function(callback) {
    	FB.getLoginStatus(function(response) {
      		callback(response);
    	});
  	}
		
	var getFacebookData =  function() {	
  		FB.api('/me?fields=id,first_name,last_name,location,email', function(response) {
  			console.log(JSON.stringify(response));
  	
	  		// return an image as an ArrayBuffer.
	  		var xhr = new XMLHttpRequest();
	  		
	  		// cross-domain issues.
	  		xhr.open( "GET", 'http://graph.facebook.com/'+response.id+'/picture?type=large', true );
	
	  		// Ask for the result as an ArrayBuffer.
	  		xhr.responseType = "arraybuffer";
	
	  		xhr.onload = function( e ) {
	  		    // Obtain a blob: URL for the image data.
	  		    var arrayBufferView = new Uint8Array( this.response );
	  		    var blob = new Blob( [ arrayBufferView ], { type: "image/jpg" } );
	  		    var urlCreator = window.URL || window.webkitURL;
	  		    var imageUrl = urlCreator.createObjectURL(blob);
	  		    //var img = document.querySelector( "#fileDisplayArea");
	  		    
	  		    $scope.image = blob;
	  		    $scope.requestObject.donor.userGeneral.email = response.email
				$scope.requestObject.donor.name = response.first_name
				$scope.requestObject.donor.lastName = response.last_name
				$scope.requestObject.donor.country.id = 1;
	  		    $scope.requestObject.donor.facebookId = response.id;
				$scope.create();
				
	  		};

	  		xhr.send();		  		
		});
  	}
		
	var facebookLogin = function() {
  		checkLoginState(function(data) {
  			if (data.status !== 'connected') {
  				FB.login(function(response) {
  					if (response.status === 'connected')
  						getFacebookData();
  				}, {scope: 'email,user_location'});
  			}
  		})
  	}
	
  	var facebookLogout = function() {
  		checkLoginState(function(data) {
  			if (data.status === 'connected') {
				FB.logout(function(response) {
					$('#facebook-session').before(btn_login);
					$('#facebook-session').remove();
				})
			}
  		})
  	}
	
	$scope.init();
	
	$scope.countryName = "";
	
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
		
		$scope.upload = $upload.upload({
			url : 'rest/protected/donor/register',
			data : {
				email:$scope.requestObject.donor.userGeneral.email,
				password:$scope.requestObject.donor.userGeneral.password,
				name:$scope.requestObject.donor.name,
				lastName:$scope.requestObject.donor.lastName,
				country:$scope.requestObject.donor.country.id,
				facebookId:$scope.requestObject.donor.facebookId,
				facebookToken:$scope.requestObject.donor.facebookToken
			},
			file : $scope.image,
		}).success(function(response){
			  
			  if(response.code == "200")
			  {
				  var credentials = {
						    email: $scope.requestObject.donor.userGeneral.email,
						    password: $scope.requestObject.donor.userGeneral.password
					   };
					 	  
					  AuthService.login(credentials).then(function (user) {
						  
						  console.log(JSON.stringify(user));
					    	
					    	if(user.code=="200"){
					    		if(user.type=="nonprofit"){
					    			$scope.setCurrentUser(user.idUser, user.firstName, user.img );
					    			
					        	}else if(user.type=="donor"){
					        		$scope.setCurrentUser(user.idUser, user.firstName+" "+user.lastName, user.img );
					        		$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
					        		$state.go('treeSeed.donor', {donorId: response.donorId});
					        	}
					    	}
					    });  
			  }
			  else
			  {
					if (response.code == "400")
		        	{
						$scope.facebookFail = true;
						$scope.requestObject.donor.name = "";
						$scope.requestObject.donor.lastName = "";
						$scope.requestObject.donor.userGeneral.email = "";	
		        	}
			  }
			
			  
		})			
				
	};
    
	$scope.requestObject.lenguage = $scope.selectLang;
	$scope.requestObject.type = 'Country';
	
	$scope.getCountries = function(){
        return $http.post('rest/protected/catalog/getAllCatalog',$scope.requestObject)
                    .then(function(response){
                     $scope.requestObject.donor.country = response.data.catalogs[0];
                     $scope.selectSortOptions = response.data.catalogs;  
                     //$scope.selectSortOptions.splice(0,1);
                    }); 
	 };
	 $scope.getCountries(); 
	  
	 $scope.validateEmail = function() 
	 {
		 var emailFormat = $scope.requestObject.donor.userGeneral.email;
		 if (emailFormat == "")
		 {
			 document.getElementById("emailValidate").className = "md-default-theme md-input-invalid md-input-has-value";
		 }
		 var result = validateEmail(emailFormat);
		 if (result == true)
		 {
			 document.getElementById("emailValidate").className = "md-default-theme md-input-has-value"; 
		 }
		 else
	     {
			 document.getElementById("emailValidate").className = "md-default-theme md-input-invalid md-input-has-value"; 
	     }
	 }

	 function validateEmail(email) {
		    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
		    return re.test(email);
	 }
	 
	 $(document).on('click', '#login', function(e) {
	  		e.preventDefault();
	  		facebookLogin();
	  	})

	  	$(document).on('click', '#logout', function(e) {
	  		e.preventDefault();
	  		
	  			facebookLogout();
	  	
	  	})	 
});


treeSeedAppControllers.controller('donorSearchController', function($scope,
		$http, $location, $modal, $log, $timeout) {

	$scope.donor = {};
	$scope.donor.country = "";
	$scope.donor.lastName = "";
	$scope.requestObject1 = {};

	$scope.init = function() {
		$scope.requestObject1.lenguage = $scope.selectLang;
		console.log($scope.selectLang);
		$scope.requestObject1.type = "country";
		$http.post('rest/protected/catalog/getAllCatalog',
				$scope.requestObject1).then(function(response) {
			$scope.selectSortOptionsCountry = response.data.catalogs;
		});
	}

	$scope.init();

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
	$scope.requestObject.name = $scope.name;
	$scope.requestObject.country = $scope.country;
	$scope.requestObject.lastName = $scope.lastName;

	$scope.searchDonor = function(page) {

		$scope.requestObject.pageNumber = page;
		$scope.requestObject.name = $scope.name;
		$scope.requestObject.country = $scope.donor.country.id;
		$scope.requestObject.lastName = $scope.lastName;

		$http.post('rest/protected/donor/advanceGet',
				$scope.requestObject).success(function(mydata, status) {
			$scope.donors = mydata.donors;
			$scope.totalItems = mydata.totalElements;
		}).error(function(mydata, status) {

		});

		$scope.pageChangeHandler = function(num) {
			$scope.searchDonor(num);
		};
    }
});

treeSeedAppControllers.controller('getDonorProfileController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session) {

	//Declaration of donor object
	$scope.donor = {};
	$scope.donor.id = $stateParams.donorId;
	$scope.requestObject = {};

	//init function, calls the java controller
	$scope.init = function() {
		$scope.requestObject.idUser= Session.id;
		$scope.requestObject.id = $scope.donor.id;

			$http.post('rest/protected/donor/getDonorProfile',
					$scope.requestObject).success(function(mydata, status) {
						$scope.donor = mydata.donor;
						
						if(mydata.owner==true){
							$scope.isOwner=true;
						}else{
							$scope.isOwner=false;
						}
			}).error(function(mydata, status) {
				console.log(status);
				console.log("No data found")
			});		
	}
	$scope.init();

  	//About Edit
  	$scope.aboutEditClicked = function() {
  		$scope.aboutInEdition = true;
  		$scope.error = false;
  		//$scope.aboutEdit = $scope.donor.description;
	};

	$scope.aboutCancelEditing = function(){
		$scope.aboutInEdition = false;
	};

	$scope.aboutSaveEditing = function(){
		$scope.editDonor();
		$scope.aboutInEdition = false;
	};

	//Name Edit
	$scope.nameEditClicked = function() {
  		$scope.nameInEdition = true;
  		$scope.error = false;
  		//$scope.nameEdit = $scope.donor.name;
  		//$scope.lastNameEdit = $scope.donor.lastName;
	};

	$scope.nameCancelEditing = function(){
		$scope.nameInEdition = false;
	};

	$scope.nameSaveEditing = function(){
		$scope.editDonor();
		$scope.nameInEdition = false;
	};

	//Email Edit
	$scope.emailEditClicked = function() {
  		$scope.emailInEdition = true;
  		$scope.error = false;
  		//$scope.emailEdit = $scope.email;
	};

	$scope.emailCancelEditing = function(){
		$scope.emailInEdition = false;
	};

	$scope.emailSaveEditing = function(){
		$scope.editDonor();
		$scope.emailInEdition = false;
	};

	//Webpage Edit
	$scope.webPageEditClicked = function() {
  		$scope.webPageInEdition = true;
  		$scope.error = false;
  		//$scope.webPageEdit = $scope.donor.webPage;
	};

	$scope.webPageCancelEditing = function(){
		$scope.webPageInEdition = false;
	};

	$scope.webPageSaveEditing = function(){
		$scope.editDonor();
		$scope.webPageInEdition = false;
	};
	//Finish controller for edit buttons

	//Start editing profile
	$scope.requestObjectEdit={};
	$scope.requestObjectEdit.donor={}
	$scope.requestObjectEdit.coverImage=null;
	$scope.requestObjectEdit.profileImage=null;
	
	$scope.editDonor = function(){

		console.log($scope.donor.description)
		
		$scope.requestObjectEdit.email = $scope.donor.userGeneral.email;
		$scope.requestObjectEdit.name = $scope.donor.name;
		$scope.requestObjectEdit.lastName = $scope.donor.lastName;
		$scope.requestObjectEdit.description= $scope.donor.description;
		$scope.requestObjectEdit.webPage= $scope.donor.webPage;
		$scope.requestObjectEdit.id= $scope.donor.id; 
		$scope.requestObjectEdit.idUser= Session.id;
		$scope.requestObjectEdit.coverImage=null;
		$scope.requestObjectEdit.profilePicture=$scope.donor.profilePicture;
		
		$http({
			   method : 'POST',
			   url : 'rest/protected/donor/editDonor',
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
		    		$scope.donor.userGeneral.email = data.donor.userGeneral.email;
		        }	    		
			});
		};
	
		$scope.$on('profilePicture', function(event, args){
			
			$scope.profileImageContrainer= args
			
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
			$scope.editDonor(); 
		};
		
		$scope.closeModalWithoutEdit = function() {		
			modalInstance.close();
		};
	//Finish editing profile
});
