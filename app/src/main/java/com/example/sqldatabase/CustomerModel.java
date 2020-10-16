package com.example.sqldatabase;

public class CustomerModel {
    private int id;
    private int age;
    private String name;
    private boolean isActive;
    //Constructor


    public CustomerModel() {
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public CustomerModel(int id, int age, String name, boolean isActive) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.isActive = isActive;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }
}

