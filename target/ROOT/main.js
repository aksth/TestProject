angular.module('MyApp',['ngAnimate', 'ui.bootstrap','ngRoute','ngStorage', 'ngImgCrop']);

angular.module('MyApp').config(['$routeProvider', function ($routeProvider) {

    $routeProvider
    //route to login page
        .when('/login', {
            controller: 'LoginController as loginCtrl',
            templateUrl: 'login/login.jsp',
            resolve:{
                user:['AuthService','$q', function(AuthService, $q){
                    var deferred = $q.defer();
                    return AuthService.getUser()
                        .then(function(response){
                            return $q.reject(response);
                        },function(response){
                            return $q.resolve(response);
                        })
                }]
            }
        })
        .when('/admin', {
            controller: 'FormController as formCtrl',
            templateUrl: 'admin/admin.jsp',
            resolve:{
                user:['AuthService', function(AuthService){
                    return AuthService.getUser();
                }]
            }
        })
        .when('/test',{
            controller: 'TestController as testCtrl',
            templateUrl: 'test/test.jsp',
            resolve:{
                user:['AuthService', function(AuthService){
                    return AuthService.getUser();
                }]
            }
        })
        .otherwise({redirectTo: '/login'});

}]);

//run function for the application
angular.module('MyApp').run(function ($rootScope, $localStorage, $location) {

    //checking if the user is logged in or not.
    // if (angular.isUndefined($localStorage.loggedIn)) {
    //     $rootScope.isLoggedIn = false;
    // } else {
    //     $rootScope.isLoggedIn = true;
    // }

    // $rootScope.$on('$locationChangeStart', function (event, next, current) {
    //     if ($location.path() === '/test') {
    //         $location.path('/test');
    //     } else if ($location.path() === '/login' && $localStorage.isLoggedIn) {
    //         $location.path('/admin');
    //     } else if ($localStorage.isLoggedIn === undefined) {
    //         $location.path('/login');
    //     } else if ($localStorage.isLoggedIn === false) {
    //         $location.path('/login');
    //     }
    // });

    $rootScope.$on('$routeChangeError',function(angularEvent, current, previous, rejection){
        if(rejection.status == 401) {
            $location.path('/login').search({next:current.$$route.originalPath});
        }else if(rejection.id){
            $location.path('/admin');
        }

    });

});