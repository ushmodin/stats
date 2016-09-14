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
        $scope.loadData = loadData;

        loadData();
        loadHosts();



        function loadHosts() {
            $http.post('api/common/hosts').then(function (response) {
                $scope.hosts = response.data.data;
            })
        };
        function loadData() {
            $http.post('api/requests/list', $scope.filter, {
                params: {
                    page: $scope.currentPage - 1,
                    size: $scope.pageSize
                }
            }).then(function (response) {
                $scope.data = response.data.data;
            })
        };
    };
})();