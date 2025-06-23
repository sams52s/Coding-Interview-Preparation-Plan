# 9. Machine Learning Integration in Spring Boot

#### Overview
Expose and serve machine learning models using Spring Boot, including best practices for model management.

#### Subtopics
- **Model Serving**
  - Expose ML models as REST APIs
  - Integrate with TensorFlow Serving or Deep Java Library (DJL)
- **Batch Inference**
- **Model Versioning**
- **Real-time vs Batch Predictions**

#### Example: Exposing a TensorFlow Model via REST
```java
@RestController
@RequestMapping("/ml")
public class MLController {

    @PostMapping("/predict")
    public PredictionResult predict(@RequestBody InputData input) {
        // Call TensorFlow Serving or local model inference here
        return predictionService.predict(input);
    }
}
```

#### External Links
- [DJL Documentation](https://docs.djl.ai/)
- [TensorFlow Serving](https://www.tensorflow.org/tfx/guide/serving)
- [Spring Boot ML Example](https://github.com/deepjavalibrary/djl-spring-boot-starter)

---