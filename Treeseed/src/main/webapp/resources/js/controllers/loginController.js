var treeSeedAppLoginControllers = angular.module('treeSeedLoginController', [ 'treeSeedServices' ]);

treeSeedAppLoginControllers.controller('loginController', function($cookies, $http, $scope, $state, $rootScope, AUTH_EVENTS, AuthService, $modalInstance, setCurrentUser,USER_ROLES, Session, $modal) {
	$scope.error = false;
	$scope.rememberMe = false;
	$scope.credentials = {
		    email: '',
		    password: ''
		  };
	var app_id = '319610508162843';
	
	$scope.init = function()
	{
		(function(d, s, id){
		    var js, fjs = d.getElementsByTagName(s)[0];
		    if (d.getElementById(id)) {return;}
		    js = d.createElement(s); js.id = id;
		    js.src = "//connect.facebook.net/en_US/sdk.js";
		    fjs.parentNode.insertBefore(js, fjs);
		  }(document, 'script', 'facebook-jssdk'));
		
		window.fbAsyncInit = function() {

		  	FB.init({
		  		
		    	appId      : app_id,
		    	status     : true,
		    	cookie     : true, 
		    	xfbml      : true, 
		    	version    : 'v2.1'
		    		
		  	});

		  	FB.getLoginStatus(function(response) {
		  		if (response.status === 'connected') {
		  		    //$scope.requestObject.donor.facebookToken = response.authResponse.accessToken;
		  		  }
		    	statusChangeCallback(response, function() {});
		  	});
	  	};	
		  
	}
	$scope.init();
	
	var statusChangeCallback = function(response, callback) {
    	if (response.status === 'connected') {
      		//getFacebookData();
    	} else {
     		callback(false);
    	}
  	}

  	var checkLoginState = function(callback) {
    	FB.getLoginStatus(function(response) {
      		callback(response);
    	});
  	}
  	
  	var facebookLogin = function() {
  		checkLoginState(function(data) {
  			if (data.status !== 'connected') {
  				FB.login(function(response) {
  					if (response.status === 'connected')
  						getFacebookData();
  				}, {scope: 'email,user_location'});
  			}
  			else if (data.status === 'connected')
  			{
  				getFacebookData();
  			}
  		})
  	}  	  
	
	$scope.loginFacebook = function()
	{
		facebookLogin();
	}
	
	var getFacebookData =  function() {	
  		
		FB.api('/me?fields=id,first_name,last_name,location,email', function(response) {
		  $scope.facebookId = response.id;
  		  $http.get('rest/login/checkFacebookuser?facebookId='+$scope.facebookId).success(
  				function(res) {
  					if (res.code == "200") {
			    	   if(res.type=="donor"){
			        		Session.destroy();
							Session.create(res.idSession, res.idUser,
									USER_ROLES.donor);
							$cookies['userRoleTree'] = USER_ROLES.donor;
			        		setCurrentUser(res.idUser, res.firstName+" "+res.lastName, res.img );
			        	}
			    		$cookies['idSessionTree'] = res.idSession;
						$cookies['idUserTree'] = res.idUser;
						
			    		$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
			    		//$scope.remebermeUser = $scope.rememberMe;
			    		$modalInstance.close()	;
			    		if(Session.userRole==USER_ROLES.donor){
			    			$state.go('treeSeed.donorDashboard');
			    		}
			    	}else{
			    		
			    		$rootScope.$broadcast(AUTH_EVENTS.loginFailed); 
					    $scope.error=true;
			    	}
  				}).error(function(status) {
  					$scope.errorServer(status);
  				});
		});
  	}
	
		  $scope.login = function (credentials) {
		    AuthService.login(credentials).then(function (user) {
		    	if(user.code=="200"){
		    		
		    		if(user.type=="nonprofit"){
		    			setCurrentUser(user.idUser, user.firstName, user.img );
		        	}else if(user.type=="donor"){
		        		setCurrentUser(user.idUser, user.firstName+" "+user.lastName, user.img );
		        	}
		    		$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
		    		//$scope.remebermeUser = $scope.rememberMe;
		    		$modalInstance.close();
		    		if(Session.userRole==USER_ROLES.nonprofit){
		    			$state.go('treeSeed.nonProfitDashboard');
		    		}else if(Session.userRole==USER_ROLES.donor){
		    			$state.go('treeSeed.donorDashboard');
		    		}
		    	}else{
		    		
		    		$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
				     
				      $scope.error=true;
		    	}
		      
		    }, function(status){
		    	$scope.errorServer(status);
		      });
			
		  };
		  
		  $scope.close=function(){
			  $modalInstance.close();
		  }
		  
	
		  $scope.errorServer = function(status) {
				
				var modalInstance = $modal.open({
					animation : $scope.animationsEnabled,
					templateUrl :'layouts/components/feedbackModal.html',
					controller : 'errorHandlerCtlr',
					size : 'sm',//,
					resolve : {
						code : function() {
							return status;
						}
					}
				});
			}
})
;

