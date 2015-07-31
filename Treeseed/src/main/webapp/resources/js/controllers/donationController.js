/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('simpleDonationController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $modal, $stateParams, StripeService) {
	

	$scope.percent = 0;
	$scope.donor = {
			name:'',
			lastName:'',
			email:''
	};
	
	$scope.card = '';
	$scope.cvc='';
	$scope.ex_month='';
	$scope.ex_year='';
	$scope.amount = '';
	
	$scope.stateInput1 = false;
	$scope.stateInput2 = false;
	$scope.stateInput3 = false;
	$scope.stateInput4 = false;
	$scope.stateInput5 = false;
	$scope.stateInput6 = false;
	$scope.stateInput7 = false;
	$scope.stateInput8 = false;

	

	$scope.errorCard = undefined;

	
	$scope.objectRequestD = {};
	$scope.objectRequestD.donation ={};
	$scope.objectRequestD.donation.donorId = "1";
	$scope.objectRequestD.donation.nonProfitId ="1";
	$scope.objectRequestD.donation.campaignId = "22";
	$scope.objectRequestD.donation.amount = "321";
	$scope.objectRequestD.token="";
	$scope.stripeResponse={};
	Stripe.setPublishableKey(StripeService.getStripeApiKey());
	
	$scope.resul=true;
	$scope.button=false;
	$scope.token = "";
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
			if (valid) {
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
					$scope.percent = $scope.percent + 12.5;
					$scope.stateInput8 = true;
				}

			} else {
				if ($scope.stateInput8) {
					$scope.percent = $scope.percent - 12.5;
					$scope.stateInput8 = false;
				}
			}
			
			break;
			
		
		}
	};
	
	$scope.close = function() {
		$modalInstance.close();
		
	};
	

});

treeSeedAppControllers.controller('recurrentDonationController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, getPosts,  campaignId ,Session,
		$modalInstance) {

	

});
