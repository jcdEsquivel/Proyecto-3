'use strict';
var treeSeedAppServices = angular.module('treeSeed.services', []);

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

treeSeedAppServices.service('$sharedData', function() {
	var loggedUser = "";
	var type = "";
	var img = "";
	var loged = false;
	var ongName = "Territorio de Zaguates"
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
		}
	}
});

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

/******************************************************Factories***********************************************/

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


treeSeedAppServices.factory('AuthService', function($http, Session, USER_ROLES) {
	var authService = {};

	authService.login = function(credentials) {
		return $http.post('rest/login/checkuser', credentials).then(function(res) {
			if(res.data.code=="200"){
				if(res.data.type=="nonprofit"){
					Session.destroy();
					Session.create(res.data.idSession, res.data.idUser, USER_ROLES.nonprofit);
					
				}else if(res.data.type=="donor"){
					Session.destroy();
					Session.create(res.data.idSession, res.data.idUser, USER_ROLES.donor);
				}
				
			}
			
			return res.data;
		});
	};

	authService.isAuthenticated = function() {
		return !!Session.userId;
	};
	
	authService.guestSession = function() {
		 Session.create("0","0",USER_ROLES.guest);
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

treeSeedAppServices.factory('AuthResolver', function ($q, $rootScope, $state) {
  return {
    resolve: function () {
      var deferred = $q.defer();
      var unwatch = $rootScope.$watch('currentUser', function (currentUser) {
        if (angular.isDefined(currentUser)) {
          if (currentUser) {
            deferred.resolve(currentUser);
          } else {
            deferred.reject();
            $scope.animationsEnabled = true;
    		

            var modalInstance = $modal.open({
              animation: $scope.animationsEnabled,
              templateUrl: 'layouts/components/page_login.html',
              controller: 'loginController'
            });

          
          $scope.toggleAnimation = function () {
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


