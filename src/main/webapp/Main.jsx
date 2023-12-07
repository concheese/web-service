import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Feed from "./pages/Feed";
import WriteConcert from "./pages/WriteConcert";
import App from "./App";
import Free from "./pages/Free";
import Info from "./pages/Info";
import Login from "./pages/Login";
import MyPage from "./pages/MyPage";

const Main = () => {
  return (
    <BrowserRouter>
      <App>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="login" element={<Login />} />
          <Route path="myPage" element={<MyPage />} />
          <Route path="feed/" element={<Feed />}>
            <Route path="info" element={<Info />} />
            <Route path="free" element={<Free />} />
          </Route>
          <Route path="/writeConcert" element={<WriteConcert />} />
        </Routes>
      </App>
    </BrowserRouter>
  );
};

export default Main;
