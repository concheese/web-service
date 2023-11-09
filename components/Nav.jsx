import Wrapper from "../layout/Wrapper";
import logo from "../assets/logo-second.png";
import styled from "styled-components";
import { Link, useLocation , useNavigate} from "react-router-dom";
import { useSearchParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { isLoggedIn } from "../recoil/atom";

const Nav = () => {
  const { pathname } = useLocation();

  const [searchParams] = useSearchParams();
  const [userName, setUserName] = useState("");
  const token = searchParams.get("loginId");
  const [isLog, setIsLog] = useRecoilState(isLoggedIn);
  let navigate = useNavigate();

  const handleClick = () => {
    setIsLog(false)
    setUserName("")
    window.sessionStorage.clear()
    navigate(`/logout`)
    navigate( `/`)
  }

  const getUsers = async () => {
    if (!token) {
      return;
    }
    const api_url = `https://concheese.net/api/v1/user/info/${token}`;

    const users = await fetch(api_url);

    window.sessionStorage.setItem("token", token);
    setIsLog(true);
    const userData = await users.json();
    setUserName(userData.nickname);
    window.sessionStorage.setItem("nickname", userData.nickname);
  };

  useEffect(() => {
    const session_token = window.sessionStorage.getItem("token");
    console.log(session_token);
    console.log(typeof session_token);
    if (session_token) {
      setUserName(window.sessionStorage.getItem("nickname"));
      setIsLog(true);
    } else {
      getUsers();
    }
  }, []);

  return (
    <Navbar>
      <Wrapper>
        <NavContents>
          <Link to={{ pathname: "/" }}>
            <img width="32" src={logo} />
          </Link>

          <NavList>
            <NavItem p={(pathname === "/").toString()}>
              <Link to={{ pathname: "/" }}>홈</Link>
            </NavItem>
            <NavItem
              p={(
                pathname === "/feed" ||
                pathname === "/feed/info" ||
                pathname === "/feed/free"
              ).toString()}
            >
              <Link to={{ pathname: "/feed/info" }}>커뮤니티</Link>
            </NavItem>
          </NavList>

          <LogInBtn>
            {!isLog ? (
              <Link
                style={{
                  display: "block",
                  padding: "10px 20px",
                  backgroundColor: "black",
                  border: "none",
                  color: "white",
                  borderRadius: "8px",
                }}
                to={{ pathname: "/login" }}
                onMouseEnter={(e) => {
                  e.target.style.backgroundColor = "#f49c5d";
                }}
                onMouseLeave={(e) => {
                  e.target.style.backgroundColor = "black";
                }}
              >
                로그인
              </Link>
            ) : (
              <div
                style={{ display: "flex", alignItems: "center", gap: "5px" }}
              >
                <Link to={{ pathname: "/myPage" }}>
                  {userName}님 반갑습니다
                </Link>
               
                <button
                  style={{
                    display: "block",
                    padding: "10px 20px",
                    backgroundColor: "black",
                    border: "none",
                    color: "white",
                    borderRadius: "8px",
                  }}
                  onClick = {() => {handleClick()}}
                >
                  로그아웃
                </button> 
              </div>
            )}
          </LogInBtn>
        </NavContents>
      </Wrapper>
    </Navbar>
  );
};

const Navbar = styled.nav`
  background-color: white;
`;

const NavList = styled.ul`
  display: flex;
  gap: 20px;
`;

const NavContents = styled.div`
  display: flex;
  gap: 40px;
  align-items: center;
  justify-content: space-between;
  font-weight: bold;
  padding: 20px 0px;
`;

const NavItem = styled.li`
  color: ${(props) => (props.p === true ? "#f49c5d" : "black")};
  &:hover {
    border-top: 3px solid #f49c5d;
    color : orange;
  }
`;
// border: 1px solid #f49c5d;

const LogInBtn = styled.button`
  border: none;
  font-weight: bold;
  border-radius: 10px;
`;

export default Nav;
