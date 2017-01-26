(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('intervenant', {
            parent: 'entity',
            url: '/intervenant',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'crmisticApp.intervenant.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/intervenant/intervenants.html',
                    controller: 'IntervenantController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('intervenant');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('intervenant-detail', {
            parent: 'entity',
            url: '/intervenant/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'crmisticApp.intervenant.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/intervenant/intervenant-detail.html',
                    controller: 'IntervenantDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('intervenant');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Intervenant', function($stateParams, Intervenant) {
                    return Intervenant.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'intervenant',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('intervenant-detail.edit', {
            parent: 'intervenant-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/intervenant/intervenant-dialog.html',
                    controller: 'IntervenantDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Intervenant', function(Intervenant) {
                            return Intervenant.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('intervenant.new', {
            parent: 'intervenant',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/intervenant/intervenant-dialog.html',
                    controller: 'IntervenantDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nom: null,
                                prenom: null,
                                telephone: null,
                                mail: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('intervenant', null, { reload: 'intervenant' });
                }, function() {
                    $state.go('intervenant');
                });
            }]
        })
        .state('intervenant.edit', {
            parent: 'intervenant',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/intervenant/intervenant-dialog.html',
                    controller: 'IntervenantDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Intervenant', function(Intervenant) {
                            return Intervenant.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('intervenant', null, { reload: 'intervenant' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('intervenant.delete', {
            parent: 'intervenant',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/intervenant/intervenant-delete-dialog.html',
                    controller: 'IntervenantDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Intervenant', function(Intervenant) {
                            return Intervenant.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('intervenant', null, { reload: 'intervenant' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
