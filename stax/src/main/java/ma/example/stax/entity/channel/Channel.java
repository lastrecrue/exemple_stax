package ma.example.stax.entity.channel;

import java.util.ArrayList;

//<channel id="11239" order="0" slug="1-2-3-tv" license="0">
//<region lang="en">de</region>
//<country lang="en">de</country>
//<category lang="en">OTHER</category>
//<display-name lang="de">1-2-3-tv</display-name>
//<logo src="http://epgs.com/imgs/logo/1-2-3-tv_big.png" />
//<icon src="http://epgs.com/imgs/logo/1-2-3-tv.png" />
//<xml-feed src="http://www.epgs.com/feeds/xml/epg.php?channel=11239&amp;checksum=50db41a33fefc" />
//</channel>

public class Channel {

	private Integer id;
	private Integer idEpg;
	private String order;
	private String slug;
	private String license;
	private Region region = new Region();
	private Country country = new Country();
	private Category category = new Category();
	private ArrayList<DisplayName> displayNameList = new ArrayList<DisplayName>();
	private Logo logo = new Logo();
	private Icon icon = new Icon();
	private XmlFeed xmlFeed = new XmlFeed();
	private String desc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdEpg() {
		return idEpg;
	}
	public void setIdEpg(Integer idEpg) {
		this.idEpg = idEpg;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public ArrayList<DisplayName> getDisplayNameList() {
		return displayNameList;
	}
	public void setDisplayNameList(ArrayList<DisplayName> displayNameList) {
		this.displayNameList = displayNameList;
	}
	public Logo getLogo() {
		return logo;
	}
	public void setLogo(Logo logo) {
		this.logo = logo;
	}
	public Icon getIcon() {
		return icon;
	}
	public void setIcon(Icon icon) {
		this.icon = icon;
	}
	public XmlFeed getXmlFeed() {
		return xmlFeed;
	}
	public void setXmlFeed(XmlFeed xmlFeed) {
		this.xmlFeed = xmlFeed;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	

}
