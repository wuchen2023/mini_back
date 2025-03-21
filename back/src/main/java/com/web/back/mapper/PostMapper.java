package com.web.back.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.domain.Post;
import com.web.back.viewmodel.admin.post.PostPageRequestVM;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/12.
 * @DESC:
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {
    List<Post> selectList(QueryWrapper queryWrapper);

    Post selectList_by_deleted();

    Post delete_post(Integer id);

    Post selectByPrimaryKey(Integer id);

    List<Post> page(PostPageRequestVM requestVM);

    Post getPostWithReplies(Integer postId);

    List<Post> select_post();

    Integer update_post_reply_count(Integer postId);

}
