angular.module('MyApp').factory('RoleService', RoleService);

RoleService.$inject = ['HttpService'];

function RoleService(HttpService){

    var vm = this;

    vm.resourceUrl = '/roles/';

    return {
        fetchAllRole: function(){
            return HttpService.get(vm.resourceUrl + 'fetchAll');
        }
    };

}