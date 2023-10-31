package leetcode.sorting.interval;

public class EmployeeSchedule
{
	Interval interval;
	int outerIndex;
	int innerIndex;

	public EmployeeSchedule() {}

	public EmployeeSchedule(Interval interval, int outerIndex, int innerIndex) {
		this.interval = interval;
		this.outerIndex = outerIndex;
		this.innerIndex = innerIndex;
	}
}
