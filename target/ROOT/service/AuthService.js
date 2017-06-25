angular.module('MyApp').factory('AuthService', AuthService);

AuthService.$inject =['HttpService'];

function AuthService(HttpService){
    var vm = this;

    vm.resourceUrl = '/auth';

    return {
        getUser: function(){
            return HttpService.get(vm.resourceUrl);
                // .then(
                //     function(response){
                //         return response.data;
                //     },
                //     function(error){
                //         return $q.reject(error);
                //     }
                // )
        }
    };

}