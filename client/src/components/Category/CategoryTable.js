import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import Role from "../Share/Role";


export default function CategoryTable(props) {

    const [categories, setCategories] = useState([]);

    useEffect(() => {
        fetch('/api/categories')
            .then(
                function (response) {
                    if (response.status !== 200) {
                        console.log('Lỗi, mã lỗi ' + response.status);
                        return;
                    }
                    return response.json();
                }
            )
            .then(responseJson => setCategories(responseJson))
            .catch(err =>
                console.log('Error :-S', err)
            );
    }, [])

    return (
        <table className="table table-striped">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên thể loại</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <ListItem categories={categories} sessionUser={props.sessionUser} />
        </table>
    );
}

const ListItem = (props) => {
    const categoriesData = props.categories
    if (categoriesData.length > 0) {
        const listCategory = categoriesData.map((category, index) => (
            <tr key={category.id}>
                <td>{index + 1}</td>
                <td>{category.name}</td>
                <td>
                    <Link to={`/category/${category.id}/edit`}><span><i className="fas fa-edit"></i></span></Link>
                    <Link><span onClick={() => deleteCategory(category.id, props)}><i class="fas fa-trash-alt"></i></span></Link>
                </td>
            </tr>
        ))
        return (
            <tbody>{listCategory}</tbody>
        )
    } else {
        return (<tbody><tr><td colSpan="5">Không có dữ liệu</td></tr></tbody>)
    }
}

function deleteCategory(id, props) {
    var { history } = props
    if (id) {
        fetch(`/api/category/${id}`, {
            method: 'DELETE'
        }).then(response => {
            if (response.status === 200) {
                history.goBack();
            }

            if (response.status === 409) {
                alert("Thể loại sách không thể xóa do có sách");
            }

            if (response.status === 302) {
                alert("Có lỗi xảy ra");
            }
        });
    }
}
