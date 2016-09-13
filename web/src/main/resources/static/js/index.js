'use strict';






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

