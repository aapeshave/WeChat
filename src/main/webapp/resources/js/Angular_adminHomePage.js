/**
 * 
 */
angular.module('httpController', [])
.controller('FetchController', ['$scope', '$http',
  function($scope, $http) {
    $scope.method = 'GET';
    $scope.url = 'getOnlineUserInfo.htm';

    $scope.fetch = function() {
      $scope.code = null;
      $scope.response = null;

      $http({method: $scope.method, url: $scope.url}).
        then(function(response) {
          $scope.status = response.status;
          $scope.data = response.data;
        }, function(response) {
          $scope.data = response.data || "Request failed";
          $scope.status = response.status;
      });
    };

    $scope.updateModel = function(method, url) {
      $scope.method = method;
      $scope.url = url;
    };
  }]);
