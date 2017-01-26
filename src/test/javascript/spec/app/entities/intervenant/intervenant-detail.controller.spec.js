'use strict';

describe('Controller Tests', function() {

    describe('Intervenant Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockIntervenant, MockDiplome, MockEntreprise;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockIntervenant = jasmine.createSpy('MockIntervenant');
            MockDiplome = jasmine.createSpy('MockDiplome');
            MockEntreprise = jasmine.createSpy('MockEntreprise');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Intervenant': MockIntervenant,
                'Diplome': MockDiplome,
                'Entreprise': MockEntreprise
            };
            createController = function() {
                $injector.get('$controller')("IntervenantDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'crmisticApp:intervenantUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
