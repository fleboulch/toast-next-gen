(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('MaitreStageDetailController', MaitreStageDetailController);

    MaitreStageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'MaitreStage', 'ConventionStage'];

    function MaitreStageDetailController($scope, $rootScope, $stateParams, previousState, entity, MaitreStage, ConventionStage) {
        var vm = this;

        vm.maitreStage = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('crmisticApp:maitreStageUpdate', function(event, result) {
            vm.maitreStage = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
