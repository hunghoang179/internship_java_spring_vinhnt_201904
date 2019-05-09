import React from 'react'
import { Link,Redirect  } from "react-router-dom";

class Create extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            title: '',
            contentShort: '',
            author: '',
            year: '',
            idCategory: 1
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const state = this.state
        state[event.target.name] = event.target.value
        this.setState(state);
    }

    handleSubmit(event) {
        event.preventDefault();
        fetch('http://localhost:8080/api/book', {
            method: 'POST',
            body: JSON.stringify({
                title: this.state.title,
                contentShort: this.state.contentShort,
                author: this.state.author,
                year: this.state.year,
                idCategory: 1
            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }).then(response => {
            if (response.status === 201) {
                alert("New website saved successfully");
                return <Redirect to='/form'/>
            }
        });
    }

    render() {
        return (
            <div>
                <p>Thêm mới sách nào</p>
                <form onSubmit={this.handleSubmit}>
                    <p>
                        <label>Tiêu đề:</label>
                        <input type="text" name="title" value={this.state.title} onChange={this.handleChange}/>
                    </p>
                    <p>
                        <label>Nội dung:</label>
                        <input type="text" name="contentShort" value={this.state.contenShort} onChange={this.handleChange}/>
                    </p>
                    <p>
                        <label>Thể loại:</label>
                        <input type="text" name="idCategory" value={this.state.idCategory} onChange={this.handleChange}/>
                    </p>
                    <p>
                        <label>Tác giả:</label>
                        <input type="text" name="author" value={this.state.author} onChange={this.handleChange}/>
                    </p>
                    <p>
                        <label>Năm:</label>
                        <input type="text" name="year" value={this.state.year} onChange={this.handleChange}/>
                    </p>
                    <p>
                        <input type="submit" value="Submit" />
                    </p>
                </form>
                <Link to='/form'>Hủy</Link>
            </div>
        );
    }
}

export default Create;