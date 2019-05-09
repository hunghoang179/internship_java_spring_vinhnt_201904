import React, { useState, useEffect } from 'react';
import './hook.css'
import UserTable from './component/UserTable'
import AddUserForm from './component/AddUserForm'
import EditUserForm from './component/EditUserForm'

const HookApp = () => {

    const initUserData = [
        { id: 1, username: 'Vinh', address: 'Việt Nam', yearOld: 20 },
        { id: 2, username: 'Tuấn', address: 'Thái Lan', yearOld: 20 },
        { id: 3, username: 'Tú', address: 'Đức', yearOld: 20 },
        { id: 4, username: 'Chỉnh', address: 'Pháp', yearOld: 20 }
    ]

    const initUserEditState = {
        id: null,
        username: '',
        address: '',
        yearOld: ''
    }

    const [users, setUsers] = useState(initUserData)
    const [isEdit, setIsEdit] = useState(false)
    const [currentUser, setCurrentUser] = useState(initUserEditState)



    const addUser = user => {
        user.id = users.length + 1
        setUsers([...users, user])
    }

    const deleteUser = id => {
        setUsers(
            users.filter(user => user.id !== id)
        )
    }

    const editUserRow = user => {
        setIsEdit(true)
        setCurrentUser({ id: user.id, username: user.username, address: user.address, yearOld: user.yearOld })
    }

    const updateUser = (id, updateUser) => {
        setIsEdit(false)
        setUsers(
            users.map(user => (user.id === id ? updateUser : user))
        )
    }

    return (
        <div className="container">
            <h1>Example CRUD User Using Hook in React</h1>
            <div className="row">
                <div className="col-md-6">
                    <h4>Thao tác người dùng</h4>
                    {
                        isEdit ? (<EditUserForm updateUser={updateUser} currentUser={currentUser} />) : (<AddUserForm addUser={addUser} />)
                    }
                </div>
                <div className="col-md-6">
                    <h4>Danh sách người dùng</h4>
                    <UserTable users={users} editUserRow={editUserRow} deleteUser={deleteUser} />
                </div>
            </div>
        </div>
    );
}


export default HookApp
