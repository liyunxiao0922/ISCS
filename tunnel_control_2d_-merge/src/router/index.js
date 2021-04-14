import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/components/Home';

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
      redirect: '/netMonitoring',
      children: [
        {
          path: '/netMonitoring',
          component: () => import('@/pages/roadNetInspection/index'),
          meta: {
            title: '道路运行'
          }
        },
        {
          path: '/videoWall',
          component: () => import('@/pages/videoWall/index'),
          meta: {
            title: '视频监控'
          }
        },
        {
          path: '/networkSystem',
          component: () => import('@/pages/networkSystem/index'),
          meta: {
            // title: '网络系统'
            title: '门架系统'
          }
        },
        {
          path: '/tollSystem',
          component: () => import('@/pages/tollSystem/index'),
          meta: {
            title: '收费系统'
          }
        },
        {
          path: '/integratedApplication',
          component: () => import('@/pages/integratedApplication/index'),
          meta: {
            // title: '综合应用'
            title: '交通气象'
          }
        },
        {
          path: '/tunnelMonitoring',
          component: () => import('@/pages/tunnelMonitoring/index'),
          meta: {
            title: '隧道监控'
          }
        }
      ]
    }
  ]
});
