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
@Table(name = "tb_ongs")
public class OngsEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_ong")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "mail")
    private String mail;
    @Column(name = "num_cnpj")
    private String numCnpj;

    @ElementCollection
    @CollectionTable(name = "tb_phones_ongs")
    private Set<String> phones;



}
