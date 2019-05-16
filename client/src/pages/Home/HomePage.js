import React from 'react'
import BookComponent from "../../components/Book/BookComponent";

function HomePage(props) {
    // uset
    // function renderWithRole(params) {
    //     if(role = )
    // }
    return (
        <div>
            <BookComponent sessionUser={props.sessionUser} />
        </div>
    );
}

export default HomePage;
