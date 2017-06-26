angular.module('MyApp').controller('LoginController',LoginController);

LoginController.$inject = [
    'LoginService',
    '$location',
    '$routeParams'
];

function LoginController(LoginService, $location, $routeParams){
    vm = this;

    vm.loginData = {
        username:'',
        password:''
    }

    vm.errorMsg = '';

    vm.login = login;

    function login(){
        vm.errorMsg = '';
        LoginService.login(vm.loginData)
            .then(
                function(data){
                    if($routeParams.next != undefined && $routeParams.next != '') {
                        $location.path(decodeURIComponent($routeParams.next)).search('next', null);
                    }else{
                        $location.path('/admin');
                    }
                },
                function(errMsg){
                    vm.errorMsg = "Invalid username/password!";
                }
            );
    }
}