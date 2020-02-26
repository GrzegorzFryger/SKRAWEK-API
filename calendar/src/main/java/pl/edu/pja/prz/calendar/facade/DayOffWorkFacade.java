package pl.edu.pja.prz.calendar.facade;

import pl.edu.pja.prz.calendar.facade.dto.DayOffWorkDto;

import java.util.List;

public interface DayOffWorkFacade {
	DayOffWorkDto createDayOffWork(DayOffWorkDto dayOffWorkDto);

	DayOffWorkDto updateDayOffWork(DayOffWorkDto dayOffWorkDto);

	void deleteDayOffWork(Long id);

	DayOffWorkDto getDayOffWork(Long id);

	List<DayOffWorkDto> getAllDaysOff();
}
