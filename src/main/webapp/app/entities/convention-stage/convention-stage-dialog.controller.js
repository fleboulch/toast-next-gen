(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('ConventionStageDialogController', ConventionStageDialogController);

    ConventionStageDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ConventionStage', 'Site', 'Tuteur', 'MaitreStage', 'Etudiant'];

    function ConventionStageDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ConventionStage, Site, Tuteur, MaitreStage, Etudiant) {
        var vm = this;

        vm.conventionStage = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.sites = Site.query();
        vm.tuteurs = Tuteur.query();
        vm.maitrestages = MaitreStage.query();
        vm.etudiants = Etudiant.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.conventionStage.id !== null) {
                ConventionStage.update(vm.conventionStage, onSaveSuccess, onSaveError);
            } else {
                ConventionStage.save(vm.conventionStage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('crmisticApp:conventionStageUpdate', result);
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
