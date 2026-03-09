var app = angular.module("carbonApp", []);

/* LOGIN CONTROLLER */
app.controller("LoginController", function($scope, $http){

$scope.message="";

$scope.login=function(){

var data = "action=login"
         + "&email=" + encodeURIComponent($scope.email)
         + "&password=" + encodeURIComponent($scope.password);

$http({
method:"POST",
url:"/java-carbon-emission-calculator/user",
data:data,
headers:{
"Content-Type":"application/x-www-form-urlencoded"
}

}).then(function(response){

$scope.message=response.data;

if(response.data.includes("Login Success")){
window.location.href="dashboard.html";
}

},function(error){

$scope.message="Server Error";

});

}

});


/* REGISTER CONTROLLER */
app.controller("RegisterController", function($scope, $http){

$scope.message="";

$scope.register=function(){

var data = "action=register"
         + "&name=" + encodeURIComponent($scope.name)
         + "&email=" + encodeURIComponent($scope.email)
         + "&password=" + encodeURIComponent($scope.password)
         + "&country=" + encodeURIComponent($scope.country);

$http({
method:"POST",
url:"/java-carbon-emission-calculator/user",
data:data,
headers:{
"Content-Type":"application/x-www-form-urlencoded"
}

}).then(function(response){

$scope.message=response.data;

if(response.data.trim()==="User Registered Successfully"){
setTimeout(function(){
window.location.href="login.html";
},1500);
}

},function(){

$scope.message="Server error";

});

}

});


/* DASHBOARD CONTROLLER */
app.controller("DashboardController", function($scope, $http){

$scope.result = "";
$scope.categoryData = [];

/* Upload Excel */
$scope.uploadExcel = function(){

var file = document.querySelector('input[type=file]').files[0];

var formData = new FormData();
formData.append("file", file);

$http.post("/java-carbon-emission-calculator/uploadExcel", formData, {
transformRequest: angular.identity,
headers: {'Content-Type': undefined}

}).then(function(response){

$scope.result = response.data;

/* Refresh category table after calculation */
$scope.loadCategoryEmission();

}, function(){

$scope.result = "Error uploading Excel";

});

};


/* Load category wise carbon emission */
$scope.loadCategoryEmission = function(){

$http.get("/java-carbon-emission-calculator/dashboard/emission")

.then(function(response){

console.log("Dashboard Data:", response.data);

$scope.categoryData = response.data;

}, function(error){

console.log("Error loading dashboard data", error);

});

};

});