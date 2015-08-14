/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('loadingController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams) {

	$scope.redirectToProfile = function()
	{
		$scope.nonprofit.id = $stateParams.nonProfitId;
		
		var decrypted = CryptoJS.AES.decrypt(encrypted, "golondrinasTicas");
		var id = decrypted.toString(CryptoJS.enc.Utf8);
		$scope.fatherId = Session.userId;
		$state.go('treeSeed.donor', {donorId: id});
	}
	
	$scope.redirectToProfile();
	
});