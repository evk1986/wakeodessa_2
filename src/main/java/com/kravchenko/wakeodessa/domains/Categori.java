package com.kravchenko.wakeodessa.domains;

/**
 * Created by Egor on 25.10.2016.
 */
public enum Categori {
    WAKEBOARD("WAKEBOARD"),
    WAKESKATE("WAKESKATE"),
    OTHER("OTHER");
    private final String displayedName;

    public String getDisplayedName() {

        return displayedName;
    }

    Categori(String displayedName) {

        this.displayedName = displayedName;
    }

    public static Categori[] getValues()
    {
        return values();
    }
}
