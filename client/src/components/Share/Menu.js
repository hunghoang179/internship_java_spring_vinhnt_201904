import React from 'react'
import { Route, Link } from 'react-router-dom'

export default function Menu() {

    const menus = [
        { to: '/home', label: "Trang chủ" },
        { to: '/user', label: "Người dùng" },
        { to: '/category', label: "Thể loại sách" },
        { to: '/borrow', label: "Mượn sách" },
        { to: '/contact', label: "Liên hệ" }
    ]

    const CustomLink = ({ label, to }) => {
        return (
            <Route path={to} children={({ match }) => {
                var active = match ? 'active' : '';
                return (
                    <li className={active}>
                        <Link to={to}>{label}</Link>
                    </li>
                );
            }} />
        );
    }

    function renderMenu(menus) {
        var listLink = null;
        listLink = menus.map((menu, index) => {
            return (<CustomLink key={index} label={menu.label} to={menu.to} />)
        })
        return listLink;
    }


    return (
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
                            {renderMenu(menus)}
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                            <li className="dropdown"><a className="dropdown-toggle" data-toggle="dropdown" href="#"><span /><span className="caret" /></a>
                                <ul className="dropdown-menu">
                                    <li><a>Thay đổi mật khẩu</a></li>
                                    <li><a>Thay đổi thông tin</a></li>
                                    <li><a>Đăng xuất</a></li>
                                </ul></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    );
}

