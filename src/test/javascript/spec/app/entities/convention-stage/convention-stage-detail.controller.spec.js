'use strict';

describe('Controller Tests', function() {

    describe('ConventionStage Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockConventionStage, MockTuteur, MockEtudiant, MockSite;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockConventionStage = jasmine.createSpy('MockConventionStage');
            MockTuteur = jasmine.createSpy('MockTuteur');
            MockEtudiant = jasmine.createSpy('MockEtudiant');
            MockSite = jasmine.createSpy('MockSite');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'ConventionStage': MockConventionStage,
                'Tuteur': MockTuteur,
                'Etudiant': MockEtudiant,
                'Site': MockSite
            };
            createController = function() {
                $injector.get('$controller')("ConventionStageDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'crmisticApp:conventionStageUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
