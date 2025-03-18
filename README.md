# Maven Serenity TestNG Pipeline

Este repositorio contiene un pipeline de integración continua configurado en GitHub Actions para construir y ejecutar pruebas automatizadas utilizando Maven, Serenity y TestNG. Además, genera reportes detallados con ExtentReports, que son almacenados como artefactos en GitHub Actions.

## Tabla de contenido

- [Descripción del Pipeline](#descripción-del-pipeline)
- [Características](#características)
- [Requisitos Previos](#requisitos-previos)
- [Cómo Configurar y Usar](#cómo-configurar-y-usar)
- [Estructura del Pipeline](#estructura-del-pipeline)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Descripción del Pipeline

Este pipeline se activa automáticamente con cada `push` a la rama `main` del repositorio. El flujo de trabajo incluye los siguientes pasos:
- Configuración del entorno de desarrollo (Java 8 con Maven).
- Construcción del proyecto, omitiendo las pruebas iniciales para optimizar tiempos.
- Ejecución de pruebas con TestNG integradas con Serenity.
- Generación de reportes detallados utilizando ExtentReports.
- Subida de los reportes generados como artefactos accesibles desde GitHub Actions.

## Características

- Automatización completa del flujo de pruebas y generación de reportes.
- Uso de Serenity para obtener informes legibles y detallados.
- Compatibilidad con TestNG para pruebas estructuradas.
- Almacenamiento de artefactos en GitHub Actions para un fácil acceso.

## Requisitos Previos

Antes de usar este pipeline, asegúrate de contar con:
- Un repositorio en GitHub configurado con GitHub Actions.
- Maven configurado y compatible con tu proyecto.
- Serenity y TestNG correctamente integrados en tu código.
- Java Development Kit (JDK) 8 instalado en tu entorno local si deseas realizar pruebas manuales.

## Cómo Configurar y Usar

1. **Clona este repositorio:**
   ```bash
   git clone https://github.com/baquerol/kata-automated-testing.git
