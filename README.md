# Hundir la Flota - Juego de Batalla Naval

## Enlace al repositorio

```
https://github.com/MarcosAlonso05/UndirFlota
```

## Descripción del Proyecto
Este proyecto implementa el clásico juego de "Hundir la Flota" con:
- Sistema de tableros para 2 jugadores
- Tres tipos de barcos con características únicas
- Persistencia de datos usando JPA/Hibernate
- Interfaz de consola interactiva

## Características Principales
### Sistema de Juego
- Turnos alternados entre jugadores
- Tableros de ataque y defensa separados
- Detección automática de fin de juego
- Registro de impactos y barcos hundidos

### Tipos de Barcos
- **Acorazado (Battleship)**: 5 casillas, requiere todos los impactos
- **Fragata (Frigate)**: 3 casillas
- **Canoa (Canoe)**: 1 casilla

## Requisitos del Sistema
### Dependencias
- Java JDK 11+
- Spring Boot 2.7+
- H2 Database (incluida)
- Maven 3.6+

### Configuración
```properties
# application.properties
spring.datasource.url=jdbc:h2:mem:hundirlaflota
spring.h2.console.enabled=true

Hay una vesion funcional sin hash en la rama develop