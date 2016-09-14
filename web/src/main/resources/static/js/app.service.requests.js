(function () {
    angular
        .module('app')
        .service('$requests', ['$http', '$q', RequestsService]);

    function RequestsService($http, $q) {
        var self = this;

        self.getRequest = function (id) {
            return $q(function (accept, reject) {
                $http.get('api/request/' + id).then(function (response) {
                    accept(response.data.data);
                })
            });
        };

        self.link = function (requestId, stationId) {
            return $q(function (accept, reject) {
                $http.post('api/request/link',{
                    requestId: requestId,
                    stationId: stationId
                }).then(function (response) {
                    accept();
                });
            });
        };

        self.newStation = function (model) {
            return $q(function (accept, reject) {
                $http.post('api/request/newStation', model).then(function (response) {
                    accept();
                });
            })
        }
    };
})();