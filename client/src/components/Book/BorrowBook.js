import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'

export default function BorrowBook(props) {

    const [book, setBook] = useState({})
    const [borrowDate, setBorrowDate] = useState("")
    const [returnDate, setReturnDate] = useState("")
    const [note, setNote] = useState("")
    const [error, setError] = useState(null);

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

    function handleSubmit(event) {
        event.preventDefault();
        var { history } = props
        if (book.id) {
            fetch("/api/book/borrow", {
                method: 'POST',
                body: JSON.stringify({ idBook: book.id, borrowDate: borrowDate, returnDate: returnDate, note: note }),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }).then(response => {
                if (response.status === 201) {
                    history.goBack();
                }
                if (response.status === 400) {
                    setError("Thời gian không hợp lệ");
                    setTimeout(function () {
                        setError(null);
                    }, 3000);
                }

                if (response.status === 409) {
                    setError("Số sách mượn vượt quá quy định");
                    setTimeout(function () {
                        setError(null);
                    }, 3000);
                }

                if (response.status === 500) {
                    setError("Sách không đủ để mượn");
                    setTimeout(function () {
                        setError(null);
                    }, 3000);
                }
            });
        }
    }

    return (
        <div className="container">
            <div className="row">
                <h1>Yêu cầu mượn sách</h1>
                <p>{error !== null ? error : ""}</p>
                <form className="form-horizontal" onSubmit={handleSubmit}>
                    <div className="modal-body">
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Tiêu đề sách:</label>
                            <div className="col-sm-9">
                                <input type="text" value={book.title} className="form-control" id="title" name="title" disabled />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Ngày mượn:</label>
                            <div className="col-sm-9">
                                <input type="date" value={borrowDate} onChange={() => setBorrowDate(event.target.value)} className="form-control" name="borrowDate" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Ngày trả:</label>
                            <div className="col-sm-9">
                                <input type="date" value={returnDate} onChange={() => setReturnDate(event.target.value)} className="form-control" name="returnDate" />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Chú ý:</label>
                            <div className="col-sm-9">
                                <input type="text" value={note} onChange={() => setNote(event.target.value)} className="form-control" name="note" />
                            </div>
                        </div>
                    </div>
                    <div className="modal-footer">
                        <button type="submit" className="btn btn-primary">Mượn sách</button>
                        <Link to='/home' className="btn btn-default">Đóng</Link>
                    </div>
                </form>
            </div>
        </div>
    );
}
