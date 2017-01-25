(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('TuteurController', TuteurController);

    TuteurController.$inject = ['$scope', '$state', 'Tuteur', 'TuteurSearch'];

    function TuteurController ($scope, $state, Tuteur, TuteurSearch) {
        var vm = this;

        vm.tuteurs = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Tuteur.query(function(result) {
                vm.tuteurs = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            TuteurSearch.query({query: vm.searchQuery}, function(result) {
                vm.tuteurs = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
