import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class Delete extends Component {


constructor(props) {
  super(props)
   this.state ={product : {} };
}
  onClick(){
    const axios = require('axios');
    console.log(this.state.id);
    axios.delete('http://localhost:8080/api/products/'+this.state.id)
    .then(res=>{
      console.log(res.data);
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
        <button onClick={this.onClick.bind(this)}>Delete</button>
        <br/>


      </div>
    );
  }
  
}

export default Delete;
