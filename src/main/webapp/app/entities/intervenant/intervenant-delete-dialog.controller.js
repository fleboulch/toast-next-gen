(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('IntervenantDeleteController',IntervenantDeleteController);

    IntervenantDeleteController.$inject = ['$uibModalInstance', 'entity', 'Intervenant'];

    function IntervenantDeleteController($uibModalInstance, entity, Intervenant) {
        var vm = this;

        vm.intervenant = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Intervenant.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
