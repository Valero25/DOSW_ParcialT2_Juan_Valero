# DOSW_ParcialT2_Juan_Valero_Carlos_Uribe

## Información del Proyecto
- **Autor:** Juan David Valero y Carlos Andres Uribe Vargas
- **Grupo:** [1]
- **Versión de Java:** 21
- **Framework:** Spring Boot 3.1.5
- **Base de Datos:** MySQL 8.0
## Caso de Estudio
La empresa DOSW ha sido seleccionada para desarrollar el MVP de la nueva
aplicación que saldrá al mercado estudiantil ECIXPRESS, una aplicación web
orientada a la gestión de pedidos en cafeterías institucionales mediante el uso de
códigos QR, permitiendo así que los usuarios ya no tengan que hacer filas.
El objetivo del MVP es permitir a los usuarios realizar pedidos de manera ágil,
escaneando productos y gestionando su compra desde el registro hasta la
entrega del pedido.
📌 Entidades principales
Usuario
● Identificador único
● Nombre completo
● Correo electrónico (institucional)
● Contraseña
● Rol (cliente / señora de la cafetería)
☕ Producto
● Nombre
● Descripción
● Precio
● Código QR - Identificador único
● Stock disponible
● Estado (disponible / no disponible)
🛒 Pedido
● Identificador único
● Usuario (cliente)
● Lista de productos
● Cantidades
● Estado
○ CREADO
○ EN_PREPARACION
○ ENTREGADO
○ CANCELADO
● Total del pedido
● Fecha de creación
⚙️ Funcionalidades
● Los usuarios pueden registrarse usando su correo institucional.
● Pueden autenticarse en la plataforma.
● Los productos pueden ser consultados mediante escaneo de código QR.
● Un usuario puede crear un pedido agregando productos escaneados.
● El sistema valida disponibilidad de stock antes de agregar productos.
● Un usuario solo puede tener un pedido activo.
● Los pedidos inician en estado CREADO.
● El administrador puede cambiar el estado del pedido a:
○ EN_PREPARACION
○ ENTREGADO
● El cliente puede cancelar el pedido solo en estado CREADO.
● El sistema debe actualizar el stock al confirmar el pedido.
● El sistema tiene que ser responsive y adicionalmente los colores que
maneja en su gama visual son naranjas, amarillos y blancos.
ACTIVIDADES A DESARROLLAR - PARTE TEÓRICA
En base al anterior enunciado, se espera que ustedes como equipo de desarrollo
puedan realizar las siguientes actividades para cumplir con el producto.
Para la parte teórica generen una rama por punto ,una vez esté
completa mezclen sobre develop y borre la rama - si no está sobre
develop no se calificara el entregable teórico.
1. Para cada una de las siete funcionalidades identificadas en el caso de
estudio menciona: (se le recomienda manejar los siguientes puntos
como una tabla de excel)
a. Que tipo de verbo HTTP Maneja
b. Establezca si es una funcionalidad idempotente o no
c. Cuál es la razón técnica de su decisión.
d. Cuáles Roles de los identificados tienen acceso a esa funcionalidad
e. Mencione sus datos de entrada y de salida (Establezca de qué tipo
es cada propiedad y si es obligatorio o no)
f. De un ejemplo de cómo se vería la entrada y la salida.
g. Establezca qué validaciones de input y el negocio debe tener en
cuenta.
h. Establezca los códigos HTTP y mensaje para Happy Path y Flujo de
Error
2. Explique la diferencia entre Validaciones de input y Validaciones de
negocio
3. Explique la diferencia entre autenticación, autorización e integridad.
4. Genere el diagrama de componentes general del sistema ECIXPRESS
5. ¿Qué problemas pueden surgir si no se separan correctamente las capas
dentro de un proyecto de software?
6. Genere el diagrama de componentes específicos del sistema ECIXPRESS
7. ¿Cuáles son las diferencias entre un validador, una utilidad y un servicio?
8. Genere el diagrama de clases de los modelos y responda: ¿Qué patrón de
software usaría para manejar los estados del pedido y por qué?
9. Genere el diagrama entidad-relación para el marco relacional de
persistencia.
10.Proponga 2 índices que mejoren el rendimiento de las consultas de
ECIXPRESS y establezca con un criterio técnico el porque dan valor a la
solución.
11.Como parte de la solución, es fundamental definir un conjunto robusto de
pruebas que garantice la calidad y correcto funcionamiento de las
funcionalidades expuestas en el sistema. Dado el enfoque de
transparencia con el cliente, se requiere evidenciar cómo se desarrollaría
la funcionalidad de “Solicitar pedido” siguiendo el enfoque de TDD (Test
Driven Development). En este contexto, se espera que usted:
● Describa cómo se aplican las fases de TDD (Red, Green,
Refactor) en la implementación de esta funcionalidad.
● Defina los casos de prueba iniciales antes de la implementación,
contemplando tanto escenarios exitosos como de error.
● Identifique las validaciones clave que deben ser cubiertas por las
pruebas.
12.Explique cómo las pruebas garantizan el cumplimiento de las reglas de
negocio y la integridad del sistema.
13.Nuestro cliente quiere automatizar el proceso del ciclo de vida de la
aplicación, sin embargo necesita entender cómo funciona, describa las
etapas principales de un pipeline y en qué consiste cada una.
14.¿Qué sucede si una prueba falla en el pipeline? ¿Debe permitirse el
despliegue? Justifique
15. Explique el concepto de logging en el manejo de errores:
a. ¿Qué información debería registrarse?
b. ¿Qué NO debería registrarse (por seguridad)?
16. Como parte del MVP, el cliente requiere una validación visual del producto.
Diseñe en Figma las pantallas necesarias para el flujo de:
● Registro de usuario
● Inicio de sesión
● Selección de productos y su detalle
● Creación del pedido

---

## Solucion

### 2. Diferencia entre Validaciones de Input y Validaciones de Negocio

#### **Validaciones de Input**

Las validaciones de input son aquellas que verifican el formato y estructura correcta de los datos que envía el cliente. Se enfoca en la integridad sintáctica de los datos, sin considerar las reglas del dominio del negocio.

**Características:**
- Se ejecutan en el primer punto de entrada de la solicitud
- Validan propiedades técnicas del dato: tipo, longitud, patrón, formato
- No requieren acceso a la lógica de negocio o base de datos
- Son independientes del contexto de negocio
- Generan error `400 Bad Request`

**Ejemplos en ECIXPRESS:**
```
- Email con formato válido (debe contener @)
- Contraseña con mínimo 8 caracteres
- Cantidad debe ser número positivo > 0
- Código QR debe contener solo alfanuméricos
- Precio debe ser decimal válido
- UUID debe tener formato correcto (36 caracteres con guiones)
```

**Implementación (Ejemplo en Spring Boot):**
```java
@PostMapping("/usuarios")
public ResponseEntity<?> registro(@Valid @RequestBody RegistroRequest request) {
    // @Valid ejecuta las validaciones de input
    // Si fallan, genera 400 Bad Request automáticamente
}

public class RegistroRequest {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "Nombre entre 3 y 100 caracteres")
    private String nombre;
    
    @Email(message = "El email debe ser válido")
    @Pattern(regexp = ".*@institucion\\.edu\\.co$", message = "Debe ser email institucional")
    private String email;
    
    @NotBlank
    @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres")
    private String password;
}
```

---

#### **Validaciones de Negocio**

Las validaciones de negocio son aquellas que verifican el cumplimiento de las reglas y políticas del dominio específico de la aplicación. Se enfoca en la integridad semántica y la consistencia del estado del sistema.

**Características:**
- Se ejecutan después de validar el input
- Acceden a la base de datos y lógica empresarial
- Consideran el contexto, estado actual y políticas del sistema
- Requieren conocimiento del dominio
- Generan errores `422 Unprocessable Entity` o `409 Conflict`

**Ejemplos en ECIXPRESS:**
```
- El email no está registrado (validación de unicidad)
- El usuario está activo (no suspendido)
- El usuario NO tiene un pedido activo
- El producto tiene stock disponible (cantidad > cantidadSolicitada)
- Solo clientes pueden crear pedidos (no cafetería)
- El pedido solo puede cancelarse en estado CREADO
- La transición de estado es válida (CREADO → EN_PREPARACION → ENTREGADO)
- El usuario propietario del pedido es quien solicita cancelarlo
- El stock no se puede actualizar si el pedido está CANCELADO
```

**Implementación (Ejemplo en Spring Boot):**
```java
@Service
public class PedidoService {
    
    public PedidoResponse crearPedido(String usuarioId, CrearPedidoRequest request) {
        // 1. Validación de input ya ocurrió en el controller
        
        // 2. Validaciones de negocio
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        
        // Validación de negocio: Usuario activo
        if (!usuario.isActivo()) {
            throw new BusinessException("El usuario está suspendido");
        }
        
        // Validación de negocio: Sin pedido activo
        if (usuario.tienePedidoActivo()) {
            throw new ConflictException("El usuario ya tiene un pedido activo");
        }
        
        // Validación de negocio: Stock disponible
        for (ItemPedidoRequest item : request.getItems()) {
            Producto producto = productoRepository.findById(item.getProductoId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
            
            if (producto.getStockDisponible() < item.getCantidad()) {
                throw new UnprocessableEntityException(
                    "Stock insuficiente para " + producto.getNombre()
                );
            }
        }
        
        // Si todas las validaciones pasan, crear el pedido
        return construirPedido(usuario, request);
    }
}
```

---

#### **Comparativa: Input vs Negocio**

**Validación de Input vs Validación de Negocio**

Las validaciones de input se enfocan en la sintaxis y formato de los datos, estructurando la información que llega del cliente, mientras que las validaciones de negocio se centran en la semántica y reglas del dominio, garantizando la consistencia del sistema.

En términos de ubicación, las validaciones de input se implementan a nivel de Controlador/DTOs, verificándose automáticamente sin acceso a la base de datos. Por el contrario, las validaciones de negocio residen en el Servicio/Repositorio y sí requieren acceso a la base de datos para consultar el estado actual del sistema.

Las herramientas también difieren: en validaciones de input se utilizan anotaciones como `@Valid`, `@Email`, `@Size`, etc., mientras que en validaciones de negocio se emplean queries y comparaciones lógicas personalizadas.

Desde el punto de vista de códigos HTTP, las validaciones de input generan 400 Bad Request cuando los datos no cumplen con el formato requerido, mientras que las validaciones de negocio generan 422 Unprocessable Entity o 409 Conflict cuando se viola una regla del dominio.

El contexto también es diferente: en validaciones de input, el cliente desconoce las reglas de negocio, solo valida estructura. En validaciones de negocio, se requiere conocimiento profundo del contexto empresarial (por ejemplo, si un usuario ya tiene un pedido activo).

Respecto a la velocidad, las validaciones de input son muy rápidas ya que no acceden a la base de datos, mientras que las validaciones de negocio son más lentas porque requieren consultas a la BD.

**Ejemplos prácticos:**
- **Input**: Validar que un email tiene formato válido (contiene @) vs **Negocio**: Validar que el email no está registrado
- **Input**: Validar que la cantidad es un número > 0 vs **Negocio**: Validar que hay cantidad disponible en stock

---

#### **Diagrama de Flujo de Validaciones**

```
SOLICITUD HTTP

[VALIDACION INPUT]  Formato, tipo, tamaño
    Falso: 400 Bad Request
    Verdadero
[VALIDACION DE NEGOCIO] Reglas del dominio, BD
    Falso: 409/422 Error
    Verdadero
[PROCESAR SOLICITUD] Crear/actualizar recursos
    201/200 OK
```

---

#### **Implementación de Ejemplo en ECIXPRESS**

**Endpoint: Crear Pedido**

```java
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    @PostMapping
    public ResponseEntity<PedidoResponse> crearPedido(
        @Valid @RequestBody CrearPedidoRequest request,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        // Validación Input: @Valid verifica formato de request
        // Si falla → 400 Bad Request
        
        // Validación Negocio: Servicio verifica reglas
        try {
            PedidoResponse response = pedidoService.crearPedido(
                userDetails.getUsername(), 
                request
            );
            return ResponseEntity.status(201).body(response);
        } catch (BusinessException e) {
            // Error de negocio específico
            return ResponseEntity.status(422).body(
                ErrorResponse.builder()
                    .codigo(422)
                    .mensaje(e.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build()
            );
        }
    }
}
```

**Request DTO con Validaciones de Input:**
```java
@Data
public class CrearPedidoRequest {
    
    @NotNull(message = "Items no puede ser nulo")
    @NotEmpty(message = "Debe incluir al menos un producto")
    private List<ItemPedidoRequest> items;
    
    @Size(max = 500, message = "Notas máximo 500 caracteres")
    private String notas;
}

@Data
public class ItemPedidoRequest {
    
    @NotBlank(message = "ID producto requerido")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
    private String productoId;
    
    @NotNull(message = "Cantidad requerida")
    @Min(value = 1, message = "Cantidad mínima es 1")
    @Max(value = 100, message = "Cantidad máxima es 100 por solicitud")
    private Integer cantidad;
}
```

---

#### **Impacto en la Calidad**

**Sin separación adecuada:**
- Lógica de negocio contaminada con validaciones técnicas
- Difícil de testear
- Inconsistencia en errores
- Datos corruptos en BD

**Con separación clara:**
- Responsabilidades bien definidas
- Código más mantenible
- Errores consistentes y documentados
- Fácil de testear cada nivel

---

## 3. Diferencia entre Autenticación, Autorización e Integridad

Estos tres conceptos son fundamentales en seguridad, pero cumplen funciones diferentes:

### **Autenticación: ¿Quién eres?**

La autenticación verifica quién eres tú. Es como presentar tu documento de identidad. El sistema te pide credenciales (usuario y contraseña) para confirmar que eres quien dices ser.

**En ECIXPRESS:**
- Usuario ingresa: email y contraseña
- Sistema verifica que la contraseña coincida
- Si es correcto, te da un token JWT (tu "pase" de entrada)
- No importa qué hagas, primero debes autenticarte

```java
@PostMapping("/auth/login")
public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    Usuario usuario = verificarCredenciales(request.getEmail(), request.getPassword());
    // Si pasa esta verificación, la autenticación es exitosa
    return ResponseEntity.ok(generarToken(usuario));
}
```

### **Autorización: ¿Qué puedes hacer?**

La autorización verifica qué tienes permiso de hacer. Es como tener un carnet que te permite entrar a ciertos lugares. Una vez autenticado, el sistema verifica si tu rol te permite realizar esa acción.

**En ECIXPRESS:**
-  Cliente autenticado → Puede crear pedidos
-  Cliente autenticado → NO puede cambiar estado de pedidos (solo cafetería)
-  Cafetería autenticada → Puede cambiar estado
-  Cafetería autenticada → NO puede crear pedidos

```java
@PostMapping("/pedidos") 
@PreAuthorize("hasRole('CLIENTE')")  // Solo clientes autorizados
public ResponseEntity<PedidoResponse> crearPedido(@RequestBody CrearPedidoRequest request) {
    // Si no eres cliente, obtienes 403 Forbidden
    return pedidoService.crear(request);
}

@PatchMapping("/pedidos/{id}/estado")
@PreAuthorize("hasRole('CAFETERIA') or hasRole('ADMIN')")  // Solo personal de cafetería
public ResponseEntity<PedidoResponse> cambiarEstado(@PathVariable String id, @RequestBody CambiarEstadoRequest request) {
    return pedidoService.cambiarEstado(id, request);
}
```

### **Integridad: ¿Fue modificado?**

La integridad verifica que los datos no hayan sido alterados*. Es asegurar que lo que envías sea exactamente lo mismo que recibe el servidor, sin cambios en el camino.

**Métodos comunes:**
- **Hash/Checksum**: Calcular un código único del mensaje. Si alguien lo modifica, el hash cambia
- **Firma Digital**: Firmar el mensaje con clave privada para probbar que vraiste de quien dices
- **HTTPS**: Cifra todo el tráfico para evitar que se modifique en tránsito

**En ECIXPRESS:**
- Todo se comunica por HTTPS (cifrado)
- El JWT tiene firma digital (no se puede modificar sin que se note)
- El servidor verifica el JWT: si fue modificado, es rechazado

---

## 5. Problemas de no Separar Correctamente las Capas

Si el código mezclado en un único archivo gigante es caótico.
**Las capas son:**
- Controlador: Recibe solicitudes del cliente
- Servicio: Aplica las reglas de negocio
- Repositorio: Accede a la base de datos
- Modelo: Representa los datos

Si se mezclan, empiezan los problemas.

**Dificultad de mantener el código:** Cambiar algo en la base de datos afecta todo. Un error pequeño se propaga por todas partes. Es como editar un laberinto mientras navegas en él.

**Imposible hacer pruebas:** Para probar si el servicio funciona bien, termina necesitando la base de datos, el controlador y el cliente todo junto. Imposible testear cosas de forma aislada. Gastarás horas configurando pruebas complicadas.

**Reutilización imposible:** Quieres usar la misma lógica de negocio en otro proyecto? Imposible, está toda mezclada con código específico de base de datos y controladores. Tenés que copiar y pegar código duplicado.

**Escalabilidad comprometida:** Cuando crece el proyecto, todo se vuelve más lento de arreglar. Agregar una simple funcionalidad toma el doble de tiempo porque todo está conectado como un nudo.

**Duplicación de código:** Sin separación clara, terminas escribiendo la misma validación en 5 lugares diferentes. Cambiar algo significa buscar y editar en 5 lugares.

**Seguridad comprometida:** Lógica de seguridad mezclada con lógica de negocio es peligroso. Fácilmente alguien olvida aplicar una validación en un lugar, dejando un agujero de seguridad.

**Difícil para nuevos desarrolladores:** Alguien nuevo en el equipo no sabe por dónde empezar. Todo está revuelto. Para entender una funcionalidad debe leer código en 10 archivos interconectados.

---

## 7. Diferencias entre Validador, Utilidad y Servicio

**Validador:** Es una clase especializada en verificar datos. Su único trabajo es responder "esto es válido o no". Por ejemplo, validar que un email tiene formato correcto, o que una cantidad es positiva. Recibe datos y retorna true o false, o lanza una excepción.

```java
@Component
public class PedidoValidator {
    public void validarItemsPedido(List<ItemPedidoRequest> items) {
        if (items == null || items.isEmpty()) {
            throw new ValidationException("El pedido debe tener al menos un producto");
        }
    }
}
```

**Utilidad:** Una clase con métodos auxiliares que reutilizas en muchos lugares. No accede a base de datos, solo hace cálculos o transformaciones simples. Por ejemplo, formatear fechas, calcular totales, convertir textos. Es más como una caja de herramientas.

```java
public class PedidoUtil {
    public static BigDecimal calcularTotal(List<ItemPedido> items) {
        return items.stream()
            .map(item -> item.getPrecio().multiply(new BigDecimal(item.getCantidad())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
```

**Servicio:** Es donde va la lógica de negocio. Coordina todo: valida, accede a la base de datos, calcula, y ejecuta las reglas del negocio. Un servicio usa validadores y utilidades, pero es responsable de orquestar todo el flujo.

```java
@Service
public class PedidoService {
    public PedidoResponse crearPedido(CrearPedidoRequest request) {
        // Valida
        pedidoValidator.validarItemsPedido(request.getItems());
        
        // Accede a datos
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId());
        
        // Usa utilidades
        BigDecimal total = PedidoUtil.calcularTotal(request.getItems());
        
        // Aplica lógica
        Pedido pedido = new Pedido(usuario, request.getItems(), total);
        return pedidoRepository.save(pedido);
    }
}
```



---

## 8. Diagrama de Clases y Patrón para Estados del Pedido

**Modelos principales de ECIXPRESS:**

Los modelos que necesitamos son:
- Usuario: Con su id, nombre, email, contraseña y rol
- Producto: Con id, nombre, descripción, precio, código QR y stock
- Pedido: Con id, usuario, lista de productos, cantidades, estado y total
- ItemPedido: Representa cada producto dentro de un pedido

La relación es: Un Usuario crea muchos Pedidos, y cada Pedido contiene muchos ItemPedidos.

**¿Qué patrón usar para los estados del pedido?**

El mejor patrón es State Pattern. Porque un pedido tiene 4 estados diferentes (CREADO, EN_PREPARACION, ENTREGADO, CANCELADO) y cada estado permite acciones distintas.

**Ventajas**
- Cada estado tiene su propia lógica en su propia clase
- Fácil de agregar nuevos estados sin tocar el código existente
- No hay gigantescos if-else anidados
- Las reglas de negocio están claras y organizadas
- Fácil de testear cada estado por separado

---

## 10. Dos Índices para Mejorar el Rendimiento de ECIXPRESS

**Índice 1: En la tabla Pedido por usuario y estado**

```sql
CREATE INDEX idx_pedido_usuario_estado ON pedido(usuario_id, estado);
```

¿Por qué funciona? Cuando buscas "dame todos los pedidos de un usuario" o "dame los pedidos en estado CREADO de este usuario", la base de datos busca en este índice en lugar de recorrer todas las filas de la tabla. Es como buscar por apellido en una guía telefónica ordenada: mucho más rápido que leer página por página.

En ECIXPRESS necesitamos esto constantemente: ver los pedidos de un usuario, verificar si tiene pedido activo, cambiar estado de pedidos específicos. Sin el índice, la base de datos debe leer toda la tabla.

**Índice 2: En la tabla Producto por código QR**

```sql
CREATE INDEX idx_producto_codigo_qr ON producto(codigo_qr);
```

¿Por qué funciona? Cada vez que un cliente escanea un código QR para buscar un producto, la base de datos necesita encontrar ese producto específico. Sin índice, busca en todas las filas. Con índice, lo encuentra directamente.

Esto es crítico en ECIXPRESS porque los clientes están escaneando constantemente QR mientras hacen compras. Si cada escaneo requiere leer toda la tabla, el sistema se vuelve lento. Con el índice, es instantáneo.

**Criterio técnico por el que generan valor:**

Ambos índices reducen el tiempo de búsqueda de O(n) a O(log n), donde n es el número de filas. Si tienes 100,000 pedidos, sin índice necesitas leer hasta 100,000 filas. Con índice, solo lees aproximadamente 17 filas. Eso es una diferencia enormemente en velocidad.

Además, estos índices alinean con los patrones de uso real de ECIXPRESS: búsquedas frecuentes por usuario/estado en pedidos e búsquedas por código QR en productos.

---

## ACTIVIDADES A DESARROLLAR - PARTE PRÁCTICA:
Por cada funcionalidad que van a realizar generen una rama feature y
una vez esté completa mezcle sobre develop y borre su rama - si no está
sobre develop no se calificara el entregable práctico.
1. Implemente a nivel de código las funcionalidades relacionadas con el flujo
de Registro de usuarios y Creación del pedido, Recuerde que tiene que
estar alineado con:
a. Las definiciones que menciono de Request y Response (Códigos de
error)
b. Validaciones de input y negocio.
c. Componentes diagramados en los diagramas de componentes
específicos, de clases y entidad-relación.
2. Implemente la documentación Swagger de su API
3. Genere las pruebas unitarias correspondientes para las funcionalidades
presentadas - Agregue a su README el análisis de cobertura con jacoco y
el análisis estático.
4. Realice pruebas funcionales de su API mostrando que está utilizando
swagger o postman junto a los logs que generó en la aplicación por cada
operación probada y sus escenarios.
5. Implementa Seguridad para el tema de autenticación y manejo de
permisos por roles para las operaciones que desarrollaste.
BONO:
A. Genere con Github Actions un pipeline que permite automatizar el ciclo de
su aplicación: build, test and deploy, este pipeline se debe ejecutar cada
vez que realice una mezcla de feature a develop.
B. Genere el despliegue de su aplicación en Azure DevOps y agregue en el
README el link del despliegue.

