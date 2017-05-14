package baluk.springframework.services.map;

import baluk.springframework.model.ModelObject;



import java.util.*;


public abstract class AbstractMapServices {
    protected Map<Integer, ModelObject> modelMap;

    public AbstractMapServices() {
        modelMap = new HashMap<>();
    }

    public List<ModelObject> listAll() {
        return new ArrayList<>(modelMap.values());
    }

    public ModelObject getById(Integer id) {
        return modelMap.get(id);
    }

    public ModelObject saveOrUpdate(ModelObject modelObject) {
        if (modelObject != null) {

            if (modelObject.getId() == null) {
                modelObject.setId(getNextKey());
            }
            modelMap.put(modelObject.getId(), modelObject);

            return modelObject;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
    }

    public void delete(Integer id) {
        modelMap.remove(id);
    }

    private Integer getNextKey() {
        return Collections.max(modelMap.keySet()) + 1;
    }

}