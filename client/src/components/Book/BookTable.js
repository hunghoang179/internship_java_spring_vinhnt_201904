import React, { useState, useEffect } from 'react'

export default function BookTable() {

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
            ).then(responseJson => setBooks(responseJson))
            .catch(err => {
                console.log('Error :-S', err)
            });
    }, [])

    return (
        <div className="col-md-10" >
            <h2>Danh sách sách</h2>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>Tiêu đề</th>
                        <th>Tác giả</th>
                        <th>Năm sáng tác</th>
                        <th>Tác vụ</th>
                    </tr>
                </thead>
                <ListItem books={books} />
            </table>
        </div>
    );
}

const ListItem = (props) => {
    const booksData = props.books
    if (booksData.length > 0) {
        const listBook = booksData.map(book => (
            <tr key={book.id}>
                <td>{book.title}</td>
                <td>{book.author}</td>
                <td>{book.year}</td>
                <td>
                    <span><i className="far fa-eye ml-2 mr-1"></i></span>
                    <span><i className="fas fa-book-medical"></i></span>
                    <span><i className="fas fa-edit"></i></span>
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
