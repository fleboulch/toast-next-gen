(function() {
    'use strict';
    angular
        .module('crmisticApp')
        .factory('Intervenant', Intervenant);

    Intervenant.$inject = ['$resource'];

    function Intervenant ($resource) {
        var resourceUrl =  'api/intervenants/:id';

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
