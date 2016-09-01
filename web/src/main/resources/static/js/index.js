'use strict';

var app = angular.module('app', ['ngRoute','ngSanitize','ui.bootstrap','ui.select']);

app.config(['$routeProvider', function ($routeProvider) {
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
}]);


app.controller('AddrobjController', ['$scope','$http',function ($scope,$http) {
    $scope.addrobjs = {};
    $scope.currentPage = 1;
    $scope.pageSize = 20;
    $scope.filter = {};

    $scope.loadAddrobjs = function () {
        $http.post('api/addrobj/list?page='+($scope.currentPage-1)+'&size='+($scope.pageSize), $scope.filter).then(function (response) {
            $scope.addrobjs = response.data.data;
        })
    };

    $scope.loadAddrobjs();
}]);

app.controller('RequestsController', ['$scope','$http',function ($scope,$http) {
    $scope.data = {};
    $scope.hosts = [];
    $scope.currentPage = 1;
    $scope.pageSize = 20;
    $scope.filter = {};

    $scope.loadData = function () {
        $http.post('api/requests/list?page='+($scope.currentPage-1)+'&size='+($scope.pageSize), $scope.filter).then(function (response) {
            $scope.data = response.data.data;
        })
    };
    $scope.loadHosts = function () {
        $http.post('api/requests/hosts').then(function (response) {
            $scope.hosts = response.data.data;
        })
    };

    $scope.loadData();
    $scope.loadHosts();
}]);



app.controller('RequestController', ['$scope','$http',function ($scope,$http) {
    $scope.data = {};
    $scope.hosts = [];
    $scope.currentPage = 1;
    $scope.pageSize = 20;
    $scope.filter = {};

    $scope.loadData = function () {
        $http.post('api/requests/list?page='+($scope.currentPage-1)+'&size='+($scope.pageSize), $scope.filter).then(function (response) {
            $scope.data = response.data.data;
        })
    };
    $scope.loadHosts = function () {
        $http.post('api/common/hosts').then(function (response) {
            $scope.hosts = response.data.data;
        })
    };

    $scope.loadData();
    $scope.loadHosts();
}]);

