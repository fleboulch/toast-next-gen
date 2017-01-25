'use strict';

describe('Controller Tests', function() {

    describe('EntreprisePartenaire Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockEntreprisePartenaire, MockEntreprise, MockDiplome;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockEntreprisePartenaire = jasmine.createSpy('MockEntreprisePartenaire');
            MockEntreprise = jasmine.createSpy('MockEntreprise');
            MockDiplome = jasmine.createSpy('MockDiplome');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'EntreprisePartenaire': MockEntreprisePartenaire,
                'Entreprise': MockEntreprise,
                'Diplome': MockDiplome
            };
            createController = function() {
                $injector.get('$controller')("EntreprisePartenaireDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'crmisticApp:entreprisePartenaireUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
