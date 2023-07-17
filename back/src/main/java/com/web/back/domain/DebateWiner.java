package com.web.back.domain;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName debate_winer
 */
@TableName(value ="debate_winer")
@Data
public class DebateWiner implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private Integer student_group_id;

    /**
     *
     */
    private Integer activityId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public DebateWiner(Integer student_group_id, Integer activityId) {
        this.student_group_id = student_group_id;
        this.activityId = activityId;
    }
}