var treeSeedAppControllers = angular.module('treeSeed.controller',
		[ 'treeSeedServices' ]);

treeSeedAppControllers.controller('logoutController', function($rootScope,
		$state, $location, $sharedData, $scope, Session, AUTH_EVENTS,
		AuthService) {
	$scope.logout = function() {
		AuthService.guestSession()
		$scope.currentUser = null;
		$state.go("treeSeed.index");
		$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
	}

});

treeSeedAppControllers.controller('indexController', function($state,
		$location, $sharedData, $scope, Session, AUTH_EVENTS) {

	/*
	 * $scope.ro= Session.userRole; $scope.rolesroles = $scope.userRoles.guest;
	 * $scope.$on(AUTH_EVENTS.loginSuccess,function(){ $scope.ro=
	 * {id:Session.id,userId:Session.userId,role: Session.userRole}; })
	 */
});

treeSeedAppControllers.controller('headerMenuCtrl', function($state, $location,
		$http, $rootScope, $sharedData, $scope, AUTH_EVENTS, Session, $modal) {
	$scope.nom = $sharedData.getLoggedUser();
	$scope.img = $sharedData.getImg();
	$sharedData.getUserCountry();
	$scope.country = "";

	$scope.goProfile = function() {
		if (Session.userRole == $scope.userRoles.nonprofit) {
			$state.go('treeSeed.nonProfit', {
				nonProfitId : Session.userId
			});
		} else if (Session.userRole == $scope.userRoles.donor) {
			$state.go('treeSeed.donor', {
				donorId : Session.userId
			});
		}
	}
	
	$scope.goSettings = function() {
		if (Session.userRole == $scope.userRoles.nonprofit) {
			$state.go('treeSeed.nonProfitSettings');
		} else if (Session.userRole == $scope.userRoles.donor) {
			$state.go('treeSeed.donorSettings');
		}
	}

	$scope.temps = [];

	$scope.generalSearch = function(val) {
		$scope.country = $sharedData.getUserCountry();
		console.log('in');
		return $http.post('rest/protected/generalSearch/search', {
			filter : val,
			country : $scope.country
		}).then(function(response) {
			return response.data.results;

		}); // end $http.post
	};// end generalSearch

	$scope.animationsEnabled = true;

	$scope.init = function() {

		if (Session.userRole == $scope.userRoles.guest) {
			$scope.logButton = true;
			$scope.logged = false;
		} else {
			$scope.logged = true;
			$scope.logButton = false;

			$scope.user = $scope.currentUser;
			$scope.name = $scope.user.userName;
			$scope.img = $scope.user.userImage;
		}

	};// end init()

	$scope.$on(AUTH_EVENTS.loginSuccess, function() {

		$scope.logged = true;
		$scope.logButton = false;

		$scope.user = $scope.currentUser;
		$scope.name = $scope.user.userName;
		$scope.img = $scope.user.userImage;
	});

	$scope.open = function() {

		var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/page_login.html',
			controller : 'loginController',
			resolve : {
				setCurrentUser : function() {
					return $scope.setCurrentUser;
				}
			}

		})
	};

	$scope.toggleAnimation = function() {
		$scope.animationsEnabled = !$scope.animationsEnabled;
	};

	$scope.init();

});// end header controller

treeSeedAppControllers.controller('leftMenuCtrl', function($state, $location,
		$sharedData, $scope, AUTH_EVENTS, Session) {

	$scope.init = function() {

		if (Session.userRole == $scope.userRoles.nonprofit) {
			$scope.menu = "layouts/components/nGOMenu.html";
		} else if (Session.userRole == $scope.userRoles.donor) {
			$scope.menu = "layouts/components/donorMenu.html";
		}
	};

	$scope.$on(AUTH_EVENTS.loginSuccess, function() {
		if (Session.userRole == $scope.userRoles.nonprofit) {
			$scope.menu = "layouts/components/nGOMenu.html";
		} else if (Session.userRole == $scope.userRoles.donor) {
			$scope.menu = "layouts/components/donorMenu.html";
		}
	});

	$scope.$on(AUTH_EVENTS.logoutSuccess, function() {
		$scope.menu = "";
	})

	$scope.init();
}); // end leftMenuCtrl

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

treeSeedAppControllers.controller('sideMenuNonprofitController', function(
		$state, $scope, Session) {
	$scope.goProfile = function() {
		$state.go('treeSeed.nonProfit', {
			nonProfitId : Session.userId
		});
	}

});

treeSeedAppControllers.controller('sideMenuDonorController', function(
		$state, $scope, Session) {
	$scope.goProfile = function() {
		$state.go('treeSeed.donor', {
			donorId : Session.userId
		});
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


treeSeedAppControllers.controller('feedbackCtrl', function($modalInstance ,  $scope, title, text) {

	$scope.title = title;
	$scope.text = text;
	
	$scope.close = function(){
		$modalInstance.close();
	};

});


treeSeedAppControllers.controller('errorHandlerCtlr', function($modalInstance, $scope, code, $state, AUTH_EVENTS, 
		$rootScope, AuthService, $location, $sharedData) {
	
	$scope.title= "FEEDBACK-MODAL.GENERAL-TITLE"
	
	switch(code) {
    case 400:
        $scope.text = "FEEDBACK-MODAL.ERROR-400-TEXT";
        break;
    case 401:
    	$scope.text = "FEEDBACK-MODAL.ERROR-401-TEXT";
        break;
    case 408:
    	$scope.text = "FEEDBACK-MODAL.ERROR-408-TEXT";
        break;
    case 404:
    	$scope.text = "FEEDBACK-MODAL.ERROR-404-TEXT";
        break;
    case 500:
		$scope.text = "FEEDBACK-MODAL.ERROR-500-TEXT";
		$state.go("treeSeed.index");
		break;
    case 520:
    	$scope.text = "FEEDBACK-MODAL.ERROR-520-TEXT";
        break;
    case 10:
    	$scope.text = "FEEDBACK-MODAL.ERROR-10-TEXT";
        break;
    default:
    	$scope.text = "FEEDBACK-MODAL.ERROR-500-TEXT";
	    $state.go("treeSeed.index");
		
	}
	
	$scope.close = function(){
		$modalInstance.close();
	};

});
