import React, { useState } from 'react';
import axios from 'axios';
import styles from './LoginForm.module.css';
import { IoPersonCircle, IoLockClosed } from 'react-icons/io5';

const translations = {
    en: {
        username: "Username",
        password: "Password",
        login: "Login",
        authSuccess: "Authentication successful! Token stored.",
        authFailed: "Authentication failed: ",
    },
    ar: {
        username: "اسم المستخدم",
        password: "كلمة المرور",
        login: "تسجيل الدخول",
        authSuccess: "تم التحقق بنجاح! تم تخزين الرمز.",
        authFailed: "فشل التحقق: ",
    }
};

function LoginForm() {
    const [credentials, setCredentials] = useState({
        username: '',
        password: ''
    });

    const [language, setLanguage] = useState('en');

    const handleChange = (e) => {
        setCredentials({...credentials, [e.target.name]: e.target.value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/api/v1/auth/authenticate', JSON.stringify(credentials), {
                headers: {'Content-Type': 'application/json'}
            });
            localStorage.setItem('jwt', response.data.token);
            alert(translations[language].authSuccess);
        } catch (error) {
            console.error('Login failed:', error.response || error.message);
            alert(translations[language].authFailed + (error.response ? error.response.data : error.message));
        }
    };

    return (
        <div>
            <div>
                <button onClick={() => setLanguage('en')}>English</button>
                <button onClick={() => setLanguage('ar')}>عربي</button>
            </div>
            <form onSubmit={handleSubmit} className={styles.formContainer}>
                <div className={styles.label}>
                    <IoPersonCircle size={20} style={{ marginRight: '10px' }} />
                    {translations[language].username}:
                    <input
                        type="text"
                        name="username"
                        value={credentials.username}
                        onChange={handleChange}
                        className={styles.inputField}
                        required
                    />
                </div>
                <div className={styles.label}>
                    <IoLockClosed size={20} style={{ marginRight: '10px' }} />
                    {translations[language].password}:
                    <input
                        type="password"
                        name="password"
                        value={credentials.password}
                        onChange={handleChange}
                        className={styles.inputField}
                        required
                    />
                </div>
                <button type="submit" className={styles.submitButton}>{translations[language].login}</button>
            </form>
        </div>
    );
}

export default LoginForm;
