(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('EntreprisePartenaireDialogController', EntreprisePartenaireDialogController);

    EntreprisePartenaireDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'EntreprisePartenaire', 'Entreprise', 'Diplome'];

    function EntreprisePartenaireDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, EntreprisePartenaire, Entreprise, Diplome) {
        var vm = this;

        vm.entreprisePartenaire = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.entreprises = Entreprise.query();
        vm.diplomes = Diplome.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.entreprisePartenaire.id !== null) {
                EntreprisePartenaire.update(vm.entreprisePartenaire, onSaveSuccess, onSaveError);
            } else {
                EntreprisePartenaire.save(vm.entreprisePartenaire, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('crmisticApp:entreprisePartenaireUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dateDebut = false;
        vm.datePickerOpenStatus.dateFin = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
