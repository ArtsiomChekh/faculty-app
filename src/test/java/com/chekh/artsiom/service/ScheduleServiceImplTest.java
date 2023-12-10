package com.chekh.artsiom.service;

import com.chekh.artsiom.model.Schedule;
import com.chekh.artsiom.repository.ScheduleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ScheduleServiceImplTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllSchedules() {
        List<Schedule> mockSchedules = new ArrayList<>();
        mockSchedules.add(new Schedule());
        mockSchedules.add(new Schedule());

        when(scheduleRepository.findAll()).thenReturn(mockSchedules);

        List<Schedule> schedules = scheduleService.getAllSchedules();
        verify(scheduleRepository, times(1)).findAll();
        assertEquals(mockSchedules, schedules);
    }
}
