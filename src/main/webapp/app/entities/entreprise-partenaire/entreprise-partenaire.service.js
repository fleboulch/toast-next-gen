(function() {
    'use strict';
    angular
        .module('crmisticApp')
        .factory('EntreprisePartenaire', EntreprisePartenaire);

    EntreprisePartenaire.$inject = ['$resource', 'DateUtils'];

    function EntreprisePartenaire ($resource, DateUtils) {
        var resourceUrl =  'api/entreprise-partenaires/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dateDebut = DateUtils.convertDateTimeFromServer(data.dateDebut);
                        data.dateFin = DateUtils.convertDateTimeFromServer(data.dateFin);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
