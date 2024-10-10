package com.example.test.WorkerService;

import com.project.dao.WorkerRepo;
import com.project.entity.Worker;
import com.project.service.WorkerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkerServiceTest {

    @InjectMocks
    private WorkerService workerService;

    @Mock
    private WorkerRepo workerRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerWorker_ShouldSaveWorker() {
        Worker worker = new Worker();
        when(workerRepo.save(worker)).thenReturn(worker);

        Worker result = workerService.registerWorker(worker);

        verify(workerRepo, times(1)).save(worker);
        assertEquals(worker, result);
    }

    @Test
    void getWorkerByFloor_ShouldReturnWorkersForFloorAndHostel() {
        int floor = 1;
        String hostel = "HostelA";
        Worker worker1 = new Worker();
        worker1.setFloor(floor);
        worker1.setHostel(hostel);
        Worker worker2 = new Worker();
        worker2.setFloor(floor);
        worker2.setHostel(hostel);
        List<Worker> workers = Arrays.asList(worker1, worker2);
        when(workerRepo.findByFloorAndHostel(floor, hostel)).thenReturn(workers);

        List<Worker> result = workerService.getWorkerByFloor(floor, hostel);

        verify(workerRepo, times(1)).findByFloorAndHostel(floor, hostel);
        assertEquals(workers, result);
    }

    @Test
    void getWorkerByNameAndFloor_ShouldReturnWorkersForHostel() {
        String hostel = "HostelA";
        Worker worker1 = new Worker();
        worker1.setHostel(hostel);
        Worker worker2 = new Worker();
        worker2.setHostel("HostelB");
        List<Worker> allWorkers = Arrays.asList(worker1, worker2);
        when(workerRepo.findAll()).thenReturn(allWorkers);

        List<Worker> result = workerService.getWorkerByNameAndFloor(hostel);

        verify(workerRepo, times(1)).findAll();
        assertTrue(result.contains(worker1));
        assertFalse(result.contains(worker2));
    }

    @Test
    void getWorkerById_ShouldReturnWorkerById() {
        long workerId = 1L;
        Worker worker = new Worker();
        when(workerRepo.findById(workerId)).thenReturn(Optional.of(worker));

        Worker result = workerService.getWorkerById(workerId);

        verify(workerRepo, times(1)).findById(workerId);
        assertEquals(worker, result);
    }

    @Test
    void getWorkerById_ShouldReturnNullWhenNotFound() {
        long workerId = 1L;
        when(workerRepo.findById(workerId)).thenReturn(Optional.empty());

        Worker result = workerService.getWorkerById(workerId);

        verify(workerRepo, times(1)).findById(workerId);
        assertNull(result);
    }

    @Test
    void deleteWorker_ShouldReturnTrueWhenDeleted() {
        long workerId = 1L;
        doNothing().when(workerRepo).deleteById(workerId);

        boolean result = workerService.deleteWorker(workerId);

        verify(workerRepo, times(1)).deleteById(workerId);
        assertTrue(result);
    }

    @Test
    void deleteWorker_ShouldReturnFalseWhenExceptionOccurs() {
        long workerId = 1L;
        doThrow(new RuntimeException()).when(workerRepo).deleteById(workerId);

        boolean result = workerService.deleteWorker(workerId);

        verify(workerRepo, times(1)).deleteById(workerId);
        assertFalse(result);
    }
}
