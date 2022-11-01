package app.model;

public class Configuration {

	private int quantityToProduce;
	private int quantityToConsume;
	private int delayTime;	
	
	public Configuration(int quantityToProduce, int quantityToConsume, int delayTime) {
		this.quantityToProduce = quantityToProduce;
		this.quantityToConsume = quantityToConsume;
		this.delayTime = delayTime;
	}

	public int getQuantityToProduce() {
		return quantityToProduce;
	}
	
	public void setQuantityToProduce(int quantityToProduce) {
		this.quantityToProduce = quantityToProduce;
	}
	
	public int getQuantityToConsume() {
		return quantityToConsume;
	}
	
	public void setQuantityToConsume(int quantityToConsume) {
		this.quantityToConsume = quantityToConsume;
	}
	
	public int getDelayTime() {
		return delayTime;
	}
	
	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}
	
}