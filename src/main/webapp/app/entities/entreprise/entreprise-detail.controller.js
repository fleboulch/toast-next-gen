(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('EntrepriseDetailController', EntrepriseDetailController);

    EntrepriseDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Entreprise', 'EntreprisePartenaire', 'Site', 'Groupe'];

    function EntrepriseDetailController($scope, $rootScope, $stateParams, previousState, entity, Entreprise, EntreprisePartenaire, Site, Groupe) {
        var vm = this;

        vm.entreprise = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('crmisticApp:entrepriseUpdate', function(event, result) {
            vm.entreprise = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
