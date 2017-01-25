(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('DiplomeController', DiplomeController);

    DiplomeController.$inject = ['$scope', '$state', 'Diplome', 'DiplomeSearch'];

    function DiplomeController ($scope, $state, Diplome, DiplomeSearch) {
        var vm = this;

        vm.diplomes = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Diplome.query(function(result) {
                vm.diplomes = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            DiplomeSearch.query({query: vm.searchQuery}, function(result) {
                vm.diplomes = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
