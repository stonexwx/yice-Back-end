package cn.org.qsmx.yice_admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user
 */
@TableName("user")
public class User implements Serializable {
    /**
     * 自增id
     */
    @TableId(value = "id" ,type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 微信id
     */
    private String wxid;

    /**
     * 添加时间
     */
    private Date addtime;

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    public Long getId() {
        return id;
    }

    /**
     * 自增id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 微信id
     */
    public String getWxid() {
        return wxid;
    }

    /**
     * 微信id
     */
    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    /**
     * 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getWxid() == null ? other.getWxid() == null : this.getWxid().equals(other.getWxid()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getWxid() == null) ? 0 : getWxid().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", wxid=").append(wxid);
        sb.append(", addtime=").append(addtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}