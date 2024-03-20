# Javalins RouteRole:
Link: https://javalin.io/tutorials/auth-example

### Hvad er en rolle?  
En rolle er med til at definere, hvad vi som brugere har adgang til.

### Hvorfor bruge roller?
Man vil ikke have at ens almindelige brugere har adgang til alle ruter (en HTTP-sti). 
Med en rolle kan disse to adskilles. Således at forskellige roller har forskellige adgangsrettigheder.

### Hvordan bruger vi roller?
Man starter med at importere javalins route role således:  
import io.javalin.security.RouteRole;

Herefter har vi adgang til at definere vores roller således:  
enum Role implements RouteRole {ANYONE, USER, ADMIN}.

Nu kan vi gør brug af vores roller således:  

get("/login", UserController.login, Role.ANYONE);

get("/update", UserController.updateAccount, Role.USER);

get("/delete/{id}", UserController.deleteAccount, Role.ADMIN);

### Skrevet af

Ahmad Alkaseb