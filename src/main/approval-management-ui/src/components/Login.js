// import React, { useState } from 'react';
// import { useNavigate } from 'react-router-dom';
// import './Login.css';

// const Login = () => {
//   const [apprDiv, setApprDiv] = useState('');
//   const [userId, setUserId] = useState('');
//   const [password, setPassword] = useState('');
//   const [error, setError] = useState('');
//   const navigate = useNavigate();

//   const handleApprDivChange = (e) => {
//     setApprDiv(e.target.value);
//   };

//   const handleUserIdChange = (e) => {
//     setUserId(e.target.value);
//   };

//   const handlePasswordChange = (e) => {
//     setPassword(e.target.value);
//   };

// //   // const handleSubmit = (e) => {
// //   //   e.preventDefault();
// //   //   onSubmit(userId, apprDiv);

// const handleSubmit = async (e) => {
//     e.preventDefault();
//     // onSubmit(userId, apprDiv);
//     if (!apprDiv || !userId || !password) {
//         setError('Please fill out all fields.');
//         return;
//     }
//     else {
//         navigate('/todos')
//     }
// };

//   return (
//     <div className="login-container">
//       <form className="login-form" onSubmit={handleSubmit}>
//         <h2>품의서 관리 시스템</h2>
//         {error && <p className="error">{error}</p>}
//         <div className="form-group">
//           <label>승인 구분:</label>
//           <input type="text" value={apprDiv} onChange={handleApprDivChange} />
//           {/* <input 
//                     type="text" 
//                     value={userId} 
//                     onChange={(e) => setUserId(e.target.value)} 
//                     required 
//                 /> */}

//         </div>
//         <div className="form-group">
//           <label>사용자 ID:</label>
//           <input type="text" value={userId} onChange={handleUserIdChange} />
//           {/* <input 
//                     type="text" 
//                     value={apprDiv} 
//                     onChange={(e) => setApprDiv(e.target.value)} 
//                     required 
//                 /> */}
//         </div>
//         <div className="form-group">
//           <label>Password:</label>
//           <input type="password" value={password} onChange={handlePasswordChange} />
//         </div>
//         <button type="submit">Login</button>
//       </form>
//     </div>
//   );
// };

// export default Login;







import React, { useState } from 'react';
import { Navigate } from 'react-router-dom';
import Todo from './Todo';
import TodoList from './Todo';

const Login = ({ onSubmit }) => {
    const [userId, setUserId] = useState('');
    const [apprDiv, setApprDiv] = useState('');
    const [error, setError] = useState('')

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!userId || !apprDiv) {
            setError('Both fields are required');
            return;
        }
        onSubmit(userId, apprDiv);
    };


    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>User ID:</label>
                <input
                    type="text"
                    value={userId}
                    onChange={(e) => setUserId(e.target.value)}
                    required
                />
                
            </div>
            <div>
                <label>Approval Division:</label>
                <input
                    type="text"
                    value={apprDiv}
                    onChange={(e) => setApprDiv(e.target.value)}
                    required
                />
            </div>
            <button type="submit">Submit</button>
        </form>
        
    );
};

export default Login;






// 작동 코드
// import React, { useState } from 'react';
// import { Navigate } from 'react-router-dom';

// const Login = ({ onSubmit }) => {
//     const [userId, setUserId] = useState('');
//     const [apprDiv, setApprDiv] = useState('');
//     const [error, setError] = useState('')

//     const handleSubmit = (e) => {
//         e.preventDefault();
//         if (!userId || !apprDiv) {
//             setError('Both fields are required');
//             return;
//         }
//         onSubmit(userId, apprDiv);
//     };


//     return (
//         <form onSubmit={handleSubmit}>
//             <div>
//                 <label>User ID:</label>
//                 <input
//                     type="text"
//                     value={userId}
//                     onChange={(e) => setUserId(e.target.value)}
//                     required
//                 />
//             </div>
//             <div>
//                 <label>Approval Division:</label>
//                 <input
//                     type="text"
//                     value={apprDiv}
//                     onChange={(e) => setApprDiv(e.target.value)}
//                     required
//                 />
//             </div>
//             <button type="submit">Submit</button>
//         </form>
//     );
// };

// export default Login;
