import React, { Component } from 'react';


class StartCheckIn extends Component {
  handleSubmit(event){
    this.props.history.push('checkIn/'+this.reservationId)
}
    render() {
      return (
        <div>
          <h1>Flight CheckIn</h1>
         <h2>Enter the reservation id:</h2>
            <input type="text" onChange={(event)=>{this.reservationId=event.target.value}}/><br/>
            <button onClick={this.handleSubmit.bind(this)}>Next</button>
        </div>
      );
    }
  }
  
  export default StartCheckIn;