package cn.lth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "msg_info")
@NoArgsConstructor
//@AllArgsConstructor
public class MsgInfo {

    @Id
    @GenericGenerator(name = "user-uuid",strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "sendId", nullable = false)
    private String sendId;

    @Column(name = "sendName", nullable = false)
    private String sendName;

    @Column(name = "receiveId", nullable = false)
    private String receiveId;

    @Column(name = "receiveName", nullable = false)
    private String receiveName;

    @Column(name = "createTime", nullable = false)
    private String createTime;

    @Column(name = "content")
    private String content;
}
