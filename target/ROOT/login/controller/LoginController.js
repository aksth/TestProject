angular.module('MyApp').controller('LoginController',LoginController);

LoginController.$inject = [
    'LoginService',
    '$localStorage',
    '$location'
];

function LoginController(LoginService, $localStorage, $location){
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
                    $localStorage.isLoggedIn = true;
                    $localStorage.currentUser = data.id;
                    $localStorage.currentUserName = data.name;
                    $localStorage.currentUserUsername = data.username;
                    $localStorage.currentUserRoles = data.roles;
                    $location.path('/admin');
                },
                function(errMsg){
                    vm.errorMsg = "Invalid username/password!";
                }
            );
    }
}