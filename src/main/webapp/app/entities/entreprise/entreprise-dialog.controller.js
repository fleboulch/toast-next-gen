(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('EntrepriseDialogController', EntrepriseDialogController);

    EntrepriseDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Entreprise', 'EntreprisePartenaire', 'Site', 'Groupe'];

    function EntrepriseDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Entreprise, EntreprisePartenaire, Site, Groupe) {
        var vm = this;

        vm.entreprise = entity;
        vm.clear = clear;
        vm.save = save;
        vm.entreprisepartenaires = EntreprisePartenaire.query();
        vm.sites = Site.query();
        vm.groupes = Groupe.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.entreprise.id !== null) {
                Entreprise.update(vm.entreprise, onSaveSuccess, onSaveError);
            } else {
                Entreprise.save(vm.entreprise, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('crmisticApp:entrepriseUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
