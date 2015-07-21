/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('createTransparencyReportController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session, $upload, editableOptions, editableThemes) {

	$scope.percentageSpent = 0;
	$scope.totalCollected = 150;
	$scope.totalSpent = 0;

	$scope.openCreateForm = function() {
	    modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/createTransparencyReport.html',
			scope: $scope
		})
	};

	$scope.spences = [];

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
    };

    function updatePercentage(){
      for (var i = $scope.spences.length - 1; i >= 0; i--) {
      	$scope.totalSpent += $scope.spences[i].amount;
      };
      $scope.percentageSpent = $scope.totalSpent*100/$scope.totalCollected
      $scope.$apply;
    }
	
})
;

 