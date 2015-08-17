/**
 * 
 */
var treeSeedAppControllers = angular.module('treeSeed.controller');

treeSeedAppControllers.controller('campaignSearchController', function($scope,
		$http, $location, $modal, $log, $timeout) {

	$scope.campaign = {};
	$scope.campaign.id = "";
	$scope.campaign.name = "";
	$scope.campaign.rangeDate = "";
	$scope.campaign.nonProfit = {};
	$scope.campaign.nonProfit.name = "";
	$scope.campaign.nonProfit.cause = "";
	$scope.campaign.cause = {
		id : '',
		name : ''
	};
	$scope.requestObject1 = {};
	$scope.requestObject2 = {};
	$scope.itemPerPage = [ 10, 25, 50, 100 ];
	$scope.currentPage = 1;
	$scope.totalItems = 5;

	$scope.requestObject = {};
	$scope.requestObject.name = '';
	$scope.requestObject.nonprofitName = '';
	$scope.requestObject.causeId = '';
	$scope.requestObject.startDate = "";
	$scope.requestObject.dueDate = "";

	$scope.requestObject.pageNumber = 1;
	$scope.requestObject.pageSize = 10;
	$scope.requestObject.direction = "DESC";
	$scope.requestObject.sortBy = [];
	$scope.requestObject.searchColumn = "ALL";
	$scope.requestObject.searchTerm = "";

	$scope.init = function() {

		$scope.requestObject1.lenguage = $scope.selectLang;
		$scope.requestObject1.type = "cause";
		
		$http.post('rest/protected/catalog/getAllCatalog',
				$scope.requestObject1).success(function(response) {
			$scope.selectSortOptionsCause = response.catalogs;
		}).error(function(status){
			$scope.errorServer(status);
		});
	}

	$scope.init();

	$scope.getDateFormat = function(date) {
		if (date === undefined || date === "") {
			return "";
		}
		var parts = date.split('-');
		return new Date(parts[0], parts[1] - 1, parts[2]);
	}

	$scope.searchCampaign = function(page) {

		var dates = $scope.campaign.rangeDate.split(' - ');

		console.log(dates);

		var startDate = $scope.getDateFormat(dates[0]);
		var endDate = $scope.getDateFormat(dates[1]);

		console.log("sd " + startDate);
		console.log("ed " + endDate);

		if (startDate != "") {
			$scope.requestObject.startDate = startDate.getTime();
		}

		if (endDate != "") {
			$scope.requestObject.dueDate = endDate.getTime();
		}

		$scope.requestObject.pageNumber = page;
		$scope.requestObject.name = $scope.campaign.name;
		$scope.requestObject.nonprofitName = $scope.campaign.nonProfit.name;
		$scope.requestObject.causeId = $scope.campaign.cause.id;
		// console.log(JSON.stringify($scope.requestObject));

		$http.post('rest/protected/campaing/advanceGet', $scope.requestObject)
				.success(function(mydata, status) {
					if(mydata.code==200){
						$scope.campaigns = mydata.campaigns;
						$scope.totalItems = mydata.totalElements;
					}
					else{
						$scope.errorServer(mydata.code);
					}

				}).error(function(status) {
					$scope.errorServer(status);
				});

	};

	$scope.pageChangeHandler = function(num) {
		$scope.searchCampaign(num);
	};

});

treeSeedAppControllers.controller('campaingCreateController',
		function($http, $scope, $upload, $state, AuthService, AUTH_EVENTS,
				$rootScope, Session) {
			$scope.percent = 0;
			$scope.maxCarac = 1000;
			$scope.stateInput1 = false;
			$scope.stateInput2 = false;
			$scope.stateInput3 = false;
			$scope.stateInput4 = false;
			$scope.stateInput5 = false;
			$scope.stateInput6 = false;
			$scope.minDate1 = new Date();
			$scope.minDate2 = new Date();
			$scope.noEndCampaign = false;

			$scope.campaign = {};
			$scope.uploadImage = false;
			$scope.campaign.name = "";
			$scope.campaign.description = "";
			$scope.campaign.startDate;
			$scope.campaign.dueDate;
			$scope.campaign.amountGoal = "";
			$scope.image = "";

			$scope.minDate = function() {
				$scope.mindate2 = $scope.mindate1;
			}

			$scope.create = function(event) {
				if(!$scope.noEndCampaign){
					$scope.upload = $upload.upload({
						url : 'rest/protected/campaing/create',
						data : {
							name : $scope.campaign.name,
							description : $scope.campaign.description,
							date1 : $scope.campaign.startDate,
							date2 : $scope.campaign.dueDate,
							amount : $scope.campaign.amountGoal,
							idNonprofit : $scope.currentUser.idUser
						},
						file : $scope.image,
					}).success(function(response) {
						if(response.code==200){
							$state.go('treeSeed.campaign', {
								campaignId : response.campaignId
							});
						}else{
							$scope.errorServer(response.code);
						}	
						}).error(function(status) {
							$scope.errorServer(status);
						});

				}else{
					$scope.upload = $upload.upload({
						url : 'rest/protected/campaing/create',
						data : {
							name : $scope.campaign.name,
							description : $scope.campaign.description,
							date1 : $scope.campaign.startDate,
							date2 : "!#",// "!#" Code to define no end Campaign
							idNonprofit : $scope.currentUser.idUser
						},
						file : $scope.image,
					}).success(function(response) {
						if(response.code==200){
							$state.go('treeSeed.campaign', {
								campaignId : response.campaignId
							});
						}else{
							$scope.errorServer(response.code);
						}

					}).error(function(status) {
						$scope.errorServer(status);
					});
				}
				$scope.uploadImage = false;
				this.onError = false;

				

			};

			$scope.$on('profilePicture', function(event, args) {
				$scope.image = args;
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

					$scope.uploadImage = true;

				} else {
					$scope.uploadImage = false;
					alert("File not supported!");
				}
				$scope.progressControl(5, $scope.uploadImage);
			});

			$scope.progressControl = function(input, valid) {

				switch (input) {
				case 0:
					if (!valid) {
						if($scope.stateInput4){
							$scope.progressControl(4,false);
						}
						if($scope.stateInput6){
							$scope.progressControl(6,false);
						}
						$scope.campaign.dueDate= null;
						$scope.campaign.amountGoal = "";

					} else {
						
						
						if(!$scope.stateInput4){
							$scope.progressControl(4,true);
						}
						if(!$scope.stateInput6){
							$scope.progressControl(6,true);
						}						
						$scope.campaign.dueDate;
						$scope.campaign.amountGoal = 0;

					}
					break;
				case 1:
					if (valid) {
						if (!$scope.stateInput1) {
							$scope.percent = $scope.percent + 20;
							$scope.stateInput1 = true;
						}

					} else {
						if ($scope.stateInput1) {
							$scope.percent = $scope.percent - 20;
							$scope.stateInput1 = false;
						}
					}
					break;
				case 2:
					if (valid) {
						if (!$scope.stateInput2) {
							$scope.percent = $scope.percent + 20;
							$scope.stateInput2 = true;
						}

					} else {
						if ($scope.stateInput2) {
							$scope.percent = $scope.percent - 20;
							$scope.stateInput2 = false;
						}
					}
					break;
				case 3:
					if (valid) {
						if (!$scope.stateInput3) {
							$scope.percent = $scope.percent + 10;
							$scope.stateInput3 = true;
						}

					} else {
						if ($scope.stateInput3) {
							$scope.percent = $scope.percent - 10;
							$scope.stateInput3 = false;
						}
					}

					break;
				case 4:
					if (valid) {
						if (!$scope.stateInput4) {
							$scope.percent = $scope.percent + 20;
							$scope.stateInput4 = true;
						}

					} else {
						if ($scope.stateInput4) {
							$scope.percent = $scope.percent - 20;
							$scope.stateInput4 = false;
						}
					}
					break;
				case 5:
					if (valid) {
						if (!$scope.stateInput5) {
							$scope.percent = $scope.percent + 20;
							$scope.stateInput5 = true;
						}

					} else {
						if ($scope.stateInput5) {
							$scope.percent = $scope.percent - 20;
							$scope.stateInput5 = false;
						}
					}
					break;
				case 6:
					if (valid) {
						if (!$scope.stateInput6) {
							$scope.percent = $scope.percent + 10;
							$scope.stateInput6 = true;
						}

					} else {
						if ($scope.stateInput6) {
							$scope.percent = $scope.percent - 10;
							$scope.stateInput6 = false;
						}
					}

					break;
				}
			}
		});

treeSeedAppControllers.controller('nonprofitCampaignSearchController',

function($scope, $http, $location, $modal, $log, $timeout, $stateParams) {

	$scope.page = 1;

	$scope.currentCampaignPage = 0;

	$scope.itemPerPage = [ 10, 25, 50, 100 ];
	$scope.currentPage = 1;
	$scope.totalItems = 0;
	$scope.borderColor = "green";
	$scope.requestObjectCampaigns = {};
	$scope.requestObjectCampaigns.isActive = true;
	$scope.requestObjectCampaigns.pageNumber = 1;
	$scope.requestObjectCampaigns.pageSize = 3;
	$scope.requestObjectCampaigns.direction = "DES";
	$scope.requestObjectCampaigns.sortBy = [ "StartDate" ];
	$scope.requestObjectCampaigns.searchColumn = "ALL";
	$scope.requestObjectCampaigns.searchTerm = "";
	$scope.requestObjectCampaigns.nonprofitId = $stateParams.nonProfitId;

	$scope.$on('loadCampaigns', function() {
		$scope.searchCampaigns(1);
	});

	$scope.searchCampaigns = function(page) {

		$scope.currentCampaignPage = page;
		$scope.requestObjectCampaigns.pageNumber = page;

		$http.post('rest/protected/campaing/nonprofitCampaigns',
			$scope.requestObjectCampaigns).success(
				function(mydata, status) {
					
					if(mydata.code==200){
						$scope.campaigns = mydata.campaigns;
						$scope.totalItems = mydata.totalElements;
					}else{
						$scope.errorServer(mydata.code);
					}
				}).error(function(status) {
					$scope.errorServer(status);
				});

	};

	$scope.pageChangeHandler = function(num) {
		$scope.searchCampaigns(num);
	};

	$scope.getClass = function(item) {
		if (item.state == 'soon') {
			return 'border-commingSoon';
		} else if (item.state == 'active') {
			return 'border-active';
		} else {
			return 'border-finished';
		}
	};

	$scope.getColor = function(start, end) {
		$scope.color = "";
		if (start) {
			$scope.color = "#EBEB0A";
		} else if (!end) {
			$scope.color = "red";
		} else if (!start && end) {
			$scope.color = "#27c24c";
		}
	};

});

treeSeedAppControllers.controller('searchCampaignFromNonProfitController',
		function($scope, $http, $location, $modal, $log, $timeout, Session,
				$translate) {

			$scope.datesDisable = false;
			$scope.stateDisable = false;
			$scope.state = '';
			$scope.range = '';
			$scope.requestObject2 = {};
			$scope.requestCatalog = {
				lenguage : $scope.selectLang,
				type : "cause"
			};

			$scope.itemPerPage = [ 10, 25, 50, 100 ];
			$scope.sortList = [ "startDate" ];
			$scope.currentPage = 1;
			$scope.totalItems = 5;

			$scope.requestObject = {};
			$scope.requestObject.name = '';
			$scope.requestObject.nonprofitName = '';
			$scope.requestObject.causeId = '';
			$scope.requestObject.startDate = "";
			$scope.requestObject.dueDate = "";
			$scope.requestObject.nonprofitId = Session.userId;
			$scope.requestObject.state = '';

			// $scope.range = '';
			$scope.requestObject.pageNumber = 1;
			$scope.requestObject.pageSize = 10;
			$scope.requestObject.direction = "DESC";
			$scope.requestObject.sortBy = [ "startDate" ];
			$scope.requestObject.searchColumn = "ALL";
			$scope.requestObject.searchTerm = "";

			$scope.getDateFormat = function(date) {
				if (date === undefined || date === "") {
					return "";
				}
				var parts = date.split('-');
				return new Date(parts[0], parts[1] - 1, parts[2]);
			};

			$scope.usingDateRangeState = function(val) {
				if ($scope.range != '') {// range dates are been used
					$scope.datesDisable = false;
					$scope.stateDisable = true;
					$scope.state = '';
				} else if ($scope.state) {// state been used
					$scope.datesDisable = true;
					$scope.stateDisable = false;
					$scope.range = '';

				} else {
					$scope.datesDisable = false;
					$scope.stateDisable = false;
					$scope.state = '';
					$scope.range = '';

				}
			};

			$scope.searchCampaign = function(page) {

				var dates = $scope.range.split(' - ');

				var startDate = $scope.getDateFormat(dates[0]);
				var endDate = $scope.getDateFormat(dates[1]);

				if (startDate != "") {
					$scope.requestObject.startDate = startDate.getTime();
				}

				if (endDate != "") {
					$scope.requestObject.dueDate = endDate.getTime();
				}

				$scope.requestObject.pageNumber = page;

				$scope.requestObject.state = $scope.state.Id;

				$http.post(
						'rest/protected/campaing/searchCampaignsForNonprofit',

						$scope.requestObject).success(function(mydata, status) {
							if(mydata.code==200){
								$scope.campaigns = mydata.campaigns;
								$scope.totalItems = mydata.totalElements;
							}else{
								$scope.errorServer(mydata.code);
							}

				}).error(function(status) {
					$scope.errorServer(status);
				});

			};

			$scope.pageChangeHandler = function(num) {
				$scope.searchCampaign(num);
			};

			$scope.stateList = [];

			$translate('CAMPAIGN-STATE.SOON').then(
					function successFn(translation) {
						$scope.stateList.push({
							Id : 'soon',
							Name : translation
						});
					});

			$translate('CAMPAIGN-STATE.ACTIVE').then(
					function successFn(translation) {
						$scope.stateList.push({
							Id : 'active',
							Name : translation
						});
					});

			$translate('CAMPAIGN-STATE.FINISHED').then(
					function successFn(translation) {
						$scope.stateList.push({
							Id : 'finished',
							Name : translation
						});
					});

			$scope.getClass = function(item) {
				if (item.state == 'soon') {
					return 'border-commingSoon';
				} else if (item.state == 'active') {
					return 'border-active';
				} else {
					return 'border-finished';
				}
			};

});


treeSeedAppControllers.controller('getCampaingProfileController', function($scope,
		$http, $location, $modal, $log, $timeout, $stateParams, Session, $upload, $state , MODULE_CONFIG,lazyService, USER_ROLES) {

					$scope.showDonationButton = true;
					$scope.postsLoaded = false;
					$scope.campaign = {};
					$scope.campaign.id = $stateParams.campaignId;
					$scope.requestObject = {};
					$scope.requestObject.campaign = {};
					$scope.requestClose = {};
					$scope.requestClose.campaign = {};
					$scope.isOwner = false;
					$scope.isOpen = true;
					var modalInstance = null;
					$scope.postsLoaded = false;
					$scope.minDate2 = new Date();
					$scope.nonprofitId = 0;

					$scope.minDate = function() {
						$scope.mindate2 = $scope.mindate1;
					}
					
					$scope.init = function() {
						//show donate button
						if(Session.userRole == USER_ROLES.nonprofit){
							$scope.showDonationButton = false;
						}
						
						$scope.requestObject.idUser = Session.userId;
						$scope.requestObject.campaign.id = $scope.campaign.id;

						$http
								.post(
										'rest/protected/campaing/getCampignProfile',
										$scope.requestObject)
								.success(
										function(mydata, status) {
											if(mydata.code==200){
												$scope.campaign = mydata.campaign;
												$scope.nonprofitId = mydata.campaign.nonprofit.id;
												
												if ($scope.campaign == null) {
													$state.go("treeSeed.index");
												}
												if (mydata.owner == true) {
													$scope.isOwner = true;
													if (mydata.campaign.state != "finished") {
														$scope.editable = true;
													}
												} else {
													$scope.isOwner = false;
												}
												if (mydata.campaign.state == "finished") {
													$scope.isOpen = false;
												}
											}else{
												$scope.errorServer(mydata.code);
											}
										}).error(function(status) {
											$scope.errorServer(status);
										});

					};

					$scope.init();

					$scope.getClass = function(item) {
						if (item.state == 'soon') {
							return 'border-commingSoon';
						} else if (item.state == 'active') {
							return 'border-active';
						} else {
							return 'border-finished';
						}
					}

					$scope.closeCampaign = function() {

						modalInstance = $modal
								.open({
									animation : $scope.animationsEnabled,
									templateUrl : 'layouts/components/closeCampaign_confirmation.html',
									scope : $scope
								})
					};

					$scope.accept = function() {
						$scope.requestClose.idUser = Session.id;
						$scope.requestClose.campaign.id = $scope.campaign.id;
						$http.post('rest/protected/campaing/close',
								$scope.requestClose).success(
								function(response) {
									if(response.code==200){
										$scope.closeModal();
										$state.go($state.current, {}, {
											reload : true
										});
									}else{
										$scope.errorServer(response.code);	
									}
								}).error(function(status) {
									$scope.errorServer(status);
								});
					}

					$scope.closeModal = function() {
						modalInstance.close();
					};

					$scope.loadPosts = function() {
						if (!$scope.postsLoaded) {
							$scope.$broadcast('loadPostsCampaign');
							$scope.postsLoaded = true;
						}
					};

					/***********************************************************
					 * EDIT CAMPAIGN
					 **********************************************************/
					$scope.editCampaign = function() {

						$scope.requestObjectEdit = {}

						$scope.requestObjectEdit.id = $scope.campaign.id;
						$scope.requestObjectEdit.name = $scope.campaign.name;
						$scope.requestObjectEdit.description = $scope.campaign.description;
						$scope.requestObjectEdit.amountGoal = $scope.campaign.amountGoal;
						$scope.requestObjectEdit.amountCollected = $scope.campaign.amountCollected;
						$scope.requestObjectEdit.startDateData = new Date(
								$scope.campaign.startDate);
						$scope.requestObjectEdit.dueDateData = new Date(
								$scope.campaign.dueDate);
						$scope.requestObjectEdit.picture = $scope.campaign.picture;

						$http(
								{
									method : 'POST',
									url : 'rest/protected/campaing/editCampaign',
									headers : {
										'Content-Type' : undefined
									},
									transformRequest : function(data) {
										var formData = new FormData();

										formData.append('data', new Blob(
												[ angular.toJson(data.data) ],
												{
													type : "application/json"
												}));
										formData.append("fileCampaign",
												data.fileCampaign);
										return formData;
									},
									data : {
										data : $scope.requestObjectEdit,
										fileCover : $scope.coverImageContainer,
										fileCampaign : $scope.profileImageContrainer
									}

								})
								.success(
										function(data, status, headers, config) {
											if(data.code==200){
												$scope.campaign = data.campaign;
												$scope.campaign.picture = data.campaign.picture;
											}else{
												$scope.errorServer(data.code);
											}
										}).error(function(status) {
											$scope.errorServer(status);
										});
					};

					// Picture modal
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

					$scope.openModalImage = function(type) {

						if (type == 'cover') {
							$scope.fileCampaign = true;
							console.log("es cover")
							console.log($scope.fileCampaign)

						}

						modalInstance = $modal.open({
							animation : $scope.animationsEnabled,
							templateUrl : 'layouts/components/drag_drop.html',
							scope : $scope,
							resolve : {
								setCurrentUser : function() {
									return $scope.image;
								}
							}
						})
					};

					$scope.closeModal = function() {
						modalInstance.close();
						$scope.editCampaign();
					};

					$scope.closeModalWithoutEdit = function() {
						modalInstance.close();
					};

					// About Edit
					$scope.aboutEditClicked = function() {
						$scope.aboutInEdition = true;
						$scope.error = false;
						$scope.campaignEdit ={
				  				description: $scope.campaign.description
				  		}
					};

					$scope.aboutCancelEditing = function() {
						$scope.aboutInEdition = false;
					};

					$scope.aboutSaveEditing = function() {
						$scope.campaign.description = $scope.campaignEdit.description;
						$scope.editCampaign();
						$scope.aboutInEdition = false;
					};

					// Name Edit
					$scope.nameEditClicked = function() {
						$scope.nameInEdition = true;
						$scope.error = false;
						$scope.campaignEdit ={
				  				name: $scope.campaign.name
				  		}
					};

					$scope.nameCancelEditing = function() {
						$scope.nameInEdition = false;
					};

					$scope.nameSaveEditing = function() {
						$scope.campaign.name = $scope.campaignEdit.name;
						$scope.editCampaign();
						$scope.nameInEdition = false;
					};

					// Date Edit
					$scope.dateEditClicked = function(type) {
						if (type.state == 'active') {
							$scope.disabled = true;
						} else {
							$scope.disabled = false;
						}
						modalInstance = $modal
								.open({
									animation : $scope.animationsEnabled,
									templateUrl : 'layouts/components/DateEdition.html',
									scope : $scope
								})
					};

					$scope.closeDateModalWithoutEdit = function() {
						modalInstance.close();
					}

					$scope.closeDateModal = function(startDate, dueDate) {
						$scope.campaign.startDate = new Date(startDate);
						$scope.campaign.dueDate = new Date(dueDate);
						modalInstance.close();
						$scope.editCampaign();
					}

					//Amount edit clicked
					$scope.amountGoalClicked = function() {
						$scope.amountGoalInEdition = true;
						$scope.error = false;
						$scope.campaignEdit ={
				  				amountGoal: $scope.campaign.amountGoal
				  		}
					};

					$scope.amountGoalSaveEditing = function(){
						$scope.campaign.amountGoal = $scope.campaignEdit.amountGoal
						$scope.editCampaign();
						$scope.amountGoalInEdition = false;
					};

					$scope.amountGoalCancelEditing = function() {
						$scope.amountGoalInEdition = false;
					};
	
	
	/*************************
	 * DONATION CALL
	 *************************/
	
	$scope.showDonation = function() {
		var modalUrl = '';
	    var controller = '';
		if(Session.userRole == USER_ROLES.guest){
			modalUrl = 'layouts/components/guestDonationModal.html';
			controller = 'guestDonationController';
		}else{
			modalUrl = 'layouts/components/donationModal.html';
			controller = 'donorDonationController'
		}
		
		
		
		var modalInstance = $modal.open({
			animation : $scope.animationsEnabled,
			templateUrl : modalUrl,
			controller : controller,
			size : 'md',//,
			resolve : {
				setCurrentUser : function() {
					return $scope.setCurrentUser;
				},
				nonprofitId: function(){
					return $scope.nonprofitId;
				},
				titleFace: function(){
					return $scope.campaign.name;
				},
				descriptionFace: function(){
					return $scope.campaign.description;
				},
				pictureFace: function(){
					return $scope.campaign.picture;
				}
			} 
			// resolve : lazyService.load(['https://js.stripe.com/v2/'])
		});
		
	};//end showDonation
});
