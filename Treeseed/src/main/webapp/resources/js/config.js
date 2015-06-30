
angular.module('treeSeed')
.run(
    [          '$rootScope', '$state', '$stateParams',
      function ($rootScope,   $state,   $stateParams) {
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;        
      }
    ]
  )
.config(
    [          '$stateProvider', '$urlRouterProvider', 'JQ_CONFIG', 'MODULE_CONFIG', 
      function ($stateProvider,   $urlRouterProvider, JQ_CONFIG, MODULE_CONFIG) {


    $urlRouterProvider.otherwise('/signin');
    $stateProvider
        .state('treeSeed',{
            abstract: true,
            url: '/treeseed.org',
            templateUrl: '../layouts/pages/main.html'
        })    
        .state('signin',{
            url: '/signin',
            templateUrl: '../layouts/components/page_signin.html',
            controller: 'SigninFormController'
        })
        .state('treeSeed.index', {
              url: '/index',
              templateUrl: '../layouts/pages/index.html',
              controller: 'indexController'
        })    
        .state('treeSeed.donor', {
              url: '/donor',
              templateUrl: '../layouts/pages/donor.html'
              //controller: 'TypeaheadDemoCtrl'
        })
        .state('treeSeed.nonProfit', {
             url: '/nonProfit',
             templateUrl: '../layouts/pages/nonProfit.html'
             //resolve: load(['js/controllers/chart.js'])
        })
        .state('treeSeed.donate', {
            url: '../donate',
            templateUrl: '../layouts/pages/donate.html'
            //resolve: load(['js/controllers/chart.js'])
        })
        .state('treeSeed.searchTransReport', {
            url: '/str',
            templateUrl: '../layouts/pages/transparencyReportSearch.html',
            controller: "searchTransparecyReportController"
        })
        .state('treeSeed.createCampaing', {
            url: '/createCampaing',
            templateUrl: '../layouts/pages/createCampaing.html'
            //resolve: load(['js/controllers/chart.js'])
        })
         .state('treeSeed.campaingViewer', {
            url: '/campaingViewer',
            templateUrl: '../layouts/pages/campaingViewer.html'
            //resolve: load(['js/controllers/chart.js'])
        })
        
}]);

/*.config(function($routeProvider, $locationProvider, $urlRouterProvider) {

    $routeProvider.when('/signin', {
                templateUrl: '/layouts/main.html', 
                controller: 'SigninFormController'                    
            } );
    $routeProvider.when('/', {
                emplateUrl: '/layouts/main.html' 
                //controller: 'moviesController'                    
            } );
    $routeProvider.when('/donor', {
                templateUrl: '/layouts/donor.html' 
                //controller: 'moviesController'
            } );
    $routeProvider.when('/nonProfit', {
                templateUrl: '/layouts/nonProfit.html'                    
                //controller: 'seatsController'
            } );
    $routeProvider.when('/donate', {
                templateUrl: '/layouts/donate.html' 
                //controller: 'confirmationController'
            } );

    //$locationProvider.html5Mode(true);

});*/

angular.module('treeSeed').config(
    [        '$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
    function ($controllerProvider,   $compileProvider,   $filterProvider,   $provide) {
        
        // lazy controller, directive and service
        app.controller = $controllerProvider.register;
        app.directive  = $compileProvider.directive;
        app.filter     = $filterProvider.register;
        app.factory    = $provide.factory;
        app.service    = $provide.service;
        app.constant   = $provide.constant;
        app.value      = $provide.value;
    }
  ])
  .config(['$translateProvider', function($translateProvider){
    // Register a loader for the static files
    // So, the module will search missing translation tables under the specified urls.
    // Those urls are [prefix][langKey][suffix].
    $translateProvider.useStaticFilesLoader({
      prefix: '/src/main/webapp/resources/js/l10n/',
      suffix: '.js'
    });
    // Tell the module what language to use by default
    $translateProvider.preferredLanguage('en');
    // Tell the module to store the language in the local storage
    $translateProvider.useLocalStorage();
  }]);
// lazyload config
    /**
   * jQuery plugin config use ui-jq directive , config the js and css files that required
   * key: function name of the jQuery plugin
   * value: array of the css js file located
   */
  angular.module('treeSeed').constant('JQ_CONFIG', {
      easyPieChart:   [   '/src/main/webapp/resources/js/libs/jquery/jquery.easy-pie-chart/dist/jquery.easypiechart.fill.js'],
      sparkline:      [   '/src/main/webapp/resources/js/libs/jquery/jquery.sparkline/dist/jquery.sparkline.retina.js'],
      plot:           [   '/src/main/webapp/resources/js/libs/jquery/flot/jquery.flot.js',
                          '/src/main/webapp/resources/js/libs/jquery/flot/jquery.flot.pie.js', 
                          '/src/main/webapp/resources/js/libs/jquery/flot/jquery.flot.resize.js',
                          '/src/main/webapp/resources/js/libs/jquery/flot.tooltip/js/jquery.flot.tooltip.min.js',
                          '/src/main/webapp/resources/js/libs/jquery/flot.orderbars/js/jquery.flot.orderBars.js',
                          '/src/main/webapp/resources/js/libs/jquery/flot-spline/js/jquery.flot.spline.min.js'],
      moment:         [   '/src/main/webapp/resources/js/js/libs/jquery/moment/moment.js'],
      screenfull:     [   '/src/main/webapp/resources/js/libs/jquery/screenfull/dist/screenfull.min.js'],
      slimScroll:     [   '/src/main/webapp/resources/js/libs/jquery/slimscroll/jquery.slimscroll.min.js'],
      sortable:       [   '/src/main/webapp/resources/js/libs/jquery/html5sortable/jquery.sortable.js'],
      nestable:       [   '/src/main/webapp/resources/js/libs/jquery/nestable/jquery.nestable.js',
                          '/src/main/webapp/resources/js/libs/jquery/nestable/jquery.nestable.css'],
      filestyle:      [   '/src/main/webapp/resources/js/libs/jquery/bootstrap-filestyle/src/bootstrap-filestyle.js'],
      slider:         [   '/src/main/webapp/resources/js/libs/jquery/bootstrap-slider/bootstrap-slider.js',
                          '/src/main/webapp/resources/js/libs/jquery/bootstrap-slider/bootstrap-slider.css'],
      chosen:         [   '/src/main/webapp/resources/js/libs/jquery/chosen/chosen.jquery.min.js',
                          '/src/main/webapp/resources/js/libs/jquery/chosen/bootstrap-chosen.css'],
      TouchSpin:      [   '/src/main/webapp/resources/js/libs/jquery/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js',
                          '/src/main/webapp/resources/js/libs/jquery/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css'],
      wysiwyg:        [   '/src/main/webapp/resources/js/libs/jquery/bootstrap-wysiwyg/bootstrap-wysiwyg.js',
                          '/src/main/webapp/resources/js/libs/jquery/bootstrap-wysiwyg/external/jquery.hotkeys.js'],
      dataTable:      [   '/src/main/webapp/resources/js/libs/jquery/datatables/media/js/jquery.dataTables.min.js',
                          '/src/main/webapp/resources/js/libs/jquery/plugins/integration/bootstrap/3/dataTables.bootstrap.js',
                          '/src/main/webapp/resources/js/libs/jquery/plugins/integration/bootstrap/3/dataTables.bootstrap.css'],
      vectorMap:      [   '/src/main/webapp/resources/js/libs/jquery/bower-jvectormap/jquery-jvectormap-1.2.2.min.js', 
                          '/src/main/webapp/resources/js/libs/jquery/bower-jvectormap/jquery-jvectormap-world-mill-en.js',
                          '/src/main/webapp/resources/js/libs/jquery/bower-jvectormap/jquery-jvectormap-us-aea-en.js',
                          '/src/main/webapp/resources/js/libs/jquery/bower-jvectormap/jquery-jvectormap.css'],
      footable:       [   '/src/main/webapp/resources/js/libs/jquery/footable/dist/footable.all.min.js',
                          '/src/main/webapp/resources/js/libs/jquery/footable/css/footable.core.css'],
      fullcalendar:   [   '/src/main/webapp/resources/js/js/libs/jquery/moment/moment.js',
                          '/src/main/webapp/resources/js/libs/jquery/fullcalendar/dist/fullcalendar.min.js',
                          '/src/main/webapp/resources/js/libs/jquery/fullcalendar/dist/fullcalendar.css',
                          '/src/main/webapp/resources/js/libs/jquery/fullcalendar/dist/fullcalendar.theme.css'],
      daterangepicker:[   '/src/main/webapp/resources/js/libs/jquery/moment/moment.js',
                          '/src/main/webapp/resources/js/libs/jquery/bootstrap-daterangepicker/daterangepicker.js',
                          '/src/main/webapp/resources/js/libs/jquery/bootstrap-daterangepicker/daterangepicker-bs3.css'],
      tagsinput:      [   '/src/main/webapp/resources/js/libs/jquery/bootstrap-tagsinput/dist/bootstrap-tagsinput.js',
                          '/src/main/webapp/resources/js/libs/jquery/bootstrap-tagsinput/dist/bootstrap-tagsinput.css']
                      
    }
  )
  angular.module('treeSeed').constant('MODULE_CONFIG', [
      {
          name: 'ngGrid',
          files: [
              '/src/main/webapp/resources/js/libs/angular/ng-grid/build/ng-grid.min.js',
              '/src/main/webapp/resources/js/libs/angular/ng-grid/ng-grid.min.css',
              '/src/main/webapp/resources/js/libs/angular/ng-grid/ng-grid.bootstrap.css'
          ]
      },
      {
          name: 'ui.grid',
          files: [
              '/src/main/webapp/resources/js/libs/angular/angular-ui-grid/ui-grid.min.js',
              '/src/main/webapp/resources/js/libs/angular/angular-ui-grid/ui-grid.min.css',
              '/src/main/webapp/resources/js/libs/angular/angular-ui-grid/ui-grid.bootstrap.css'
          ]
      },
      {
          name: 'ui.select',
          files: [
              '/src/main/webapp/resources/js/libs/angular/angular-ui-select/dist/select.min.js',
              '/src/main/webapp/resources/js/libs/angular/angular-ui-select/dist/select.min.css'
          ]
      },
      {
          name:'angularFileUpload',
          files: [
            '/src/main/webapp/resources/js/libs/angular/angular-file-upload/angular-file-upload.js'
          ]
      },
      {
          name:'ui.calendar',
          files: ['/src/main/webapp/resources/js/libs/angular/angular-ui-calendar/src/calendar.js']
      },
      {
          name: 'ngImgCrop',
          files: [
              '/src/main/webapp/resources/js/libs/angular/ngImgCrop/compile/minified/ng-img-crop.js',
              '/src/main/webapp/resources/js/libs/angular/ngImgCrop/compile/minified/ng-img-crop.css'
          ]
      },
      {
          name: 'angularBootstrapNavTree',
          files: [
              '/src/main/webapp/resources/js/libs/angular/angular-bootstrap-nav-tree/dist/abn_tree_directive.js',
              '/src/main/webapp/resources/js/libs/angular/angular-bootstrap-nav-tree/dist/abn_tree.css'
          ]
      },
      {
          name: 'toaster',
          files: [
              '/src/main/webapp/resources/js/libs/angular/angularjs-toaster/toaster.js',
              '/src/main/webapp/resources/js/libs/angular/angularjs-toaster/toaster.css'
          ]
      },
      {
          name: 'textAngular',
          files: [
              '/src/main/webapp/resources/js/libs/angular/textAngular/dist/textAngular-sanitize.min.js',
              '/src/main/webapp/resources/js/libs/angular/textAngular/dist/textAngular.min.js'
          ]
      },
      {
          name: 'vr.directives.slider',
          files: [
              '/src/main/webapp/resources/js/libs/angular/venturocket-angular-slider/build/angular-slider.min.js',
              '/src/main/webapp/resources/js/libs/angular/venturocket-angular-slider/build/angular-slider.css'
          ]
      },
      {
          name: 'com.2fdevs.videogular',
          files: [
              '/src/main/webapp/resources/js/libs/angular/videogular/videogular.min.js'
          ]
      },
      {
          name: 'com.2fdevs.videogular.plugins.controls',
          files: [
              '/src/main/webapp/resources/js/libs/angular/videogular-controls/controls.min.js'
          ]
      },
      {
          name: 'com.2fdevs.videogular.plugins.buffering',
          files: [
              '/src/main/webapp/resources/js/libs/angular/videogular-buffering/buffering.min.js'
          ]
      },
      {
          name: 'com.2fdevs.videogular.plugins.overlayplay',
          files: [
              '/src/main/webapp/resources/js/libs/angular/videogular-overlay-play/overlay-play.min.js'
          ]
      },
      {
          name: 'com.2fdevs.videogular.plugins.poster',
          files: [
              '/src/main/webapp/resources/js/libs/angular/videogular-poster/poster.min.js'
          ]
      },
      {
          name: 'com.2fdevs.videogular.plugins.imaads',
          files: [
              '/src/main/webapp/resources/js/libs/angular/videogular-ima-ads/ima-ads.min.js'
          ]
      },
      {
          name: 'xeditable',
          files: [
              '/src/main/webapp/resources/js/libs/angular/angular-xeditable/dist/js/xeditable.min.js',
              '/src/main/webapp/resources/js/libs/angular/angular-xeditable/dist/css/xeditable.css'
          ]
      },
      {
          name: 'smart-table',
          files: [
              '/src/main/webapp/resources/js/libs/angular/angular-smart-table/dist/smart-table.min.js'
          ]
      },
      {
          name: 'angular-skycons',
          files: [
              '/src/main/webapp/resources/js/libs/angular/angular-skycons/angular-skycons.js'
          ]
      }
    ]
  )
  // oclazyload config
  angular.module('treeSeed').config(['$ocLazyLoadProvider', 'MODULE_CONFIG', function($ocLazyLoadProvider, MODULE_CONFIG) {
      // We configure ocLazyLoad to use the lib script.js as the async loader
      $ocLazyLoadProvider.config({
          debug:  false,
          events: true,
          modules: MODULE_CONFIG
      });
  }])
;