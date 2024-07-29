import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';

const Login = () => {
  const [division, setDivision] = useState('');
  const [userId, setUserId] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleDivisionChange = (e) => {
    setDivision(e.target.value);
  };

  const handleUserIdChange = (e) => {
    setUserId(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!division || !userId || !password) {
      setError('Please fill out all fields.');
      return;
    }
    else{
      navigate('/todos')
    } 
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2>품의서 관리 시스템</h2>
        {error && <p className="error">{error}</p>}
        <div className="form-group">
          <label>승인 구분:</label>
          <input type="text" value={division} onChange={handleDivisionChange} />
        </div>
        <div className="form-group">
          <label>사용자 ID:</label>
          <input type="text" value={userId} onChange={handleUserIdChange} />
        </div>
        <div className="form-group">
          <label>Password:</label>
          <input type="password" value={password} onChange={handlePasswordChange} />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;
