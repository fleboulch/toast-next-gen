(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('EntreprisePartenaireDetailController', EntreprisePartenaireDetailController);

    EntreprisePartenaireDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'EntreprisePartenaire', 'Entreprise', 'Diplome'];

    function EntreprisePartenaireDetailController($scope, $rootScope, $stateParams, previousState, entity, EntreprisePartenaire, Entreprise, Diplome) {
        var vm = this;

        vm.entreprisePartenaire = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('crmisticApp:entreprisePartenaireUpdate', function(event, result) {
            vm.entreprisePartenaire = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
