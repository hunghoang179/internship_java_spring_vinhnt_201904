import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter, Switch, Route, Link } from "react-router-dom";
import Create from './create.js';
import HookApp from './hook.js';
import LifeCycle from './lifeCycle.js'
import './index.css'



class Home extends React.Component {
    render() {
        return (
            <div>
                <h1>Hello, {this.props.name}</h1>
                <h2>Tutorial Game XO By React js</h2>
                <Link to='/form'>Go to library</Link>
            </div>
        );
    }
}
class Form extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
    }

    componentDidMount() {
        fetch('http://192.168.10.153:8080/api/book')
            .then(function (response) {
                return response.json();
            }).then(result => {
                this.setState({
                    data: result
                });
            });
    }

    render() {
        return (
            <div>
                <div>
                    <button><Link to="/create">Create New</Link></button>
                </div>
                <br></br>
                <div>
                    <RenderList data={this.state.data} />
                </div>
            </div>
        );
    }
}

function RenderList(props) {
    const data = props.data;
    const listItems = data.map(e => {
        return (
            <tr key={e.id}>
                <td>{e.title}</td>
                <td>{e.author}</td>
                <td>{e.year}</td>
                <td className="text-max200">{e.contentShort}</td>
                <td>
                    <a><i className="far fa-edit"></i></a>
                    <a><i className="fas fa-trash-alt"></i></a>
                </td>
            </tr>
        );
    }
    );
    return (
        <div>
            <table className="table-bordered">
                <thead>
                    <tr className="text-center">
                        <th>Tên sách</th>
                        <th>Tác giả</th>
                        <th>Năm sản xuất</th>
                        <th>Nội dung ngắn</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>{listItems}</tbody>
            </table>
            <Link to='/'>Back</Link>
        </div>
    );
}

const App = () => {
    return (
        <div>
            <h3>App React Test</h3>
            <Switch>
                <Route exact path='/' component={Home} />
                <Route path='/form' component={Form} />
                <Route path='/create' component={Create} />
                {/* <Route path='/update/:id' component={Update} /> */}
            </Switch>
        </div>
    );
}
// ReactDOM.render(
//     <BrowserRouter>
//         <div>
//             <App />
//         </div>
//     </BrowserRouter>
//     , document.getElementById('root'))

ReactDOM.render(
    <BrowserRouter>
        <div>
            <HookApp />
        </div>
    </BrowserRouter>
    , document.getElementById('root-hook'))

// ReactDOM.render(
//     <BrowserRouter>
//         <div>
//             <LifeCycle />
//         </div>
//     </BrowserRouter>
//     , document.getElementById('root-life-cycle'))
