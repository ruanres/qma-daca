import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api/'
});

api.interceptors.request.use(config => {
    const token = localStorage.getItem("token");
    config.headers.common['Authorization'] = token ? "Bearer " + token : null;
    return config;
}, error => {
    return Promise.reject(error);
});

export default api;