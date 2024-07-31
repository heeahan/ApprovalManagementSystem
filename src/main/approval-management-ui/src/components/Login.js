import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css'

function Login() {
    const [userId, setUserId] = useState('');
    const [apprDiv, setApprDiv] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (event) => {
        event.preventDefault();

        navigate(`/todos/${userId}/${apprDiv}`)
    }

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>User ID:
                    <input
                        type="text"
                        value={userId}
                        onChange={(e) => setUserId(e.target.value)}
                        required
                    />
                </label>

            </div>
            <div>
                <label>Approval Division:
                    <input
                        type="text"
                        value={apprDiv}
                        onChange={(e) => setApprDiv(e.target.value)}
                        required
                    /> </label>
            </div>
            <button type="submit">Log In</button>
        </form>

    );
};

export default Login;






// (for test) 작동 코드
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
