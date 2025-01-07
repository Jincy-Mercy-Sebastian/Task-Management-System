import React from 'react';
import { useState } from 'react';
import TicketService from './TicketService';
import { useNavigate } from 'react-router-dom';
import '../css/styles.css'; 


const  CreateTask = () => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [status, setStatus] = useState('');
    const [priority, setPriority] = useState('');
    const [deadline, setDeadline] = useState('');

    const navigate = useNavigate();

    const createTask = (e) => {
        e.preventDefault();

        const task = {
            title: title,
            description: description,
            status: status,
            priority: priority,
            deadline: deadline
        };

        TicketService.createTask(task)
            .then(() => {
                navigate('/viewTasks');
            })
            .catch((error) => {
                console.error('Error while adding task:', error);
            });
    };
    
    return (
        <div className='create-task-container'>
            <h2> Create Task</h2> 
            <form onSubmit={createTask}>
                <div className='form-group'>
                    <label>Title</label><br/>
                    <input type='text' value={title} onChange={(e) => setTitle(e.target.value)} />
                </div>
                <div className='form-group'>
                    <label>Description</label> <br/>
                    <input type='text' value={description} onChange={(e) => setDescription(e.target.value)} />
                </div>
                <div className='form-group'>
                    <label>Status</label><br/>
                    <input type='text' value={status} onChange={(e) => setStatus(e.target.value)} />
                </div>
                <div className='form-group'>
                    <label>Priority</label><br/>
                    <input type='text' value={priority} onChange={(e) => setPriority(e.target.value)} />
                </div>
                <div className='form-group'>
                    <label>Deadline</label><br/>
                    <input type='text' value={deadline} onChange={(e) => setDeadline(e.target.value)} />
                </div>
                <div className="button-container">
                    <button className='btn btn-save' type='submit'>Save</button>
                    <button className='btn btn-cancel' onClick={() => navigate('/viewTasks')}>Cancel</button>
                </div>
            </form>
        </div>
    );
}

export default CreateTask;