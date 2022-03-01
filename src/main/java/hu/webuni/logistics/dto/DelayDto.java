package hu.webuni.logistics.dto;

public class DelayDto {

	public Long milestoneId;
	public Integer delayLength;
	
	public DelayDto(Long milestoneId, Integer delayLength) {
		this.milestoneId = milestoneId;
		this.delayLength = delayLength;
	}
	
	public Long getMilestoneId() {
		return milestoneId;
	}
	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}
	public Integer getDelayLength() {
		return delayLength;
	}
	public void setDelayLength(Integer delayLength) {
		this.delayLength = delayLength;
	}



}
