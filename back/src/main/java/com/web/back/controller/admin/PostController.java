package com.web.back.controller.admin;

import com.web.back.domain.Post;
import com.web.back.service.PostService;
import com.web.back.state.ResposeResult;
import com.web.back.state.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "api/admin/popst")
public class PostController {
    private final PostService postService;

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
    public RestResponse delete_post(@RequestParam Integer id){
        Post post = postService.selectById(id);
        post.setDeleted(true);
        postService.updateByIdFilter(post);
        return RestResponse.ok();
    }

}
