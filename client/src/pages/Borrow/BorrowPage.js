import React from 'react'
import BorrowComponent from "../../components/BorrowBook/BorrowComponent";

function BorrowPage(props) {

    return (
        <div>
            <BorrowComponent sessionUser={props.sessionUser}/>
        </div>
    );
}

export default BorrowPage;
