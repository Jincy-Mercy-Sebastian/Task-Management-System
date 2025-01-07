import axios from 'axios';
import React, { Component } from 'react';

const TaskMGMT_URL = `http://localhost:8085/ticket`

class TicketService extends Component {
    getAllTasks() {

        return axios.get(`${TaskMGMT_URL}/viewTasks`)   
            }

    createTask(task) {
        return axios.post(`${TaskMGMT_URL}/createTask`, task)
    }

    updateTask(id,task) {
        return axios.put(`${TaskMGMT_URL}/updateTask/`+id, task)
    }

    getTaskById(id){
        return axios.get(`${TaskMGMT_URL}/get/`+id)
    }

    deleteTask(id){
        return axios.delete(`${TaskMGMT_URL}/deleteTask/`+id)
    }

    searchTask(status, priority) {
        const url = `${TaskMGMT_URL}/search?status=${status || ''}&priority=${priority || ''}`;
        return axios.get(url);
      }
      
      searchTaskByDeadLine(startDate, endDate) {
        const url = `${TaskMGMT_URL}/searchTaskbyDeadLine?startDate=${startDate || ''}&endDate=${endDate || ''}`;
        return axios.get(url);
      }

}

export default new TicketService();