angular.module('MyApp').factory('LogoutService', LogoutService);

LogoutService.$inject = ['$localStorage', '$location','HttpService'];

function LogoutService($localStorage, $location, HttpService){

    var vm = this;

    return {
        logout: function(){

            HttpService.get('/logout')
                .then(
                    function(response){
                        $localStorage.$reset();
                        $localStorage.isLoggedIn = false;
                        $location.path('/login');
                    },
                    function(response){

                    });


        }
    };
}