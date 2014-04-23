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

	/**
	 * Column name for tap number. 0 = not on tap, -1 = bottle pouring,
	 * positive # = tap number from which beer is pouring
	 * <P>Type: INTEGER</P>
	 */
	public static final String TAP_NUMBER_COLUMN = "tap_number";

	/**
	 * Column name for the beer packaging type
	 * <P>Type: TEXT</P>
	 */
	public static final String PACKAGING_COLUMN = "packaging";

	/**
	 * Column name for the description of the beer
	 * <P>Type: TEXT</P>
	 */
	public static final String DESCRIPTION_COLUMN = "description";
	
	/**
	 * Column name for beer's original gravity
	 * <P>Type: REAL</P>
	 */
	public static final String ORIGINAL_GRAVITY_COLUMN = "original_gravity";

	/**
	 * Column name for beer's final gravity
	 * <P>Type: REAL</P>
	 */
	public static final String FINAL_GRAVITY_COLUMN = "final_gravity";

}
