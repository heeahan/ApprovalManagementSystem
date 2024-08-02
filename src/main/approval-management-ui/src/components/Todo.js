import React from 'react';
import { useEffect, useState } from 'react';
import { Link, useParams, useNavigate } from 'react-router-dom';
import './Todo.css'

function TodoList() {
    const { userId, apprDiv } = useParams();
    const navigate = useNavigate();
    const [todos, setTodos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`/api/appr/get?userId=${userId}&apprDiv=${apprDiv}`);
                if (!response.ok) {
                    throw new Error('Not responsing...');
                }
                const data = await response.json();
                setTodos(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, [userId, apprDiv]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>처리 대기중인 품의서 없습니다.</div>;
    }

    const viewDetail = (apprId) => {
        navigate(`/todos/${userId}/${apprDiv}/${apprId}`);
    }

    return (
        <div>
            <h1>Approval Check To-Do List</h1>
            <h4>처리 대기중인 품의서 리스트 입니다.</h4>
            {error && <p>{error}</p>}

            <table>
                <thead>
                    <tr>
                        <th>품의서 ID</th>
                        <th>업무 구분</th>
                        <th>승인 유형</th>
                        <th>품의서 제목</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {todos.map((todo, index) => (
                        <tr key={index}>
                            <td>{todo[0]}</td>
                            <td>{todo[1]}</td>
                            <td>{todo[2]}</td>
                            <td>{todo[3]}</td>
                            <td>
                                <button onClick={() => viewDetail(todo[0])}>품의서 조회</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );


};

export default TodoList;







// // （for test) 작동 코드 정리
// import React from 'react';
// import { useEffect, useState } from 'react';
// import axios from 'axios';
// import Login from './Login'
// import './Todo.css'

// const TodoList = ({ userId, apprDiv }) => {
//     const [todos, setTodos] = useState([]);
//     const [error, setError] = useState(null);

//     const handleFormSubmit = (userId, apprDiv) => {
//         fetch(`/api/appr/get?userId=${userId}&apprDiv=${apprDiv}`)
//             .then(response => {
//                 if (!response.ok) {
//                     if (response.status === 204) {
//                         throw new Error('No content');
//                     }
//                     throw new Error('Error fetching todo list');
//                 }
//                 return response.json();
//             })
//             .then(data => setTodos(data))
//             .catch(error => setError(error.message));
//     };

//     const handleViewApproval = (apprId) => {
//         console.log(`Viewing approval with ID: ${apprId}`);
//         // history.push(`/approvals/${approvalId}`); // If using react-router
//     };

//     return (
//         <div>
//             <Login onSubmit={handleFormSubmit} />
//             <h1>To-Do List</h1>
//             {error && <p>{error}</p>}

//             <table>
//                 <thead>
//                     <tr>
//                         <th>품의서 ID</th>
//                         <th>업무 구분</th>
//                         <th>승인 유형</th>
//                         <th>품의서 제목</th>
//                         <th></th>
//                     </tr>
//                 </thead>
//                 <tbody>
//                     {todos.map((todo, index) => (
//                         <tr key={index}>
//                             <td>{todo[0]}</td>
//                             <td>{todo[1]}</td>
//                             <td>{todo[2]}</td>
//                             <td>{todo[3]}</td>
//                             <td>
//                                 <button onClick={() => handleViewApproval(todo[0])}>품의서 조회</button>
//                             </td>
//                         </tr>
//                     ))}
//                 </tbody>
//             </table>
//         </div>
//     );


// };

// export default TodoList;

