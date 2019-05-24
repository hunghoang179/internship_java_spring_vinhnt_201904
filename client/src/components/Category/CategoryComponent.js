import React from 'react'
import CategoryTable from './CategoryTable'

export default function CategoryComponent(props) {
    return (
        <div className="container">
            <div className="row">
                <div className="col-md-12" >
                    <h2>Danh sách thể loại sách</h2>
                    <CategoryTable sessionUser={props.sessionUser} />
                </div>
            </div>
        </div>
    );
}