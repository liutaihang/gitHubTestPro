package cn.lth.base;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@JsonAutoDetect
@MappedSuperclass
public class BaseEntity implements Serializable{

    private String id;

    private Date updateDate;
    private Date createDate;
    private String remark;

    @Id
    @GenericGenerator(name = "user-uuid",strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void preInsert(){
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.updateDate = new Date();
        this.createDate = new Date();
    }
}
