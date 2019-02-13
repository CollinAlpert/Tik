package de.coke.tik.entities;

import com.github.collinalpert.java2db.annotations.ForeignKeyEntity;
import com.github.collinalpert.java2db.annotations.TableName;
import com.github.collinalpert.java2db.entities.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Collin Alpert
 */
@TableName("data")
public class Data extends BaseEntity {

	private long userId;

	@ForeignKeyEntity("userId")
	private User user;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime date;

	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime shiftStart;

	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime shiftEnd;

	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime breakFrom;

	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime breakTo;

	private String annotation;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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
