
'use strict';
var treeSeedAppServices = angular.module('treeSeedServices', [ 'ngCookies' ]);

treeSeedAppServices.value('version', '0.1');

treeSeedAppServices.service('$uniqueDataService', function($http) {

	return {
		isEmailUnique : function(email) {

			var request = {
					
						email: email
					
					
			};
			
			
			
			return $http.post('rest/protected/users/isEmailUnique', JSON.stringify(request))
					.then(function(response) {
					

						if (response.data.codeMessage == 'UNIQUE') {
							return true;
						} else {
							return false;
						}
					});
		}
	}
});


treeSeedAppServices.service('$donationService', function($http) {

	return {
		createDonation : function(type, nonprofitId, campaignId, donorId, stripeToken, plan, amount, fatherId) {

		
			
			if(plan == 'custom'){//simple donation
				var request = {
						id: '',
						campaignId:'',
						nonProfitId:'',
						amount: '',
						donationDate:'',
						token: stripeToken,
						startPeriodDate:'',
						endPeriodDate:'',
						plan:'',
						donation:{
							id:'',
							campaignId: campaignId,
							donorId: donorId,
							donorFatherId: fatherId,
							nonProfitId: nonprofitId,
							amount: amount,
							cardId:''
						}	
				};
				
				return $http.post('rest/protected/donation/donate', JSON.stringify(request))
						.then(function(response) {				
							return response.data;
						});
			
			}else{//monthly donation
				
				var requestSuscription = {
						id: '',
						campaignId:'',
						nonProfitId:'',
						amount: '',
						donationDate:'',
						token: stripeToken,
						startPeriodDate:'',
						endPeriodDate:'',
						plan: plan,
						donation:{
							id:'',
							campaignId: campaignId,
							donorId: donorId,
							donorFatherId: fatherId,
							nonProfitId: nonprofitId,
							amount: '',
							cardId:''
						}	
				};
				
				return $http.post('rest/protected/recurrableDonation/subscription', JSON.stringify(requestSuscription))
						.then(function(response) {				
							return response.data;
						});
				
				
			}
			
		}//end createSimpleDonation
	
	}
});



/*
'use strict';
var treeSeedAppServices = angular.module('treeSeedServices', []);

treeSeedAppServices.value('version', '0.1');

treeSeedAppServices.service('$uniqueDataService', function($http) {

	return {
		isEmailUnique : function(email) {

			return $http.post('rest/protected/users/isEmailUnique', email)
					.then(function(response) {
						console.log(response.data.codeMessage);

						if (response.data.codeMessage == 'UNIQUE') {
							return true;
						} else {
							return false;
						}
					});
		}
	}
});
*/

treeSeedAppServices.service('$sharedData', function($http) {
	var loggedUser = "";
	var type = "";
	var img = "";
	var loged = false;
	var ongName = "Territorio de Zaguates"
	var userCountry = "";

	return {
		getLoggedUser : function() {
			return loggedUser;
		},
		setLoggedUser : function(value) {
			loggedUser = value;
		},
		getLoged : function() {
			return loged;
		},
		setLoged : function(value) {
			loged = value;
		},
		getOngName : function() {
			return ongName;
		},
		setOngName : function(value) {
			ongName = value;
		},
		getUserType : function() {
			return type;
		},
		setUserType : function(value) {
			type = value;
		},
		getImg : function() {
			return img;
		},
		setImg : function(value) {
			img = value;
		},
		getUserCountry : function() {
			if (userCountry == "") {
				//ipinfo to get the users country
				$http.get('http://ipinfo.io/json').then(function(data) {
					var jsonData = JSON.parse(JSON.stringify( data));
					userCountry = jsonData.data.country;
					
					return userCountry;
				});
			}
			
			return userCountry;
		

		}// end getUserCountry
	} // end return
}); // end shareDataService
/*
treeSeedAppServices.service('$uniqueDataService', function($http) {

	return {
		isEmailUnique : function(email) {

			return $http.post('rest/protected/users/isEmailUnique', email)
					.then(function(response) {
						console.log(response.data.codeMessage);

						if (response.data.codeMessage == 'UNIQUE') {
							return true;
						} else {
							return false;
						}
					});
		}
	}
});
*/
treeSeedAppServices.service('$userData', function() {
	var users = [ {
		Name : "Ricardo Bonilla",
		Email : "eldoc@gmail.com",
		Password : "123",
		Type : "donor",
		Imagen : "a8.jpg"

	}, {
		Name : "Aramis",
		Email : "Aramis@hola",
		Password : "test456",
		Type : "donor",
		Imagen : ""
	}, {
		Name : "Camilo",
		Email : "camilo@hola",
		Password : "test123",
		Type : "donor",
		Imagen : ""
	}, {
		Name : "Fabian",
		Email : "Fabian@hola",
		Password : "test789",
		Type : "donor",
		Imagen : ""
	}, {
		Name : "Hola",
		Email : "a@hola",
		Password : "1",
		Type : "donor",
		Imagen : ""
	}, {
		Name : "Territorio de Zaguates",
		Email : "territoriodezaguates@gmail.com",
		Password : "12345",
		Type : "ONG",
		Imagen : "territorio.jpg"
	} ];
	return {
		getUsers : function() {
			return users;
		},
		setUsers : function(value) {
			users = value;
		}
	}
});

treeSeedAppServices.service('lazyService',['JQ_CONFIG','MODULE_CONFIG', function(JQ_CONFIG,MODULE_CONFIG) {

	this.load = function(srcs, callback) {
		return {
			deps : [
					'$ocLazyLoad',
					'$q',
					function($ocLazyLoad, $q) {
						var deferred = $q.defer();
						var promise = false;
						srcs = angular.isArray(srcs) ? srcs
								: srcs.split(/\s+/);
						if (!promise) {
							promise = deferred.promise;
						}
						angular
								.forEach(
										srcs,
										function(src) {
											console.log(src);
											promise = promise
													.then(function() {
														if (JQ_CONFIG[src]) {
															return $ocLazyLoad
																	.load(JQ_CONFIG[src]);
														}
														angular
																.forEach(
																		MODULE_CONFIG,
																		function(
																				module) {
																			if (module.name == src) {
																				name = module.name;
																			} else {
																				name = src;
																			}
																		});
														return $ocLazyLoad
																.load(name);
													});
										});
						deferred.resolve();
						return callback ? promise
								.then(function() {
									return callback();
								}) : promise;
					} ]
		}
	}
}])

treeSeedAppServices.service('Session', function() {

	this.create = function(sessionId, userId, userRole) {
		this.id = sessionId;
		this.userId = userId;
		this.userRole = userRole;
	};
	this.destroy = function() {
		this.id = null;
		this.userId = null;
		this.userRole = null;
	};
})

treeSeedAppServices.service('StripeService', function() {

	var stripeApiKey = "pk_test_uLHafCqM7q7GeVZxDkabaA2y";

	return {
		getStripeApiKey : function() {
			return stripeApiKey;
		}/*,
		setStripeApiKey : function(value) {
			stripeApiKey = value;
		}*/
	}
})

/** ****************************************************Factories********************************************** */



treeSeedAppServices.factory('AuthService', function($http, $cookies, Session, USER_ROLES ) {
	var authService = {};

	authService.login = function(credentials) {
		return $http.post('rest/login/checkuser', credentials).then(
				function(res) {
					if (res.data.code == "200") {
						if (res.data.type == "nonprofit") {
							Session.destroy();
							Session.create(res.data.idSession, res.data.idUser,
									USER_ROLES.nonprofit);
							$cookies['userRoleTree'] = USER_ROLES.nonprofit;
						} else if (res.data.type == "donor") {
							Session.destroy();
							Session.create(res.data.idSession, res.data.idUser,
									USER_ROLES.donor);
							$cookies['userRoleTree'] = USER_ROLES.donor;
						}

						$cookies['idSessionTree'] = res.data.idSession;
						$cookies['idUserTree'] = res.data.idUser;

					}

					return res.data;
				});
	};
	
	authService.getSession = function() {
		
		var sessionrequest= {id: Session.id, role: Session.userRole}
		
		return $http.post('rest/login/getSession', sessionrequest).then(
				function(res) {	
					console.log(res.id)
					return res;
				});
	};

	authService.isAuthenticated = function() {
		return !!Session.userId;
	};

	authService.guestSession = function() {
		Session.create("0", "0", USER_ROLES.guest);
		$cookies['idSessionTree'] = "0";
		$cookies['idUserTree'] = "0";
		$cookies['userRoleTree'] = USER_ROLES.guest;
		$cookies['userNameTree'] = "0";
	    $cookies['userImageTree'] =  "0";
	}

	authService.isAuthorized = function(authorizedRoles) {
		if (!angular.isArray(authorizedRoles)) {
			authorizedRoles = [ authorizedRoles ];
		}
		return (authService.isAuthenticated() && authorizedRoles
				.indexOf(Session.userRole) !== -1);
	};

	return authService;
});

treeSeedAppServices.factory('AuthInterceptor', function($rootScope, $q,
		AUTH_EVENTS) {
	return {
		responseError : function(response) {
			$rootScope.$broadcast({
				401 : AUTH_EVENTS.notAuthenticated,
				403 : AUTH_EVENTS.notAuthorized,
				419 : AUTH_EVENTS.sessionTimeout,
				440 : AUTH_EVENTS.sessionTimeout
			}[response.status], response);
			return $q.reject(response);
		}
	};
})

treeSeedAppServices
		.factory(
				'AuthResolver',
				function($q, $rootScope, $state) {
					return {
						resolve : function() {
							var deferred = $q.defer();
							var unwatch = $rootScope
									.$watch(
											'currentUser',
											function(currentUser) {
												if (angular
														.isDefined(currentUser)) {
													if (currentUser) {
														deferred
																.resolve(currentUser);
													} else {
														deferred.reject();
														$scope.animationsEnabled = true;

														var modalInstance = $modal
																.open({
																	animation : $scope.animationsEnabled,
																	templateUrl : 'layouts/components/page_login.html',
																	controller : 'loginController'
																});

														$scope.toggleAnimation = function() {
															$scope.animationsEnabled = !$scope.animationsEnabled;
														};
													}
													unwatch();
												}
											});
							return deferred.promise;
						}
					};
				})
