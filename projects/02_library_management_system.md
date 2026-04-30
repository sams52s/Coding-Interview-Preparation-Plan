# Project 02: Library Management System

Navigation: [Projects](README.md) | [Stage 2](../README.md#learning-roadmap) | [JAVA Hub](../Java%20%26%20Spring%20Interview%20Preparation/JAVA/README.md) | [DSA](../Java%20%26%20Spring%20Interview%20Preparation/DSA/README.md)

## Goal

Build a Java console application that manages books, members, borrowing, returns, and simple persistence using collections, generics, exceptions, and IO/NIO.

## Concepts practiced

- `List`, `Map`, `Set`, `Queue`, and sorting.
- Generics and type-safe repositories.
- Custom exceptions.
- File IO/NIO for save/load.
- Lambda expressions and streams for filtering/reporting.

## Functional requirements

- Add, update, remove, and search books.
- Register members.
- Borrow a book if available.
- Return a borrowed book.
- Track overdue books using due dates.
- Export inventory and borrowing history to a file.

## Suggested model

| Entity | Fields |
|--------|--------|
| `Book` | isbn, title, author, category, totalCopies, availableCopies |
| `Member` | memberId, name, email, active |
| `Loan` | loanId, isbn, memberId, borrowedAt, dueAt, returnedAt |

## Data structures

- `Map<String, Book>` for ISBN lookup.
- `Map<String, Member>` for member lookup.
- `List<Loan>` for loan history.
- `Queue<Reservation>` optional for waitlists.
- `Set<String>` to detect duplicate emails or ISBNs.

## Custom exceptions

- `BookNotFoundException`
- `MemberNotFoundException`
- `BookUnavailableException`
- `DuplicateRecordException`
- `InvalidInputException`

## Reports to implement

- Top borrowed books.
- Overdue loans.
- Active members.
- Inventory by category.

## Stretch goals

- Reservation waitlist.
- CSV import/export.
- JSON persistence.
- Command pattern for menu actions.
- Unit tests for service methods.

## Interview talking points

- Why `Map` is better than scanning a list for lookup-heavy flows.
- How generics improve repository type safety.
- Why custom exceptions make business errors clearer.
- How stream operations improve reporting without mutating state.
