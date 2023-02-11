![example workflow](https://github.com/nicolastpi01/IndigoGraficaBack/actions/workflows/build.yml/badge.svg)
# Indigo
Este proyecto fue generado con Spring Boot v2.6.7 y utiliza Java 8.

## Para correr la aplicación para desarrollo:
* Clonar el proyecto.
* Abrir el proyecto con el IDE como Eclipse o Intellij.
* Hacer un `mvn clean install`.
* Por último, ir a _DemoApplication_ y ejecutar el método _main_.

## Para configurar la base de datos:
En el archivo _application.properties_, modificar los siguientes valores según la base de datos que se vaya a utilizar:
```
spring.datasource.url= jdbc:mysql://localhost:{'tuPuerto'}/{'unaDB'}?useSSL=false
spring.datasource.username= {'Username'}
spring.datasource.password= {'Password'}
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update
```
## CI con github actions:
Cuenta con integración continua sobre la rama main para revisar el estado del build y los test en cada subida.

Para configurar el pipeline, se crea en la carpeta raiz del proyecto el siguiente directorio: `.github/workflows`. <br>
A continuación, se crea un archivo llamado `build.yml` y en éste se especifican los pasos que realizará el pipeline.

La estructura del pipeline es la siguiente:

* Se indica cuando y sobre que rama se ejecutará el job
```
name: CI/CD Pipeline
on:
workflow_dispatch:
push:
    branches:
      - main
```
* Lo siguiente es configurar el workflow de github. En tu repositorio ve a la solapa Actions, clickea el link para crear tu propio workflow. Te redireccionara a una pagina con un editor de texto con algo de codigo auto-generado, vamos a modificar solo lo que necesitamos y conservar lo otro. En la sección de Jobs indicamos que corra sobre la ultima versión de Ubuntu:
```
jobs:
  #Test's job
  tests:
    name: Unit tests
    #Run on Ubuntu using the latest version
    runs-on: ubuntu-latest
```
* Por ultimo se indican los pasos que realizará el pipeline.
```
    #Job's steps
    steps:
      #Check-out your repository under $GITHUB_WORKSPACE, so your workflow can access it
      - uses: actions/checkout@v1
      #Set up JDK 8
      - name: Set up JDK
        uses: actions/setup-java@v1
        with:
          java-version: '8'
      #Set up Maven cache
      - name: Cache Maven packages
        #This action allows caching dependencies and build outputs to improve workflow execution time.
        uses: actions/cache@v1
        with:
          path: ~/.m2
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
          restore-keys: ${{ runner.os }}-m2
      #Run Tests
      - name: Run Tests
        run: mvn -B test
```
