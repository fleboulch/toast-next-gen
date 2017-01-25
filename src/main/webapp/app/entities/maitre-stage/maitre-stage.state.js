(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('maitre-stage', {
            parent: 'entity',
            url: '/maitre-stage',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'crmisticApp.maitreStage.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/maitre-stage/maitre-stages.html',
                    controller: 'MaitreStageController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('maitreStage');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('maitre-stage-detail', {
            parent: 'entity',
            url: '/maitre-stage/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'crmisticApp.maitreStage.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/maitre-stage/maitre-stage-detail.html',
                    controller: 'MaitreStageDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('maitreStage');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'MaitreStage', function($stateParams, MaitreStage) {
                    return MaitreStage.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'maitre-stage',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('maitre-stage-detail.edit', {
            parent: 'maitre-stage-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/maitre-stage/maitre-stage-dialog.html',
                    controller: 'MaitreStageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MaitreStage', function(MaitreStage) {
                            return MaitreStage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('maitre-stage.new', {
            parent: 'maitre-stage',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/maitre-stage/maitre-stage-dialog.html',
                    controller: 'MaitreStageDialogController',
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
                                fonction: null,
                                ancienEtudiant: null,
                                debutVersion: null,
                                finVersion: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('maitre-stage', null, { reload: 'maitre-stage' });
                }, function() {
                    $state.go('maitre-stage');
                });
            }]
        })
        .state('maitre-stage.edit', {
            parent: 'maitre-stage',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/maitre-stage/maitre-stage-dialog.html',
                    controller: 'MaitreStageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MaitreStage', function(MaitreStage) {
                            return MaitreStage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('maitre-stage', null, { reload: 'maitre-stage' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('maitre-stage.delete', {
            parent: 'maitre-stage',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/maitre-stage/maitre-stage-delete-dialog.html',
                    controller: 'MaitreStageDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['MaitreStage', function(MaitreStage) {
                            return MaitreStage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('maitre-stage', null, { reload: 'maitre-stage' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
