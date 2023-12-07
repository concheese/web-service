import { AiFillHeart } from "react-icons/ai";
import { BsBookmarkStarFill } from "react-icons/bs";
import styled from "styled-components";
import { Link } from "react-router-dom";
import {AiFillStar} from "react-icons/ai";
import concheese from "../assets/1699544049717.png";

export default function HomeCard({ data  }) {
  
  
  const scheduleDateRender = () => { 
   
    if (data.schedules.length > 0){
    return(
    data.schedules.map((dat) => {
      return <p style ={{ fontSize : "12px"}}> {dat.dateTime.slice(0,10)}/{dat.postalCode } </p> 
    }))}
    else{
      return <p>스케줄 없음</p>
    }
  }

  

  
  
  
  return (
  
    <div style ={{ display : "absolute" , zIndex : "0"}}>
      <div style={{ display: "flex" }}>
        <div>
          <Link to={`/feed/info` } state ={{id : data.id}}>
        <AiFillHeart style={{ color: "orange" }} /></Link>
        <a href={data.link}>
          <BsBookmarkStarFill style={{ color: "#bebebe" }} />
        </a> </div>
        <label style ={{  fontStyle : "italic" , fontWeight : "bold"}}> {data.title.slice(0,10)} ... </label> 
        </div>
   
      <Fonty><img style ={{ width : "100%" , height : "100%"}} src={concheese} alt="My Image" /></Fonty>
      
      <L>  {(data.performers.length > 0) ?  data.performers[0].name : <p>해당없음</p> } </L>
       

      <div>
        <p style={{ display: "flex", gap: "65px", margin: " 0px 2px 5px" }}>
          <P>장르</P>
          <p style={{ fontSize: "12px" }}>{data.type}</p>
        </p>

        <p style={{ display: "flex", gap: "33px", margin: "5px 2px 5px " }}>
          <P>선예매날짜 </P>
          <p style={{ fontSize: "12px" }}>
            {data.ticketings[0].start?.slice(0,10)}
          </p>
        </p>
        <p style={{ display: "flex", gap: "35px", margin: "5px 2px 5px" }}>
          <P> 티켓팅날짜</P>
          <p style={{ fontSize: "12px" }}>{ (data.ticketings.length > 1 ) ? data.ticketings[1].start.slice(0,10) : 
          <p style = {{fontSize : "12px"}}>이름 없음</p> }</p>
        </p>

        <p style={{ display: "flex", gap: "10px", margin: "5px 2px 5px " }}>
          <P>공연날짜 / 장소 </P>
          <p>
          {scheduleDateRender()} </p>
        </p>

   </div> </div> 
  );
}

const Fonty = styled.div`
  
  height: 220px;
  font-size: 33px;
  background-color: #ffffff;
  text-align: center;
`;

const P = styled.p`
  font-size: 11px;
  font-weight: bold;
`;


const L = styled.li`
font-size : 15px;
font-weight : bold;
margin: 0px 4px 15px 5px;
display : inline-block;
position : relative;


`
const S =styled.li`
opacity : 0;
position : absolute;
width : 200px; 
height : 30px;
border-radius : 8px;
background-color : #e9e4cf ;
font-size : 12px;
transition : opacity 0.2s;
&:hover {
 
  opacity : 1;
}

`
