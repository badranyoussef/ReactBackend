Application Programming Interface (API)  
Representational State Transfer (ReST)  
Client to (via http) Server  
Json (Key & Value)  
HTTP Request methods (GET & POST)  
HTTP Status Code (link: https://restfulapi.net/http-status-codes)
* (1xx = Informational)
* (2xx = Success)
* (3xx = Redirection)
* (4xx = Client error)
* (5xx = Server error)  

URI (Uniform Resource Identifiers)

REST API URI Naming Conventions and Best Practices
* /customers			// is a collection resource
* /customers/{id}		// is a singleton resource

Idempotent (An operation that will produce the same results if executed once or multiple times)  
Behavioral language (Given, when, then)  
Testing by Gherkin Language (Given, when, then)  
Vi bruger biblioteket RestAssured til test  
  
### Predicate:

Definition: A Predicate is a functional interface that represents a condition that can be evaluated to either true or false. It's commonly used to filter elements in a collection or stream based on a specified condition.  
Parameter: Takes an input element of the collection or stream.  
Returns: Returns a boolean value, indicating whether the input element satisfies the condition.  
Example:  
Predicate<Integer> isEven = (Integer x) -> x % 2 == 0;  
boolean result = isEven.test(5); // Checks if 5 is even  
result will be false

### Supplier:
Definition: A Supplier is a functional interface that represents a supplier of results. It does not take any input but produces a result when called. It's commonly used to lazily generate or provide values.  
Parameter: Takes no parameters.  
Returns: Returns a result of a specified type.  
Example:  
Supplier<String> helloSupplier = () -> "Hello, World!";  
String result = helloSupplier.get(); // Retrieves the supplied string  
result will be "Hello, World!"  

### Consumer:
Definition: A Consumer is a functional interface that represents an operation that accepts a single input argument and returns no result. It's commonly used to perform some action on each element of a collection or stream.  
Parameter: Takes an input element of the collection or stream.  
Returns: Does not return any value (void).  
Example:  
Consumer<String> printConsumer = (String message) -> System.out.println(message);  
printConsumer.accept("Hello, World!"); // Prints "Hello, World!" to the console  

### Function:
Definition: A Function is a functional interface that represents a function that accepts one argument and produces a result. It's commonly used to transform elements from one type to another.  
Parameter: Takes an input of a specified type.  
Returns: Returns a result of a different type.  
Example:  
Function<Integer, String> toStringFunction = (Integer x) -> "Number: " + x;  
String result = toStringFunction.apply(42); // Converts integer to string with a prefix  
result will be "Number: 42"  