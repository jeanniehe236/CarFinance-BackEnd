import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

  constructor(props){
    console.log('constructor');
    super(props);
    this.state = {count:0};
  }

  componentDidMount(){
    console.log("componentDidMount");
  }

  shouldComponentUpdate(props,newState){
     if(newState.count%3 == 0){
       return true;
     }else{
       return false;
     }
  }

  componentDidUpdate(){
    console.log("componentDidUpdate");
  }



  handleClick(){
    this.setState({count:this.state.count+1});
  }

  render() {
    return (
      <div className="App">
        <button onClick = {this.handleClick.bind(this)}>Click Me</button>
        <br/>{this.state.count}
      </div>
    );
  }
}

export default App;
