/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('createTransparencyReportController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session, $upload, editableOptions, editableThemes) {

	$scope.percentageSpent = 0;
	//The $scope.totalCollected be retrieved from the DB
	$scope.totalCollected = 150;
	$scope.spences = [];
	$scope.totalSpent;
	$scope.description = "";
	$scope.uiOptions = "{percent: '"+$scope.percentageSpent+"',lineWidth: 10,trackColor: '{{app.color.light}}',barColor: '{{app.color.success}}',scaleColor: '{{app.color.light}}',size: 188,lineCap: 'butt',animate: 1000}";

	$scope.openCreateForm = function() {
	    modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/createTransparencyReport.html',
			scope: $scope
		})
	};

    $scope.addSpence = function() {
      $scope.inserted = {
      	id: $scope.spences.length+1,
        description: '',
        amount: null
      };
      $scope.spences.push($scope.inserted);
    };

    $scope.saveSpence = function(data, id) {
      angular.extend(data, {id: id});
      updatePercentage();
    };

    $scope.removeSpence = function(index) {
      $scope.spences.splice(index, 1);
      updatePercentage();
    };

    function updatePercentage(){
      $scope.totalSpent = 0;
      for (var i = $scope.spences.length - 1; i >= 0; i--) {
      	$scope.totalSpent += $scope.spences[i].amount;
      };
      $scope.percentageSpent = Math.round($scope.totalSpent*100/$scope.totalCollected)
    }

	$scope.createReport = function(){
		for (var i = $scope.spences.length - 1; i >= 0; i--) {
      		$scope.description += $scope.spences[i].description +"#"+$scope.spences[i].amount + "/";
      	};
      	$scope.requestObject = {};
      	$scope.transparencyReport = {};
      	$scope.requestObject.transparencyReport = $scope.transparencyReport;
		$scope.requestObject.transparencyReport.amountIn = $scope.totalCollected;
		$scope.requestObject.transparencyReport.amountOut = $scope.totalSpent;
		$scope.requestObject.transparencyReport.description = $scope.description;
		$scope.requestObject.transparencyReport.nonProfitId = Session.userId;
		
		$http.post('rest/protected/transparencyReport/create',
				$scope.requestObject).success(function(mydata, status) {
					modalInstance.close();
		}).error(function(mydata, status) {
			alert(status);
		});	
	}
});

treeSeedAppControllers.controller('searchTransparencyReportController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $rootScope, $modal,
		$stateParams, Session) {

	//Variable declarations
	$scope.baseYear = 2010;
	$scope.month;
	$scope.year;
	$scope.years = [];
	$scope.reports = [];
	$scope.totalReports = 0;
	$scope.reportsPaginCurrentPage = 0;
	$scope.reportsRequest = {
		nonProfitId : $stateParams.nonProfitId,
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

	//Gets execute when reports tab is clicked. is called from getNonProfitProfileController
	$scope.$on('loadReports',function(){
		$scope.getReports(1);
	});

	$scope.getReports = function(pageNumber) {

		$scope.reportsRequest.pageNumber = pageNumber;
		$scope.reportsPaginCurrentPage = pageNumber;
		$scope.reportsRequest.month = $scope.month;
		$scope.reportsRequest.year = $scope.year;

		$http.post('rest/protected/transparencyReport/getTransparencyReports',
				$scope.reportsRequest).success(function(data, status) {

			if (data.code == 200) {
				$scope.reports = data.transparencyReports;
				$scope.totalReports = data.totalElements;
			}else{
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