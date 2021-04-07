import React from 'react';
import './App.css';
import {Route,Switch} from 'react-router-dom';
import CheckIn from './components/CheckIn';
import StartCheckIn from './components/StartCheckIn';
import ConfirmCheckin from './components/ConfirmCheckIn';



class App extends React.Component {
  render() {
    return (
      <div className="App">
       <Switch>
        <Route exact path="/" component={StartCheckIn}/>
        <Route exact path="/checkIn/:reservationId" component={CheckIn}/>
        <Route exact path="/confirmCheckIn" component={ConfirmCheckin}/>
       </Switch>
      </div>
    );
  }
}

export default App;