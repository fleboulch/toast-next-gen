(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('IntervenantDetailController', IntervenantDetailController);

    IntervenantDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Intervenant', 'Diplome', 'Entreprise'];

    function IntervenantDetailController($scope, $rootScope, $stateParams, previousState, entity, Intervenant, Diplome, Entreprise) {
        var vm = this;

        vm.intervenant = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('crmisticApp:intervenantUpdate', function(event, result) {
            vm.intervenant = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
