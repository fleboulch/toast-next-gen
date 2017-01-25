(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('filiere', {
            parent: 'entity',
            url: '/filiere',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'crmisticApp.filiere.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/filiere/filieres.html',
                    controller: 'FiliereController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('filiere');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('filiere-detail', {
            parent: 'entity',
            url: '/filiere/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'crmisticApp.filiere.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/filiere/filiere-detail.html',
                    controller: 'FiliereDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('filiere');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Filiere', function($stateParams, Filiere) {
                    return Filiere.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'filiere',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('filiere-detail.edit', {
            parent: 'filiere-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/filiere/filiere-dialog.html',
                    controller: 'FiliereDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Filiere', function(Filiere) {
                            return Filiere.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('filiere.new', {
            parent: 'filiere',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/filiere/filiere-dialog.html',
                    controller: 'FiliereDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                niveau: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('filiere', null, { reload: 'filiere' });
                }, function() {
                    $state.go('filiere');
                });
            }]
        })
        .state('filiere.edit', {
            parent: 'filiere',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/filiere/filiere-dialog.html',
                    controller: 'FiliereDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Filiere', function(Filiere) {
                            return Filiere.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('filiere', null, { reload: 'filiere' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('filiere.delete', {
            parent: 'filiere',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/filiere/filiere-delete-dialog.html',
                    controller: 'FiliereDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Filiere', function(Filiere) {
                            return Filiere.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('filiere', null, { reload: 'filiere' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
