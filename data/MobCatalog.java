package fr.omega2028.dungeoncreator.data;

import java.util.ArrayList;

public class MobCatalog {
    private ArrayList<MobCustom> catalog;

    public MobCatalog(){
        catalog = new ArrayList<MobCustom>();
    }

    public MobCustom getCustomMob(int index){
        return catalog.get(index);
    }

    public void replaceCustomMob(int index, MobCustom mobCustom){
        catalog.set(index, mobCustom);
    }

    public void delCustomMob(int index){
        catalog.remove(index);
    }

    public void addCustomMob(MobCustom mobCustom){
        catalog.add(mobCustom);
    }

    public int getLenght(){
        return catalog.size();
    }

    //Recherche un mob custom, -1 : pas trouvé, autre valeur : trouvé.
    public int rechercheMobCustom(MobCustom mobCustom) {
        for (int i = 0; i < catalog.size(); i++){
            if (catalog.get(i) == mobCustom) return i;
        }
        return -1;
    }

}
