(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('MaitreStageDialogController', MaitreStageDialogController);

    MaitreStageDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'MaitreStage', 'ConventionStage'];

    function MaitreStageDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, MaitreStage, ConventionStage) {
        var vm = this;

        vm.maitreStage = entity;
        vm.clear = clear;
        vm.save = save;
        vm.conventionstages = ConventionStage.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.maitreStage.id !== null) {
                MaitreStage.update(vm.maitreStage, onSaveSuccess, onSaveError);
            } else {
                MaitreStage.save(vm.maitreStage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('crmisticApp:maitreStageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
