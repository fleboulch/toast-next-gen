(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .factory('IntervenantSearch', IntervenantSearch);

    IntervenantSearch.$inject = ['$resource'];

    function IntervenantSearch($resource) {
        var resourceUrl =  'api/_search/intervenants/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
