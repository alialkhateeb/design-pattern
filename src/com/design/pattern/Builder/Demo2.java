package com.design.pattern.Builder;

class Person{
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder <SELF extends PersonBuilder<SELF>>{
    protected Person per = new Person();

    public SELF withName(String name){
        per.name=name;
        return self();
    }

    public Person build(){
        return per;
    }

    protected SELF self(){
        return (SELF) this;
    }

}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder>{

    public EmployeeBuilder workAt(String pos){
        per.position=pos;
        return self()   ;
    }
    public Person build(){
        return per;
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}
class Demo2{
    public static void main(String[] args) {
        PersonBuilder pb = new PersonBuilder();
        Person ali = pb.withName("Ali").build();
        System.out.println(ali);

        EmployeeBuilder emb = new EmployeeBuilder();
        Person max = emb.withName("Max")
                .workAt("stc")
                .build();
        System.out.println(emb);

    }
}