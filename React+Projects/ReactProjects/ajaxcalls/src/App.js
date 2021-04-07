import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

  handleClick(event){
    const axios = require('axios');
    alert(event.target.value)

    axios.get('https://restcountries.eu/rest/v2/all')
    .then(res=>{
      console.log(res.data);
    }).catch(error=>{
      console.error('Error',error)
    })
  }

  handlePostClick(event){
    const axios = require('axios');

    axios.post('http://test-routes.herokuapp.com/test/uppercase',{message:"All the power is with in me"})
    .then(res=>{
      console.log(res.data);
    }).catch(error=>{
      console.error('Error',error)
    })
  }
  
  render() {
    return (
      <div className="App">
       <button onClick={this.handleClick.bind(this)} name="mybutton" value="test">Get Countries</button>
       <button onClick={this.handlePostClick.bind(this)}>Convert to UpperCase</button>
      </div>
    );
  }
}

export default App;
