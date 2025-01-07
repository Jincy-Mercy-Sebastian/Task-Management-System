import React, { useState, useEffect } from 'react';
import TicketService from './TicketService';
import { useNavigate } from 'react-router-dom';
import '../css/listtask.css';
import UpdateTask from './UpdateTask';

const ListTasks = () => {
    const [tasks, setTasks] = useState([]);
    const navigate = useNavigate();
    
    const[status, setStatus] = useState('');
    const[priority, setPriority] = useState('');

    useEffect(() => {

        TicketService.getAllTasks().then((response) => {
            setTasks(response.data);
        }).catch((error) => {
            console.error('Error while fetching tasks:', error);
        });
    }, []);

    const addTask = () => {
        navigate('/createTask');
    };

    const updateTask = (id) => {
        navigate(`/updateTask/${id}`);
      };

    const  deleteTask = (id) => {
        const confirm = window.confirm('Are you sure you want to delete this task?');
        if (confirm) {
            TicketService.deleteTask(id)
            .then(() => {
                updatedTasks = tasks.filter((task) => task.id !== id);
                setTasks(updatedTasks);
            }).catch((error) => {
                console.error('Error while deleting task:', error);
        });
    }
    };

    const searchTask = (e) => {
        e.preventDefault();
        TicketService.searchTask(status, priority)
        .then((response) => {
            setTasks(response.data);
        }).catch((error) => {
            console.error('Error while searching tasks:', error);
        });
    };

    const searchTaskByDeadline = () => {
        navigate('/searchTaskbyDeadLine');
    };

    const goBack = () => {
        navigate('/');
      };

    return (
        <div className='list-task-container'>
            <h2>Tasks</h2>
            <div className='search-task'>
                
                <form onSubmit={searchTask}>
                    
                        <label htmlFor='status'> Status </label>
                        <input type='text' id='status' name='status' value={status} onChange={(e) => setStatus(e.target.value)} />
                    

                        <label htmlFor='priority'> Priority </label>
                        <input type='text' id='priority' name='priority' value={priority} onChange={(e) => setPriority(e.target.value)} />

                        <button type='submit'> Search </button>
                        <button type='submit' onClick={searchTaskByDeadline}> Advanced Search </button>
                        

                </form>                
            </div>

            <div >

                <table className='tasks-table'>
                    <thead>
                        <tr>
                            <th>Task Id</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Priority</th>
                            <th>Deadline</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        { tasks.map((task) => (
                                <tr key={task.id}>
                                    <td>{task.id}</td>
                                    <td>{task.title}</td>
                                    <td>{task.description}</td>
                                    <td>{task.status}</td>
                                    <td>{task.priority}</td>
                                    <td>{task.deadline}</td>
                                    <td className='button-btm'>
                                        <button onClick={() => updateTask(task.id)}>
                                        Update
                                        </button>
                                        <button onClick={() => deleteTask(task.id)}>
                                        Delete
                                        </button>
                                    </td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>

                <div className='button-btm'>
                <button className='btn btn-add' onClick={addTask}> Add Task</button>
                <button className="btn btn-back" onClick={goBack}> Back </button>
                </div>
            </div>
        </div>
    );
};

export default ListTasks;