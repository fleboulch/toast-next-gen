(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .controller('PromotionController', PromotionController);

    PromotionController.$inject = ['$scope', '$state', 'Promotion', 'PromotionSearch'];

    function PromotionController ($scope, $state, Promotion, PromotionSearch) {
        var vm = this;

        vm.promotions = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Promotion.query(function(result) {
                vm.promotions = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            PromotionSearch.query({query: vm.searchQuery}, function(result) {
                vm.promotions = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
