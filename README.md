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

Las pruebas ayudan a asegurar que las reglas del negocio se cumplan y que el sistema funcione correctamente de forma confiable
Primero permiten verificar que las reglas esten bien implementadas en el codigo es decir cada regla importante se convierte en una prueba Por ejemplo si en ECIXPRESS un usuario solo puede tener un pedido activo se puede hacer una prueba que intente crear un segundo pedido y comprobar que el sistema lo rechaza Si alguien cambia el codigo y rompe esa regla la prueba falla de inmediato
Tambien ayudan a evitar errores cuando el sistema crece Cuando se agrega una nueva funcionalidad las pruebas anteriores aseguran que lo que ya funcionaba siga igual Por ejemplo si al implementar confirmar pedido se rompe sin querer la validacion de stock las pruebas lo detectan antes de que llegue a produccion
Otro punto importante es que validan los cambios de estado dentro del sistema En un pedido por ejemplo no todas las transiciones son validas como pasar de entregado a cancelado Las pruebas se encargan de comprobar que el sistema respete esas reglas
Por ultimo las pruebas de integracion ayudan a asegurar que los datos se mantengan consistentes Por ejemplo que al confirmar un pedido realmente se descuente el stock en la base de datos y no quede nada a medias si ocurre un error
En pocas palabras las reglas de negocio sin pruebas dependen de que alguien las recuerde Con pruebas quedan automatizadas y se verifican cada vez que el sistema cambia


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

