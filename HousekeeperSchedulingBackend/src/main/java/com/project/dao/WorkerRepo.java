package com.project.dao;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Worker;
 
@Repository
public interface WorkerRepo extends JpaRepository<Worker, Long> {
 
    List<Worker> findByFloorAndHostel(int floor, String hostel);
 
    List<Worker> findByNameAndFloor(String name, int floor);
}