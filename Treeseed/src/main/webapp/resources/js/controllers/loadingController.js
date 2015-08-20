/**
 * 
 */



var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('sharedController', function($scope,$state,
		$http, $location, $modal, $log, $timeout, $stateParams) {

	console.log('loading2');
	$scope.redirectToProfile = function()
	{
		 var fatherId = $stateParams.fatherId;
		 var id = $stateParams.id;
		 var type = $stateParams.type;
		//$scope.donorId.id = $stateParams.donorId;
		//var encry = $stateParams.donorId + "";
		//var decrypted = CryptoJS.AES.decrypt(encry, "golondrinasTicas");
		//var id = decrypted.toString(CryptoJS.enc.Utf8);
		$scope.fatherId = fatherId;
		
		console.log('father '+fatherId);
		
		
		switch(type){
		case 'donor':
			$state.go('treeSeed.donor', {donorId: id});
			break;
		case 'nonProfit':
			$state.go('treeSeed.nonProfit', {nonProfitId: id});
		break;
		case 'campaign':
			$state.go('treeSeed.campaign', {campaignId: id});
			break;
		default:
			$state.go('treeSeed.index');
		
		}
		
	}
	
	$scope.redirectToProfile();
	
});