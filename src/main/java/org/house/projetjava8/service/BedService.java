package org.house.projetjava8.service;

import org.house.projetjava8.dao.BedDao;
import org.house.projetjava8.model.Bed;

import java.sql.SQLException;
import java.util.List;

public class BedService {
    private final BedDao bedDao = new BedDao();

    public void add(Bed bed) {
        try {
            bedDao.add(bed);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add bed: " + e.getMessage(), e);
        }
    }

    public List<Bed> getAll() {
        try {
            return bedDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load beds: " + e.getMessage(), e);
        }
    }
}
