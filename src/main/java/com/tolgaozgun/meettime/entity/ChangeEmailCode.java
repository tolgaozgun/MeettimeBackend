package com.tolgaozgun.meettime.entity;

import com.tolgaozgun.meettime.entity.enums.ChangeEmailCodeType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "change_email_codes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeEmailCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date expireDate;

    @NotNull
    private String code;

    @NotNull
    private String newEmail;

    @Enumerated(EnumType.STRING)
    private ChangeEmailCodeType changeEmailCodeType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @NotNull
    private boolean used = false;

    @NotNull
    private boolean valid = true;

    @Nullable
    private Date usedDate;

}
