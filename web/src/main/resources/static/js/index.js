var app = angular.module('app', ['ngRoute', 'ui.bootstrap','ui.grid']);

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