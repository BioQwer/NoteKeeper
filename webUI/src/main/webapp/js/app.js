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

    app.directive("locator", function () {
        return {
            restrict: "A",
            templateUrl: "/views/locator.html"
        };
    });

    app.directive("accountPage", function () {
        return {
            restrict: "A",
            templateUrl: "/views/account.html"
        };
    });

    app.directive("header", function () {
        return {
            restrict: "A",
            templateUrl: "/views/header.html"
        };
    });

    app.directive("note", function () {
        return {
            restrict: "A",
            templateUrl: "/views/note.html"
        };
    });

    app.directive("searchPage", function () {
        return {
            restrict: "A",
            templateUrl: "/views/searchPage.html"
        };
    });

    app.controller("SearchController", ['$http', function ($http) {
        var vm = this;
        vm.searchItem = '';
        vm.notes = [];

        this.getNotes = function () {
            return vm.notes;
        };

        this.setSearchItem = function (newSearchItem) {
            vm.searchItem = newSearchItem;
            if (vm.searchItem !== '')
                this.updateSearchNotes();
        };

        this.getSearchItem = function () {
            return vm.searchItem;
        };

        this.updateSearchNotes = function () {
            console.log("start do updateSearchNotes");
            vm.notes = [];
            $http({
                method: 'GET',
                url: '/api/user/search/' + vm.searchItem
            }).success(function (data) {
                vm.notes = data;
            }).error(function (data, status) {
                console.log("error" + status);
                console.log(data);
            });
            console.log("end do updateSearchNotes");
        };

        this.setForEdit = function (note, login) {
            login.setForEdit(note, login);
        };

        function getNoteById(id) {
            for (var i = 0; i < vm.notes.length; i++) {
                if (vm.notes[i].noteId === id)
                    return vm.notes[i];
            }
        }

        this.deleteNote = function (id, search) {
            console.log("start do deleteNote");
            $http({
                method: 'DELETE',
                url: '/api/user/note',
                data: getNoteById(id),
                headers: {'Content-Type': 'application/json'}
            }).success(function () {
                search.updateSearchNotes();
            });
            console.log("end do deleteNote");
        };

    }]);


    app.controller("PageCtrl", ['$http', function ($http) {

        this.doShow = function (isShow) {
            return this.state === isShow;
        };

        this.setPage = function (newPage) {
            this.state = newPage;
        };

        this.checkLogin = function (login, page) {
            console.log("start do checkLogin");
            $http({
                method: 'GET',
                url: '/api/user'
            }).success(function (data) {
                login.doLogin(data, page, login);
            }).error(function () {
                page.setPage('main');
            });
            console.log("end do checkLogin");
        };
    }]);

    app.controller("LoginController", ['$http', function ($http) {
        var user = this;
        user = {
            email: undefined,
            login: undefined,
            password: undefined
        };

        this.setUser = function (user) {
            this.user = user;
        };
        this.getUser = function () {
            return user;
        };

        var errorMessage = this;
        errorMessage = '';

        this.getError = function () {
            return errorMessage;
        };

        this.clearError = function () {
            errorMessage = '';
        };

        this.doLogin = function (user, page, loginCtrl) {
            console.log("start do Login");
            $http({
                method: 'POST',
                url: '/api/j_spring_security_check',
                params: {
                    "j_username": user.login,
                    "j_password": user.password
                }
            }).success(function (data) {
                console.log(data);
                this.user = data;
                loginCtrl.setUser(data);
                console.log(this.user);
                page.setPage('dash');
                loginCtrl.updateNotes();
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

        this.doSingUp = function (user, page, loginCtrl) {
            console.log("start do SingUp");
            $http({
                method: 'POST',
                url: '/api/user/singIn',
                data: {
                    login: user.login,
                    password: user.password,
                    email: user.email
                }
            }).success(function (data) {
                console.log(data);
                this.user = data;
                loginCtrl.setUser(data);
                console.log(this.user);
                loginCtrl.doLogin(data, page, loginCtrl);
                loginCtrl.confirmPassword = '';
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
        };

        var notes = this;
        notes = [];

        this.getNotes = function () {
            return notes;
        };

        this.updateNotes = function () {
            console.log("start do getNotes");
            $http({
                method: 'GET',
                url: '/api/user/notes'
            }).success(function (data) {
                notes = data;
            }).error(function (data, status) {
                console.log("error" + status);
                console.log(data);
            });
            console.log("end do getNotes");
        };

        function getNoteById(id) {
            for (var i = 0; i < notes.length; i++) {
                if (notes[i].noteId === id)
                    return notes[i];
            }
        }

        this.deleteNote = function (id, loginCtrl) {
            console.log("start do deleteNote");
            $http({
                method: 'DELETE',
                url: '/api/user/note',
                data: getNoteById(id),
                headers: {'Content-Type': 'application/json'}
            }).success(function () {
                loginCtrl.updateNotes();
            });
            console.log("end do deleteNote");
        };

        this.logout = function (page, login) {
            console.log("start do logout");
            $http({
                method: 'GET',
                url: '/api/logout'
            }).error(function () {
                login.setUser({});
                notes = [];
                page.setPage('main');
            });
            console.log("end do logout");
        };

        var editNote = this;
        editNote = {};

        this.setForEdit = function (note) {
            editNote = note;
        };

        this.getForEdit = function () {
            return editNote;
        };

        this.saveEdit = function (loginCtrl) {
            console.log("start do saveEdit");
            $http({
                method: 'POST',
                url: '/api/user/note',
                data: editNote
            }).success(function (data) {
                console.log(data);
                editNote = {};
                loginCtrl.updateNotes();
            }).error(function (data, status) {
                    console.log("status = " + status);
                    console.log("saveEdit Errors = " + data);
                    console.log(data);
                }
            );
            console.log("end do saveEdit");
        };

        this.addNewNote = function (loginCtrl) {
            console.log("start do saveEdit");
            editNote.userByUserId = loginCtrl.user;
            $http({
                method: 'POST',
                url: '/api/user/newNote',
                data: editNote
            }).success(function (data) {
                console.log(data);
                editNote = {};
                loginCtrl.updateNotes();
            }).error(function (data, status) {
                    console.log("status = " + status);
                    console.log("saveEdit Errors = " + data);
                    console.log(data);
                }
            );
            console.log("end do saveEdit");
        };

        var editUserErrors = this;
        editUserErrors = [];

        this.getEditUserErrors = function () {
            return editUserErrors;
        };

        this.doEditUser = function (page, loginCtrl) {
            console.log("start do EditUser");
            console.log("before");
            console.log(loginCtrl.user);
            $http({
                method: 'POST',
                url: '/api/user',
                data: loginCtrl.user
            }).success(function (data) {
                console.log("after");
                loginCtrl.setUser(data);
                console.log(loginCtrl.user);
                loginCtrl.confirmPassword = '';
                page.setPage('dash');
            }).error(function (data, status) {
                    console.log("status = " + status);
                    console.log("saveEdit Errors = " + data);
                    if (data.length === undefined)
                        editUserErrors.push(data);
                    else
                        editUserErrors = data;
                    console.log(data);
                }
            );
            console.log("end do EditUser");
        };

        var userMonitoring = this;
        userMonitoring = [];

        this.getUserMonitoring = function () {
            return userMonitoring;
        };

        this.doGetUserMonitoring = function () {
            console.log("start do doGetUserMonitoring");
            $http({
                method: 'GET',
                url: '/api/monitoring'
            }).success(function (data) {
                userMonitoring = data;
            }).error(function (data, status) {
                console.log("error" + status);
                console.log(data);
            });
            console.log("end do doGetUserMonitoring");
        };
    }]);

})();