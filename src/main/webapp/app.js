var app = angular.module("carbonApp",[]);

app.controller("LoginController",function($scope,$http){

$scope.login=function(){

var data=new FormData();

data.append("action","login");
data.append("email",$scope.email);
data.append("password",$scope.password);

$http.post("user",data,{
transformRequest:angular.identity,
headers:{'Content-Type':undefined}

}).then(function(response){

$scope.message=response.data;

});

}

});

app.controller("RegisterController",function($scope,$http){

$scope.register=function(){

var data=new FormData();

data.append("action","register");
data.append("name",$scope.name);
data.append("email",$scope.email);
data.append("password",$scope.password);
data.append("country",$scope.country);

$http.post("user",data,{
transformRequest:angular.identity,
headers:{'Content-Type':undefined}

}).then(function(response){

$scope.message=response.data;

});

}

});