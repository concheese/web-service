const EndPoint = `https://concheese.net/api/v1/info/concert`

export async function getInfoPosts() {
    const response = await fetch(
       EndPoint + `/list`
    );
  
    if (!response.ok) {
      throw new Error("getPost에 문제가 생김!");
    }
  
    const body = await response.json();
    console.log("body: ", body);
    return body;
  }

 // filter하기 
 export async function getInfoFilter(id , index) {
  
  const response = await fetch(EndPoint + `/list?${index}=${id}`)
  if(!response.ok){
    throw new Error("filter에 문제가 생김")
    
  }
  const body = await response.json();
  console.log("body : " , body);
  return body;
 }
 

  
  export async function writeInfoPost(formData) {
    console.log(formData);
    const response = await fetch(
      EndPoint + "/register",
     
      {
        method: "POST",
        headers: {
          "Content-type" : "application/json",


        },
        body: JSON.stringify(formData),
      }
    );
  
    if (!response.ok) {
      throw new Error("writePost에 문제가 생김");
    }
  
    const body = await response.json();
    console.log(body);
    return body;
  }
  
  export async function updateInfoPost(formData ) {
    const url = EndPoint + `/update`;
    const response = await fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData)
      
    });
  
    if (!response.ok) {
      throw new Error("데이터를 알맞게 보냈는지 확인하세요");
    }
  
    const body = await response.json();
    return body;
  }
  
  export async function deleteInfoPost(id) {
    console.log(EndPoint + `/delete/${id}`)
    const response = await fetch(
      EndPoint + `/delete/${id}`,
      {
        method: "DELETE",
      }
    );
    if (!response.ok) {
      throw new Error("error");
    }
  
  }
  