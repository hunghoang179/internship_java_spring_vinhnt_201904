import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'


export default function CategoryTable(props) {

    const [categories, setCategories] = useState([]);
    const [error, setError] = useState(null);
    const [keySearch, setKeySearch] = useState("");
    const [pageNumber, setPageNumber] = useState(1);
    const [pages, setPages] = useState(0);

    useEffect(() => {
        if (keySearch !== "") {
            getListCategoryPagination();
            setPages(0)
        } else {
            getListAllCategory();
        }
    }, [pageNumber, keySearch])

    useEffect(() => {
        if (keySearch === "") {
            getTotalPage();
        }
    }, [keySearch])


    function getListAllCategory() {
        fetch(`/api/categories/${pageNumber}`)
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
    }

    function getListCategoryPagination() {
        fetch(`/api/categories/pagination/${pageNumber}/${keySearch}`)
            .then(
                function (response) {
                    if (response.status !== 200) {
                        setCategories([])
                    }
                    return response.json();
                }
            )
            .then(responseJson => setCategories(responseJson))
            .catch(err =>
                console.log('Error :-S', err)
            );
    }

    function getTotalPage() {
        fetch("/api/categories/pagination/totalPage")
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

    function arrayPages(params) {
        let arr = [];
        for (let i = 1; i <= params; i++) {
            arr.push(i);
        }
        return arr;
    }

    return (
        <div className="row">
            <div className="col-md-2">
                <Link to='/category/add' className="btn btn-primary">Thêm mới thể loại sách</Link>
            </div>
            <div className="col-md-5"></div>
            <div className="col-md-5">
                <input className="form-control" value={keySearch} onChange={e => setKeySearch(e.target.value)} type="text" placeholder="Search" aria-label="Search" />
            </div>
            <div className="col-md-12">
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
            <nav aria-label="Page navigation example">
                <ul className="pagination">
                    {pages > 1 ? arrayPages(pages).map(page =>
                        <li className="page-item" className={page === pageNumber ? 'active' : ''} key={page}><a className="page-link" onClick={() => { setPageNumber(page) }}>{page}</a></li>
                    ) : ""}
                </ul>
            </nav>
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
