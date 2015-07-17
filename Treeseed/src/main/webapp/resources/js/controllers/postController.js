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
			size : 'lg',
			resolve : {
				getPosts : function() {
					return $scope.getPosts;
				}
			}

		})

	};

});

treeSeedAppControllers.controller('createPostController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, getPosts, Session,
		$modalInstance) {

	$scope.image;
	$scope.post = {
		nonprofitId : Session.userId,
		title : '',
		descripcion : ''
	};

	$scope.$on('profilePicture', function(event, args) {
		$scope.image = args;
		$scope.uploadImage = true;

		var file = args;
		var imageType = /image.*/;

		if (file.type.match(imageType)) {
			var reader = new FileReader();
			reader.onload = function(e) {
				var img = new Image();
				img.src = reader.result;
				fileDisplayArea.src = img.src;
			}
			reader.readAsDataURL(file);

		} else {
			alert("File not supported!");
		}

	});

	$scope.createPost = function() {

		$http({
			method : 'POST',
			url : 'rest/protected/postNonprofit/register',
			headers : {
				'Content-Type' : undefined
			},
			transformRequest : function(data) {
				 var formData = new FormData();

			        formData.append('data', new Blob([angular.toJson(data.data)], {
			            type: "application/json"
			        }));
			        formData.append("file", data.file);
			        return formData;
			},
			data : {
				data : $scope.post,
				file : $scope.image
			}

		}).
		success(function (data, status, headers, config) {
			$scope.close();
		});

	};

	$scope.close = function() {
		$modalInstance.close();
	}

});
