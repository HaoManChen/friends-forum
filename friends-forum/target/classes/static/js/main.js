require.config({
    baseUrl:'js/',
    paths:{
        'jquery': 'libs/jquery.min',
        'bootstrap': 'libs/bootstrap.min',
        'angular': 'libs/angular.min',
        'app': 'app/app',
        'uiRoute' : 'libs/angular-ui-router',
        'route' : 'routes/appRoute',
        'index' : 'controllers/index'
    },
    shim:{
        'angular':{
            exports:'angular'
        },
        'uiRoute': {
            deps: ['angular'],
            exports: 'uiRoute'
        },
        'jquery':{
            exports:'jquery'
        },
        'app': {
            deps: ['jquery'],
            exports: 'app'
        },
        'bootstrap':{
            deps:['jquery'],
            exports:'bootstrap'
        }

    }
});

require([
    'jquery',
    'angular',
    'uiRoute',
    'app',
    'route',
    'bootstrap',
    'index'
], function ($, angular) {
    $(function () {
        angular.bootstrap(document, ['webapp']);

        require('index').initMenu();
    });
});



