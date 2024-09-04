import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import CitizenList from './components/CitizenList';
import AddCitizenForm from './components/AddCitizenForm';
import SearchCitizen from './components/SearchCitizen';
import CitizenDetail from './components/CitizenDetail';
import Home from './components/Home';
import ServiceSelection from './components/ServiceSelection';
import './App.css';  // Importing the CSS file
import AUILogo from './AUIlogo.png';  // Import the AUI logo
import userLogo from './userlogo.png';  // Import the user profile logo

function App() {
    return (
        <Router>
            <div className="container">
                <div className="top-nav">
                    <div className="logo">
                        <img src={AUILogo} alt="AUI Logo" />
                    </div>
                    <nav className="nav-container">
                        <ul>
                            <li><Link to="/">Home / الصفحة الرئيسية</Link></li>
                            <li><Link to="/service-selection">Select Service / اختر خدمة</Link></li>
                            <li><Link to="/search">Search Citizen / البحث عن مواطن</Link></li>
                        </ul>
                    </nav>
                    <div className="profile">
                        <img src={userLogo} alt="User Profile" className="profile-icon" />
                        <div className="profile-dropdown">
                            <Link to="/profile">Profile / الملف الشخصي</Link>
                            <Link to="/logout">Logout / تسجيل الخروج</Link>
                        </div>
                    </div>
                </div>
                <Routes>
                    <Route path="/" element={<ServiceSelection />} />
                    <Route path="/search" element={<SearchCitizen />} />
                    <Route path="/citizen/:cin" element={<CitizenDetail />} />
                    <Route path="/service-selection" element={<ServiceSelection />} />
                    <Route path="/citizen-management" element={<div><AddCitizenForm /><CitizenList /></div>} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
