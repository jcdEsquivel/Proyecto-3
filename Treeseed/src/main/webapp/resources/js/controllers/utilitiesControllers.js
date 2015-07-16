
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('dragnDropController', function($scope){
	$scope.showImage=false;
	$scope.imageDropped = function(){
	    $scope.$emit('profilePicture', $scope.uploadedFile);
	    
	    $scope.image=$scope.uploadedFile;
	    
	    $scope.showImage=true;
	    
	    
	    //Clear the uploaded file
	    $scope.uploadedFile = null;
	};
	
	
	
});


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



 