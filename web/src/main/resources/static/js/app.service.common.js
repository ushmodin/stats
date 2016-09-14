(function () {
    angular
        .module('app')
        .service('$dict', ['$http', '$q', DictService]);

    function DictService($http, $q) {
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
    };
})();