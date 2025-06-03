## Low-Level Design (LLD) - Master Interview Reference

This guide provides a deep-dive into LLD interview strategies, frequently asked system design questions, and comprehensive, step-by-step solutions with real-world scenarios and code samples.

---

### Q1: How should you approach an LLD interview problem?

**Answer:**
Approaching an LLD interview requires a structured methodology to demonstrate both problem understanding and design skills. The process typically involves:

1. **Clarifying requirements:** Discuss functional and non-functional requirements with the interviewer. Identify scope, constraints, and expected features.
2. **Identifying entities and responsibilities:** Break down the system into core objects or entities and assign responsibilities to each.
3. **Defining relationships:** Determine how these entities interact, including associations, dependencies, and inheritance.
4. **Drawing class diagrams:** Visualize the design using UML class diagrams to represent entities, attributes, methods, and relationships.
5. **Applying design patterns:** Identify opportunities to use design patterns (e.g., Singleton, Factory, Observer) to solve recurring design problems.
6. **Providing code skeletons:** Write class skeletons with key methods, interfaces, and relationships.

**Real-world scenario:**  
Suppose you are asked to design an online parking lot system. You would start by asking about the types of vehicles, parking slot types, payment requirements, and user roles (e.g., admin, customer). Then, you’d identify entities like Vehicle, ParkingSlot, Ticket, Payment, and User, and map their relationships.

**Java Example:**
```java
// Entity: Vehicle
public abstract class Vehicle {
    private String licensePlate;
    public Vehicle(String licensePlate) { this.licensePlate = licensePlate; }
    public String getLicensePlate() { return licensePlate; }
}
```

**Related Questions:**
- *Q: Why is it important to clarify requirements before starting the design?*  
  **A:** It helps avoid incorrect assumptions, ensures alignment with business goals, and prevents rework.
- *Q: What is the difference between high-level and low-level design?*  
  **A:** High-level design focuses on system architecture and modules, while low-level design dives into class diagrams, methods, and interactions.

---

### Q2: What are some common LLD problems asked in interviews?

**Answer:**
Some of the most frequently asked LLD problems include:
- Stack Overflow Design
- Parking Lot System
- Library Management System

These problems test your ability to model real-world systems, apply OOP principles, and use design patterns.

**Real-world scenario:**  
In a Stack Overflow design, you have to model users, questions, answers, comments, votes, and tags, ensuring relationships like a user posting multiple questions or voting on answers.

**Java Example - Class Diagram (UML):**
```
User
  - id: int
  - name: String
  + postQuestion(q: Question)

Question
  - id: int
  - title: String
  - content: String
  - author: User
  + addAnswer(a: Answer)
```

**Related Questions:**
- *Q: What patterns are often used in these problems?*  
  **A:** Singleton (for services), Factory (for object creation), Observer (for notifications), Strategy (for payment processing), etc.
- *Q: How do you handle scalability in LLD?*  
  **A:** By designing extensible classes, using interfaces/abstract classes, and separating concerns.

---

### Q3: Can you walk through the step-by-step LLD approach for a Parking Lot System?

**Answer:**
1. **Gather requirements:** Multiple parking slots, different vehicle types, ticketing, payment, admin functions.
2. **Identify entities:** Vehicle, ParkingSlot, ParkingLot, Ticket, Payment, User.
3. **Define relationships:** A ParkingLot has many ParkingSlots, each slot can have one Vehicle, Users generate Tickets.
4. **Draw class diagrams:** Map entities and relationships.
5. **Apply patterns:** Singleton for ParkingLot, Factory for Vehicle creation, Strategy for Payment.
6. **Code skeleton:**
```java
public class ParkingLot {
    private static ParkingLot instance;
    private List<ParkingSlot> slots;
    private ParkingLot() { slots = new ArrayList<>(); }
    public static ParkingLot getInstance() {
        if (instance == null) instance = new ParkingLot();
        return instance;
    }
    public Ticket parkVehicle(Vehicle v) { /*...*/ }
}
```

**Real-world scenario:**  
In a large mall, the parking lot system must handle thousands of vehicles, assign slots efficiently, and process payments via cash or card.

**Related Questions:**
- *Q: How would you handle concurrent parking requests?*  
  **A:** Use thread-safe data structures or synchronization to prevent double assignment of slots.
- *Q: How can you extend the design to support electric vehicle charging stations?*  
  **A:** Inherit from ParkingSlot and add charging capabilities, following the Open/Closed principle.

---

### Q4: What is the importance of class diagrams in LLD?

**Answer:**
Class diagrams visually represent the structure and relationships of entities, making it easier to understand and communicate the design. They help identify missing entities, circular dependencies, and opportunities for abstraction.

**Example:**  
In a Library Management System, a class diagram would show relationships between Book, Member, Librarian, and Transaction classes.

**Java Example:**
```java
public class Book {
    private String isbn;
    private String title;
    private Author author;
}
public class Member {
    private String memberId;
    private List<Book> borrowedBooks;
}
```

**Related Questions:**
- *Q: How do class diagrams help in identifying design patterns?*  
  **A:** They make recurring structures visible, such as inheritance (Template), composition (Decorator), or associations (Observer).

---

### Q5: How do you decide which design patterns to use in LLD?

**Answer:**
Analyze the problem for recurring challenges:
- **Singleton:** When only one instance is needed (e.g., ParkingLot manager).
- **Factory:** When object creation logic varies (e.g., Vehicle types).
- **Observer:** For event-driven updates (e.g., notifications).
- **Strategy:** For interchangeable algorithms (e.g., payment processing).

**Java Example:**
```java
// Strategy Pattern for Payment
public interface PaymentStrategy {
    void pay(double amount);
}
public class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) { /*...*/ }
}
public class CashPayment implements PaymentStrategy {
    public void pay(double amount) { /*...*/ }
}
```

**Related Questions:**
- *Q: What happens if you overuse patterns?*  
  **A:** The design becomes complex and harder to maintain. Apply patterns only when they solve a real problem.

---

### Q6: What are some references to improve LLD skills?

- [How to Answer a LLD Interview Problem](https://blog.algomaster.io/p/how-to-answer-a-lld-interview-problem)
- Practice on real-world systems and review open-source code for applied patterns and class design.

---

### Q7: Stack Overflow System Design Implementation

**Interview Question:** Design the core components of Stack Overflow with voting, answering, and reputation systems.

**Answer:** Let's implement a comprehensive solution with object-oriented design principles.

**Implementation Example:**
```java
// Core domain models
public class Question {
    private final long id;
    private final String title;
    private final String content;
    private final User author;
    private final List<Answer> answers = new ArrayList<>();
    private final List<Tag> tags = new ArrayList<>();
    private final VotingSystem votes = new VotingSystem();
    
    public void addAnswer(Answer answer) {
        answers.add(answer);
        NotificationService.notify(author, "New answer to your question");
    }
    
    public void addComment(Comment comment) {
        comments.add(comment);
        NotificationService.notify(author, "New comment on your question");
    }
}

public class VotingSystem {
    private final Map<User, Vote> votes = new ConcurrentHashMap<>();
    private final ReputationCalculator reputationCalculator;
    
    public synchronized void addVote(User voter, VoteType type) {
        if (!canUserVote(voter)) {
            throw new InsufficientReputationException();
        }
        
        Vote vote = votes.computeIfAbsent(voter, 
            k -> new Vote(voter, type));
        
        reputationCalculator.updateReputation(
            vote.getTarget().getAuthor(), 
            type
        );
    }
}

// Service layer with business logic
@Service
public class QuestionService {
    private final QuestionRepository questionRepo;
    private final TagService tagService;
    private final SearchEngine searchEngine;
    
    public Question askQuestion(QuestionRequest request) {
        validateQuestion(request);
        Question question = new Question(request);
        
        // Assign tags
        List<Tag> tags = tagService.processTags(request.getTags());
        question.setTags(tags);
        
        // Index for search
        searchEngine.index(question);
        
        return questionRepo.save(question);
    }
    
    public List<Question> search(SearchCriteria criteria) {
        return searchEngine.search(criteria)
            .stream()
            .map(this::enrichWithMetadata)
            .collect(Collectors.toList());
    }
}
```

**Follow-up Q1:** How would you handle concurrent voting?

**Implementation:**
```java
public class ConcurrentVotingSystem {
    private final ConcurrentHashMap<Long, AtomicInteger> voteCount;
    private final Lock voteLock = new ReentrantLock();
    
    public void vote(User voter, Votable item, VoteType type) {
        voteLock.lock();
        try {
            if (hasUserVoted(voter, item)) {
                throw new DuplicateVoteException();
            }
            
            updateVoteCount(item, type);
            recordVote(voter, item, type);
            
        } finally {
            voteLock.unlock();
        }
    }
}
```

**Follow-up Q2:** How would you implement the reputation system?

**Implementation:**
```java
public class ReputationSystem {
    private static final Map<ActionType, Integer> REPUTATION_RULES = Map.of(
        ActionType.QUESTION_UPVOTED, 5,
        ActionType.ANSWER_UPVOTED, 10,
        ActionType.ANSWER_ACCEPTED, 15
    );
    
    @Transactional
    public void updateReputation(User user, ActionType action) {
        int delta = REPUTATION_RULES.getOrDefault(action, 0);
        user.updateReputation(delta);
        
        // Check for new privileges
        privilegeService.checkAndGrantPrivileges(user);
        
        // Notify user
        if (delta > 0) {
            notificationService.notifyReputationChange(user, delta);
        }
    }
}
```

---

### Q8: Design a Real-time Collaboration Editor

**Interview Question:** How would you design a Google Docs-like collaborative editor?

**Answer:** Implement using Operational Transformation (OT) for concurrent edits.

**Implementation Example:**
```java
public class CollaborativeEditor {
    private final Document document;
    private final OperationQueue operations;
    private final List<EditorClient> clients = new CopyOnWriteArrayList<>();
    
    public void applyOperation(Operation op) {
        // Transform operation against concurrent ops
        Operation transformed = operations.transform(op);
        
        // Apply to document
        document.apply(transformed);
        
        // Broadcast to other clients
        broadcast(transformed, op.getClientId());
    }
    
    private class Operation {
        private final OperationType type;
        private final int position;
        private final String content;
        private final int clientId;
        
        public Operation transform(Operation other) {
            // Implementation of OT algorithm
            return transformed;
        }
    }
}
```

---

**Summary Table: LLD Interview Steps**

| Step | Description | Example |
|------|-------------|---------|
| 1    | Clarify requirements | Ask about features, constraints |
| 2    | Identify entities   | Vehicle, Slot, Ticket, Payment |
| 3    | Define relationships| ParkingLot has many Slots |
| 4    | Draw diagrams       | UML class diagrams |
| 5    | Apply patterns      | Singleton, Factory, Strategy |
| 6    | Provide code        | Java class skeletons |
