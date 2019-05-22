import React, { useState } from 'react'
import BookTable from './BookTable'
import BookForm from './BookForm'
import { Link } from "react-router-dom";

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
                <div className="col-md-12" >
                    <h2>Danh sách sách</h2>
                    <BookForm sessionUser={props.sessionUser} />
                    {/* <form class="form-inline active-pink-4" onSubmit={handleSubmit}>
                            <input class="form-control form-control-sm mr-3 w-75" type="text" placeholder="Search" value={keyword} onChange={e => setKeyword(e.target.value)} />
                        </form*/}
                    {/* <input className="form-control form-control-sm mr-3 w-75" type="text" placeholder="Search" value={keyword} onChange={e => setKeyword(e.target.value)} />*/}
                    <BookTable sessionUser={props.sessionUser} keyword={keyword} />
                </div>
            </div>
        </div>
    );
}