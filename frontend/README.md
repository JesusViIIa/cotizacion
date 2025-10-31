# Prueba tecnica Cotizacion Italika


## Base de datos


En este repositorio se encuentra el dump y el diagrama ER en archivo png

![erdiagram.](https://github.com/JesusViIIa/cotizacion/blob/main/cotizacion.png?raw=true")

    # Tablas:
    
    ```
    CREATE TABLE `moto` (
    `id` int NOT NULL AUTO_INCREMENT,
    `modelo` varchar(255) DEFAULT NULL,
    `precio` double DEFAULT NULL,
    `version` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
        ) 

        CREATE TABLE `accesorio` (
      `id` int NOT NULL AUTO_INCREMENT,
      `descripcion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
      `nombre` varchar(255) DEFAULT NULL,
      `precio` double DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) 

    CREATE TABLE `cliente` (
      `id` int NOT NULL AUTO_INCREMENT,
      `correo` varchar(255) DEFAULT NULL,
      `nombre` varchar(255) DEFAULT NULL,
      `telefono` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    )

     // Contraits generados por Java, por eso los caracteres raros
    CREATE TABLE `cotizacion` (
      `id` int NOT NULL AUTO_INCREMENT,
      `costo_accesorios` int DEFAULT NULL,
      `costo_total` int DEFAULT NULL,
      `fecha_cotizacion` datetime(6) DEFAULT NULL,
      `gastos_administrativos` int DEFAULT NULL,
      `mensualidad` double DEFAULT NULL,
      `monto_enganche` int DEFAULT NULL,
      `plazo` int DEFAULT NULL,
      `precio_coniva` int DEFAULT NULL,
      `precio_siniva` int DEFAULT NULL,
      `cliente_id` int DEFAULT NULL,
      `moto_id` int DEFAULT NULL,
      `seguro_vehiculo_id` int DEFAULT NULL,
      PRIMARY KEY (`id`),
      KEY `FKjm3xw2ma78h5q5lbrr2li9l9c` (`cliente_id`),
      KEY `FKfb1wcir95k28ophgu8asip3gt` (`moto_id`),
      KEY `FKyucygoffcnbww7sshu6t7ypu` (`seguro_vehiculo_id`),
      CONSTRAINT `FKfb1wcir95k28ophgu8asip3gt` FOREIGN KEY (`moto_id`) REFERENCES `moto` (`id`),
      CONSTRAINT `FKjm3xw2ma78h5q5lbrr2li9l9c` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
      CONSTRAINT `FKyucygoffcnbww7sshu6t7ypu` FOREIGN KEY (`seguro_vehiculo_id`) REFERENCES `seguro` (`id`)
    )

    CREATE TABLE `cotizacion_accesorios` (
      `cotizacion_id` int NOT NULL,
      `accesorios_id` int NOT NULL,
      PRIMARY KEY (`cotizacion_id`,`accesorios_id`),
      KEY `UKojse2dx1mlyarjhkr4yincw0l` (`accesorios_id`) USING BTREE,
      CONSTRAINT `FKiw7ya0hy18tug0c93f5bk9eo6` FOREIGN KEY (`cotizacion_id`) REFERENCES `cotizacion` (`id`),
      CONSTRAINT `FKjxv01v6qvk9x2qyr1uk941uat` FOREIGN KEY (`accesorios_id`) REFERENCES `accesorio` (`id`)
    ) 

    CREATE TABLE `seguro` (
      `id` int NOT NULL AUTO_INCREMENT,
      `descripcion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
      `nombre` varchar(255) DEFAULT NULL,
      `precio` double DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) 
    ```


## Frontend 
Preview del frontend, faltaron algunos detalles visuales como imagenes y estilos pero dado el tiempo me enfoqué en terminar funcionalidad
### Herramientas usadas
 * Angular 20.0
 * Angular Material
 * Tailwind
 * Signals (En lugar de BehaviorSubject)



![erdiagram.](https://github.com/JesusViIIa/cotizacion/blob/main/frontend1.png?raw=true")
![erdiagram.](https://github.com/JesusViIIa/cotizacion/blob/main/frontend2.png?raw=true")
![erdiagram.](https://github.com/JesusViIIa/cotizacion/blob/main/frontend4.png?raw=true")
![erdiagram.](https://github.com/JesusViIIa/cotizacion/blob/main/frontend3.png?raw=true")

## Backend


## Herrramientas usadas
 * Java 21
 * springboot 3.5
 * jpa - hibernate
 * lombok


 ### endpoints: 
 #### Crear Cotizacion
 ```

    curl --location 'localhost:8080/cotizacion' \
--header 'Content-Type: application/json' \
--data-raw '{
    "idmoto": 2, 
    "datos": {
        "nombre": "Jesus",
        "correo": "sdfsdf@exaample.com",
        "telefono": "2436346534766"
    },
    "enganche": 10000,
    "idaccesorios": [1,3],
    "idseguro": 1,
    "plazomeses": 24

}'
```


#### Catalogos de Moto, Accesorios, Seguros y Lsitado de cotizaciones
```
curl --location 'localhost:8080/motos'
curl --location 'localhost:8080/accesorios'
curl --location 'localhost:8080/seguros'
curl --location 'localhost:8080/cotizacion'