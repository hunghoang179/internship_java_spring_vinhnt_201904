import React, { useState } from 'react'

const AddUserForm = props => {

    const userObject = {
        id: '',
        username: '',
        address: '',
        yearOld: ''
    }
    const [user, setUser] = useState(userObject);

    const handleInputChange = event => {
        const name = event.target.name
        const value = event.target.value
        setUser({ ...user, [name]: value })
    }

    const handleOnSubmit = event => {
        event.preventDefault()
        if (!user.username || !user.address) return
        props.addUser(user)
        setUser(userObject)
    }

    return (
        <form onSubmit={handleOnSubmit}>
            <div className="form-group">
                <label>Tên người dùng</label>
                <input type="text" name="username" value={user.username} onChange={handleInputChange} className="form-control" placeholder="Nhập tên người dùng" />
            </div>
            <div className="form-group">
                <label>Địa chỉ</label>
                <input type="text" name="address" value={user.address} onChange={handleInputChange} className="form-control" placeholder="Địa chỉ" />
            </div>
            <div className="form-group">
                <label>Địa chỉ</label>
                <input type="text" name="yearOld" value={user.yearOld} onChange={handleInputChange} className="form-control" placeholder="Tuổi" />
            </div>
            <button type="submit" className="btn btn-primary">Lưu</button>
        </form>
    );
}

export default AddUserForm