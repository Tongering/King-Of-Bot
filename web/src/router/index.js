import { createRouter, createWebHistory } from 'vue-router'
import PkIndexView from "@/views/pk/PkIndexView"
import RankIndexView from "@/views/ranklist/RankIndexView"
import RecordIndexView from "@/views/record/RecordIndexView"
import NotFound from "@/views/error/NotFound"
import UserBotIndexView from "@/views/user/bot/UserBotIndexView"

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/pk",
  },
  {
    path: "/pk/",
    name: "pk_index",
    component: PkIndexView,
  },
  {
    path: "/record/",
    name: "record_index",
    component: RecordIndexView,
  },
  {
    path: "/ranklist/",
    name: "ranklist_index",
    component: RankIndexView,
  },
  {
    path: "/user/bot/",
    name: "user_bot_index",
    component: UserBotIndexView,
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound,
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/",
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router