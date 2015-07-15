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
	
	$scope.requestObject.donor.userGeneral = {};
	$scope.requestObject.donor.userGeneral.email = "";
	$scope.requestObject.donor.userGeneral.password = "";
	$scope.confirm_password = $scope.requestObject.donor.userGeneral.password;
	$scope.requestObject.donor.userGeneral.confirmPassword = "";	
	$scope.image = "";
	
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
				country:$scope.requestObject.donor.country.id
			},
			file : $scope.image,
		}).success(function(response){
			   
			  var credentials = {
				    email: $scope.requestObject.donor.userGeneral.email,
				    password: $scope.requestObject.donor.userGeneral.password
			   };
			 
			  AuthService.login(credentials).then(function (user) {
			    	
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
	 
	 
	 $scope.validateConfirm = function() 
	 {
		 var password = $scope.requestObject.donor.userGeneral.password;
		 var confirmPassword = $scope.requestObject.donor.userGeneral.confirmPassword;
		 
		 if (password != confirmPassword)
		 {
			 alert("wrong");
			 document.getElementById("confirmPass").style.display = 'block';
			 document.getElementById("passValidate").className = "md-default-theme md-input-invalid md-input-has-value";
			 document.getElementById("passCValidate").className = "md-default-theme md-input-invalid md-input-has-value";
		 }
		 else
		 {
			 alert("good");
			 document.getElementById("confirmPass").style.display = 'hide'; 
			 document.getElementById("passValidate").className = "md-default-theme md-input-has-value";
			 document.getElementById("passCValidate").className = "md-default-theme md-input-has-value";
		 }
	 }
	 
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
	$scope.donor.name = "";
	$scope.donor.lastName = "";
	$scope.donor.description = "";
	$scope.donor.country = "";
	//$scope.donor.userGeneral.email = "";
	$scope.donor.profilePicture = "";
	$scope.donor.webPage = "";
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
  		$scope.aboutEdit = $scope.donor.description;
	};

	$scope.aboutCancelEditing = function(){
		$scope.aboutInEdition = false;
	};

	$scope.aboutSaveEditing = function(){
		$scope.donor.description = $scope.aboutEdit;
		$scope.aboutInEdition = false;
	};

	//Name Edit
	$scope.nameEditClicked = function() {
  		$scope.nameInEdition = true;
  		$scope.nameEdit = $scope.donor.name;
  		$scope.lastNameEdit = $scope.donor.lastName;
	};

	$scope.nameCancelEditing = function(){
		$scope.nameInEdition = false;
	};

	$scope.nameSaveEditing = function(){
		$scope.donor.name = $scope.nameEdit;
		$scope.donor.lastName = $scope.lastNameEdit;
		$scope.nameInEdition = false;
	};

	//Email Edit
	$scope.emailEditClicked = function() {
  		$scope.emailInEdition = true;
  		$scope.emailEdit = $scope.email;
	};

	$scope.emailCancelEditing = function(){
		$scope.emailInEdition = false;
	};

	$scope.emailSaveEditing = function(){
		$scope.email = $scope.emailEdit;
		$scope.emailInEdition = false;
	};

	//Webpage Edit
	$scope.webPageEditClicked = function() {
  		$scope.webPageInEdition = true;
  		$scope.webPageEdit = $scope.donor.webPage;
	};

	$scope.webPageCancelEditing = function(){
		$scope.webPageInEdition = false;
	};

	$scope.webPageSaveEditing = function(){
		$scope.donor.webPage = $scope.webPageEdit;
		$scope.webPageInEdition = false;
	};
	//Finish controller for edit buttons
});