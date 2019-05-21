import React, { useState, useEffect, useRef } from 'react'
import { Link } from 'react-router-dom'
import Role from "../Share/Role";

export default function ChangeInfor(props) {

    const [user, setUser] = useState(props.sessionUser)
    const [error, setError] = useState(null)

    function handleInputChange(event) {
        const name = event.target.name
        const value = event.target.value
        var newUser = Object.assign({}, user);
        newUser[name] = value;
        setUser(newUser);
    }

    function handleSubmit(event) {
        event.preventDefault();
        var { history } = props
        fetch('/api/change/infor', {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }).then(response => {
            if (response.status === 200) {
                history.goBack();
            }
            if (response.status === 409) {
                setError("Email đã tồn tại");
                setTimeout(function () {
                    setError(null);
                }, 3000);
            }
        });
    }


    return (
        <div className="container">
            <div className="row">
                <h1>Cập nhật thông tin</h1>
                <p>{error !== null ? error : ""}</p>
                <form className="form-horizontal" onSubmit={handleSubmit}>
                    <div className="modal-body">
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Mail:</label>
                            <div className="col-sm-9">
                                <input type="email" value={user.mail} className="form-control" name="mail" onChange={handleInputChange} placeholder="Mail" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Địa chỉ:</label>
                            <div className="col-sm-9">
                                <input type="text" value={user.address} className="form-control" name="address" onChange={handleInputChange} placeholder="Địa chỉ" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Điện thoại:</label>
                            <div className="col-sm-9">
                                <input type="text" value={user.phone} className="form-control" name="phone" onChange={handleInputChange} placeholder="Điện thoại" />
                            </div>
                        </div>
                    </div>
                    <div className="modal-footer">
                        <button type="submit" className="btn btn-primary">Cập nhật</button>
                        <Link to='/home' className="btn btn-default">Hủy</Link>
                    </div>
                </form>
            </div>
        </div>

    );
}
