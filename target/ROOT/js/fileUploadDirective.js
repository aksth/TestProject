angular.module('MyApp').directive('upload', function(){
    return{

        scope:{
            myImage:"="
        },

        link: function(scope, elem, attrs){
            elem.on("change" ,function(evt){
                var file=evt.currentTarget.files[0];
                var reader = new FileReader();
                reader.onload = function (evt) {
                    scope.$apply(function(scope){
                        scope.myImage=evt.target.result;
                    });
                };
                reader.readAsDataURL(file);

            });
        }
    }
});