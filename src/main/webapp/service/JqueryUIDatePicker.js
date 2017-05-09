angular.module('MyApp').
        directive('myDatepicker', function ($parse) {
            return function (scope, element, attrs) {
                var ngModel = $parse(attrs.ngModel);
                $(function () {
                    element.datepicker({
                        showOn: "both",
                        changeYear: true,
                        changeMonth: true,
                        dateFormat: 'yy-mm-dd',
                        maxDate: new Date(),
                        setDate: new Date(),
                        yearRange: '1920:' + new Date(),
                        change: function (dateText, inst) {
                            scope.$apply(function (scope) {
                                // Change binded variable
                                ngModel.assign(scope, dateText);
                            });
                        }
                    });
                });
            };
        });