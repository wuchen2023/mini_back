package com.web.back.domain.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.back.domain.SubjectClass;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by hongdou
 * @date 2023/8/10.
 * @DESC:
 */
public class TreeSelect implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect()
    {

    }

    public TreeSelect(SubjectClass subjectClass)
    {
        this.id = subjectClass.getSubjectId();
        this.label = subjectClass.getSubjectName();
        this.children = subjectClass.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

//    public TreeSelect(SysMenu menu)
//    {
//        this.id = menu.getMenuId();
//        this.label = menu.getMenuName();
//        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
//    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<TreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeSelect> children)
    {
        this.children = children;
    }
}
