package br.com.backend.leitura_solidaria.models.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tb_users")
public class UsersEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_user")
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "mail", unique = true)
    private String mail;
    @Column(name = "url_img")
    private String urlImg;
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "cod_profile")
    private ProfileEntity profile;

    @ManyToOne
    @JoinColumn(name = "cod_ongs")
    private OngsEntity ongs;

    @ManyToOne
    @JoinColumn(name = "cod_partner")
    private PartnerEntity partner;

}
