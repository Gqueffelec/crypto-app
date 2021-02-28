package app.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class Asset {
	private int id;
	private int	numberOfAction;
	private double buyPrice;
	private double deltaPrice;
	private LocalDate buyDate;
}
