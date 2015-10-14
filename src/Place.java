
public class Place {
	
	private String name;
	private String city;
	private String country;
	
	public Place(String name, String city, String country)	{
		this.name = name;
		this.city = city;
		this.country = country;
	}
	
	public String getCity(){
		return city;
	}
	public String getName()	{
		return name;
	}
	public String getCountry()	{
		return country;
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
