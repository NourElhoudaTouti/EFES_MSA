import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './SearchCitizen.css';
const SearchCitizen = () => {
    const [cin, setCin] = useState('');
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleSearch = async () => {
        try {
            const response = await fetch(`http://localhost:8099/api/citizen/getCitizen/${cin}`);
            if (response.ok) {
                const citizen = await response.json();
                navigate(`/citizen/${citizen.cin}`);
            } else {
                setMessage('No citizen found with that CIN / لم يتم العثور على مواطن بهذا الرقم.');
            }
        } catch (error) {
            setMessage('Error fetching citizen: ' + error.message + ' / خطأ أثناء جلب المواطن: ' + error.message);
        }
    };

    return (
        <div className="form-section">
            <h1>Search Citizen / البحث عن مواطن</h1>
            <input
                type="text"
                value={cin}
                onChange={(e) => setCin(e.target.value)}
                placeholder="Enter CIN / ادخل الرقم الوطني"
            />
            <button onClick={handleSearch}>Search / بحث</button>
            {message && <p>{message}</p>}
        </div>
    );
};

export default SearchCitizen;
