import React, { useState, useEffect } from 'react'
import { Link, Redirect } from 'react-router-dom'
import Role from "../Share/Role";


export default function CategoryTable(props) {

    const [categories, setCategories] = useState([]);
    const [error, setError] = useState(null);

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

    const onDelete = (id) => {
        if (id) {
            fetch(`/api/category/${id}`, {
                method: 'DELETE'
            }).then(response => {
                if (response.status === 200) {
                    setCategories(
                        categories.filter(category => category.id !== id)
                    )
                }

                if (response.status === 409) {
                    setError("Thể loại sách không thể xóa do có sách");
                    setTimeout(function () {
                        setError(null);
                    }, 3000);
                }

                if (response.status === 302) {
                    setError("Có lỗi xảy ra");
                }
            });
        }
    }

    return (
        <div>
            <p>{error !== null ? error : ""}</p>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên thể loại</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <ListItem categories={categories} sessionUser={props.sessionUser} onDelete={onDelete} />
            </table>
        </div>
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
                    <span onClick={() => props.onDelete(category.id)}><i className="fas fa-trash-alt"></i></span>
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

// function deleteCategory(id, props) {
//     var { history } = props
//     if (id) {
//         fetch(`/api/category/${id}`, {
//             method: 'DELETE'
//         }).then(response => {
//             if (response.status === 200) {
//                 return <Redirect to="/category" />
//             }

//             if (response.status === 409) {
//                 alert("Thể loại sách không thể xóa do có sách");
//             }

//             if (response.status === 302) {
//                 alert("Có lỗi xảy ra");
//             }
//         });
//     }
// }
