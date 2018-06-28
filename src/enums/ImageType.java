package enums;

/**
 * 
 * The image types that this application supports
 * 
 * @author Michael Shur
 *
 */
public enum ImageType 
{
	PNG("png"), JPEG("jpg"), GIF("gif");
	
	private final String extension;

	private ImageType(String extension)
	{
		this.extension = extension;
	}
	
	public String getExtension() {
		return "."+extension;
	}
}
