import React, { useState } from 'react'
import BookTable from './BookTable'

export default function BookComponent(props) {


    return (
        <div className="container">
            <div className="row">
                <div className="col-md-12" >
                    <h2>Danh sách sách</h2>
                    <BookTable sessionUser={props.sessionUser} />
                </div>
            </div>
        </div>
    );
}