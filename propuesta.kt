// ----------------------------------------
// Programa: Registro de Huésped en Hotel
// ----------------------------------------

data class Huesped(
    val numeroDocumento: String,
    val nombresCompletos: String,
    val numeroTelefono: String,
    val correoElectronico: String
)

fun validarDocumento(documento: String): Boolean {    
    return documento.isNotBlank()
}

fun validarTelefono(telefono: String): Boolean {   
    return telefono.isNotBlank() && telefono.all { it.isDigit() } && telefono.length >= 7
}


fun validarCorreo(correo: String): Boolean {
    // Expresión regular básica para validar formato de correo
    val formatoCorreo = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return correo.isNotBlank() && formatoCorreo.matches(correo)
}

fun leerConValidacion(
    mensaje: String,
    mensajeError: String,
    validacion: (String) -> Boolean
): String {
    // Variable que almacenará el valor ingresado
    var valor: String

    // Ciclo que se repite hasta que el valor sea válido
    do {
        print(mensaje)                          // Mostrar el mensaje de solicitud
        valor = readLine()?.trim() ?: ""        // Leer línea; trim() elimina espacios; ?: "" evita null

        // Si la validación falla, mostrar el mensaje de error
        if (!validacion(valor)) {
            println(" Error: $mensajeError\n")
        }
    } while (!validacion(valor))                // Repetir si el valor no es válido

    return valor
}

fun main() {
    
    // Vista de bienvenida
   
    println("=".repeat(55))
    println(" SISTEMA DE REGISTRO DE HUÉSPEDES - HOTEL")
    println("=".repeat(55))
    println("  Por favor ingrese los datos del nuevo huésped:\n")

   
    // Solicitar y validar el número de documento
    
    val numeroDocumento = leerConValidacion(
        mensaje      = "  Número de documento : ",
        mensajeError = "El documento no puede estar vacío.",
        validacion   = ::validarDocumento       // Referencia a la función de validación
    )
    
    // Solicitar y validar los nombres completos
  
    val nombresCompletos = leerConValidacion(
        mensaje      = "  Nombres completos   : ",
        mensajeError = "El nombre no puede estar vacío.",
        validacion   = { it.isNotBlank() }      // Lambda en línea: solo verifica que no esté vacío
    )
   
    // Solicitar y validar el número de teléfono
   
    val numeroTelefono = leerConValidacion(
        mensaje      = "  Número de teléfono  : ",
        mensajeError = "El teléfono debe contener solo dígitos y tener mínimo 7 caracteres.",
        validacion   = ::validarTelefono
    )
    
    // Solicitar y validar el correo electrónico
   
    val correoElectronico = leerConValidacion(
        mensaje      = "  Correo electrónico  : ",
        mensajeError = "El correo debe tener un formato válido (ejemplo: usuario@dominio.com).",
        validacion   = ::validarCorreo
    )

    // Crear la instancia de la data class Huesped con los datos recopilados y validados
        
    val huesped = Huesped(
        numeroDocumento   = numeroDocumento,
        nombresCompletos  = nombresCompletos,
        numeroTelefono    = numeroTelefono,
        correoElectronico = correoElectronico
    )

    // 6. Mostrar  registro 
    println()
    println("=".repeat(55))
    println("         ✔ HUÉSPED REGISTRADO EXITOSAMENTE")
    println("=".repeat(55))
    println("  Número de documento : ${huesped.numeroDocumento}")
    println("  Nombres completos   : ${huesped.nombresCompletos}")
    println("  Número de teléfono  : ${huesped.numeroTelefono}")
    println("  Correo electrónico  : ${huesped.correoElectronico}")
    println("=".repeat(55))        
    println("  Representación interna del objeto (data class):")
    println("  $huesped")
    println()
}