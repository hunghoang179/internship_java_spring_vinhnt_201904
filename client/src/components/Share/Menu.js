import React from 'react'
import { Route, Link } from 'react-router-dom'

export default function Menu(props) {

    const menusNomalUser = [
        { to: '/home', label: "Trang chủ" },
        { to: '/borrow', label: "Mượn sách" }
    ]

    const menusAdminUser = [
        { to: '/home', label: "Trang chủ" },
        { to: '/user', label: "Người dùng" },
        { to: '/category', label: "Thể loại sách" },
        { to: '/borrow', label: "Mượn sách" }
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

    // function logout() {
    //     fetch('/api/logout')
    //         .then(
    //             function (response) {
    //                 if (response.status === 200) {
    //                     return;
    //                 }
    //             }
    //         )
    //         .catch(err =>
    //             console.log('Error :-S', err)
    //         );
    // }


    return (
        <div>
            <nav className="navbar navbar-inverse">
                <div className="container-fluid">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span className="icon-bar" /> <span className="icon-bar" /> <span className="icon-bar" />
                        </button>
                        <Link to="/home" className="navbar-brand">Logo</Link>
                    </div>
                    <div className="collapse navbar-collapse" id="myNavbar">
                        <ul className="nav navbar-nav">
                            {(props.sessionUser.role === 0) ? renderMenu(menusNomalUser) : renderMenu(menusAdminUser)}
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                            <li className="dropdown"><a className="dropdown-toggle" data-toggle="dropdown" href="#"><span /><span className="caret" /></a>
                                <ul className="dropdown-menu">
                                    <Link to="/change/password"><li>Thay đổi mật khẩu</li></Link>
                                    <Link to="/change/infor"><li>Thay đổi thông tin</li></Link>
                                    <li>Đăng xuất</li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    );
}

