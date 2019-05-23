import React from 'react'
import BorrowTable from './BorrowTable'
import { Link } from "react-router-dom";
import Role from "../Share/Role";

export default function BorrowComponent(props) {

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-12" >
                    <BorrowTable sessionUser={props.sessionUser} />
                </div>
            </div>
        </div>
    );
}