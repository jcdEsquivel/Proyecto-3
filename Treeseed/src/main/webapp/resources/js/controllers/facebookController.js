var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppLoginControllers.controller('facebookController', function($cookies, $http, $scope, $state, $rootScope, AUTH_EVENTS, AuthService, USER_ROLES, Session) {
	
	$scope.name = "";
	$scope.idFacebook = "";
	$scope.descripcionCampaign = "";
	$scope.idShareUser = "";
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
		  		    //**here a can take the token from the user in the case i need it :D
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
  		
  	var postInFacebook = function() {
  		checkLoginState(function(data) {
  			if (data.status !== 'connected') {
  				FB.login(function(response) {
  					if (response.status === 'connected')
  						{
  						  $scope.idShareUser = Session.userId;
  						  postInFacebookMe();
  						}
  						
  				}, {scope: 'email,user_location, publish_actions'});
  			}
  			else if (data.status === 'connected')
  			{
  				$scope.idShareUser = Session.userId;
  				postInFacebookMe();
  			}
  		})
  	} 
  	
  	$scope.postInFacebook = function(titleFace,descriptionFace,pictureFace)
  	{
  		postInFacebook(titleFace,descriptionFace,pictureFace);
  	}
  	
  	var postInFacebookMe = function(titleFace,descriptionFace,pictureFace)
  	{
  		var cause = "Animals";
  		var donateAmount = "1000";
  		var descripcionC = "Campaign for the Animals";
  		var campaignTittle = "Save the Animals";
  		var body = "";
  		
		body = descriptionFace;
		
		var id = $scope.idShareUser + "";
		
		var encrypted = CryptoJS.AES.encrypt(id, "golondrinasTicas");
		
		//var decrypted = CryptoJS.AES.decrypt(encrypted, "golondrinasTicas");
		//var result = decrypted.toString(CryptoJS.enc.Utf8);
		//console.log(result);
		
		FB.api('/me/feed','post', { 
			message: body, 
			link: "http://127.0.0.1:8080/treeseed.org/sharedDonation?id=" + encrypted,	
			picture: pictureFace,
			name: titleFace,
			description: descripcionC
		}, function(response) {
		    if (!response || response.error)
		    {
		    	console.log(JSON.stringify(response));
		    } 
		    else
		    {
		    	console.log('Post ID: ' + response.id);
		    }
		});	    	   					
  	}
  	
  	var facebookLogin = function() {
  		checkLoginState(function(data) {
  			if (data.status !== 'connected') {
  				FB.login(function(response) {
  					if (response.status === 'connected')
  						{
  							getFacebookData();
  						}
  						
  				}, {scope: 'email,user_location, publish_actions'});
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
		  $scope.idFacebook = response.id;
  		  console.log(response);
  		  $http.get('rest/login/checkFacebookuser?facebookId='+$scope.idFacebook).then(
  				function(res) {
  					if (res.data.code == "200") {
  					   console.log(res.data);
			    	   if(res.data.type=="donor"){
			        		Session.destroy();
							Session.create(res.data.idSession, res.data.idUser,
									USER_ROLES.donor);
							$cookies['userRoleTree'] = USER_ROLES.donor;
			        		setCurrentUser(res.data.idUser, res.data.firstName+" "+res.data.lastName, res.data.img );
			        	}
			    		$cookies['idSessionTree'] = res.data.idSession;
						$cookies['idUserTree'] = res.data.idUser;
						
			    		$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
			    		//$scope.remebermeUser = $scope.rememberMe;
			    		$modalInstance.close()	;	
			    		if(Session.userRole==USER_ROLES.donor){
			    			$state.go('treeSeed.donor', {donorId: res.data.idUser});
			    		}
			    	}else{
			    		
			    		$rootScope.$broadcast(AUTH_EVENTS.loginFailed); 
					    $scope.error=true;
			    	}
  				});
		});
  	}
})
;