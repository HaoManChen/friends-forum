/**
 * 建立angular.module
 */
define(['angular','uiRoute'],function (angular) {
    var app = angular.module('webapp',['ui.router']);
    return app;
});