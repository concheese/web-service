import { keyframes, styled } from "styled-components";
import { Link , useLocation  } from "react-router-dom";
import { BsFillPencilFill } from "react-icons/bs";
import { AiOutlineHeart } from "react-icons/ai";
import { useState , useEffect } from 'react';
import { AiFillCheckCircle } from "react-icons/ai"
import { getInfoPosts , getInfoFilter } from "../api/api2"
import Infos from "../components/Infos";


export default function Info() {
  const [artist, setArtist] = useState("");
  const [artistList, setArtistList] = useState([]);
  const [filterform , setFilterForm] = useState([])
  const [form, setForm] = useState([]);
 
  
  const a = useLocation()

  // 바로 받았을 때 게시판 하나만 표시 
  useEffect(() => {
    getPosts();
  } , [])

  const checking = async (result) =>{
    console.log(a);
    if(a.state === null ){
      setForm(result)
    }
    else{
      const tempFilter = result.filter((ie) => (ie.id === a.state.id))
      setForm(tempFilter)
    }
   
    } 
  
    
    
  
  // 값을 받기 
  
  
  const getPosts = async () => {
    try {
     const result = await getInfoPosts();
     checking(result)
     //setForm(result);
    } catch (err) {
      console.error(err);
    }
    
    
 
  };

  


  const artistListchange = async () => {
    if (artist.length > 0 ) {
      setArtistList([...artistList , artist ])

     let result = await getInfoFilter(artist , "performer");
    setFilterForm( [...filterform, result])
     
      
    }
    setArtist("")
  }

  const artistListDelete = (value) => {
   
    const list = artistList.filter((artist) => {
      return artist !== value
    })
    
    setArtistList(list)
    let filterTemp = filterform.flat().filter((data) =>  {let b = data.performers.findIndex(index => index.name.includes(value)) 
    return b === -1
    })
    setFilterForm(filterTemp)
    
    
  }


  const artistRender = () => {

    return (
      artistList.map((arti) => {
        return (

          <Tag key={arti} onClick={() => { artistListDelete(arti) }}>{arti} x </Tag>
        )
      }))
  }
  
  
  
   // async 사용하면 안됨 / promise 객체로 덮임 
  const makeForm =  () => {
    
    let test1 = form;
    
    
   if(artistList.length > 0 ){
    
     test1 = filterform.flat()
     
 
   } 

    return (
      <>
        {test1.map((data, i) => <Infos key={i} data={data} />)}
      </>
    )
  }



  return (
    <>
      <Link to={{ pathname: "/writeConcert" }}>
        <Btn
          style={{
            marginTop: "-30px",
            marginBottom: "10px",
            marginLeft: "auto",
            display: "block",
          }}
        >
          <BsFillPencilFill style={{ marginRight: "5px" }} />
          공연정보 등록
        </Btn>
      </Link>

      <input value={artist}
        style={{
          border: "none", borderRadius: "8px",
          margin: "10px",
          backgroundColor: "#fafaf0",
          boxShadow: "0 0 2px 3px rgba(0, 0, 0, 0.1)"
        }}
        onChange={(e) => { setArtist(e.target.value) }} />
      <AiFillCheckCircle
        style={{ margin: "5px" }}
        onClick={() => { artistListchange() }}></AiFillCheckCircle>
      {artistRender()}

      {makeForm()}

    </>
  );
}

const colorChange = keyframes`
  0%{
    color: #e06363;
  }
  25%{
    color: #eb8e38:
  }
  50%{
    color: #d4c717;
  }
  75%{
    color: #4ac72e;
  }
  100%{
    color: #0b84e0;
  }
`;

const Mold = styled.div`
background-color : #fafaf0;
max-width: 400px;
height : auto;
padding: 10px 15px;
border-radius: 5px;
margin-bottom: 30px;
box-shadow: 0 0 10px 3px rgba(0, 0, 0, 0.1);
`;





const Image = styled.div`
  background-color: orange;
  padding: 2px;
  width: 25px;
  height: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;`
  ;

const Image2 = styled.img`
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 50%;
  

`

const Btn = styled.button`
  background-color: #eee;
  padding: 10px 20px;
  border-radius: 10px;
  font-size: 13px;
  font-weight: bold;
  border: none;
  cursor: pointer;
  &:hover {
    color: red;
    animation: ${colorChange} 1.3s infinite;
  }
`;

const Im = styled.button`
height : 20px;
weight : 30px;
font-size : 15px;
background-color : orange; 
color : white;
margin : 7px 0px 0px 10px ;
border : none;
border-radius : 10px;
`;

const P = styled.p`
font-weight : bold;
font-size : 14px;
`
const P2 = styled.p`
font-size : 13px;
margin : 5px 0px 5px 0px;
`
const Tag = styled.span`
  background-color : orange;
  color : white;
  padding: 5px 10px;
  border-radius: 10px;
  font-size: 12px;
  cursor: pointer;
`
