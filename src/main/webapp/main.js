if(!sessionStorage.getItem('JWT') && window.location.href.split("/").slice(-1)[0] == 'home.html'){
    window.location.href = 'index.html'
}

function openPopup(open) {
    let popups = document.querySelectorAll("div[id^=popup-]")

    for(popup of popups){
        popup.style.display = 'none';
    }
    document.querySelector("#popup-" + open).style.display = 'block';
}

function studentToevoegen() {
    document.querySelector("#studentToevoegenButton").addEventListener("click", function () {
        let formData = new FormData(document.querySelector("#studentToevoegen"));
        let encData = new URLSearchParams(formData.entries());

        fetch("/api/student", {method: 'POST', body: encData, headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
            .then(response => {
                if(response.status == 200){
                    alert("Student toegevoegd");
                } else{
                    alert("Gebruiker bestaat al");
                }
            })
    })
}
function studentInfo() {
    fetch("/api/student", {method: 'GET',  headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response => response.json())
        .then(function (myJson) {
            document.querySelector("#voornaam").value = myJson.voornaam;
            if(myJson.tusenvoegsel == undefined){
                document.querySelector("#tussenvoegsel").value = "";
            }else{
                document.querySelector("#tussenvoegsel").value = myJson.tusenvoegsel;
            }
            document.querySelector("#achternaam").value = myJson.achternaam;
            document.querySelector("#email").value = myJson.email;
            // document.querySelector("#wachtwoord").value = myJson.wachtwoord;
    })
}

function studentWijzig() {
    let formData = new FormData(document.querySelector("#studentInfo"));
    let encData = new URLSearchParams(formData.entries());
    fetch("/api/student", {method: 'PUT', body: encData, headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response =>{
            if(response.status == 200){
                alert("Student gewijzigd");
                studentInfo();
            }else {
                alert("Student kon niet worden gewijzigd");
            }
        })
}

function studentDelete() {
    let formData = new FormData(document.querySelector("#studentInfo"));
    let encData = new URLSearchParams(formData.entries());
    fetch("/api/student", {method: 'DELETE', body: encData, headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response =>{
            if(response.status == 200){
                alert("Student verwijderd");
                window.sessionStorage.removeItem('JWT');
                window.location.href = 'index.html';

            } else{
                alert("Student kan niet worden verwijderd");
            }
        })
}

function huisToevoegen() {
    let formData = new FormData(document.querySelector("#huisToevoegen"));
    let encData = new URLSearchParams(formData.entries());

    fetch("/api/huis", {method: 'POST', body: encData, headers:{ 'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT") }})
        .then(response => {
            if(response.status == 200){
                alert("Huis is toegevoegd");
            }else {
                alert("Dit huis bestaat al");
            }
        })
}

function login(){
    let formData = new FormData(document.querySelector("#login"));
    let encData = new URLSearchParams(formData.entries());
    fetch("/api/authentication", { method: 'POST', body: encData })
        .then(async response => {
            if(response.status == 200){
                json = await response.json();

                window.sessionStorage.setItem('JWT', json.JWT);
                console.log("Gebruiker ingelogt");
                window.location.href = 'home.html';
            } else{
                alert("Email en wachtwoord komen niet overeen");
            }
        })
}

function laadHuizen() {
    fetch("/api/huis", {method: 'GET', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response => response.json())
        .then(function (myJson) {
            let select = document.querySelector("#kiesHuis");
            select.innerHTML = '';
            for(huis of myJson){

                select.innerHTML += '<option value="' + huis.id + '">' + huis.naam + '</option>'
            }
        })
}

function uitloggen(){
    window.sessionStorage.removeItem('JWT');
    window.location.href = 'index.html'
}



/*function huisEnDatum(){
    fetch("/api/huis/{id}/{datum", {method: 'GET', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response => response.json())
        .then(function (myJson){

        })

}*/


document.addEventListener("DOMContentLoaded", () => {
    laadHuizen();
    document.querySelector("#addHuis").addEventListener("click", function () {
        openPopup("HuisToevoegen");
    });
    document.querySelector("#nieuweStudent").addEventListener("click", function () {
        openPopup("StudentToevoegen");
    });
    document.querySelector("#studentPop").addEventListener("click", function (){
        studentInfo();
        openPopup("StudentInfo");
    });
});