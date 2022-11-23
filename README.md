[<img alt="SGU LOGO" src="http://is.am/58sw" title="Trường Đại học Sài Gòn" width="100px"/>](https://www.facebook.com/TruongDaihocSaiGon.SGU)

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

| **ID**       | **Name**                                                           |
|--------------|--------------------------------------------------------------------|
| _3121410116_ | _[Đinh Quang Duy   ](https://github.com/quangduy201)_(team leader) |
| _3121410296_ | _[Nguyễn Hoàng Long](https://github.com/LongBOTT)_                 |
| _3121410111_ | _[Nguyễn Tiến Dũng ](https://github.com/Dungweb)_                  |
| _3121410138_ | _[Nguyễn Zi Đan    ](https://github.com/zidan63)_                  |

___

## License
MIT License

Copyright (c) 2022 [SGU Library](https://github.com/quangduy201/Library)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

___

_This file was created on November 23, 2022, v1.0_
