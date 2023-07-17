package com.web.back.viewmodel.admin.debateVote;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
public class DebateVoteInfoVM {
    private Integer debate_id;

    private Integer positive_side_student_group_id;
    private Integer positive_side_tickets;

    private Integer negative_side_student_group_id;
    private Integer negative_side_tickets;




    public Integer getDebate_id(){
        return debate_id;
    }

    public void setDebate_id(Integer debate_id){
        this.debate_id = debate_id;
    }

    public Integer getPositive_side_student_group_id(){
        return positive_side_student_group_id;
    }

    public void setPositive_side_student_group_id(Integer positive_side_student_group_id){
        this.positive_side_student_group_id = positive_side_student_group_id;
    }

    public Integer getNegative_side_student_group_id(){
        return negative_side_student_group_id;
    }

    public void setNegative_side_student_group_id(Integer negative_side_student_group_id){
        this.negative_side_student_group_id = negative_side_student_group_id;
    }

    public Integer getPositive_side_tickets(){
        return positive_side_tickets;
    }

    public void setPositive_side_tickets(Integer positive_side_tickets){
        this.positive_side_tickets = positive_side_tickets;
    }

    public Integer getNegative_side_tickets(){
        return negative_side_tickets;
    }

    public void setNegative_side_tickets(Integer negative_side_tickets){
        this.negative_side_tickets = negative_side_tickets;
    }
}
