package cn.org.qsmx.yice_admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName search
 */
@TableName("search")
public class Search implements Serializable {
    /**
     * 自增id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 经纬度，格式xxx,xxx
     */
    private String latitudeAndLongitude;

    /**
     * 查询数量
     */
    private Long number;

    /**
     * 选择数量
     */
    private Long choose;

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

    public Long getChoose() {
        return choose;
    }

    public void setChoose(Long choose) {
        this.choose = choose;
    }

    /**
     * 经纬度，格式xxx,xxx
     */
    public String getLatitudeAndLongitude() {
        return latitudeAndLongitude;
    }

    /**
     * 经纬度，格式xxx,xxx
     */
    public void setLatitudeAndLongitude(String latitudeAndLongitude) {
        this.latitudeAndLongitude = latitudeAndLongitude;
    }

    /**
     * 查询数量
     */
    public Long getNumber() {
        return number;
    }

    /**
     * 查询数量
     */
    public void setNumber(Long number) {
        this.number = number;
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
        Search other = (Search) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLatitudeAndLongitude() == null ? other.getLatitudeAndLongitude() == null : this.getLatitudeAndLongitude().equals(other.getLatitudeAndLongitude()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLatitudeAndLongitude() == null) ? 0 : getLatitudeAndLongitude().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
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
        sb.append(", latitudeAndLongitude=").append(latitudeAndLongitude);
        sb.append(", number=").append(number);
        sb.append(", addtime=").append(addtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}