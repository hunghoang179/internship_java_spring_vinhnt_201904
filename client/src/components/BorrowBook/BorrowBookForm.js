import React, { useState, useEffect } from 'react'

export default function BorrowBookForm(props) {

    const [title, setTitle] = useState("")
    const [username, setUsername] = useState("")
    const [status, setStatus] = useState("")

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
                    <label className="control-label col-sm-3" htmlFor="pwd">Người mượn:</label>
                    <div className="col-sm-9">
                        <input type="text" value={username} onChange={e => setUsername(e.target.value)} className="form-control" name="username" />
                    </div>
                </div>
                <div className="form-group col-md-6">
                    <label className="control-label col-sm-3" htmlFor="pwd">Trạng thái:</label>
                    <div className="col-sm-9">
                        <input type="text" value={status} onChange={e => setStatus(e.target.value)} className="form-control" name="status" />
                    </div>
                </div>
            </div>
            <div className="col-md-12">
                <button onClick={() => props.onSearch(title.trim(), username.trim(), status.trim())} className="btn btn-default">Tìm kiếm</button>
            </div>
        </div>
    );
}
