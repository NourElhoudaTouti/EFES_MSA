import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from 'react-router-dom';
import LoginForm from './components/LoginForm';
import RegistrationForm from './components/RegistrationForm';
import backgroundImage from './moroccan-pattern.png';
import './App.css';

function App() {
    const backgroundStyle = {
        height: '100vh',  // Full viewport height
        backgroundImage: `url(${backgroundImage})`,
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'center center',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center'
    };

    return (
        <Router>
            <div style={backgroundStyle}>
                <div className="container">
                    <Routes>
                        <Route path="/register" element={
                            <>
                                <RegistrationForm />
                                <p className="linkContainer">
                                    Already have an account? <Link to="/">Login here</Link>
                                </p>
                            </>
                        } />
                        <Route path="/" element={
                            <>
                                <LoginForm />
                                <p className="linkContainer">
                                    Don't have an account? <Link to="/register">Register here</Link>
                                </p>
                            </>
                        } />
                        <Route path="*" element={<Navigate replace to="/" />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
