package model;

public class Menus {
    static int cod, codTipoMenu;
    static String descMenu;


    public Menus() {
    }

    public static int getCod() {
        return cod;
    }

    public static void setCod(int cod) {
        Menus.cod = cod;
    }

    public static int getCodTipoMenu() {
        return codTipoMenu;
    }

    public static void setCodTipoMenu(int codTipoMenu) {
        Menus.codTipoMenu = codTipoMenu;
    }

    public static String getDescMenu() {
        return descMenu;
    }

    public static void setDescMenu(String descMenu) {
        Menus.descMenu = descMenu;
    }


}