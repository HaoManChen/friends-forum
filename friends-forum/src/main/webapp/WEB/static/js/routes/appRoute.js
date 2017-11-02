/**
 * 路由策略
 */
define(['app'],function (app) {
    app.config(function ($stateProvider, $urlRouterProvider, $controllerProvider,$httpProvider) {
        app.registerController = $controllerProvider.register;
        app.loadJs = function (js) {
            return function ($rootScope, $q) {
                var def = $q.defer(), deps = [];
                angular.isArray(js) ? (deps = js) : deps.push(js);
                require(deps, function () {
                    $rootScope.$apply(function () {
                        def.resolve();
                    });
                });
                return def.promise;
            };
        };
        $stateProvider
            .state('forum',{            //校友论坛模版
                url:'/forum',
                controller:'forumCtrl',
                templateUrl:'views/forum/forum.html',
                resolve:{
                    forumCtrl : app.loadJs('../js/controllers/forum')
                }
            })
            .state('forum.index',{            //校友论坛首页
                url:'/index.html',
                templateUrl:'views/forum/forum-index.html'
            })
            .state('forum.official',{            //校友论坛官方页面
                url:'/official.html',
                templateUrl:'views/forum/forum-official.html'
            })
            .state('forum.reply',{            //校友论坛官方页面
                url:'/forum-reply.html?postId&userId',
                templateUrl:'views/forum/forum-reply.html',
                controller:'forumReplyCtrl',
                resolve:{
                    forumReplyCtrl:app.loadJs('../js/controllers/forumReply')
                }
            });

    })
});