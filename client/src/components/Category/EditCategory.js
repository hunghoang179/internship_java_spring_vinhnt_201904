import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import Role from "../Share/Role";

export default function EditCategory(props) {

    const [category, setCategory] = useState({})

    useEffect(() => {
        var { match } = props;
        if (match) {
            var id = match.params.id;
            fetch(`/api/category/${id}`)
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
        }
    }, [props])

    function handleInputChange(event) {
        const name = event.target.name
        const value = event.target.value
        var newCategory = Object.assign({}, category);
        newCategory[name] = value;
        setCategory(newCategory);
    }

    function handleSubmit(event) {
        event.preventDefault();
        var { history } = props
        if (category.id) {
            fetch(`/api/category/${category.id}`, {
                method: 'PUT',
                body: JSON.stringify(category),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }).then(response => {
                if (response.status === 200) {
                    history.goBack();
                }
            });
        } else {

        }
    }

    return (
        <div className="container">
            <div className="row">
                <h1>Thay đổi thể loại sách</h1>
                <form className="form-horizontal" onSubmit={handleSubmit}>
                    <div className="modal-body">
                        <div className="form-group">
                            <label className="control-label col-sm-3" htmlFor="email">Tên thể loại sách:</label>
                            <div className="col-sm-9">
                                <input type="text" value={category.name} onChange={handleInputChange} className="form-control" id="name" name="name" placeholder="Tên thể loại sách" />
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="submit" className="btn btn-primary">Lưu</button>
                            <Link to='/category' className="btn btn-default">Đóng</Link>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    );
}
