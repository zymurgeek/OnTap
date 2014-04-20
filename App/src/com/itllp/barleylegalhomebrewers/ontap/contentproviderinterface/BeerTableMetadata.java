package com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface;

public class BeerTableMetadata {

	/**
	 * Column name for the identification number of the beer
	 * <P>Type: TEXT</P>
	 */
	public static final String ID_COLUMN = "_id";
	/**
	 * Column name for the identification number of the event
	 * <P>Type: TEXT</P>
	 */
	public static final String EVENT_ID_COLUMN = "event_id";
	/**
	 * Column name for the name of the event
	 * <P>Type: TEXT</P>
	 */
	public static final String NAME_COLUMN = "name";
	/**
	 * Column name for the name of the brewer
	 * <P>Type: TEXT</P>
	 */
	public static final String BREWER_NAME_COLUMN = "brewer";
	/**
	 * Column name for the beer style code
	 * <P>Type: TEXT</P>
	 */
	public static final String STYLE_CODE_COLUMN = "style_code";
	/**
	 * Column name for the beer style name
	 * <P>Type: TEXT</P>
	 */
	public static final String STYLE_NAME_COLUMN = "style_name";
	/**
	 * Column name for the beer style override
	 * <P>Type: TEXT</P>
	 */
	public static final String STYLE_OVERRIDE_COLUMN = "style_override";

	/**
	 * Column name for if the beer is kicked.  0 = no, !0 = yes
	 * <P>Type: INTEGER</P>
	 */
	public static final String IS_KICKED_COLUMN = "is_kicked";

}
