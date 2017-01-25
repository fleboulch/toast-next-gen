(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('diplome', {
            parent: 'entity',
            url: '/diplome',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'crmisticApp.diplome.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/diplome/diplomes.html',
                    controller: 'DiplomeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('diplome');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('diplome-detail', {
            parent: 'entity',
            url: '/diplome/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'crmisticApp.diplome.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/diplome/diplome-detail.html',
                    controller: 'DiplomeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('diplome');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Diplome', function($stateParams, Diplome) {
                    return Diplome.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'diplome',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('diplome-detail.edit', {
            parent: 'diplome-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diplome/diplome-dialog.html',
                    controller: 'DiplomeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Diplome', function(Diplome) {
                            return Diplome.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('diplome.new', {
            parent: 'diplome',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diplome/diplome-dialog.html',
                    controller: 'DiplomeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nom: null,
                                debutVersion: null,
                                finVersion: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('diplome', null, { reload: 'diplome' });
                }, function() {
                    $state.go('diplome');
                });
            }]
        })
        .state('diplome.edit', {
            parent: 'diplome',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diplome/diplome-dialog.html',
                    controller: 'DiplomeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Diplome', function(Diplome) {
                            return Diplome.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('diplome', null, { reload: 'diplome' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('diplome.delete', {
            parent: 'diplome',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/diplome/diplome-delete-dialog.html',
                    controller: 'DiplomeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Diplome', function(Diplome) {
                            return Diplome.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('diplome', null, { reload: 'diplome' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
