/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');



treeSeedAppControllers.controller('createTransparencyReportController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session, $upload, editableOptions, editableThemes) {

	$scope.percentageSpent = 0;
	$scope.totalCollected = 0;
	$scope.overDraft = 0;
	$scope.isOverDraft = false;
	$scope.spences = [];
	$scope.totalSpent;
	$scope.description = "";
	$scope.notSpecified;
	$scope.uiOptions = "{percent: '"+$scope.percentageSpent+"',lineWidth: 10,trackColor: '{{app.color.light}}',barColor: '{{app.color.success}}',scaleColor: '{{app.color.light}}',size: 188,lineCap: 'butt',animate: 1000}";

	function getTotalCollected(){
		//Today's date
		var today = new Date();
		$scope.mm = today.getMonth()+1;
		//Donation request
		$scope.donationRequest = {};
		$scope.donationRequest.nonProfitId = Session.userId;
		$scope.donationRequest.month = $scope.mm;
		$http.post('rest/protected/donation/getDonationOfNonProfitPerMonth',
				$scope.donationRequest).success(function(mydata, status) {
					if(mydata.code==200){
						$scope.totalCollected = mydata.donation.amount;
					}else{
						
					}
		}).error(function(status) {
			$scope.errorServer(status);
		});
	}

	$scope.openCreateForm = function() {
		$scope.percentageSpent = 0;
		$scope.totalSpent = 0;
		$scope.notSpecified = 0;
		$scope.spences = [];
		$scope.started = false;
		$scope.isOverDraft = false;
		getTotalCollected();
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
      $scope.notSpecified = 0;
      for (var i = $scope.spences.length - 1; i >= 0; i--) {
      	$scope.totalSpent += $scope.spences[i].amount;
      	$scope.notSpecified = $scope.totalCollected - $scope.totalSpent;
      };
      $scope.percentageSpent = Math.round($scope.totalSpent*100/$scope.totalCollected);
      if($scope.percentageSpent>100)
      {
      	$scope.isOverDraft = true;
      	$scope.overDraft = $scope.totalSpent - $scope.totalCollected;
      }
      else
      {
      	$scope.isOverDraft = false;
      }
      if($scope.spences.length>0)
      {
      	$scope.started =  true;
      }
      else
      {
      	$scope.started = false;
      }
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
					if(mydata.code==200){
						modalInstance.close();
					}else{
						$scope.errorServer(mydata.code);
					}
					$scope.getReports(1);
					modalInstance.close();
		}).error(function(status) {
			$scope.errorServer(status);
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
	$scope.currentPage = 1;
	
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
				$scope.zeroReports = false;
			}else{
				$scope.reports = [];
				$scope.zeroReports = true;
				
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