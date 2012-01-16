package client.resourcemanager;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageManager {
	private static Map<Long,Image> imageMap = new HashMap<Long,Image>();

	public static Image getOrPutImage(long id, String url){
		Image toReturn = imageMap.get(id);
		// If it doesn't already exist
		if(toReturn == null){
			Image toInsert;
			try {
				toInsert = new Image(url);
			} catch (SlickException e) {
				e.printStackTrace();
				toInsert = null;
			}
			imageMap.put(id,toInsert);
			toReturn = toInsert;
		}
		
		return toReturn;
	}
}
