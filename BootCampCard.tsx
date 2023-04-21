
import React from "react";
import {Gallerymodel} from "../Models/Gallerymodel";
import PersonCard from "./PersonCard";
import '../CSS Styling/BootCampCard.css';

export const  BootCampCard = (props:Gallerymodel)=>{
    const Data=props;
    //console.log(Data);
    return(
        <div id="bootcamp" className="bootcamp">
            <h3>{Data.bootcampName}</h3>
            <h4>Instructor List</h4>
            <div id="cardList" className="cardList">
            {Data.instructorlist.map (i=><PersonCard type="INSTRUCTOR" id={i.id} name={i.name}></PersonCard>)}
            </div>
            <h5>Developer List</h5>
            <div id="cardList" className="cardList">
            {Data.developerlist.map (i=><PersonCard type="DEVELOPER" id={i.id} name={i.name}></PersonCard>)}
            </div>
        </div>
    );
}
export default BootCampCard

