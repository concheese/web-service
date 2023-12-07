import { RecoilRoot } from "recoil";
import Nav from "./components/Nav";

export default function App({ children }) {
  return (
    <>
      <RecoilRoot>
        <Nav />
        <div>{children}</div>
      </RecoilRoot>
    </>
  );
}

//firebase
