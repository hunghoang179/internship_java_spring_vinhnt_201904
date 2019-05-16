import React, { useState, useEffect } from 'react';
import './App.css';
import { BrowserRouter as Router } from 'react-router-dom'
import Menu from './components/Share/Menu'
import Routes from './components/Share/Routes'

function App() {

  const [sessionUser, setSessionUser] = useState({});

  useEffect(() => {
    fetch(`/api/session/user`)
      .then(
        function (response) {
          if (response.status !== 200) {
            console.log('Lỗi, mã lỗi ' + response.status);
            return;
          }
          return response.json();
        }
      )
      .then(responseJson => setSessionUser(responseJson))
      .catch(err =>
        console.log('Error :-S', err)
      );
  }, [])

  return (
    <div className="App">
      <Router>
        <Menu sessionUser={sessionUser} />
        <Routes sessionUser={sessionUser} />
      </Router>
    </div>
  );
}

export default App;
