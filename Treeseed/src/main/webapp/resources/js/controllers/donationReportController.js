/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('nonProfitDonationReportController', function($http,
		$scope, $state, Session) {
	
	//Variable declarations
	$scope.baseYear = 2010;
	$scope.month;
	$scope.year;
	$scope.years = [];
	$scope.reports = {};
	$scope.totalReports = 0;
	$scope.reportsPaginCurrentPage = 0;
	$scope.reportsRequest = {
		nonProfitId : $scope.currentUser.idUser,
		pageNumber : '',
		pageSize : '5',
		direction : "DESC",
		sortBy : ["dateTime"]
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
	
	$scope.getReports = function(pageNumber) {
		$scope.reportsRequest.pageNumber = pageNumber;
		$scope.reportsPaginCurrentPage = pageNumber;
		$scope.reportsRequest.month = $scope.month;
		$scope.reportsRequest.year = $scope.year;
		
		$http.post('rest/protected/donation/getDonationReport',
				$scope.reportsRequest).success(function(data, status) {
			if (data.code == 200) {
				$scope.reports = data.donations;
				$scope.totalReports = data.totalElements;
			}else{
				$scope.reports = [];
				$scope.errorServer(status);
				
			}
			
		}).error(function(status) {
			$scope.errorServer(status);
		});		
	};
	//end getReports

	$scope.getNewReports = function(newPage){
		$scope.getReports(newPage);
	}
	
	
});