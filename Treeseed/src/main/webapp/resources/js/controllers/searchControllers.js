var treeSeedAppControllers = angular.module('treeseed.controller',
		[ 'treeSeed.services' ]);

treeSeedAppControllers.controller('nonProfitSearchController', function($scope,
		$http, $location, $modal, $log, $timeout) {

	$scope.nonprofit = {};
	$scope.nonprofit.country = "";
	$scope.nonprofit.cause = "";
	$scope.requestObject1 = {};
	$scope.requestObject2 = {};
	$scope.itemPerPage = [ 10, 25, 50, 100 ];
	$scope.currentPage = 1;
	$scope.totalItems = 5;
	$scope.requestObject = {};
	$scope.requestObject.pageNumber = 1;
	$scope.requestObject.pageSize = 10;
	$scope.requestObject.direction = "DESC";
	$scope.requestObject.sortBy = [];
	$scope.requestObject.searchColumn = "ALL";
	$scope.requestObject.searchTerm = "";

	$scope.init = function() {
		$scope.requestObject1.lenguage = $scope.selectLang;
		$scope.requestObject1.type = "country";
		$http.post('rest/protected/catalog/getAllCatalog',
				$scope.requestObject1).then(function(response) {
			$scope.selectSortOptionsCountry = response.data.catalogs;
		});
		$scope.requestObject2.lenguage = $scope.requestObject1.lenguage;
		$scope.requestObject2.type = "cause";
		$http.post('rest/protected/catalog/getAllCatalog',
				$scope.requestObject2).then(function(response) {
			$scope.selectSortOptionsCause = response.data.catalogs;

		});
	}

	$scope.init();

	$scope.searchNonProfit = function(page) {

		$scope.requestObject.pageNumber = page;
		$scope.requestObject.name = $scope.name;
		$scope.requestObject.country = $scope.nonprofit.country.id;
		$scope.requestObject.cause = $scope.nonprofit.cause.id;

		$http.post('rest/protected/searches/getNonprofits',
				$scope.requestObject).success(function(mydata, status) {
			$scope.nonprofits = mydata.nonprofits;
			$scope.totalItems = mydata.totalElements;
		}).error(function(mydata, status) {
			alert(status);
		});

		$scope.pageChangeHandler = function(num) {
			$scope.searchNonProfit(num);
		};

	};

});
