package com.axyy.component;

/**
 * @date 2020/4/15--16:55
 */
public enum  NoticeAreaEnum {
    AREA_PUBLIC(1,"公告区域"),
    AREA_PRIVATE(2,"私人区域");

    private int id;
    private String name;

    NoticeAreaEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
