var app = angular.module("carbonApp", []);

/* =========================
   LOGIN CONTROLLER
========================= */
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


/* =========================
   REGISTER CONTROLLER
========================= */
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


/* =========================
   DASHBOARD CONTROLLER
========================= */
app.controller("DashboardController", function($scope, $http){
	var chartColors = [
	"#ff6384",
	"#36a2eb",
	"#ffce56",
	"#4bc0c0",
	"#9966ff",
	"#ff9f40"
	];
$scope.result = "";
$scope.categoryData = [];
$scope.combinedData = [];

//Total emission loader
$scope.loadTotalEmission = function(){

$http.get("/java-carbon-emission-calculator/dashboard/total")
.then(function(response){

$scope.totalEmission = response.data.total;

document.getElementById("totalEmission").innerText =
"Total Carbon Emission = " + $scope.totalEmission + " kg CO2";

});

};

/* LOGOUT */
$scope.logout = function(){

$http.post("/java-carbon-emission-calculator/user","action=logout",{
headers:{
"Content-Type":"application/x-www-form-urlencoded"
}

}).then(function(response){

console.log("Logout:", response.data);

window.location.href="index.html";

});

};


/* Upload Excel */
$scope.uploadExcel = function(){

var file = document.getElementById("excelFile").files[0];

if(!file){
$scope.result="Please select a file";
return;
}

var formData = new FormData();
formData.append("file", file);

$http.post("/java-carbon-emission-calculator/uploadExcel", formData, {

transformRequest: angular.identity,
headers: {'Content-Type': undefined}

}).then(function(response){

$scope.result = response.data;
/* reload tables */
$scope.loadCategoryEmission();
$scope.loadCombinedEmission();
$scope.loadTotalEmission();

}, function(){

$scope.result = "Error uploading Excel";

});

};


/* Latest CSV emission */
$scope.loadCategoryEmission = function(){

$http.get("/java-carbon-emission-calculator/dashboard/emission")

.then(function(response){

$scope.categoryData = response.data;

/* Prepare chart data */
var labels = [];
var values = [];

for(var i=0;i<response.data.length;i++){
    labels.push(response.data[i].vehicle);
    values.push(response.data[i].emission);
}

/* Draw chart */
var ctx = document.getElementById("csvChart").getContext("2d");

new Chart(ctx,{
type:"pie",
data:{
labels:labels,
datasets:[{
label:"Carbon Emission",
data:values,
backgroundColor:[
"#ff6384",
"#36a2eb",
"#ffce56",
"#4bc0c0",
"#9966ff",
"#ff9f40"
]
}]
}
});

});
};


/* Combined CSV emission */
$scope.loadCombinedEmission = function(){

$http.get("/java-carbon-emission-calculator/dashboard/combined")

.then(function(response){

$scope.combinedData = response.data;

var labels = [];
var values = [];

for(var i=0;i<response.data.length;i++){

labels.push(response.data[i].vehicle);
values.push(response.data[i].emission);

}

var ctx = document.getElementById("combinedChart").getContext("2d");

new Chart(ctx,{
type:"pie",
data:{
labels:labels,
datasets:[{
data:values,
backgroundColor:[
"#ff6384",   // Bike
"#36a2eb",   // Bus
"#ffce56",   // Truck
"#4bc0c0",   // Auto
"#9966ff",   // Van
"#ff9f40"    // Car
]
}]
},
options:{
responsive:true,
maintainAspectRatio:false
}
});

});

};


/* auto load when page opens */
$scope.loadCategoryEmission();
$scope.loadCombinedEmission();

});

// Used to display total in dashboard
$http.get("dashboard/total")
.then(function(response){
    $scope.totalEmission = response.data.total;
});