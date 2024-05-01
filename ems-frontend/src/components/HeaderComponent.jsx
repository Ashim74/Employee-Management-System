import React from 'react'
import { useNavigate } from 'react-router-dom';

const HeaderComponent = () => {

  const navigator = useNavigate();
  
  const handleRedirect = () =>{
    navigator('/');
  }

  return (
    <div>
        <header>
            <nav className='navbar navbar-dark bg-dark'>
               <a className='navbar-brand' href = "#" onClick={handleRedirect} >Employee Management System</a> 
            </nav>
        </header>
    </div>
  )
}

export default HeaderComponent