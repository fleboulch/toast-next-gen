(function() {
    'use strict';

    angular
        .module('crmisticApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('entreprise-partenaire', {
            parent: 'entity',
            url: '/entreprise-partenaire',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'crmisticApp.entreprisePartenaire.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/entreprise-partenaire/entreprise-partenaires.html',
                    controller: 'EntreprisePartenaireController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('entreprisePartenaire');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('entreprise-partenaire-detail', {
            parent: 'entity',
            url: '/entreprise-partenaire/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'crmisticApp.entreprisePartenaire.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/entreprise-partenaire/entreprise-partenaire-detail.html',
                    controller: 'EntreprisePartenaireDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('entreprisePartenaire');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'EntreprisePartenaire', function($stateParams, EntreprisePartenaire) {
                    return EntreprisePartenaire.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'entreprise-partenaire',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('entreprise-partenaire-detail.edit', {
            parent: 'entreprise-partenaire-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/entreprise-partenaire/entreprise-partenaire-dialog.html',
                    controller: 'EntreprisePartenaireDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EntreprisePartenaire', function(EntreprisePartenaire) {
                            return EntreprisePartenaire.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('entreprise-partenaire.new', {
            parent: 'entreprise-partenaire',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/entreprise-partenaire/entreprise-partenaire-dialog.html',
                    controller: 'EntreprisePartenaireDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                dateDebut: null,
                                dateFin: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('entreprise-partenaire', null, { reload: 'entreprise-partenaire' });
                }, function() {
                    $state.go('entreprise-partenaire');
                });
            }]
        })
        .state('entreprise-partenaire.edit', {
            parent: 'entreprise-partenaire',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/entreprise-partenaire/entreprise-partenaire-dialog.html',
                    controller: 'EntreprisePartenaireDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EntreprisePartenaire', function(EntreprisePartenaire) {
                            return EntreprisePartenaire.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('entreprise-partenaire', null, { reload: 'entreprise-partenaire' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('entreprise-partenaire.delete', {
            parent: 'entreprise-partenaire',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/entreprise-partenaire/entreprise-partenaire-delete-dialog.html',
                    controller: 'EntreprisePartenaireDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['EntreprisePartenaire', function(EntreprisePartenaire) {
                            return EntreprisePartenaire.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('entreprise-partenaire', null, { reload: 'entreprise-partenaire' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
