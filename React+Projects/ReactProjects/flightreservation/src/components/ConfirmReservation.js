import React from 'react';

class ConfirmReservation extends React.Component{

    render(){
        return (<div>
            <b>Flight Reservation is complete.The confirmation code is {this.props.match.params.reservationId}</b>
        </div>)
    }
}

export default ConfirmReservation;