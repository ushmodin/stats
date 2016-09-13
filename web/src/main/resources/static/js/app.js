(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngSanitize', 'ui.bootstrap', 'ui.select'])
        .config(['$routeProvider', routeConfig]);

    function routeConfig($routeProvider) {
        $routeProvider.when('/', {
            redirectTo: '/'
        }).when('/addrobj', {
            templateUrl: 'partial/addrobj.html',
            controller: 'AddrobjController'
        }).when('/regions', {
            templateUrl: 'partial/regions.html',
            controller: 'RegionsController'
        }).when('/areas', {
            templateUrl: 'partial/areas.html',
            controller: 'AreasController'
        }).when('/places', {
            templateUrl: 'partial/places.html',
            controller: 'PlacesController'
        }).when('/requests', {
            templateUrl: 'partial/requests.html',
            controller: 'RequestsController'
        }).when('/request/:id', {
            templateUrl: 'partial/request.html',
            controller: 'RequestController'
        }).otherwise({
            redirectTo: '/'
        });
    };
})();
