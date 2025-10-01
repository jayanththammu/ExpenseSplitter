 let createBtn = document.getElementById("createBtn");
function setupPasswordMask(inputId) {
    const input = document.getElementById(inputId);
    let realPassword = '';

    input.addEventListener('input', (e) => {
        const value = input.value;

        if (value.length > realPassword.length) {
            const lastChar = value[value.length - 1];
            realPassword += lastChar;

            input.type = 'text';
            input.value = '•'.repeat(realPassword.length - 1) + lastChar;

            setTimeout(() => {
                input.type = 'password';
                input.value = '•'.repeat(realPassword.length);
            }, 1000);
        } else if (value.length < realPassword.length) {
            realPassword = realPassword.slice(0, value.length);
        }

        // ✅ Attach the real password to the DOM element
        input.dataset.realPassword = realPassword;
    });
}

 
setupPasswordMask('password');
setupPasswordMask('cpassword');


 
 
 

function getBody(){

    let userName = document.getElementById("userName").value;
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let password = document.getElementById("password").dataset.realPassword || "";
    let cpassword = document.getElementById("cpassword").dataset.realPassword || "";
    let mobileNo = document.getElementById("mobileNo").value;
    let email = document.getElementById("email").value;
   
    let checkPassword = checkPass(password,cpassword);
    
    if(checkPassword !== "Password is valid"){
        alert(checkPassword);
        return;
    }
    
    let body = {
    userName,
    firstName,
    lastName,
    password,
    mobileNo,
    email
    };

    return body;

}
function checkPass(password,cpassword){
    if(password.length == 0){
        return "Password is Empty";
    }
    // Password should not be number
    if(!isNaN(password[0])){
        return "password First digit must not start with a number";
    }
     // 2. At least one uppercase letter
    if(!/[A-Z]/.test(password)){
        return "password Must Contain atleast one Capital letter";
    }
      // 3. At least one digit
    if(!/[0-9]/.test(password)){
        return "Password Must contain atleast one number";
    }
     // 4. At least one special character (you can adjust allowed symbols)
     if (!/[!@#$%^&*(),.?":{}|<>]/.test(password)) {
        return "Password must contain at least one special character";
    }

    if(password.length < 8){
        return "password length must be atleast 8";
    }
    if(password !== cpassword){
        return "Password does not match ";
    }
    
    return "Password is valid";

}

createBtn.addEventListener("click",function(e){

    e.preventDefault();
    let body = getBody();
    if (Object.keys(body).length === 0) {
    return;
}

    fetch("http://localhost:8080/server/create",{
        method:"POST",
        body:JSON.stringify(body),
        headers:{ "Content-Type" : "application/json"},
        credentials:"include"
    }).then( res => {
        if(!res.ok){
            throw new Error("Network response was not ok");
        }
        return res.text;
    }).then(
        data =>{
            alert("data");
            window.location.href = "Login.html";
        }
    ).catch();
 
});