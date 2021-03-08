package practicumopdracht.data;

import java.util.List;

public interface DAO<Smartphone> {
     List<Smartphone> getAll();
     void addOrUpdate(Smartphone object);
     void remove(Smartphone object);
     boolean save();
     boolean load();
}
