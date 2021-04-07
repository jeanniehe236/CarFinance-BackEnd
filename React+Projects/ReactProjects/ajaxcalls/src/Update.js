import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class Update extends Component {
  componentDidMount(){
    this.setState({
      id: "",
      name: "",
      description: "",
      price: ""
  });
  }


  handlePostClick(event){
    const axios = require('axios');
    alert(this.state)
    axios.put('http://localhost:8080/api/products/',{
      id:this.state.id,
    name:this.state.name,
description:this.state.description,
price:this.state.price
})
    .then(res=>{
      console.log(res.data);
    }).catch(error=>{
      console.error('Error',error)
    })
  }
  
onIdChange = (e) => {
    this.setState({id: e.target.value});
}

onNameChange = (e) => {
  this.setState({name: e.target.value});
}

onDescriptionChange = (e) => {
  this.setState({description: e.target.value});
}

onPriceChange = (e) => {
  this.setState({price: e.target.value});
}
  render() {
    return (
      <div className="App">
            Id: <input onChange={this.onIdChange}/>
      Enter Name: <input onChange={this.onNameChange}/>
      Enter Description: <input onChange={this.onDescriptionChange}/>
      Enter Price: <input onChange={this.onPriceChange}/>
      <button onClick={this.handlePostClick.bind(this)}>Update</button>
      </div>
    );
  }
}

export default Update;
