angular.module('MyApp').factory('HttpService',HttpService);

HttpService.$inject =['$http', '$q'];

function HttpService($http, $q){
    var vm = this;

    return{
        get: function(resourceURI){
            return $http.get(resourceURI)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(error){
                        return $q.reject(error);
                    }
                )
        },

        post: function(resourceURI, data){
            return $http.post(resourceURI, data)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(error){
                        return $q.reject(error);
                    }
                )
        }
    }
}