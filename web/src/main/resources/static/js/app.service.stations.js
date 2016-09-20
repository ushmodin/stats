(function () {
    angular
        .module('app')
        .service('$stations', ['$http', '$q', StationsService]);

    function StationsService($http, $q) {
        var self = this;

        self.getStations = function (filter, page, pageSize) {
            return $q(function (accept, reject) {
                $http.post('api/request/stations', filter ,{
                    params: {
                        page: page,
                        size: pageSize,
                        sort: 'name,ASC'
                    }
                }).then(function (response) {
                    accept(response.data.data);
                });
            });
        }

        self.searchLike = function (requestId, page, pageSize) {
            return $q(function (accept, reject) {
                $http.get('api/request/' + requestId + '/stations/like', {
                    params: {
                        page: page,
                        size: pageSize,
                    }
                }).then(function (response) {
                    accept(response.data.data);
                })
            })
        }
    };
})();