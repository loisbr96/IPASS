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

/*function huisEnDatum(){
    fetch("/api/huis/{id}/{datum", {method: 'GET', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response => response.json())
        .then(function (myJson){

        })

}*/


document.addEventListener("DOMContentLoaded", () => {
    laadHuizen();
    document.querySelector("#addHuis").addEventListener("click", function () {
        document.querySelector("#huisToevoegenDiv").style.display = "block";
    })
    document.querySelector("#nieuweStudent").addEventListener("click", function () {
        document.querySelector("#studentToevoegenDiv").style.display = "block";
    })
});