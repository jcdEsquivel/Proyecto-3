/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('postAdminController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $rootScope, $modal) {
	console.log('inctr');
	$scope.posts = [];
	console.log('inctr');
	$scope.getPosts = function() {

	};

	$scope.openModal = function() {

		var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/createPostModal.html',
			controller : 'createPostController',
			size: 'lg',
			resolve : {
				getPosts : function() {
					return $scope.getPosts;
				}
			}

		})

	};

});

treeSeedAppControllers.controller('createPostController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, getPosts,
		$modalInstance) {

	$scope.post = {
		nonprofitId : '',
		title : '',
		descripcion : ''
	};

	$scope.createPost = function()
	{
		$http.post('rest/protected/postNonprofit/getNonProfitProfile',
				$scope.requestObject).success(function(mydata, status) {
			$scope.nonprofit = mydata.nonprofit;
			console.log(mydata.owner)
			if (mydata.owner == true) {
				$scope.isOwner = true;
			} else {
				$scope.isOwner = false;
			}

		}).error(function(mydata, status) {
			alert(status);
		});
	};

	$scope.close = function(){
		$modalInstance.close();
	}
	
});
