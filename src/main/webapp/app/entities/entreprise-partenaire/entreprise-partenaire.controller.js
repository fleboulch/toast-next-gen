(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('EntreprisePartenaireController', EntreprisePartenaireController);

    EntreprisePartenaireController.$inject = ['$scope', '$state', 'EntreprisePartenaire', 'EntreprisePartenaireSearch'];

    function EntreprisePartenaireController ($scope, $state, EntreprisePartenaire, EntreprisePartenaireSearch) {
        var vm = this;

        vm.entreprisePartenaires = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            EntreprisePartenaire.query(function(result) {
                vm.entreprisePartenaires = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            EntreprisePartenaireSearch.query({query: vm.searchQuery}, function(result) {
                vm.entreprisePartenaires = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
