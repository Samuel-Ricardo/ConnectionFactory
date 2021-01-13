# ConnectionFactory

Biblioteca que gera conexões com os mais diversos bancos de dados compatíveis com Java, para cada banco existe uma classe com teste unitários que garante a consistência e qualidade.

Para a primeira versão está prevista conexão com o [MySQL](https://www.mysql.com/)

Para a próximas versões estão previstos conexão com:

- [MongoDB](https://www.mongodb.com/),
- [PostgreSQL](https://www.postgresql.org/),
- [SQLite](https://www.sqlite.org/index.html),
- [SQLServer](https://www.microsoft.com/pt-br/sql-server/sql-server-downloads)

- __Documentação: [Javadoc](https://samuel-ricardo.github.io/Javadoc-ConnectionFactory.github.io/)__

## __*Versão Atual:*__
- __*Notas da Versão 1.0.0*__
  
# __*Informações Gerais:*__
- __*Autor:*__ [Samuel Ricardo Cabral de Barros](https://github.com/Samuel-Ricardo)

- __*Documentação:* [Javadoc](https://samuel-ricardo.github.io/Javadoc-ConnectionFactory.github.io/)__

- __*Criada:*  01/2021__ 

- __*Ultima Atualização:*  01/2021__ 

- __*Estado:*__ Em andamento

- __*Versão Atual:*__ 1.0.0


# Para Usar a Biblioteca 

## Projeto Maven:

1. Caso esteja trabalhando com um projeto Maven, procure o arquivo `pom.xml` do seu projeto Maven

2. Dentro deste arquivo, procure a tag: 

``` XML
<dependencies>
	
</dependencies>
``` 

3. E cole este trecho de código nela...

``` XML
        <dependency>
            <groupId>com.samuel.cabral</groupId>
            <artifactId>ConnectionFactory</artifactId>
            <version>1.0.0</version>
        </dependency>
``` 

2-1. Caso não tenha essa tag cole este trecho no final do código antes da tag   `< /project >`:


``` XML
    <dependencies>
       
        <dependency>
            <groupId>com.samuel.cabral</groupId>
            <artifactId>ConnectionFactory</artifactId>
            <version>1.0.0</version>
        </dependency>

    </dependencies>
``` 


4. Após ter feito isso, faça o build da sua aplicação e ele já estará pronto para ser usado com todas as dependências instaladas..

## Projeto sem Maven 

- Caso seu projeto não seja Maven e você prefira fazer o download...

1. baixe este arquivo: [ConnectionFactory-1.0.0.jar](https://github.com/Samuel-Ricardo/ConnectionFactory/blob/master/Lib/ConnectionFactory-1.0.0.jar?raw=true)


2. Após o download, adicione o arquivo baixado ao seu projeto.


__*OBS*: Lembre-se, ao escolher este método deverá fazer o download do JDBC e adcionar ao seu projeto, vou deixar uma lista com os JDBC dos bancos de dados compatíveis com esta versão e os links para o download.__


- MySQL - [MySQLConnector 8.0.22](https://dev.mysql.com/downloads/connector/j/?os=26)



# Como criar  Uma conexão: 
	
- MySQL:

1. importe a classe MySQLConnectionFactory:

```Java
import static com.MySQL.Connection.MySQLConnectionFactory.*;
```
2. agora chame o método `getConnection(...)`, o método se conectará com o banco de dados e retornará uma Connection.

3. Quando desejar fechar uma conexão chame o método `closeConnection(...)`


