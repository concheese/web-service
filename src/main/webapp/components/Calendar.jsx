// Calendar.js

import React, { useState } from "react";
import "./Calender.css"; // 이 부분에서 CSS 파일을 불러옵니다.

const Calendar = () => {
  // <useState들 >
  const [currentDate, setCurrentDate] = useState(new Date()); // 날짜  -- A
  const [circle, makeCircle] = useState(0); // 클릭 시 동그라미  -- B
  const [datas, setDatas] = useState([]); // circle 만들 때 쓸 것
  const [cardData, dataRender] = useState([]); //card를 구성할 것들 --C
  const [isPopupVisible, setPopupVisible] = useState(false); // setPopupVisible을 이용해 상태를 바꾸기
  console.log(isPopupVisible);
  // A 지금 시간을 나타내 주는 객체
  const daysInMonth = new Date(
    currentDate.getFullYear(),
    currentDate.getMonth() + 1,
    0
  ).getDate();
  const firstDayOfMonth = new Date(
    currentDate.getFullYear(),
    currentDate.getMonth(),
    1
  ).getDay();

  const days = [];
  for (let i = 1; i <= daysInMonth + firstDayOfMonth; i++) {
    if (i > firstDayOfMonth) {
      days.push(i - firstDayOfMonth);
    } else {
      days.push("");
    }
  }

  // B  동그라미를 그리고 날짜를 C에 넘겨줌
  const handleClick = (event) => {
    const clickedEnd = event.target; // 태그 안의 내용을 읽어주기
    //1) 상태를 true로 해 창이 나타나게 하기
    setPopupVisible((isPopupVisible) => (isPopupVisible = true));

    //2) 색깔을 추가하는 방법
    makeCircle((circle) => (circle += 1));
    if (circle < 1) {
      clickedEnd.style.backgroundColor = "#ed7014";
      clickedEnd.style.color = "white";
    } else {
      clickedEnd.style.backgroundColor = "#ed7014";
      clickedEnd.style.color = "white";
      datas.forEach((value) => {
        value.style.backgroundColor = "white";
        value.style.color = "black";
        console.log(datas.pop());
      });
    }
    setDatas((prevDatas) => [...prevDatas, clickedEnd]); // 객체 추가
  };
  // C 더미 데이터를 받아서 창에 옮기기

  // popup을 state로 정의해 놓고 click 여부에 따라 보이게 하기
  return (
    <div className="mold">
      <div className="calendar">
        <div className="header">
          <button
            onClick={() =>
              setCurrentDate(
                new Date(
                  currentDate.getFullYear(),
                  currentDate.getMonth() - 1,
                  1
                )
              )
            }
          >
            &lt;
          </button>
          <h2>
            {currentDate.toLocaleString("default", {
              month: "long",
              year: "numeric",
            })}
          </h2>
          <button
            onClick={() =>
              setCurrentDate(
                new Date(
                  currentDate.getFullYear(),
                  currentDate.getMonth() + 1,
                  1
                )
              )
            }
          >
            &gt;
          </button>
        </div>
        <div className="days">
          {["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"].map(
            (day, index) => (
              <div key={index} className="front">
                {day}
              </div>
            )
          )}
          {days.map((day, index) => (
            <div className="end" key={index}>
              <div
                className={`circleA ${day ? "" : "empty"}`}
                onClick={handleClick}
                async
              >
                {day}
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className={`popup ${isPopupVisible ? "show-popup" : ""}`}></div>
    </div>
  );
};

export default Calendar;
