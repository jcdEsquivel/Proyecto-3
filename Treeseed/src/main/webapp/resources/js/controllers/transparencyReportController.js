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
})
;

 