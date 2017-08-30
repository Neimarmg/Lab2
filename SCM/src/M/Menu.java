package M;

public class Menu {
    int cod, codTipoMenu;
    String descMenu;

   public Menu(){       
   }
    
    public Menu(int cod, int codTipoMenu, String descMenu) {
        this.cod = cod;
        this.codTipoMenu = codTipoMenu;
        this.descMenu = descMenu;
    }
    
    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setCodTipoMenu(int codTipoMenu) {
        this.codTipoMenu = codTipoMenu;
    }

    public void setDescMenu(String descMenu) {
        this.descMenu = descMenu;
    }

    public int getCod() {
        return cod;
    }

    public int getCodTipoMenu() {
        return codTipoMenu;
    }

    public String getDescMenu() {
        return descMenu;
    }
    
    
    
    
}
