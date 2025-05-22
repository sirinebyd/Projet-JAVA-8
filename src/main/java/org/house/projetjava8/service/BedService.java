package org.house.projetjava8.service;

import org.house.projetjava8.dao.BedDao;
import org.house.projetjava8.model.Bed;

import java.util.List;
import java.sql.SQLException;

public class BedService {
    private final BedDao dao = new BedDao();

    public List<Bed> getAll() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to getAll: " + e.getMessage(), e);
        }
    }

    public Bed getById(int id) {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to getById: " + e.getMessage(), e);
        }
    }

    public void save(Bed bed) {
        try {
            dao.add(bed);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save: " + e.getMessage(), e);
        }
    }

    public void update(Bed bed) {
        try {
            dao.update(bed);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update: " + e.getMessage(), e);
        }
    }

    public void delete(int id) {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete: " + e.getMessage(), e);
        }
    }
}
