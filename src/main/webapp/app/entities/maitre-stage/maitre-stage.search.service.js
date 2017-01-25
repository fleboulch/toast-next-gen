(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .factory('MaitreStageSearch', MaitreStageSearch);

    MaitreStageSearch.$inject = ['$resource'];

    function MaitreStageSearch($resource) {
        var resourceUrl =  'api/_search/maitre-stages/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
