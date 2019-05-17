import React from 'react'
import CategoryTable from './CategoryTable'
import { Link } from 'react-router-dom'

export default function CategoryComponent(props) {
    return (
        <div className="container">
            <div className="row">
                <div className="col-md-10" >
                    <h2>Danh sách thể loại sách</h2>
                    <Link to='/category/add' className="btn btn-primary">Thêm mới thể loại sách</Link>
                    <CategoryTable sessionUser={props.sessionUser} />
                </div>
            </div>
        </div>
    );
}