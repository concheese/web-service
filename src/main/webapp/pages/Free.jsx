import { styled } from "styled-components";
import { FcGallery } from "react-icons/fc";
import { getFreePosts, writeFreePost } from "../api/api";
import { useState, useEffect } from "react";
import FreeCard from "../components/FreeCard";


export default function Free() {
  const [posts, setPosts] = useState([]);
  const [content, setContent] = useState();
  const [artist, setArtist] = useState();
  

  const getPosts = async () => {
    try {
      const result = await getFreePosts();
      setPosts(result);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    getPosts();
  }, []);

  const addPost = () => {
    if (!content || !artist) {
      alert("내용을 입력해주세요");
      return;
    }
    try {
      writeFreePost({
        category: "FREE",
        content: content,
        title: artist,
      });
    } catch (err) {
      console.err(err);
    }
    setContent("");
    setArtist("");
    getPosts();
  };


  return (

   
       <div> 
      <Editor>
        <EditorTitle style={{}}>자유주제</EditorTitle>
        <div>
          <label htmlFor="name">닉네임</label>
          <Input
            style={{ padding: "10px", fontWeight: "bold", color: "#aaa" }}
            type="text"
            value={artist}
            id="name"
            onChange = {(e) => {setArtist(e.target.value)}}
          />
          <label style={{ marginTop: "10px", display: "block" }}>내용</label>
          <Textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
          ></Textarea>
        </div>
        <Btn onClick={addPost}>게시하기</Btn>
      </Editor>
      {posts.map((item) => {
        return (
          <Box key={item.postId}>
            <FreeCard
              id={item.postId}
              date={item.createdAt}
              username={item.title}
              content={item.content}
            />
          </Box>
        );
      })}
      </div>
    
  );
}

const Box = styled.div`
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2);
  border-radius: 10px;
  padding: 20px;
  margin-bottom: 20px;
  max-width: 480px;
`;

const Editor = styled.div`
  max-width: 480px;
  padding: 10px 15px;
  border-radius: 10px;
  margin-bottom: 30px;
  box-shadow: 0 0 10px 3px rgba(0, 0, 0, 0.1);
`;

const EditorTitle = styled.h3`
  padding-bottom: 5px;
  margin-bottom: 10px;
  border-bottom: 1px solid #eee;
  color: orange;
  fontsize: 16px;
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
`;

const Input = styled.input`
  width: 100%;
  border: none;
  background-color: #f1f3f5;
  border-radius: 10px;
  padding: 8px 0;
  &:focus {
    outline: none;
  }
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