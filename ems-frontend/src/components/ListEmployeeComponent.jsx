import React, { useEffect, useState } from 'react';
import { deleteEmployee, listEmployee } from '../services/EmployeeService';
import { useNavigate } from 'react-router-dom';

const ListEmployeeComponent = () => {
  const [employees, setEmployees] = useState([]);
  const navigator = useNavigate();

  useEffect(() => {
    getAllEmployee();
  }, []);

  const getAllEmployee = () => {
    listEmployee()
      .then((Response) => {
        setEmployees(Response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  function addNewEmployee() {
    navigator('/add-employee');
  }

  function updateEmployee(id) {
    navigator(`/edit-employee/${id}`);
  }

  function removeEmployee(id) {
    deleteEmployee(id)
      .then((response) => {
        getAllEmployee();
      })
      .catch((error) => {
        console.error(error);
      });
  }

  return (
    <div className='container'>
      <h2 className='text-center'>List of Employees</h2>

      <div className='d-flex justify-content-center mb-3'>
        <button className='btn btn-primary' onClick={addNewEmployee}>
          Add Employee
        </button>
      </div>

      <table className='table table-striped table-bordered'>
        <thead>
          <tr>
            <th>employee id</th>
            <th>employee first name</th>
            <th>employee last name</th>
            <th>employee email id</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((employee) => (
            <tr key={employee.id}>
              <td>{employee.id}</td>
              <td>{employee.firstName}</td>
              <td>{employee.lastName}</td>
              <td>{employee.email}</td>
              <td>
                <button className='btn btn-info' onClick={() => updateEmployee(employee.id)}>
                  Update
                </button>
                <button
                  className='btn btn-danger'
                  onClick={() => removeEmployee(employee.id)}
                  style={{ marginLeft: '10px' }}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListEmployeeComponent;
