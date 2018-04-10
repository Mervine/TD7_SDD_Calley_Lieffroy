import java.util.Collection;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Exercice 1
 * @author Nicolas
 *
 */
public class APIContact {
	private final HTTPTools RETRIEVER = new HTTPTools();
	
	/*
	 *Exercice 2 
	 *Question 1
	 */
	private final MongoClient CLIENT = new MongoClient("localhost");
	private final MongoDatabase ALBUMS = CLIENT.getDatabase("albums");
	private final MongoDatabase TRACKS = CLIENT.getDatabase("tracks");
	
	
	/**
	 * Exercice 1
	 * Question 3
	 * Question 4
	 * @param nomArtiste
	 * @param nomAlbum
	 * @return
	 */
	public Document RetrieveAlbum(final String nomArtiste, final String nomAlbum){
		return Document.parse(
				RETRIEVER.sendGet(
						"http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=b02d3993a22662337304f8f95f4a1fe2&artist="+nomArtiste+"&album="+nomAlbum+"&format=json"
						)
				);
	}

	/**
	 * Exercice 1
	 * Question 3
	 * Question 4
	 * @param nomArtiste
	 * @param nomTitre
	 * @return
	 */
	public Document RetrieveTitre(final String nomArtiste, final String nomTitre){
		return Document.parse(RETRIEVER.sendGet("http://ws.audioscrobbler.com/2.0/?method=track.getinfo&api_key=b02d3993a22662337304f8f95f4a1fe2&artist="+nomArtiste+"&track="+nomTitre+"&format=json"));
	}
	
	/**
	 * Exercice 2
	 * Question 2
	 * insère le document dans la collection donnée
	 * @param document
	 * @param nomCollection
	 * @return
	 */
	public void insertAlbum(Document document, String nomCollection){
		ALBUMS.getCollection(nomCollection).insertOne(document);
	}
	
	/**
	 * Exercice 2
	 * Question 2
	 * insère le document dans la collection donnée
	 * @param document
	 * @param nomCollection
	 * @return
	 */
	public void insertTracks(Document document, String nomCollection){
		TRACKS.getCollection(nomCollection).insertOne(document);
	}
	
	
	/**
	 * Exercice 2
	 * Question 3
	 * On supprime le document au mbid correspondant dans la collection
	 * @param mbid
	 * @param nomCollection
	 */
	public void supprMBIDAlbum(Bson mbid, String nomCollection) {
		ALBUMS.getCollection(nomCollection).deleteOne(mbid);
	}
	
	/**
	 * Exercice 2
	 * Question 3
	 * On supprime le document au mbid correspondant dans la collection
	 * @param mbid
	 * @param nomCollection
	 */
	public void supprMBIDTracks(Bson mbid, String nomCollection) {
		TRACKS.getCollection(nomCollection).deleteOne(mbid);
	}
}
