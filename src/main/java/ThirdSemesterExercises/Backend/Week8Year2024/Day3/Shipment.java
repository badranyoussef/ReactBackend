package ThirdSemesterExercises.Backend.Week8Year2024.Day3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

    /*
    Create a new entity named "Shipment" to represent the movement of packages between locations.
    The "Shipment" entity should have the following attributes:

    ID (auto-generated primary key)
    Package (ManyToOne relationship with the Package entity)
    Source location (ManyToOne relationship with the Location entity)
    Destination location (ManyToOne relationship with the Location entity)
    Shipment date/time (Date/Time attribute)
    */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package shipmentPackage;

    @ManyToOne
    @JoinColumn(name = "source_location_id")
    private Location sourceLocation;

    @ManyToOne
    @JoinColumn(name = "destination_location_id")
    private Location destinationLocation;

    // Modify the "Package" entity to include a OneToMany relationship with the "Shipment" entity.
    @Column(name = "shipment_datetime")
    private LocalDate shipmentDateTime;

    public Shipment(Package shipmentPackage, Location sourceLocation, Location destinationLocation, LocalDate shipmentDateTime) {
        this.shipmentPackage = shipmentPackage;
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
        this.shipmentDateTime = shipmentDateTime;
    }
}