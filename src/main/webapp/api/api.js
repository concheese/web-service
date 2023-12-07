export async function getFreePosts() {
  const response = await fetch(
    "http://swacademy.null0xff.com:8080/api/v1/community/posts"
  );

  if (!response.ok) {
    throw new Error("error");
  }

  const body = await response.json();
  console.log("body: ", body);
  return body;
}

export async function writeFreePost(formData) {
  console.log(formData);
  const response = await fetch(
    "http://swacademy.null0xff.com:8080/api/v1/community/post",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    }
  );

  if (!response.ok) {
    throw new Error("데이터를 알맞게 보냈는지 확인하세요");
  }

  const body = await response.json();
  console.log(body);
  return body;
}

export async function updatePost(id, formData) {
  const url = `http://swacademy.null0xff.com:8080/api/v1/community/post/${id}`;
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

export async function deletePost(id) {
  const response = await fetch(
    `http://swacademy.null0xff.com:8080/api/v1/community/post/${id}`,
    {
      method: "DELETE",
    }
  );
  if (!response.ok) {
    throw new Error("error");
  }

}
