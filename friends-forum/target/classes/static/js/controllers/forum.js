define(['app','jquery'],function(app,$){
    return app.registerController('forumCtrl', [
        '$scope',
        '$rootScope',
        '$location',
        '$filter',
        '$http',
        '$stateParams',
        '$state',
        function ($scope, $rootScope, $location,$filter, $http, $stateParams, $state) {
            $rootScope.headTitle = $rootScope.title = '校友论坛';
            //导航栏点击事件
            $scope.navigationBarClickEvent = function ($event) {
                $('li').removeClass('active');
                $($event.target).parent().addClass('active');
            };
            //页面刷新时自寻找页面对应标签
            findNavbarText($location);
        }
    ]);
    function findNavbarText($location) {
        var url = $location.absUrl();
        var patt =/#\/(\w+)\/(\w+)/;
        var arr = url.match(patt);
        var id = '#navbar-text-'+arr[2];
        $('li').removeClass('active');
        $(id).addClass('active');
    }
});
