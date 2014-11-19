package towergame;

import java.net.URL;
import java.util.HashMap;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundManager {
	private static final HashMap<String, Music> music = new HashMap<String, Music>();
	private static final HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	
	
	/**
	 * @param rscName the name/path of a resource to load
	 * @return a URL if the resource can be found, null otherwise
	 */
	private static URL findResource(final String rscName) {
		URL url = null;

		url = ClassLoader.getSystemResource(rscName);

		if (url == null) {
			System.err.println("Didn't find the resource on at the location " + rscName );

		}
		
		return url;
	}
	
	/**
	 * @param rscName The name/path of the resource to load
	 * @throws SlickException
	 */
	public static void loadMusic(final String rscName) {
		URL u = findResource(rscName);
		
		try {
			music.put(rscName, new Music(u.openStream(), rscName));
		} catch (Exception e) {
			System.err.println("Failed to load the resource found by the spec " + rscName);
			e.printStackTrace();
		}
	}
	
	/**
	 * @param rscName The name/path of the resource to load
	 * @return An Music resource
	 * @throws SlickException
	 */
	public static Music getMusic(final String rscName) {
		if(music.get(rscName) == null) {
			System.err.println("Warning: Music '" + rscName + "' was requested that wasn't previously loaded. Use loadMusic(path) before calling getMusic(path) to avoid runtime lag.");
			loadMusic(rscName);
		}

		return music.get(rscName);
	}
	
	public static void clearMusicCache() {
		music.clear();
	}
	
	/**
	 * @param rscName The name/path of the resource to load
	 * @throws SlickException
	 */
	public static void loadSound(final String rscName) {

		URL u = findResource(rscName);
		try {
			sounds.put(rscName, new Sound(u.openStream(), rscName));
		} catch (Exception e) {

			System.err.println("Failed to load the resource found by the spec " + rscName);
			e.printStackTrace();
		}
	}
	
	/**
	 * @param rscName The name/path of the resource to load
	 * @return An Sound resource
	 * @throws SlickException
	 */
	public static Sound getSound(final String rscName) {
		if(sounds.get(rscName) == null) {
			System.err.println("Warning: Sound '" + rscName + "' was requested that wasn't previously loaded. Use loadSound(path) before calling getSound(path) to avoid runtime lag.");
			loadSound(rscName);
		}

		return sounds.get(rscName);
	}

	public static void clearSoundCache() {
		sounds.clear();
	}
	
}
