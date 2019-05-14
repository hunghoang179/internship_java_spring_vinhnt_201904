import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Link, NavLink } from 'react-router-dom'
import BookComponent from './components/Book/BookComponent'
import UserComponent from './components/User/UserComponent'
import CategoryComponent from './components/Category/CategoryComponent'

function App() {

  const CustomLink = ({label,to}) =>{
    return (
      <Route path={to} children={({match}) =>{
        var active = match ? 'active' : '';
        return (
          <li className={active}>
            <NavLink to={to}>{label}</NavLink>
          </li>
      );
      }} />
    );
  }

  return (
    <div className="App">
      <Router>
        <div>
          <nav className="navbar navbar-inverse">
            <div className="container-fluid">
              <div className="navbar-header">
                <button type="button" className="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                  <span className="icon-bar" /> <span className="icon-bar" /> <span className="icon-bar" />
                </button>
                <a className="navbar-brand" href="#">Logo</a>
              </div>
              <div className="collapse navbar-collapse" id="myNavbar">
                <ul className="nav navbar-nav">
                  {/* <li><NavLink activeClassName="active" to="/home">Trang chủ</NavLink></li>
                  <li><NavLink activeClassName="active" to="/user">Người dùng</NavLink></li>
                  <li><NavLink activeClassName="active" to="/category">Thể loại sách</NavLink></li> */}
                  <CustomLink label="Trang chủ" to="/home"/>
                  <CustomLink label="Người dùng" to="/user"/>
                  <CustomLink label="Thể loại" to="/category"/>
                  <li><a>Mượn sách</a></li>
                  <li><a>Liên hệ</a></li>
                </ul>
                <ul className="nav navbar-nav navbar-right">
                  <li className="dropdown"><a className="dropdown-toggle" data-toggle="dropdown" href="#"><span /><span className="caret" /></a>
                    <ul className="dropdown-menu">
                      <li><a>Thay đổi mật khẩu</a></li>
                      <li><a>Thay đổi thông
                  tin</a></li>
                      <li><a>Đăng xuất</a></li>
                    </ul></li>
                </ul>
              </div>
            </div>
          </nav>
        </div>

        <Route path="/user" component={UserComponent}></Route>
        <Route path="/home" component={BookComponent}></Route>
        <Route path="/category" component={CategoryComponent}></Route>
      </Router>
    </div >
  );
}

export default App;
