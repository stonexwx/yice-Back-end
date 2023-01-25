package cn.org.qsmx.yice_admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName advertising
 */
@TableName("advertising")
public class Advertising implements Serializable {
    /**
     * 自增id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 管理员id
     */
    private Long adminId;

    /**
     * 图片链接
     */
    private String imageUrl;

    /**
     * 商品链接
     */
    private String shopUrl;

    /**
     * 标题
     */
    private String title;

    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
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
     * 管理员id
     */
    public Long getAdminId() {
        return adminId;
    }

    /**
     * 管理员id
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    /**
     * 图片链接
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 图片链接
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 商品链接
     */
    public String getShopUrl() {
        return shopUrl;
    }

    /**
     * 商品链接
     */
    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
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
        Advertising other = (Advertising) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAdminId() == null ? other.getAdminId() == null : this.getAdminId().equals(other.getAdminId()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
            && (this.getShopUrl() == null ? other.getShopUrl() == null : this.getShopUrl().equals(other.getShopUrl()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getShopUrl() == null) ? 0 : getShopUrl().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
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
        sb.append(", adminId=").append(adminId);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", shopUrl=").append(shopUrl);
        sb.append(", title=").append(title);
        sb.append(", addtime=").append(addtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}