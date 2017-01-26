(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('IntervenantController', IntervenantController);

    IntervenantController.$inject = ['$scope', '$state', 'Intervenant', 'IntervenantSearch'];

    function IntervenantController ($scope, $state, Intervenant, IntervenantSearch) {
        var vm = this;

        vm.intervenants = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Intervenant.query(function(result) {
                vm.intervenants = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            IntervenantSearch.query({query: vm.searchQuery}, function(result) {
                vm.intervenants = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
