import React, { Component } from 'react'
import EmployeeService from '../service/EmployeeService';

export default class EmployeeComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            employeeDto : {},
            departmentDto : {},
            organisationDto : {}
        }
    }

    componentDidMount(){
        EmployeeService.getEmployee().then((response) => {
            this.setState({employeeDto : response.data.employeeDto})
            this.setState({departmentDto : response.data.departmentDto})
            this.setState({organisationDto : response.data.organisationDto})

            console.log(this.state.employeeDto)
            console.log(this.state.departmentDto)
            console.log(this.state.organisationDto)
        });
    }
    
  render() {
    return (
      <div>
        
      </div>
    )
  }
}

