(function () {
    angular
        .module('app')
        .controller('RequestsController', ['$scope', '$http', RequestsController]);

    function RequestsController($scope, $http) {
        $scope.data = {};
        $scope.hosts = [];
        $scope.currentPage = 1;
        $scope.pageSize = 20;
        $scope.filter = {};

        $scope.loadData();
        $scope.loadHosts();

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
    };
})();