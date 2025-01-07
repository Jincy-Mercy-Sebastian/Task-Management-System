import React, { useState, useEffect } from 'react';
import TicketService from './TicketService';
import { useNavigate } from 'react-router-dom';
import '../css/adv.css';

const SearchTaskAdvanced = () => {
    const[startDate, setStartDate] = useState('');
    const[endDate, setEndDate] = useState('');

    const [tasks, setTasks] = useState([]);

    const navigate = useNavigate();

    const searchTaskByDeadline = (e) => {
        e.preventDefault();
        TicketService.searchTaskByDeadLine(startDate, endDate)
        .then((response) => {
            setTasks(response.data);
        }).catch((error) => {
            console.error('Error while searching tasks:', error);
        });
    };

    const goBack = () => {
        navigate('/');
    };

    return (
        <div className='search-task'>
            <form onSubmit={searchTaskByDeadline}>
                
                    <label htmlFor='startDate'> Start Date </label>
                    <input type='date' id='startDate' name='startDate' value={startDate} onChange={(e) => setStartDate(e.target.value)} />
          
                    <label htmlFor='endDate'> End Date </label>
                    <input type='date' id='endDate' name='endDate' value={endDate} onChange={(e) => setEndDate(e.target.value)} />
                
                <button type='submit'> Search </button>
                <button  onClick={goBack}> Back </button>
            </form>
            

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
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default SearchTaskAdvanced;