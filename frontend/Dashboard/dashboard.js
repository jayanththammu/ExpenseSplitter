
//-------------------Log out Code------------
document.getElementById("logoutBtn").addEventListener("click",function(){
    fetch("http://localhost:8080/server/logout",{
        method:"GET",
        credentials:"include"
    }).then(res =>{
        if(!res.ok){ throw new Error("Network Response Was not ok");
            return res.text();
        }
    }).then( () => {
       setTimeout(2000,window.location.href = "/Login/Login.html");
    }).catch(err => {
        console.log(err);
    })
});
// -----------------Code For Pop Ups -----------------
document.getElementById("splitBtn")
.addEventListener("click",function(){
      
      popUpToggle(document.querySelector(".splitpopup-cover"));
    });
document.getElementById("friendBtn")
.addEventListener("click",function(){ 
     
    popUpToggle(document.querySelector(".friendpopup-cover"));
     
});
document.getElementById("groupBtn")
.addEventListener("click",function(){ 
    popUpToggle(document.querySelector(".grouppopup-cover"));
});


function popUpToggle(popUp){
 
    popUp.classList.add("activepopupcover");
};


document.querySelectorAll(".closebtn").forEach( close =>{
    close.addEventListener("click",function(){
       
        close.closest(".popupcover").classList.remove("activepopupcover");
    });
});

//  --------------Code for Split change ---------------
document.getElementById("selectType").addEventListener("change",function(){
    let splitPop = document.querySelector(".split-popup");

    let friendList = splitPop.querySelector(".friendslist");
    let groupList = splitPop.querySelector(".grouplist");
    friendList.style.display = "none";
    groupList.style.display= "none";
    if(this.value == "friends"){
    friendList.style.display  = "flex";
    groupList.style.display = "none";

    }else{
    groupList.style.display  = "flex";
        friendList.style.display = "none";
    }
});
 

// --------------Code To Add Friend --------------
let friends = [];
let groups = [];
document.getElementById("saveFriendBtn").addEventListener("click",function(e){
    e.preventDefault();
    let friendName = document.getElementById("friendName").value.toLowerCase().trim();
    if(friendName === ""){
        alert("Friend Name is Empty");
        return;
    }
    
    let body ={
        friendName
    }
    fetch("http://localhost:8080/friendserver/addfriend",{
        method:"POST",
        headers : {"Content-Type":"application/json"},
        body:JSON.stringify(body),
        credentials:"include"
    })
    .then(res =>{
        if(!res.ok) throw new Error("Network Response Was Not ok");
        return res.ok;
    }).then(()=>{
        console.log("Added SUccessfully");
    }).catch(err =>{
        console.log(err);
    });
});

// Code To Fetch Friends 
function loadFriends(){
  
  return fetch("http://localhost:8080/friendserver/getfriends",{
        method:"GET",
        credentials:"include"
    }).then(res =>{
        if(!res.ok) throw new Error("Network Response Was Not ok");
        return res.json();
    }).then(data =>{
       
       friends =data;
    })
    .catch(err => {
        console.log(err);
    })
}
function loadFriendsIntoFriendCard(){
     
    let friendCardContent = document.querySelector(".friend-card-content");
    friendCardContent.innerHTML ="";
    friends.forEach(friend => {
        let id = friend.id;
        let name = friend.friendName;
     
         let friendCardItem = document.createElement("div");
         friendCardItem.classList.add("friend-card-item");
        let span = document.createElement("span");
        
        span.textContent= name;
       
        friendCardItem.appendChild(span);
        friendCardContent.appendChild(friendCardItem);
    });

}
//-------------------------load friendslist into popUps----------------
function loadFriendsInPopUps(){
    document.querySelectorAll(".friendslist").forEach( list => {
    
    let idName = list.classList.contains("grouppopup") ? "groupfriend-" : "splitfriend-" ;
    list.innerHTML = "";
    let span = document.createElement("span");
    span.textContent="Friends";
    list.appendChild(span);
    friends.forEach(friend => {
        let friendItem = document.createElement("div");
        friendItem.classList.add("friend-item");

        let input = document.createElement("input");
        let label = document.createElement("label");

        input.type = "checkbox";
        input.name = "friends";
        input.id = idName+friend.id;
        input.value = friend.id;

        label.setAttribute("for",idName+friend.id);
        label.textContent = friend.friendName;

        friendItem.appendChild(input);
        friendItem.appendChild(label);

        list.appendChild(friendItem);
    });
 
 });

} 


// ---------------DOM CONTENT LOADED ------------------
document.addEventListener("DOMContentLoaded",function(){
    loadFriends().then( () => {
        loadFriendsIntoFriendCard();
        loadFriendsInPopUps();
    });

    fetchGroups();
    fetchLatestSplit();

});

//-----------------Code TO Save Group-----------------
document.querySelector("#save-group-btn").addEventListener("click",function(e){
    e.preventDefault();
    console.log("clicked");
    let groupPopUp = document.querySelector(".grouppopup-cover");
    let groupName = document.getElementById("groupName").value.trim();
    if(groupName === "") { alert("Group Name was Empty"); 
                        return;
    }
    let checkedFriends = groupPopUp.querySelectorAll('input[name="friends"]:checked');
    let friendsIds = Array.from(checkedFriends).map(input =>Number(input.value));

 
    let body = {
     groupName,
     friendsIds
     
    }
    console.log(body);
    fetch("http://localhost:8080/groupserver/addgroup",
       {  
        method:"POST",
        headers : {"Content-Type":"application/json"},
        body:JSON.stringify(body),
        credentials:"include"
       }
    ).then(res => {
        if(!res.ok){ throw new Error("Network Response Was Not ok");
            return res.text();
        }
        return res.text();
    }).then(data=>{
        alert(data);
        fetchGroups().then( () =>{ loadGroupsIntoSplitPopUp();});
    }).catch(err =>{ console.log(err);});

});



document.querySelectorAll(".group-card-item span").forEach( f => {
    f.addEventListener("click",function(e){
 
        console.log("clicked");
    });
});

function fetchGroups(){

 return fetch("http://localhost:8080/groupserver/getgroups",{
        method : "GET",
        credentials:"include"
    }).then(res => {
        if(!res.ok){ throw new Error("Network Response Was Not ok");
            return res.text();
        }
        return res.json();
    }).then(data=>{
            
        groups = data;
        loadGroups();
        loadGroupsIntoSplitPopUp();
       
    }).catch(err =>{ console.log(err);});

}
function loadGroups(){
    

   let groupContent = document.querySelector(".group-card-content");
        groupContent.innerHTML = "";
        
    groups.forEach( group => {

        let groupItem = document.createElement("div");
        groupItem.classList.add("group-card-item");
        
        let span = document.createElement("span");
        span.textContent =group.group_name;

        groupItem.appendChild(span);

        groupContent.appendChild(groupItem);

    }); 

    
}
function loadGroupsIntoSplitPopUp(){

    let groupList = document.querySelector(".splitpopup-cover .grouplist");

 
    groupList.innerHTML = "";

    let span = document.createElement("span");
    span.textContent = "Groups";

    groupList.appendChild(span);

    groups.forEach(group => {
        let groupItem = document.createElement("div");
        groupItem.classList.add("group-item");

        let input  = document.createElement("input");
        input.type = "radio";
        input.name = "groups";
        let id = `splitgroup-${group.id}`;
        input.id = id;
        input.value = group.id;

        let label = document.createElement("label");
        label.setAttribute("for",id);
        label.textContent = group.group_name;

        groupItem.appendChild(input);
        groupItem.appendChild(label);

        groupList.appendChild(groupItem);

    });

}
//  ---------------------------Code To Save Split----------------------

document.getElementById("saveSplit-btn").addEventListener("click",function(e){
    e.preventDefault();

    let SplitName = document.getElementById("splitName").value;

    if(SplitName ===""){ alert("Split Name Cannot be Empty");
        return;
    }
    let amountInput = document.getElementById("amount");
    let amount = document.getElementById("amount").value;
    if(amount ===""){ alert("Amount Cannot be Empty");
        return;
    }
    else if( amount < 0){
         alert("Amount Cannot be Negative");
        amountInput.value ="";
        return;
    }
    const splitType = document.getElementById("selectType").value;

    let selectedFriends = [];
    let selectedGroup = [];
    let groupTransaction = false;

    if (splitType === "friends") {
    selectedFriends = Array.from(document.querySelectorAll('input[name="friends"]:checked'))
        .map(cb => cb.value);
         if(selectedFriends.length === 0){ alert("No Friends Selected");
        return;
    }

} else if (splitType === "groups") {
    groupTransaction = true;
    selectedGroup = Array.from(document.querySelectorAll('input[name="groups"]:checked'))
        .map(g => g.value);

     if(selectedGroup.length === 0){ alert("No Group Selected");
        return;
    }
}

    let body = {
       "transactionName": SplitName,
        amount,
        groupTransaction,
        "friendsId":selectedFriends.map(id => parseInt(id)),
        "groupId": selectedGroup.length > 0 ? parseInt(selectedGroup[0]) : null
    }
    console.log(body);

    SplitTransactionFetch(body).then( 
        ()=> {
            fetchLatestSplit();
        }
    );
     
 
});

function SplitTransactionFetch(body){


    return fetch("http://localhost:8080/transactionserver/createsplit",{
        method : "POST",
        headers : {"Content-Type":"application/json"},
        body:JSON.stringify(body),
        credentials:"include"
    }).then(res => {
        if(!res.ok){ throw new Error("Network Response Was Not ok");
            return res.text();
        }
        return res.text();
    }).catch(err =>{ console.log(err);});

}
 function fetchLatestSplit(){

    fetch("http://localhost:8080/transactionserver/getLatestSplit",{
        method:"GET",
        credentials:"include"
    }).then(res => {
        if(!res.ok){ throw new Error("Network Response Was Not ok"); }
        return res.json();
    }).then(data=>{
 
      
        document.getElementById("split-header-span").textContent = `Latest Split - ${data.transactionName}`;
       
         let splitCardContent = document.querySelector(".split-card-content");
         splitCardContent.innerHTML= "";
         let span = document.createElement("span");
        
         
         span.innerHTML = `<b>Amount</b> - ${data.amount}&#8377`;
          splitCardContent.appendChild(span);
         data.shareMembers.forEach( mem => {

              span = document.createElement("span");
             span.innerHTML = `${mem} - ${data.shareAmount}&#8377`;

            splitCardContent.appendChild(span);

             

         });
 
    }).catch(err =>{ console.log(err);});
}
 