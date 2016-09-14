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
    };
})();