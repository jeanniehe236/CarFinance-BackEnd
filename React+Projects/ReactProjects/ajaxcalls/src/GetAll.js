import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class Create extends Component {

constructor(props) {
  super(props)
   this.state ={ products: []};
}
  componentDidMount(){
    const axios = require('axios');
    axios.get('http://localhost:8080/api/products/')
    .then(res=>{
      this.setState({products : res.data});
    }).catch(error=>{
      console.error('Error',error)
    })
  }
  
  render() {
    var liElements = [];
    alert(this.state.products)
    for(var product of this.state.products){
        liElements.push(<li>{product.name}</li>);
      }
    return (
      <div className="App">
     <ul>{liElements}</ul>
      </div>
    );
  }
  
}

export default Create;
