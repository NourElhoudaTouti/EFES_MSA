import React from 'react';
import './BirthForm.css';

const BirthForm = () => {
    return (
        <div className="birth-form">
            <h2>Register Birth / تسجيل الولادة</h2>
            <form>
                <div className="form-group">
                    <label htmlFor="Nom_child_AR">Name (AR) / الاسم (عربي)</label>
                    <input type="text" id="Nom_child_AR" name="Nom_child_AR" required />
                </div>
                <div className="form-group">
                    <label htmlFor="Prenom_child_AR">Surname (AR) / اللقب (عربي)</label>
                    <input type="text" id="Prenom_child_AR" name="Prenom_child_AR" required />
                </div>
                <div className="form-group">
                    <label htmlFor="Nom_child_FR">Name (FR) / الاسم (فرنسي)</label>
                    <input type="text" id="Nom_child_FR" name="Nom_child_FR" required />
                </div>
                <div className="form-group">
                    <label htmlFor="Prenom_child_FR">Surname (FR) / اللقب (فرنسي)</label>
                    <input type="text" id="Prenom_child_FR" name="Prenom_child_FR" required />
                </div>
                <div className="form-group">
                    <label htmlFor="date_de_naissance_Child">Date of Birth / تاريخ الميلاد</label>
                    <input type="date" id="date_de_naissance_Child" name="date_de_naissance_Child" required />
                </div>
                <div className="form-group">
                    <label htmlFor="Lieu_naissance">Place of Birth / مكان الولادة</label>
                    <input type="text" id="Lieu_naissance" name="Lieu_naissance" required />
                </div>
                <div className="form-group">
                    <label htmlFor="Mere_CIN">Mother's CIN / رقم البطاقة الوطنية للأم</label>
                    <input type="text" id="Mere_CIN" name="Mere_CIN" required />
                </div>
                <div className="form-group">
                    <label htmlFor="Pere_CIN">Father's CIN / رقم البطاقة الوطنية للأب</label>
                    <input type="text" id="Pere_CIN" name="Pere_CIN" required />
                </div>
                <div className="form-group">
                    <label htmlFor="Address_Parent_FR">Address (FR) / العنوان (فرنسي)</label>
                    <input type="text" id="Address_Parent_FR" name="Address_Parent_FR" required />
                </div>
                <div className="form-group">
                    <label htmlFor="Address_Parent_AR">Address (AR) / العنوان (عربي)</label>
                    <input type="text" id="Address_Parent_AR" name="Address_Parent_AR" required />
                </div>
                <button type="submit">Register / تسجيل</button>
            </form>
        </div>
    );
};

export default BirthForm;
