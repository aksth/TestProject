angular.module('MyApp',['ngAnimate', 'ui.bootstrap','ngRoute','ngStorage', 'ngImgCrop']);

angular.module('MyApp').config(['$routeProvider', function ($routeProvider) {

    $routeProvider
    //route to login page
        .when('/login', {
            controller: 'LoginController as loginCtrl',
            templateUrl: 'login/login.jsp',
            resolve:{
                currentUser:['AuthService', function(AuthService){
                    return AuthService.getUser()
                        .then(function(response){
                            throw "authenticationError"
                        },function(response){
                            return response;
                        })
                }]
            }
        })
        .when('/admin', {
            controller: 'FormController as formCtrl',
            templateUrl: 'admin/admin.jsp',
            resolve:{
                currentUser:['AuthService', function(AuthService){
                    return AuthService.getUser();
                }]
            }
        })
        .when('/test',{
            controller: 'TestController as testCtrl',
            templateUrl: 'test/test.jsp',
            resolve:{
                currentUser:['AuthService', function(AuthService){
                    return AuthService.getUser()
                        .then(function(user){
                            if(user.roles.indexOf(3) == -1){
                                throw "authorizationError";
                            }else{
                                return user;
                            }
                        });
                }]
            }
        })
        .when('/unauthorized',{
            controller: 'UnauthorizedController as unauthorizedCtrl',
            templateUrl: 'unauthorized/unauthorized.jsp',
        })
        .otherwise({redirectTo: '/login'});

}]);

//run function for the application
angular.module('MyApp').run(['$rootScope', '$localStorage', '$location', function ($rootScope, $localStorage, $location) {

    $rootScope.$on('$routeChangeError',function(angularEvent, current, previous, rejection){
        if(rejection.status == 401) {
            $location.path('/login').search({next:current.$$route.originalPath});
        } else if(rejection == "authorizationError"){
            $location.path('/unauthorized');
        } else if(rejection == "authenticationError"){
            $location.path('/admin').search('next', null);
        }

    });

}]);