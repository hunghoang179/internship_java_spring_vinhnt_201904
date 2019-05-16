import React from 'react'
import { Route, Switch } from 'react-router-dom'
import HomePage from '../../pages/Home/HomePage'
import UserPage from '../../pages/User/UserPage'
import CategoryPage from '../../pages/Category/CategoryPage'

import AddBook from "../Book/AddBook";

export default function Routes(props) {

    const routes = [
        { path: '/home', main: () => <HomePage sessionUser={props.sessionUser} /> },
        { path: '/user', main: () => <UserPage /> },
        { path: '/category', main: () => <CategoryPage /> },
        { path: '/borrow', main: () => <HomePage /> },
        { path: '/contact', main: () => <HomePage /> },

        { path: '/book/add', main: ({ history }) => <AddBook history={history} /> },
        { path: '/book/:id/edit', main: ({ match, history }) => <AddBook match={match} history={history} /> },
        { path: '/contact', main: () => <HomePage /> },
        { path: '/contact', main: () => <HomePage /> },
        { path: '/contact', main: () => <HomePage /> },
    ];

    function DevideRequest(routes) {
        var listLink = null;
        listLink = routes.map((route, index) => {
            return (<Route key={index} path={route.path} component={route.main} />)
        })
        return listLink;
    }


    return (
        <Switch>{DevideRequest(routes)}</Switch>
    );
}

