package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.service.BlindBoxService;
import com.web.back.domain.BlindBox;
import com.web.back.mapper.BlindBoxMapper;
import com.web.back.state.ResposeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author Dell
* @description 针对表【blind_box】的数据库操作Service实现
* @createDate 2023-05-07 13:09:42
*/
@Service
public class BlindBoxServiceImpl extends ServiceImpl<BlindBoxMapper, BlindBox>
    implements BlindBoxService {
    @Resource
    BlindBoxMapper blindBoxMapper;
    @Override
    public ResposeResult blindbox_answerSubmit(Integer exam_paper_id, Integer is_right, String true_answer){
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("stu_account",stu_account);
            queryWrapper.eq("exam_paper_id",exam_paper_id);
//            queryWrapper.eq("is_right",is_right);
//            queryWrapper.eq("true_answer",true_answer);
//            queryWrapper.eq("stu_account",stu_account);
//            queryWrapper.eq("teacher_account",teacher_account);
            BlindBox blindBox = blindBoxMapper.selectOne(queryWrapper);
            if(blindBox == null){
                throw new Exception();
            }
            BlindBox blindBox1 = new BlindBox(blindBox.getStu_account(), exam_paper_id, is_right, true_answer, blindBox.getTeacher_account(), blindBox.getClass_name());
//            blindBoxMapper.insert(blindBox1);
            blindBoxMapper.update(blindBox1, queryWrapper);
            return new ResposeResult(1, "提交答案成功");
        }catch (Exception e){
            return new ResposeResult(0, "提交答案失败");
        }
    }

    public  List<BlindBox>  blindbox_view(String stu_account, Integer exam_paper_id){
        try{
            List<BlindBox> blindBoxList = new ArrayList<>();
            blindBoxList = blindBoxMapper.findInfo(stu_account, exam_paper_id);
            System.out.println("查询到盲盒答题数据");

            return blindBoxList;

        }catch (Exception e){
            return null;
        }
    }
    @Override
    public List<BlindBox> blindbox_view_history(String class_name, String stu_account, String teacher_account){
        try{
            List<BlindBox> blindBoxList = new ArrayList<>();
            blindBoxList = blindBoxMapper.findInfo_condition(class_name, stu_account, teacher_account);
            System.out.println("按条件查询盲盒历史记录");
            return blindBoxList;
        }catch (Exception e){
            return null;
        }
    }

}




