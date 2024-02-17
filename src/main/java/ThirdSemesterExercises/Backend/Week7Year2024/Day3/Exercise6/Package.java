package ThirdSemesterExercises.Backend.Week7Year2024.Day3.Exercise6;

// Exercise: GLS Package Tracking System - Part 1

/*
Scenario:
GLS (Global Logistics Services) wants to develop a package tracking system to manage the delivery of packages.
As part of the initial phase, they need to create a basic system using Java, JPA, and JPQL
to manage package information.
*/

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/*
Requirements:
Create an entity named "Package" using Lombok to manage package information.
The "Package" entity should have the following attributes:

ID (auto-generated primary key)
Tracking number (String)
Sender name (String)
Receiver name (String)
Delivery status (Enum: PENDING, IN_TRANSIT, DELIVERED)
*/
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tracking_number", nullable = false)
    private String trackingNumber;

    @Column(name = "sender_name", nullable = false)
    private String senderName;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", nullable = false)
    private Package.deliveryStatus deliveryStatus;

    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "date_updated", nullable = false)
    private LocalDate dateUpdated;

    public enum deliveryStatus {
        PENDING,
        IN_TRANSIT,
        DELIVERED
    }

    public Package(String trackingNumber, String senderName, String receiverName, Package.deliveryStatus deliveryStatus) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.deliveryStatus = deliveryStatus;
    }

    // Define pre-update and pre-persist life cycle methods in the "Package"
    // entity to update the last updated timestamp automatically.

    @PrePersist
    public void onCreate() {
        dateCreated = LocalDate.now().minusDays(1);
        dateUpdated = LocalDate.now().minusDays(1);
    }

    @PreUpdate
    public void onUpdate() {
        dateUpdated = LocalDate.now();
    }
}
