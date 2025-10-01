
 
  const step1 = document.getElementById("step1");
  const step2 = document.getElementById("step2");
  const step3 = document.getElementById("step3");
  const messageBox = document.getElementById("message");
  let userName = "";
  // Step 1: Send OTP
  document.getElementById("sendOtpBtn").addEventListener("click", async () => {
    const username = document.getElementById("username").value.trim();
    if (!username) {
      messageBox.innerHTML = "<span class='text-danger'>Username is required</span>";
      return;
    }
    userName = username;
    let body = {
        userName : username
    }
    fetch("http://localhost:8080/server/otprequest",{
        method:"POST",
        body:JSON.stringify(body),
        headers:{ "Content-Type" : "application/json"},
        credentials:"include"
    }).then(res => {
        if(!res.ok){
            throw new Error("Network response was not ok");
        }

    }).catch(err => { validateotp
        console.log(err);
    })
    
    // Example: let res = await fetch("/send-otp", {method:"POST", body: JSON.stringify({username})});
    messageBox.innerHTML = "<span class='text-success'>OTP sent successfully to your email</span>";
    
    step1.classList.add("d-none");
    step2.classList.remove("d-none");
  });

  // Step 2: Verify OTP
  document.getElementById("verifyOtpBtn").addEventListener("click", async () => {
    const otp = document.getElementById("otp").value.trim();
    if (!otp) {
      messageBox.innerHTML = "<span class='text-danger'>Enter OTP</span>";
      return;
    }
    let body = {
        userName,
        otp
        
    }
    // 🚀 Call backend to verify OTP
    fetch("http://localhost:8080/server/validateotp",{
        method:"POST",
        body:JSON.stringify(body),
        headers:{ "Content-Type" : "application/json"},
        credentials:"include"
    }).then(res => {
        if(!res.ok){
            throw new Error("Network response was not ok");
        }

    }).catch(err => { 
        console.log(err);
    })
    // Example: let res = await fetch("/verify-otp", {method:"POST", body: JSON.stringify({otp})});
    messageBox.innerHTML = "<span class='text-success'>OTP verified. Please set your new password</span>";

    step2.classList.add("d-none");
    step3.classList.remove("d-none");
  });

  // Step 3: Change Password
  document.getElementById("resetPassBtn").addEventListener("click", async () => {
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    let validationMsg = checkPass(newPassword, confirmPassword);
    if (validationMsg !== "Password is valid") {
        messageBox.innerHTML = `<span class='text-danger'>${validationMsg}</span>`;
        return;
    }

    let body = {
        userName,
        password : newPassword
    }
    // 🚀 Call backend to update password
    fetch("http://localhost:8080/server/changepassword",{
        method:"PUT",
        body:JSON.stringify(body),
        headers:{ "Content-Type" : "application/json"},
        credentials:"include"
    }).then(res => {
        if(!res.ok){
            throw new Error("Network response was not ok");
        }

    }).catch(err => { 
        console.log(err);
    })
    // Example: let res = await fetch("/reset-password", {method:"POST", body: JSON.stringify({newPassword})});
    messageBox.innerHTML = "<span class='text-success'>Password changed successfully ✅</span>";
     setTimeout(() => {
          window.location.href = "Login.html"; // <-- change this to your login page path
      }, 2000);
    step3.classList.add("d-none");
  });
 

function checkPass(password, cpassword) {
  if (password.length == 0) {
    return "Password is Empty";
  }
  // 1. Password should not start with a number
  if (!isNaN(password[0])) {
    return "Password first character must not be a number";
  }
  // 2. At least one uppercase letter
  if (!/[A-Z]/.test(password)) {
    return "Password must contain at least one capital letter";
  }
  // 3. At least one digit
  if (!/[0-9]/.test(password)) {
    return "Password must contain at least one number";
  }
  // 4. At least one special character
  if (!/[!@#$%^&*(),.?":{}|<>]/.test(password)) {
    return "Password must contain at least one special character";
  }
  // 5. Minimum length
  if (password.length < 8) {
    return "Password length must be at least 8";
  }
  // 6. Confirm password check
  if (password !== cpassword) {
    return "Passwords do not match";
  }

  return "Password is valid";
}
