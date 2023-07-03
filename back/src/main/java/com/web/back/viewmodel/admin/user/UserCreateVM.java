package com.web.back.viewmodel.admin.user;

/**
 * @author by hongdou
 * @date 2023/7/2.
 * @DESC:
 */
public class UserCreateVM {

//    表名为主键，生成策略为自增长
//    @TableId(type = IdType.AUTO)
    //由于Web端传入的id可能为空，所以这里不能加上主键注解
    private Integer id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String permission;

    /**
     *
     */
    private String account;

    /**
     *
     */
    private String password;

    /**
     *
     */
//    private Date createTime;

    /**
     *
     */
    private String gender;

    /**
     * 学生的角色，与web端有关的字段
     */
    private Integer role;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPermission(){
        return permission;
    }

    public void setPermission(String permission){
        this.permission = permission;
    }

    public String getAccount(){
        return account;
    }

    public void setAccount(String account){
        this.account = account;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public Integer getRole(){
        return role;
    }

    public void setRole(Integer role){
        this.role = role;
    }
}
