package com.axyy.component;

/**
 * @date 2020/4/15--16:52
 */
public enum TypeEnum {
    NOTICE_ADD(1,"希望");

    private int id;
    private String name;

    TypeEnum(int id, String name) {
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
    }}
