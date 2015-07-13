var treeSeedAppControllers = angular.module('treeSeed.controller',[ 'treeSeedServices']);

treeSeedAppControllers.controller('logoutController', function($rootScope,$state, $location, $sharedData, $scope, Session, AUTH_EVENTS,AuthService) {
	$scope.logout=function(){
		AuthService.guestSession()
		$scope.currentUser=null;
		$state.go("treeSeed.index");
		$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
	}
	
});


treeSeedAppControllers.controller('indexController', function($state, $location, $sharedData, $scope, Session, AUTH_EVENTS) {
	$scope.ro= Session.userRole;
	$scope.rolesroles = $scope.userRoles.guest;
	$scope.$on(AUTH_EVENTS.loginSuccess,function(){
		$scope.ro= {id:Session.id,userId:Session.userId,role: Session.userRole};
	})
});


treeSeedAppControllers.controller('headerMenuCtrl', function($state, $location, $rootScope,
		$sharedData, $scope, AUTH_EVENTS, Session , $modal) {
	if(Session.userRole == $scope.userRoles.guest){
		$scope.logButton=true;
		$scope.logged=false;
	}else{
		$scope.logged=true;
		$scope.logButton=false;
	}
	
	
	$scope.$on(AUTH_EVENTS.loginSuccess,function(){
		
		$scope.logged=true;
		$scope.logButton=false;
		
		$scope.user =  $scope.currentUser;
		$scope.name = $scope.user.userName;
		$scope.img = $scope.user.userImage;
	});
	
	$scope.animationsEnabled = true;
	$scope.open = function () {

    var modalInstance = $modal.open({
      animation: $scope.animationsEnabled,
      templateUrl: 'layouts/components/page_login.html',
      controller: 'loginController',
      resolve: {
    	  setCurrentUser: function(){
    		  return $scope.setCurrentUser;
    	  }
      }
    	 
    })
  };
  
  
  
  $scope.toggleAnimation = function () {
      $scope.animationsEnabled = !$scope.animationsEnabled;
    };  
	
	//$scope.isUserLogged = $sharedData.isUserLogged();

});

treeSeedAppControllers.controller('leftMenuCtrl', function($state,
		$location, $sharedData, $scope, AUTH_EVENTS, Session ) {
	$scope.$on(AUTH_EVENTS.loginSuccess,function(){
		if (Session.userRole == $scope.userRoles.nonprofit) {
			$scope.menu= "layouts/components/nGOMenu.html";
		} else if(Session.userRole == $scope.userRoles.donor) {
			$scope.menu= "layouts/components/donorMenu.html";
		}
	})
	$scope.$on(AUTH_EVENTS.logoutSuccess,function(){
		$scope.menu="";
	})
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





