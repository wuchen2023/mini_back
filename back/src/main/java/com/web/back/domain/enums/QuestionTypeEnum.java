package com.web.back.domain.enums;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
public enum QuestionTypeEnum {
    SingleChoice(1,"单选题"),
    MultipleChoice(2, "多选题"),
    TrueFalse(3, "判断题"),
    GapFilling(4, "填空题"),
    ShortAnswer(5, "简答题");
    int code;
    String name;

    /**
     *
     * @param code
     * @param name
     */
    QuestionTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }


    /**
     * 创建get，set
     * @return
     */
    public int getCode(){
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
