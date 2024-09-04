// api.js
export const createCitizen = async (citizen) => {
    const response = await fetch('http://localhost:8099/api/citizen', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(citizen),
    });
    return response.json();
};

export const getCitizens = async () => {
    const response = await fetch('http://localhost:8099/api/citizen');
    return response.json();
};

export const getCitizenByCIN = async (cin) => {
    const response = await fetch(`http://localhost:8099/api/citizen/getCitizen/${cin}`);
    return response.json();
};
export const updateCitizen = async (id, citizen) => {
    const response = await fetch(`http://localhost:8099/api/citizen/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(citizen),
    });
    return response.json();
};

export const deleteCitizen = async (id) => {
    const response = await fetch(`http://localhost:8099/api/citizen/${id}`, {
        method: 'DELETE',
    });
    return response.json();
};