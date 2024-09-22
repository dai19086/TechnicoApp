package com.ed.webcompany.technico.models;

import com.ed.webcompany.technico.enumerations.PropertyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    @Column(unique = true, nullable = false)
    private String e9;

    private String address;

    private int yearOfConstruction;

    @Enumerated(EnumType.STRING)
    private PropertyType typeOfProperty;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ownerId")
    private PropertyOwner propertyOwner;

    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private List<PropertyRepair> repairs;

    @Override
    public String toString() {
        return "Property{" + " Ε9=" + e9 + ", Αddress=" + address + ", Υear Of Construction=" + yearOfConstruction + ", Τype Of Property=" + typeOfProperty + '}';
    }
}
