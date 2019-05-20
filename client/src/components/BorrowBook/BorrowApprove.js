import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import Role from "../Share/Role";

function BorrowApprove(props) {

    const [borrowBook, setBorrowBook] = useState({});
    const [error, setError] = useState(null);

    useEffect(() => {
        var { match } = props;
        if (match) {
            var id = match.params.id;
            fetch(`/api/book/approve/${id}`)
                .then(
                    function (response) {
                        if (response.status !== 200) {
                            console.log('Lỗi, mã lỗi ' + response.status);
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
    }, [props])

    function handleSubmit(event) {
        event.preventDefault();
        var { history } = props
        if (borrowBook.id) {
            fetch('/api/book/approve', {
                method: 'POST',
                body: JSON.stringify(borrowBook.id),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }).then(response => {
                if (response.status === 200) {
                    history.goBack();
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

    const cancleBorrow = () => {
        var { history } = props
        if (borrowBook.id) {
            fetch('/api/book/cancle', {
                method: 'POST',
                body: JSON.stringify(borrowBook.id),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }).then(response => {
                if (response.status === 200) {
                    history.goBack();
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
                            <label className="control-label col-sm-3" htmlFor="pwd">Người mượn:</label>
                            <div className="col-sm-9">
                                <input type="text" value={borrowBook.username} className="form-control" name="username" readOnly />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Tiêu đề sách:</label>
                            <div className="col-sm-9">
                                <input type="text" value={borrowBook.title} className="form-control" id="title" name="title" readOnly />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Ngày mượn:</label>
                            <div className="col-sm-9">
                                <input type="date" value={borrowBook.borrowDate} className="form-control" name="borrowDate" readOnly />
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="pwd">Ngày trả:</label>
                            <div className="col-sm-9">
                                <input type="date" value={borrowBook.returnDate} className="form-control" name="returnDate" readOnly />
                            </div>
                        </div>
                    </div>
                    <div className="modal-footer">
                        <button type="submit" className="btn btn-primary">Chấp nhận</button>
                        <button type="button" className="btn btn-default" onClick={cancleBorrow}>Từ chối</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default BorrowApprove;