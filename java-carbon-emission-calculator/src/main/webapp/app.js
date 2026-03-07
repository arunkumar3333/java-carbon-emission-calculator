var app = angular.module("carbonApp", []);

app.controller("LoginController",function($scope,$http){

$scope.message="";

$scope.login=function(){  // when the user clicks login button in html file this fuction was called

var data = "action=login"  //This creates a message to send to the server.
         + "&email=" + encodeURIComponent($scope.email)//This adds the user email to the message.
         + "&password=" + encodeURIComponent($scope.password);

$http({
method:"POST",
url:"/java-carbon-emission-calculator/user",
data:data,
headers:{
"Content-Type":"application/x-www-form-urlencoded"
}

}).then(function(response){

console.log("Server Response:",response.data);

$scope.message=response.data;

if(response.data.includes("Login Success")){
window.location.href="dashboard.html";
}

},function(error){

$scope.message="Server Error";

});

}

});
app.controller("RegisterController", function($scope, $http){

    $scope.message = "";

    $scope.register = function(){

        var data = "action=register"
                 + "&name=" + encodeURIComponent($scope.name)
                 + "&email=" + encodeURIComponent($scope.email)
                 + "&password=" + encodeURIComponent($scope.password)
                 + "&country=" + encodeURIComponent($scope.country);

        $http({
            method: "POST",
            url: "/java-carbon-emission-calculator/user",
            data: data,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
			}).then(function(response){

			    $scope.message = response.data;

			    if(response.data.trim() === "User Registered Successfully"){
			        setTimeout(function(){
			            window.location.href = "login.html";
			        },1500);
			    }

			}, function(error){

			    $scope.message = "Server error";

			});

    };

});

app.controller("DashboardController", function($scope, $http){

$scope.uploadExcel = function(){

var file = document.querySelector('input[type=file]').files[0];

var formData = new FormData();
formData.append("file", file);

$http.post("/java-carbon-emission-calculator/uploadExcel", formData, {
transformRequest: angular.identity,
headers: {'Content-Type': undefined}

}).then(function(response){

$scope.result = response.data;

}, function(){

$scope.result = "Error uploading Excel";

});

};


$scope.saveActivity = function(){

var data = new FormData();

data.append("userId",1);
data.append("fromCity",$scope.fromCity);
data.append("toCity",$scope.toCity);
data.append("vehicleType",$scope.vehicleType);
data.append("vehicles",$scope.vehicles);
data.append("km",$scope.km);
data.append("time",$scope.time);

$http.post("/java-carbon-emission-calculator/activity/add", data, {
transformRequest: angular.identity,
headers:{'Content-Type':undefined}

}).then(function(response){

$scope.activityMessage = response.data;

});

};

});