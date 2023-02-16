import java.util.Arrays;
import java.util.StringJoiner;

public class Song {
	
	private String title;
	private String artist;
	private int[] duration;
	private final static String INFO_DELIMITER = "; ";
	private final static String TIME_DELIMITER = ":";
	
	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.duration = Arrays.copyOf(time, time.length);
	}
	
	public Song(String info) {
		String[] songInfo = info.split(INFO_DELIMITER);
		this.title = songInfo[0];
		this.artist = songInfo[1];
		String[] times = songInfo[2].split(TIME_DELIMITER);
		this.duration = new int[times.length];
		
		if (times.length == 1) {
			this.duration[0]=Integer.parseInt(times[0]);
		
		}
		else if (times.length == 2) {
			this.duration[0]=Integer.parseInt(times[1]);
			this.duration[1]=Integer.parseInt(times[0]);
		
		}
		else if (times.length == 3) {
			this.duration[0]=Integer.parseInt(times[2]);
			this.duration[1]=Integer.parseInt(times[1]);
			this.duration[2]=Integer.parseInt(times[0]);
		}
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(duration, duration.length);
	}
	
	@Override
	public String toString() {
		
		StringJoiner sj1 = new StringJoiner(INFO_DELIMITER);
		sj1.add(getTitle());
		sj1.add(getArtist());
		
		int[] time = getTime();
		int hour = 0;
		int min = 0;
		int sec = 0;
		StringJoiner sj2 = new StringJoiner(TIME_DELIMITER);

		
		if (time.length == 3) {
			hour = time[2];
			min = time[1];
			sec = time[0];
			sj2.add(String.valueOf(String.format("%02d",hour)));
			sj2.add(String.valueOf(String.format("%02d",min)));
			sj2.add(String.valueOf(String.format("%02d",sec)));
			
		}
		else if (time.length == 2) {
			min = time[1];
			sec = time[0];
			sj2.add(String.valueOf(String.format("%02d",min)));
			sj2.add(String.valueOf(String.format("%02d",sec)));
			
		}
		else if (time.length == 1) {
			sec = time[0];
			sj2.add(String.valueOf(String.format("%02d",sec)));
			
		}
			return sj1.toString() + "; " + sj2.toString();

	}
}
