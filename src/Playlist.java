import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Playlist {
	
	private ArrayList<Song> songs;
	private Song[] songs2;
	private static final int EXPAND_THRESHOLD = 4;
	private int expandBy = 2;
	private int expandFrequency = 0;
	
	public Playlist() {
		songs = new ArrayList<Song>();
	}
	
	public Playlist(String filename) throws IOException {		

		this.songs = new ArrayList<Song>();
		Scanner file = new Scanner(new File(filename));
		
		for(int i = 0; file.hasNextLine(); ++i) {
			Song song = new Song(file.nextLine());
			songs.add(song);
		}
		file.close();
		
			
	}
	
	public int getNumSongs() {
		return songs.size();
	}
	
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong (int index, Song song) {
		if(song == null || index <0|| index > songs.size())
			return false;
			
			songs.add(index,song);
			return true;
	}
	
	public int addSongs(Playlist playlist) {
		if(playlist == null)
			return 0;
		
		int temp = songs.size();
		
		songs.addAll(playlist.songs);
		return songs.size() - temp;
	}

	public int addSongs(String filename) throws IOException {
		
		Scanner file = new Scanner(new File(filename));
		
		int lineCount = 0;
		for(int i = 0; file.hasNextLine(); ++i) {
			Song song = new Song(file.nextLine());
			songs.add(song);
			++lineCount;
		}
		file.close();
		return lineCount;
	}
	
	public void expand() {
		// If we have already expanded the playlist more than (or equal) the threshold
		// double the expand step
		
		if (expandFrequency >= EXPAND_THRESHOLD) {
			expandBy *= 2;
		}
		// Keep track of the number of times the playlist was expanded 
		expandFrequency++;	
		// Actual expansion of the playlist
		songs2 = Arrays.copyOf(songs2, songs2.length + expandBy);
	}
	
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	public Song removeSong(int index) {
		if(index < 0 || index > songs.size() - 1) {
			return null;
		}
	
		return songs.remove(index);
		
	}
	
	public void saveSongs(String filename) throws IOException {
		PrintWriter writer = new PrintWriter(filename);

			writer.print(toString());
		
		writer.close();
		
	}

	public int[] getTotalTime() {
		int[] totalTime = {0,0,0};
		for(int i = 0; i < getNumSongs(); ++i) {
			int[] allTime = songs.get(i).getTime();
			for(int j =0; j < allTime.length; ++j) {
				totalTime[j] = totalTime[j] + allTime[j];
			}
		}
			totalTime[1] = totalTime[1] + (totalTime[0] / 60);
			totalTime[0] = totalTime[0] % 60;
			totalTime[2] = totalTime[2] + (totalTime[1] / 60);
			totalTime[1] = totalTime[1] % 60;
			if (totalTime[2] == 0) 
				if(totalTime[1] == 0) 
					return Arrays.copyOf(totalTime, 1);
				else
					return Arrays.copyOf(totalTime, 2);
			
			return totalTime.clone();
	
	}
	
	@Override
	public String toString() {
		String line = "";
		for(Song song: songs) {
			if(songs.size() == 1) {
			line = line + song.toString();
			}
			else {
				line = line + song.toString() + System.lineSeparator();
			}
		}
		return line.trim();
	}
}
