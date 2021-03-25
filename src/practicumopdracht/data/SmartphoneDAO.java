package practicumopdracht.data;

import practicumopdracht.models.Smartphone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Functionality:
 *
 * @author Chi Yu Yeung
 */
public abstract class SmartphoneDAO implements DAO<Smartphone> {
    protected List<Smartphone> objects;

    public SmartphoneDAO() {
        objects = new ArrayList<>();
        load();
    }

    @Override
    public List<Smartphone> getAll() {
//        return Collections.unmodifiableList(objects);
        return this.objects;
    }

    public Smartphone getById(int index) {
        if(objects.size() == 0) {
           return null;
        }
        return objects.get(index);
    }

    public int getIdFor(Smartphone object){
        if(!objects.contains(object)) {
            return objects.indexOf(object);
        } else {
            return -1;
        }
    }

    @Override
    public void addOrUpdate(Smartphone object) {
        if(!objects.contains(object)) {
            objects.add(object);
        } else {
            int index = objects.indexOf(object);
            objects.set(index, object);
        }
    }

    @Override
    public void remove(Smartphone object) {
        objects.remove(object);
        System.out.println(objects.size());
    }

    @Override
    public abstract boolean load();

    @Override
    public abstract boolean save();
}
