/**
 * Movie.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sy.video.valueobj;

public class Movie  implements java.io.Serializable {
    private int availableCopies;

    private java.lang.String category;

    private java.lang.String movieBanner;

    private java.lang.String movieId;

    private java.lang.String movieName;

    private int releaseDate;

    private float rentAmount;

    public Movie() {
    }

    public Movie(
           int availableCopies,
           java.lang.String category,
           java.lang.String movieBanner,
           java.lang.String movieId,
           java.lang.String movieName,
           int releaseDate,
           float rentAmount) {
           this.availableCopies = availableCopies;
           this.category = category;
           this.movieBanner = movieBanner;
           this.movieId = movieId;
           this.movieName = movieName;
           this.releaseDate = releaseDate;
           this.rentAmount = rentAmount;
    }


    /**
     * Gets the availableCopies value for this Movie.
     * 
     * @return availableCopies
     */
    public int getAvailableCopies() {
        return availableCopies;
    }


    /**
     * Sets the availableCopies value for this Movie.
     * 
     * @param availableCopies
     */
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }


    /**
     * Gets the category value for this Movie.
     * 
     * @return category
     */
    public java.lang.String getCategory() {
        return category;
    }


    /**
     * Sets the category value for this Movie.
     * 
     * @param category
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }


    /**
     * Gets the movieBanner value for this Movie.
     * 
     * @return movieBanner
     */
    public java.lang.String getMovieBanner() {
        return movieBanner;
    }


    /**
     * Sets the movieBanner value for this Movie.
     * 
     * @param movieBanner
     */
    public void setMovieBanner(java.lang.String movieBanner) {
        this.movieBanner = movieBanner;
    }


    /**
     * Gets the movieId value for this Movie.
     * 
     * @return movieId
     */
    public java.lang.String getMovieId() {
        return movieId;
    }


    /**
     * Sets the movieId value for this Movie.
     * 
     * @param movieId
     */
    public void setMovieId(java.lang.String movieId) {
        this.movieId = movieId;
    }


    /**
     * Gets the movieName value for this Movie.
     * 
     * @return movieName
     */
    public java.lang.String getMovieName() {
        return movieName;
    }


    /**
     * Sets the movieName value for this Movie.
     * 
     * @param movieName
     */
    public void setMovieName(java.lang.String movieName) {
        this.movieName = movieName;
    }


    /**
     * Gets the releaseDate value for this Movie.
     * 
     * @return releaseDate
     */
    public int getReleaseDate() {
        return releaseDate;
    }


    /**
     * Sets the releaseDate value for this Movie.
     * 
     * @param releaseDate
     */
    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }


    /**
     * Gets the rentAmount value for this Movie.
     * 
     * @return rentAmount
     */
    public float getRentAmount() {
        return rentAmount;
    }


    /**
     * Sets the rentAmount value for this Movie.
     * 
     * @param rentAmount
     */
    public void setRentAmount(float rentAmount) {
        this.rentAmount = rentAmount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Movie)) return false;
        Movie other = (Movie) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.availableCopies == other.getAvailableCopies() &&
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.movieBanner==null && other.getMovieBanner()==null) || 
             (this.movieBanner!=null &&
              this.movieBanner.equals(other.getMovieBanner()))) &&
            ((this.movieId==null && other.getMovieId()==null) || 
             (this.movieId!=null &&
              this.movieId.equals(other.getMovieId()))) &&
            ((this.movieName==null && other.getMovieName()==null) || 
             (this.movieName!=null &&
              this.movieName.equals(other.getMovieName()))) &&
            this.releaseDate == other.getReleaseDate() &&
            this.rentAmount == other.getRentAmount();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getAvailableCopies();
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getMovieBanner() != null) {
            _hashCode += getMovieBanner().hashCode();
        }
        if (getMovieId() != null) {
            _hashCode += getMovieId().hashCode();
        }
        if (getMovieName() != null) {
            _hashCode += getMovieName().hashCode();
        }
        _hashCode += getReleaseDate();
        _hashCode += new Float(getRentAmount()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Movie.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://valueobj.video.sy", "Movie"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("availableCopies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "availableCopies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieBanner");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "movieBanner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "movieId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "movieName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("releaseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "releaseDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rentAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "rentAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
