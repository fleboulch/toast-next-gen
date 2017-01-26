(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('PartenariatController', PartenariatController);

    PartenariatController.$inject = ['$scope', '$state', 'Partenariat', 'PartenariatSearch'];

    function PartenariatController ($scope, $state, Partenariat, PartenariatSearch) {
        var vm = this;

        vm.partenariats = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Partenariat.query(function(result) {
                vm.partenariats = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            PartenariatSearch.query({query: vm.searchQuery}, function(result) {
                vm.partenariats = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
