'use strict';

describe('Controller Tests', function() {

    describe('Entreprise Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockEntreprise, MockPartenariat, MockSite, MockIntervenant, MockGroupe;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockEntreprise = jasmine.createSpy('MockEntreprise');
            MockPartenariat = jasmine.createSpy('MockPartenariat');
            MockSite = jasmine.createSpy('MockSite');
            MockIntervenant = jasmine.createSpy('MockIntervenant');
            MockGroupe = jasmine.createSpy('MockGroupe');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Entreprise': MockEntreprise,
                'Partenariat': MockPartenariat,
                'Site': MockSite,
                'Intervenant': MockIntervenant,
                'Groupe': MockGroupe
            };
            createController = function() {
                $injector.get('$controller')("EntrepriseDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'crmisticApp:entrepriseUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
