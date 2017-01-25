(function() {
    'use strict';
    angular
        .module('crmisticApp')
        .factory('MaitreStage', MaitreStage);

    MaitreStage.$inject = ['$resource'];

    function MaitreStage ($resource) {
        var resourceUrl =  'api/maitre-stages/:id';

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
