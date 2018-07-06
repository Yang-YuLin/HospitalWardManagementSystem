CREATE TABLE Department
	(Deptname CHAR(20) PRIMARY KEY,
	Deptaddress CHAR(20) NOT NULL,
	Depttel CHAR(11) NOT NULL
	);

CREATE TABLE Doctor
	(Dno CHAR(10) PRIMARY KEY,
	Dpassword VARCHAR(15) NOT NULL,
	Dname CHAR(20) NOT NULL,
	Dsex CHAR(2) NOT NULL,
	Dtitle CHAR(10) NOT NULL,
	Dage SMALLINT NOT NULL,
	Dtel CHAR(11) NOT NULL,
	Deptname CHAR(20) NOT NULL,
	FOREIGN KEY(Deptname) REFERENCES Department(Deptname)
	);

CREATE TABLE Ward
	(Wno CHAR(9) PRIMARY KEY,
	Deptname CHAR(20) NOT NULL,
	Wcharge INT NOT NULL,
	FOREIGN KEY(Deptname) REFERENCES Department(Deptname)
	);

CREATE TABLE Bed
	(Wno CHAR(9),
	Bno CHAR(9),
	Bstate CHAR(20) ,
	PRIMARY KEY(Wno,Bno),
	FOREIGN KEY(Wno) REFERENCES Ward(Wno)
	);

CREATE TABLE Patient
	(Pno CHAR(10) PRIMARY KEY,
	Pname CHAR(20) NOT NULL,
	Psex CHAR(2) NOT NULL,
	Pdiagnose CHAR(20) NOT NULL,
	Wno CHAR(9) NOT NULL,
	Bno CHAR(9) NOT NULL,
	Dno CHAR(10) NOT NULL,
	Ptel CHAR(11) NOT NULL,
	Pindate DATE NOT NULL,
	Poutdate DATE ,
	FOREIGN KEY(Wno,Bno) REFERENCES Bed(Wno,Bno),
	FOREIGN KEY(Dno) REFERENCES Doctor(Dno)
	);



