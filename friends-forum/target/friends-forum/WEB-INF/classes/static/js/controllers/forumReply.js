define(['app','jquery'],function(app,$){
    return app.registerController('forumReplyCtrl', [
        '$scope',
        '$rootScope',
        '$stateParams',
        function ($scope, $rootScope,$stateParams) {
            $rootScope.headTitle = $rootScope.title = '校友论坛';
            alert($stateParams.userId);
        }
    ]);
});