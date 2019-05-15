import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Link, NavLink, Switch } from 'react-router-dom'
import BookComponent from './components/Book/BookComponent'
import UserComponent from './components/User/UserComponent'
import CategoryComponent from './components/Category/CategoryComponent'
import Menu from './components/Share/Menu'
import routes from './routes'

function App() {

  const DevideRequest = (routes) => {
    var result = null;
    if (routes.lenght > 0) {
      result = routes.map((route, index) => {
        <Route key={index} path={route.path} component={route.main} />
      });
    }
    return <Switch>{result}</Switch>
  }

  return (
    <div className="App">
      <Router>
        <Menu />
        {DevideRequest(routes)}
      </Router>
    </div>
  );
}

export default App;
