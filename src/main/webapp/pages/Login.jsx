import styled from "styled-components";
import Contents from "../layout/Contents";
import Wrapper from "../layout/Wrapper";
import { SiNaver } from "react-icons/si";
import { useEffect } from "react";

export default function Login() {
  const NAVER_CLIENT_ID = "gl0ZZ40v4N4u7AfOZ_74";
  const STATE = "flase";
  const REDIRECT_URI = "https://concheese.net/login/oauth2/code/naver";
  const NAVER_AUTH_URL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${NAVER_CLIENT_ID}&state=${STATE}&redirect_uri=${REDIRECT_URI}`;

  const NaverLogin = () => {
    window.location.href = NAVER_AUTH_URL;
  };

  return (
    <Wrapper>
      <Contents>
        <Container>
          <LoginBtn onClick={NaverLogin}>
            <SiNaver style={{ marginRight: "10px" }} />
            네이버로 로그인하기
          </LoginBtn>
        </Container>
      </Contents>
    </Wrapper>
  );
}

const Container = styled.div`
  border-radius: 20px;
  width: 480px;
  height: 600px;
  margin: 0 auto;
`;

const LoginBtn = styled.button`
  width: 100%;
  background-color: #26d107;
  color: white;
  border: none;
  border-radius: 10px;
  padding: 20px 0;
  font-size: 19px;
  cursor: pointer;
  margin-top: 50px;
`;
