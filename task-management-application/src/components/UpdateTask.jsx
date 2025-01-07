import React, { useEffect, useState } from 'react';
import TicketService from './TicketService';
import { useParams, useNavigate } from 'react-router-dom';

const UpdateTask = () => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [status, setStatus] = useState('');
    const [priority, setPriority] = useState('');
    const [deadline, setDeadline] = useState('');

    const { id  } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        TicketService.getTaskById(id)
        .then((response) => {
            const task = response.data;
            setTitle(task.title);
            setDescription(task.description);
            setStatus(task.status);
            setPriority(task.priority);
            setDeadline(task.deadline);

        }).catch((error) => {
            console.error('Error fetching task details', error)
        });

    },[id]);

    const updateStudent = (e) => {
        e.preventDefault();

        const task ={
            title: title,
            description: description,
            status: status,
            priority: priority,
            deadline: deadline
        };

        TicketService.updateTask(id, task)
            .then(() => {
                navigate('/viewTasks');
            })
            .catch((error) => {
                console.error('Error while updating task:', error);
            });
    };

    const cancel = () => {
        navigate('/');
      };

    return (
        <div>
            <h2> Update Task</h2>
            <form onSubmit={updateStudent}>
                <div>
                    <label htmlFor='title'> Title </label>
                    <input
                        type='text'
                        id='title'
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label htmlFor='description'> Description </label>
                    <input
                        type='text'
                        id='description'
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label htmlFor='status'> Status </label>
                    <input
                        type='text'
                        id='status'
                        value={status}
                        onChange={(e) => setStatus(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor='priority'> Priority </label>
                    <input
                        type='text'
                        id='priority'
                        value={priority}
                        onChange={(e) => setPriority(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label htmlFor='deadline'> Deadline </label>
                    <input
                        type='date'
                        id='deadline'
                        value={deadline}
                        onChange={(e) => setDeadline(e.target.value)}
                        required
                    />
                </div>
                

                <div className="button-group">
                    <button type="submit" className="btn btn-save">
                        Save
                    </button>
                    <button type="button" className="btn btn-cancel" onClick={cancel}>
                        Cancel
                    </button>
                </div>
            </form>
        </div>
    );
};

export default UpdateTask;