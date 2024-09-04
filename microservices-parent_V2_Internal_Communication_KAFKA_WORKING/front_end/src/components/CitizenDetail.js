import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './CitizenDetail.css';

const CitizenDetail = () => {
    const { cin } = useParams();
    const [citizen, setCitizen] = useState(null);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCitizen = async () => {
            try {
                const response = await axios.get(`http://localhost:8099/api/citizen/getCitizen/${cin}`);
                if (response.status === 200) {
                    setCitizen(response.data);
                } else {
                    setCitizen(null);
                }
            } catch (error) {
                console.error('Error fetching citizen:', error);
                setCitizen(null);
            } finally {
                setLoading(false);
            }
        };

        fetchCitizen();
    }, [cin]);

    const handleUpdate = async () => {
        try {
            const updatedCitizen = {
                NameFR: citizen.nameFR,
                NameAR: citizen.nameAR,
                Birth_Date: citizen.birth_Date,
                CNSS: citizen.cnss,
                CIN: citizen.cin,
                addressFR: citizen.addressFR,
                addressAR: citizen.addressAR,
            };

            await axios.put(`http://localhost:8099/api/citizen/${citizen.id}`, updatedCitizen);
            console.log('Citizen updated successfully');
        } catch (error) {
            console.error('Failed to update citizen:', error);
        }
    };

    const handleDelete = async () => {
        try {
            await axios.delete(`http://localhost:8099/api/citizen/${citizen.id}`);
            navigate('/');
        } catch (error) {
            console.error('Error deleting citizen:', error);
        }
    };

    if (loading) return <p>Loading... / جار التحميل...</p>;

    if (!citizen) return <p>Citizen not found / لم يتم العثور على المواطن</p>;

    return (
        <div className="form-section">
            <h1>Citizen Detail / تفاصيل المواطن</h1>
            <form>
                <label>Name and Surname / الاسم الشخصي والعائلي</label>
                <input
                    type="text"
                    value={citizen.nameFR}
                    onChange={(e) => setCitizen({ ...citizen, nameFR: e.target.value })}
                />
                <input
                    type="text"
                    value={citizen.nameAR}
                    onChange={(e) => setCitizen({ ...citizen, nameAR: e.target.value })}
                />
                <label>Date of Birth / تاريخ الازدياد</label>
                <input
                    type="date"
                    value={citizen.birth_Date}
                    onChange={(e) => setCitizen({ ...citizen, birth_Date: e.target.value })}
                />
                <label>CNSS / الصندوق الوطني للضمان الاجتماعي</label>
                <input
                    type="text"
                    value={citizen.cnss}
                    onChange={(e) => setCitizen({ ...citizen, cnss: e.target.value })}
                />
                <label>National ID / البطاقة الوطنية</label>
                <input
                    type="text"
                    value={citizen.cin}
                    onChange={(e) => setCitizen({ ...citizen, cin: e.target.value })}
                />
                <label>Address / عنوان السكن</label>
                <input
                    type="text"
                    value={citizen.addressFR}
                    onChange={(e) => setCitizen({ ...citizen, addressFR: e.target.value })}
                />
                <input
                    type="text"
                    value={citizen.addressAR}
                    onChange={(e) => setCitizen({ ...citizen, addressAR: e.target.value })}
                />
                <button type="button" onClick={handleUpdate}>Update / تحديث</button>
                <button type="button" onClick={handleDelete}>Delete / حذف</button>
            </form>
        </div>
    );
};

export default CitizenDetail;
