package cn.lth.dto;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "name.null.error")
    private String name;

    @Column(name = "something")
    @NotNull(message = "something.null.error")
    private String something;

    @Column(name = "content")
    @NotNull(message = "content.null.error")
    private String content;
}
