(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('ConventionStageDetailController', ConventionStageDetailController);

    ConventionStageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ConventionStage', 'Site', 'Tuteur', 'MaitreStage', 'Etudiant'];

    function ConventionStageDetailController($scope, $rootScope, $stateParams, previousState, entity, ConventionStage, Site, Tuteur, MaitreStage, Etudiant) {
        var vm = this;

        vm.conventionStage = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('crmisticApp:conventionStageUpdate', function(event, result) {
            vm.conventionStage = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
