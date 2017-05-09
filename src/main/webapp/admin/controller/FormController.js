angular.module('MyApp').controller('FormController',FormController);

FormController.$inject = [
    'AdminService',
    'RoleService',
    '$uibModal',
    'LogoutService',
    '$localStorage',
    '$scope'

];

function FormController(AdminService, RoleService, $uibModal, LogoutService, $localStorage, $scope){

    var vm = this;

    vm.formData = {
        name:'',
        username:'',
        password:'',
        email:'',
        gender:'',
        dob:''
    }

    vm.profilePic = '';

    vm.roles = {};

    vm.successMsg = '';
    vm.errorMsg = '';

    vm.adminList = [];
    vm.roleList = [];
    vm.roleListMapping = {};

    vm.currentUserName = $localStorage.currentUserName;
    vm.currentUserRoles = $localStorage.currentUserRoles;

    vm.fetchAllAdmin = fetchAllAdmin;
    vm.fetchAllRole = fetchAllRole;
    vm.submit = submit;
    vm.resetFormData = resetFormData;
    vm.resetMsg = resetMsg;
    vm.deleteAdmin = deleteAdmin;
    vm.editAdmin = editAdmin;
    vm.detailAdmin = detailAdmin;
    vm.openProfilePicModal = openProfilePicModal;

    vm.logout = logout;

    fetchAllAdmin();
    fetchAllRole();

    function fetchAllAdmin(){

        AdminService.fetchAllAdmin()
            .then(
                function(response){
                    vm.adminList = response;
                }, function(error){
                    //error
                    console.log(error);
                }
            )
    }

    function fetchAllRole(){
        RoleService.fetchAllRole()
            .then(
                function(response){
                    for(var i=0; i<response.length;i++){
                        vm.roles[response[i].id] = false;
                        vm.roleListMapping[response[i].id] = response[i].name;
                    }
                    vm.roleList = response;
                }, function(error){
                    //error
                    console.log(error);
                }
            )
    }

    function submit(){
        vm.resetMsg();

        var roles=[];
        for(var property in vm.roles){
            if(vm.roles[property])
                roles.push(parseInt(property));
        }

        vm.dataToSubmit = {};
        vm.dataToSubmit.admin = vm.formData;
        vm.dataToSubmit.roles = roles;
        vm.dataToSubmit.profilePic = vm.profilePic;

        AdminService.submitAdmin(vm.dataToSubmit)
            .then(
                function(response){
                    vm.successMsg = "Admin added successfully.";
                    vm.resetFormData();
                    vm.roles = {};
                    vm.profilePic = '';
                    vm.fetchAllAdmin();
                },
                function(errMsg){
                    console.log(errMsg)
                    vm.errorMsg = errMsg.data.errorMsg;
                }
            );
    }

    function deleteAdmin(id){
        vm.resetMsg();

        AdminService.deleteAdmin(id)
            .then(
                function(response){
                    vm.successMsg = "Admin deleted successfully."
                    vm.fetchAllAdmin();
                },
                function(errMsg){
                    vm.errorMsg = errMsg.data;
                }
            );
    }

    function editAdmin(admin){
        vm.resetMsg();
        var adminCopy = angular.copy(admin);

        var modalInstance = $uibModal.open({
            size:'lg',
            templateUrl:'admin/modal/EditAdmin.jsp',
            controller: 'EditAdminController',
            resolve:{
                admin:function(){
                    return adminCopy;
                },
                roleList: function(){
                    return vm.roleList;
                }

            },
            backdrop: 'static',
            keyboard:true
        });

        modalInstance.result.then(
            function (dataToSubmit) {
                AdminService.editAdmin(dataToSubmit)
                    .then(
                        function(response){
                            vm.successMsg = "Admin edited successfully."
                            adminCopy = '';
                            vm.fetchAllAdmin();
                        },
                        function(errMsg){
                            vm.errorMsg = errMsg.data.errorMsg;
                        }
                    );
            },
            function () {
                console.log("Modal dismissed at: " + new Date());
            }
        );
    }

    function detailAdmin(admin){
        vm.resetMsg();
        var adminCopy = angular.copy(admin);

        var modalInstance = $uibModal.open({
            size:'lg',
            templateUrl:'admin/modal/DetailAdmin.jsp',
            controller: 'DetailAdminController',
            resolve:{
                admin:function(){
                    return adminCopy;
                },
                roleList: function(){
                    return vm.roleList;
                }

            },
            backdrop: 'static',
            keyboard:true
        });

        modalInstance.result.then(
            function (dataToSubmit) {
                AdminService.submitAdmin(dataToSubmit)
                    .then(
                        function(response){
                            adminCopy = '';
                        },
                        function(errMsg){
                            //error
                        }
                    );
            },
            function () {
                console.log("Modal dismissed at: " + new Date());
            }
        );
    }

    function openProfilePicModal(){

        var modalInstance = $uibModal.open({
            size: 'md',
            templateUrl: 'admin/modal/ProfilePic.jsp',
            controller: 'ProfilePicController'
        });

        modalInstance.result
            .then(
                function (croppedImage) {
                    vm.profilePic = croppedImage;
                }
            );
    }

    function logout(){
        LogoutService.logout();
    }

    function resetFormData () {
        vm.formData = {
            name:'',
            username:'',
            password:'',
            email:'',
            gender:'',
            dob:''
        }
    }

    function resetMsg () {
        vm.successMsg = '';
        vm.errorMsg = '';
    }
}