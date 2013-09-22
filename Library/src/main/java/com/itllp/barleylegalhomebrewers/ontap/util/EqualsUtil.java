package com.itllp.barleylegalhomebrewers.ontap.util;

public final class EqualsUtil {

	  static public boolean areEqual(boolean aThis, boolean aThat){
	    return aThis == aThat;
	  }

	  static public boolean areEqual(char aThis, char aThat){
	    return aThis == aThat;
	  }

	  /*
	   * Implementation Note
	   * Note that byte, short, and int are handled by this method, through
	   * implicit conversion.
	   */
	  static public boolean areEqual(long aThis, long aThat){
	    return aThis == aThat;
	  }

	  static public boolean areEqual(float aThis, float aThat){
	    return Float.floatToIntBits(aThis) == Float.floatToIntBits(aThat);
	  }

	  static public boolean areEqual(double aThis, double aThat){
	    return Double.doubleToLongBits(aThis) == Double.doubleToLongBits(aThat);
	  }

	  /**
	  * Possibly-null object field.
	  *
	  * Includes type-safe enumerations and collections, but does not include
	  * arrays. See class comment.
	  */
	  static public boolean areEqual(Object aThis, Object aThat){
	    return aThis == null ? aThat == null : aThis.equals(aThat);
	  }
	}
