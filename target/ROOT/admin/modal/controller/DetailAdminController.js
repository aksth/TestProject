angular.module('MyApp').controller('DetailAdminController', DetailAdminController);

EditAdminController.$inject = [
    '$scope',
    '$uibModalInstance',
    'admin',
    'roleList'
];

function DetailAdminController($scope, $uibModalInstance, admin, roleList) {

    var vm = $scope;

    vm.admin = admin;
    vm.roleList = roleList;

    vm.adminRoleIdList = [];
    for(var i=0; i<vm.admin.roles.length; i++){
        vm.adminRoleIdList.push(vm.admin.roles[i].id);
    }

    //all role list - for displaying empty checkboxes
    vm.roles ={};
    for(var i=0; i<roleList.length;i++){
        vm.roles[roleList[i].id] = false;
    }

    //check the required boxes
    for(var property in vm.roles){
        if(vm.adminRoleIdList.indexOf(parseInt(property)) != -1){
            vm.roles[property] = true;
        }
    }

    $scope.ok = function () {

        var roles=[];
        for(var property in vm.roles){
            if(vm.roles[property])
                roles.push(parseInt(property));
        }

        vm.dataToSubmit = {
            admin:{},
            roles:{}
        };

        vm.dataToSubmit.admin.id = vm.admin.admin.id;
        vm.dataToSubmit.admin.name = vm.admin.admin.name;
        vm.dataToSubmit.admin.username = vm.admin.admin.username;
        vm.dataToSubmit.admin.password = vm.admin.admin.password;
        vm.dataToSubmit.admin.email = vm.admin.admin.email;
        vm.dataToSubmit.admin.gender = vm.admin.admin.gender;
        vm.dataToSubmit.admin.dob = vm.admin.admin.dob;

        vm.dataToSubmit.roles = roles;

        $uibModalInstance.close(vm.dataToSubmit);
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };

}

