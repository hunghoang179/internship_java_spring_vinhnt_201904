import React from 'react'

class LifeCycle extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            test: 0
        }

        //this.setNumber = this.setNumber.bind(this)
    }

    setNumber = () => {
        this.setState({
            test: this.state.test + 1
        })
    }

    render() {
        return (
            <div>
                <h3>Detail Life Cycle in React</h3>
                <button onClick={this.setNumber}>Click</button>
                <Detail number={this.state.test}/>
            </div>
        );
    }
}

class Detail extends React.Component {

    componentWillMount() {
        console.log("1. componentWillMount thuc hien 1 lan")
    }

    componentDidMount() {
        console.log("2. componentDidMount thuc hien 1 lan")
    }

    componentWillReceiveProps(newProps){
        console.log("componentWillReceiveProps khi props thay doi")
    }

    shouldComponentUpdate(newProps, newState){
        console.log("shouldComponentUpdate khi props thay doi goi truoc render ");
        return true;
    }

    componentWillUpdate(nextProps, nextState){
        console.log("componentWillUpdate goi truoc render dựa vào return cua shouldComponentUpdate")
    }

    componentDidUpdate(prevProps, prevState){
        console.log("componentDidUpdate gọi sau render lần 2. Gần giống componentDidMount")
    }

    componentWillUnmount(){
        console.log("Để hủy các timer,...")
    }

    render(){
        return(
            <div>
                Gía trị test là {this.props.number}
            </div>
        );
    }

}

export default LifeCycle;