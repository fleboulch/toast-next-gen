(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('FiliereController', FiliereController);

    FiliereController.$inject = ['$scope', '$state', 'Filiere', 'FiliereSearch'];

    function FiliereController ($scope, $state, Filiere, FiliereSearch) {
        var vm = this;

        vm.filieres = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Filiere.query(function(result) {
                vm.filieres = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            FiliereSearch.query({query: vm.searchQuery}, function(result) {
                vm.filieres = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
