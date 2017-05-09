angular.module('MyApp').controller('ProfilePicController', ProfilePicController);

ProfilePicController.$inject = [
    '$scope',
    '$uibModalInstance'
];

function ProfilePicController($scope, $uibModalInstance ) {

    $scope.hello = 'hello';

    $scope.myImage='';
    $scope.myCroppedImage='';

    $scope.ok = function () {
        $uibModalInstance.close($scope.myCroppedImage);
    };

    //FUNCTION THAT HANDLES THE CANCEL OPERATION FOR THE UPDATE MODAL
    $scope.cancel = function () {
//        $localStorage.tempImage = vm.tempImage;
        $uibModalInstance.dismiss('cancel');
    };


}