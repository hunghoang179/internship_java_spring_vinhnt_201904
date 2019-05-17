import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import Role from "../Share/Role";

export default function EditUser(props) {

    const [user, setUser] = useState({})
    const [roles, setRoles] = useState([])

    useEffect(() => {
        var { match } = props;
        if (match) {
            var id = match.params.id;
            fetch(`/api/user/${id}`)
                .then(
                    function (response) {
                        if (response.status !== 200) {
                            console.log('Lỗi, mã lỗi ' + response.status);
                            return;
                        }
                        return response.json();
                    }
                )
                .then(responseJson => setUser(responseJson))
                .catch(err =>
                    console.log('Error :-S', err)
                );
        }
    }, [props])

    useEffect(() => {
        fetch(`/api/role`)
            .then(
                function (response) {
                    if (response.status !== 200) {
                        console.log('Lỗi, mã lỗi ' + response.status);
                        return;
                    }
                    return response.json();
                }
            )
            .then(responseJson => setRoles(responseJson))
            .catch(err =>
                console.log('Error :-S', err)
            );
    }, [])

    function handleInputChange(event) {
        const name = event.target.name
        const value = event.target.value
        var newUser = Object.assign({}, user);
        newUser[name] = value;
        setUser(newUser);
    }

    function renderListRole(roles) {
        var list = null;
        list = roles.map((role, index) =>
            <option key={index} value={role.id}>{role.roleName}</option>
        )
        return list;
    }

    function handleSubmit(event) {
        event.preventDefault();
        var { history } = props
        if (user.id) {
            fetch(`/api/user/${user.id}`, {
                method: 'PUT',
                body: JSON.stringify(user),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }).then(response => {
                if (response.status === 200) {
                    history.goBack();
                }
            });
        } else {

        }
    }

    return (
        <div className="container">
            <div className="row">
                <h1>Thay đổi thông tin người dùng</h1>
                <form className="form-horizontal" onSubmit={handleSubmit}>
                    <div className="modal-body">
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Địa chỉ:</label>
                            <div className="col-sm-9">
                                <input type="text" value={user.address} onChange={handleInputChange} className="form-control" id="address" name="address" placeholder="Địa chỉ" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Số điện thoại:</label>
                            <div className="col-sm-9">
                                <input type="text" className="form-control" value={user.phone} onChange={handleInputChange} name="phone" placeholder="Điện thoại" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Mật khẩu:</label>
                            <div className="col-sm-9">
                                <input type="password" value={user.password} onChange={handleInputChange} className="form-control" name="password" placeholder="Mật khẩu" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Trạng thái</label>
                            <div className="col-sm-9">
                                <select value={user.status} onChange={handleInputChange} name="status" className="form-control fill">
                                    <option value={0}>Hoạt động</option>
                                    <option value={1}>Dừng hoạt động</option>
                                </select>
                            </div>
                        </div>
                        <Role sessionUser={props.sessionUser} status={2}>
                            <div className="form-group">
                                <label className="control-label col-sm-3" htmlFor="pwd">Quyền</label>
                                <div className="col-sm-9">
                                    <select value={user.role} onChange={handleInputChange} name="role" className="form-control fill">
                                        {renderListRole(roles)}
                                    </select>
                                </div>
                            </div>
                        </Role>
                    </div>
                    <div className="modal-footer">
                        <button type="submit" className="btn btn-primary">Lưu</button>
                        <Link to='/user' className="btn btn-default">Đóng</Link>
                    </div>
                </form>
            </div>
        </div>

    );
}
