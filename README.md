# customer-api

#Author: DANIEL SOUZA DE OLIVEIRA
    #Email: danielsouza.ifsp@gmail.com 
    #Tech Stack: Java 11, Spring Boot, JPA, HIBERNATE, Docker, Swagger, H2 DATABASE
    #Tipo: customer-api

#instruções Gerais

Os comandos apresentados são necessários para compilar o projeto e preparar/executar a imagem Docker
em ambiente de desenvolvimento. 
Todos os comando devem ser executados via terminal diretamente no diretório raiz deste projeto.
A aplicação roda no Tomcat, por tanto a porta padrão é 8080
Se necessário trocar a porta, no arquivo de configurações application.properties use o comando  
"server.port = {porta_desejada}" para mudar a configuração.

## 1 - Realizado o build do projeto
A execução do build do projeto é necessária para gerar os binários da aplicação, bem 
como gerar os arquivos para que seja possível a construção da imagem docker:

        ./mvnw clean package -DskipTests
         
## 2 - Construir imagem docker

         docker build -t customer-api:v1 .
         
## 3 - Executar a imagem Docker

         docker run -p 8080:8080 customer-api:v1
         
Obs: as portas são customizaveis 

##4 - Depois de realizar as operações acima é possivel acessar a aplicação via browser nos seguintes PATH:

- Para acessar documentação com swagger
    http://localhost:8080/swagger-ui.html

- Para acessar interface do banco de dados H2 DATABASE
    http://localhost:8080/h2-console
        dados do banco: 
            url: jdbc:h2:mem:customerDB
            username: customerApi
            password: customerApi 

# Estrutura dos pacotes de fontes da aplicação

<pre>
    └── customerapi
        ├── CustomerApiApplication.java
        ├── appConfig
        │   ├── MessageConfig.java
        │   └── SwaggerConfig.java
        ├── controller
        │   ├── AppControllerAdvice.java
        │   ├── CustomerController.java
        │   ├── dto
        │   │   └── CustomerUpdateDto.java
        │   └── errors
        │       └── ApiErrors.java
        ├── exceptions
        │   ├── BusinessException.java
        │   └── CustomerNotFundException.java
        ├── model
        │   └── Customer.java
        ├── repository
        │   └── CustomerRepository.java
        └── service
            ├── CustomerService.java
            └── impl
                └── CustomerServiceImpl.java
</pre>