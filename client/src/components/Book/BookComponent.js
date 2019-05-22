import React, { useState } from 'react'
import BookTable from './BookTable'
import { Link } from "react-router-dom";
import Role from "../Share/Role";

export default function BookComponent(props) {

    const [keyword, setKeyword] = useState("")
    const [keySeach, setKeySeach] = useState("")

    const handleSubmit = e => {
        e.preventDefault();
        setKeySeach(keyword);
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-10" >
                    <h2>Danh sách sách</h2>
                    <Role sessionUser={props.sessionUser} status={1}>
                        <div className="col-md-3"><Link to='/book/add' className="btn btn-primary">Thêm mới sách</Link></div>
                    </Role>
                    <div className="col-md-5"></div>
                    <div className="col-md-4">
                        {/* <form class="form-inline active-pink-4" onSubmit={handleSubmit}>
                            <input class="form-control form-control-sm mr-3 w-75" type="text" placeholder="Search" value={keyword} onChange={e => setKeyword(e.target.value)} />
                        </form></div> */}
                        <input className="form-control form-control-sm mr-3 w-75" type="text" placeholder="Search" value={keyword} onChange={e => setKeyword(e.target.value)} /></div>
                        <BookTable sessionUser={props.sessionUser} keyword={keyword} />
                    </div>
                </div>
            </div>
            );
}