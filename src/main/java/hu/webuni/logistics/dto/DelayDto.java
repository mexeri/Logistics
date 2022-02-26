package hu.webuni.logistics.dto;



public class DelayDto {

	public Long milestoneId;
	public Integer delayLength;
	
	public Long getlMilestoneId() {
		return milestoneId;
	}
	public void setlMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}
	public Integer getiDelayLength() {
		return delayLength;
	}
	public void setiDelayLength(Integer delayLength) {
		this.delayLength = delayLength;
	}

	
}
