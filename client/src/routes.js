import React from 'react'
import HomePage from './pages/Home/HomePage'
import UserPage from './pages/User/UserPage'
import CategoryPage from './pages/Category/CategoryPage'

const routes = [
    { path: '/home', main: () => <HomePage /> },
    { path: '/user', main: () => <UserPage /> },
    { path: '/category', main: () => <CategoryPage /> },
    { path: '/borrow', main: () => <HomePage /> },
    { path: '/contact', main: () => <HomePage /> }
];

export default routes;