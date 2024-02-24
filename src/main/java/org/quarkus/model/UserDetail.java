package org.quarkus.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
@Table(name = "usrs_detl", schema = "fundamentals")
public class UserDetail extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial", name = "usrs_detl_id")
    private Integer userDetailId;

    @Column(name = "usrs_detl_uuid", length = 100, columnDefinition = "varchar(100) DEFAULT gen_random_uuid()")
    private String userDetailUuid;

    @Column(name = "usrs_id")
    private Integer userId;

    @Column(name = "usrs_detl_frst_nme", length = 100)
    private String userDetailFirstName;

    @Column(name = "usrs_detl_lst_nme", length = 64)
    private String userDetailLastName;

    @Column(name = "usrs_detl_phone", length = 14)
    private String userDetailPhone;

    @Column(name = "usrs_detl_addrss", length = 400)
    private String userDetailAddress;

    @Column(name = "usrs_detl_phtos", length = 400)
    private String userDetailPhotos;

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
