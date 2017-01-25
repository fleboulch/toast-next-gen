(function() {
    'use strict';
    angular
        .module('crmisticApp')
        .factory('ConventionStage', ConventionStage);

    ConventionStage.$inject = ['$resource'];

    function ConventionStage ($resource) {
        var resourceUrl =  'api/convention-stages/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
