# ConnectionFactory


[![Badge](https://img.shields.io/static/v1?label=Samuel&message=Ricardo&color=green&style=for-the-badge&logo=GITHUB)](https://github.com/Samuel-Ricardo)
[![Badge](https://img.shields.io/static/v1?label=Samuel&message=Ricardo&color=green&style=for-the-badge&logo=LinkedIn)](https://www.linkedin.com/in/samuel-ricardo-cabral/)
[![Badge](https://img.shields.io/static/v1?label=License&message=GNU%20v3.0&color=green&style=for-the-badge&logo=GITHUB)](https://github.com/Samuel-Ricardo/ConnectionFactory/blob/master/LICENSE)


[![Badge](https://img.shields.io/static/v1?label=Made%20With&message=Java&color=red&style=for-the-badge&logo=JAVA)](https://www.oracle.com/java/technologies/)
[![Badge](https://img.shields.io/static/v1?label=JDK&message=8&color=red&style=for-the-badge&logo=JAVA)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
[![Badge](https://img.shields.io/static/v1?label=Made%20With&message=Apache%20Maven&color=red&style=for-the-badge&logo=Apache-Maven)](https://maven.apache.org/)


[![Badge](https://img.shields.io/static/v1?label=Compatible%20With&message=MySQL&color=green&style=for-the-badge&logo=Apache)](https://www.mysql.com/)


![Badge](https://img.shields.io/static/v1?label=Version&message=1.0.0&color=green&style=for-the-badge&logo=GITHUB)
![Badge](https://img.shields.io/static/v1?label=State&message=in%20progress&color=green&style=for-the-badge&logo=GITHUB)
[![Badge](https://img.shields.io/static/v1?label=Documentation&message=Javadoc&color=red&style=for-the-badge&logo=JAVA)](https://samuel-ricardo.github.io/Javadoc-ConnectionFactory.github.io/)
#

Project whose main objective is to generate connections to databases compatible with Java, for each bank there is a class with unit tests that guarantees consistency and quality.

The first version includes a connection to the [MySQL](https://www.mysql.com/)

For the next versions are planned connections with:

- [MongoDB](https://www.mongodb.com/),
- [PostgreSQL](https://www.postgresql.org/),
- [SQLite](https://www.sqlite.org/index.html),
- [SQLServer](https://www.microsoft.com/pt-br/sql-server/sql-server-downloads)

- __Documentation: [Javadoc](https://samuel-ricardo.github.io/Javadoc-ConnectionFactory.github.io/)__
  
# __*General Information:*__

- __*Author:*__ [Samuel Ricardo Cabral de Barros](https://github.com/Samuel-Ricardo)

- __*Documentation:* [Javadoc](https://samuel-ricardo.github.io/Javadoc-ConnectionFactory.github.io/)__

- __*Created:*  01/2021__ 

- __*Last update:*  01/2021__ 

- __*State:*__ In Progress

- __*Current version:*__ 1.0.0

- __*License:*__ [GNU General Public License v3.0](https://github.com/Samuel-Ricardo/ConnectionFactory/blob/master/LICENSE)

# To Use the Library 
## Maven Project:

1. If you are working with a Maven project, look for the `pom.xml` file in your Maven project

2. Inside this file, look for the tag:

``` XML
<dependencies>
	
</dependencies>
``` 

3. And paste this code snippet into it...

``` XML
        <dependency>
            <groupId>com.samuel.cabral</groupId>
            <artifactId>ConnectionFactory</artifactId>
            <version>1.0.0</version>
        </dependency>
``` 

2-1. If you don't have this tag in your pom.xml, paste this snippet at the end of the code before the tag `< /project >`:


``` XML
    <dependencies>
       
        <dependency>
            <groupId>com.samuel.cabral</groupId>
            <artifactId>ConnectionFactory</artifactId>
            <version>1.0.0</version>
        </dependency>

    </dependencies>
``` 


4. After doing this, execute the `build` in your application and it will be ready to be used with all dependencies installed...


## Project without Maven 


- If your project is not Maven and you prefer to download it ...


1. download this file: [ConnectionFactory-1.0.0.jar](https://github.com/Samuel-Ricardo/ConnectionFactory/blob/master/Lib/ConnectionFactory-1.0.0.jar?raw=true)


2. After downloading, add the downloaded file to your project.


__*WARNING*: Remember, when choosing this method you should download the JDBC and add it to your project, I will leave a list with the JDBC of the databases compatible with this version and the download links:__


- MySQL - [MySQLConnector 8.0.22](https://dev.mysql.com/downloads/connector/j/?os=26)



# How to create a connection: 
	
- MySQL:


1. Import the MySQLConnectionFactory class:


```Java
import static com.MySQL.Connection.MySQLConnectionFactory.*;
```

2. now call the `getConnection (...)` method, the method will connect to the database and return a Connection


3. When you want to close a connection, call the `closeConnection (...)` method

