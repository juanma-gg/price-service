# Price Service

Este proyecto es un microservicio desarrollado en Java con Spring Boot que permite consultar los precios de un producto en función de la fecha, el identificador del producto y la marca. El servicio utiliza una base de datos embebida H2 y sigue una arquitectura de capas bien definida.
Estructura del proyecto

## Estructura

    src/
    ├── main/
    │   ├── java/com/ecommerce/price_service/
    │   │   ├── model/                               # Contiene el dominio de la aplicación
    │   │   │   ├── repository/                      # Repositorios para acceso a datos
    │   │   │   │   ├── entity/                      # Entidades JPA
    │   │   │   │   ├── h2/                          # Configuración de la base de datos H2
    │   │   │   │   ├── mappers/                     # Mapeadores entre entidades y DTOs
    │   │   │   │   ├── service/                     # Lógica de negocio
    │   │   │   │   │	├── filters/                 # Filtros y validaciones
    │   │   │   │   │	└── PricesService.java       # Servicio principal
    │   │   │   │   ├── VO/                          # Objetos de valor
    │   │   │   │   ├── PriceRepository.java         # Interfaz del repositorio
    │   │   │   │   └── PriceRepositoryImpl.java     # Implementación del repositorio
    │   │   │   ├── service/                         # Controladores y DTOs
    │   │   │   │   ├── controller/                  # Controlador REST principal
    │   │   │   │   ├── dto/                         # Objetos de transferencia de datos
    │   │   │   │   └── mappers/                     # Mapeadores de datos
    │   │   │   └── Constants.java                   # Clase de constantes
	│   │   └── PriceServiceApplication.java         # Clase que ejecuta la aplicación
    │   └── resources/
    │       ├── application.properties               # Configuración de la aplicación
    │       └── data.sql                             # Datos precargados en la base de datos
    └── test/                                        # Tests de la aplicación

## Tecnologías utilizadas

    Java 21
    Spring Boot 3
    Spring Data JPA
    BD H2 embebida
    Maven para la gestión del ciclo de vida del proyecto

## Endpoints
Consultar precios

    URL: /api/prices
    Método: GET
    Parámetros de consulta:
        appDate (String) – Fecha en formato ISO8601.
        productId (Long) – Identificador del producto.
        brandId (Long) – Identificador de la marca.

### Ejemplo de petición:

        curl "http://localhost:8080/api/prices?appDate=2020-06-14T10:00:00Z&productId=35455&brandId=1"

## Base de datos

El servicio utiliza una base de datos embebida H2 que se inicializa automáticamente con datos precargados mediante el script data.sql.
Con las credenciales por defecto de H2, puedes acceder a la consola de H2 mientras el servicio está corriendo en:

        http://localhost:8080/h2-console
