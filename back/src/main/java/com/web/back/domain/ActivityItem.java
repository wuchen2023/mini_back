package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author by hongdou
 * @date 2023/7/19.
 * @DESC:
 */
@TableName(value ="activity_item")
@Data
@AllArgsConstructor
public class ActivityItem {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private  Integer activity_id;

    private Integer stu_item1;

    private  Integer stu_item2;

    //构造函数
    public ActivityItem(Integer activity_id, Integer stu_item1, Integer stu_item2){
        this.activity_id = activity_id;
        this.stu_item1 = stu_item1;
        this.stu_item2 = stu_item2;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id  = id;
    }

    public Integer getActivity_id(){
        return activity_id;
    }

    public void setActivity_id(Integer activity_id){
        this.activity_id = activity_id;
    }

    public Integer getStu_item1(){
        return stu_item1;
    }

    public void setStu_item1(Integer stu_item1){
        this.stu_item1 = stu_item1;
    }


    public Integer getStu_item2(){
        return stu_item2;
    }

    public void setStu_item2(Integer stu_item2){
        this.stu_item2 = stu_item2;
    }


}
