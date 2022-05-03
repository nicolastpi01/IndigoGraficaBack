# IndigoGraficaBack

Hay que modificar el application.properties segun tu confuguración

spring.datasource.url= jdbc:mysql://localhost:{'tuPuerto'}/{'unaDB'}?useSSL=false
spring.datasource.username= {'Username'}
spring.datasource.password= {'Password'}
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto= update
