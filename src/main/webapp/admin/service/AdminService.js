angular.module('MyApp').factory('AdminService', AdminService);

AdminService.$inject = ['HttpService'];

function AdminService(HttpService){

    var vm = this;

    vm.resourceUrl = '/admin/';

    return {
        fetchAllAdmin: function(){
            return HttpService.get(vm.resourceUrl + 'fetchAll');
        },
        submitAdmin: function(data){
            data.edit = false;
            return HttpService.post(vm.resourceUrl + 'submit', data);
        },
        editAdmin: function(data){
            data.edit = true;
            return HttpService.post(vm.resourceUrl + 'submit', data);
        },
        deleteAdmin:function(adminId){
            return HttpService.get(vm.resourceUrl + 'delete'+'?id='+adminId);
        },
        fecthAdminAndRoleByAdminId:function(adminId){
            return HttpService.get(vm.resourceUrl + 'edit'+'?id='+adminId);
        }

    };

}