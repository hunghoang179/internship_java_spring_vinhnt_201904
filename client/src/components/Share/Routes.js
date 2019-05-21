import React from 'react'
import { Route, Switch } from 'react-router-dom'
import HomePage from '../../pages/Home/HomePage'
import UserPage from '../../pages/User/UserPage'
import CategoryPage from '../../pages/Category/CategoryPage'
import BorrowPage from '../../pages/Borrow/BorrowPage'

import AddBook from "../Book/AddBook"
import ViewBook from "../Book/ViewBook"
import BorrowBook from "../Book/BorrowBook"
import EditUser from "../User/EditUser"
import EditCategory from "../Category/EditCategory"
import AddCategory from "../Category/AddCategory"
import BorrowApprove from "../BorrowBook/BorrowApprove"
import ChangePassword from "../User/ChangePassword"
import ChangeInfor from "../User/ChangeInfor"

export default function Routes(props) {

    const routes = [
        { path: '/user/:id/edit', main: ({ match, history }) => <EditUser sessionUser={props.sessionUser} match={match} history={history} /> },
        { path: '/category/:id/edit', main: ({ match, history }) => <EditCategory match={match} history={history} /> },
        { path: '/category/add', main: ({ history }) => <AddCategory history={history} /> },
        { path: '/borrow/approve/:id', main: ({ match, history }) => <BorrowApprove match={match} history={history} /> },

        { path: '/home', main: () => <HomePage sessionUser={props.sessionUser} /> },
        { path: '/user', main: () => <UserPage sessionUser={props.sessionUser} /> },
        { path: '/category', main: ({ history }) => <CategoryPage sessionUser={props.sessionUser} history={history} /> },
        { path: '/borrow', main: () => <BorrowPage sessionUser={props.sessionUser} /> },
        { path: '/contact', main: () => <HomePage /> },

        { path: '/book/add', main: ({ history }) => <AddBook history={history} /> },
        { path: '/book/:id/edit', main: ({ match, history }) => <AddBook match={match} history={history} /> },
        { path: '/book/:id/view', main: ({ match, history }) => <ViewBook match={match} history={history} /> },
        { path: '/book/borrow/:id', main: ({ match, history }) => <BorrowBook match={match} history={history} /> },

        { path: '/change/password', main: ({ history }) => <ChangePassword history={history} /> },
        { path: '/change/infor', main: ({ history }) => <ChangeInfor history={history} sessionUser={props.sessionUser} /> },
        { path: '/contact', main: () => <HomePage /> },
    ];

    function DevideRequest(routes) {
        var listLink = null;
        listLink = routes.map((route, index) => {
            return (<Route exact key={index} path={route.path} component={route.main} />)
        })
        return listLink;
    }


    return (
        <Switch>{DevideRequest(routes)}</Switch>
    );
}

