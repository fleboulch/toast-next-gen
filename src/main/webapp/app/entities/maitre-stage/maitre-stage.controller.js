(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('MaitreStageController', MaitreStageController);

    MaitreStageController.$inject = ['$scope', '$state', 'MaitreStage', 'MaitreStageSearch'];

    function MaitreStageController ($scope, $state, MaitreStage, MaitreStageSearch) {
        var vm = this;

        vm.maitreStages = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            MaitreStage.query(function(result) {
                vm.maitreStages = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            MaitreStageSearch.query({query: vm.searchQuery}, function(result) {
                vm.maitreStages = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
