import { useEffect, useState } from "react";
import Contents from "../layout/Contents";
import Wrapper from "../layout/Wrapper";

export default function MyPage() {
  const [user, setUser] = useState("");
  const getUsers = async () => {
    const users = await fetch(
      `https://concheese.net/api/v1/user/info/${window.sessionStorage.getItem(
        "token"
      )}`
    );

    const results = await users.json();
    console.log(results);
    setUser(results);
  };
  useEffect(() => {
    getUsers();
  }, []);

  return (
    <Wrapper>
      <Contents>
        <h1 style={{ marginBottom: "20px", color: "orange" }}>
          {user.nickname}님의 페이지
        </h1>
        <h2>{user.nickname}님의 정보</h2>
        <p>이름: {user.name}</p>
        <p>이메일: {user.email}</p>

        <div style={{ marginTop: "30px" }}>
          <h2 style={{ marginBottom: "20px" }}>내가 방문한 페이지</h2>
          <div style={{ display: "flex", gap: "20px" }}>
            <div>
              <div
                style={{
                  width: "100px",
                  height: "100px",
                  backgroundColor: "#f0a22e",
                  borderRadius: "10px",
                }}
              ></div>
              <span>세븐틴 콘서트</span>
            </div>
            <div>
              <div
                style={{
                  width: "100px",
                  height: "100px",
                  backgroundColor: "#f0a22e",
                  borderRadius: "10px",
                }}
              ></div>
              <span>빅뱅 콘서트</span>
            </div>
            <div>
              <div
                style={{
                  width: "100px",
                  height: "100px",
                  backgroundColor: "#f0a22e",
                  borderRadius: "10px",
                }}
              ></div>
              <span>부활 콘서트</span>
            </div>
            <div>
              <div
                style={{
                  width: "100px",
                  height: "100px",
                  backgroundColor: "#f0a22e",
                  borderRadius: "10px",
                }}
              ></div>
              <span>승리 콘서트</span>
            </div>
            <div>
              <div
                style={{
                  width: "100px",
                  height: "100px",
                  backgroundColor: "#f0a22e",
                  borderRadius: "10px",
                }}
              ></div>
              <span>세븐틴 콘서트</span>
            </div>
          </div>
        </div>

        <div style={{ marginTop: "30px" }}>
          <h2 style={{ marginBottom: "20px" }}>내가 북마크한 공연</h2>
          <div style={{ display: "flex", gap: "20px" }}>
            <div>
              <div
                style={{
                  width: "100px",
                  height: "100px",
                  backgroundColor: "#f0a22e",
                  borderRadius: "10px",
                }}
              ></div>
              <span>아이브 콘서트</span>
            </div>
            <div>
              <div
                style={{
                  width: "100px",
                  height: "100px",
                  backgroundColor: "#f0a22e",
                  borderRadius: "10px",
                }}
              ></div>
              <span>르세라핌 콘서트</span>
            </div>
          </div>
        </div>

        <div style={{ marginTop: "30px" }}>
          <h2 style={{ marginBottom: "20px" }}>알람설정한 공연</h2>
          <div style={{ display: "flex", gap: "20px" }}>
            <div>
              <div
                style={{
                  width: "100px",
                  height: "100px",
                  backgroundColor: "#f0a22e",
                  borderRadius: "10px",
                }}
              ></div>
              <span>아이브 콘서트</span>
            </div>
          </div>
        </div>
      </Contents>
    </Wrapper>
  );
}
