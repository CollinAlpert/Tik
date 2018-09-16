package de.coke.tik.entities;

import com.github.collinalpert.java2db.database.ForeignKey;
import com.github.collinalpert.java2db.database.ForeignKeyObject;
import com.github.collinalpert.java2db.database.TableName;
import com.github.collinalpert.java2db.entities.BaseEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Collin Alpert
 */
@TableName("data")
public class Data extends BaseEntity {

	@ForeignKey(1)
	private int userId;

	@ForeignKeyObject(1)
	private User user;

	private LocalDateTime date;
	private LocalTime shiftStart;
	private LocalTime shiftEnd;
	private LocalTime breakFrom;
	private LocalTime breakTo;
	private String annotation;

	public Data() {
	}

	public Data(int userId, LocalDateTime date, LocalTime shiftStart, LocalTime shiftEnd, LocalTime breakFrom, LocalTime breakTo, String annotation) {
		this.userId = userId;
		this.date = date;
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
		this.breakFrom = breakFrom;
		this.breakTo = breakTo;
		this.annotation = annotation;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalTime getShiftStart() {
		return shiftStart;
	}

	public void setShiftStart(LocalTime shiftStart) {
		this.shiftStart = shiftStart;
	}

	public LocalTime getShiftEnd() {
		return shiftEnd;
	}

	public void setShiftEnd(LocalTime shiftEnd) {
		this.shiftEnd = shiftEnd;
	}

	public LocalTime getBreakFrom() {
		return breakFrom;
	}

	public void setBreakFrom(LocalTime breakFrom) {
		this.breakFrom = breakFrom;
	}

	public LocalTime getBreakTo() {
		return breakTo;
	}

	public void setBreakTo(LocalTime breakTo) {
		this.breakTo = breakTo;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
}
