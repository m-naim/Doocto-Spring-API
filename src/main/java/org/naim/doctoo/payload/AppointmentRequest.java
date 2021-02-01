package org.naim.doctoo.payload;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AppointmentRequest {
	@NotNull
	private Long docteurId;
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date date;
	private String motif;

}
