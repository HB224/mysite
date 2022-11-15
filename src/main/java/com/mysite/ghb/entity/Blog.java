package com.mysite.ghb.entity;
/*
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class Blog {

    private Long id;
    private String title;
    private String content;
    private String coverPicture;
    private String flag;
    private Integer views;
    private Integer thumbs;
    private boolean appreciation;
    private String shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    private Date createDate;
    private Date updateTime;
    private String description;


    private Type type;
    private List<Tag> tags=new LinkedList<>();
    private User user;
    private List<Comment> comments=new LinkedList<>();

    private String tagIds;

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }



    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", coverPicture='" + coverPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createDate +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", tags=" + tags +
                ", comments=" + comments +
                ", tagIds='" + tagIds + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
