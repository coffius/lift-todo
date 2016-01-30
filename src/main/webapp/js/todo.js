function TaskCtrl($scope, $http) {
    $scope.tasks = [];
    $scope.taskDescription = "";

    //receive tasks
    $http({
        method: 'GET',
        url: '/api/tasks'
    }).then(function successCallback(response) {
        $scope.tasks = response.data;
    }, handleError);

    $scope.removeTask = function(task){
        $http({
            method: 'DELETE',
            url: '/api/tasks/'+task.id
        }).then(function successCallback(response) {
            $scope.tasks.splice($scope.tasks.indexOf(task), 1);
        }, handleError);
    };

    $scope.changeReadiness = function(task){
        updateTask(task)
    };

    $scope.createNewTask = function(){
        var newTask = {
            description: $scope.taskDescription,
            isReady: false
        };

        $http.post('/api/tasks', newTask).then(function(response){
            $scope.tasks.push(response.data);
        }, handleError);
    };

    function handleError(errorResponse) {
        window.alert(JSON.stringify(errorResponse));
    }

    function updateTask(task) {
        $http.put('/api/tasks/'+task.id, task).then(function(){}, handleError);
    }
    //$scope.addTodo = function() {
    //    $scope.todos.push({text:$scope.todoText, done:false});
    //    $scope.todoText = '';
    //};
    //
    //$scope.remaining = function() {
    //    var count = 0;
    //    angular.forEach($scope.todos, function(todo) {
    //        count += todo.done ? 0 : 1;
    //    });
    //    return count;
    //};
    //
    //$scope.archive = function() {
    //    var oldTodos = $scope.todos;
    //    $scope.todos = [];
    //    angular.forEach(oldTodos, function(todo) {
    //        if (!todo.done) $scope.todos.push(todo);
    //    });
    //};
}

angular.module('app', []).controller('TaskCtrl', TaskCtrl);
