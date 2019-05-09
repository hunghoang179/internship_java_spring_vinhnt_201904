import React, { useState, useEffect } from 'react';

const EditUserForm = props => {

    const [user, setUser] = useState(props.currentUser);
    const handleInputChange = event => {
        const name = event.target.name
        const value = event.target.value
        setUser({ ...user, [name]: value })
    }

    useEffect(() => {
        setUser(props.currentUser);
    }, [props]);

    return (
        <form onSubmit={event => {
            event.preventDefault()
            props.updateUser(user.id, user)
        }}>
            <div className="form-group">
                <label>Tên người dùng</label>
                <input type="text" name="username" value={user.username} onChange={handleInputChange} className="form-control" placeholder="Nhập tên người dùng" />
            </div>
            <div className="form-group">
                <label>Địa chỉ</label>
                <input type="text" name="address" value={user.address} onChange={handleInputChange} className="form-control" placeholder="Địa chỉ" />
            </div>
            <div className="form-group">
                <label>Tuổi</label>
                <input type="text" name="yearOld" value={user.yearOld} onChange={handleInputChange} className="form-control" placeholder="Tuổi" />
            </div>
            <button type="submit" className="btn btn-primary">Cập nhật</button>&emsp;
            <button onClick={() => props.setEditing(false)} type="submit" className="btn btn-primary">Hủy</button>
        </form>
    );
}

export default EditUserForm