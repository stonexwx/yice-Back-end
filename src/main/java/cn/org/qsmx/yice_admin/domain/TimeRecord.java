package cn.org.qsmx.yice_admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName time_record
 */
@TableName("time_record")
public class TimeRecord implements Serializable {
    /**
     * 自增id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 用户状态选择
     */
    private String state;

    /**
     * 上厕所时间
     */
    private String time;

    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
     * 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 用户状态选择
     */
    public String getState() {
        return state;
    }

    /**
     * 用户状态选择
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 上厕所时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 上厕所时间
     */
    public void setTime(String time) {
        this.time = time;
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
        TimeRecord other = (TimeRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
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
        sb.append(", uid=").append(uid);
        sb.append(", state=").append(state);
        sb.append(", time=").append(time);
        sb.append(", addtime=").append(addtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}