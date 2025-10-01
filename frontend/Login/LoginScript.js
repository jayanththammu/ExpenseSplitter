const loginBtn = document.querySelector("#loginBtn");


//Login the account when pressed on login btn
loginBtn.addEventListener("click",function(){
    console.log("hi");
    let userName = document.getElementById("userName").value.trim();
    let password = document.getElementById("password").value.trim();

    const body = {
        userName : userName,
        password : password
    }

    loginAcc(body);
});

//  FETCHES  Backend  login method
function loginAcc(body){

        fetch("http://localhost:8080/server/login",{
            method:"Post",
            body:JSON.stringify(body),
            headers:{ "Content-Type" : "application/json"},
            credentials:"include"
        }).then(res =>{
            if(!res.ok){
                throw new Error("Network Response was not ok");
            }
            return res.text();
        }).then(data =>{
          
            setTimeout(() =>{
                 window.location.href = "/Dashboard/dashboard.html";
            },2000);
          
        }
        ).catch(e =>{
            console.log(e);
        });
}