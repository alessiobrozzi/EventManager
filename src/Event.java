
public class Event {
	
	private String name;
	private String place;
	private String date;
	
	public Event(String name, String place, String date)	{
		this.name = name;
		this.place = place;
		this.date = date;
	}
	
	public String getDate(){
		return date;
	}
	public String getName()	{
		return name;
	}
	public String getPlace()	{
		return place;
	}
	
	/*
	public static void main(String[] args)	{
		String name = args[0];
		String place = args[1];
		int day = Integer.parseInt(args[2]);
		Event event = new Event(name, place, day);
	}
	*/

}
