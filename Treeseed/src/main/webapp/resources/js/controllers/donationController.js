/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('simpleDonationController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $modal, $stateParams) {
	
	$scope.errorCard = undefined;
	$scope.objectRequestD = {};
	$scope.objectRequestD.donation ={};
	$scope.objectRequestD.donation.donorId = "1";
	$scope.objectRequestD.donation.nonProfitId ="1";
	$scope.objectRequestD.donation.campaignId = "22";
	$scope.objectRequestD.donation.amount = "123";
	$scope.objectRequestD.token="";
	$scope.stripeResponse={};
	Stripe.setPublishableKey('pk_test_uLHafCqM7q7GeVZxDkabaA2y');
	
	$scope.resul=true;
	$scope.button=false;
	$scope.token = "";
	$scope.submit = function (){
		$scope.$form = angular.element(document.querySelector('#payment-form'));
		
		$scope.button = true;
		//$scope.pay={card: $scope.card, cvc:$scope.cvc, exp:$scope.exp }
		$scope.resul=false;
		
		$scope.stripeResponse=Stripe.card.createToken($scope.$form, $scope.stripeResponseHandle);
		if($scope.stripeResponse.id==undefined){
			$scope.errorCard = $scope.stripeResponse.message;
		}
		
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
	    	alert(response.id);
	        // Insert the token into the form so it gets submitted to the server
	        // and submit
	    	
	    	form.append(' <input type="hidden" name="stripeToken" value="'+response.id+'" />');
	  
	    		    	
	    	
	    	$http.post('rest/protected/donation/donate', $scope.objectRequestD).success(function(mydata, status){
	    		
	    	});
	    	//form.action='rest/protected/donation';
	    	
	    	//$scope.$form.get(0).submit();

	     }
	};

});

treeSeedAppControllers.controller('recurrentDonationController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, getPosts,  campaignId ,Session,
		$modalInstance) {

	

});
