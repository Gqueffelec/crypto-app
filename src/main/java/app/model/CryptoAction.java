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
public class CryptoAction {
	private int id;
	private String name;
	private int	numberOfAction;
	private double buyPrice;
	private double actualPrice;
	private double deltaPrice;
	private LocalDate buyDate;
}
