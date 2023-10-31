package leetcode.sorting.interval;

public class CarPool //implements Comparable<CarPool>
{
	public int location;//endPoint
	public int numPassengers;

	public CarPool(int location, int numPassengers) {
		this.location = location;
		this.numPassengers = numPassengers;
	}

//	public int compareTo(CarPool that) {
//		if (this.location == that.location) {
//			return this.numPassengers - that.numPassengers;
//		}
//
//		return this.location - that.location;
//	}

}
