package org.naim.doctoo.payload;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AppointmentRequest {
	@NotNull
	private Long docteurId;
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime date;
	private String motif;
	private String userName;

}
