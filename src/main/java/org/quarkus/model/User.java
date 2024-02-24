package org.quarkus.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usrs", schema = "fundamentals")
@org.hibernate.annotations.NamedNativeQuery(
        name = "findUserByUserUsernameOrUserEmail",
        query = "SELECT a.* FROM fundamentals.usrs a WHERE (a.usrs_email = ?1 OR a.usrs_usrnme = ?1) AND a.usrs_sttus = ?2",
        resultClass = User.class
)
@org.hibernate.annotations.NamedNativeQuery(
        name = "findUserByUserUsername",
        query = "SELECT a.* FROM fundamentals.usrs a WHERE a.usrs_usrnme = ?1 AND a.usrs_sttus = ?2",
        resultClass = User.class
)
@org.hibernate.annotations.NamedNativeQuery(
        name = "findUserByUserEmail",
        query = "SELECT a.* FROM fundamentals.usrs a WHERE a.usrs_email = ?1 AND a.usrs_sttus = ?2",
        resultClass = User.class
)
@org.hibernate.annotations.NamedNativeQuery(
        name = "findUserByUserUuid",
        query = "SELECT a.* FROM fundamentals.usrs a WHERE a.usrs_uuid = ?1 AND a.usrs_sttus = ?2",
        resultClass = User.class
)
public class User extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial", name = "usrs_id")
    private Integer userId;

    @Column(name = "usrs_uuid", length = 100, columnDefinition = "varchar(100) DEFAULT gen_random_uuid()")
    private String userUuid;

    @Column(name = "usrs_email", length = 100)
    private String userEmail;

    @Column(name = "usrs_usrnme", length = 64)
    private String userUsername;

    @Column(name = "usrs_sttus", columnDefinition = "int4 DEFAULT 0")
    private Integer userStatus;

    @Column(name = "usrs_pass", length = 100)
    private String userPass;

    @Column(name = "created_by", columnDefinition = "int4")
    private Integer createdBy;

    @Column(name = "created_date", columnDefinition = "timestamptz DEFAULT NOW()")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Jakarta")
    private Date createdDate;

    @Column(name = "updated_by", columnDefinition = "int4")
    private Integer updatedBy;

    @Column(name = "updated_date", columnDefinition = "timestamptz")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Jakarta")
    private Date updatedDate;

}
