(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('IntervenantDialogController', IntervenantDialogController);

    IntervenantDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Intervenant', 'Diplome', 'Entreprise'];

    function IntervenantDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Intervenant, Diplome, Entreprise) {
        var vm = this;

        vm.intervenant = entity;
        vm.clear = clear;
        vm.save = save;
        vm.diplomes = Diplome.query();
        vm.entreprises = Entreprise.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.intervenant.id !== null) {
                Intervenant.update(vm.intervenant, onSaveSuccess, onSaveError);
            } else {
                Intervenant.save(vm.intervenant, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('crmisticApp:intervenantUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
