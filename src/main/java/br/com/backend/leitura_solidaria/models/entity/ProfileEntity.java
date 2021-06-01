package br.com.backend.leitura_solidaria.models.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tbprofile")
public class ProfileEntity extends BaseEntity {

    @Id
    @Column(name = "cod_profile")
    private Integer id;

    @Column(name = "type")
    private String type;



}
