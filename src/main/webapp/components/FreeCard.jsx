// import profile from "../assets/profile2.jpg";
import { RiThumbUpFill } from "react-icons/ri";
import { IoChatbubbleEllipsesSharp } from "react-icons/io5";
import { useEffect, useState } from "react";
import { HiDotsVertical } from "react-icons/hi";
import styled from "styled-components";
import { deletePost, updatePost } from "../api/api";
import { useNavigate } from "react-router-dom";

export default function FreeCard({ id, date, content, username }) {
  const navigator = useNavigate();
  // update,delete할 때 id필요
  // const [createdAt, setCreatedAt] = useState(date);
  const [canEdit, setCanEdit] = useState(false);
  const [canUpdate, setCanUpdate] = useState(false);
  const [newContent, setNewContent] = useState(content);
  
  
  // 옆 창에 보내기 연습 

  // const getDate = () => {
  //   let result;
  //   const date = new Date(createdAt);
  //   result = `${date.getFullYear()}-${
  //     date.getMonth() + 1
  //   }-${date.getDate()} ${date.getHours()}:${date.getMinutes()}`;
  //   return result;
  // };

  const deleteHandler = (id) => {
    try {
      deletePost(id);
    } catch (err) {
      console.error(err);
    }

    navigator(0);
  };

  const updateHandler = (id) => {
    try {
      updatePost(id, {
        title: "Taejin Kim",
        category: "FREE",
        content: newContent,
      });

      setCanEdit(false);
      setCanUpdate(false);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <>
      <div
        style={{
          display: "flex",
          alignItems: "center",
          gap: "10px",
          position: "relative",
        }}
      >
        <ProfileCover>
        
        </ProfileCover>
        <div>
          <p style={{ fontSize: "18px", fontWeight: "bold" }}>{username}</p>
          <span
            style={{ fontSize: "13px", opacity: "0.6" }}
          >{`${date[0]}-${date[1]}-${date[2]} ${date[3]}:${date[4]}`}</span>
        </div>
        <div style={{ marginLeft: "auto", cursor: "pointer" }}>
          <HiDotsVertical onClick={() => setCanEdit((c) => !c)} />
        </div>
        {canEdit && (
          <div
            style={{
              position: "absolute",
              right: "30px",
              // boxShadow: "0 0 3px 3px rgba(0,0,0,0.1)",
              border: "1px solid",
              padding: "10px",
              borderRadius: "10px",
            }}
          >
            <button
              onClick={() => setCanUpdate((c) => !c)}
              style={{ display: "block" }}
            >
              {!canUpdate ? "수정" : "취소"}
            </button>
            <button
              onClick={() => deleteHandler(id)}
              style={{ display: "block" }}
            >
              삭제
            </button>
          </div>
        )}
      </div>
      {!canUpdate ? (
        <p style={{ marginTop: "20px" }}>{content}</p>
      ) : (
        <div>
          <Textarea
            value={newContent}
            onChange={(e) => setNewContent(e.target.value)}
          ></Textarea>
          <Btn onClick={() => updateHandler(id)}>수정하기</Btn>
        </div>
      )}
      <div
        style={{
          marginTop: "20px",
          display: "flex",
          color: "#aaa",
          paddingTop: "10px",
          borderTop: "1px solid #eee",
        }}
      >
    
         
        <IconWrap>
          <RiThumbUpFill />
          좋아요
        </IconWrap>
        <IconWrap>
          <IoChatbubbleEllipsesSharp />
          댓글달기
        </IconWrap> 
        
      </div>
    </>
  );
}

const ProfileCover = styled.div`
  background-color: orange;
  padding: 2px;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
`;



const IconWrap = styled.span`
  flex-grow: 1;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 5px;
  justify-content: center;
`;

const Textarea = styled.textarea`
  resize: none;
  border: none;
  border-radius: 10px;
  width: 100%;
  height: 80px;
  padding: 10px;
  background-color: #f1f3f5;
  &:focus {
    outline: none;
  }
  margin-top: 20px;
`;

const Btn = styled.button`
  margin-top: 10px;
  border: none;
  padding: 5px 10px;
  background-color: orange;
  color: white;
  border-radius: 10px;
  cursor: pointer;
`;
