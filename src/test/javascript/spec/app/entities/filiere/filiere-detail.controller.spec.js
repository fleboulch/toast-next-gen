'use strict';

describe('Controller Tests', function() {

    describe('Filiere Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockFiliere, MockDiplome, MockPromotion;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockFiliere = jasmine.createSpy('MockFiliere');
            MockDiplome = jasmine.createSpy('MockDiplome');
            MockPromotion = jasmine.createSpy('MockPromotion');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Filiere': MockFiliere,
                'Diplome': MockDiplome,
                'Promotion': MockPromotion
            };
            createController = function() {
                $injector.get('$controller')("FiliereDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'crmisticApp:filiereUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
