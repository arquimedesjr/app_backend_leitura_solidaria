package br.com.backend.leitura_solidaria.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tborganization")
public class OrganizationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String mail;
    private String numCnpj;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @OneToMany(mappedBy = "organization")
    private List<AddressEntity> addressEntities;

    @ElementCollection
    @CollectionTable(name = "PHONE")
    private Set<String> phones;

    @OneToMany(mappedBy = "organization")
    private List<UsersEntity> users;
}
