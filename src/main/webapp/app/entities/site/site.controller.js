(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('SiteController', SiteController);

    SiteController.$inject = ['$scope', '$state', 'Site', 'SiteSearch'];

    function SiteController ($scope, $state, Site, SiteSearch) {
        var vm = this;

        vm.sites = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Site.query(function(result) {
                vm.sites = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            SiteSearch.query({query: vm.searchQuery}, function(result) {
                vm.sites = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
