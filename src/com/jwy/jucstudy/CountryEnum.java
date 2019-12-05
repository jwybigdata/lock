package com.jwy.jucstudy;




public enum CountryEnum {
    One(1,"赵国"),Two(2,"韩国"),Three(3,"楚国"),Four(4,"魏国"),Five(5,"齐国"),Six(6,"燕国");

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    CountryEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CountryEnum foreach(int index){
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum elment : countryEnums) {
            if (index == elment.getId()){
                return elment;
            }
        }

        return null;
    }
}
