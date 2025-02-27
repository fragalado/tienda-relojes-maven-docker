document.addEventListener("DOMContentLoaded", function () {
    document.querySelector("form").addEventListener("submit", function (event) {
        if (!validarFormulario()) {
            event.preventDefault(); // Evita el envío si la validación falla
        }
    });
});

function validarFormulario() {
    var dni = document.getElementById("id").value.trim();
    var password = document.getElementById("password").value.trim();
    var dniError = document.getElementById("dni-error");
    var passwordError = document.getElementById("password-error");

    // Limpiar mensajes previos
    dniError.textContent = "";
    passwordError.textContent = "";

    let isValid = true;

    // Validar DNI (máx. 12 caracteres, solo letras y números)
    var dniRegex = /^[a-zA-Z0-9]{1,12}$/;
    if (!dniRegex.test(dni)) {
        dniError.textContent = "El ID debe tener hasta 12 caracteres y solo contener letras y números.";
        isValid = false;
    }

    // Validar Password (máx. 50 caracteres)
    if (password.length > 50) {
        passwordError.textContent = "La contraseña no puede tener más de 50 caracteres.";
        isValid = false;
    }

    return isValid;
}