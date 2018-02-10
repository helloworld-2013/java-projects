import React, { Component } from 'react';
import {
  Paper,
  TextField,
  RaisedButton,
  Divider
} from 'material-ui';

import SimpleTable from './SimpleTable';

export default class SimpleDashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      endingMonthValue: '',
      endingYearValue: '',
      tableData: [{
        key: 'Banks Trend',
        value: '-'  
      },{
        key: 'Financial Comp. Trend',
        value: '-'
      },{
        key: 'Banks Savings',
        value: '0.0'
      },{
        key: 'Banks Fixed Deposits 3m',
        value: '0.0'
      },{
        key: 'Banks Fixed Deposits 6m',
        value: '0.0'
      },{
        key: 'Banks Fixed Deposits 12m',
        value: '0.0'
      },{
        key: 'Financial Comp. Savings',
        value: '0.0'
      },{
        key: 'Financial Comp. Fixed Deposits 3m',
        value: '0.0'
      },{
        key: 'Financial Comp. Fixed Deposits 6m',
        value: '0.0'
      },{
        key: 'Financial Comp. Fixed Deposits 12m',
        value: '0.0'
      }]
    };
    this.handleMonthChange = this.handleMonthChange.bind(this);
    this.handleYearChange = this.handleYearChange.bind(this);
    this.handle3Months = this.handle3Months.bind(this);
    this.handle6Months = this.handle6Months.bind(this);
    this.handle1Year = this.handle1Year.bind(this);
  }

  handleMonthChange(e) {
    this.setState({
      endingMonthValue: e.target.value
    });
  }

  handleYearChange(e) {
    this.setState({
      endingYearValue: e.target.value
    });
  }

  handle3Months(e) {
    let self = this;
    let endMonth = this.state.endingMonthValue;
    let endYear = this.state.endingYearValue;

    fetch('/api/?duration=3&endMonth=' + endMonth + '&endYear=' + endYear, { 
      method: 'GET', headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      } 
    })
    .then(function(response) {
      response.json().then(function(data){
        self.updateTableData(self,data);
      });
    })
    .catch(function(err) {
      console.log(err);
    });
  }

  handle6Months(e) {
    let self = this;
    let endMonth = this.state.endingMonthValue;
    let endYear = this.state.endingYearValue;

    fetch('/api/?duration=6&endMonth=' + endMonth + '&endYear=' + endYear, { 
      method: 'GET', headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      } 
    })
    .then(function(response) {
      response.json().then(function(data){
        self.updateTableData(self,data);
      });
    })
    .catch(function(err) {
      console.log(err);
    });
  }

  handle1Year(e) {
    let self = this;
    let endMonth = this.state.endingMonthValue;
    let endYear = this.state.endingYearValue;

    fetch('/api/?duration=12&endMonth=' + endMonth + '&endYear=' + endYear, { 
      method: 'GET', headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      } 
    })
    .then(function(response) {
      response.json().then(function(data){
        self.updateTableData(self,data);
      });
    })
    .catch(function(err) {
      console.log(err);
    });
  }

  updateTableData(self,data) {
    self.setState({
      tableData: [{
        key: 'Banks Trend',
        value: data.banksTrend
      },{
        key: 'Financial Comp. Trend',
        value: data.fcTrend
      },{
        key: 'Banks Savings',
        value: parseFloat(data.banksSavingsDep.toFixed(2))
      },{
        key: 'Banks Fixed Deposits 3m',
        value: parseFloat(data.banksFixedDep3m.toFixed(2))
      },{
        key: 'Banks Fixed Deposits 6m',
        value: parseFloat(data.banksFixedDep6m.toFixed(2))
      },{
        key: 'Banks Fixed Deposits 12m',
        value: parseFloat(data.banksFixedDep12m.toFixed(2))
      },{
        key: 'Financial Comp. Savings',
        value: parseFloat(data.fcSavingsDep.toFixed(2))
      },{
        key: 'Financial Comp. Fixed Deposits 3m',
        value: parseFloat(data.fcFixedDep3m.toFixed(2))
      },{
        key: 'Financial Comp. Fixed Deposits 6m',
        value: parseFloat(data.fcFixedDep6m.toFixed(2))
      },{
        key: 'Financial Comp. Fixed Deposits 12m',
        value: parseFloat(data.fcFixedDep12m.toFixed(2))
      }]
    });
  }

  render() {
    return (
      <Paper>
        <TextField hintText='Ending month:' style={{width: 200}} onChange={this.handleMonthChange} />
        &nbsp;
        <TextField hintText='Ending year:' style={{width: 200}} onChange={this.handleYearChange} />
        <br/>
        <RaisedButton label='3 Months' primary={true} onClick={this.handle3Months} />
        &nbsp;
        <RaisedButton label='6 Months' primary={true} onClick={this.handle6Months} />
        &nbsp;
        <RaisedButton label='1 Year' primary={true} onClick={this.handle1Year} />
        <br/><br/>
        <Divider />
        <SimpleTable tableData={ this.state.tableData } />
      </Paper>
    );
  }
}