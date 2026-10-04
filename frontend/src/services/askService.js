import axios from "axios";
const API_URL = "http://localhost:8080/api/wiki/askAI";


const getAnswer = (question) => {
  return axios.post(API_URL, { question });
};


export default {
  getAnswer
};