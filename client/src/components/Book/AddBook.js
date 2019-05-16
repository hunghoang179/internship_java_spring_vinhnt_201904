import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'

export default function AddBook(props) {

    const bookObject = { title: '', contentShort: '', stock: '', author: '', year: '', idCategory: 0 }

    const [book, setBook] = useState(bookObject)
    const [category, setCategory] = useState([])
    const [action, setAction] = useState(1)

    useEffect(() => {
        var { match } = props;
        if (match) {
            setAction(2)
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

    useEffect(() => {
        fetch(`/api/categories`)
            .then(
                function (response) {
                    if (response.status !== 200) {
                        console.log('Lỗi, mã lỗi ' + response.status);
                        return;
                    }
                    return response.json();
                }
            )
            .then(responseJson => setCategory(responseJson))
            .catch(err =>
                console.log('Error :-S', err)
            );
    }, [])

    function handleInputChange(event) {
        const name = event.target.name
        const value = event.target.value
        var newBook = Object.assign({}, book);
        newBook[name] = value;
        setBook(newBook);
        //setBook(book => ({ ...book, [name]: value }));
    }

    function renderListCategory(categories) {
        var list = null;
        list = categories.map((category, index) =>
            <option key={index} value={category.id}>{category.name}</option>
        )
        return list;
    }

    function handleSubmit(event) {
        event.preventDefault();
        var { history } = props
        if (book.id) {
            fetch(`/api/book/${book.id}`, {
                method: 'PUT',
                body: JSON.stringify(book),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }).then(response => {
                if (response.status === 200) {
                    history.goBack();
                }
            });
        } else {
            fetch('/api/book', {
                method: 'POST',
                body: JSON.stringify(book),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }).then(response => {
                if (response.status === 201) {
                    history.goBack();
                }
            });
        }
    }

    return (
        <div className="container">
            <div className="row">
                {(action === 1) ? <h1>Thêm mới sách</h1> : <h1>Cập nhật sách</h1>}
                <form className="form-horizontal" onSubmit={handleSubmit}>
                    <div className="modal-body">
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Tiêu đề sách:</label>
                            <div className="col-sm-9">
                                <input type="text" value={book.title} onChange={handleInputChange} className="form-control" id="title" name="title" placeholder="Tiêu đề sách" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Nội dung tóm tắt:</label>
                            <div className="col-sm-9">
                                <textarea className="form-control" value={book.contentShort} onChange={handleInputChange} rows={5} name="contentShort" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Số lượng sách:</label>
                            <div className="col-sm-9">
                                <input type="text" value={book.stock} onChange={handleInputChange} className="form-control" name="stock" placeholder="Số lượng sách" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Tác giả:</label>
                            <div className="col-sm-9">
                                <input type="text" value={book.author} onChange={handleInputChange} className="form-control" name="author" placeholder="Tác giả" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Năm xuất bản</label>
                            <div className="col-sm-9">
                                <input type="text" value={book.year} onChange={handleInputChange} className="form-control" name="year" placeholder="Năm xuất bản" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Thể loại</label>
                            <div className="col-sm-9">
                                <select value={book.idCategory} onChange={handleInputChange} name="idCategory" className="form-control fill">
                                    {renderListCategory(category)}
                                </select>
                            </div>
                        </div>
                    </div>
                    <div className="modal-footer">
                        <button type="submit" className="btn btn-primary">Lưu</button>
                        <Link to='/home' className="btn btn-default">Đóng</Link>
                    </div>
                </form>
            </div>
        </div>

    );
}
