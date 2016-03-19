angular.module('myApp').controller('mainController', ['$scope', 'model', function ($scope, model) {
        $scope.model = model;

        window.modelUpdated = jsfjs.eventHandlerFactory(function (element, jsUpdate) {
            $scope.$apply(function () {
                model.text = JSON.parse(jsUpdate).text;
            });
        }, 'myform:mypanel');

        $scope.sendModel = function () {
            jsfjs.sendAJAX('myform:mypanel', 'myform:mypanel', model);
        };

    }]);
