/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('postAdminController', function($http,
		$scope, $upload, $state, AuthService, AUTH_EVENTS, $rootScope, $modal,
		$stateParams) {

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
		pageSize : '4',
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
			$scope.close();
		});

	};

	$scope.close = function() {
		$scope.getPosts(1);
		$modalInstance.close();
		
	}

});
