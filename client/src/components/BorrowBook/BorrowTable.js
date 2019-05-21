import React, { useState, useEffect, Fragment } from 'react'
import { Link, Redirect } from 'react-router-dom'
import Role from "../Share/Role";


export default function BorrowTable(props) {

    const pageLimit = 10;

    const [offset, setOffset] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const [currentData, setCurrentData] = useState([]);
    const [borrowBook, setBorrowBook] = useState([]);

    useEffect(() => {
        getList();
    }, [])


    function getList(params) {
        fetch('/api/book/borrow/list')
            .then(
                function (response) {
                    if (response.status !== 200) {
                        return;
                    }
                    return response.json();
                }
            )
            .then(responseJson => setBorrowBook(responseJson))
            .catch(err =>
                console.log('Error :-S', err)
            );
    }

    // const paginationPage = () => {
    //     var list = "";
    //     for (var idx = 1; idx < 5; idx++) {
    //         list = <li className="page-item"><a className="page-link" href="#">1</a></li>;
    //     }
    //     console.log(list);
    //     console.log("ok");
    //     return list;
    // }

    return (
        <Fragment>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tiêu đề sách</th>
                        <th>Người mượn</th>
                        <th>Ngày mượn</th>
                        <th>Ngày trả</th>
                        <th>Trạng thái</th>
                        <th></th>
                    </tr>
                </thead>
                <ListItem borrowBook={borrowBook} sessionUser={props.sessionUser} getList={getList} />
            </table>
            <nav aria-label="Page navigation example">
                <ul className="pagination">
                    <li className="page-item"><a className="page-link" href="#">1</a></li>
                </ul>
            </nav>
        </Fragment>
    );
}

const ListItem = (props) => {
    const borrowBookData = props.borrowBook

    const status = (params) => {
        if (params === 0) return "Pendding"
        if (params === 1) return "Đã duyệt"
        if (params === 2) return "Từ chối"
        if (params === 3) return "Trả sách"
        if (params === 4) return "Mất sách"
    }

    const setAction = (params, id) => {
        if (params === 0) {
            return (
                <Link to={`/borrow/approve/${id}`}><i className="fas fa-book-medical"></i></Link>
            );
        }
        if (params === 1) {
            return (
                <span>
                    <a onClick={() => returnBook(id)}><i className="fas fa-exchange-alt"></i></a>
                    <a onClick={() => missingBook(id)}><i className="fas fa-times-circle"></i></a>
                </span>
            );
        }
    }

    const returnBook = function (id) {
        if (id) {
            fetch('/api/book/return', {
                method: 'POST',
                body: JSON.stringify(id),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }).then(response => {
                if (response.status === 200) {
                    props.getList();
                }
            });
        }
    }

    const missingBook = function (id) {
        if (id) {
            fetch('/api/book/missing', {
                method: 'POST',
                body: JSON.stringify(id),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }).then(response => {
                if (response.status === 200) {
                    props.getList();
                }
            });
        }
    }

    if (borrowBookData.length > 0) {
        const listBorrowBook = borrowBookData.map((borrowBook, index) => (
            <tr key={borrowBook.id} >
                <td>{index + 1}</td>
                <td>{borrowBook.title}</td>
                <td>{borrowBook.username}</td>
                <td>{borrowBook.borrowDate}</td>
                <td>{borrowBook.returnDate}</td>
                <td>{status(borrowBook.status)}</td>
                <td>
                    <Role sessionUser={props.sessionUser} status={4}>
                        {setAction(borrowBook.status, borrowBook.id)}
                    </Role>
                </td>
            </tr >
        ))
        return (
            <tbody>{listBorrowBook}</tbody>
        )
    } else {
        return (<tbody><tr><td colSpan="5">Không có dữ liệu</td></tr></tbody>)
    }

}
