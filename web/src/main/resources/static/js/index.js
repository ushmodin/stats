'use strict';

var app = angular.module('app', ['ngRoute', 'ngSanitize', 'ui.bootstrap', 'ui.select']);

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


app.controller('AddrobjController', ['$scope', '$http', function ($scope, $http) {
    $scope.addrobjs = {};
    $scope.currentPage = 1;
    $scope.pageSize = 20;
    $scope.filter = {};

    $scope.loadAddrobjs = function () {
        $http.post('api/addrobj/list?page=' + ($scope.currentPage - 1) + '&size=' + ($scope.pageSize), $scope.filter).then(function (response) {
            $scope.addrobjs = response.data.data;
        })
    };

    $scope.loadAddrobjs();
}]);

app.controller('RequestsController', ['$scope', '$http', function ($scope, $http) {
    $scope.data = {};
    $scope.hosts = [];
    $scope.currentPage = 1;
    $scope.pageSize = 20;
    $scope.filter = {};

    $scope.loadData = function () {
        $http.post('api/requests/list?page=' + ($scope.currentPage - 1) + '&size=' + ($scope.pageSize), $scope.filter).then(function (response) {
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


app.service('$common', ['$http', '$q', function ($http, $q) {
    var self = this;
    self.countries = function () {
        return $q(function (accept, reject) {
            $http.post('api/common/countries').then(function (response) {
                accept(response.data.data);
            });
        })
    };
    self.regions = function (countryId, name) {
        return $q(function (accept, reject) {
            $http.get('api/common/regions', {
                params: {
                    countryId: countryId,
                    name: name
                }
            }).then(function (response) {
                accept(response.data.data);
            });
        })
    };
    self.areas = function (regionId, name) {
        return $q(function (accept, reject) {
            $http.get('api/common/areas', {
                params: {
                    regionId: regionId,
                    name: name
                }
            }).then(function (response) {
                accept(response.data.data);
            });
        });
    };
    self.places = function (regionId, areaId, cityId, name) {
        return $q(function (accept, reject) {
            $http.get('api/common/places', {
                params: {
                    regionId: regionId,
                    areaId: areaId,
                    cityId: cityId,
                    name: name
                }
            }).then(function (response) {
                accept(response.data.data);
            });
        });
    };
    self.getPopulatedLocalityTypes = function (regionId, name) {
        return $q(function (accept, reject) {
            $http.get('api/common/populatedLocalityTypes').then(function (response) {
                accept(response.data.data);
            });
        });
    };

}]);

app.controller('RequestController', ['$scope', '$http', '$common', '$routeParams', '$location', function ($scope, $http, $common, $routeParams, $location) {
    $scope.populatedLocalityTypes = [];
    $scope.data = {};
    $scope.countries = [];
    $scope.regions = [];
    $scope.areas = [];
    $scope.cities = [];
    $scope.stationFilter = {
        currentPage: 1,
        pageSize: 20
    };
    $scope.newStation = {};
    $common.getPopulatedLocalityTypes().then(function (data) {
        $scope.populatedLocalityTypes = data;
    });

    $scope.loadData = function () {
        $http.post('api/request/' + $routeParams.id, $scope.filter).then(function (response) {
            $scope.data = response.data.data;
        })
    };
    $scope.loadData();

    $scope.loadRegions = function (name) {
        var countryId = $scope.stationFilter.country ? $scope.stationFilter.country.id : null;
        $common.regions(countryId,name).then(function (data) {
            $scope.regions = data;
        });
    };
    $scope.loadAraeas = function (name) {
        var regionId = $scope.stationFilter.region ? $scope.stationFilter.region.id : null;
        $common.areas(regionId,name).then(function (data) {
            $scope.areas = data;
        });
    };
    $scope.loadCities = function (name) {
        var regionId = $scope.stationFilter.region ? $scope.stationFilter.region.id : null;
        var areaId = $scope.stationFilter.area ? $scope.stationFilter.area.id : null;
        $common.places(regionId, areaId, null, name).then(function (data) {
            $scope.cities = data;
        });
    };
    $scope.loadPlaces = function (name) {
        var regionId = $scope.stationFilter.region ? $scope.stationFilter.region.id : null;
        var areaId = $scope.stationFilter.area ? $scope.stationFilter.area.id : null;
        var cityId = $scope.stationFilter.city ? $scope.stationFilter.city.id : null;
        $common.places(regionId, areaId, cityId, name).then(function (data) {
            $scope.places = data;
        });
    };

    $scope.loadStations = function () {
        $common.countries().then(function (data) {
            $scope.countries = data;
        });
        $scope.loadRegions();
        $scope.loadAraeas();
        $scope.loadCities();
        $scope.loadPlaces();
        $http.post('api/request/stations', {
            name: $scope.stationFilter.name,
            type: $scope.stationFilter.type,
            okato: $scope.stationFilter.okato,
            countryId: $scope.stationFilter.country ? $scope.stationFilter.country.id : null,
            regionId: $scope.stationFilter.region ? $scope.stationFilter.region.id : null,
            areaId: $scope.stationFilter.area ? $scope.stationFilter.area.id : null,
            cityId: $scope.stationFilter.city ? $scope.stationFilter.city.id : null,
            placeId: $scope.stationFilter.place ? $scope.stationFilter.place.id : null
        },{
            params: {
                page: $scope.stationFilter.currentPage - 1,
                size: $scope.stationFilter.pageSize
            }
        }).then(function (response) {
            $scope.stations = response.data.data;
        });
    };

    $scope.link = function (stationId) {
        $http.post('api/request/link',{
                requestId: Number($routeParams.id),
                stationId: stationId
            }).then(function (response) {
            $location.path('#requests');
        });
    };
    $scope.loadStations();
}]);

