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
    @Column(name = "cod_organization")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "mail")
    private String mail;
    @Column(name = "num_cnpj")
    private String numCnpj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_profile")
    private ProfileEntity profile;

    @OneToMany(mappedBy = "organization")
    private List<AddressEntity> address;

    @ElementCollection
    @CollectionTable(name = "phones")
    private Set<String> phones;

}
