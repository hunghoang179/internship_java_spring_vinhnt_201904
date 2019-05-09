import React from 'react'
import ReactDOM from 'react-dom'

class Board extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            indexSquare: Array(9).fill(null)
        }
    }

    handleClick(i) {
        const squares = this.state.squares.slice();
        squares[i] = 'X';
        this.setState({ squares: squares });
    }

    renderSquare(i) {
        return <Square value={this.state.indexSquare[i]} onClick={() => this.handleClick(i)} />
    }

    render() {
        return (
            <div>
                <div className="board-row">
                    {this.renderSquare(0)}
                    {this.renderSquare(1)}
                    {this.renderSquare(2)}
                </div>
                <div className="board-row">
                    {this.renderSquare(3)}
                    {this.renderSquare(4)}
                    {this.renderSquare(5)}
                </div>
                <div className="board-row">
                    {this.renderSquare(6)}
                    {this.renderSquare(7)}
                    {this.renderSquare(8)}
                </div>
            </div>
        );
    }
}
class Square extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: ''
        }

        this.changeValue = this.changeValue.bind(this)
    }

    changeValue() {
        this.setState({
            value: 'O'
        })
    }
    render() {
        return (
            <button className="square" onClick={this.changeValue}>
                {this.state.value}</button>
        );
    }
}