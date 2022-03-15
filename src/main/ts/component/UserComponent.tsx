import React from "react";
import UserService from '../api/GameApi'

class UserComponent extends React.Component {
    constructor(){
        super();
        this.state = {
            users: []
        }
    }
}

export default UserComponent;