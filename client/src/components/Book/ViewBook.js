import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'

export default function ViewBook(props) {

    const [book, setBook] = useState({})

    useEffect(() => {
        var { match } = props;
        if (match) {
            var id = match.params.id;
            fetch(`/api/book/${id}`)
                .then(
                    function (response) {
                        if (response.status !== 200) {
                            console.log('Lỗi, mã lỗi ' + response.status);
                            return;
                        }
                        return response.json();
                    }
                )
                .then(responseJson => setBook(responseJson))
                .catch(err =>
                    console.log('Error :-S', err)
                );
        }
    }, [props])

    function BookStocking(params1, params2) {
        var stock = params1 - params2;
        if (stock > 0) {
            return true;
        }
        return false;
    }

    return (
        <div className="container">
            <div className="row">
                <h1>Thông tin sách</h1>
                <div className="form-horizontal">
                    <div className="modal-body">
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Tiêu đề sách:</label>
                            <div className="col-sm-9">
                                <input type="text" value={book.title} className="form-control" id="title" name="title" disabled />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Nội dung tóm tắt:</label>
                            <div className="col-sm-9">
                                <textarea className="form-control" value={book.contentShort} rows={5} name="contentShort" disabled />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Tác giả:</label>
                            <div className="col-sm-9">
                                <input type="text" value={book.author} className="form-control" name="author" disabled />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Năm xuất bản</label>
                            <div className="col-sm-9">
                                <input type="text" value={book.year} className="form-control" name="year" disabled />
                            </div>
                        </div>
                    </div>
                    <div className="modal-footer">
                        {BookStocking(book.stock, book.outStock) ? <Link to={`/book/borrow/${book.id}`} className="btn btn-primary">Mượn sách</Link> : ''}
                        <Link to='/home' className="btn btn-default">Đóng</Link>
                    </div>
                </div>
            </div>
        </div>
    );
}
