angular.module('MyApp').factory('LoginService', LoginService);

LoginService.$inject = ['HttpService'];

function LoginService(HttpService){

    var vm = this;

    vm.resourceUrl = '/';

    return {
        login: function(data){
            return HttpService.post(vm.resourceUrl + 'loginUser', data);
        }
    };

}