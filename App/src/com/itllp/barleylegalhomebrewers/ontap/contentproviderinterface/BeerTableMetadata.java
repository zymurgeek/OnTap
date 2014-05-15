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

	/**
	 * Column name for beer's percentage alcohol by volume
	 * <P>Type: TEXT</P>
	 */
	public static final String ALCOHOL_BY_VOLUME_COLUMN = "alcohol_by_volume";

	/**
	 * Column name for beer's international bitterness units
	 * <P>Type: REAL</P>
	 */
	public static final String INTERNATIONAL_BITTERNESS_UNITS_COLUMN = "international_bitterness_units";

	/**
	 * Column name for beer's standard reference method (color)
	 * <P>Type: TEXT</P>
	 */
	public static final String STANDARD_REFERENCE_METHOD_COLUMN = "standard_reference_method";

	/**
	 * Column name for whether the brewer's email address is shown.  0 = no, !0 = yes
	 * <P>Type: INTEGER</P>
	 */
	public static final String IS_EMAIL_SHOWN = "is_email_shown";

	/**
	 * Column name for brewer's email address.
	 * <P>Type: TEXT</P>
	 */
	public static final String EMAIL_ADDRESS = "email_address";

	/**
	 * Column name for beer ID on Untappd.
	 * <P>Type: TEXT</P>
	 */
	public static final String UNTAPPD_BEER_ID = "untappd_beer_id";

}
