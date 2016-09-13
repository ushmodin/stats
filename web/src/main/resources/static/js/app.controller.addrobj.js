(function () {
    angular
        .module('app')
        .controller('AddrobjController', ['$scope', '$http', AddrobjController1]);


    function AddrobjController1($scope, $http) {
        $scope.addrobjs = {};
        $scope.currentPage = 1;
        $scope.pageSize = 20;
        $scope.filter = {};
        $scope.loadAddrobjs = loadAddrobjs;

        $scope.loadAddrobjs();

        function loadAddrobjs() {
            $http.post('api/addrobj/list?page=' + ($scope.currentPage - 1) + '&size=' + ($scope.pageSize), $scope.filter).then(function (response) {
                $scope.addrobjs = response.data.data;
            })
        };
    }
})();