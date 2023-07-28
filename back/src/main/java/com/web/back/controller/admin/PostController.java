package com.web.back.controller.admin;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.Post;
import com.web.back.mapper.PostMapper;
import com.web.back.service.PostService;
import com.web.back.state.ResposeResult;
import com.web.back.state.RestResponse;
import com.web.back.utils.ModelMapperSingle;
import com.web.back.viewmodel.admin.debate.DebateRequestVM;
import com.web.back.viewmodel.admin.post.PostPageRequestVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.parameters.P;
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
    private final PostMapper postMapper;

    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

    @Autowired
    public PostController(PostService postService, PostMapper postMapper){
        this.postService = postService;
        this.postMapper = postMapper;
    }


    @ResponseBody
    @ApiOperation("获取所有帖子，不带回复贴")
    @GetMapping("get_all_posts")
    public List<Post> get_all_posts(){
        return postService.get_all_posts();
    }


    @ResponseBody
    @ApiOperation("获取所有帖子pagelist，除了两个必要参数，其余参数可输入开头的几个字符不必输入一模一样的内容，从而进行模糊查询")
    @PostMapping("/get_all_post_page")
    public RestResponse<PageInfo<Post>> get_all_post_page(@RequestParam(required = false) Integer id, @RequestParam(required = false) String title, @RequestParam(required = false) String content,@RequestParam(required = false) String className, @RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        PostPageRequestVM model = new PostPageRequestVM();
        model.setId(id);
        model.setTitle(title);
        model.setContent(content);
        model.setClassName(className);
        model.setPageIndex(pageIndex);
        model.setPageSize(pageSize);

        PageInfo<Post> page = postService.page(model);
        return RestResponse.ok(page);

    }
    @ResponseBody
    @PostMapping("/student/add_post")
    @ApiOperation("学生添加帖子")
    public ResposeResult stu_add_post(@RequestParam String student_account,@RequestParam String className, @RequestParam String title, @RequestParam String content){
        System.out.println("学生登录的账户是："+student_account);
        Post post = new Post(title, content,"stu_account:"+student_account,className);
        return postService.add_post(post);


    }

    @ResponseBody
    @PostMapping("/teacher/add_post")
    @ApiOperation("老师添加帖子")
    public ResposeResult tea_add_post(@RequestParam String teacher_account, @RequestParam String title, @RequestParam String content,@RequestParam String className){

        System.out.println("老师登录的账户是："+teacher_account);
        Post post = new Post(title, content,"tea_account:"+teacher_account,className);
        return postService.add_post(post);

    }

    @ResponseBody
    @PostMapping("delete_post/{id}")
    @ApiOperation("删除帖子")
    public RestResponse delete_post(@PathVariable Integer id){
        postService.delete_post(id);
        return RestResponse.ok();

    }

//    @ResponseBody
//    @ApiOperation("帖子查询")
//    @PostMapping("select/{id}")
//    public RestResponse<DebateRequestVM> select(@PathVariable Integer id){
//        try{
//            Post post = postService.selectById(id);
//            DebateRequestVM vm = modelMapper.map(post,DebateRequestVM.class);
//            return RestResponse.ok(vm);
//        }catch (Exception e){
//            return RestResponse.fail(500,"没有此id的帖子");
//        }
//    }

    @ResponseBody
    @ApiOperation("查询某一条帖子详细信息，返回帖子和该条的回复贴")
    @PostMapping("getPostWithReplies/{id}")
    public Post getPostWithReplies(Integer postId) {
        return postMapper.getPostWithReplies(postId);
    }




}
