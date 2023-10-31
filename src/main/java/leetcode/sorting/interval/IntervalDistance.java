package leetcode.sorting.interval;

public class IntervalDistance
{
	public Interval interval;
	public int distance;

	public IntervalDistance(Interval interval, int distance) {
		this.interval = interval;
		this.distance = distance;
	}

	@Override
	public String toString()
	{
		return "["+interval.start+","+interval.end+"],"+distance;
	}
}
