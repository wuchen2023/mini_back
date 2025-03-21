package com.web.back.service.impl;

import com.web.back.domain.TextContent;
import com.web.back.mapper.TextContentMapper;
import com.web.back.service.TextContentService;
import com.web.back.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
@Service
public class TextContentServiceImpl extends BaseServiceImpl<TextContent> implements TextContentService {
    private final TextContentMapper textContentMapper;

    @Autowired
    public TextContentServiceImpl(TextContentMapper textContentMapper){
        super(textContentMapper);
        this.textContentMapper = textContentMapper;
    }

    @Override
    public TextContent selectById(Integer id) {
        return super.selectById(id);
    }

    @Override
    public int insertByFilter(TextContent record) {
        return super.insertByFilter(record);
    }

    @Override
    public int updateByIdFilter(TextContent record) {
        return super.updateByIdFilter(record);
    }

    @Override
    public Integer selectAllCount() {
        return null;
    }

    @Override
    public <T, R> TextContent jsonConvertInsert(List<T> list, Date now, Function<? super T, ? extends R> mapper) {
        String frameTextContent = null;
        if (null == mapper) {
            frameTextContent = JsonUtil.toJsonStr(list);
        } else {
            List<R> mapList = list.stream().map(mapper).collect(Collectors.toList());
            frameTextContent = JsonUtil.toJsonStr(mapList);
        }
        TextContent textContent = new TextContent(frameTextContent, now);
        return textContent;
    }

    @Override
    public <T, R> TextContent jsonConvertUpdate(TextContent textContent, List<T> list, Function<? super T, ? extends R> mapper) {
        String frameTextContent = null;
        if (null == mapper) {
            frameTextContent = JsonUtil.toJsonStr(list);
        } else {
            List<R> mapList = list.stream().map(mapper).collect(Collectors.toList());
            frameTextContent = JsonUtil.toJsonStr(mapList);
        }
        textContent.setContent(frameTextContent);
        return textContent;
    }

}
