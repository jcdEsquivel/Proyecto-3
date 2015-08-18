var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers
		.controller(
				'donorRegistrationController',
				function($http, $scope, $upload, $state, AuthService,
						AUTH_EVENTS, $rootScope) {

					$scope.requestObject = {};
					$scope.requestObject.donor = {};
					$scope.requestObject.donor.name = "";
					$scope.requestObject.donor.lastName = "";
					$scope.requestObject.donor.profilePicture = "";
					$scope.requestObject.donor.description = "";
					$scope.requestObject.donor.webPage = "";
					$scope.requestObject.donor.country = "";
					$scope.requestObject.donor.type = "";
					$scope.requestObject.donor.facebookId = "";
					$scope.requestObject.donor.facebookToken = "";

					$scope.requestObject.donor.userGeneral = {};
					$scope.requestObject.donor.userGeneral.email = "";
					$scope.requestObject.donor.userGeneral.password = "";
					$scope.confirm_password = $scope.requestObject.donor.userGeneral.password;
					$scope.requestObject.donor.userGeneral.confirmPassword = "";
					$scope.image = "";
					var app_id = '319610508162843';
					$scope.facebookFail = false;

					$scope.init = function() {
						(function(d, s, id) {
							var js, fjs = d.getElementsByTagName(s)[0];
							if (d.getElementById(id)) {
								return;
							}
							js = d.createElement(s);
							js.id = id;
							js.src = "//connect.facebook.net/en_US/sdk.js";
							fjs.parentNode.insertBefore(js, fjs);
						}(document, 'script', 'facebook-jssdk'));

						window.fbAsyncInit = function() {

							FB.init({

								appId : app_id,
								status : true,
								cookie : true,
								xfbml : true,
								version : 'v2.1'

							});

							FB
									.getLoginStatus(function(response) {
										if (response.status === 'connected') {
											// console.log(response.authResponse.accessToken);
											$scope.requestObject.donor.facebookToken = response.authResponse.accessToken;
										}
										statusChangeCallback(response,
												function() {
												});
									});
						};
					}

					var statusChangeCallback = function(response, callback) {
						if (response.status === 'connected') {
							// getFacebookData();
						} else {
							callback(false);
						}
					}

					var checkLoginState = function(callback) {
						FB.getLoginStatus(function(response) {
							callback(response);
						});
					}

					var getFacebookData = function() {
						FB
								.api(
										'/me?fields=id,first_name,last_name,location,email',
										function(response) {
											console.log(JSON
													.stringify(response));

											// return an image as an
											// ArrayBuffer.
											var xhr = new XMLHttpRequest();

											// cross-domain issues.
											xhr
													.open(
															"GET",
															'http://graph.facebook.com/'
																	+ response.id
																	+ '/picture?type=large',
															true);

											// Ask for the result as an
											// ArrayBuffer.
											xhr.responseType = "arraybuffer";

											xhr.onload = function(e) {
												// Obtain a blob: URL for the
												// image data.
												var arrayBufferView = new Uint8Array(
														this.response);
												var blob = new Blob(
														[ arrayBufferView ], {
															type : "image/jpg"
														});
												var urlCreator = window.URL
														|| window.webkitURL;
												var imageUrl = urlCreator
														.createObjectURL(blob);
												// var img =
												// document.querySelector(
												// "#fileDisplayArea");

												$scope.image = blob;
												$scope.requestObject.donor.userGeneral.email = response.email
												$scope.requestObject.donor.name = response.first_name
												$scope.requestObject.donor.lastName = response.last_name
												$scope.requestObject.donor.country.id = 1;
												$scope.requestObject.donor.facebookId = response.id;
												$scope.create();

											};

											xhr.send();
										});
					}

					var facebookLogin = function() {
						checkLoginState(function(data) {
							if (data.status !== 'connected') {
								FB.login(function(response) {
									if (response.status === 'connected')
										getFacebookData();
								}, {
									scope : 'email,user_location, publish_actions'
								});
							} else if (data.status === 'connected') {
								getFacebookData();
							}
						})
					}

					var facebookLogout = function() {
						checkLoginState(function(data) {
							if (data.status === 'connected') {
								FB.logout(function(response) {
									$('#facebook-session').before(btn_login);
									$('#facebook-session').remove();
								})
							}
						})
					}

					$scope.init();

					$scope.countryName = "";

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

					$scope.create = function(event) {

						$scope.upload = $upload
								.upload(
										{
											url : 'rest/protected/donor/register',
											data : {
												email : $scope.requestObject.donor.userGeneral.email,
												password : $scope.requestObject.donor.userGeneral.password,
												name : $scope.requestObject.donor.name,
												lastName : $scope.requestObject.donor.lastName,
												country : $scope.requestObject.donor.country.id,
												fatherId: $scope.getFatherId(),
												facebookId : $scope.requestObject.donor.facebookId,
												facebookToken : $scope.requestObject.donor.facebookToken,
												fatherId : $scope.getFatherId()
											},
											file : $scope.image,
										})
								.success(
										function(response) {

											if (response.code == "200") {
												var credentials = {
													email : $scope.requestObject.donor.userGeneral.email,
													password : $scope.requestObject.donor.userGeneral.password
												};

												AuthService
														.login(credentials)
														.then(
																function(user) {

																	

																	if (user.code == "200") {
																		if (user.type == "nonprofit") {
																			$scope
																					.setCurrentUser(
																							user.idUser,
																							user.firstName,
																							user.img);

																		} else if (user.type == "donor") {
																			$scope
																					.setCurrentUser(
																							user.idUser,
																							user.firstName
																									+ " "
																									+ user.lastName,
																							user.img);
																			$rootScope
																					.$broadcast(AUTH_EVENTS.loginSuccess);
																			$state
																					.go(
																							'treeSeed.donor',
																							{
																								donorId : response.donorId
																							});
																		}
																	}
																}, function(status){
																	$scope.errorServer(status.status);
																});
											} else {
												if (response.code == 400) {
													$scope.facebookFail = true;
													$scope.requestObject.donor.name = "";
													$scope.requestObject.donor.lastName = "";
													$scope.requestObject.donor.userGeneral.email = "";
													
												}
												
												$scope.errorServer(response.code);
												
											}

										});

					};

					$scope.requestObject.lenguage = $scope.selectLang;
					$scope.requestObject.type = 'Country';

					$scope.getCountries = function() {
						return $http
								.post('rest/protected/catalog/getAllCatalog',
										$scope.requestObject)
								.success(
										function(response) {
											$scope.requestObject.donor.country = response.catalogs[0];
											$scope.selectSortOptions = response.catalogs;
											// $scope.selectSortOptions.splice(0,1);
										}).error(function(status){
											$scope.errorServer(status);
										});
					};
					$scope.getCountries();

					$scope.validateEmail = function() {
						var emailFormat = $scope.requestObject.donor.userGeneral.email;
						if (emailFormat == "") {
							document.getElementById("emailValidate").className = "md-default-theme md-input-invalid md-input-has-value";
						}
						var result = validateEmail(emailFormat);
						if (result == true) {
							document.getElementById("emailValidate").className = "md-default-theme md-input-has-value";
						} else {
							document.getElementById("emailValidate").className = "md-default-theme md-input-invalid md-input-has-value";
						}
					}

					function validateEmail(email) {
						var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
						return re.test(email);
					}

					$(document).on('click', '#login', function(e) {
						e.preventDefault();
						facebookLogin();
					})

					$(document).on('click', '#logout', function(e) {
						e.preventDefault();

						facebookLogout();

					})
				});

treeSeedAppControllers.controller('donorSearchController', function($scope,
		$http, $location, $modal, $log, $timeout) {

	$scope.donor = {};
	$scope.donor.country = "";
	$scope.donor.lastName = "";
	$scope.requestObject1 = {};

	$scope.init = function() {
		$scope.requestObject1.lenguage = $scope.selectLang;
		console.log($scope.selectLang);
		$scope.requestObject1.type = "country";
		$http.post('rest/protected/catalog/getAllCatalog',
				$scope.requestObject1).success(function(response) {
			$scope.selectSortOptionsCountry = response.data.catalogs;
		}).error(function(status){
			$scope.errorServer(status);
		});
	}

	$scope.init();

	$scope.itemPerPage = [ 10, 25, 50, 100 ];
	$scope.currentPage = 1;
	$scope.totalItems = 5;

	$scope.requestObject = {};
	$scope.requestObject.pageNumber = 1;
	$scope.requestObject.pageSize = 10;
	$scope.requestObject.direction = "DESC";
	$scope.requestObject.sortBy = [];
	$scope.requestObject.searchColumn = "ALL";
	$scope.requestObject.searchTerm = "";
	$scope.requestObject.name = $scope.name;
	$scope.requestObject.country = $scope.country;
	$scope.requestObject.lastName = $scope.lastName;

	$scope.searchDonor = function(page) {

		$scope.requestObject.pageNumber = page;
		$scope.requestObject.name = $scope.name;
		$scope.requestObject.country = $scope.donor.country.id;
		$scope.requestObject.lastName = $scope.lastName;

		console.log($scope.requestObject.country)
		$http.post('rest/protected/donor/advanceGet', $scope.requestObject)
				.success(function(mydata, status) {
					if(mydata.code==200){
						$scope.donors = mydata.donors;
						$scope.totalItems = mydata.totalElements;
					}else{
						$scope.errorServer(mydata.code);
					}
				}).error(function(status) {
					$scope.errorServer(status);
				});

		$scope.pageChangeHandler = function(num) {
			$scope.searchDonor(num);
		};
	}
});

treeSeedAppControllers.controller('getDonorProfileController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session) {


	//portfolio variables
	$scope.portfolioURL = null;
	$scope.d3 = [];
	$scope.porfolioRequest = {};
	$scope.porfolioRequest.donorId = $stateParams.donorId;
	//end portfolio variables
	

	///
	
	// Declaration of donor object
	$scope.donor = {};
	$scope.donor.id = $stateParams.donorId;
	$scope.requestObject = {};
	
	// init function, calls the java controller
	$scope.init = function() {
		$scope.requestObject.idUser = Session.id;
		$scope.requestObject.id = $scope.donor.id;

		$http
				.post('rest/protected/donor/getDonorProfile',
						$scope.requestObject).success(function(mydata, status) {
					
							if(mydata.code==200){
								$scope.donor = mydata.donor;
	
								if (mydata.owner == true) {
									$scope.isOwner = true;
								} else {
									$scope.isOwner = false;
								}
							}else{	
								$scope.errorServer(mydata.code);
								}
						}).error(function(status) {
							$scope.errorServer(status);
						});

	}
	$scope.init();

	// About Edit
	$scope.aboutEditClicked = function() {
		$scope.aboutInEdition = true;
		$scope.error = false;
		$scope.donorEdit ={
  				description: $scope.donor.description
  		}
	};

	$scope.aboutCancelEditing = function() {
		$scope.aboutInEdition = false;
	};

	$scope.aboutSaveEditing = function() {
		$scope.donor.description = $scope.donorEdit.description;
		$scope.editDonor();
		$scope.aboutInEdition = false;
	};

	// Name Edit
	$scope.nameEditClicked = function() {
		$scope.nameInEdition = true;
		$scope.error = false;
		$scope.donorEdit ={
  				name: $scope.donor.name,
  				lastName: $scope.donor.lastName
  		}
	};

	$scope.nameCancelEditing = function() {
		$scope.nameInEdition = false;
	};

	$scope.nameSaveEditing = function() {
		if($scope.donorEdit.name){
			$scope.donor.name = $scope.donorEdit.name;
		}
		
		if($scope.donorEdit.lastName){
			$scope.donor.lastName = $scope.donorEdit.lastName;
		}
		
		
		$scope.editDonor();
		$scope.nameInEdition = false;
	};

	// Email Edit
	$scope.emailEditClicked = function() {
		$scope.emailInEdition = true;
		$scope.error = false;
		$scope.donorEdit ={
  				email: $scope.donor.userGeneral.email
  		}
	};

	$scope.emailCancelEditing = function() {
		$scope.emailInEdition = false;
	};

	$scope.emailSaveEditing = function() {
		$scope.donor.userGeneral.email = $scope.donorEdit.email;
		$scope.editDonor();
		$scope.emailInEdition = false;
	};

	// Webpage Edit
	$scope.webPageEditClicked = function() {
		$scope.webPageInEdition = true;
		$scope.error = false;
		$scope.donorEdit ={
  				webPage: $scope.donor.webPage
  		}
	};

	$scope.webPageCancelEditing = function() {
		$scope.webPageInEdition = false;
	};

	$scope.webPageSaveEditing = function() {
		$scope.donor.webPage = $scope.donorEdit.webPage;
		$scope.editDonor();
		$scope.webPageInEdition = false;
	};
	// Finish controller for edit buttons

	// Start editing profile
	$scope.requestObjectEdit = {};
	$scope.requestObjectEdit.donor = {}
	$scope.requestObjectEdit.coverImage = null;
	$scope.requestObjectEdit.profileImage = null;

	$scope.editDonor = function() {

		$scope.requestObjectEdit.email = $scope.donor.userGeneral.email;
		$scope.requestObjectEdit.name = $scope.donor.name;
		$scope.requestObjectEdit.lastName = $scope.donor.lastName;
		$scope.requestObjectEdit.description = $scope.donor.description;
		$scope.requestObjectEdit.webPage = $scope.donor.webPage;
		$scope.requestObjectEdit.id = $scope.donor.id;
		$scope.requestObjectEdit.idUser = Session.id;
		$scope.requestObjectEdit.coverImage = null;
		$scope.requestObjectEdit.profilePicture = $scope.donor.profilePicture;

		$http(
				{
					method : 'POST',
					url : 'rest/protected/donor/editDonor',
					headers : {
						'Content-Type' : undefined
					},
					transformRequest : function(data) {
						var formData = new FormData();

						formData.append('data', new Blob([ angular
								.toJson(data.data) ], {
							type : "application/json"
						}));
						formData.append("fileCover", data.fileCover);
						formData.append("fileProfile", data.fileProfile);
						console.log("Obj: " + JSON.stringify(data.data));
						return formData;
					},
					data : {
						data : $scope.requestObjectEdit,
						fileCover : $scope.coverImageContainer,
						fileProfile : $scope.profileImageContrainer
					}

				}).success(function(data, status, headers, config) {
					if(data.code == 200){
						$scope.donor.profilePicture = data.donor.profilePicture;
						$scope.currentUser.userImage = data.donor.profilePicture;
					}
					else if (data.code == 400) {
						$scope.error = true;
						$scope.donor.userGeneral.email = data.donor.userGeneral.email;
					}else{
						$scope.errorServer(data.code);
					}
				
					
				
		}).error(function(status) {
			$scope.errorServer(status);
		});
	};

	$scope.$on('profilePicture', function(event, args) {

		$scope.profileImageContrainer = args

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

	var modalInstance = null;

	$scope.openModalImage = function(type) {

		if (type == 'cover') {
			$scope.imageCover = true;
			console.log("es cover")
			console.log($scope.imageCover)

		} else if (type == 'profile') {
			$scope.imageCover = false;
			console.log("es profile")
			console.log($scope.imageCover)
		}

		modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/drag_drop.html',
			// controller : 'getNonProfitProfileController',
			scope : $scope,
			resolve : {
				setCurrentUser : function() {
					return $scope.image;
				}
			}
		});
	};

		$scope.closeModal = function() {		
			modalInstance.close();
			$scope.editDonor(); 
		};
		
		$scope.closeModalWithoutEdit = function() {
			modalInstance.close();
		};
	//Finish editing profile
		
		$scope.noDonations = false;
		$scope.noDonationsMessage = false;
	//Portfolio functions
		$scope.getRecurrableData = function() {
			$http.post('rest/protected/recurrableInformation/getRecurrableInformation', 
				$scope.porfolioRequest).success(function(response) {

					if(response.code==200){
						$scope.d3 = response.results;
						setData();
						console.log(Object.keys(response.results).length);
						if(Object.keys(response.results).length > 0){
							$scope.noDonations = true;
						}else{
							$scope.noDonationsMessage = true;
						}
					}
					else if(response.code==400){
						console.log("ERROR");
					}
			}).error(function(status){
				$scope.errorServer(status);
			});
		};
		
		function setData(){
			for (var i = $scope.d3.length - 1; i >= 0; i--) {
				$scope.d3[i].data = $scope.d3[i].amount;
				if($scope.selectLang == 'English'){
					$scope.d3[i].label = $scope.d3[i].englishCause;
				}else{
					$scope.d3[i].label = $scope.d3[i].spanishCause;
				}
			};
			
			$scope.portfolioURL = 'layouts/components/donorPortfolio.html';

		};
	
		
		$scope.getRecurrableData();
		
		$scope.recurrableDonations = function(){
			$scope.portfolioURL = null;
			var modalInstance = $modal.open({
				animation : $scope.animationsEnabled,
				templateUrl :'layouts/components/editPortfolioModal.html',
				controller : 'editPortfolioDonations',
				backdrop: 'static',
				keyboard: false,
				size : 'md',//,
				resolve : {
					
					donorId: function(){
						return   $scope.porfolioRequest.donorId;
					},
					refreshPortfolio: function(){
						return $scope.getRecurrableData;
					},
					errorFunction: function(){
						return $scope.errorServer;
					}
					
				}
			
			});//end modal
		};//end function
		
});// Finish editing profile



treeSeedAppControllers.controller('donorSearchController', function($scope,
		$http, $location, $modal, $log, $timeout) {

	$scope.donor = {};
	$scope.donor.country = "";
	$scope.donor.lastName = "";
	$scope.requestObject1 = {};

	$scope.itemPerPage = [ 10, 25, 50, 100 ];
	$scope.currentPage = 1;
	$scope.totalItems = 5;

	$scope.requestObject = {};
	$scope.requestObject.pageNumber = 1;
	$scope.requestObject.pageSize = 10;
	$scope.requestObject.direction = "DESC";
	$scope.requestObject.sortBy = [];
	$scope.requestObject.searchColumn = "ALL";
	$scope.requestObject.searchTerm = "";
	$scope.requestObject.name = $scope.name;
	$scope.requestObject.country = $scope.country;
	$scope.requestObject.lastName = $scope.lastName;

	$scope.init = function() {
		$scope.requestObject1.lenguage = $scope.selectLang;
		console.log($scope.selectLang);
		$scope.requestObject1.type = "country";
		$http.post('rest/protected/catalog/getAllCatalog',
				$scope.requestObject1).then(function(response) {
			$scope.selectSortOptionsCountry = response.data.catalogs;
		});
	}

	$scope.init();

	$scope.searchDonor = function(page) {

		$scope.requestObject.pageNumber = page;
		$scope.requestObject.name = $scope.name;
		$scope.requestObject.country = $scope.donor.country.id;
		$scope.requestObject.lastName = $scope.lastName;

		$http.post('rest/protected/donor/advanceGet', $scope.requestObject)
				.success(function(mydata, status) {
					if(mydata.code==200){
						$scope.donors = mydata.donors;
						$scope.totalItems = mydata.totalElements;
					}else{
						$scope.errorServer(mydata.code)
					}
				}).error(function(status) {
					$scope.errorServer(status);
				});

		$scope.pageChangeHandler = function(num) {
			$scope.searchDonor(num);
		};
	}
});

treeSeedAppControllers.controller('donorSettingsController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session,
		$state, $rootScope, $sharedData, AUTH_EVENTS, AuthService) {

	$scope.donor = {};
	$scope.donor.id = Session.userId;
	$scope.requestObject = {};
	var modalInstance = null;

	$scope.deleteUser = function() {

		$scope.donor.id = Session.userId;
		$scope.requestObject.id = $scope.donor.id;

		$http.post('rest/protected/donor/deleteDonor', $scope.requestObject)
				.success(function(response) {
					if (response.code == "200") {
						AuthService.guestSession()
						$scope.currentUser = null;
						$state.go("treeSeed.index");
						$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
					} else if (response.code == "400") {
						$scope.errorServer(response.code)
					}else{
						$scope.errorServer(response.code)
					}
				}).error(function(status) {
					$scope.errorServer(status);
				});
	};

	$scope.openModalConfirmation = function() {

		modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'layouts/components/delete_confirmation.html',
			// controller : 'getNonProfitProfileController',
			scope : $scope,

		})
	};

	$scope.closeModal = function() {
		modalInstance.close();
		$scope.deleteUser();
	};

	$scope.closeModalWithoutEdit = function() {
		modalInstance.close();
	};

});

treeSeedAppControllers.controller('treeController', function($scope, $http,
		Session, $state, d3) {
	$scope.fooBar = d3.version;
	$scope.donorArrayList=[];
	$scope.showAmount = false;
	
	$scope.requestObject = {};
	$scope.requestObject.treeLevelY=2;//Solo trae dos generaciones de donadore por debajo del usuario
	$scope.requestObject.id=$scope.donor.id;
	$scope.requestObject.treeLevelX=3;//Solo trae tres hijos por donador
	$scope.showComplete=false;
	
	$scope.requestObject1 = {};
	$scope.requestObject1.treeLevelY=$scope.requestObject.treeLevelY;
	$scope.requestObject1.donorId=$scope.requestObject.id;
	$scope.requestObject1.treeLevelX=$scope.requestObject.treeLevelX;
	
	$http.post("rest/protected/donation/gettreedonation",$scope.requestObject1)
	.success(function(response){
		if(response.code==200){
			if(response.treeDonation!=0){
				$scope.showAmount = true;
				$scope.totalAmount = response.treeDonation;			
			}
		}else{
			$scope.errorServer(response.code);
			
		}
	}).error(function(status) {
		$scope.errorServer(status);
	});
	
	$scope.donorArray = function(tree){
		$scope.list = [];
		$scope.donorObject = {"id":"", "image":""};
		$scope.donorObject.id=tree.identity;
		$scope.donorObject.image=tree.profilePicture;
		$scope.list.push($scope.donorObject);
		if(tree.children != null){
			tree.children.forEach(function(son){
				$scope.list=$scope.list.concat($scope.donorArray(son));
			})
		}
		return $scope.list;
	}
	
	var margin = {
			top : 30,
			right : 30,
			bottom : 30,
			left : 180
		}, width = 450 - margin.right - margin.left, height = 700 - margin.top
				- margin.bottom;
		// Duración de animacion de carga de los links
		var i = 0, duration = 1500, root;

		var tree = d3.layout.tree().size([ height, width ]);

		var diagonal = d3.svg.diagonal().projection(function(d) {
			return [ d.y, d.x ];
		});

		var svg = d3.select("#treeDiv").append("svg").attr("width",
				width + margin.right + margin.left).attr("height",
				height + margin.top + margin.bottom).append("g").attr(
				"transform",
				"translate(" + margin.left + "," + margin.top + ")");

		// Carga
		d3.json("rest/protected/donor/getTree", function(error, flare) {
			if (error)
				throw error;
			// Animacion de carga
			$scope.donors = $scope.donorArray(flare.tree);
			if(flare.tree.children.length<3){
				if($scope.isOwner){
					$scope.showComplete=true;
				}
			}
			
			root = flare.tree;
			root.x0 = height / 2;
			root.y0 = 0;
			// Conlapsa todos los nodos al carga
			function collapse(d) {
				if (d.children) {
					d._children = d.children;
					d._children.forEach(collapse);
					d.children = null;
				}
			}

			// root.children.forEach(collapse);
			update(root);
		}).header("Content-Type","application/json").send("POST", JSON.stringify($scope.requestObject));

		d3.select(self.frameElement).style("height", "700px");

		function update(source) {

			// Compute the new tree layout.
			var nodes = tree.nodes(root).reverse(), links = tree.links(nodes);

			
			
			// Normalize for fixed-depth.
			// Largo de los links
			nodes.forEach(function(d) {
				d.y = d.depth * 100;
			});

			// Update the nodes…
			var node = svg.selectAll("g.node").data(nodes, function(d) {
				return d.id || (d.id = ++i);
			});
			
			

			// Enter any new nodes at the parent's previous position.
			var nodeEnter = node.enter().append("g").attr("class", "node")
					.attr(
							"transform",
							function(d) {
								return "translate(" + source.y0 + ","
										+ source.x0 + ")";
							}).on("click", click);
			
			
			// Estilo del circulo al inicio de la carga(Animacion)
			nodeEnter.append("circle").attr("r", 1e-6).style("fill",
					function(d) {
						return d._children ? "url(#" + d.identity
								+ ")" : "url(#" + d.identity
								+ ")";
					});
				
			
			nodeEnter.append("svg:image").attr("x", "-90")
			.attr("y",function(d){
				if(d.identity==$scope.donor.id){
        		return "-185";

	        	}else{
	        		return "0";
	        	}
        	})
            .attr("width",function(d){
				if(d.identity==$scope.donor.id){
	        		return "180";

		        	}else{
		        		return "0";
		        	}
	        })
            .attr("height",function(d){
				if(d.identity==$scope.donor.id){
	        		return "180";

		        	}else{
		        		return "0";
		        	}
	        })
            .attr("xlink:href",function(d){
            	if(d.identity==$scope.donor.id){
            		return "resources/images/treeGreen.png";
            	}else{
            		return undefined;
            	}
            })
				
			
			// Propiedades del texto
			nodeEnter.append("text").attr("x", function(d) {

				
				return d.children || d._children ? 0 : 0;// Posicion en X con
															// respento al borde
															// derecho
			}).attr("dy", "2.5em").attr("text-anchor", function(d) {
				return d.children || d._children ? "middle " : "middle";// Alineacion
			}).text(function(d) {
				return d.name;
			}).style("fill", "#000000").style("font-weight","bold")
			.style("font-size","15px");
			
			

			// Transition nodes to their new position.
			var nodeUpdate = node.transition().duration(duration).attr(
					"transform", function(d) {
						return "translate(" + d.y + "," + d.x + ")";
					});
			// Radio del circulo luego de la primera carga
			nodeUpdate.select("circle").attr("r", 20).style(
					"fill",
					function(d) {
						return d._children ? "url(#" + d.identity
								+ ")" : "url(#" + d.identity
								+ ")";
					});
			
			

			nodeUpdate.select("text").style("fill-opacity", 1);

			// Transition exiting nodes to the parent's new position.
			var nodeExit = node.exit().transition().duration(duration).attr(
					"transform", function(d) {
						return "translate(" + source.y + "," + source.x + ")";
					}).remove();

			nodeExit.select("circle").attr("r", 1e-6);

			nodeExit.select("text").style("fill-opacity", 1e-6);

			// Update the links…
			var link = svg.selectAll("path.link").data(links, function(d) {
				return d.target.id;
			});

			// Enter any new links at the parent's previous position.
			link.enter().insert("path", "g").attr("class", "link").attr("d",
					function(d) {
						var o = {
							x : source.x0,
							y : source.y0
						};
						return diagonal({
							source : o,
							target : o
						});
					});

			// Transition links to their new position.
			link.transition().duration(duration).attr("d", diagonal);

			// Transition exiting nodes to the parent's new position.
			link.exit().transition().duration(duration).attr("d", function(d) {
				var o = {
					x : source.x,
					y : source.y
				};
				return diagonal({
					source : o,
					target : o
				});
			}).remove();

			// Stash the old positions for transition.
			nodes.forEach(function(d) {
				d.x0 = d.x;
				d.y0 = d.y;
			});
		}

		// Toggle children on click.
		function click(d) {
			if (d.children) {
				d._children = d.children;
				d.children = null;
			} else {
				d.children = d._children;
				d._children = null;
			}
			update(d);
		}
	
});

treeSeedAppControllers.controller('donorDashboardController', function($scope,
		$http, $location, $modal, $state,$log, $timeout, $stateParams, Session, $upload, USER_ROLES) {
	
	$scope.requestObject={};
	$scope.myInterval = 5000;
	$scope.nonprofits;
	$scope.campaigns;
	$scope.showCampaign=true;
	$scope.showNonprofit=true;
	
	$scope.requestObject.idUser=$scope.currentUser.idUser;
	$scope.requestObject.id=Session.id;
	$http.post('rest/protected/donor/getdashboard',
			$scope.requestObject).success(function(mydata) {
				if(mydata.code==200){
					$scope.nonprofits = mydata.dashboardNonprofits;
					$scope.campaigns = mydata.dashboardCampaigns;
					
					if($scope.campaigns.length==0){
						$scope.showCampaign=false;
					}
					if($scope.nonprofits.length==0){
						$scope.showNonprofit=false;
					}
					
				}else{
					$scope.errorServer(status);
				}else if(mydata.code == 400){
					$scope.errorServer(mydata.code);
				}
		
	}).error(function(status) {
		$scope.errorServer(status);
	});
	
	$scope.goNonprofit = function(id){
		$state.go('treeSeed.nonProfit', {nonProfitId: id});
	}
	
	$scope.goCampaign = function(id){
		$state.go('treeSeed.campaign', {campaignId: id});
	}
	
	$scope.getCause = function(nonprofit){
		if($scope.selectLang=="English"){
			return nonprofit.causeNameEnglish;
		}else if($scope.selectLang=="Español"){
			return nonprofit.causeNameSpanish;
		}
	}
});


	/*
treeSeedAppControllers.controller('donorPortfolioController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session, $state, $rootScope, $sharedData, AUTH_EVENTS, AuthService) {
	console.log('recurrable');
	/*$scope.d3 = {
		series: {}
	};*/

	/*
	$scope.requestObject = {};
	$scope.requestObject.donorId = $stateParams.donorId;
*/
	/*$scope.getRecurrableData = function() {
		$http.post('rest/protected/recurrableInformation/getRecurrableInformation', 
			$scope.requestObject).success(function(response) {

				if(response.data.code=="200"){
					$scope.d3 = response.data.results;
					setData();
				}
				else if(response.data.code=="400"){
					$scope.errorServer(status);
					
					
				}
		}).error(function(status) {
			$scope.errorServer(status);
		});
	};

	$scope.getRecurrableData();

	function setData(){
		for (var i = $scope.d3.length - 1; i >= 0; i--) {
			$scope.d3[i].data = $scope.d3[i].amount;
			if($scope.selectLang == 'English'){
				$scope.d3[i].label = $scope.d3[i].englishCause;
			}else{
				$scope.d3[i].label = $scope.d3[i].spanishCause;
			}
		};
	};*/
	
	/*$scope.recurrableDonations = function(){
		
		var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl :'layouts/components/editPortfolioModal.html',
			controller : 'editPortfolioDonations',
			size : 'md',//,
			resolve : {
				
				donorId: function(){
					return   $scope.requestObject.donorId;
				},
				refreshPortfolio: function(){
					return $scope.getRecurrableData;
				}
			}
		
		});//end modal
	};//end function
	*/
/*});
*/
