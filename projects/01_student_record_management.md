# Project 01: Student Record Management

Navigation: [Projects](README.md) | [Stage 1](../README.md#learning-roadmap) | [Java Fundamentals](../Java%20%26%20Spring%20Interview%20Preparation/JAVA/Java%20Fundamentals/README.md) | [OOP](../Java%20%26%20Spring%20Interview%20Preparation/JAVA/OOP/README.md)

## Goal

Build a console-based Java application that manages student records using classes, arrays, methods, and basic OOP.

## Concepts practiced

- Java syntax and control flow.
- Classes, objects, constructors, encapsulation.
- Arrays and string handling.
- Basic validation and error handling.
- Simple menu-driven program structure.

## Functional requirements

- Add a student with id, name, email, department, and GPA.
- List all students.
- Search by id or email.
- Update student name, department, or GPA.
- Delete a student by id.
- Show summary statistics: total students, average GPA, highest GPA.

## Suggested classes

| Class | Responsibility |
|-------|----------------|
| `Student` | Holds student data and validation rules. |
| `StudentRepository` | Stores students in an array and performs CRUD operations. |
| `StudentService` | Contains business rules and duplicate checks. |
| `StudentApp` | Console menu and user input flow. |

## Edge cases

- Duplicate id or email.
- Empty name or invalid email.
- GPA outside allowed range.
- Search/update/delete for missing student.
- Full array when using fixed-size storage.

## Stretch goals

- Replace arrays with `ArrayList` after completing the basic version.
- Add sorting by GPA or name.
- Add file save/load after Week 2 IO/NIO study.

## Interview talking points

- Why you separated model, repository, service, and UI.
- How you validated data.
- How you handled duplicate records.
- When arrays become limiting and collections are better.

## Done checklist

- [ ] Add/list/search/update/delete works.
- [ ] Duplicate and invalid input is handled.
- [ ] Code is split into small classes.
- [ ] Manual test cases are documented.
- [ ] You can explain the design in under two minutes.
