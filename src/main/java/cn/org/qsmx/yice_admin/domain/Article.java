package cn.org.qsmx.yice_admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName article
 */
@TableName("article")
public class Article implements Serializable {
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
     * 外部文章链接
     */
    private String url;

    /**
     * 内部文章内容
     */
    private String content;

    /**
     * 标题
     */
    private String title;
    /**
     * 类型
     */
    private String type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
     * 外部文章链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 外部文章链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 内部文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内部文章内容
     */
    public void setContent(String content) {
        this.content = content;
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
        Article other = (Article) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAdminId() == null ? other.getAdminId() == null : this.getAdminId().equals(other.getAdminId()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
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
        sb.append(", url=").append(url);
        sb.append(", content=").append(content);
        sb.append(", title=").append(title);
        sb.append(", addtime=").append(addtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}