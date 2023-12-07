import React from "react";
import ReactDom from "react-dom/client";
import Main from "./Main";
import { createGlobalStyle } from "styled-components";

const GlobalStyle = createGlobalStyle`
    *{
        margin:0;
        padding:0;
        box-sizing:border-box;
    }

    li{
        list-style-type:none;
    }

    a{
        text-decoration:none;
        color: inherit;
        cursor:pointer;
    }
`;

const root = ReactDom.createRoot(document.querySelector("#root"));

root.render(
  <>
    <GlobalStyle />
    <Main />
  </>
);
