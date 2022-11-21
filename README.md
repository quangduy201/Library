[<img alt="SGU LOGO" src="res/sgu_logo.png" title="Trường Đại học Sài Gòn" width="100"/>](https://www.facebook.com/TruongDaihocSaiGon.SGU)

# SGU Library Management
* Simple project used for library management in [SGU](https://www.facebook.com/TruongDaihocSaiGon.SGU) (Sai Gon University).
* This project is written in [Java](https://www.java.com).

___

## Description
* In this project, you are the manager of SGU Library.
* You can manage all the books, students, employees and bills.
* Only students and employees of SGU can borrow and return the books.

___

## About this project

### There are 16 classes:
* [Library](src/com/library/main/Library.java) ***Main***
* [File](src/com/library/management/File.java) ***\<\<Interface\>\>***
* [Management](src/com/library/management/Management.java) ***\<\<Interface\>\>***
  * [BookManagement](src/com/library/management/BookManagement.java)
  * [StudentManagement](src/com/library/management/StudentManagement.java)
  * [EmployeeManagement](src/com/library/management/EmployeeManagement.java)
  * [BorrowAndReturn](src/com/library/management/BorrowAndReturn.java)
* [Book](src/com/library/component/Book.java)
  * [EducationBook](src/com/library/component/EducationBook.java)
  * [ReferenceBook](src/com/library/component/ReferenceBook.java)
  * [Dictionary](src/com/library/component/Dictionary.java)
* [Person](src/com/library/component/Person.java) ***\<\<Abstract\>\>***
  * [Student](src/com/library/component/Student.java)
  * [Employee](src/com/library/component/Employee.java)
* [Bill](src/com/library/component/Bill.java)
* [Day](src/com/library/util/Day.java)

### Diagram of this project:
* [PDF file](diagram/Library_diagram.pdf)
* [PNG file](diagram/Library_diagram.png)
* [DRAWIO file](diagram/Library_diagram.drawio) ***(You must download [draw.io](https://github.com/jgraph/drawio-desktop/releases) to open DRAWIO file)***

___

## Contributors

| **ID**       | **Name**                             |
|--------------|--------------------------------------|
| _3121410116_ | _[Đinh Quang Duy   ]()_(team leader) |
| _3121410296_ | _[Nguyễn Hoàng Long]()_              |
| _3121410111_ | _[Nguyễn Tiến Dũng ]()_              |
| _3121410138_ | _[Nguyễn Zi Đan    ]()_              |

___

## Licence
Copyright © 2022 [SGU Library](https://github.com/quangduy201/Library)

___

_This file was created on November 21, 2022, v1.0_