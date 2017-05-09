angular.module('MyApp').factory('LogoutService', LogoutService);

LogoutService.$inject = ['$localStorage', '$location'];

function LogoutService($localStorage, $location){

    var vm = this;

    return {
        logout: function(){
            $localStorage.$reset();
            $localStorage.isLoggedIn = false;
            $location.path('/login');
        }
    };
}