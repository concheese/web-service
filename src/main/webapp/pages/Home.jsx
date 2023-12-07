import { keyframes, styled } from "styled-components";
import Contents from "../layout/Contents";
import Wrapper from "../layout/Wrapper";
import { useEffect, useState } from "react";
import { getInfoPosts , getInfoFilter} from "../api/api2";
// react-icons 모음
import { BsCalendarCheck } from "react-icons/bs";
import { AiFillCheckCircle } from "react-icons/ai";
import {AiOutlineSearch} from "react-icons/ai";
import HomeCard from "../components/HomeCard";




const Home = () => {
  
  const [date, setDate] = useState("");
  const [datefilter, setDateFilter] = useState("");
  const [checkdate, setCheckDate] = useState(false);
  const [form, setForm] = useState([]);
  // 자동 완성 검색어 
  const [array , setArray ] = useState([]);
  const [array2 , setArray2] = useState([]);
  const [array3 , setArray3] = useState([]);
  const [autoComplete , setAutoComplete] = useState(false);
  // 값을 전달 할 때 
  
  const [totalList , setTotalList] = useState([])
  const [filterTotal , setFilterTotalList] = useState([])
  const [select , setSelect] = useState("")
  
  

  useEffect(() => {
   InfoPosts();
   
  }, []);
  
  // 값을 받기 
  const InfoPosts = async () => {
    try {
     const result = await getInfoPosts();
     
      setForm(result)
      makealign(result)
     
    } catch (err) {
      console.error(err);
    }
 
  };

  
  //초기 자동완성 만들기  
  const makealign = async (result) => {
    
    const array = [];
    const array2 = [];
    
    
    // form에 맞춰서 객체 정렬 
    result.map((a) => { 
      array.push(a.title)
      array2.push(a.title)
      a.performers.map((b) => {
        array.push('-' + b.name)
        
      })
       
          }
    )
    
   const tempArray = [... new Set(array)];
   const tempArray2 = [... new Set(array2)];
    
   // array
    setForm(result);
    setArray(tempArray);
    setArray2(tempArray2); 
    setArray3(tempArray);
    
  }
  
  // 값을 저장하고 일치하는 것 출력 
  const autoFilter = (e) => {
   
    let fitty = array.filter((arr) => arr.includes(e.target.value));

    
    
    setSelect(e.target.value)
    setArray3(fitty)
    
  }

  
  

  const playerChange = async (e) => {
    e.preventDefault();
    setAutoComplete(false);
    if(select.length > 0){
    let select2 = select
    if(select.includes("-")){
      select2 =  select.slice(1)
     
     }

    setTotalList([...totalList , select2 ] )
   
    if(array2.includes(select)){
      const select2 = (select.length > 8) ? select.slice(0, 9) : select
      let result = await getInfoFilter(select2 , "title")
      
      setFilterTotalList([...filterTotal , result]) 

    }
    else{
     
      let result2 = await getInfoFilter(select2 , "performer") 
      setFilterTotalList([...filterTotal , result2]) //바로 렌더하지 않도록 

    }

    setSelect("");}}

  const playerRender = () => {
    
    
    return totalList.map((v) => {
      return (
        <Tag key={v} onClick={() => playerDelete(v)}>
          {v}
          <button
            key={v}
            value={v}
            style={{
              border: "none",
              backgroundColor: "orange",
              color: "white",
            }}
          >
            x
          </button>
        </Tag>
      );
    });
  };
  
  const playerDelete = (v) => {
    setTotalList(
    totalList.filter((data) => {
        return data !== v;
      })
    );
    
    // 삭제할 때 필터 
 const filtertotal =  filterTotal.flat().filter((data) => {
    let b = data.performers.findIndex(d => d.name.includes(v)) 
    let c = data.title.includes(v)
    return b === -1  &&   !c
  })

  console.log(filtertotal)
  
   setFilterTotalList(filtertotal)
  
  };

  

 
  

  
  const cardRender = () => {
    let test = form;
   
    let test2 =form;
    let test3 =form;
    
     // 공연자, 공연 이름 중복 처리 or setFilterTotalList([])
  if(totalList.length !== 0){
   test = filterTotal.flat();
   test2 = test.filter((obj , idx) => {
      const isFirstFindIdx = test.findIndex((obj2) => obj2.title === obj.title && obj2.description === obj.description )
      return idx === isFirstFindIdx 
   })
   test3 =test2
   
  }
 
  
  
  
   
   // 날짜 필터 
    if (datefilter.length !== 0 && date.length !== 0) {
      
      switch (date) {
        case "선예매":
          test3 = test.filter((data) => {
            console.log(data.ticketings[0].start.slice(0,7))
            return (
              data.ticketings[0].start.slice(0,7) ===
              datefilter 
            );
          });
          break;
        case "티켓팅 날짜":
          test3 = test.filter((data) => {
            return (
             data.ticketings[1].start.slice(0,7) ===
              datefilter
            );
          });
          break;
        case "공연 날짜":
          test3 = test
          .filter((data) => {
              let a = data.schedules.findIndex((data2) => (
                data2.timestamp.slice(0, 7) === datefilter ))
             return (a === -1) ? true : false }
             
            );
          break;
      }
    
    
    


    
    }

    return test3.map((data) => {
      return (
        <L_col>
          <Mold>
            <HomeCard  data={data} />
          </Mold>
        </L_col>
      );
    });
  };

  
  const setallDate = () => {
    return (
      <div style={{ display: "flex" }}>
        <DateSelect 
        style = {{boxShadow: "0 0 10px 0.2px rgba(0, 0, 0, 0.1)"}}
          onChange={(e) => {
            setDate(e.target.value);
          }}
        >
          <option>선택해주세요 </option> <option>선예매</option>
          <option>티켓팅 날짜 </option>
          <option> 공연 날짜 </option>
        </DateSelect>
        <input
          type="month"
          style={{
            backgroundColor: "#f5f5dc",
            border: "none",
            borderRadius: "7px",
            margin: "5px",
            boxShadow: "0 0 10px 0.2px rgba(0, 0, 0, 0.1)"
          }}
          onChange={(e) => {
            setDateFilter(e.target.value);
          }}
          
        ></input>
      </div>
    );
  };

  return (
    
    <Wrapper >
      
      <Contents>
        <div>
          <div style={{ display: "flex", gap: "5px" }}>
            {checkdate === true ? (
              setallDate()
            ) : (
              <BsCalendarCheck
               style = {{boxShadow: "0 0 10px 0.2px rgba(0, 0, 0, 0.1)"}}
                onClick={() => {
                  const check2 = checkdate;
                  setCheckDate(!check2);
                }}
              />
            )}
            <div style={{ display: "flex", gap: "5px" }}>
              <div>
              <input
                value={select}
                style={{
                  backgroundColor: "#f5f5dc",
                  border: "none",
                  borderRadius: "7px",
                  margin: "5px",
                  boxShadow: "0 0 10px 0.5px rgba(0, 0, 0, 0.1)"
                }}
                onChange={(e) => {autoFilter(e)}}
                onClick = {() => {setAutoComplete(true)}}
              />
              
              {autoComplete ? <Autoa>
                {array3.map((arr) => (<Auto><AiOutlineSearch/> <li onClick = {() => {setSelect(arr); setAutoComplete(false)}}>
                {arr} </li></Auto>))}</Autoa> : <ul></ul>}
            </div>

            </div>
          
           
            
            <AiFillCheckCircle
              onClick={playerChange}
              style={{
                backgroundColor: "#f5f5dc",
                border: "none",
                borderRadius: "7px",
                margin: "5px",
              }}
            />
              
            {playerRender()}
          </div>
          
          <L_row  >{cardRender()}</L_row>
         
      
      </div>
      </Contents>
     
    </Wrapper>
    
  );
};

const L_row = styled.ul`
  display: flex;
  flex-wrap: wrap;
  margin: 0 -5px;
  row-gap: 10px;
  padding-bottom: 30px;
`;

const L_col = styled.li`
  width: 33.33333%;
  padding: 0 5px;
`;
const Mold = styled.div`
  height: 370px;
  background-color: #f5f5dc;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.1);
`;

const Tag = styled.span`
  background-color: orange;
  color: white;
  padding: 5px 10px;
  border-radius: 10px;
  font-size: 12px;
  cursor: pointer;
`;

const DateSelect = styled.select`
background-color: #f5f5dc;
border: none;
border-radius: 7px;
margin: 5px;

`

const Autoa = styled.ul`
position : absolute;
z-index : 3; 
width : 12%;
background-color : #f5f5dc; 
border-radius : 8px;
box-shadow: 0 0 10px 0.5px rgba(0, 0, 0, 0.1);
`


const Auto = styled.span`
 display : flex;
 &:hover {
  background-color : #e9e4cf;
 }
`



export default Home;
