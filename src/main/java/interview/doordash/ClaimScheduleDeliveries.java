package interview.doordash;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * https://leetcode.com/discuss/interview-question/1544410/Doordash-TPS-Senior-Software-Engineer
 * https://leetcode.com/discuss/interview-question/1920251/Doordash-or-Phone-or-Claim-Scheduled-Deliveries
 *
 * At DoorDash, many deliveries are scheduled well in advance. To improve our assignment rate, we want to enable dashers
 * to claim these scheduled deliveries early.
 * However, we noticed that certain dashers perform better, and want to reward them with a better selection.
 * As a simple solution, we will introduce open windows for when deliveries will appear for a particular dasher.
 * Below are the following requirements.
 *
 * deliveries scheduled two days or further into the future should never be available
 * high tier dashers can see all of next day deliveries if the current time is 18:00 or later
 * all dashers can see all of next day deliveries if the current time is 19:00 or later
 * all dashers can see same day deliveries anytime
 * Example:
 * deliveries = [
 * { “id”: “one”, “pickupTime”: “2021/01/15 10:00”, “storeId”: “store_1” },
 * { “id”: “two”, “pickupTime”: “2021/01/16 6:00”, “storeId”: “store_1” }
 * ];
 *
 * dasher = { “id”: “dasher_1”, “tier”: “low” };
 *
 * getAvailableDeliveries(dasher, deliveries, 2021/01/15 18:00) -> [“one”]
 */

/**
 * We realized, some dashers work really well for some stores, but not as much for others.
 * Therefore we want to allow stores the ability to prefer dashers, so that they can see and claim their deliveries first.
 * Below, is the new requirement we want to introduce.
 * Stores who prefer dashers will have their next day deliveries show up at 17:00 for those preferred dashers only.
 *
 * Consider this new class and new method signature:
 * class StorePreference {
 * string id;
 * string storeId;
 * string dasherId;
 * }
 *
 * Example:
 * List getAvailableDeliveries(Dasher dasher, List Delivery, DateTime currentTime, List storePreferences)
 * deliveries = [{ one, 2021/01/15 10:00, store_1 }, { two, 2021/01/16 6:00, store_1 }]
 * dasher = { dasher_1, low }
 * preferences = [{ one, store_1, dasher_1 }]
 *
 * getAvailableDeliveries(dasher, deliveries, 2021/01/15 18:00, preferences) should return both deliveries
 */

public class ClaimScheduleDeliveries
{

	private List<Delivery> deliveries;
	private Dasher dasher;
	private List<StorePreference> storePreferences;

	public ClaimScheduleDeliveries() {}

	public ClaimScheduleDeliveries(List<Delivery> deliveries, Dasher dasher) {
		this.deliveries = deliveries;
		this.dasher = dasher;
	}

	public ClaimScheduleDeliveries(List<Delivery> deliveries, Dasher dasher, List<StorePreference> storePreferences) {
		this(deliveries, dasher);
		this.storePreferences = storePreferences;
	}

	public  List<String> getAvailableDeliveries(Dasher dasher, List<Delivery> deliveries, LocalDateTime currentTime)
	{
		List<String> availableDeliveries = new ArrayList<>();
		for (Delivery delivery : deliveries)
		{
			if (canDasherSeeTheDelivery(dasher, delivery, currentTime))
			{
				availableDeliveries.add(delivery.id);
			}
		}
		return availableDeliveries;
	}

	public List<String> getAvailableDeliveries(Dasher dasher, List<Delivery> deliveries, LocalDateTime currentTime,
			List<StorePreference> storePreferences)
	{
		List<String> availableDeliveries = new ArrayList<>();
		for (Delivery delivery : deliveries)
		{
			if (canDasherSeeTheDelivery(dasher, delivery, currentTime, storePreferences))
			{
				availableDeliveries.add(delivery.id);
			}
		}
		return availableDeliveries;
	}

	public boolean canDasherSeeTheDelivery(Dasher dasher, Delivery delivery, LocalDateTime dateTime,
			List<StorePreference> storePreferences)
	{
		String delStoreId = delivery.storeId;
		String dasherId = dasher.id;
		LocalDateTime deliveryPickupTime = delivery.pickupTime;
		//long days = Duration.between(deliveryPickupTime.toLocalDate(), dateTime.toLocalDate()).toDays();
		long days = DAYS.between(dateTime.toLocalDate(), deliveryPickupTime.toLocalDate());

		int currentHour = dateTime.getHour();

		if (days >= 2) return false;
		if (days <= 0) 	return true;

		if (dasher.tier.equals("high") && currentHour >= 18) return true;

		if (isPreferredDasher(dasherId, delStoreId, storePreferences) && currentHour >= 17) return true;

		return currentHour >= 19;
	}

	public  boolean isPreferredDasher(String dasherId, String deliveryStoreId, List<StorePreference> storePreferences)
	{
		return storePreferences.stream()
				.anyMatch(storePreference -> {
					String storeDasherId = storePreference.dasherId;
					String storeId = storePreference.storeId;
					return (deliveryStoreId.equals(storeId) && dasherId.equals(storeDasherId));
				});
	}


	public  boolean canDasherSeeTheDelivery(Dasher dasher, Delivery delivery, LocalDateTime dateTime)
	{
		//long days = Duration.between(deliveryPickupTime.toLocalDate(), dateTime.toLocalDate()).toDays();
		long days = DAYS.between(dateTime.toLocalDate(), delivery.pickupTime.toLocalDate());

		int currentHour = dateTime.getHour();

		if (days >= 2) return false;
		if (days <= 0) 	return true;

		if (dasher.tier.equals("high") && currentHour >= 18) return true;
		return currentHour >= 19;
	}

	 static class Delivery {
		final String id;
		final LocalDateTime pickupTime;
		final String storeId;

		public Delivery(String id, LocalDateTime pickupTime, String storeId) {
			this.id = id;
			this.pickupTime = pickupTime;
			this.storeId = storeId;
		}

		@Override
		public String toString() {
			return "id:"+id + ", pickupTime:"+pickupTime+", storeId:"+storeId;
		}
	}

	static class Dasher
	{
		String id;
		String tier; //high, low

		public Dasher(String id, String tier) {
			this.id = id;
			this.tier = tier;
		}

		@Override
		public String toString() {
			return "id:"+id + ", tier:"+tier;
		}
	}

	static class StorePreference {
		String id;
		String storeId;
		String dasherId;

		public StorePreference(String id, String storeId, String dasherId) {
			this.id = id;
			this.storeId = storeId;
			this.dasherId = dasherId;
		}

		@Override
		public String toString() {
			return "id:"+id + ", storeId:"+storeId+", dasherId:"+dasherId;
		}
	}

	public static void main(String ... args)
	{
		Delivery d1 = new Delivery("one", LocalDateTime.of(2021,1,15,10,0,0), "store_1");
		Delivery d2 = new Delivery("two", LocalDateTime.of(2021,1,16,6,0,0), "store_1");
		List<Delivery> deliveries = Arrays.asList(d1, d2);
		Dasher dasher = new Dasher("dasher_1", "low");

		ClaimScheduleDeliveries c = new ClaimScheduleDeliveries(deliveries, dasher);

		List<String> availableDeliveries = c.getAvailableDeliveries(dasher, deliveries, LocalDateTime.of(2021, 1, 15, 18,0,0));
		System.out.println(availableDeliveries);//[one]

		StorePreference storePreference = new StorePreference("one", "store_1", "dasher_1");
		List<StorePreference> storePreferences = Arrays.asList(storePreference);

		c = new ClaimScheduleDeliveries(deliveries, dasher);

		List<String> availableDeliveries1 = c.getAvailableDeliveries(dasher, deliveries, LocalDateTime.of(2021, 1, 15, 18,0,0), storePreferences);
		System.out.println(availableDeliveries1);//[one, two]
	}

}
