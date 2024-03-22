import {createRouter, createWebHistory} from 'vue-router'
import BooksView from "../views/BooksView.vue";
import AuthorsView from "../views/AuthorsView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/books',
            name: 'BooksView',
            component: BooksView
        },
        {
            path: '/authors',
            name: 'AuthorsView',
            component: AuthorsView
        }
    ]
})

export default router
