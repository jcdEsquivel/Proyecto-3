
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('dragnDropController', function($scope){
	console.log('asdf');
	$scope.showImage=false;
	$scope.imageDropped = function(){
	    $scope.$emit('profilePicture', $scope.uploadedFile);
	    
	    $scope.image=$scope.uploadedFile;
	    
	    $scope.showImage=true;
	    
	    
	    //Clear the uploaded file
	    $scope.uploadedFile = null;
	};
	
	
	
});

treeSeedAppControllers.controller('DatepickerCtrl', ['$scope', function($scope) {
    $scope.today = function() {
      $scope.dt = new Date();
    };
    $scope.today();

    $scope.clear = function () {
      $scope.dt = null;
    };

    // Disable weekend selection
    $scope.disabled = function(date, mode) {
      return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
    };

    $scope.toggleMin = function() {
      $scope.minDate = $scope.minDate ? null : new Date();
    };
    $scope.toggleMin();

    $scope.open = function($event) {
      $event.preventDefault();
      $event.stopPropagation();

      $scope.opened = true;
    };

    $scope.dateOptions = {
      formatYear: 'yy',
      startingDay: 1,
      class: 'datepicker'
    };

    $scope.initDate = new Date();
    $scope.formats = ['dd-MMMM-yyyy'];
    $scope.format = $scope.formats[0];
  }])
  ; 


treeSeedAppControllers.controller('selectUserController', function($scope){
	if($scope.selectLang=="English"){
		$scope.srcDonor = "resources/images/donor-simple.jpg";
		$scope.srcNonprofit = "resources/images/nonprofit-simple.jpg";
	}else if($scope.selectLang=="Español"){
		$scope.srcDonor = "resources/images/donador-simple.jpg";
		$scope.srcNonprofit = "resources/images/ong-simple.jpg";
	}
	
	$scope.hoverDonor = function(){
		
		if($scope.selectLang=="English"){
			$scope.srcDonor = "resources/images/donor-click.jpg";
		}else if($scope.selectLang=="Español"){
			$scope.srcDonor = "resources/images/donador-click.jpg";
		}
	    
	};
	
	$scope.unHoverDonor = function(){
		
		if($scope.selectLang=="English"){
			$scope.srcDonor = "resources/images/donor-simple.jpg";
		}else if($scope.selectLang=="Español"){
			$scope.srcDonor = "resources/images/donador-simple.jpg";
		}
	    
	};
	
	$scope.unHoverNonprofit = function(){
		
		if($scope.selectLang=="English"){
			$scope.srcNonprofit = "resources/images/nonprofit-simple.jpg";
		}else if($scope.selectLang=="Español"){
			$scope.srcNonprofit = "resources/images/ong-simple.jpg";
		}
	    
	};
	
	$scope.hoverNonprofit = function(){
		
		if($scope.selectLang=="English"){
			$scope.srcNonprofit = "resources/images/nonprofit-click.jpg";
		}else if($scope.selectLang=="Español"){
			$scope.srcNonprofit = "resources/images/ong-click.jpg";
		}
	    
	};
	
	
	
});



 