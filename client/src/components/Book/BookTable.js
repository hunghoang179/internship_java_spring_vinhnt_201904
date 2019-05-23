import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import Role from "../Share/Role";
import BookForm from './BookForm'


export default function BookTable(props) {

    const [books, setBooks] = useState([]);
    const [pageNumber, setPageNumber] = useState(1);
    const [pages, setPages] = useState(0);

    const [title, setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [year, setYear] = useState("");

    useEffect(() => {
        getBookData(title, author, year);
    }, [pageNumber])

    useEffect(() => {
        getTotalPage(title, author, year)
    }, [])

    function getBookData(title, author, year) {
        fetch(`/api/book/pagination/${pageNumber}`, {
            method: 'POST',
            body: JSON.stringify({ title: title, author: author, year: year }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        })
            .then(
                function (response) {
                    if (response !== 200) {
                        setBooks([])
                    }
                    return response.json();
                }
            )
            .then(responseJson => {
                setBooks(responseJson)
            })
            .catch(err =>
                console.log('Error :-S', err)
            );
    }

    function getTotalPage(title, author, year) {
        fetch("/api/book/list/pagination/totalPage", {
            method: 'POST',
            body: JSON.stringify({ title: title, author: author, year: year }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        })
            .then(
                function (response) {
                    return response.json();
                }
            )
            .then(responseJson => setPages(responseJson))
            .catch(err =>
                console.log('Error :-S', err)
            );
    }


    const onSearch = (title, author, year) => {
        setTitle(title);
        setAuthor(author);
        setYear(year);
        setPageNumber(1);
        getBookData(title, author, year);
        getTotalPage(title, author, year);
    }

    function arrayPages(params) {
        let arr = [];
        for (let i = 1; i <= params; i++) {
            arr.push(i);
        }
        return arr;
    }

    return (
        <React.Fragment>
            <BookForm sessionUser={props.sessionUser} onSearch={onSearch} />
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
            <nav aria-label="Page navigation example">
                <ul className="pagination">
                    {pages > 1 ? arrayPages(pages).map(page =>
                        <li className="page-item" className={page === pageNumber ? 'active' : ''} key={page}><a className="page-link" onClick={() => { setPageNumber(page) }}>{page}</a></li>
                    ) : ""}
                </ul>
            </nav>
        </React.Fragment>
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
