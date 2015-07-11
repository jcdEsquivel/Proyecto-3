var treeSeedAppLoginControllers = angular.module('treeSeedLoginController', [ 'treeSeedServices' ]);

treeSeedAppLoginControllers.controller('loginController', function($scope, $state, $userData, $sharedData, AUTH_EVENTS, AuthService) {
    if($sharedData.getLoged()==false){
      $scope.authError = "entre";
      $scope.users = $userData.getUsers();
      $scope.login = function() {
        var totalUsers = $scope.users.length;
          var usernameTyped = $scope.user.name;
          var useremail = $scope.user.email;
          var passwordTyped = $scope.user.password;
          var name = "";
          var img = "";
          var userType = "";
          var loggedin= false;
          
         for(i=0; i < totalUsers; i++ ) {
              if( $scope.users[i].email == usernameTyped && $scope.users[i].Password == passwordTyped) {
                  loggedin=true;
                  userType = $scope.users[i].Type;
                  img = $scope.users[i].Imagen;
                  name = $scope.users[i].Name;
                  }
          }

          if( loggedin === true ) {
              $sharedData.setLoggedUser(name);
              $sharedData.setLoged(true);
               $sharedData.setUserType(userType);
               $sharedData.setImg(img);

              if(userType == "ONG"){
                   $state.go('treeSeed.nonProfit');
              }else{
                  $state.go('treeSeed.index');
              }
              

          } else {
              $scope.authError="Email o contraseña incorrecta";
              $sharedData.setLoged(false);
          }
      };
    }else{
      //$state.go('treeSeed.index');
    }
})
;

