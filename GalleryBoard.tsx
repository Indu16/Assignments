
import React from "react";
import {useEffect,useState} from "react";
import axios from "axios";
import {BootcampProp,RootBootcamp}  from "../Models/Bootcampmodel";
import {InstructorProp, RootInstructor} from "../Models/Instructormodel";
import {DeveloperProp, RootDevloper} from "../Models/Developermodel";
import {Gallerymodel} from "../Models/Gallerymodel";
import BootCampCard from "./BootCampCard";
import '../CSS Styling/GalleryBoard.css';

export const GalleryBoard =()=>{
    const [bootcampsUs, setBootcampsUs]= useState<BootcampProp[]>([]);
    const [instructorsUs, setInstructorsUS]=useState<InstructorProp[]>([]);
    const [developersUs, setDevelopersUS]=useState<DeveloperProp[]>([]);
    const [bootcampValue,setBootcampValue]=useState("all");
    let block

    useEffect(() => {
        function getBootcamps(){
            axios
                .get<RootBootcamp>('http://localhost:3001/bootcamps')
                .then(response=>{
                    setBootcampsUs(response.data.bootcamps);
                    });
        }

        function getInstructors(){
            axios
                .get<RootInstructor>('http://localhost:3001/instructors')
                .then(response=>{
                    setInstructorsUS(response.data.instructors);
                    //console.log(response.data);
                });
        }

        function getDevelopers (){
            axios
                .get<RootDevloper>('http://localhost:3001/developers')
                .then(response=>{
                    setDevelopersUS(response.data.developers);
                });
        }

        getBootcamps();
        getInstructors();
        getDevelopers();

    });
    function getBootcampID(props:string){
        bootcampsUs.forEach(item=>{
            if(item.bootcamp==props)
                setBootcampValue(item.id)});
        if(props=="all")
            setBootcampValue(props);
        }
    function HandleChangeDropdown(event:React.ChangeEvent<HTMLSelectElement>){
        getBootcampID(event.currentTarget.value)
        CreateBlock();
    }

    let galMod :Gallerymodel[]=[];

    bootcampsUs
        .forEach((bcItem)=>{
            const obj = {
                bootcampId: bcItem.id,
                bootcampName:bcItem.bootcamp,
                instructorlist:instructorsUs.filter(item => item.bootcampId === bcItem.id),
                developerlist:developersUs.filter(item => item.bootcampId === bcItem.id)
            }
            galMod.push(obj);
        })
    CreateBlock();

    function CreateBlock(){
        if(bootcampValue=="all")
        {block= galMod.map(item=><BootCampCard
                bootcampId={item.bootcampId}
                bootcampName={item.bootcampName}
                instructorlist={item.instructorlist}
                developerlist={item.developerlist}></BootCampCard>)

        }
        else {
            galMod.forEach(item=>{
                if (item.bootcampId===bootcampValue){
                   block=<BootCampCard bootcampId={item.bootcampId}
                                       bootcampName={item.bootcampName}
                                       instructorlist={item.instructorlist}
                                       developerlist={item.developerlist}></BootCampCard>
                }
            })
        }

    }

    return(<div className="gallerySection">
            <div className="drop-down">
                <h4>Filter Bootcamp:</h4>
                <select id="selectBootcamp" name="Filter Bootcamp" className="selectBootcamp" onChange={HandleChangeDropdown}>
                    <option value="all">all</option>
                    <option value="jfs">jfs</option>
                    <option value="jsfs">jsfs</option>
                    <option value="dnfs">dnfs</option>
                </select>
            </div>
            <hr></hr>
            <h2 className="sectionHeader">Gallery</h2>
            <div id="gallery" className="gallery">
                {block}
            </div>
        </div>
    );
}

export default GalleryBoard
