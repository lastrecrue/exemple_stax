package ma.example.stax;


import java.util.Iterator;

import ma.example.stax.entity.channel.Channel;
import ma.example.stax.entity.channel.DisplayName;

public class LoadChannelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Load loadChannel = new LoadChannel("exemple2.xml");
		Iterator<Channel> it = ((LoadChannel) loadChannel).getChannelList().iterator();
		while(it.hasNext()){
			Channel channel = it.next();
			System.out.println(channel.getIdEpg());
			Iterator<DisplayName> it2 = channel.getDisplayNameList().iterator();
			while(it2.hasNext()){
				DisplayName displayName = it2.next();
				System.out.println("*"+displayName.getName());
			}
			System.out.println(channel.getXmlFeed().getSrc());
		}
	}

}
