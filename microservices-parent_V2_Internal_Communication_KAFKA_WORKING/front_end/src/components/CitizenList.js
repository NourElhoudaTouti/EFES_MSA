import React, { useEffect, useState } from 'react';
import { getCitizens } from '../api';

function CitizenList({ setCitizens }) {
    const [citizens, setLocalCitizens] = useState([]);

    useEffect(() => {
        const fetchCitizens = async () => {
            try {
                const data = await getCitizens();
                setLocalCitizens(data);
                setCitizens(data);
            } catch (error) {
                console.error('Failed to fetch citizens:', error);
            }
        };

        fetchCitizens();
    }, [setCitizens]);

    return (
        <div className="form-section">
            <h1>Citizens List / لائحة المواطنين</h1>
            <ul>
                {citizens.length === 0 ? (
                    <li>No citizens found / لم يتم العثور على مواطنين.</li>
                ) : (
                    citizens.map(citizen => (
                        <li key={citizen.id}>
                            {citizen.nameFR} - {citizen.cin}
                        </li>
                    ))
                )}
            </ul>
        </div>
    );
}

export default CitizenList;
