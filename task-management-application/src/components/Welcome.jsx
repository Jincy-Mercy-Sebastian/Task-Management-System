import React from 'react';
import '../css/styles.css'; // Custom CSS
import { useNavigate } from 'react-router-dom';

const Welcome = () => {
    const navigate = useNavigate();

    const listTasks = () => {
        navigate('/viewTasks');
    };

    return (
        <div className="welcome-container">
             <h1>Task Management</h1>
            <p>Welcome to the Task Management System</p>

            <button className="btn" onClick={listTasks}>View Tasks</button>


        </div>
    );
};


export default Welcome;