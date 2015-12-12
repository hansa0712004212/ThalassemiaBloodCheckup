CREATE CLASS UserRole
CREATE PROPERTY UserRole.role STRING

INSERT INTO UserRole SET role='Admin'
INSERT INTO UserRole SET role='Doctor'
INSERT INTO UserRole SET role='Lab Tester'

CREATE CLASS UserPermission
CREATE PROPERTY UserPermission.permission STRING

INSERT INTO UserPermission SET permission='Analyze'
INSERT INTO UserPermission SET permission='WriteDB'
INSERT INTO UserPermission SET permission='ReadDB'
INSERT INTO UserPermission SET permission='Summarize'

CREATE CLASS User
CREATE PROPERTY User.username STRING
CREATE PROPERTY User.password STRING
CREATE PROPERTY User.userRole LINK UserRole
CREATE PROPERTY User.firstName STRING
CREATE PROPERTY User.lastName STRING
CREATE PROPERTY User.nic STRING
CREATE PROPERTY User.email STRING
CREATE PROPERTY User.regNo STRING
CREATE PROPERTY User.mobile INTEGER
CREATE PROPERTY User.assignedDate DATE
CREATE PROPERTY User.leftDate DATE
CREATE PROPERTY User.permissions LINKSET UserPermission

INSERT INTO User SET username = 'hansa', password = '4212', firstName = 'Hansa', lastName = 'Amarasekara', email = 'hansa0712004212@gmail.com', mobile = '0712004212', assignedDate = '2013-01-20', userRole = (SELECT FROM UserRole WHERE role.toLowerCase() = 'admin')
INSERT INTO User SET username = 'anupama', password = '4212', firstName = 'Anupama', lastName = 'Wimalasooriya', email = 'yasanthianupama@gmail.com', mobile = '0713212637', assignedDate = '2013-11-05', userRole = (SELECT FROM UserRole WHERE role.toLowerCase() = 'doctor')

UPDATE User ADD permissions = (select from UserPermission)

CREATE CLASS TestType
CREATE PROPERTY TestType.testType STRING

INSERT INTO TestType SET testType='Blood Cell Image Analysis'
INSERT INTO TestType SET testType='Image Colour Comparison'
INSERT INTO TestType SET testType='Home'

CREATE CLASS Circle
CREATE PROPERTY Circle.xAxis INTEGER
CREATE PROPERTY Circle.yAxis INTEGER
CREATE PROPERTY Circle.radius INTEGER
CREATE PROPERTY Circle.perimeter DOUBLE
CREATE PROPERTY Circle.area DOUBLE

CREATE CLASS Test
CREATE PROPERTY Test.testType LINK TestType
CREATE PROPERTY Test.imagePath STRING
CREATE PROPERTY Test.testDate DATE
CREATE PROPERTY Test.circles EMBEDDEDSET Circle
CREATE PROPERTY Test.isInfected BOOLEAN

CREATE CLASS Patient

CREATE CLASS TestSuite
CREATE PROPERTY TestSuite.patient LINK Patient
CREATE PROPERTY TestSuite.test LINKLIST Test
CREATE PROPERTY TestSuite.performedBy LINK User

CREATE CLASS ContactPerson
CREATE PROPERTY ContactPerson.title STRING
CREATE PROPERTY ContactPerson.name STRING
CREATE PROPERTY ContactPerson.email STRING
CREATE PROPERTY ContactPerson.mobile INTEGER

CREATE PROPERTY Patient.nic STRING
CREATE PROPERTY Patient.title STRING
CREATE PROPERTY Patient.firstName STRING
CREATE PROPERTY Patient.middleName STRING
CREATE PROPERTY Patient.lastName STRING
CREATE PROPERTY Patient.birthDate DATE
CREATE PROPERTY Patient.sex STRING
CREATE PROPERTY Patient.address1 STRING
CREATE PROPERTY Patient.address2 STRING
CREATE PROPERTY Patient.city STRING
CREATE PROPERTY Patient.email STRING
CREATE PROPERTY Patient.mobile INTEGER
CREATE PROPERTY Patient.imagePath STRING
CREATE PROPERTY Patient.registeredDate DATE
CREATE PROPERTY Patient.contactPerson EMBEDDED ContactPerson
CREATE PROPERTY Patient.isActive BOOLEAN
CREATE PROPERTY Patient.testSuite LINKLIST TestSuite
CREATE PROPERTY Patient.password STRING

INSERT INTO Patient SET nic = '901563349V', title='Mr. ', firstName = 'Hansa', middleName = 'Madusanka', lastName = 'Amarasekara', birthDate='1990-06-04', sex = 'Male', address1 = 'No 24/F, Dewata Road', address2 = 'Kandegoda', city='Ambalangoda', email = 'hansa0712004212@gmail.com', mobile = '0712004212', registeredDate = '2015-05-05', isActive = true, password = '19900604', imagePath = 'me.jpg', contactPerson = {"type":"d", "name":"Thilak Ranjith", "mobile":"0915050586","title":"Mr. ","email":"thilakranjith@gmail.com"}
INSERT INTO Patient SET nic = '905813349V', title='Miss. ', firstName = 'Anupama', middleName = 'Yasanthi', lastName = 'Wimalasooriya', birthDate='1990-03-20', sex = 'Female', address1 = 'No 42 ', address2 = 'Seenigoda Road', city='Wathugedara', email = 'yasanthianupama@gmail.com', mobile = '0713212637', registeredDate = '2015-05-06', isActive = true, password = '19900320', imagePath = 'me.jpg', contactPerson = {"type":"d", "name":"Lalitha Pinnaduwa", "mobile":"0915782067","title":"Mrs. ","email":"lalitha@gmail.com"}
