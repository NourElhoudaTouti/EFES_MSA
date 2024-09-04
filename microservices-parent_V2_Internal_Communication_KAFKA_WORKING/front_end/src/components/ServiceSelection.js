// ServiceSelection.js
import React from 'react';
import { Link } from 'react-router-dom';
import './ServiceSelection.css';

const ServiceSelection = () => {
    return (
        <div className="service-selection">
            <h1>Select a Microservice / اختر خدمة صغيرة</h1>
            <div className="services">
                <Link to="/citizen-management" className="service-link">Citizen Management / إدارة المواطنين</Link>
                <Link to="/birth-management" className="service-link">Birth Management / إدارة الولادات</Link>
                <Link to="/marriage-management" className="service-link">Marriage Management / إدارة الزواج</Link>
                <Link to="/divorce-management" className="service-link">Divorce Management / إدارة الطلاق</Link>
                <Link to="/death-management" className="service-link">Death Management / إدارة الوفيات</Link>
                <Link to="/image-management" className="service-link">Image Management / إدارة الصور</Link>
                <Link to="/notification-management" className="service-link">Notification Management / إدارة الإشعارات</Link>
                <Link to="/statistics-management" className="service-link">Statistics and Reporting / إدارة الإحصائيات والتقارير</Link>
            </div>
        </div>
    );
};

export default ServiceSelection;
