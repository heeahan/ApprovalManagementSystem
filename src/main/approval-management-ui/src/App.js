import React, { Component, useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Redirect, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Todo from'./components/Todo';
import Detail from './components/Detail';
import './App.js'

const App = () => {
    return (

        <Router>
            <Routes>
                <Route path='/' element = {<Login />} />
                <Route path="/todos/:userId/:apprDiv" element={<Todo />} />
                {/* <Route path='/todos/detail' element={<Detail />} /> */}
                {/* <Route path='/todos/detail/:apprId' element={<Detail />} /> */}
                <Route path='/todos/:userId/:apprDiv/:apprId' element={<Detail />} />
            </Routes>
        </Router>
    );
};

export default App;




// (for test) 작동 코드
// import React, { useState } from 'react';
// import { BrowserRouter as Router, Route, Routes, Redirect, Navigate } from 'react-router-dom';
// import Login from './components/Login'; // Adjust the import based on your file structure
// import TodoList from'./components/Todo';

// const App = () => {
//     const [userId, setUserId] = useState('');
//     const [apprDiv, setApprDiv] = useState('');

//     const handleLoginSubmit = (id, division) => {
//         setUserId(id);
//         setApprDiv(division);
//     };

//     return (
 
//         <Router>
//             <Routes>
//                 <Route path="/login" element={<Login onSubmit={handleLoginSubmit} />} />
            
//                 <Route path="/todos" element={<TodoList />} /> 
//                 <Route path="/" element={<Navigate to="/login" />} />
//             </Routes>
//         </Router>
//     );
// };

// export default App;
