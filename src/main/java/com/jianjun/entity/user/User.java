package com.jianjun.entity.user;

import com.google.gson.annotations.SerializedName;
import org.apache.ibatis.type.Alias;

@Alias("User")
public class User {

    @SerializedName("email")
    public String email;

    @SerializedName("username")
    public String username;

    @SerializedName("create_date")
    public long createDate;

    @SerializedName("update_date")
    public long updateDate;

    @SerializedName("token")
    public Token token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
