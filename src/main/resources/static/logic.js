var app = angular.module('app', ['ngRoute']);
var contextPath = 'http://localhost:8080/store'

app.config(function ($routeProvider) {
    $routeProvider
        .when('/about', {
            templateUrl: 'about-page.html',
            controller: 'aboutController'
        })
        .when('/books', {
            templateUrl: 'book-store.html',
            controller: 'booksController'
        })
});

app.controller('aboutController', function ($scope, $http) {
    fillTable = function () {
        $http.get(contextPath + '/api/v1/books/DTOs')
            .then(function (response) {
                $scope.PopularBooksList = response.data;
            });
    }
    fillTable();
});

app.controller('booksController', function ($scope, $http) {
    fillTable = function () {
        $http.get(contextPath + '/api/v1/books')
            .then(function (response) {
                $scope.BooksList = response.data;
            });
    };

    $scope.submitCreateNewBook = function () {
        $http.post(contextPath + '/api/v1/books', $scope.newBook)
            .then(function (response) {
                $scope.BooksList.push(response.data);
            });
    };

    fillTable();
});