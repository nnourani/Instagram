package info.instagram.instagram.entities;

import android.media.Image;

/**
 * Created by Naser on 10/24/2017.
 */

public class Post {

    private long id;
    private long userId;
    private String profileName;
    private int profileImage;
    private int postImage;
    private String postText;
    private int likeCount;
    private int commentCount;
    private boolean isLiked;
    private boolean isCommented;
    private boolean isShared;

    public Post() {
    }

    public Post(long id, long userId, String profileName,
                int profileImage, int postImage,
                String postText, int likeCount,
                int commentCount, boolean isLiked,
                boolean isCommented, boolean isShared) {
        this.id = id;
        this.userId = userId;
        this.profileName = profileName;
        this.profileImage = profileImage;
        this.postImage = postImage;
        this.postText = postText;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.isLiked = isLiked;
        this.isCommented = isCommented;
        this.isShared = isShared;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isCommented() {
        return isCommented;
    }

    public void setCommented(boolean commented) {
        isCommented = commented;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }


}
