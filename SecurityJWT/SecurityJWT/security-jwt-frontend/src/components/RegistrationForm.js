import React, { useState } from 'react';
import axios from 'axios';
import styles from './RegistrationForm.module.css';

const translations = {
    en: {
        firstname: "Firstname",
        lastname: "Lastname",
        username: "Username",
        password: "Password",
        register: "Register",
        regSuccess: "Registration successful! Your token: ",
        regFailed: "Registration failed: ",
    },
    ar: {
        firstname: "الاسم الأول",
        lastname: "الاسم الأخير",
        username: "اسم المستخدم",
        password: "كلمة المرور",
        register: "تسجيل",
        regSuccess: "تم التسجيل بنجاح! رمزك: ",
        regFailed: "فشل التسجيل: ",
    }
};

function RegistrationForm() {
    const [user, setUser] = useState({
        firstname: '',
        lastname: '',
        username: '',
        password: ''
    });

    const [language, setLanguage] = useState('en');

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/api/v1/auth/register', user);
            alert(translations[language].regSuccess + response.data.token);
        } catch (error) {
            alert(translations[language].regFailed + error.message);
        }
    };

    return (
        <div>
            <div>
                <button onClick={() => setLanguage('en')}>English</button>
                <button onClick={() => setLanguage('ar')}>عربي</button>
            </div>
            <form onSubmit={handleSubmit} className={styles.formContainer}>
                <input
                    type="text"
                    name="firstname"
                    value={user.firstname}
                    onChange={handleChange}
                    className={styles.inputField}
                    placeholder={translations[language].firstname}
                    required
                />
                <input
                    type="text"
                    name="lastname"
                    value={user.lastname}
                    onChange={handleChange}
                    className={styles.inputField}
                    placeholder={translations[language].lastname}
                    required
                />
                <input
                    type="text"
                    name="username"
                    value={user.username}
                    onChange={handleChange}
                    className={styles.inputField}
                    placeholder={translations[language].username}
                    required
                />
                <input
                    type="password"
                    name="password"
                    value={user.password}
                    onChange={handleChange}
                    className={styles.inputField}
                    placeholder={translations[language].password}
                    required
                />
                <button type="submit" className={styles.submitButton}>{translations[language].register}</button>
            </form>
        </div>
    );
}

export default RegistrationForm;
