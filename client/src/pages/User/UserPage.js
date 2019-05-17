import React from 'react'
import UserComponent from "../../components/User/UserComponent";

function UserPage(props) {

    return (
        <div>
            <UserComponent sessionUser={props.sessionUser} />
        </div>
    );
}

export default UserPage;
