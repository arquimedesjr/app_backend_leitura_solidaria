package br.com.backend.leitura_solidaria.models.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tbaddress")
public class AddressEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_addres")
    private Integer id;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    @Column(name = "complement")
    private String complement;
    @Column(name = "district")
    private String district;
    @Column(name = "cep")
    private String cep;
    @Column(name = "city")
    private String city;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;

}

