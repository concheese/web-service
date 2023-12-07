import { useState } from "react";
import Contents from "../layout/Contents";
import Wrapper from "../layout/Wrapper";
import { styled } from "styled-components";
import { BsFillPlusCircleFill } from "react-icons/bs";
import { AiOutlineMinusSquare } from "react-icons/ai";
import {writeInfoPost} from "../api/api2"
import { useNavigate } from "react-router-dom";


const genretal = ["선택해주세요" ,"Concert", "Musical" , "Orchestra" , "Festival" , "Others"] 

const WriteConcert = () => {
  // form 배치 순
  // 제목
  const [title, setTitle] = useState("");
  // 장르
  const [genreList , setGenreList] = useState(genretal);
  const [selectedGenre, setSelectedGenre] = useState("");
  // 가수
  const [player, setPlayer] = useState("");
  const [playerList, setPlayerList] = useState([]);
  
  // 날짜 
  const [dates, setDate] = useState(["","","","","","" , "" , ""]);
  const [click , setClick] = useState( false);

  //공연 날짜 & 장소 
  const [performancedate , setPerformanceDate] = useState("");
  const [perstart , setPerStart] = useState("");
  const [schedule , setSchedule] = useState([]);
  // 내용
  const [content, setContent] = useState("");
  // 링크 
  const [link, setLink] = useState("");
  const navigator = useNavigate();
  
  

   
  // 폼 데이터 업데이트 및 제출 
  //업데이트 
  const handleChange = (event ) => {
    const data = event.target.id;
    switch (data) {
      case "genre" : changeGenre(event.target.value); break; // genre 바꾸기  
      case "title" : setTitle(event.target.value); break;  // title 바꾸기 
      case "check" : setClick(!click); break; // 선예매 유무에 따라 날짜/ 시간 다르게 나타나게 
      case "link"   : setLink(event.target.value); break;
      case "content" : setContent(event.target.value); break; 
    
    }
  }

  // 장르바꾸기
 const changeGenre = (data) => {
   const data2 = data.toUpperCase();
   setSelectedGenre(data2);
 }
 
 
  
  //제출  
  const setReturn = () =>  {
    // 후처리
    const dates2 = dates.map((date) => {return (date === "") ? date = "정보없음" : date }  ) 
    let [a , b ,c , d , e , f , g , h] = dates2;

    
    
    
    const form = {
      title : title , 
      performers  : playerList , 
      type : selectedGenre , 
      schedules : schedule ,
      ticketings : [
        {start : a +" "+b + ":00",
        end : c+" "+d + ":00" ,
        status : "PRE_SALE"} ,
        {start :e+ " "+ f + ":00" ,
         end : g + " " + h + ":00",
         status : "GENERAL_SALE"
        }
      ], 
      description : content,
      link : link
    }

   console.log(form)
   //비동기 문제가 있음으로 그냥 form을 이용해서 값을 넣는다 
   getPosts(form);
   // 다시 시작 

  }
  

  const getPosts = async (form) =>{
    
    if(form.length === 0 ){
      alert("입력해주세요");
      console.log(form)
    }
    else{
    
    try{
     console.log(JSON.stringify(form))
     await writeInfoPost(form);
    } 
    catch (err) {
      console.error(err);
    }
    } 
    navigator(`/`)

  }

 //날짜 시간 맞춰서 
 const handleDate = (event) => {
  const date = dates.map((d , index) => {return ( (index === parseInt(event.target.id)) ?  d = event.target.value : d )})
  setDate(date);
  console.log(date);

}

// schedule(공연날짜 + 시간 ) 만들기 
 const handleSchedule1 = (event) => {
  const data = event.target.value;
  const type = event.target.id;
  
  switch (type ) {
    case "date" : setPerformanceDate(data); break;
    case "time" : setPerStart(data); break; 
  } 
  


  }
 

  // 장소 
  const addPlaceHandler = (e) => {
    const previousdate = performancedate + " " + perstart + ":00"
    new daum.Postcode({
      oncomplete: function (data) {
        // 날짜당
        const code = parseInt(data.zonecode)
        setSchedule( [...schedule , {dateTime : previousdate , postalCode : code} ])
      },
    }).open();
    setPerformanceDate("");
    setPerStart("");
  };

  const subPlaceHandler = (e) => {
    
    const data2 = schedule.filter((sch) => {
      return sch.postalCode !== e
    
    });
    setSchedule(data2);
  };
  
  //공연자 
  const addPlayerHandler = (e) => {
    e.preventDefault();
    const p = {name : player}
    setPlayerList([...playerList, p]);
    setPlayer("");
    
  };

  const playerRender = () =>{
    
   return  (playerList
      .filter((v) => {
        return v.name.length > 0;
      })
      .map((data) => { 
        console.log(data.name)
        return <Tag onClick = {() => {playerDelete(data.name)}}>{data.name} x </Tag>;
      }))   }
  
  const playerDelete = (value) =>{
      const playerlist = playerList.filter((player) => { return player.name !== value })
      setPlayerList(playerlist);
  }
  

  const popup = () => {
    return (
      <> 
      {click === true && <div style={{ margin : `10px 0 20px 0` , fontSize : '10px' }}>
            <label htmlFor="ticketDate">선예매 날짜</label> / <label htmlFor="time">선예매 시간</label>
            <br/>
            <Datey  id = "0" type = "date" onChange = {handleDate} /> 
            <Datey  id = "1" type = "time" style = {{ margin : "5px"}} onChange ={handleDate} />  <br/> 
            <Datey  id = "2" type = "date" onChange = {handleDate} /> 
            <Datey  id = "3" type = "time" style = {{ margin : "5px"}} onChange ={handleDate} />
          </div> }
       </>    
          )
  } 



  return (
    <Wrapper>
      <Contents style={{ textAlign: "center" }}>
        <form onSubmit={(e)=>e.preventDefault()}>
          <h1 style={{ fontSize: "20px" }}>공연정보 등록</h1>
          <div
            style={{
              marginTop: "15px",
              maxWidth: "500px",
            }}
           >
           <label htmlFor="title">제목</label>
            <Cont id = "title" style ={{ width : "20%" , height : "10%"}}
            onChange = {handleChange}/> </div> 


          <div style={{ marginTop: "0px" }}>
            <div style={{ display: "flex" }}>
    
              <div>
                <label htmlFor="player" > 가수 </label>
                <Cont
                  value={player}
                  id="player"
                  style={{ width: " 40%", height: "50%" , margin : "0px 5px 10px 10px" }}
                  onChange={(e) => {
                    setPlayer(e.target.value);
                    
                  }}
                />
                <BsFillPlusCircleFill style ={{color : "orange"
                , margin : "10px 0px 0px 0px" }}onClick={addPlayerHandler}/>
              </div>
            </div>
            <div style={{ display: "flex", gap: "3px" }}>
             {playerRender()}
            </div>

            <div style={{ marginTop: "20px" , display : "flex"}}>
            <p style={{ marginBottom: "30px"  }}>장르 </p> 
            
              <label htmlFor = "genre" />
              <Select  style = {{borderRadius :"10px"}}
              id = "genre"
               onChange = {handleChange}>
             {genreList.map((v) => {
              return (
                <option value = {v}> {v} </option>
              )
             }) }
             </Select> 
              
          </div>
    

          <div> 날짜 </div> 
          <p style = {{fontSize : "10px"}}>선예매 유무
          <input id = "check" type="checkbox" onClick = {handleChange} 
          style= {{margin : "10px" , backgroundColor : 'orange'}}  /> </p>
          <div > {popup()} </div> 

          <div style={{ margin: "10px 0 20px 0" }}>
            <label htmlFor="ticketDate" style = {{fontSize : "10px" }}>티켓팅 날짜 / 티켓팅 시작 시간 </label>
             <br/> 
             <Datey id = "4" type ="date" onChange = {handleDate} />  
             <Datey id = "5" type ="time" onChange = {handleDate} style ={{margin : "5px"}}  /> <br/> 
             <Datey id = "6" type = "date" onChange ={handleDate} /> 
             <Datey id = "7" type ="time" onChange = {handleDate} style ={{margin : "5px"}} /> 
          </div>

          <div style={{ margin: "10px 0 0px 0" }}>
            <label htmlFor="ticketDate" style = {{fontSize : "10px" }}> 공연 날짜 / 시작 시간 </label>
             <br/> 
             <Datey id = "date" type ="date" value = {performancedate} style = {{margin : "5px"}} onChange = {handleSchedule1} />  
             <Datey id = "time" type = "time" value = {perstart} onChange = {handleSchedule1} /> 
             
          </div>
          {((performancedate.length !== 0 && perstart.length !== 0) ) ? 
           <div> 
            <label style ={{margin : "5px"}}>공연 장소 [날짜순] </label>
           
            <BsFillPlusCircleFill 
            style= {{cursor : "pointer" ,color : "orange"}}
            onClick = {addPlaceHandler} />
          
          </div> : <p style = {{fontSize : "10px" ,color : "grey" , margin : "5px"}}> 공연 날짜와 시작시간을 입력해 주세요 </p> }
          {schedule.map((v) => {
              return  <div key={v.datetime} >{v.dateTime} / {v.postalCode} <AiOutlineMinusSquare onClick = {() => {subPlaceHandler(v.postalCode)}}/> </div>;
            })}

          
          <div>
            <label htmlFor="content">공연내용</label>
            <br />
            <textarea
              id="content"
              style={{ resize: "none", padding: "5px", width: "70%" , backgroundColor : "#e9ecef" , border : "none" , borderRadius : "10px" }}
              rows={8}
              placeholder="공연 내용이나 기타 특이사항을 적어주세요."
              onChange = {handleChange}
            ></textarea>
          </div>
  
            <div>
            <label htmlFor="title">참고링크</label>
            <br />
            <Cont id ="link"
             style = {{width : "60%" , height : "10%"}}
            onChange = {handleChange}/>
          </div>
          <button style = {{backgroundColor : "orange" ,border : "none" , borderRadius : "8px" , margin : "0px 0px 10px 0px"}} 
          onClick = {() => {setReturn()}} >제출하기</button>
          </div>  </form> 
      </Contents>
    </Wrapper>
  );
};

const Tag = styled.span`
  background-color: orange;
  color: white;
  padding: 5px 10px;
  border-radius: 10px;
  font-size: 12px;
  cursor: pointer;
`;

const Cont = styled.input`
  border: none;
  background-color: #e9ecef;
  padding: 10px;
  border-radius: 10px;
  margin: 10px 10px 15px;
`;
const Button = styled.button`
  margin-left: 10px;
  border: none;
  padding: 5px;
  border-radius: 10px;
  font-size: 12px;
  cursor: pointer;
`;
const Select = styled.select`
  margin: 5px;
  height: 30%;
  border: none;
  background-color: #e9ecef;
  border-radius: "5px";
`;
const Datey = styled.input`
  type: date;
  border: none;
  background-color: #e9ecef;
  border-radius: 10px;
`;

export default WriteConcert;


 