import React from 'react'
import BookTable from './BookTable'
import { Link } from "react-router-dom";
import Role from "../Share/Role";

export default function BookComponent(props) {

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-10" >
                    <h2>Danh sách sách</h2>
                    <Role sessionUser={props.sessionUser}>
                        <Link to='/book/add' className="btn btn-primary">Thêm mới sách</Link>
                    </Role>
                    <BookTable sessionUser={props.sessionUser} />
                </div>
            </div>
        </div>
    );
}