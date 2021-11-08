package fr.omega2028.dungeoncreator.data;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private List<DjRoom> rooms;
    private String name;

    public Dungeon(String name) {
        rooms = new ArrayList<DjRoom>();
        this.name = name;
    }

    public List<DjRoom> getRooms() {
        return rooms;
    }

    public String getName() {
        return name;
    }
}
