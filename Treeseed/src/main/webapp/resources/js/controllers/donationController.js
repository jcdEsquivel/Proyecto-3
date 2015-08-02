/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('simpleDonationController', function($http, $donationService,StripeService,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $modalInstance, $stateParams, $rootScope, setCurrentUser) {
	

	$scope.percent = 0;
	
	$scope.donor = {
			name:'ara',
			lastName:'ddd',
			email:'asdf@asdref.com',
			password:'asdf',
			confirm_password:'asdf'
	};
	
	$scope.number = '4242 4242 4242 4242';
	$scope.cvc = '1235';
	$scope.expiry = '01/2019';
	
	$scope.donationInfo = {
		amount : 1000,
		donationPlan: 'custom'
	};
	
/*
 * For wizard porpuses. Will be use in the future
	$scope.stateInput1 = false;
	$scope.stateInput2 = false;
	$scope.stateInput3 = false;
	$scope.stateInput4 = false;
	$scope.stateInput5 = false;
	$scope.stateInput6 = false;
	$scope.stateInput7 = false;
	$scope.stateInput8 = false;
	$scope.stateInput9 = false;
	*/
	$scope.errorCard = undefined;

	
	$scope.objectRequestD = {};
	$scope.objectRequestD.donation ={};
	$scope.objectRequestD.donation.donorId = "1";
	$scope.objectRequestD.donation.nonProfitId ="1";
	$scope.objectRequestD.donation.campaignId = "22";
	$scope.objectRequestD.donation.amount = "321";
	$scope.objectRequestD.token="";
	$scope.stripeResponse={};
	
	//Stripe.setPublishableKey(StripeService.getStripeApiKey());
	
	$scope.resul=true;
	$scope.button=false;
	$scope.token = "";
	
	
	
	//Stripe submit method
	$scope.stripeCallback = function (code, result) {
	    if (result.error) {
	        window.alert('it failed! error: ' + result.error.message);
	    } else {
	    	console.log("yes");
	    	$scope.upload = $upload.upload({
				url : 'rest/protected/donor/register',
				data : {
					email:$scope.donor.email,
					password:$scope.donor.password,
					name:$scope.donor.name,
					lastName:$scope.donor.lastName,
					country: "",
					facebookId: "",
					facebookToken: ""
				},
				file : ""
			}).success(function(response){
				  
				  if(response.code == "200")
				  {
					  $scope.logIn($scope.donor.email, $scope.donor.password);
					  $donationService.createDonation().then(function(data){
						  console.log(JSON.stringify(data));
						  $modalInstance.close();
					  });
					  
					
				  }
				  
			});//end success	
	    	
	    }
	};//end stripe submit
	
	
	$scope.checkAmount = function(){
		if($scope.donationInfo.donationPlan != 'custom'){
			
			$scope.donationInfo.amount = 5;
		}else{
			$scope.donationInfo.amount = 0;
			
		}
	};
	
	$scope.close = function() {
		$modalInstance.close();
		
	};
	
	$scope.createDonation = function(idDonor, stripeToken){
		
		$donationService.createDonation(idDonor, stripeToken,
										$scope.donationInfo.donationPlan, 
										$scope.donationInfo.amount)
										.then(function (data){
											console.log(JSON.stringify(data));
		
										});//end then
		
	};
	
	
	$scope.logIn = function(email, password ){
		 var credentials = {
				    email: email,
				    password: password
			   };
			 	  
		 AuthService.login(credentials).then(function (user) {
			  					    	
		    	if(user.code=="200"){
		    		if(user.type=="nonprofit"){
		    			$scope.setCurrentUser(user.idUser, user.firstName, user.img );
		    			
		        	}else if(user.type=="donor"){
		        		setCurrentUser(user.idUser, user.firstName+" "+user.lastName, user.img );
		        		$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
		        		//$state.go('treeSeed.donor', {donorId: response.donorId});
		        	}
		    	}
	    });//end AuthService  
	};//end logIn
	
	
	
	/*

	
	$scope.submit = function (){
		$scope.$form = angular.element(document.querySelector('#payment-form'));
		
		$scope.button = true;
		//$scope.pay={card: $scope.card, cvc:$scope.cvc, exp:$scope.exp }
		$scope.resul=false;
		
		$scope.stripeResponse=Stripe.card.createToken($scope.$form, $scope.stripeResponseHandle);

		
		return false;
	};
	
	
	$scope.stripeResponseHandle = function (status,response){
		 form = angular.element(document.querySelector('#payment-form'));
		if(response.error){
			 // Show the errors on the form
			$scope.$form.find('.payment-errors').text(response.error.message);
	        $scope.button = false;
	      } else {
	        // response contains id and card, which contains additional card details
	    	  $scope.objectRequestD.token=response.id;
	        // Insert the token into the form so it gets submitted to the server
	        // and submit
	    	
	    	form.append(' <input type="hidden" name="stripeToken" value="'+response.id+'" />');
	  
	    		    	
	    	
	    	$http.post('rest/protected/donation/donate', $scope.objectRequestD)
	    		.error(function(status){
	    			$scope.$form.find('.payment-errors').text(status.message);
	    	});
	    	//form.action='rest/protected/donation';
	    	
	    	//$scope.$form.get(0).submit();

	     }
	};
	*/
	
	
	/*
	$scope.progressControl = function(input, valid) {
		
		
		
		switch (input) {
		case 1:
			if (valid) {
				if (!$scope.stateInput1) {
					$scope.percent = $scope.percent + 12.5;
					$scope.stateInput1 = true;
				}

			} else {
				if ($scope.stateInput1) {
					$scope.percent = $scope.percent - 12.5;
					$scope.stateInput1 = false;
				}
			}
			break;
		case 2:
			if (valid) {
				if (!$scope.stateInput2) {
					$scope.percent = $scope.percent + 12.5;
					$scope.stateInput2 = true;
				}

			} else {
				if ($scope.stateInput2) {
					$scope.percent = $scope.percent - 12.5;
					$scope.stateInput2 = false;
				}
			}
			break;
		case 3:
			var temp = false;
			console.log('unique '+temp);
			$uniqueDataService.isEmailUnique($scope.donor.email).then( function(value) {
						temp = value;
			});
			
			console.log('unique '+temp);
			
			if (valid && temp == true) {
				if (!$scope.stateInput3) {
					$scope.percent = $scope.percent + 12.5;
					$scope.stateInput3 = true;
				}

			} else {
				if ($scope.stateInput3) {
					$scope.percent = $scope.percent - 12.5;
					$scope.stateInput3 = false;
				}
			}
			
			break;
		case 4:
			if (valid) {
				if (!$scope.stateInput4) {
					$scope.percent = $scope.percent + 12.5;
					$scope.stateInput4 = true;
				}

			} else {
				if ($scope.stateInput4) {
					$scope.percent = $scope.percent - 12.5;
					$scope.stateInput4 = false;
				}
			}
			break;
		case 5:
			if (valid) {
				if (!$scope.stateInput5) {
					$scope.percent = $scope.percent + 12.5;
					$scope.stateInput5 = true;
				}

			} else {
				if ($scope.stateInput5) {
					$scope.percent = $scope.percent - 12.5;
					$scope.stateInput5 = false;
				}
			}
			break;
		case 6:
			if (valid) {
				if (!$scope.stateInput6) {
					$scope.percent = $scope.percent + 12.5;
					$scope.stateInput6 = true;
				}

			} else {
				if ($scope.stateInput6) {
					$scope.percent = $scope.percent - 12.5;
					$scope.stateInput6 = false;
				}
			}
			
			break;
		case 7:
			if (valid) {
				if (!$scope.stateInput7) {
					$scope.percent = $scope.percent + 12.5;
					$scope.stateInput7 = true;
				}

			} else {
				if ($scope.stateInput7) {
					$scope.percent = $scope.percent - 12.5;
					$scope.stateInput7 = false;
				}
			}
			
			break;
		case 8:
			
			if (valid) {
				if (!$scope.stateInput8) {
					if($scope.donationInfo.donationPlan == 'custom'){
						
						$scope.percent = $scope.percent + 6;
						$scope.stateInput8 = true;
					
					}else if($scope.donationInfo.donationPlan != ''){
						
						$scope.percent = $scope.percent + 12.5;
						$scope.stateInput8 = true;
					
					}
					
					//$scope.oldVal = $scope.donationInfo.donationPlan;
					
				}else{
					
					if($scope.donationInfo.donationPlan == 'custom'){
						
						$scope.percent = $scope.percent - 6.5;//from amount
						
					}else{//is plan
						
						if($scope.oldVal == 'custom' ){
							if($scope.donationInfo.amount == ''){
								console.log('amount 0');
								$scope.percent = $scope.percent + 6.5;	
							}
						}
						
						$scope.donationInfo.amount = '';
						
					}
							
				}

			} else {
				if ($scope.stateInput8) {
					if($scope.donationInfo.donationPlan == 'custom'){
						$scope.percent = $scope.percent - 6;
						
					}else if($scope.donationInfo.donationPlan != ''){
						$scope.percent = $scope.percent - 12.5;
						$scope.stateInput8 = false;
					}
					
				}
			}
			
			$scope.oldVal = $scope.donationInfo.donationPlan;
			
		break;
			
	case 9:
			if (valid) {
				if (!$scope.stateInput9) {
					if($scope.donationInfo.donationPlan == 'custom'){
						$scope.percent = $scope.percent + 6.5;
						$scope.stateInput9 = true;
					}
				}
					

			} else {
				if ($scope.stateInput9) {
					if($scope.donationInfo.donationPlan == 'custom'){
						$scope.percent = $scope.percent - 6.5;
						$scope.stateInput9 = false;
					}
				}
			}
			
			break;
		
		}
		
		
	};*/

})

treeSeedAppControllers.controller('recurrentDonationController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, getPosts,  campaignId ,Session,
		$modalInstance) {

	

});


treeSeedAppControllers.controller('webhookController', function($http,$scope) {
	alert("WebHooks!");
	

});
