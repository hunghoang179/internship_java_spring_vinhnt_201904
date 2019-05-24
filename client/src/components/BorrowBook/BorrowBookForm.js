import React, { useState, useEffect } from 'react'

export default function BorrowBookForm(props) {

    const [title, setTitle] = useState("")
    const [username, setUsername] = useState("")
    const [status, setStatus] = useState(-1)

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
                    <label className="control-label col-sm-3" htmlFor="pwd">Thể loại</label>
                    <div className="col-sm-9">
                        <select value={status} onChange={e => setStatus(e.target.value)} name="status" className="form-control fill">
                            <option value={-1}>Tất cả</option>
                            <option value={0}>Chờ duyệt</option>
                            <option value={1}>Đã duyệt</option>
                            <option value={2}>Từ chối</option>
                            <option value={3}>Trả sách</option>
                            <option value={4}>Mất sách</option>
                        </select>
                    </div>
                </div>
            </div>
            <div className="col-md-12">
                <button onClick={() => props.onSearch(title.trim(), username.trim(), status)} className="btn btn-default">Tìm kiếm</button>
            </div>
        </div>
    );
}
