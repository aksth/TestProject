angular.module('MyApp',['ngAnimate', 'ui.bootstrap','ngRoute','ngStorage', 'ngImgCrop']);

angular.module('MyApp').config(function ($routeProvider) {

    $routeProvider
    //route to login page
        .when('/login', {
            controller: 'LoginController as loginCtrl',
            templateUrl: 'login/login.jsp'
        })
        .when('/admin', {
            controller: 'FormController as formCtrl',
            templateUrl: 'admin/admin.jsp'
        })

        .otherwise({redirectTo: '/login'});

});

//run function for the application
angular.module('MyApp').run(function ($rootScope, $localStorage, $location) {

    //checking if the user is logged in or not.
    // if (angular.isUndefined($localStorage.loggedIn)) {
    //     $rootScope.isLoggedIn = false;
    // } else {
    //     $rootScope.isLoggedIn = true;
    // }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        if ($location.path() === '/login' && $localStorage.isLoggedIn) {
            $location.path('/admin');
        } else if ($localStorage.isLoggedIn === undefined) {
            $location.path('/login');
        } else if ($localStorage.isLoggedIn === false) {
            $location.path('/login');
        }
    });

});