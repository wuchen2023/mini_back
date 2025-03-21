package com.web.back.controller;

import com.web.back.domain.ReviewDiscussion;
import com.web.back.service.ReviewDiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ReviewDiscussionController 控制层
 */
@RestController
@RequestMapping("/review-discussion")
public class ReviewDiscussionController {

    @Autowired
    private ReviewDiscussionService reviewDiscussionService;

    // 添加一条审核记录
    @PostMapping("/add")
    public void addReviewDiscussion(@RequestBody ReviewDiscussion reviewDiscussion) {
        reviewDiscussionService.addReviewDiscussion(reviewDiscussion);  // 调用服务层添加审核记录
    }

    // 查询某个讨论的所有审核记录
    @GetMapping("/history/{discussionId}")
    public List<ReviewDiscussion> getReviewHistoryByDiscussionId(@PathVariable Integer discussionId) {
        return reviewDiscussionService.getReviewHistoryByDiscussionId(discussionId);  // 查询审核历史
    }

    // 查询某个讨论的最新审核记录
    @GetMapping("/latest/{discussionId}")
    public ReviewDiscussion getLatestReviewByDiscussionId(@PathVariable Integer discussionId) {
        return reviewDiscussionService.getLatestReviewByDiscussionId(discussionId);  // 查询最后一次审核记录
    }
}

