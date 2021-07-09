package br.com.backend.leitura_solidaria.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tb_partner")
public class PartnerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_partner")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "mail")
    private String mail;
    @Column(name = "num_cnpj")
    private String numCnpj;

    @ElementCollection
    @CollectionTable(name = "tb_phones_partner")
    private Set<String> phones;



}
