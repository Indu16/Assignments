
import React from "react";
import {PersonProp} from "../Models/Personmodel";
import '../CSS Styling/PersonCard.css';
import axios from "axios";

export const  PersonCard = (props:PersonProp)=> {
    const Data = props;
    let button;

    const deleteDeveloper=async(id:string)=>{
        await axios.delete('http://localhost:3001/developers/' + id)
            .then(response=>console.log(response))
    }

    if (Data.type == "DEVELOPER") {
        button =  <button className="deleteBtn" id="deleteBtn"
                          onClick={() => deleteDeveloper(Data.id)}>Delete</button>
    }

    return (
        <div id="card" className="card">
            <li id="toggled" className="toggled">{Data.name}</li>
            {button}
        </div>);
}
export default PersonCard
