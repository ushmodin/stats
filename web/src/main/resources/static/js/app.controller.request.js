(function () {
    'use strict';
    angular
        .module('app')
        .controller('RequestController', ['$scope', '$requests', '$routeParams', RequestController])
        .controller('RequestExistController', ['$scope', '$dict', '$routeParams', '$location', '$requests', '$stations',  RequestExistController])
        .controller('RequestNewController', ['$scope', '$dict', '$routeParams', '$location', '$requests', '$stations',  RequestNewController]);



    function RequestController($scope, $requests, $routeParams) {
        $scope.request = {};
        $requests.getRequest($routeParams.id).then(function (data) {
            $scope.request = data;
        });
    };

    function RequestExistController($scope, $dict, $routeParams, $location, $requests, $stations) {
        $scope.countries = [];
        $scope.regions = [];
        $scope.areas = [];
        $scope.cities = [];
        $scope.populatedLocalityTypes = [];
        $scope.stationFilter = {
            currentPage: 1,
            pageSize: 20
        };
        $scope.loadStations = loadStations;
        loadStations();



        $scope.loadPopulatedLocalityTypes = function () {
            $dict.getPopulatedLocalityTypes().then(function (data) {
                $scope.populatedLocalityTypes = data;
            });
        };

        $scope.loadCountries = function () {
            $dict.countries().then(function (data) {
                $scope.countries = data;
            })
        };

        $scope.loadRegions = function (name) {
            var countryId = $scope.stationFilter.country ? $scope.stationFilter.country.id : null;
            $dict.regions(countryId,name).then(function (data) {
                $scope.regions = data;
            });
        };

        $scope.loadAreas = function (name) {
            var regionId = $scope.stationFilter.region ? $scope.stationFilter.region.id : null;
            $dict.areas(regionId,name).then(function (data) {
                $scope.areas = data;
            });
        };

        $scope.loadCities = function (name) {
            var regionId = $scope.stationFilter.region ? $scope.stationFilter.region.id : null;
            var areaId = $scope.stationFilter.area ? $scope.stationFilter.area.id : null;
            $dict.places(regionId, areaId, null, name).then(function (data) {
                $scope.cities = data;
            });
        };

        $scope.loadPlaces = function (name) {
            var regionId = $scope.stationFilter.region ? $scope.stationFilter.region.id : null;
            var areaId = $scope.stationFilter.area ? $scope.stationFilter.area.id : null;
            var cityId = $scope.stationFilter.city ? $scope.stationFilter.city.id : null;
            $dict.places(regionId, areaId, cityId, name).then(function (data) {
                $scope.places = data;
            });
        };

        $scope.countryChanged = function () {
            $scope.stationFilter.region = null;
            $scope.stationFilter.area = null;
            $scope.stationFilter.city = null;
            $scope.stationFilter.place = null;
            loadStations();
        };

        $scope.regionChanged = function () {
            $scope.stationFilter.area = null;
            $scope.stationFilter.city = null;
            $scope.stationFilter.place = null;
            loadStations();
        };

        $scope.areaChanged = function () {
            $scope.stationFilter.city = null;
            $scope.stationFilter.place = null;
            loadStations();
        };

        $scope.cityChanged = function () {
            $scope.stationFilter.place = null;
            loadStations();
        };

        $scope.link = function (stationId) {
            $requests.link($routeParams.id, stationId).then(function () {
                $location.path('/requests');
            });
        };


        function loadStations() {
            var filter = {
                name: $scope.stationFilter.name,
                type: $scope.stationFilter.type ? $scope.stationFilter.type.shortName : null,
                okato: $scope.stationFilter.okato,
                countryId: $scope.stationFilter.country ? $scope.stationFilter.country.id : null,
                regionId: $scope.stationFilter.region ? $scope.stationFilter.region.id : null,
                areaId: $scope.stationFilter.area ? $scope.stationFilter.area.id : null,
                cityId: $scope.stationFilter.city ? $scope.stationFilter.city.id : null,
                placeId: $scope.stationFilter.place ? $scope.stationFilter.place.id : null
            };
            $stations.getStations(filter, $scope.stationFilter.currentPage - 1, $scope.stationFilter.pageSize).then(function (data) {
                $scope.stations = data;
            });
        };
    };

    function RequestNewController($scope, $dict, $routeParams, $location, $requests, $stations) {
        $scope.countries = [];
        $scope.regions = [];
        $scope.areas = [];
        $scope.cities = [];
        $scope.populatedLocalityTypes = [];
        $scope.newStation = {};

        $scope.loadPopulatedLocalityTypes = function () {
            $dict.getPopulatedLocalityTypes().then(function (data) {
                $scope.populatedLocalityTypes = data;
            });
        };

        $scope.loadCountries = function () {
            $dict.countries().then(function (data) {
                $scope.countries = data;
            })
        };

        $scope.loadRegions = function (name) {
            var countryId = $scope.newStation.country ? $scope.newStation.country.id : null;
            $dict.regions(countryId,name).then(function (data) {
                $scope.regions = data;
            });
        };

        $scope.loadAreas = function (name) {
            var regionId = $scope.newStation.region ? $scope.newStation.region.id : null;
            $dict.areas(regionId,name).then(function (data) {
                $scope.areas = data;
            });
        };

        $scope.loadCities = function (name) {
            var regionId = $scope.newStation.region ? $scope.newStation.region.id : null;
            var areaId = $scope.newStation.area ? $scope.newStation.area.id : null;
            $dict.places(regionId, areaId, null, name).then(function (data) {
                $scope.cities = data;
            });
        };

        $scope.loadPlaces = function (name) {
            var regionId = $scope.newStation.region ? $scope.newStation.region.id : null;
            var areaId = $scope.newStation.area ? $scope.newStation.area.id : null;
            var cityId = $scope.newStation.city ? $scope.newStation.city.id : null;
            $dict.places(regionId, areaId, cityId, name).then(function (data) {
                $scope.places = data;
            });
        };

        $scope.countryChanged = function () {
            $scope.newStation.region = null;
            $scope.newStation.area = null;
            $scope.newStation.city = null;
            $scope.newStation.place = null;
            loadStations();
        };

        $scope.regionChanged = function () {
            $scope.newStation.area = null;
            $scope.newStation.city = null;
            $scope.newStation.place = null;
            loadStations();
        };

        $scope.areaChanged = function () {
            $scope.newStation.city = null;
            $scope.newStation.place = null;
            loadStations();
        };

        $scope.cityChanged = function () {
            $scope.newStation.place = null;
            loadStations();
        };


        $scope.crateNewStation = function () {
            var areaId = $scope.newStation.area ? $scope.newStation.area.id : null;
            var cityId = $scope.newStation.city ? $scope.newStation.city.id : null;
            var placeId = $scope.newStation.place ? $scope.newStation.place.id : null;
            $requests.newStation({
                name: $scope.newStation.name,
                okato: $scope.newStation.okato,
                latitude: $scope.newStation.latitude,
                longitude: $scope.newStation.longitude,
                type: $scope.newStation.type.shortName,
                areaId: areaId,
                cityId: cityId,
                placeId: placeId,
            }).then(function () {
                $location.path('/requests');
            })
        }
    }
})();







