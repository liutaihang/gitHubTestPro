package cn.lth.dto;

import cn.lth.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user_demo")
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends BaseEntity {

    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

    private String userUuid;

    @NotNull
    @Column(name = "passwd", nullable = false)
    private String passwd;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    public SysUser(@NotNull String username, @NotNull String passwd, @NotNull String email) {
        this.username = username;
        this.passwd = passwd;
        this.email = email;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
