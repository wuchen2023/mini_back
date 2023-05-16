package com.web.back.controller.admin;

import com.web.back.domain.Post;
import com.web.back.service.PostService;
import com.web.back.state.ResposeResult;
import com.web.back.state.RestResponse;
import com.web.back.utils.ModelMapperSingle;
import com.web.back.viewmodel.admin.debate.DebateRequestVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author by hongdou
 * @date 2023/5/12.
 * @DESC:
 */
@Controller
@Api("Post帖子的API")
@RequestMapping(value = "api/admin/post")
public class PostController {
    private final PostService postService;

    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ResponseBody
    @ApiOperation("获取所有帖子")
    @GetMapping("get_all_posts")
    public List<Post> get_all_posts(){
        return postService.get_all_posts();
    }

    @ResponseBody
    @PostMapping("/student/add_post")
    @ApiOperation("学生添加帖子")
    public ResposeResult stu_add_post(@RequestParam String code, @RequestParam String student_account, @RequestParam String title, @RequestParam String content){
        if(code.equals(student_redis_get(student_account))){
            System.out.println("学生登录的账户是："+student_account);
            Post post = new Post(title, content,"stu_account："+student_account);
            return postService.add_post(post);
        }else{
            return new ResposeResult(0,"学生用户未登录!请登录");
        }

    }

    @ResponseBody
    @PostMapping("/teacher/add_post")
    @ApiOperation("老师添加帖子")
    public ResposeResult tea_add_post(@RequestParam String code, @RequestParam String teacher_account, @RequestParam String title, @RequestParam String content){
        if(code.equals(teacher_redis_get(teacher_account))){
            System.out.println("老师登录的账户是："+teacher_account);
            Post post = new Post(title, content,"tea_account："+teacher_account);
            return postService.add_post(post);
        }else{
            return new ResposeResult(0,"老师用户未登录!请登录");
        }

    }

    @ResponseBody
    @PostMapping("delete_post/{id}")
    @ApiOperation("删除帖子")
    public RestResponse delete_post(@PathVariable Integer id){

//        Post post = postService.selectById(id);
//        post.setDeleted(true);
//        postService.updateByIdFilter(post);
//        return RestResponse.ok();

        postService.delete_post(id);
        return RestResponse.ok();

    }

    @ResponseBody
    @ApiOperation("帖子查询")
    @PostMapping("select/{id}")
    public RestResponse<DebateRequestVM> select(@PathVariable Integer id){
        try{
            Post post = postService.selectById(id);
            DebateRequestVM vm = modelMapper.map(post,DebateRequestVM.class);
            return RestResponse.ok(vm);
        }catch (Exception e){
            return RestResponse.fail(500,"没有此id的帖子");
        }
    }


    public String student_redis_get(String key) {
        return (String) redisTemplate.opsForValue().get(key + "-student");
    }
    public String teacher_redis_get(String key) {
        return (String) redisTemplate.opsForValue().get(key + "-teacher");
    }

}
