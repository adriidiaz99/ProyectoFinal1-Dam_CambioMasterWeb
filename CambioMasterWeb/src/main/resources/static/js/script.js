var letras = ['T', 'R', 'W', 'A',
    'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T'];

function clickBoton() {
    var result = false;
    if(comprobarCorreo() && comprobarContrasena() && comprobarEdad()){
        result = true;
    } else {
    	alert("Hay un error en el registro. Por favor, vuelva a intentarlo.")
    }

    return result;

}

function nextVersion() {
	alert("Esta función no está disponible en este momento.")
}

window.onload = function () {
    document.getElementById("email").onblur = comprobarCorreo;
    document.getElementById("passw").onblur = comprobarContrasena;
    document.getElementById("passw1").onblur = comprobarContrasena;
    document.getElementById("fechaNacimiento").onblur = comprobarEdad;
}

function comprobarCorreo() {

    var correo = document.getElementById("email").value.trim();
    var campoSinCaracter = "";
    var comprob = false;

    if (correo !== campoSinCaracter) {

        correo = correo.split("");

        correo.forEach(b => {
            if (b == "@") {
                comprob = true;
                document.getElementById("email").style.border = "default";
                document.getElementById("email").style.borderStyle = "inset";
                document.getElementById("email").style.borderColor = "initial";
                document.getElementById("errorCorreo").classList.add("d-none");
            }
        });

        if (!comprob) {
            document.getElementById("email").style.borderWidth = "2px";
            document.getElementById("email").style.borderStyle = "solid";
            document.getElementById("email").style.borderColor = "red";
            document.getElementById("errorCorreo").classList.remove("d-none");

        }
    }
    
    console.log(comprob);

    return comprob;

}

function comprobarContrasena() {
    var passw = document.getElementById("passw");
    var passw1 = document.getElementById("passw1");
    var caracterSin = "";

    var comprob = false;
    var result = false;
    var pattern = "(?=.*[A-Z])(?=.*[0-9])";

    if (passw.value !== caracterSin) {
        if (passw1.value !== caracterSin) {
            if (passw1.value == passw.value) {
                if (passw.value.length >= 8) {
                    if (passw.value.match(pattern)) {

                        comprob = true;
                        result = true;
                        document.getElementById("passw").style.border = "default";
                        document.getElementById("passw").style.borderStyle = "inset";
                        document.getElementById("passw").style.borderColor = "initial";
                        document.getElementById("passFailed").classList.add("d-none");
                        document.getElementById("passNotValue").classList.add("d-none");
                        document.getElementById("passw1").style.border = "default";
                        document.getElementById("passw1").style.borderStyle = "inset";
                        document.getElementById("passw1").style.borderColor = "initial";
                    }

                }
            }
            else {
                if (passw.value.length >= 8) {
                    if (passw.value.match(pattern)) {
                        comprob = true;
                        document.getElementById("passw").style.border = "default";
                        document.getElementById("passw").style.borderStyle = "inset";
                        document.getElementById("passw").style.borderColor = "initial";
                        document.getElementById("passFailed").classList.add("d-none");
                        document.getElementById("passw1").style.borderWidth = "2px";
                        document.getElementById("passw1").style.borderStyle = "solid";
                        document.getElementById("passw1").style.borderColor = "red";
                        document.getElementById("passNotValue").classList.remove("d-none");
                    }
                }
            }
        }
        else {
            if (passw.value.length >= 8) {
                if (passw.value.match(pattern)) {
                    comprob = true;
                    document.getElementById("passw").style.border = "default";
                    document.getElementById("passw").style.borderStyle = "inset";
                    document.getElementById("passw").style.borderColor = "initial";
                    document.getElementById("passFailed").classList.add("d-none");
                }
            }
        }
    }

    if (!comprob) {
        document.getElementById("passw").style.borderWidth = "2px";
        document.getElementById("passw").style.borderStyle = "solid";
        document.getElementById("passw").style.borderColor = "red";
        document.getElementById("passFailed").classList.remove("d-none");
        if (passw1.value !== caracterSin) {
            document.getElementById("passw1").style.borderWidth = "2px";
            document.getElementById("passw1").style.borderStyle = "solid";
            document.getElementById("passw1").style.borderColor = "red";
            document.getElementById("passNotValue").classList.remove("d-none");
        }
    }
    
    console.log(result);
    
    return result;
}

function comprobarEdad() {
    var hoy = new Date(Date.now());
    var fecha = Date.parse(document.getElementById("fechaNacimiento").value);
    var comprob = false;

    if (years_between(fecha, hoy) >= 18) {
        comprob = true;
        document.getElementById("fechaNacimiento").style.borderStyle = "inset";
        document.getElementById("fechaNacimiento").style.borderColor = "initial";
        document.getElementById("errorFecha").classList.add("d-none");
    }

    if (!comprob) {
        document.getElementById("fechaNacimiento").style.borderWidth = "2px";
        document.getElementById("fechaNacimiento").style.borderStyle = "solid";
        document.getElementById("fechaNacimiento").style.borderColor = "red";
        document.getElementById("errorFecha").classList.remove("d-none");
    }
    
    console.log(comprob);

    return comprob;
}

function years_between(date1, date2) {

    // The number of milliseconds in one day
    const ONE_DAY = 1000 * 60 * 60 * 24 * 364;

    // Calculate the difference in milliseconds
    const differenceMs = Math.abs(date1 - date2);

    // Convert back to days and return
    return Math.round(differenceMs / ONE_DAY);

}
