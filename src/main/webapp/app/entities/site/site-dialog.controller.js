(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('SiteDialogController', SiteDialogController);

    SiteDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Site', 'Entreprise', 'ConventionStage'];

    function SiteDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Site, Entreprise, ConventionStage) {
        var vm = this;

        vm.site = entity;
        vm.clear = clear;
        vm.save = save;
        vm.entreprises = Entreprise.query();
        vm.conventionstages = ConventionStage.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.site.id !== null) {
                Site.update(vm.site, onSaveSuccess, onSaveError);
            } else {
                Site.save(vm.site, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('crmisticApp:siteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
