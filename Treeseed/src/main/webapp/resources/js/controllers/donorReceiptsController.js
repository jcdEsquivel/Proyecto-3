var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('donorReceiptsController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session, $state, $rootScope, $sharedData, AUTH_EVENTS, AuthService) {

	//Variable declarations
	$scope.baseYear = 2010;
	$scope.month;
	$scope.year;
	$scope.years = [];
	$scope.receipts = [];
	$scope.totalReceipts = 0;
	$scope.receiptsPaginCurrentPage = 0;
	$scope.receiptsRequest = {
		donorId : $scope.currentUser.idUser,
		pageNumber : '',
		pageSize : '5',
		direction : "DESC",
		sortBy : ['dateTime']
	};

	//Call to getYears() to initialize the combo box in the view
	getYears();

	//Function to get the years that will be displayed
	function getYears(){
		$scope.date = new Date();
		$scope.currentYear = $scope.date.getFullYear();
		for ($scope.baseYear;$scope.currentYear>=$scope.baseYear;$scope.baseYear++) {
			$scope.years.push($scope.baseYear);
		};
	}

	$scope.getReceipts = function(pageNumber) {

		$scope.receiptsRequest.pageNumber = pageNumber;
		$scope.receiptsPaginCurrentPage = pageNumber;
		$scope.receiptsRequest.month = $scope.month;
		$scope.receiptsRequest.year = $scope.year;

		$http.post('rest/protected/donation/getDonationOfDonorPerMonth',
				$scope.receiptsRequest).success(function(data, status) {

			if (data.code == 200) {
				$scope.receipts = data.donations;
				$scope.totalReceipts = data.totalElements;
			}else{
				$scope.receipts = [];
				$scope.errorServer(status);
				
			}
			
		}).error(function(status) {
			$scope.errorServer(status);
		});	
	};
	//end getReports

	$scope.getNewReceipts = function(newPage){
		$scope.getReceipts(newPage);
	}	
});
