package com.web.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.web.back.domain.Post;
import com.web.back.domain.result.ActicityRes;
import com.web.back.state.ResposeResult;
import com.web.back.viewmodel.admin.post.PostPageRequestVM;

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


    PageInfo<Post> page(PostPageRequestVM requestVM);



}
