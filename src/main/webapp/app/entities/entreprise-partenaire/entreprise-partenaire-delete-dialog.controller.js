(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('EntreprisePartenaireDeleteController',EntreprisePartenaireDeleteController);

    EntreprisePartenaireDeleteController.$inject = ['$uibModalInstance', 'entity', 'EntreprisePartenaire'];

    function EntreprisePartenaireDeleteController($uibModalInstance, entity, EntreprisePartenaire) {
        var vm = this;

        vm.entreprisePartenaire = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            EntreprisePartenaire.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
