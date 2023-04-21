
import React from "react";
import {useState} from "react";
import '../CSS Styling/DevelopersForm.css';
import axios from "axios";
import {BootcampProp, RootBootcamp} from "../Models/Bootcampmodel";
export const  DevelopersForm = ()=>{
    const [firstnameValue, setFirstnameValue]=useState("");
    const [lastnameValue, setLastnameValue]=useState("");
    const [bootcampValue,setBootcampValue]=useState("4c88d1fb-36a6-46e4-ad79-1c24f90e1e93");
    const [message,setMessage]=useState("");
    let errorBlock;
    function getBootcampID(props:string){
        axios
            .get<RootBootcamp>('http://localhost:3001/bootcamps')
            .then(response=>{
                const bc :BootcampProp[]=(response.data.bootcamps);
                bc.forEach(item=>{
                    if(item.bootcamp==props)
                        setBootcampValue(item.id)
                })
            });
    }

    const addDeveloper=async(developer: {preventDefault:()=>void})=>{
        developer.preventDefault();
        if(firstnameValue && lastnameValue && firstnameValue.match( /^[A-Za-z ]*$/g) && lastnameValue.match(/^[A-Za-z ]*$/g)){
            await axios.post('http://localhost:3001/developers', {
                "name": firstnameValue + " " + lastnameValue,
                "bootcampId": bootcampValue
            }).then(response=>{console.log(response);setMessage("Developer Added")})
        }
        else setMessage("Invalid input!");
        }

    function HandleChangeDropdown(event:React.ChangeEvent<HTMLSelectElement>){
        getBootcampID(event.currentTarget.value)
    }

    function HandleFirstNameChange(e:{
        target:{value:React.SetStateAction<string>};
    }){setFirstnameValue(e.target.value);setMessage("")}

    function HandleLastNameChange(e:{
        target:{value:React.SetStateAction<string>};
    }){setLastnameValue(e.target.value);setMessage("")}

    function getMessage(){
        if(message==="")
            errorBlock=<strong className="errormessage"></strong>
        else
            errorBlock=<strong id="errormessage" className="errormessage">{message}</strong>
    }
    getMessage();
    return (

        <div className="Developer">
            <h4>Add new developer</h4>
            <hr></hr>
            <form id="addDeveloperForm" className="addDeveloperForm" onSubmit={addDeveloper}>
                <div className="addDevDiv">
                <label>First Name:</label>
                    <br></br>
                    <input type="text" id="addDeveloperFirstNameInput" className="addDeveloperFirstNameInput" placeholder="First Name" value={firstnameValue} onChange={HandleFirstNameChange}></input>
                    <br></br>
                    <br></br>
                <label>Last Name:</label>
                    <br></br>
                    <input type="text" id="addDeveloperLastNameInput" className="addDeveloperLastNameInput" placeholder="Last Name" value={lastnameValue} onChange={HandleLastNameChange}></input>
                    <br></br>
                    <br></br>
                <div className="dropDown">
                    <label>Select bootcamp:</label><br></br>
                <select className="dropDown" name="select bootcamp Type" onChange={HandleChangeDropdown}>
                    <option value="jfs">jfs</option>
                    <option value="jsfs">jsfs</option>
                    <option value="dnfs">dnfs</option>
                </select>
                </div><br></br>
                <button id="addDeveloperBtn" name="button" type="submit" className="addDeveloperBtn" onClick={addDeveloper}>Add developer</button>
                   <br></br>
                    {errorBlock}
                </div>
            </form>
        </div>
    );

}
export default DevelopersForm

