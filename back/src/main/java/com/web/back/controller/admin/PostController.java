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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ResponseBody
    @ApiOperation("获取所有帖子")
    @GetMapping("get_all_posts")
    public List<Post> get_all_posts(){
        return postService.get_all_posts();
    }

    @ResponseBody
    @PostMapping("add_post")
    @ApiOperation("添加帖子")
    public ResposeResult add_post(@RequestParam String title, @RequestParam String content){
        Post post = new Post(title, content);
        return postService.add_post(post);
    }

    @ResponseBody
    @PostMapping("delete_post/{id}")
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

}
