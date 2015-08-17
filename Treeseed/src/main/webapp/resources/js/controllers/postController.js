/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('postAdminController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $rootScope, $modal,
		$stateParams, Session) {

	$scope.posts = [];
	$scope.totalPosts = 0;
	$scope.postPaginCurrentPage = 0;

	$scope.postRequest = {
		postNonprofit : {
			id : 0,
			title : '',
			picture : '',
			description : '',
			nonprofitId : $stateParams.nonProfitId
		},
		pageNumber : '',
		pageSize : '5',
		direction : "DESC",
		sortBy : ['creationDate']
	};

	$scope.getPosts = function(pageNumber) {

		$scope.postRequest.pageNumber = pageNumber;
		$scope.postPaginCurrentPage = pageNumber;
		
		
		$http.post('rest/protected/postNonprofit/getNonprofitPost',
				$scope.postRequest).success(function(data, status) {

			if (data.code == 200) {
				$scope.posts = data.posts;
				$scope.totalPosts = data.totalElements;
				console.log($scope.totalPosts);
			}else{
				$scope.errorServer(data.code);
				
			}
			
		}).error(function(status) {
			$scope.errorServer(status);
		});
		
	};//end getPosts
	
	
	$scope.changePostsPage = function(pageNumber){
		$scope.getPosts(pageNumber);
	};
	
	//Gets execute when posts tab is clicked. is called from getNonProfitProfileController
	$scope.$on('loadPosts',function(){
		$scope.getPosts(1);
	});

	$scope.openModal = function() {

		var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/createPostModal.html',
			controller : 'createPostController',
			size : 'lg',
			resolve : {
				getPosts : function() {
					return $scope.getPosts;
				},
				nonprofitId: function(){
					return $scope.postRequest.postNonprofit.nonprofitId
				}
			}

		})

	};
	
	$scope.openModalFilled = function(p) {
			var modalInstance = $modal.open({
				animation : $scope.animationsEnabled,
				templateUrl : 'layouts/components/createPostModal.html',
				controller : 'editPostController',
				size : 'lg',
				resolve : {
					getPosts : function() {
						return $scope.getPosts;
					},
					post: function(){
						return p
					}
				}
			})
	};
	
	$scope.deletePost = function()
	{
		$scope.postRequest.postNonprofit.nonprofitId= Session.id;
		$http.post('rest/protected/postNonprofit/deletePostNonProfit',
				$scope.postRequest).success(function(response) {
					if(response.code==200){
						$scope.postRequest.postNonprofit.nonprofitId= $stateParams.nonProfitId;
						$scope.getPosts(1);						
					}else{
						$scope.errorServer(data.code);
						
					}
		}).error(function(status) {
			$scope.errorServer(status);
		});;
	};
	
	$scope.openModalDeletePost = function(p) {

		$scope.postRequest.postNonprofit.id = p.id;
		modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/delete_confirmation_post.html',
			scope: $scope,
			
			
	    })
	};
	
	$scope.closeModal = function() {		
		modalInstance.close();
		$scope.deletePost(); 
	};
	
	$scope.closeModalWithoutEdit = function() {	
		modalInstance.close();
	};

});

treeSeedAppControllers.controller('createPostController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, getPosts, nonprofitId, Session,
		$modalInstance) {

	$scope.getPosts = getPosts;
	
	$scope.postRequestModal = {
			postNonprofit : {
				id : '',
				title : '',
				picture : '',
				description : '',
				nonprofitId : nonprofitId
			}
		}
	
	
	$scope.maxCarac = 500;
	$scope.image;
	$scope.post = {
		nonprofitId : Session.userId,
		title : '',
		description : ''
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

		$scope.postRequestModal.postNonprofit.title = $scope.post.title;
		$scope.postRequestModal.postNonprofit.description = $scope.post.description;
		
		$http(
				{
					method : 'POST',
					url : 'rest/protected/postNonprofit/register',
					headers : {
						'Content-Type' : undefined
					},
					transformRequest : function(data) {
						var formData = new FormData();

						formData.append('data', new Blob([ angular
								.toJson(data.data) ], {
							type : "application/json"
						}));
						formData.append("file", data.file);
						return formData;
					},
					data : {
						data : $scope.postRequestModal,
						file : $scope.image
					}

				}).success(function(data, status, headers, config) {
					if(data.code==200){
						$scope.close();
					}else{
						$scope.errorServer(data.code);
						
					}
		}).error(function(status) {
			$scope.errorServer(status);
		});

	};

	$scope.close = function() {
		$scope.getPosts(1);
		$modalInstance.close();
		
	}

});


treeSeedAppControllers.controller('editPostController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, getPosts, post, Session,
		$modalInstance) {

	$scope.getPosts = getPosts;
	
	$scope.post = {
			nonprofitId : Session.userId,
			title : post.title,
			description : post.description,
			picture : post.picture
		};
	
	$scope.postRequestModal = {
			postNonprofit : {
				id : post.id,
				title : post.title,
				picture : post.picture,
				description : post.description,
				nonprofitId : Session.id
			}
		}
	
	
	
	$scope.maxCarac = 500;
	$scope.image =  post.picture;
	/*$scope.post = {
		nonprofitId : Session.id,
		title : post.title,
		descripcion :  post.description
	};*/

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
		
		$scope.postRequestModal.postNonprofit.title = $scope.post.title;
		$scope.postRequestModal.postNonprofit.description =  $scope.post.description;
			
		$http(
				{
					method : 'POST',
					url : 'rest/protected/postNonprofit/editPostNonProfit',
					headers : {
						'Content-Type' : undefined
					},
					transformRequest : function(data) {
						var formData = new FormData();

						formData.append('data', new Blob([ angular
								.toJson(data.data) ], {
							type : "application/json"
						}));
						formData.append("file", data.file);
						return formData;
					},
					data : {
						data : $scope.postRequestModal,
						file : $scope.image
					}

				}).success(function(data, status, headers, config) {
					if(data.code==200){
						$scope.close();
					}else{
						$scope.errorServer(data.code);
						
					}
		}).error(function(status) {
			$scope.errorServer(status);
		});

	};

	$scope.close = function() {
		$scope.getPosts(1);
		$modalInstance.close();
		
	}

});
