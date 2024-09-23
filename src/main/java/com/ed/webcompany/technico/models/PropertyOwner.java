package com.ed.webcompany.technico.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

/**
 * The PropertyOwner class represents an owner of one or more properties. It
 * contains personal and contact information for the owner, as well as a list of
 * properties owned.
 *
 * @author matina
 */
public class PropertyOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    @Column(unique = true)
    private String vatNumber;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;

    /**
     * A list of properties owned by the PropertyOwner. This is a one-to-many
     * relationship, where each property is associated with one owner.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "propertyOwner", cascade = CascadeType.REMOVE)
    List<Property> properties;

}
