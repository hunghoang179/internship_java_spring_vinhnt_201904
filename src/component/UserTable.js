import React from 'react'

const UserTable = (props) => {
    return (
        <div>
            <table className="table">
                <thead className="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Tên người dùng</th>
                        <th scope="col">Địa chỉ</th>
                        <th scope="col">Tuổi</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                </thead>
                <ListItem users={props.users} editUserRow={props.editUserRow} deleteUser={props.deleteUser}/>
            </table>
        </div>
    );
}

const ListItem = (props) => {
    const usersData = props.users
    if (usersData.length > 0) {
        const listUser = usersData.map(user => (
            <tr key={user.id}>
                <th scope="row">{user.id}</th>
                <td>{user.username}</td>
                <td>{user.address}</td>
                <td>{user.yearOld}</td>
                <td>
                    <span onClick={()=>props.editUserRow(user)}><i className="fas fa-edit ml-2 mr-1"></i></span>
                    <span onClick={() => props.deleteUser(user.id)}><i className="fas fa-trash-alt"></i></span>
                </td>
            </tr >
        ))
        return (
            <tbody>{listUser}</tbody>
        )
    } else {
        return (<tbody><tr><td colSpan="5">Không có dữ liệu</td></tr></tbody>)
    }
}

export default UserTable