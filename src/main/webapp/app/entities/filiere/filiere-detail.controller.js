(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('FiliereDetailController', FiliereDetailController);

    FiliereDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Filiere', 'Diplome', 'Promotion'];

    function FiliereDetailController($scope, $rootScope, $stateParams, previousState, entity, Filiere, Diplome, Promotion) {
        var vm = this;

        vm.filiere = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('crmisticApp:filiereUpdate', function(event, result) {
            vm.filiere = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
