'use strict';

describe('Controller Tests', function() {

    describe('Partenariat Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockPartenariat, MockEntreprise, MockDiplome;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockPartenariat = jasmine.createSpy('MockPartenariat');
            MockEntreprise = jasmine.createSpy('MockEntreprise');
            MockDiplome = jasmine.createSpy('MockDiplome');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Partenariat': MockPartenariat,
                'Entreprise': MockEntreprise,
                'Diplome': MockDiplome
            };
            createController = function() {
                $injector.get('$controller')("PartenariatDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'crmisticApp:partenariatUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
