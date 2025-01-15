import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:5000/api',
});

export const createProblem = async (contestId, formData) => {
  const response = await api.post(
    `/problemsetter/createProblem/${contestId}`,
    formData,
    {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    }
  );
  return response.data;
};