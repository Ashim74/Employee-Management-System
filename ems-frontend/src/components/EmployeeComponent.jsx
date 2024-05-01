import React, { useState, useEffect } from 'react';
import { createEmployee, getEmployee, updateEmployee } from '../services/EmployeeService';
import { useNavigate, useParams } from 'react-router-dom';

const EmployeeComponent = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [errors, setErrors] = useState({
        firstName: '',
        lastName: '',
        email: ''
    });

    const navigator = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) {
            getEmployee(id).then((response) => {
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setEmail(response.data.email);
            }).catch(error => {
                console.error(error);
            });
        }
    }, [id]);

    function saveOrUpdateEmployee(event) {
        event.preventDefault();

        if (validateForm()) {
            const employee = { firstName, lastName, email };

            if (id) {
                updateEmployee(id, employee).then((response) => {
                    navigator('/employees');
                }).catch(error => {
                    console.error(error);
                });
            } else {
                createEmployee(employee).then((response) => {
                    console.log(response.data);
                    navigator('/employees');
                }).catch(error => {
                    console.error(error);
                });
            }
        }
    }

    function validateForm() {
        let valid = true;
        const errorsCopy = {...errors};

        if (!firstName.trim()) {
            errorsCopy.firstName = 'First name is required';
            valid = false;
        } else {
            errorsCopy.firstName = '';
        }

        if (!lastName.trim()) {
            errorsCopy.lastName = 'Last name is required';
            valid = false;
        } else {
            errorsCopy.lastName = '';
        }

        if (!email.trim()) {
            errorsCopy.email = 'Email is required';
            valid = false;
        } else {
            errorsCopy.email = '';
        }

        setErrors(errorsCopy);
        return valid;
    }

    function pageTitle() {
        return (
            <h2 className='text-center'>
                {id ? 'Update Employee' : 'Add Employee'}
            </h2>
        );
    }

    return (
        <div className='container'>
            <br/>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    <div className='card-body'>
                        {pageTitle()}
                        <form>
                            <div className='form-group sb-2'>
                                <label className='form-label'>First Name</label>
                                <input
                                    type='text'
                                    placeholder='Enter Employee first Name'
                                    name='firstName'
                                    value={firstName}
                                    className={`form-control ${errors.firstName ? 'is-invalid' : ''}`}
                                    onChange={(event) => setFirstName(event.target.value)}
                                />
                                {errors.firstName && <div className='invalid-feedback'>{errors.firstName}</div>}
                            </div>
                            <div className='form-group sb-2'>
                                <label className='form-label'>Last Name</label>
                                <input
                                    type='text'
                                    placeholder='Enter Employee last Name'
                                    name='lastName'
                                    value={lastName}
                                    className={`form-control ${errors.lastName ? 'is-invalid' : ''}`}
                                    onChange={(event) => setLastName(event.target.value)}
                                />
                                {errors.lastName && <div className='invalid-feedback'>{errors.lastName}</div>}
                            </div>
                            <div className='form-group sb-2'>
                                <label className='form-label'>Email</label>
                                <input
                                    type='email'
                                    placeholder='Enter Employee Email'
                                    name='email'
                                    value={email}
                                    className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                    onChange={(event) => setEmail(event.target.value)}
                                />
                                {errors.email && <div className='invalid-feedback'>{errors.email}</div>}
                            </div>
                            <button className='btn btn-success' onClick={saveOrUpdateEmployee} style={{marginTop:'10px'}}> Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};


export default EmployeeComponent;
