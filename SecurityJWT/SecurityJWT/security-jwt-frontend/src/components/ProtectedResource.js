import React, { useState } from 'react';
import axios from 'axios';

function ProtectedResource() {
    const [message, setMessage] = useState('');

    const handleClick = async () => {
        try {
            const token = localStorage.getItem('jwt');
            const response = await axios.get('http://localhost:8080/api/v1/demo-controller', {
                headers: { Authorization: `Bearer ${token}` }
            });
            setMessage(response.data);
        } catch (error) {
            setMessage('Failed to access protected content: ' + error.message);
        }
    };

    return (
        <div>
            <button onClick={handleClick}>Access Protected Content</button>
            <p>{message}</p>
        </div>
    );
}

export default ProtectedResource;
