(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .factory('EntreprisePartenaireSearch', EntreprisePartenaireSearch);

    EntreprisePartenaireSearch.$inject = ['$resource'];

    function EntreprisePartenaireSearch($resource) {
        var resourceUrl =  'api/_search/entreprise-partenaires/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
