package com.web.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.Post;
import com.web.back.state.ResposeResult;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/12.
 * @DESC:
 */
public interface PostService extends IService<Post> {
    public List<Post> get_all_posts();

    /**
     * 建立帖子
     */
    public ResposeResult add_post(Post post);

    public ResposeResult delete_post(Integer id);
    public Post selectById(Integer id);

}
