
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




 