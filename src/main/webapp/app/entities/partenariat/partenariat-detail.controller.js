(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('PartenariatDetailController', PartenariatDetailController);

    PartenariatDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Partenariat', 'Entreprise', 'Diplome'];

    function PartenariatDetailController($scope, $rootScope, $stateParams, previousState, entity, Partenariat, Entreprise, Diplome) {
        var vm = this;

        vm.partenariat = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('crmisticApp:partenariatUpdate', function(event, result) {
            vm.partenariat = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
