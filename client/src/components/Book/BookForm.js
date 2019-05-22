import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import Role from "../Share/Role";

export default function BookForm(props) {

    const [title, setTitle] = useState("")
    const [author, setAuthor] = useState("")
    const [year, setYear] = useState("")

    return (
        <div className="form-horizontal">
            <div>
                <div className="form-group col-md-6">
                    <label className="control-label col-sm-3" htmlFor="email">Tiêu đề sách:</label>
                    <div className="col-sm-9">
                        <input type="text" value={title} onChange={e => setTitle(e.target.value)} className="form-control" id="title" name="title" />
                    </div>
                </div>
                <div className="form-group col-md-6">
                    <label className="control-label col-sm-3" htmlFor="pwd">Tác giả:</label>
                    <div className="col-sm-9">
                        <input type="text" value={author} onChange={e => setAuthor(e.target.value)} className="form-control" name="author" />
                    </div>
                </div>
                <div className="form-group col-md-6">
                    <label className="control-label col-sm-3" htmlFor="pwd">Năm xuất bản</label>
                    <div className="col-sm-9">
                        <input type="text" value={year} onChange={e => setYear(e.target.value)} className="form-control" name="year" />
                    </div>
                </div>
            </div>
            <div className="col-md-12">
                <Role sessionUser={props.sessionUser} status={1}>
                    <Link to='/book/add' className="btn btn-primary">Thêm mới sách</Link>
                </Role>
                <Link to='/home' className="btn btn-default">Tìm kiếm</Link>
            </div>
        </div>
    );
}
