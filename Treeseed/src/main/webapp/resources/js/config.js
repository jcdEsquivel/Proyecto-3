angular
		.module('treeSeed')
		.run(
				[ '$rootScope', '$state', '$stateParams',
						function($rootScope, $state, $stateParams) {
							$rootScope.$state = $state;
							$rootScope.$stateParams = $stateParams;

						} ])
		.run(function($rootScope, AUTH_EVENTS, AuthService) {
			$rootScope.$on('$stateChangeStart', function(event, next) {
				var authorizedRoles = next.data.authorizedRoles;
				if (!AuthService.isAuthorized(authorizedRoles)) {
					event.preventDefault();
					if (AuthService.isAuthenticated()) {
						// user is not allowed
						$rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
					} else {
						// user is not logged in
						$rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
					}
				}
			});
		})

		.config(function($httpProvider) {
			$httpProvider.interceptors.push(function($injector) {
				return $injector.get('AuthInterceptor');
			});
		})
		.config(
				function($stateProvider, $urlRouterProvider, JQ_CONFIG,
						MODULE_CONFIG, USER_ROLES) {

					$urlRouterProvider.otherwise('index');
					$stateProvider
							.state('treeSeed', {
								abstract : true,
								url : '/',
								templateUrl : 'layouts/pages/main.html'/*
																		 * ,
																		 * resolve: {
																		 * auth:
																		 * function
																		 * resolveAuthentication(AuthResolver) {
																		 * return
																		 * AuthResolver.resolve(); } }
																		 */
							})								
							.state(
									'treeSeed.index',
									{
										url : 'index',
										templateUrl : 'layouts/pages/index.html',
										controller : 'indexController',
										data : {
											authorizedRoles : [
													USER_ROLES.donor,
													USER_ROLES.guest,
													USER_ROLES.nonprofit ]
										
										}
									})
									.state(
											'treeSeed.donor',
											{
												url : 'donor/:donorId',
												templateUrl : 'layouts/pages/donor.html',
												controller: "getDonorProfileController",
												params: {donorId: null},
												data : {
													authorizedRoles : [
															USER_ROLES.donor,
															USER_ROLES.guest,
															USER_ROLES.nonprofit ]
												
												}
											})
									.state(
											'treeSeed.nonProfit',
											{
												url : 'nonProfit/:nonProfitId',
												templateUrl : 'layouts/pages/nonProfit.html',
												controller: "getNonProfitProfileController",
												params: {nonProfitId: null},
												resolve : load([
																'angularUtils.directives.dirPagination']),
												 data : {
													authorizedRoles : [
													USER_ROLES.donor,
													USER_ROLES.guest,
													USER_ROLES.nonprofit ]

												}
											})
									.state(
										'treeSeed.nonProfitSettings',
										{
											url : 'nonProfitSettings',
											templateUrl : 'layouts/pages/nonprofitSettings.html',
											controller : "nonprofitSettingsController",
											data : {
												authorizedRoles : [
														USER_ROLES.guest,
														USER_ROLES.nonprofit ]
											
											}
										})
									/*.state(
											'treeSeed.searchTransReport',
											{
												url : 'str',
												templateUrl : 'layouts/pages/transparencyReportSearch.html',
												controller : "searchTransparecyReportController"
											})*/
									.state(
											'treeSeed.createCampaing',
											{
												url : 'createCampaing',
												templateUrl : 'layouts/pages/createCampaing.html',
												controller: 'campaingCreateController',
												data : {
													authorizedRoles : [USER_ROLES.nonprofit ]
												}
											})
									.state(
											'treeSeed.campaignManagement',
											{
												url : 'campaignManagement',
												templateUrl : 'layouts/pages/campaignManagement.html',
												controller: 'searchCampaignFromNonProfitController',
												resolve : load([
																	'angularUtils.directives.dirPagination',
																	'resources/js/controllers/campaignController.js' ]),
												data : {
													authorizedRoles : [USER_ROLES.nonprofit ]
												}
											})
									.state(
											'treeSeed.campaign',
											{
												url : 'campaign/:campaignId',
												templateUrl : 'layouts/pages/campaign.html',
												params: {campaignId: null},
												controller: "getCampaingProfileController",
												resolve : load([
																'angularUtils.directives.dirPagination']),
												data : {
													authorizedRoles : [
													USER_ROLES.donor,
													USER_ROLES.guest,
													USER_ROLES.nonprofit ]


												}
											})
									.state(
											'treeSeed.nonProfitSearch',
											{
												url : 'nonProfitSearch',
												templateUrl : 'layouts/pages/nonProfitSearch.html',
												resolve : load([
														'angularUtils.directives.dirPagination',
														'resources/js/controllers/nonprofitController.js' ]),
												controller : "nonProfitSearchController",
												data : {
													authorizedRoles : [
															USER_ROLES.donor,
															USER_ROLES.guest,
															USER_ROLES.nonprofit ]
												
												}
											})
									.state(
											'treeSeed.campaignSearch',
											{
												url : 'campaignSearch',
												templateUrl : 'layouts/pages/campaignSearch.html',
												resolve : load([
														'angularUtils.directives.dirPagination',
														'resources/js/controllers/campaignController.js' ]),
												controller : "campaignSearchController",
												data : {
													authorizedRoles : [
															USER_ROLES.donor,
															USER_ROLES.guest,
															USER_ROLES.nonprofit ]
												
												}
											})		
									.state(
											'treeSeed.registerNonProfit',
											{
												url : 'registerNonProfit',
												templateUrl : 'layouts/pages/registerNonProfitProfile.html',
												controller : "nonProfitRegistrationController",
												data : {
													authorizedRoles : [
															USER_ROLES.guest]
												
												}
											})
									.state(
											'treeSeed.registerDonor',
											{
												url : 'registerDonor',
												templateUrl : 'layouts/pages/registerDonor.html',
												controller : "donorRegistrationController",
												data : {
													authorizedRoles : [
															USER_ROLES.guest ]
												
												}
											})
									.state(
											'treeSeed.selectUser',
											{
												url : 'selecUser',
												templateUrl : 'layouts/pages/registerUserSelect.html',
												data : {
													authorizedRoles : [	USER_ROLES.guest ]
												
												}
												
											})
									.state(
											'treeSeed.donorSearch',
											{
												url : 'donorSearch',
												templateUrl : 'layouts/pages/donorSearch.html',
												resolve : load([ 'angularUtils.directives.dirPagination' ]),
												controller : "donorSearchController",
												data : {
													authorizedRoles : [
															USER_ROLES.donor,
															USER_ROLES.guest,
															USER_ROLES.nonprofit ]
												
												}
											})
								  	.state(
										'treeSeed.donorSettings',
										{
											url : 'donorSettings',
											templateUrl : 'layouts/pages/donorSettings.html',
											controller : "donorSettingsController",
											data : {
												authorizedRoles : [
														USER_ROLES.donor
														 ]
											
												}
											})
								  	.state(
										'treeSeed.donorReceipts',
										{
											url : 'donorReceipts',
											templateUrl : 'layouts/pages/donorReceipts.html',
											controller : "donorReceiptsController",
											resolve : load([
														'angularUtils.directives.dirPagination',
														'resources/js/controllers/nonprofitController.js' ]),
											data : {
												authorizedRoles : [
														USER_ROLES.donor
														 ]
											
												}
											})
								  	.state(
											'treeSeed.transparencyReport',
											{
												url : 'transparencyReport',
												templateUrl : 'layouts/pages/transparencyReport.html',
												resolve : load([
														'xeditable',
														'resources/js/libs/angular/angular-xeditable/dist/js/xeditable.js' ]),
												controller: "createTransparencyReportController",
												data : {
													authorizedRoles : [
													USER_ROLES.nonprofit 
													]
												}
											 })
									.state(
											'treeSeed.nonProfitDonationsReport',
											{
												url : 'nonProfitDonationsReport',
												templateUrl : 'layouts/pages/nonProfitDonationsReport.html',
												resolve : load([
																'angularUtils.directives.dirPagination',
																'resources/js/controllers/donationReportController.js' ]),
												controller: "nonProfitDonationReportController",
												data : {
													authorizedRoles : [
													USER_ROLES.nonprofit 
													]
												}
											 });
									

							
							

					function load(srcs, callback) {
						return {
							deps : [
									'$ocLazyLoad',
									'$q',
									function($ocLazyLoad, $q) {
										var deferred = $q.defer();
										var promise = false;
										srcs = angular.isArray(srcs) ? srcs
												: srcs.split(/\s+/);
										if (!promise) {
											promise = deferred.promise;
										}
										angular
												.forEach(
														srcs,
														function(src) {
															console.log(src);
															promise = promise
																	.then(function() {
																		if (JQ_CONFIG[src]) {
																			return $ocLazyLoad
																					.load(JQ_CONFIG[src]);
																		}
																		angular
																				.forEach(
																						MODULE_CONFIG,
																						function(
																								module) {
																							if (module.name == src) {
																								name = module.name;
																							} else {
																								name = src;
																							}
																						});
																		return $ocLazyLoad
																				.load(name);
																	});
														});
										deferred.resolve();
										return callback ? promise
												.then(function() {
													return callback();
												}) : promise;
									} ]
						}
					}
				});

angular.module('treeSeed').config(
		[
				'$controllerProvider',
				'$compileProvider',
				'$filterProvider',
				'$provide',
				function($controllerProvider, $compileProvider,
						$filterProvider, $provide) {

					// lazy controller, directive and service
					app.controller = $controllerProvider.register;
					app.directive = $compileProvider.directive;
					app.filter = $filterProvider.register;
					app.factory = $provide.factory;
					app.service = $provide.service;
					app.constant = $provide.constant;
					app.value = $provide.value;
				} ]).config(
		[ '$translateProvider', function($translateProvider) {
			// Register a loader for the static files
			// So, the module will search missing translation tables under the
			// specified urls.
			// Those urls are [prefix][langKey][suffix].
			$translateProvider.useStaticFilesLoader({
				prefix : 'resources/js/l10n/',
				suffix : '.js'
			});
			// Tell the module what language to use by default
			$translateProvider.preferredLanguage('en');
			// Tell the module to store the language in the local storage
			$translateProvider.useLocalStorage();
		} ]);
// lazyload config
/**
 * jQuery plugin config use ui-jq directive , config the js and css files that
 * required key: function name of the jQuery plugin value: array of the css js
 * file located
 */
angular
		.module('treeSeed')
		.constant(
				'JQ_CONFIG',
				{
					easyPieChart : [ 'resources/js/libs/jquery/jquery.easy-pie-chart/dist/jquery.easypiechart.fill.js' ],
					sparkline : [ 'resources/js/libs/jquery/jquery.sparkline/dist/jquery.sparkline.retina.js' ],
					plot : [
							'resources/js/libs/jquery/flot/jquery.flot.js',
							'resources/js/libs/jquery/flot/jquery.flot.pie.js',
							'resources/js/libs/jquery/flot/jquery.flot.resize.js',
							'resources/js/libs/jquery/flot.tooltip/js/jquery.flot.tooltip.min.js',
							'resources/js/libs/jquery/flot.orderbars/js/jquery.flot.orderBars.js',
							'resources/js/libs/jquery/flot-spline/js/jquery.flot.spline.min.js' ],
					moment : [ 'resources/js/js/libs/jquery/moment/moment.js' ],
					screenfull : [ 'resources/js/libs/jquery/screenfull/dist/screenfull.min.js' ],
					slimScroll : [ 'resources/js/libs/jquery/slimscroll/jquery.slimscroll.min.js' ],
					sortable : [ 'resources/js/libs/jquery/html5sortable/jquery.sortable.js' ],
					nestable : [
							'resources/js/libs/jquery/nestable/jquery.nestable.js',
							'resources/js/libs/jquery/nestable/jquery.nestable.css' ],
					filestyle : [ 'resources/js/libs/jquery/bootstrap-filestyle/src/bootstrap-filestyle.js' ],
					slider : [
							'resources/js/libs/jquery/bootstrap-slider/bootstrap-slider.js',
							'resources/js/libs/jquery/bootstrap-slider/bootstrap-slider.css' ],
					chosen : [
							'resources/js/libs/jquery/chosen/chosen.jquery.min.js',
							'resources/js/libs/jquery/chosen/bootstrap-chosen.css' ],
					TouchSpin : [
							'resources/js/libs/jquery/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js',
							'resources/js/libs/jquery/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css' ],
					wysiwyg : [
							'resources/js/libs/jquery/bootstrap-wysiwyg/bootstrap-wysiwyg.js',
							'resources/js/libs/jquery/bootstrap-wysiwyg/external/jquery.hotkeys.js' ],
					dataTable : [
							'resources/js/libs/jquery/datatables/media/js/jquery.dataTables.min.js',
							'resources/js/libs/jquery/plugins/integration/bootstrap/3/dataTables.bootstrap.js',
							'resources/js/libs/jquery/plugins/integration/bootstrap/3/dataTables.bootstrap.css' ],
					vectorMap : [
							'resources/js/libs/jquery/bower-jvectormap/jquery-jvectormap-1.2.2.min.js',
							'resources/js/libs/jquery/bower-jvectormap/jquery-jvectormap-world-mill-en.js',
							'resources/js/libs/jquery/bower-jvectormap/jquery-jvectormap-us-aea-en.js',
							'resources/js/libs/jquery/bower-jvectormap/jquery-jvectormap.css' ],
					footable : [
							'resources/js/libs/jquery/footable/dist/footable.all.min.js',
							'resources/js/libs/jquery/footable/css/footable.core.css' ],
					fullcalendar : [
							'resources/js/js/libs/jquery/moment/moment.js',
							'resources/js/libs/jquery/fullcalendar/dist/fullcalendar.min.js',
							'resources/js/libs/jquery/fullcalendar/dist/fullcalendar.css',
							'resources/js/libs/jquery/fullcalendar/dist/fullcalendar.theme.css' ],
					daterangepicker : [
							'resources/js/libs/jquery/moment/moment.js',
							'resources/js/libs/jquery/bootstrap-daterangepicker/daterangepicker.js',
							'resources/js/libs/jquery/bootstrap-daterangepicker/daterangepicker-bs3.css' ],
					tagsinput : [
							'resources/js/libs/jquery/bootstrap-tagsinput/dist/bootstrap-tagsinput.js',
							'resources/js/libs/jquery/bootstrap-tagsinput/dist/bootstrap-tagsinput.css' ]

				})
angular
		.module('treeSeed')
		.constant(
				'MODULE_CONFIG',
				[
						{
							name : 'postController',
							files : ['resources/js/controllers/postController.js']
						},
						{
							name : 'angularUpload',
							files : [
									'resources/js/libs/angular/angular-upload/angular-file-upload.min.js',
									'resources/js/libs/angular/angular-upload/angular-file-upload-shim.min.js' ]
						},
						{
							name : 'angularUtils.directives.dirPagination',
							files : [
									'resources/js/libs/angular/dirPagination/dirPagination.js',
									'resources/js/libs/angular/dirPagination/dirPagination.tpl.html' ]
						},
						{
							name : 'ngGrid',
							files : [
									'resources/js/libs/angular/ng-grid/build/ng-grid.min.js',
									'resources/js/libs/angular/ng-grid/ng-grid.min.css',
									'resources/js/libs/angular/ng-grid/ng-grid.bootstrap.css' ]
						},
						{
							name : 'ui.grid',
							files : [
									'resources/js/libs/angular/angular-ui-grid/ui-grid.min.js',
									'resources/js/libs/angular/angular-ui-grid/ui-grid.min.css',
									'resources/js/libs/angular/angular-ui-grid/ui-grid.bootstrap.css' ]
						},
						{
							name : 'ui.select',
							files : [
									'resources/js/libs/angular/angular-ui-select/dist/select.min.js',
									'resources/js/libs/angular/angular-ui-select/dist/select.min.css' ]
						},
						{
							name : 'angularFileUpload',
							files : [ 'resources/js/libs/angular/angular-file-upload/angular-file-upload.js' ]
						},
						{
							name : 'ui.calendar',
							files : [ 'resources/js/libs/angular/angular-ui-calendar/src/calendar.js' ]
						},
						{
							name : 'ngImgCrop',
							files : [
									'resources/js/libs/angular/ngImgCrop/compile/minified/ng-img-crop.js',
									'resources/js/libs/angular/ngImgCrop/compile/minified/ng-img-crop.css' ]
						},
						{
							name : 'angularBootstrapNavTree',
							files : [
									'resources/js/libs/angular/angular-bootstrap-nav-tree/dist/abn_tree_directive.js',
									'resources/js/libs/angular/angular-bootstrap-nav-tree/dist/abn_tree.css' ]
						},
						{
							name : 'toaster',
							files : [
									'resources/js/libs/angular/angularjs-toaster/toaster.js',
									'resources/js/libs/angular/angularjs-toaster/toaster.css' ]
						},
						{
							name : 'textAngular',
							files : [
									'resources/js/libs/angular/textAngular/dist/textAngular-sanitize.min.js',
									'resources/js/libs/angular/textAngular/dist/textAngular.min.js' ]
						},
						{
							name : 'vr.directives.slider',
							files : [
									'resources/js/libs/angular/venturocket-angular-slider/build/angular-slider.min.js',
									'resources/js/libs/angular/venturocket-angular-slider/build/angular-slider.css' ]
						},
						{
							name : 'com.2fdevs.videogular',
							files : [ 'resources/js/libs/angular/videogular/videogular.min.js' ]
						},
						{
							name : 'com.2fdevs.videogular.plugins.controls',
							files : [ 'resources/js/libs/angular/videogular-controls/controls.min.js' ]
						},
						{
							name : 'com.2fdevs.videogular.plugins.buffering',
							files : [ 'resources/js/libs/angular/videogular-buffering/buffering.min.js' ]
						},
						{
							name : 'com.2fdevs.videogular.plugins.overlayplay',
							files : [ 'resources/js/libs/angular/videogular-overlay-play/overlay-play.min.js' ]
						},
						{
							name : 'com.2fdevs.videogular.plugins.poster',
							files : [ 'resources/js/libs/angular/videogular-poster/poster.min.js' ]
						},
						{
							name : 'com.2fdevs.videogular.plugins.imaads',
							files : [ 'resources/js/libs/angular/videogular-ima-ads/ima-ads.min.js' ]
						},
						{
							name : 'xeditable',
							files : [
									'resources/js/libs/angular/angular-xeditable/dist/js/xeditable.min.js',
									'resources/js/libs/angular/angular-xeditable/dist/css/xeditable.css' ]
						},
						{
							name : 'smart-table',
							files : [ 'resources/js/libs/angular/angular-smart-table/dist/smart-table.min.js' ]
						},
						{
							name : 'angular-skycons',
							files : [ 'resources/js/libs/angular/angular-skycons/angular-skycons.js' ]
						} ])
// oclazyload config
angular.module('treeSeed').config(
		[ '$ocLazyLoadProvider', 'MODULE_CONFIG',
				function($ocLazyLoadProvider, MODULE_CONFIG) {
					// We configure ocLazyLoad to use the lib script.js as the
					// async loader
					$ocLazyLoadProvider.config({
						debug : false,
						events : true,
						modules : MODULE_CONFIG
					});
				} ]);
