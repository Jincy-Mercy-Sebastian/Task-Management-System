import { useState } from 'react'
import './App.css'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Welcome from './components/Welcome'
import ListTasks from './components/ListTasks'
import CreateTask from './components/CreateTask'
import UpdateTask from './components/UpdateTask'
import SearchTaskAdvanced from './components/SearchTaskAdvanced'


function App() {
  return (
    <div className="App">
          <Router>
            <Routes>
              <Route path="/" element={<Welcome />} > </Route>
              <Route path="/viewTasks" element={<ListTasks />} />
              <Route path="/createTask" element={<CreateTask />} />
              <Route path="/updateTask/:id" element={<UpdateTask />} />
              <Route path="/searchTaskbyDeadLine" element={<SearchTaskAdvanced/>} />
            </Routes>
          </Router>
    </div>

  );
}

export default App
