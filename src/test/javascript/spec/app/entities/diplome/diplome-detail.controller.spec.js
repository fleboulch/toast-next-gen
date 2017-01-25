'use strict';

describe('Controller Tests', function() {

    describe('Diplome Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDiplome, MockFiliere, MockEntreprisePartenaire, MockEtudiant;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDiplome = jasmine.createSpy('MockDiplome');
            MockFiliere = jasmine.createSpy('MockFiliere');
            MockEntreprisePartenaire = jasmine.createSpy('MockEntreprisePartenaire');
            MockEtudiant = jasmine.createSpy('MockEtudiant');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Diplome': MockDiplome,
                'Filiere': MockFiliere,
                'EntreprisePartenaire': MockEntreprisePartenaire,
                'Etudiant': MockEtudiant
            };
            createController = function() {
                $injector.get('$controller')("DiplomeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'crmisticApp:diplomeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
