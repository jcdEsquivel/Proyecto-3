var treeSeedAppConstants = angular.module('treeSeedConstants',[]);

treeSeedAppConstants.constant('AUTH_EVENTS', {
	  loginSuccess: 'auth-login-success',
	  loginFailed: 'auth-login-failed',
	  logoutSuccess: 'auth-logout-success',
	  sessionTimeout: 'auth-session-timeout',
	  notAuthenticated: 'auth-not-authenticated',
	  notAuthorized: 'auth-not-authorized'
	});

treeSeedAppConstants.constant('USER_ROLES', {
	  nonprofit: 'nonprofit',
	  donor: 'donor',
	  guest: 'guest'
	});
	
	