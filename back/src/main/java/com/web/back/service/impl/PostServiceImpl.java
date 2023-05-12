package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Post;
import com.web.back.domain.Subject;
import com.web.back.mapper.PostMapper;
import com.web.back.service.PostService;
import com.web.back.state.ResposeResult;
import com.web.back.utils.ModelMapperSingle;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by hongdou
 * @date 2023/5/12.
 * @DESC:
 */
@Service
@Slf4j
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService{

    @Resource
    PostService postService;

    @Resource
    PostMapper postMapper;
    @Override
    public List<Post> get_all_posts(){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        List<Post>postList = postMapper.selectList((Wrapper<Post>) queryWrapper);
        return postList.stream().map(post -> {return List_to_Post(post);}).collect(Collectors.toList());
    }

    public Post List_to_Post(Post post){
        return post;
    }

    @Override
    public ResposeResult add_post(Post post){
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>().eq("title",post.getTitle());
            if(postMapper.exists(queryWrapper)){
                throw new Exception();
            }
            postMapper.insert(post);
            log.info("新建帖子成功，id为："+ post.getId());

        }catch (Exception e){
            log.info("已经存在相同的帖子，请更改内容。");
            return new ResposeResult(0, "新增失败");
        }
        return new ResposeResult(1, "新增成功");
    }

    @Override
    public Post selectById(Integer id){
        return postMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResposeResult delete_post(Integer id){
        try{
            Post post = postMapper.delete_post(id);
            if (post ==null){
                throw new Exception();
            }
        }catch (Exception e){
            return new ResposeResult(0,"删除失败");
        }
        return new ResposeResult(1,"删除成功");

    }
}
