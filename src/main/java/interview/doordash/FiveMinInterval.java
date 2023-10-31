package interview.doordash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/***
 * https://leetcode.com/discuss/interview-question/1657195/DoorDash-or-Senior-Software-Engineer-or-E5-or-Onsite-Interview-or-Dec-2021
 * https://leetcode.com/discuss/interview-question/1553504/DoorDash-or-Phone-Interview-or-SFO-or-Senior-software-engineer
 *
 * Engineer is given the opening and closing hours of the store (in the week) with the following format,
 * find the list of all 5 min difference timestamp to query Redis.
 * Mon- 01, Tue- 02 ... Sun-07 and time (HHMM) format,
 * i.e
 * store operate on Monday 10 AM to Monday 5 PM : StartTime = 011000, EndTime = 011700 :
 * Output : 011000, 011005, 011010......, 011655, 011700
 *
 * store operate on Monday 10 AM to Tuesday 5 PM (24 x 7 open) : StartTime = 011000, EndTime = 021700
 * store operate on Tuesday 10 AM to Monday 5 PM (24 x 7 open) : StartTime = 021000, EndTime = 011700
 * Output :
 *
 * you are given 2 strings.  startTime : "mon 10:45 am" and  endTime: "mon 11:00 am".
 * you need to output all the times between starttime and endtime in the interval of 5 minutes.
 * output: ["11045", "11050","11055", "111000"].
 * in output each string represents the day+time+minute. eg: 11045: 1+10+45 => monday represents 1. tuesday 2 etc.
 *
 * Also, the output should be in 24hr format and input will be in 12hr format.
 * you are required to do input validations as they can have invalid time formats.
 */
public class FiveMinInterval
{
	private static final Map<String, Integer> DAY = new HashMap<>();
	static
	{
		DAY.put("mon", 1);
		DAY.put("tue", 2);
		DAY.put("wed", 3);
		DAY.put("thu", 4);
		DAY.put("fri", 5);
		DAY.put("sat", 6);
		DAY.put("sun", 7);
	}
	private static final Set<String> MERIDIEM = Set.of("am", "pm");

	public static List<String> fiveMinInterval(String startTime, String endTime)
	{
		List<String> result = new ArrayList<>();
		Timestamp t1 = parseTimestamp(startTime);
		Timestamp t2 = parseTimestamp(endTime);
		while (!t1.equals(t2))
		{
			result.add(t1.toString());
			t1.addFiveMinutes();
		}
		result.add(t1.toString());
		return result;
	}

	//"mon 10:00 am"
	public static Timestamp parseTimestamp(String inputTime)
	{
		String[] dayAndAmPm = inputTime.split(" ");

		//mon am
		String inputDay = dayAndAmPm[0];
		String amPm = dayAndAmPm[2];
		if (!DAY.containsKey(inputDay) || !MERIDIEM.contains(amPm))
		{
			throw new IllegalArgumentException("Invalid input: "+inputTime);
		}
		int day = DAY.get(dayAndAmPm[0]);

		String[] hourMinute = dayAndAmPm[1].split(":");
		int hour = Integer.parseInt(hourMinute[0]);
		int minute = Integer.parseInt(hourMinute[1]);

		//validate
		if (hour < 0 || hour > 12)  {
			throw new IllegalArgumentException("Invalid input: "+inputTime);
		}
		if (minute < 0 || minute > 59) {
			throw new IllegalArgumentException("Invalid input: "+inputTime);
		}

		if (amPm.equalsIgnoreCase("pm"))
		{
			if (hour != 12) hour = hour + 12; //11 pm -> 23, 12 pm -> 12
		}
		else //am
		{
			hour = hour % 12; //12 am -> 0
		}
		Timestamp timestamp = new Timestamp(day, hour, minute);
		return timestamp;
	}

	public static class Timestamp
	{
		int day;
		int hour;
		int minute;

		public Timestamp() {}

		public Timestamp(int day, int hour, int minute)
		{
			this.day = day;
			this.hour = hour;
			this.minute = minute;
		}

		//add 5 min increment methods
		//should I convert hour-minute to be number then add 5 until 55
		//add 5 to minute until 55
		public void addFiveMinutes()
		{
			minute += 5;
			if (minute >= 60)
			{
				minute %= 60;
				hour += 1;
				if (hour >= 24)
				{
					hour %= 24;
					day += 1;
					if (day >= 8)
						day = 1;
				}
			}
		}

		public boolean equals(Timestamp other)
		{
			return (this.hour == other.hour && this.minute == other.minute && this.day == other.day);
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(day, hour, minute);
		}

		@Override
		public String toString()
		{
			return "0"+String.valueOf(this.day * 10000 + this.hour * 100 + this.minute); //output
		}
	}

	public static void main(String ... args)
	{
		String startTime = "mon 10:45 am";
		String endTime = "mon 11:00 am";
		List<String> result = fiveMinInterval(startTime, endTime);
		System.out.println(result);
		//[011045, 011050, 011055, 011100]

		//StartTime = 011000, EndTime = 011700
		//Output: 011000, 011005, 011010......, 011655, 011700
		String startTime1 = "mon 10:00 am";
		String endTime1 = "mon 05:00 pm";
		List<String> result1 = fiveMinInterval(startTime1, endTime1);
		System.out.println(result1);
		//[011000, 011005, 011010, 011015, 011020, 011025, 011030, 011035, 011040, 011045, 011050, 011055, 011100, 011105, 011110, 011115, 011120, 011125, 011130, 011135, 011140, 011145, 011150, 011155, 011200, 011205, 011210, 011215, 011220, 011225, 011230, 011235, 011240, 011245, 011250, 011255, 011300, 011305, 011310, 011315, 011320, 011325, 011330, 011335, 011340, 011345, 011350, 011355, 011400, 011405, 011410, 011415, 011420, 011425, 011430, 011435, 011440, 011445, 011450, 011455, 011500, 011505, 011510, 011515, 011520, 011525, 011530, 011535, 011540, 011545, 011550, 011555, 011600, 011605, 011610, 011615, 011620, 011625, 011630, 011635, 011640, 011645, 011650, 011655, 011700]

		//StartTime = 011000, EndTime = 021700
		String startTime2 = "mon 10:00 am";
		String endTime2 = "tue 05:00 pm";
		List<String> result2 = fiveMinInterval(startTime2, endTime2);
		System.out.println(result2);
		//[011000, 011005, 011010, 011015, 011020, 011025, 011030, 011035, 011040, 011045, 011050, 011055, 011100, 011105, 011110, 011115, 011120, 011125, 011130, 011135, 011140, 011145, 011150, 011155, 011200, 011205, 011210, 011215, 011220, 011225, 011230, 011235, 011240, 011245, 011250, 011255, 011300, 011305, 011310, 011315, 011320, 011325, 011330, 011335, 011340, 011345, 011350, 011355, 011400, 011405, 011410, 011415, 011420, 011425, 011430, 011435, 011440, 011445, 011450, 011455, 011500, 011505, 011510, 011515, 011520, 011525, 011530, 011535, 011540, 011545, 011550, 011555, 011600, 011605, 011610, 011615, 011620, 011625, 011630, 011635, 011640, 011645, 011650, 011655, 011700, 011705, 011710, 011715, 011720, 011725, 011730, 011735, 011740, 011745, 011750, 011755, 011800, 011805, 011810, 011815, 011820, 011825, 011830, 011835, 011840, 011845, 011850, 011855, 011900, 011905, 011910, 011915, 011920, 011925, 011930, 011935, 011940, 011945, 011950, 011955, 012000, 012005, 012010, 012015, 012020, 012025, 012030, 012035, 012040, 012045, 012050, 012055, 012100, 012105, 012110, 012115, 012120, 012125, 012130, 012135, 012140, 012145, 012150, 012155, 012200, 012205, 012210, 012215, 012220, 012225, 012230, 012235, 012240, 012245, 012250, 012255, 012300, 012305, 012310, 012315, 012320, 012325, 012330, 012335, 012340, 012345, 012350, 012355, 020000, 020005, 020010, 020015, 020020, 020025, 020030, 020035, 020040, 020045, 020050, 020055, 020100, 020105, 020110, 020115, 020120, 020125, 020130, 020135, 020140, 020145, 020150, 020155, 020200, 020205, 020210, 020215, 020220, 020225, 020230, 020235, 020240, 020245, 020250, 020255, 020300, 020305, 020310, 020315, 020320, 020325, 020330, 020335, 020340, 020345, 020350, 020355, 020400, 020405, 020410, 020415, 020420, 020425, 020430, 020435, 020440, 020445, 020450, 020455, 020500, 020505, 020510, 020515, 020520, 020525, 020530, 020535, 020540, 020545, 020550, 020555, 020600, 020605, 020610, 020615, 020620, 020625, 020630, 020635, 020640, 020645, 020650, 020655, 020700, 020705, 020710, 020715, 020720, 020725, 020730, 020735, 020740, 020745, 020750, 020755, 020800, 020805, 020810, 020815, 020820, 020825, 020830, 020835, 020840, 020845, 020850, 020855, 020900, 020905, 020910, 020915, 020920, 020925, 020930, 020935, 020940, 020945, 020950, 020955, 021000, 021005, 021010, 021015, 021020, 021025, 021030, 021035, 021040, 021045, 021050, 021055, 021100, 021105, 021110, 021115, 021120, 021125, 021130, 021135, 021140, 021145, 021150, 021155, 021200, 021205, 021210, 021215, 021220, 021225, 021230, 021235, 021240, 021245, 021250, 021255, 021300, 021305, 021310, 021315, 021320, 021325, 021330, 021335, 021340, 021345, 021350, 021355, 021400, 021405, 021410, 021415, 021420, 021425, 021430, 021435, 021440, 021445, 021450, 021455, 021500, 021505, 021510, 021515, 021520, 021525, 021530, 021535, 021540, 021545, 021550, 021555, 021600, 021605, 021610, 021615, 021620, 021625, 021630, 021635, 021640, 021645, 021650, 021655, 021700]
	}
}
