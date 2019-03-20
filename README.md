# RUD DB
RUD is a GUI database tool that communicates directly with the vendor's database through the socket connection. This tool default
using an open source vendor MySQL.
But you can use this source code to other popular database management systems such as Oracle, DB2, PostgreSQL, DB/Apache Derby, Microsoft SQL Server, and Sybase.

![image1](/resc/sc3.png)

##### How can we use this code to another vendor.
This code using a protocol jdbc which defines how a client may access a database. So you need to get a jdbc Driver
of a vendor
* You can get these drivers easily but little bit search is need.
* MySQL Jdbc Driver <a href="https://raw.githubusercontent.com/ahmadHuss/RUDDB/master/ExternalLibs/mysql-connector-java-5.1.36-bin.jar" target="_blank">Click this</a>
* Oracle <a href="http://www.oracle.com/technetwork/database/features/jdbc/index-091264.html" target="_blank">Click this</a>
* Microsoft SQL Server <a href="https://msdn.microsoft.com/en-us/sqlserver/aa937724.aspx" target="_blank">Click this</a>
* PostgreSQL <a href="https://jdbc.postgresql.org/download.html" target="_blank">Click this</a><br/>
also many others like DB2, Sybase, Apache Derby
* Driver extension is .jar file
* Open your favorite IDE and import this project.
* After importing add a Driver as an External Library in your project.
* Now go to source code Line No 242 which is
```java
url = "jdbc:mysql://" + host + ":" + port + "/" + db;
/*
Above url called Database URL Format.

At our situation url, host, port & db are global variables which have String datatype we just changing these 
according to other vendor's URL Format.

Every vendor has its own URL Format.

For MySQL
jdbc:mysql://hostname:portNumber/datab

For Oracle
jdbc:oracle:thin:@hostname:portNumber:datab

For DB2
jdbc:db2:hostname:portNumber/datab

FOR PostgreSQL
jdbc:postgresql://hostname:portNumber/datab

For Java DB/Apache Derby (Embedded)
jdbc:derby:datab

For java DB/Apache Derby (Network)
jdbc:derby://hostname:portNumber/datab

For Microsoft SQL Server 
jdbc:sqlserver://hostname:portNumber;databaseName=datab

For Sybase
jdbc:sybase:Tds:hostname:portNumber/datab

But at the place of hostname you write host with concatenation operator.

portNumber you write port with concatenation operator.

at datab you write db with concatenation operator.

Now our URL Format is changed


*/
```
* After Modify source code It's time to Start your server
* Then Execute the Code
* It Runs

##### Screenshots and How to use it
* First Start your Database Server
* Open this tool this dialog is open.

![image1](/resc/sc1.png)

* Write Correct Credentials

![image3](/resc/sc0.jpg)

* Text Field No 3 which is _Default Database, filling in this text field is not necessary if you enter nothing then this
tool automatically fetch all databases that you have in your server.
* When it connects successfully this GUI will open.

![image2](/resc/sc2.png)

![image4](/resc/sc3.jpg)

* **ERROR ISSUE:** _When you update or delete any row if the error came_ "Cannot Delete or update a parent Row" then it's a foreign key
constraints error you can handle that error with manual query field.

##### Project
In this project there is .jar file of the fully MySQL support db which I generated for checking purposes. <a href="https://raw.githubusercontent.com/ahmadHuss/RUDDB/master/resc/RUD%20DB%20mySql.jar"
target="_blank">Download jar File</a> If you want to run that file you should install _Java RunTime Environement_ 
which is free. <a href="https://java.com/download" target="_blank">Downloaded it Here</a>

##### External Library used
* Swing X Prompt Library

## Version
v1.0
