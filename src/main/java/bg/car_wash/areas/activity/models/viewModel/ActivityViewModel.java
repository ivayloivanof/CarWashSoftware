package bg.car_wash.areas.activity.models.viewModel;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ActivityViewModel {

	@Expose
	private Long id;

	@Expose
	private String activityName;

	@Expose
	private BigDecimal activityPrice;

	public ActivityViewModel() {
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
