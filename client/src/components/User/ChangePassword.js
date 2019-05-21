import React, { useState, useEffect, useRef } from 'react'
import { Link } from 'react-router-dom'
import Role from "../Share/Role";

export default function ChangePassword(props) {

    const [passwordDto, setPasswordDto] = useState({})
    const [error, setError] = useState(null)

    function handleInputChange(event) {
        const name = event.target.name
        const value = event.target.value
        var newPasswordDto = Object.assign({}, passwordDto);
        newPasswordDto[name] = value;
        setPasswordDto(newPasswordDto);
    }

    function handleSubmit(event) {
        event.preventDefault();
        var { history } = props
        fetch('/api/change/password', {
            method: 'POST',
            body: JSON.stringify(passwordDto),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }).then(response => {
            if (response.status === 200) {
                history.goBack();
            }
            if (response.status === 417) {
                setError("Mật khẩu hiện tại không đúng");
                setTimeout(function () {
                    setError(null);
                }, 3000);
            }

            if (response.status === 400) {
                setError("Mật khẩu xác nhận không đúng");
                setTimeout(function () {
                    setError(null);
                }, 3000);
            }
        });
    }


    return (
        <div className="container">
            <div className="row">
                <h1>Thay đổi mật khẩu</h1>
                <p>{error !== null ? error : ""}</p>
                <form className="form-horizontal" onSubmit={handleSubmit}>
                    <div className="modal-body">
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Mật khẩu hiện tại:</label>
                            <div className="col-sm-9">
                                <input type="password" className="form-control" name="password" onChange={handleInputChange} placeholder="Mật khẩu hiện tại" required />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Mật khẩu mới:</label>
                            <div className="col-sm-9">
                                <input type="password" className="form-control" name="passwordNew" onChange={handleInputChange} placeholder="Mật khẩu mới" required />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Xác nhận mật khẩu mới:</label>
                            <div className="col-sm-9">
                                <input type="password" className="form-control" name="rePassword" onChange={handleInputChange} placeholder="Xác nhận mật khẩu mới" required />
                            </div>
                        </div>
                    </div>
                    <div className="modal-footer">
                        <button type="submit" className="btn btn-primary">Thay đổi</button>
                        <Link to='/home' className="btn btn-default">Đóng</Link>
                    </div>
                </form>
            </div>
        </div>

    );
}
