import React from 'react'
import BookTable from './BookTable'
import { Link } from "react-router-dom";

export default function BookComponent(props) {

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-10" >
                    <h2>Danh sách sách</h2>
                    <Link to='/book/add' className="btn btn-primary">Thêm mới sách</Link>
                    <BookTable sessionUser={props.sessionUser} />
                </div>
            </div>
        </div>
    );
}