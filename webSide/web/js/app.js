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

    app.directive("singUp", function () {
        return {
            restrict: "A",
            templateUrl: "/views/singUp.html"
        };
    });

    app.controller("PageCtrl", function () {

        this.doShow = function (isShow) {
            return this.state === isShow;
        };

        this.setPage = function (newPage) {
            this.state = newPage;
        };
    });

    app.controller("LoginController", ['$http', '$scope', function ($http, $scope) {
        var user = this;
        user = {
            emailVal: undefined,
            login: undefined,
            password: undefined
        };
        var errorMessage = this;
        errorMessage = '';

        this.getError = function () {
            return errorMessage;
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
                    j_password: user.password
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
        };

        var singUpErrors = this;
        singUpErrors = [];

        this.getSingUpErrors = function () {
            return singUpErrors;
        };
        this.doSingUp = function (user, page) {
            console.log("start do SingUp");
            $http({
                method: 'POST',
                url: '/api/user/singIn',
                data: {
                    login: user.login,
                    password: user.password,
                    email: user.emailVal
                }
            }).success(function (data) {
                console.log(data);
                this.user = data;
                console.log(this.user);
                page.setPage('dash');
            }).error(function (data, status) {
                    console.log("status = " + status);
                    if (data.length === undefined)
                        singUpErrors.push(data);
                    else
                        singUpErrors = data;
                    console.log("singUpErrors = " + singUpErrors.length);
                    console.log(singUpErrors);
                }
            );
            console.log("end do SingUp");
        }
    }]);


})();