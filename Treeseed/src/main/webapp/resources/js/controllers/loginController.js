var treeSeedAppLoginControllers = angular.module('treeSeedLoginController', [ 'treeSeedServices' ]);

treeSeedAppLoginControllers.controller('loginController', function($scope, $state, $rootScope, AUTH_EVENTS, AuthService, $modalInstance, setCurrentUser,USER_ROLES, Session) {
	$scope.error = false;
	$scope.credentials = {
		    email: '',
		    password: ''
		  };
		  $scope.login = function (credentials) {
		    AuthService.login(credentials).then(function (user) {
		    	if(user.code=="200"){
		    		
		    		if(user.type=="nonprofit"){
		    			setCurrentUser(user.idUser, user.firstName, user.img );
		        	}else if(user.type=="donor"){
		        		setCurrentUser(user.idUser, user.firstName+" "+user.lastName, user.img );
		        	}
		    		$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
		    		$modalInstance.close();
		    		if(Session.userRole==USER_ROLES.nonprofit){
		    			$state.go('treeSeed.nonProfit', {nonProfitId: user.idUser});
		    		}else if(Session.userRole==USER_ROLES.donor){
		    			$state.go('treeSeed.donor', {donorId: user.idUser});
		    		}
		    	}else{
		    		
		    		$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
				     
				      $scope.error=true;
		    	}
		      
		    });
		  };
})
;

