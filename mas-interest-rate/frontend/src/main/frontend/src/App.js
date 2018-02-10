import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import './App.css';

import { SimpleDashboard } from './components';

class App extends Component {
  render() {
    return (
      <MuiThemeProvider>
        <div className="App">
          <header>
            <h1 className="App-title">MAS Average Interest Rate</h1>
          </header>
          <div align="center">
            <SimpleDashboard />
          </div>
        </div>
      </MuiThemeProvider>
    );
  }
}

export default App;