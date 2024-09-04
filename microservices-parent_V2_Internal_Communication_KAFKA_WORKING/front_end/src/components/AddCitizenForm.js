import React, { useState, useEffect } from 'react';
import { createCitizen, updateCitizen, deleteCitizen } from '../api';

function AddCitizenForm({ citizenInitial = null, setCitizens }) {
    const [formData, setFormData] = useState({
        NameFR: '',
        NameAR: '',
        Birth_Date: '',
        CNSS: '',
        CIN: '',
        addressFR: '',
        addressAR: '',
    });
    const [message, setMessage] = useState("");
    const [loading, setLoading] = useState(false);
    const [editingCitizen, setEditingCitizen] = useState(null);

    useEffect(() => {
        if (citizenInitial) {
            setEditingCitizen(citizenInitial);
            setFormData(citizenInitial);
        }
    }, [citizenInitial]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({ ...prevState, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingCitizen) {
                await updateCitizen(editingCitizen.id, formData);
                setCitizens(prev => prev.map(item => item.id === editingCitizen.id ? formData : item));
                setMessage("Citizen updated successfully / تم تحديث المواطن بنجاح.");
            } else {
                const newCitizen = await createCitizen(formData);
                setCitizens(prev => [...prev, newCitizen]);
                setMessage("Citizen added successfully / تمت إضافة المواطن بنجاح.");
            }
            setFormData({ NameFR: '', NameAR: '', Birth_Date: '', CNSS: '', CIN: '', addressFR: '', addressAR: '' });
            setEditingCitizen(null);
        } catch (error) {
            setMessage(`Failed to save citizen. Error: ${error.message} / فشل في حفظ المواطن. خطأ: ${error.message}`);
            console.error("Failed to save citizen:", error.message);
        } finally {
            setLoading(false);
        }
    };

    const handleDelete = async () => {
        if (!editingCitizen) {
            setMessage("No citizen selected for deletion / لم يتم تحديد مواطن للحذف.");
            return;
        }
        setLoading(true);
        try {
            await deleteCitizen(editingCitizen.id);
            setCitizens(prev => prev.filter(c => c.id !== editingCitizen.id));
            setFormData({ NameFR: '', NameAR: '', Birth_Date: '', CNSS: '', CIN: '', addressFR: '', addressAR: '' });
            setMessage("Citizen deleted successfully / تم حذف المواطن بنجاح.");
            setEditingCitizen(null);
        } catch (error) {
            setMessage(`Failed to delete citizen. Error: ${error.message} / فشل في حذف المواطن. خطأ: ${error.message}`);
            console.error("Failed to delete citizen:", error.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="form-section">
            <form onSubmit={handleSubmit}>
                {message && <p style={{ color: message.includes('Failed') ? 'red' : 'green' }}>{message}</p>}
                {loading && <p>Loading... / جار التحميل...</p>}
                <label>Name and Surname / الاسم الشخصي والعائلي</label>
                <input name="NameFR" value={formData.NameFR} onChange={handleChange} placeholder="Name FR" />
                <input name="NameAR" value={formData.NameAR} onChange={handleChange} placeholder="Name AR" />
                <label>Date of Birth / تاريخ الازدياد</label>
                <input type="date" name="Birth_Date" value={formData.Birth_Date} onChange={handleChange} placeholder="mm/dd/yyyy" />
                <label>CNSS / الصندوق الوطني للضمان الاجتماعي</label>
                <input name="CNSS" value={formData.CNSS} onChange={handleChange} placeholder="CNSS" />
                <label>National ID / البطاقة الوطنية</label>
                <input name="CIN" value={formData.CIN} onChange={handleChange} placeholder="CIN" />
                <label>Address / عنوان السكن</label>
                <input name="addressFR" value={formData.addressFR} onChange={handleChange} placeholder="Address FR" />
                <input name="addressAR" value={formData.addressAR} onChange={handleChange} placeholder="Address AR" />
                <button type="submit" disabled={loading}>{editingCitizen ? 'Update / تحديث' : 'Add / تسجيل'}</button>
                {editingCitizen && <button type="button" onClick={handleDelete} disabled={loading}>Delete / حذف</button>}
            </form>
        </div>
    );
}

export default AddCitizenForm;
