(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('MaitreStageDeleteController',MaitreStageDeleteController);

    MaitreStageDeleteController.$inject = ['$uibModalInstance', 'entity', 'MaitreStage'];

    function MaitreStageDeleteController($uibModalInstance, entity, MaitreStage) {
        var vm = this;

        vm.maitreStage = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            MaitreStage.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
