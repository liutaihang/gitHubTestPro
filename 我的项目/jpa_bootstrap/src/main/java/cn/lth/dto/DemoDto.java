package cn.lth.dto;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author liutaihang
 * @version : 1.0
 * @Date : Create in 10:09 2018/4/11
 * @Description : ${TODO}
 */
@Data
@ToString
@Entity
@Table(name = "demo_dto")
public class DemoDto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,4}$", message = "name.null.error")
    private String name;

    @Column(name = "something")
    @NotBlank(message = "something.null.error")
    private String something;

    @Column(name = "content")
    @NotBlank(message = "content.null.error")
    private String content;

    public DemoDto(){super();}
    public DemoDto(String name, String something, String content){
        this.name = name;
        this.something = something;
        this.content = content;
    }
}
