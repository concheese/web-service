import Contents from "../layout/Contents";
import Wrapper from "../layout/Wrapper";
import styled from "styled-components";
import { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import { Link, useLocation } from "react-router-dom";

const Feed = () => {
  const { pathname } = useLocation();

  return (
    <div>
      <Wrapper>
        <Contents>
          <ul style={{ position: "absolute", top: "140px" }}>
            <Link to={{ pathname: "/feed/info" }}>
              <SideLi p={(pathname === "/feed/info").toString()}>정보</SideLi>
            </Link>
            <Link to={{ pathname: "/feed/free" }}>
              <SideLi
                p={(pathname === "/feed/free").toString()}
                style={{ marginTop: "5px" }}
              >
                자유
              </SideLi>
            </Link>
          </ul>

          <div style={{ marginLeft: "80px" }}>
            <Outlet />
          </div>
        </Contents>
      </Wrapper>
    </div>
  );
};

const SideLi = styled.li`
  padding: 10px;
  border-radius: 50px;
  color: white;

  background-color: ${(props) => (props.p === "true" ? "orange" : "#dee2e6")};
`;

export default Feed;
