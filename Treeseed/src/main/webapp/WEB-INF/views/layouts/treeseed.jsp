<!DOCTYPE html>
<html lang="en" data-ng-app="treeSeed">

<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js">
</script>
<![endif]-->
<title>TreeSeed</title>
<meta charset="UTF-8">
<meta name="description"	content="Angularjs, Html5, Music, Landing, 4 in 1 ui kits package" />
<meta name="keywords"	content="AngularJS, angular, bootstrap, admin, dashboard, panel, app, charts, components,flat, responsive, layout, kit, ui, route, web, app, widgets" />
<meta name="viewport"	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="resources/js/libs/assets/animate.css/animate.css" type="text/css" />
<link rel="stylesheet"	href="resources/js/libs/assets/font-awesome/css/font-awesome.min.css"	type="text/css" />
<link rel="stylesheet"	href="resources/js/libs/assets/simple-line-icons/css/simple-line-icons.css"	type="text/css" />
<link rel="stylesheet"	href="resources/js/libs/angular/angular-material/angular-material.css"	type="text/css" />
<link rel="stylesheet" href="resources/css/app.css" type="text/css" />
<link rel="stylesheet" href="resources/css/site.css" type="text/css" />

<link rel="shortcut icon" href="resources/images/circle.png">

<script type="text/javascript" src="https://js.stripe.com/v2/"></script>
<script src="resources/js/libs.js"></script>
<script src="resources/js/app.js"></script>
<script src="resources/js/appLogin.js"></script>
<script src="resources/js/libs/angular/angular-payments/angular-payments.js"></script>
<script src="resources/js/controllers/loginController.js"></script>
<script src="resources/js/services/treeSeedServices.js"></script>
<script src="resources/js/services/d3Services.js"></script>
<script src="resources/js/controllers/generalControllers.js"></script>
<script src="resources/js/filters/treeSeedFilters.js"></script>
<script src="resources/js/directives/treeSeedDirectives.js"></script>
<script src="resources/js/constants/treeSeedConstants.js"></script>
<script src="resources/js/controllers/treeSeedApp.js"></script>
<script src="resources/js/controllers/utilitiesControllers.js"></script>
<script src="resources/js/controllers/campaignController.js"></script>
<script src="resources/js/controllers/donationController.js"></script>
<script src="resources/js/controllers/nonprofitController.js"></script>
<script src="resources/js/controllers/donorController.js"></script>
<script src="resources/js/controllers/postController.js"></script>
<script src="resources/js/controllers/loadingController.js"></script>
<script src="resources/js/controllers/postCampaignController.js"></script>
<script src="resources/js/controllers/transparencyReportController.js"></script>
<script src="resources/js/controllers/donorReceiptsController.js"></script>
<script src="resources/js/controllers/donationReportController.js"></script>
<script src="resources/js/controllers/facebookController.js"></script>
<script src="resources/js/config.js"></script>
<script	src="resources/js/libs/angular/angular-upload/angular-file-upload.min.js"></script>
<script	src="resources/js/libs/angular/angular-upload/angular-file-upload-shim.min.js"></script>
<script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/aes.js"></script>
<script src="resources/js/libs/angular/ng-clip/ZeroClipboard.js"></script>
<script src="resources/js/libs/angular/ng-clip/ngClip.js"></script>
<!-- <base href="/treeseed.org" /> -->


<body layout="row" ng-controller="AppCtrl">

	<div class="app" id="app"
		ng-class="{'app-header-fixed':app.settings.headerFixed, 'app-aside-fixed':app.settings.asideFixed, 'app-aside-folded':app.settings.asideFolded, 'app-aside-dock':app.settings.asideDock, 'container':app.settings.container}"
		ui-view></div>

	<!-- jQuery -->


	<!-- Lazy loading -->
</body>
</html>