import React from 'react';
import axios from 'axios';

class CheckIn extends React.Component {

  state = {}

  componentWillMount(){
      axios.get("http://localhost:8080/flightservices/reservations/"+this.props.match.params.reservationId)
      .then(res=>{
        console.log(res.data)
          this.setState(res.data);
      })
  }

  handleSubmit(event){
    event.preventDefault();
  
    axios.put("http://localhost:8080/flightservices/reservations",{
      id:this.props.match.params.reservationId,
      checkIn:true,
      numberOfBags:this.numberOfBags
  })
    .then(res=>{
        this.props.history.push('/confirmCheckIn')
    })
}


    render() {
      if (this.state.flight === undefined) {
        return null;
      }
  
      return (
        <div>
        <h1>Review Details</h1>
<h2>Flight Details:</h2>
Airlines: {this.state.flight.operatingAirlines}<br/>
Flight No: {this.state.flight.flightNumber}<br/>
Departure City: {this.state.flight.departureCity}<br/>
Arrival City: {this.state.flight.arrivalCity}<br/>
Date Of Departure: {this.state.flight.dateOfDeparture}<br/>
Estimated Departure Time: {this.state.flight.estimatedDepartureTime}<br/>
<h2>Passenger Details:</h2>

First Name: {this.state.passenger.firstName}<br/>
Last Name: {this.state.passenger.lastName}<br/>
Email : {this.state.passenger.email}<br/>
Phone: {this.state.passenger.phone}<br/>
Enter the number of bags to check in:<input type="text" onChange={(event)=>{this.numberOfBags=event.target.value}}/>
<button onClick={this.handleSubmit.bind(this)}>CheckIn</button>
        </div>
      );
    }
  }

  export default CheckIn;