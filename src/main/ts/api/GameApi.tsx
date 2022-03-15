import axios from 'axios'

const API_URL = 'http://localhost:8080/bodyData'; 

class UserService {

    getUsers(){
        axios.get(API_URL);
    }
}

export default new UserService();