import React, { Component } from 'react';
import {
  Table,
  TableBody,
  TableRow,
  TableRowColumn
} from 'material-ui';

export default class SimpleTable extends Component {
  render() {
    return (
    <Table selectable={ false } multiSelectable={ false } style={{ width: 700 }}>
      <TableBody displayRowCheckbox={ false }>
        {this.props.tableData.map( (row, index) => (
          <TableRow key={index}>
            <TableRowColumn>{row.key}</TableRowColumn>
            <TableRowColumn>{row.value}</TableRowColumn>
          </TableRow>
        ))}
      </TableBody>
    </Table>
    );
  }
}