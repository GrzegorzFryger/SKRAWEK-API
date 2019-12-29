package pl.edu.pja.prz.account.facade.dto;

import pl.edu.pja.prz.account.model.enums.Gender;

import java.util.UUID;

public class ChildDto {
	private UUID id;
	private String name;
	private String surname;
	private String pesel;
	private Gender gender;
	private StudyPeriodDto period;

	public ChildDto() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public StudyPeriodDto getPeriod() {
		return period;
	}

	public void setPeriod(StudyPeriodDto period) {
		this.period = period;
	}
}
