/**
 * 
 */



var treeSeedAppControllers = angular.module('treeSeed.controller');



treeSeedAppControllers.controller('sharedController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams) {

	console.log("fuck inside");
	$scope.redirectToProfile = function()
	{
		//$scope.donorId.id = $stateParams.donorId;
		//var encry = $stateParams.donorId + "";
		//var decrypted = CryptoJS.AES.decrypt(encry, "golondrinasTicas");
		//var id = decrypted.toString(CryptoJS.enc.Utf8);
		$scope.fatherId = $stateParams.donorId;
		$state.go('treeSeed.donor', {donorId: $stateParams.donorId});
	}
	
	$scope.redirectToProfile();
	
});