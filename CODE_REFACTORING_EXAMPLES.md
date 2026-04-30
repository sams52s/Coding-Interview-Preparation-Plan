# Before/After Refactoring Examples

Navigation: [Main README](README.md) | [Concept-to-Code Mapping](CONCEPT_TO_CODE_MAPPING.md) | [Java & Spring Hub](Java%20%26%20Spring%20Interview%20Preparation/README.md) | [Testing Q&A](Question%20Bank/testing-quality.md)

Use these examples to explain how you improve code quality in interviews. Each example shows a common "bad code" smell and a cleaner Java/Spring alternative.

## Example 1: Large method with mixed responsibilities

### Before

```java
public Receipt checkout(Cart cart, User user) {
    if (cart == null || cart.getItems().isEmpty()) {
        throw new IllegalArgumentException("cart empty");
    }
    double total = 0;
    for (Item item : cart.getItems()) {
        if (item.getStock() <= 0) {
            throw new IllegalStateException("out of stock");
        }
        total += item.getPrice() * item.getQuantity();
    }
    if (user.isPremium()) {
        total = total * 0.9;
    }
    PaymentResponse response = paymentClient.charge(user.getCard(), total);
    if (!response.isSuccess()) {
        throw new RuntimeException("payment failed");
    }
    inventoryClient.reserve(cart.getItems());
    emailClient.send(user.getEmail(), "Order complete");
    return new Receipt(response.getId(), total);
}
```

### Problems

- Validation, pricing, payment, inventory, and notification are mixed.
- Hard to test one behavior at a time.
- Payment succeeds before inventory reservation, which can create compensation work.
- Generic exceptions hide failure type.

### After

```java
public Receipt checkout(CheckoutCommand command) {
    Cart cart = cartValidator.validate(command.cart());
    Money total = pricingService.price(cart, command.user());
    Reservation reservation = inventoryService.reserve(cart.items());

    try {
        Payment payment = paymentService.capture(command.paymentMethod(), total, command.idempotencyKey());
        notificationService.orderCompleted(command.user(), payment);
        return Receipt.from(payment, reservation, total);
    } catch (PaymentException ex) {
        inventoryService.release(reservation);
        throw ex;
    }
}
```

### Interview explanation

"I separated policies into services, made payment idempotent, introduced typed failures, and added compensation for reserved inventory if payment fails. This makes unit tests smaller and the workflow safer."

## Example 2: Controller doing business logic

### Before

```java
@PostMapping("/students")
public ResponseEntity<?> create(@RequestBody StudentRequest request) {
    if (request.name() == null || request.name().isBlank()) {
        return ResponseEntity.badRequest().body("name required");
    }
    Student student = new Student();
    student.setName(request.name());
    student.setEmail(request.email());
    student.setCreatedAt(LocalDateTime.now());
    repository.save(student);
    return ResponseEntity.ok(student);
}
```

### After

```java
@PostMapping("/students")
public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequest request) {
    StudentResponse response = studentService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
```

```java
@Service
public class StudentService {
    public StudentResponse create(StudentRequest request) {
        Student student = Student.create(request.name(), request.email(), clock.instant());
        Student saved = repository.save(student);
        return StudentResponse.from(saved);
    }
}
```

### Interview explanation

"The controller now handles HTTP concerns only. Validation uses framework annotations, business rules live in the service/domain layer, and time is injectable for tests."

## Example 3: Null-heavy code

### Before

```java
public String getDisplayName(User user) {
    if (user != null) {
        Profile profile = user.getProfile();
        if (profile != null) {
            String displayName = profile.getDisplayName();
            if (displayName != null && !displayName.isBlank()) {
                return displayName;
            }
        }
        if (user.getEmail() != null) {
            return user.getEmail();
        }
    }
    return "Unknown";
}
```

### After

```java
public String getDisplayName(User user) {
    if (user == null) {
        return "Unknown";
    }

    return Optional.ofNullable(user.getProfile())
            .map(Profile::getDisplayName)
            .filter(name -> !name.isBlank())
            .or(() -> Optional.ofNullable(user.getEmail()))
            .orElse("Unknown");
}
```

### Interview explanation

"The refactor makes the fallback order explicit. I would still avoid overusing `Optional` in hot paths or entity fields, but it is readable for this lookup."

## Example 4: Inheritance replaced with composition

### Before

```java
class DiscountedPremiumOrder extends PremiumOrder {
    @Override
    public Money calculateTotal() {
        return super.calculateTotal().minus(discount);
    }
}
```

### After

```java
public class OrderPricing {
    private final List<PricingRule> rules;

    public Money calculate(Order order) {
        Money total = order.baseTotal();
        for (PricingRule rule : rules) {
            total = rule.apply(order, total);
        }
        return total;
    }
}
```

### Interview explanation

"Composition avoids a growing inheritance tree. New pricing rules can be added without creating a new subclass for every combination."

## Refactoring checklist

- Name the smell: long method, duplication, primitive obsession, hidden side effect, tight coupling.
- Add tests before risky changes.
- Extract one responsibility at a time.
- Replace generic exceptions with domain errors.
- Preserve behavior first, then improve design.
- Measure performance if the refactor touches hot code.

