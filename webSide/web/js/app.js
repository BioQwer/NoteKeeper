(function () {
    var app = angular.module('noteKeeper', []);


    app.directive("mainPage", function () {
        return {
            restrict: "A",
            templateUrl: "/views/index.html"

        };
    });

    app.directive("loginPage", function () {
        return {
            restrict: "A",
            templateUrl: "/views/login.html"
        };
    });

    app.directive("dashPage", function () {
        return {
            restrict: "A",
            templateUrl: "/views/dash.html"
        };
    });

    app.controller("PageCtrl", function () {

        this.doShow = function (isShow) {
            return this.state === isShow;
        };

        this.setPage = function (newPage) {
            this.state = newPage;
            loginCtrl.isLogin();
        };
    });

    app.controller("LoginController", ['$http', '$scope', function ($http, $scope) {
        var user = this;
        user = {};
        var errorMessage = this;
        errorMessage = '';
        $scope.logged = '';

        this.getError = function () {
            return errorMessage;
        };

        this.isLogin = function () {

            $http.get('/api/user')
                .success(function () {
                    $scope.logged = true;
                })
                .error(function () {
                    $scope.logged = false;
                });

            console.log("logged = " + logged);
        };


        this.doLogin = function (user, page) {
            console.log("start do Login");
            $http({
                method: 'POST',
                url: '/api/j_spring_security_check',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data: {
                    j_username: user.login,
                    j_password: user.pass
                }
            }).success(function (data) {
                console.log(data);
                this.user = data;
                console.log(this.user);
                page.setPage('dash');
            }).error(function (data, status) {
                    console.log("status = " + status);
                    if (status === 403)
                        errorMessage = 'Password Invalid';
                    else if (status === 404)
                        errorMessage = "User not Found";
                    console.log(errorMessage);
                }
            );
            console.log("end do Login");
        }
    }]);


})();