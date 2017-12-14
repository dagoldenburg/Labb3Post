package BO;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by douglas on 11/4/17.
 */
@Entity
@NamedQueries({
        @NamedQuery(name="Post.FindPostBycreatorId",query = "Select p From Post  p where  p.creatorId=:id")
})
@Table(name = "post", schema = "SocialDb", catalog = "")
public class Post {

    private long id;
    private String content;
    private Date publishDate;
    private String title;
    private long creatorId;

    @Id
    @Column(name = "id")
    public long getId() { return id;}

    public void setId(long id) { this.id = id; }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "publish_date")
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "creator_id")
    public long getCreatorId() { return creatorId;}

    public void setCreatorId(long creator) { this.creatorId = creator;}
}
