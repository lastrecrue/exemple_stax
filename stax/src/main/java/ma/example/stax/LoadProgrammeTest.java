package ma.example.stax;

import java.util.Iterator;

import ma.exemple.stax.entity.programme.Title;
import ma.exemple.stax.entity.programme.Programme;

public class LoadProgrammeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Load loadProgramme = new LoadProgramme("exemple2.xml");
		Iterator<Programme> it = ((LoadProgramme) loadProgramme).getProgrammeList().iterator();
		while(it.hasNext()){
			Programme programme = it.next();
			System.out.println(programme.getIdEpg());
			Iterator<Title> it2 = programme.getTitleList().iterator();
			while(it2.hasNext()){
				Title title = it2.next();
				System.out.println("*"+title.getTitle());
			}
			//System.out.println(programme.getXmlFeed().getSrc());
		}
	}

}
