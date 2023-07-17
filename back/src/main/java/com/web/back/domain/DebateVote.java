package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
//@TableName(value ="debate_vote")
//@Data
//@AllArgsConstructor
public class DebateVote implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer debate_id;

    private Integer stu_group_id;

    private Integer tickets;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public DebateVote(Integer debate_id, Integer stu_group_id,Integer tickets){
        this.debate_id = debate_id;
        this.stu_group_id = stu_group_id;
        this.tickets = tickets;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getDebate_id(){
        return debate_id;
    }

    public void setDebate_id(Integer debate_id){
        this.debate_id = debate_id;
    }

    public Integer getStu_group_id(){
        return stu_group_id;
    }

    public void setStu_group_id(Integer stu_group_id){
        this.stu_group_id = stu_group_id;
    }

    public Integer getTickets(){
        return tickets;
    }

    public void setTickets(Integer tickets){
        this.tickets = tickets;
    }


}
