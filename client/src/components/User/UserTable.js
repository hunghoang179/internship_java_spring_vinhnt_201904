import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import Role from "../Share/Role";


export default function UserTable(props) {

    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetch('/api/user')
            .then(
                function (response) {
                    if (response.status !== 200) {
                        console.log('Lỗi, mã lỗi ' + response.status);
                        return;
                    }
                    return response.json();
                }
            )
            .then(responseJson => setUsers(responseJson))
            .catch(err =>
                console.log('Error :-S', err)
            );
    }, [])

    return (
        <table className="table table-striped">
            <thead>
                <tr>
                    <th>Tài khoản</th>
                    <th>Mail</th>
                    <th>Địa chỉ</th>
                    <th>Điện thoại</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <ListItem users={users} sessionUser={props.sessionUser} />
        </table>
    );
}

const ListItem = (props) => {
    const usersData = props.users
    if (usersData.length > 0) {
        const listUser = usersData.map(user => (
            <tr key={user.id}>
                <td>{user.username}</td>
                <td>{user.mail}</td>
                <td>{user.address}</td>
                <td>{user.phone}</td>
                <td>{(user.status === 0) ? 'Đang hoạt động' : 'Bị khóa'}</td>
                <td>
                    <Role sessionUser={props.sessionUser} role={user.role} status={3}>
                        <Link to={`/user/${user.id}/edit`}><span><i className="fas fa-edit"></i></span></Link>
                    </Role>
                </td>
            </tr>
        ))
        return (
            <tbody>{listUser}</tbody>
        )
    } else {
        return (<tbody><tr><td colSpan="5">Không có dữ liệu</td></tr></tbody>)
    }
}
