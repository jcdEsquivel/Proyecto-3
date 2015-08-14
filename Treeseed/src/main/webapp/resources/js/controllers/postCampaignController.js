/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('postCampaignAdminController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $modal,
		$stateParams, Session) {

	$scope.posts = [];
	$scope.totalPosts = 0;
	$scope.postPaginCurrentPage = 0;

	$scope.postRequest = {
		postCampaign : {
			id : 0,
			title : '',
			picture : '',
			description : '',
			campaignId : $stateParams.campaignId
		},
		pageNumber : '',
		pageSize : '5',
		direction : "DESC",
		sortBy : ['creationDate']
	};

	$scope.getPosts = function(pageNumber) {

		$scope.postRequest.pageNumber = pageNumber;
		$scope.postPaginCurrentPage = pageNumber;
		
		$http.post('rest/protected/postCampaign/getPostFromCampaign',
				$scope.postRequest).success(function(data, status) {

			if (data.code == 200) {
				$scope.posts = data.posts;
				$scope.totalPosts = data.totalElements;
				console.log($scope.totalPosts);
			}else{
				console.log('Error : '+data.errorMessage);
			}
			
		}).error(function(mydata, status) {
			console.log(status);
			console.log("No data found");
		});
		
	};//end getPosts
	
	
	$scope.changePostsPage = function(pageNumber){
		$scope.getPosts(pageNumber);
	};
	
	//Gets execute when posts tab is clicked. is called from getNonProfitProfileController
	$scope.$on('loadPostsCampaign',function(){
		$scope.getPosts(1);
	});

	$scope.openModal = function() {

		var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/createPostModal.html',
			controller : 'createPostCampaignController',
			size : 'lg',
			resolve : {
				getPosts : function() {
					return $scope.getPosts;
				},
				campaignId: function(){
					return $scope.postRequest.postCampaign.campaignId
				}
			}

		})

	};
	
	
	$scope.openModalFilled = function(p) {
		var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/createPostModal.html',
			controller : 'editPostCampaignController',
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
		
		$scope.postRequest.postCampaign.campaignId= $stateParams.campaignId;
		
		$http.post('rest/protected/postCampaign/deletePostCampaign',
				$scope.postRequest).then(function(response) {
					if(response.data.code=="200"){
						$scope.postRequest.postCampaign.campaignId = $stateParams.campaignId;
						$scope.getPosts(1);						
					}
		});
	};
	
	$scope.openModalDeletePost = function(p) {

		$scope.postRequest.postCampaign.id = p.id;
		
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

treeSeedAppControllers.controller('createPostCampaignController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, getPosts,  campaignId ,Session,
		$modalInstance) {

	$scope.getPosts = getPosts;
	
	$scope.postRequestModal = {
			postCampaign : {
				id : '',
				title : '',
				picture : '',
				description : '',
				campaignId : campaignId
			},
			nonprofitId: Session.userId
		}
	
	$scope.post={
			title:'',
			description:''
	};

	
	$scope.maxCarac = 500;
	$scope.image;
	

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

		$scope.postRequestModal.postCampaign.title = $scope.post.title;
		$scope.postRequestModal.postCampaign.description = $scope.post.description;
		
		$http(
				{
					method : 'POST',
					url : 'rest/protected/postCampaign/register',
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
					if(data.code == 200){
						$scope.close();
					}
			
		});

	};

	$scope.close = function() {
		$scope.getPosts(1);
		$modalInstance.close();
		
	}

});


treeSeedAppControllers.controller('editPostCampaignController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, getPosts, post, Session,
		$modalInstance, $stateParams) {

	$scope.getPosts = getPosts;
	$scope.post = {
			nonprofitId : Session.userId,
			title : post.title,
			description : post.description,
			picture : post.picture,
			
		};
	
	$scope.postRequestModal = {
			postCampaign : {
				id : post.id,
				title : post.title,
				picture : post.picture,
				description : post.description,
				nonprofitId : Session.id,
				campaignId : $stateParams.campaignId
			}
			
			
		}
	
	
	$scope.maxCarac = 500;
	$scope.image =  post.picture;

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
		
		$scope.postRequestModal.postCampaign.title = $scope.post.title;
		$scope.postRequestModal.postCampaign.description =  $scope.post.description;
			
		$http(
				{
					method : 'POST',
					url : 'rest/protected/postCampaign/editPostCampaign',
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
			$scope.close();
		});

	};

	$scope.close = function() {
		$scope.getPosts(1);
		$modalInstance.close();
		
	}

});
