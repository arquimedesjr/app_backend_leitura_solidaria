package br.com.backend.leitura_solidaria.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tb_organization")
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

    @ManyToOne
    @JoinColumn(name = "cod_profile")
    private ProfileEntity profile;

    @ElementCollection
    @CollectionTable(name = "tb_phones")
    private Set<String> phones;



}
