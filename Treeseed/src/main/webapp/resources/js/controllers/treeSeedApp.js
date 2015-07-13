var treeSeedAppMainControllers = angular.module('treeSeedMainController',['treeSeedServices', 'treeSeedConstants','ngCookies']);
treeSeedAppMainControllers.controller('AppCtrl', function(   $rootScope, $cookies, $scope, AUTH_EVENTS,  $translate,   $localStorage,   $window, $sharedData, USER_ROLES,AuthService, Session ) {
      // add 'ie' classes to html
      var isIE = !!navigator.userAgent.match(/MSIE/i);
      isIE && angular.element($window.document.body).addClass('ie');
      isSmartDevice( $window ) && angular.element($window.document.body).addClass('smart');
      //Session management
      
      $scope.currentUser = null;
     
      if( $cookies['idUserTree']== undefined){
    	  
    	  AuthService.guestSession();
    	  
      }else if( $cookies['idUserTree']=='0'){
    	  
    	  AuthService.guestSession();
    	  
      }else if( $cookies['idUserTree']){
    	  console.log('Logged');
    	  $scope.currentUser={};    	 
    	  $scope.currentUser.idUser = $cookies['idUserTree'];
  	      $scope.currentUser.userName = $cookies['userNameTree'];
  	      $scope.currentUser.userImage = $cookies['userImageTree'];
  	      Session.create(  $cookies['idSessionTree'],  $cookies['idUserTree'],  $cookies['userRoleTree']);
  	   
  	      
      }else{
    	  AuthService.guestSession();
      }
      
      
      
      $scope.userRoles = USER_ROLES;
      
      
     
      $scope.isAuthorized = AuthService.isAuthorized;
      
      
      $scope.setCurrentUser = function (idUser, userName, userImage) {
    	  $scope.currentUser = {};
    	    $scope.currentUser.idUser = idUser;
    	    $scope.currentUser.userName = userName;
    	    $scope.currentUser.userImage = userImage;
    	    $cookies['userNameTree'] = userName;
    	    $cookies['userImageTree'] =  userImage;
    	    
      };
      
      
      // config
      $scope.app = {
        name: 'TreeSeed.org',
        version: '2.0.2',
        // for chart colors
        color: {
          primary: '#23b7e5',
          info:    '#7266ba',
          success: '#27c24c',
          warning: '#fad733',
          danger:  '#f05050',
          light:   '#e8eff0',
          dark:    '#3a3f51',
          black:   '#1c2b36'
        },
        settings: {
          themeID: 1,
          navbarHeaderColor: 'bg-black',
          navbarCollapseColor: 'bg-white-only',
          asideColor: 'bg-black',
          headerFixed: true,
          asideFixed: true,
          asideFolded: false,
          asideDock: false,
          container: false
        }
      }

     
      // save settings to local storage
      if ( angular.isDefined($localStorage.settings) ) {
        $scope.app.settings = $localStorage.settings;
      } else {
        $localStorage.settings = $scope.app.settings;
      }
      $scope.$watch('app.settings', function(){
        if( $scope.app.settings.asideDock  &&  $scope.app.settings.asideFixed){
          // aside dock and fixed must set the header fixed.
          $scope.app.settings.headerFixed = true;
        }
        // for box layout, add background image
        $scope.app.settings.container ? angular.element('html').addClass('bg') : angular.element('html').removeClass('bg');
        // save to local storage
        $localStorage.settings = $scope.app.settings;
      }, true);
     
      // angular translate
      $scope.lang = { isopen: false };
      $scope.langs = {en:'English', es:'Español'};
      $scope.selectLang = $scope.langs[$translate.proposedLanguage()] || "English";
      //$sharedData.setLenguaje($scope.selectLang);
      $scope.setLang = function(langKey, $event) {
        // set the current lang
        $scope.selectLang = $scope.langs[langKey];
        //$sharedData.setLenguaje($scope.selectLang);
        // You can change the language during runtime
        $translate.use(langKey);
        $scope.lang.isopen = !$scope.lang.isopen;
      };

      function isSmartDevice( $window )
      {
          // Adapted from http://www.detectmobilebrowsers.com
          var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
          // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
          return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
      }

  });