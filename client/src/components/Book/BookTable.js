import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import Role from "../Share/Role";


export default function BookTable(props) {

    const [books, setBooks] = useState([]);

    useEffect(() => {
        fetch('/api/book')
            .then(
                function (response) {
                    if (response.status !== 200) {
                        console.log('Lỗi, mã lỗi ' + response.status);
                        return;
                    }
                    return response.json();
                }
            )
            .then(responseJson => setBooks(responseJson))
            .catch(err =>
                console.log('Error :-S', err)
            );
    }, [])

    // useEffect(() => {
    //     //var arrySeach = []
    //     if (props.keyword !== "") {
    //         fetch(`/api/book/seach/${props.keyword}`)
    //             .then(
    //                 function (response) {
    //                     if (response.status !== 200) {
    //                         console.log('Lỗi, mã lỗi ' + response.status);
    //                         setBooks([])
    //                         return;
    //                     }
    //                     return response.json();
    //                 }
    //             )
    //             .then(responseJson => setBooks(responseJson))
    //             .catch(err =>
    //                 console.log('Error :-S', err)
    //             );
    //     } else {
    //         fetch('/api/book')
    //             .then(
    //                 function (response) {
    //                     if (response.status !== 200) {
    //                         console.log('Lỗi, mã lỗi ' + response.status);
    //                         return;
    //                     }
    //                     return response.json();
    //                 }
    //             )
    //             .then(responseJson => setBooks(responseJson))
    //             .catch(err =>
    //                 console.log('Error :-S', err)
    //             );
    //     }
    // }, [props])

    return (
        <table className="table table-striped">
            <thead>
                <tr>
                    <th>Tiêu đề</th>
                    <th>Tác giả</th>
                    <th>Năm sáng tác</th>
                    <th>Tác vụ</th>
                </tr>
            </thead>
            <ListItem books={books} sessionUser={props.sessionUser} />
        </table>
    );
}

const ListItem = (props) => {

    function BookStocking(params1, params2) {
        var stock = params1 - params2;
        if (stock > 0) {
            return true;
        }
        return false;
    }

    let booksData = []
    if (props.books !== undefined) {
        booksData = props.books
    }
    if (booksData.length > 0) {
        const listBook = booksData.map(book => (
            <tr key={book.id}>
                <td>{book.title}</td>
                <td>{book.author}</td>
                <td>{book.year}</td>
                <td>
                    <Link to={`/book/${book.id}/view`}><span><i className="far fa-eye ml-2 mr-1"></i></span></Link>
                    {BookStocking(book.stock, book.outStock) ? <Link to={`/book/borrow/${book.id}`}><span><i className="fas fa-book-medical"></i></span></Link> : ""}
                    <Role sessionUser={props.sessionUser} status={1}>
                        <Link to={`/book/${book.id}/edit`}><span><i className="fas fa-edit"></i></span></Link>
                    </Role>
                </td>
            </tr>
        ))
        return (
            <tbody>{listBook}</tbody>
        )
    } else {
        return (<tbody><tr><td colSpan="5">Không có dữ liệu</td></tr></tbody>)
    }
}
