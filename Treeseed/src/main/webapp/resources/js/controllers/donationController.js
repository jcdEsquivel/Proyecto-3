/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('BillReportController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session, $upload) {
	
	//Variable declarations
	$scope.baseYear = 2010;
	$scope.month;
	$scope.year;
	$scope.years = [];
	$scope.reports = [];
	$scope.totalReports = 0;
	$scope.reportsPaginCurrentPage = 0;
	$scope.currentPage = 1;
	
	$scope.reportsRequest = {
		donorId : $stateParams.donorId,
		pageNumber : '',
		pageSize : '5',
		direction : "DESC",
		sortBy : ['dateTime']
	};

	//Call to getYears() to initialize the combo box in the view
	getYears();

	//Function to get the years that will be displaye
	function getYears(){
		$scope.date = new Date();
		$scope.currentYear = $scope.date.getFullYear();
		for ($scope.baseYear;$scope.currentYear>=$scope.baseYear;$scope.baseYear++) {
			$scope.years.push($scope.baseYear);
		};
	}
	
	$scope.getReports = function(pageNumber) {

		$scope.reportsRequest.pageNumber = pageNumber;
		$scope.reportsPaginCurrentPage = pageNumber;
		$scope.reportsRequest.month = $scope.month;
		$scope.reportsRequest.year = $scope.year;
		
		console.log($scope.reportsRequest);

		$http.post('rest/protected/donation/getDonationDonorReport',
				$scope.reportsRequest).success(function(data, status) {
			if (data.code == 200) {
				
				console.log(data);
				
				$scope.reports = data.donations;
				$scope.totalReports = data.totalElements;
				
			}else{
				$scope.reports = [];
				console.log('Error : '+data.errorMessage);
			}
			
		}).error(function(mydata, status) {
			console.log(status);
			console.log("No data found");
		});		
	};
	//end getReports

	$scope.getNewReports = function(newPage){
		$scope.getReports(newPage);
	}
		
});


treeSeedAppControllers.controller('guestDonationController', function($http, Session, $donationService,StripeService, $stateParams, $modal,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $modalInstance, $stateParams, $rootScope, setCurrentUser, nonprofitId, USER_ROLES, titleFace, descriptionFace, pictureFace) {

	
	$scope.percent = 0;
	
	$scope.donor = {
			name:'',
			lastName:'',
			email:'',
			password:'',
			confirm_password:''
	};
	
	$scope.number = '';
	$scope.cvc = '';
	$scope.expiry = '';
	
	$scope.campaignId = 0;
	
	$scope.donationInfo = {
		amount : 0,
		donationPlan: 'custom'
	};
	
	
	if($stateParams.campaignId){
		$scope.campaignId = $stateParams.campaignId;
	}
	
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


	//Stripe.setPublishableKey(StripeService.getStripeApiKey());
	
	$scope.resul=true;
	$scope.button=false;
	$scope.token = "";
	

	//Stripe submit method
	$scope.stripeCallback = function (code, result) {
	    if (result.error) {
	        window.alert('it failed! error: ' + result.error.message);
	    } else {
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
								  
					  $donationService.createDonation('newCard', nonprofitId, $scope.campaignId, response.donorId,
							  result.id, $scope.donationInfo.donationPlan, $scope.donationInfo.amount,
							  0).then(function(data){
						  
						  
						  $scope.close();
						  
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



treeSeedAppControllers.controller('donorDonationController', function($http,$timeout, $donationService,StripeService, $stateParams, Session, $modal,
		$scope, $upload, $state, AuthService,$translate, AUTH_EVENTS, $modalInstance,USER_ROLES, $stateParams, $rootScope, setCurrentUser, nonprofitId,  titleFace, descriptionFace, pictureFace) {

//VARIABLES DEFINITION	
    $scope.hasDonations = false;
	$scope.percent = 0;
	$scope.sameCard={
		validation: false
	};
	
	$scope.hideSubmit = false;
	
	$scope.nonprofitId = nonprofitId;
	
	$scope.requieredCard = true;
	
	$scope.submitNew = false;
	
	$scope.load = {
		isLoading : true
	};
	
	$scope.hide = ' **** **** **** ';
	
	$scope.donorCards = {
			oldCard : '',
			cards: []
	};
	
	$scope.cardRequest = {
			selectedCard:'',
			card:{
				donor:{
					id: Session.userId
				}
			}	
	};
	
	
	$scope.recurrableRequest= {
			donation:{
				id: '',
				campaignId: '',
				donorId: '',
				donorFatherId: '',
				nonProfitId: '',
				amount: '',
				cardId: '',
				donationDate: ''
			},
			id: '',
			campaignId: '',
			nonProfitId: '',
			amount: '',
			donationDate: '',
			token: '',
			startPeriodDate: '',
			endPeriodDate: '',
			plan: '',
	};
	
	$scope.donorCards = [];
	
	$scope.number = '';
	$scope.cvc = '';
	$scope.expiry = '';
	
	$scope.campaignId = 0;
	
	$scope.donationInfo = {
		amount : 0,
		donationPlan: 'custom'
	};

	

	if($stateParams.campaignId){
		$scope.campaignId = $stateParams.campaignId;
	}

	$scope.errorCard = undefined;

	
	$scope.resul=true;
	$scope.button=false;
	$scope.token = "";
	
// END VARIABLES DEFINITION	
	
	$scope.setRequired = function(){

		if($scope.cardRequest.selectedCard == 'new'){
			$scope.requieredCard = true;
			$scope.number = '';
			$scope.cvc = '';
			$scope.expiry = '';
		}else{
			$scope.requieredCard = false;
			$scope.number = '4242 4242 4242 4242';
			$scope.cvc = '123';
			$scope.expiry = '02/2099';
		}
	};
	
	$scope.getCreditCards = function(){
		
		$http.post('rest/protected/card/getByDonor', $scope.cardRequest)
			.success(function(mydata, status) {
			
				$scope.donorCards.cards = mydata.cards;
				$scope.load.isLoading = false;
			
				if(Object.keys(mydata.cards).length > 0){
					$scope.hasDonations = true;//shows edit donation tab
				}
				
		}).error(function(mydata, status) {
			//we have to do something here
		});
	}
	
	$scope.getCreditCards();
	
	
	$scope.submitForm = function(){
		
		if($scope.cardRequest.selectedCard == 'new'){
			/*var newCreditCard = $scope.number.slice(-4);

			for (index = 0; index < $scope.donorCards.cards.length; ++index) {
			console.log(newCreditCard +' - '+$scope.donorCards.cards[index].last4Numbers);
				if($scope.donorCards.cards[index].last4Numbers == newCreditCard){
					$scope.sameCard.validation = true;
					console.log('same');
					return;
				}
				
			}//end for*/
			
			  $timeout(function(){
				 
				  document.getElementById('btn-submit').click();
	          });
			
		}else{//create donation with old crea
	
			$donationService.createDonation('oldCard', nonprofitId, $scope.campaignId, Session.userId ,
					  $scope.cardRequest.selectedCard, $scope.donationInfo.donationPlan, $scope.donationInfo.amount,
					  0).then(function(data){
				 
				  $scope.close();
			  });
				
		}
		
		
	};//end submit form
	
	//Stripe submit method
	$scope.stripeCallback = function (code, result) {
		
	    if (result.error) {
	        window.alert('it failed! error: ' + result.error.message);
	    } else {
	    				
			  $donationService.createDonation('newCard', nonprofitId, $scope.campaignId, Session.userId ,
					  result.id, $scope.donationInfo.donationPlan, $scope.donationInfo.amount,
					  0).then(function(data){
				 
				  $scope.close();
			  });
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
		$scope.openSummary();
		$modalInstance.close();
		
	};

	$scope.openSummary = function(){
		
		$translate('DONATION-MODAL.SUCCESS-1').then(
				function successFn(translation) {
					 $scope.titleMessage1 = translation;
					 
				});
		
		var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/page_donationSummary.html',
			controller : 'summaryDonationController',
			size : 'md',//,
			resolve : {
				
				nonprofitId: function(){
					return nonprofitId;
				},
				titleFace: function(){
					return titleFace;
				},
				descriptionFace: function(){
					return descriptionFace;
				},
				pictureFace: function(){
					return pictureFace;
				},
				amount: function(){
					return $scope.donationInfo.amount;
				},
				plan: function(){
					return $scope.donationInfo.donationPlan;
				}
			}
			
		});
	}
	
	
	
});



treeSeedAppControllers.controller('summaryDonationController', function($http,
  $modalInstance, $stateParams, $scope ,nonprofitId, titleFace,$translate,
  descriptionFace, pictureFace, amount, plan) { 

	 $scope.titleMessage1 = '';
	 $scope.titleMessage2 = '';
	 $scope.donationTypeCustom = '';
	 $scope.donationTypePlan = '';
	 $scope.month = '';
	 $scope.year = '';
 
 $scope.titleFaceS = titleFace;
 $scope.descriptionFace = descriptionFace;
 $scope.imageFace = pictureFace;
 
 $scope.donationMessage = "";
 $scope.planMessage = "";
 $scope.amount = "";
 $scope.amount = "";
 $scope.type = '';
 
 $translate('DONATION-MODAL.SUCCESS-1').then(
	function successFn(translation) {
		 $scope.titleMessage1 = translation;
		
	});
 
 
 $translate('DONATION-MODAL.SUCCESS-2').then(
			function successFn(translation) {
				 $scope.titleMessage2 = translation;
			});
 

 

 

 
 $translate('DONATION-MODAL.MESSAGE').then(
			function successFn(translation) {
				$scope.donationMessage = translation
			});
 
 
 $translate('DONATION-MODAL.MESSAGE').then(
			function successFn(translation) {
				$scope.year = translation
			});

 


 if (plan == 'custom') {
  $scope.amount = "$"+amount;
 
  $translate('DONATION-MODAL.SUCCESS-DONATION-TYPE-CUSTOM').then(
			function successFn(translation) {
				 $scope.type = translation;
				 
			});
  
 } else {
  
	 $translate('DONATION-MODAL.SUCCESS-DONATION-TYPE-PLAN').then(
				function successFn(translation) {
					 $scope.type = translation
				});
	 
	 $translate('DONATION-MODAL.PLAN-MESSAGE').then(
				function successFn(translation) {
					 $scope.planMessage = translation
				});
	 
	 
  switch (plan) {
  case '1':
   $scope.amount = "$10";
   break;
  case '2':
   $scope.amount = "$18";
   break;
  case '3':
   $scope.amount = "$36";
   break;
  case '4':
   $scope.amount = "$50";
   break;
  case '5':
   $scope.amount = "$100";
   break;
  case '6':
   $scope.amount = "$250";
   break;

  default:
   break;
  }
 }
 
 if ($stateParams.campaignId) {
  $scope.campaignId = $stateParams.campaignId;
 } else {
  $scope.campaignId = "";
 }
 
 $scope.close = function() {

  $modalInstance.close();

 };
});



treeSeedAppControllers.controller('editRecurrableDonation', function($http, $scope,
		  $modal, Session, $timeout) { 
	$scope.showFeedBackMessage = false;
	$scope.disableEdit = false;
	$scope.recurrableDonations = [];
	$scope.request= {
			nonprofitId: $scope.nonprofitId,			
			campaignId:$scope.campaignId,			
			donorId: Session.userId,			
			stripeId:'',
			donationId: '',
			planId: ''
	};

	$scope.modal = {
			selectedDonorPlan:null,
			selectedPlan: '',
			samePlan:''
			
	};
	
	$scope.editRecurrable = function(donationId, amount){
		$scope.modal.samePlan= amount;
		$scope.request.donationId = donationId;
	};
	
	$scope.getCurrentDonations = function(){
		
		$http.post('rest/protected/recurrableDonation/getRecurrableDonations',$scope.request)
		.success(function(response) {
			$scope.recurrableDonations = response.donations;
		});
		
	};
	
	$scope.editPlan = function(){
		$scope.disableEdit = true;
		$scope.request.planId = $scope.modal.selectedPlan;//seletedPlan;
		
		$http.post('rest/protected/recurrableDonation/editRecurrableDonation',$scope.request)
		.success(function(response) {
			
			$scope.modal.selectedDonorPlan = null;
			$scope.modal.selectedPlan = '';
			$scope.modal.samePlan='';
			$scope.getCurrentDonations();
			$scope.showSuccessFeedBack();
			$scope.disableEdit = false;
		});
	};
	
	$scope.init = function(){
		$scope.getCurrentDonations();
	};
	
	
	$scope.showSuccessFeedBack = function(){
		$scope.showFeedBackMessage = true;
		$timeout($scope.hiddeFeedBack, 5000);
	};
	
	$scope.hiddeFeedBack = function(){
		$scope.showFeedBackMessage = false
	};
	
	$scope.init();

});




treeSeedAppControllers.controller('editPortfolioDonations', function($http, $scope, donorId, refreshPortfolio, $modalInstance,
		  $modal, Session) { 
//VARIABLES
	$scope.isSubmited = false;
	
	$scope.refreshPortfolio = refreshPortfolio;
	
	$scope.request= {
			nonprofitId: '',			
			campaignId:'',			
			donorId: donorId,			
			stripeId:'',
			donationId: '',
			planId: '',
			donations: []
	};

//VARIABLES END	

	$scope.getCurrentDonations = function(){
		
		$http.post('rest/protected/recurrableDonation/getRecurrableDonationsPortfolio',$scope.request)
		.success(function(response) {
			
			$scope.request.donations = response.donations;
		});
		
	};
	
	
	$scope.saveDonations = function(){
		
		$scope.isSubmited = true;
		$http.post('rest/protected/recurrableDonation/editMultipleRecurrableDonation',$scope.request)
		.success(function(response) {
			
			$scope.feedBack();
			$scope.refreshPortfolio();
			$scope.isSubmited = false;
			$modalInstance.close();
		});
	};
	
	$scope.setChanged = function(r){
		var amountPlan = 0;
	
		switch(parseInt(r.planId)){
		case 1:
			amountPlan = 10;
			break;
		case 2:
			amountPlan = 18;
			break;
		case 3:
			amountPlan = 36;
			break;
		case 4:
			amountPlan = 50;
			break;
		case 5:
			amountPlan = 100;
			break;
		case 6:
			amountPlan = 250;
			break;
		}
		
		
		
		if(amountPlan == r.amount){
			r.changed = false;
		}else{
			r.changed = true;
		}
		

	};
	
	$scope.init=function(){
		$scope.getCurrentDonations();
	};


	
	$scope.feedBack = function(){
		var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl :'layouts/components/feedbackModal.html',
			controller : 'feedbackCtrl',
			size : 'sm',//,
			resolve : {
				title : function() {
					return "FEEDBAKC-MODAL.UPDATED-DONATIONS-TITLE";
				},
				text: function(){
					return  "FEEDBAKC-MODAL.UPDATED-DONATIONS-TEXT";
				}
			}
		});
	};
	
	$scope.close = function(){
		$modalInstance.close();
	};
	
	$scope.init();

});





