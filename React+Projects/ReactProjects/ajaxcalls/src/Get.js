import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class Get extends Component {


constructor(props) {
  super(props)
   this.state ={product : {} };
}
  onClick(){
    const axios = require('axios');
    console.log(this.state.id);
    axios.get('http://localhost:8080/api/products/'+this.state.id)
    .then(res=>{
      console.log(res.data[0]);
      this.setState({product : res.data[0]});
      console.log(this.state.product.id);
    }).catch(error=>{
      console.error('Error',error)
    })
  }

  onIdChange = (e) => {
    this.setState({id: e.target.value});
  }
  
  render() {
    
    return (
      <div className="App">
           Enter Id: <input onChange={this.onIdChange}/>
        <button onClick={this.onClick.bind(this)}>Get Product</button>
        <br/>

      Name {this.state.product.name}
      Description {this.state.product.description}
      Price {this.state.product.price}
      </div>
    );
  }
  
}

export default Get;
