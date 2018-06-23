if(!sessionStorage.getItem('JWT') && window.location.href.split("/").slice(-1)[0] == 'home.html'){
    window.location.href = 'index.html'
}

fetch("/api/student", {method: 'GET',  headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
    .then(response => response.json())
    .then(function (myJson) {
        if(myJson.tussenvoegsel == null){
            document.querySelector("#volledigeNaam").innerText = myJson.voornaam + " " + myJson.achternaam;
        }else {
            document.querySelector("#volledigeNaam").innerText = myJson.voornaam + " " + myJson.tussenvoegsel + " " + myJson.achternaam;
        }
    });

Date.prototype.yyyymm = function() {
    var mm = this.getMonth() + 1; // getMonth() is zero-based

    return [this.getFullYear(),
        (mm>9 ? '' : '0') + mm,
    ].join('-');
};

function openPopup(open) {
    let popups = document.querySelectorAll("div[id^=popup-]")

    for(popup of popups){
        popup.style.display = 'none';
    }
    document.querySelector("#popup-" + open).style.display = 'block';

}

function closePopup() {
    let popups = document.querySelectorAll("div[id^=popup-]");

    for (popup of popups) {
        popup.style.display = 'none'
    }
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

function uitloggen(){
    window.sessionStorage.removeItem('JWT');
    window.location.href = 'index.html'
}

function studentToevoegen() {
    document.querySelector("#studentToevoegenButton").addEventListener("click", function () {
        let formData = new FormData(document.querySelector("#studentToevoegen"));
        let encData = new URLSearchParams(formData.entries());
        if(document.querySelector("#emailInput").innerText.length <= 1 ||
            document.querySelector("#wachtwoordInput").innerText.length <= 1 ){

            alert("email en wachtwoord zijn verplicht");
            return;
        }
        fetch("/api/student", {method: 'POST', body: encData, headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
            .then(response => {
                if(response.status == 200){
                    document.querySelector("#studentToevoegen").reset();
                    closePopup();
                    alert("Student toegevoegd");
                } else{
                    alert("Gebruiker bestaat al");
                }
            })
    })
}
function studentInfo() {
    if(document.querySelector("#email").value.length <= 1 ||
        document.querySelector("#wachtwoord").value.length <= 1){
        alert("email en wachtwoord zijn verplicht");
        return;
    }
    fetch("/api/student", {method: 'GET',  headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response => response.json())
        .then(function (myJson) {
            document.querySelector("#voornaam").value = myJson.voornaam;
            if(myJson.tussenvoegsel == undefined){
                document.querySelector("#tussenvoegsel").value = "";
            }else{
                document.querySelector("#tussenvoegsel").value = myJson.tussenvoegsel;
            }
            document.querySelector("#achternaam").value = myJson.achternaam;
            document.querySelector("#email").value = myJson.email;
            // document.querySelector("#wachtwoord").value = myJson.wachtwoord;
    })
}

function studentWijzig() {
    if(document.querySelector("#email").value.length <= 1 ||
        document.querySelector("#wachtwoord").value.length <= 1 ){
        alert("email en wachtwoord zijn verplicht");
        return;
    }
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
                closePopup();
                alert("Student verwijderd");
                window.sessionStorage.removeItem('JWT');
                window.location.href = 'index.html';

            } else{
                alert("Student kan niet worden verwijderd");
            }
        })
}

function huisToevoegen() {
    if(document.querySelector("#naamInput").value.length <= 1 ||
        document.querySelector("#huisnummerInput").value.length <= 1 ||
        document.querySelector("#postcodeInput").value.length <= 1 ){
        alert("Naam, huisnummer en postcode zijn verplicht");
        return;
    }
    let formData = new FormData(document.querySelector("#huisToevoegen"));
    let encData = new URLSearchParams(formData.entries());

    fetch("/api/huis", {method: 'POST', body: encData, headers:{ 'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT") }})
        .then(response => {
            if(response.status == 200){
                document.querySelector("#huisToevoegen").reset()
                closePopup();
                alert("Huis is toegevoegd");
            }else {
                alert("Dit huis bestaat al");
            }
        })
}

function huisDelete() {
    let id = document.querySelector("#KiesHuisDelete").value

    fetch("/api/huis/" + id, {method: 'DELETE', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response =>{
            if(response.status == 200){
                closePopup();
                alert("Huis verwijderd");
                laadHuizenDelete();
                laadHuizen();
            } else{
                alert("Huis kan niet worden verwijderd");
            }
        })
}

function laadHuizen() {
    fetch("/api/huis", {method: 'GET', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response => response.json())
        .then(function (myJson) {
            let select = document.querySelectorAll(".kiesHuis");
            for(selectbox of select){
                selectbox.innerHTML = '';

                let defaultOpt = document.createElement('option');
                defaultOpt.value = -1;
                defaultOpt.text = ' -- selecteer een huis -- ';
                selectbox.add(defaultOpt);
                for(huis of myJson){
                    let option = document.createElement('option');
                    option.value = huis.id;
                    option.text = huis.naam;
                    selectbox.add(option)
                }
            }
            select = document.querySelectorAll(".kiesHuis");

            for(const selectbox of select){
                if(selectbox.id.startsWith("slaapplek-")){
                    let datum = document.querySelector('input#' + selectbox.id).value;
                    datum = datum.split('-')[2] + '-' + datum.split('-')[1] + '-' + datum.split('-')[0];
                    fetch("api/slaapplek/datum/" + datum , {method: 'GET', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
                        .then(async response => {
                            if (response.status == 200) {
                                let myJson = await response.json();
                                selectbox.value = myJson.huis.id
                            }
                        });
                    }
                }

        })
}

function laadHuizenDelete() {
    fetch("/api/huis", {method: 'GET', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response => response.json())
        .then(function (myJson) {
            let select = document.querySelector("#KiesHuisDelete");
            select.innerHTML = '';
            for(huis of myJson){
                select.innerHTML += '<option value="' + huis.id + '">' + huis.naam + '</option>'
            }
        })
}
function slaapplekkenHuis(event) {
    let id = event.target.value;
    let datum = document.querySelector("#datumSlaapplekken").value;
    if(datum.length <= 1){
        alert("Voert a.u.b. eerst een datum in");
    }
    datum = datum.split('-')[2] + '-' + datum.split('-')[1] + '-' + datum.split('-')[0];
    fetch("/api/slaapplek/huis/" + id + "/" +  datum, {method: 'GET', headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response => response.json())
        .then(function(myJson) {
            let table = document.querySelector("#huisTable tbody");
            table.innerHTML = "";
            if(myJson.length == 0){
                alert("Geen slaapplekken bekend")
            }
            for(slaapplek of myJson){
                let tv = '';
                if(slaapplek.student.tussenvoegsel && slaapplek.student.tussenvoegsel.length >= 1){
                    tv = slaapplek.student.tussenvoegsel;
                    console.log(tv)
                }

                table.innerHTML += '<tr><td>' + slaapplek.student.voornaam + '</td><td>' + tv + '</td><td>' + slaapplek.student.achternaam + '</td></tr>'
            }
        })
}

function slaapplekToevoegen(event) {
    let datum = document.querySelector('input#' + event.target.id).value;
    let vandaag = new Date();
    vandaag.setHours(0,0,0,0);
    if (new Date(datum) < vandaag){
        alert("U mag geen datum in het verleden aanpassen");
        return;
    } else {
        datum = datum.split('-')[2] + '-' + datum.split('-')[1] + '-' + datum.split('-')[0];
    }

    let formData = new FormData();
    formData.append('huis', event.target.value);
    formData.append('datum', datum);
    let encData = new URLSearchParams(formData.entries());
    fetch("api/slaapplek", {method: 'POST', body: encData, headers:{'Authorization': 'Bearer ' + window.sessionStorage.getItem("JWT")}})
        .then(response =>{
            if(response.status == 200){
                alert("Slaapplek is ingevoerd");
            }else {
                alert("Deze slaapplek kon niet worden opgeslagen");
            }
        } )
}

document.addEventListener("DOMContentLoaded", () => {
    let datumWeek = new Date();
    let monthYear = datumWeek.yyyymm();
    for (let i = 0; i < 8; i++) {
        let day = datumWeek.getDate() + i;
        day = (day>9 ? '' : '0') + day;

        let table = document.querySelector('#slaapplekkenTable tbody');
        table.innerHTML += '<tr>' +
            '                    <form id="slaapplek-' + i + '">' +
            '                        <th><input class="datum" type="date" id="slaapplek-' + i + '" name="datum" value="' + monthYear + '-' + day + '" disabled></th>' +
            '                        <th><select class="kiesHuis" id="slaapplek-' + i + '"></select></th>' +
            '                    </form>' +
            '                </tr>'
    }

    let selectSlaapplekken = document.querySelectorAll('select[id^=slaapplek-]');

    for (selectSlaapplek of selectSlaapplekken) {
        selectSlaapplek.addEventListener("change", slaapplekToevoegen)
    }

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
    document.querySelector("#deleteHuis").addEventListener("click", function () {
        laadHuizenDelete();
        openPopup("huisDelete");
    })

    document.querySelector("#huisSlaapplekken").addEventListener("change", slaapplekkenHuis)

});