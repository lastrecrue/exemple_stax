package ma.example.stax.entity.channel;

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
	private String order;
	private String slig;
	private String license;
	private Region region;
	private Conutry country;
	private Category category;
	private DisplayName displayName;
	private Logo logo;
	private Icon icon;
	private XmlFeed xmlFeed;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSlig() {
		return slig;
	}

	public void setSlig(String slig) {
		this.slig = slig;
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

	public Conutry getCountry() {
		return country;
	}

	public void setCountry(Conutry country) {
		this.country = country;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public DisplayName getDisplayName() {
		return displayName;
	}

	public void setDisplayName(DisplayName displayName) {
		this.displayName = displayName;
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

}
