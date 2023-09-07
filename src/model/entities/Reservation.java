package model.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
	protected StringBuilder sb = new StringBuilder();
	protected DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Integer roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	Reservation() { 
	}

	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
		super();
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public long duration() {
		Duration duration = Duration.between(checkIn.atStartOfDay(), checkOut.atStartOfDay());
		return duration.toDays();	
	}
	
	public String updateDates(LocalDate checkIn, LocalDate checkOut) {
		LocalDate now = LocalDate.now();
		if (checkIn.isBefore(now) || checkOut.isBefore(now)) {
			return "Error in reservation: Reservation dates for update must be future";
		}
		if (!checkOut.isAfter(checkIn)) {
			return "Error in reservation: Check-out date must be after check-in date";
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null;
	}

	@Override
	public String toString() {
		return "Room " + roomNumber
				+ ", check-in: " + fmt.format(checkIn)
				+ ", check-out: " + fmt.format(checkOut)
				+ ", " + duration() + " nights";
	}
	
	
	
}
