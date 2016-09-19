(function () {
    'use strict';
    angular
        .module('app')
        .controller('RequestController', ['$scope', '$requests', '$routeParams', RequestController])
        .controller('RequestExistController', ['$scope', '$dict', '$routeParams', '$location', '$requests', '$stations', '$uibModal',  RequestExistController])
        .controller('RequestNewController', ['$scope', '$dict', '$routeParams', '$location', '$requests', '$stations', '$uibModal',  RequestNewController]);



    function RequestController($scope, $requests, $routeParams) {
        $scope.request = {};
        $requests.getRequest($routeParams.id).then(function (data) {
            $scope.request = data;
        });
    };

    function RequestExistController($scope, $dict, $routeParams, $location, $requests, $stations, $uibModal) {
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
            $dict.cities(regionId, areaId, name).then(function (data) {
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

        $scope.regionChanged = function (region) {
            $scope.stationFilter.country = region.country;
            $scope.stationFilter.area = null;
            $scope.stationFilter.city = null;
            $scope.stationFilter.place = null;
            loadStations();
        };

        $scope.areaChanged = function (area) {
            $scope.stationFilter.country = area.region.country;
            $scope.stationFilter.region = area.region;
            $scope.stationFilter.city = null;
            $scope.stationFilter.place = null;
            loadStations();
        };

        $scope.cityChanged = function (city) {
            $scope.stationFilter.country = city.region.country;
            $scope.stationFilter.region = city.region;
            $scope.stationFilter.area = city.area;
            $scope.stationFilter.place = null;
            loadStations();
        };

        $scope.placeChanged = function (place) {
            $scope.stationFilter.country = place.region.country;
            $scope.stationFilter.region = place.region;
            $scope.stationFilter.area = place.area;
            $scope.stationFilter.city = place.city;
            loadStations();
        };

        $scope.link = function (station) {
            $scope.station = station;

            $uibModal.open({
                templateUrl: 'partial/link.exist.html',
                controller: ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {
                    $scope.linkOk = function () {
                        $uibModalInstance.close();
                    };
                    $scope.linkCancel = function () {
                        $uibModalInstance.dismiss('cancel');
                    };
                }],
                scope: $scope,
                size: 'lg',
            }).result.then(function () {
                $requests.link($scope.request.id, station.id).then(function () {
                    $location.path('/requests');
                });
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

    function RequestNewController($scope, $dict, $routeParams, $location, $requests, $stations, $uibModal) {
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
            $dict.cities(regionId, areaId, name).then(function (data) {
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
        };

        $scope.regionChanged = function (region) {
            $scope.newStation.country = region.country;
            $scope.newStation.area = null;
            $scope.newStation.city = null;
            $scope.newStation.place = null;
        };

        $scope.areaChanged = function (area) {
            $scope.newStation.country = area.region.country;
            $scope.region = area.region;
            $scope.newStation.city = null;
            $scope.newStation.place = null;
        };

        $scope.cityChanged = function (city) {
            $scope.newStation.country = city.region.country;
            $scope.newStation.region = city.region;
            $scope.newStation.area = city.area;
            $scope.newStation.place = null;
        };

        $scope.placeChanged = function (place) {
            $scope.newStation.country = place.region.country;
            $scope.newStation.region = place.region;
            $scope.newStation.area = place.area;
            $scope.newStation.city = place.city;
        };


        $scope.crateNewStation = function () {
            $uibModal.open({
                templateUrl: 'partial/link.new.html',
                controller: ['$uibModalInstance', function ($uibModalInstance) {
                    $scope.createOk = function () {
                        $uibModalInstance.close();
                    };
                    $scope.createCancel = function () {
                        $uibModalInstance.dismiss('cancel');
                    };
                }],
                scope: $scope,
                size: 'lg'
            }).result.then(function () {
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
                    requestId: $routeParams.id
                }).then(function () {
                    $location.path('/requests');
                });
            });



        }
    }
})();







