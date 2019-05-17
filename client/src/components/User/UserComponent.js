import React from 'react'
import UserTable from './UserTable'

export default function UserComponent(props) {
    return (
        <div className="container">
            <div className="row">
                <div className="col-md-10" >
                    <h2>Danh sách người dùng</h2>
                    <UserTable sessionUser={props.sessionUser} />
                </div>
            </div>
        </div>
    );
}