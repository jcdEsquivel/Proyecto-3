var treeSeedAppControllers = angular.module('treeSeed.controller',[ 'treeSeedServices']);


treeSeedAppControllers.controller('indexController', function($state, $location, $sharedData, $scope, $modal) {

	
	$scope.animationsEnabled = true;
		$scope.open = function () {

        var modalInstance = $modal.open({
          animation: $scope.animationsEnabled,
          templateUrl: 'layouts/components/page_login.html',
          controller: 'loginController'
        });
      };
      
      $scope.toggleAnimation = function () {
          $scope.animationsEnabled = !$scope.animationsEnabled;
        };  
});


treeSeedAppControllers.controller('headerMenuCtrl', function($state, $location,
		$sharedData, $scope, AUTH_EVENTS) {
	
	$scope.$on(AUTH_EVENTS.loginSuccess,function(){
		$scope.user =  $scope.currentUser()
		$scope.name = $scope.user.name;
		$scope.img = $scope.user.img;
	});
	
	
	//$scope.isUserLogged = $sharedData.isUserLogged();

});

treeSeedAppControllers.controller('leftMenuCtrl', function($state,
		$location, $sharedData, $scope) {
	$scope.getMenu = function() {
		if ($sharedData.getUserType() == "NGO") {
			return "layouts/components/nGOMenu.html";
		} else if($sharedData.getUserType() == "Donor") {
			return "layouts/components/donorMenu.html";
		}
	}
});


treeSeedAppControllers.controller('navigateController', function($state,
		$location, $sharedData, $scope) {
	$scope.navigateDonor = function() {
		$state.go('treeSeed.donor');
	}

	$scope.navigateONG = function() {
		$state.go('treeSeed.nonProfit');
	}

	$scope.navigateCampaing = function() {
		$state.go('treeSeed.campaingViewer');
	}
});


treeSeedAppControllers.controller('HeaderCtrl', [
		'$scope',
		'$http',
		'$sharedData',
		'$state',
		function($scope, $http, $sharedData, $state) {

			$scope.selected = undefined;
			$scope.states = [ 'Territorio de Zaguates' ];
			// Any function returning a promise object can be used to load
			// values asynchronously
			$scope.getLocation = function(val) {
				return $http.get(
						'http://maps.googleapis.com/maps/api/geocode/json', {
							params : {
								address : val,
								sensor : false
							}
						}).then(function(res) {
					var addresses = [];
					angular.forEach(res.data.results, function(item) {
						addresses.push(item.formatted_address);
					});
					return addresses;
				});
			};

		} ]);

treeSeedAppControllers.controller('logoutController', function($sharedData,
		$location, $scope, $state, $modal) {
	$scope.logout = function() {
		$sharedData.setLoggedUser('');
		$sharedData.setLoged(false);
		$state.go('signin');
	}
});


