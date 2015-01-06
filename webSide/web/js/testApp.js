(function () {
    var app = angular.module('noteKeeper', ['ngRoute']);

    app.config(
        ['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {

            $routeProvider.
                when('/dash', {
                    templateUrl: '/views/dash.html',
                    controller: DashController,
                    controllerAs: 'dash'
                }).
                otherwise({
                    templateUrl: '/views/index.html',
                    controller: IndexController,
                    controllerAs: 'index'
                });

            $locationProvider.html5Mode(true);
        }]
    );
});