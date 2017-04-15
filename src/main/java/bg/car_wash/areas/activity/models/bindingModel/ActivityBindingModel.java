package bg.car_wash.areas.activity.models.bindingModel;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ActivityBindingModel {

	private Long id;

	@Size(min = 3, max = 40, message = "Invalid Activity name. (Activity name between 3 and 40 characters!")
	private String activityName;

	@Digits(integer = 2, fraction = 2)
	@DecimalMin(value = "0.00", message = "Activity price can not be negative number!")
	private BigDecimal activityPrice;

	public ActivityBindingModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public BigDecimal getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}
}
