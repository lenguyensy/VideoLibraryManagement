/**
 * User.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sy.video.valueobj;

public class User  implements java.io.Serializable {
    private java.lang.String address;

    private float balance;

    private java.lang.String city;

    private java.lang.String firstName;

    private java.lang.String hashPassword;

    private java.lang.String issuedMovies;

    private java.lang.String lastName;

    private java.lang.String membershipNo;

    private float monthlySubscriptionFee;

    private java.lang.String password;

    private java.lang.String state;

    private float total;

    private int totalOutstandingMovies;

    private java.lang.String userId;

    private java.lang.String userType;

    private java.lang.String zipCode;

    public User() {
    }

    public User(
           java.lang.String address,
           float balance,
           java.lang.String city,
           java.lang.String firstName,
           java.lang.String hashPassword,
           java.lang.String issuedMovies,
           java.lang.String lastName,
           java.lang.String membershipNo,
           float monthlySubscriptionFee,
           java.lang.String password,
           java.lang.String state,
           float total,
           int totalOutstandingMovies,
           java.lang.String userId,
           java.lang.String userType,
           java.lang.String zipCode) {
           this.address = address;
           this.balance = balance;
           this.city = city;
           this.firstName = firstName;
           this.hashPassword = hashPassword;
           this.issuedMovies = issuedMovies;
           this.lastName = lastName;
           this.membershipNo = membershipNo;
           this.monthlySubscriptionFee = monthlySubscriptionFee;
           this.password = password;
           this.state = state;
           this.total = total;
           this.totalOutstandingMovies = totalOutstandingMovies;
           this.userId = userId;
           this.userType = userType;
           this.zipCode = zipCode;
    }


    /**
     * Gets the address value for this User.
     * 
     * @return address
     */
    public java.lang.String getAddress() {
        return address;
    }


    /**
     * Sets the address value for this User.
     * 
     * @param address
     */
    public void setAddress(java.lang.String address) {
        this.address = address;
    }


    /**
     * Gets the balance value for this User.
     * 
     * @return balance
     */
    public float getBalance() {
        return balance;
    }


    /**
     * Sets the balance value for this User.
     * 
     * @param balance
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }


    /**
     * Gets the city value for this User.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this User.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the firstName value for this User.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this User.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the hashPassword value for this User.
     * 
     * @return hashPassword
     */
    public java.lang.String getHashPassword() {
        return hashPassword;
    }


    /**
     * Sets the hashPassword value for this User.
     * 
     * @param hashPassword
     */
    public void setHashPassword(java.lang.String hashPassword) {
        this.hashPassword = hashPassword;
    }


    /**
     * Gets the issuedMovies value for this User.
     * 
     * @return issuedMovies
     */
    public java.lang.String getIssuedMovies() {
        return issuedMovies;
    }


    /**
     * Sets the issuedMovies value for this User.
     * 
     * @param issuedMovies
     */
    public void setIssuedMovies(java.lang.String issuedMovies) {
        this.issuedMovies = issuedMovies;
    }


    /**
     * Gets the lastName value for this User.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this User.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the membershipNo value for this User.
     * 
     * @return membershipNo
     */
    public java.lang.String getMembershipNo() {
        return membershipNo;
    }


    /**
     * Sets the membershipNo value for this User.
     * 
     * @param membershipNo
     */
    public void setMembershipNo(java.lang.String membershipNo) {
        this.membershipNo = membershipNo;
    }


    /**
     * Gets the monthlySubscriptionFee value for this User.
     * 
     * @return monthlySubscriptionFee
     */
    public float getMonthlySubscriptionFee() {
        return monthlySubscriptionFee;
    }


    /**
     * Sets the monthlySubscriptionFee value for this User.
     * 
     * @param monthlySubscriptionFee
     */
    public void setMonthlySubscriptionFee(float monthlySubscriptionFee) {
        this.monthlySubscriptionFee = monthlySubscriptionFee;
    }


    /**
     * Gets the password value for this User.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this User.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the state value for this User.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this User.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the total value for this User.
     * 
     * @return total
     */
    public float getTotal() {
        return total;
    }


    /**
     * Sets the total value for this User.
     * 
     * @param total
     */
    public void setTotal(float total) {
        this.total = total;
    }


    /**
     * Gets the totalOutstandingMovies value for this User.
     * 
     * @return totalOutstandingMovies
     */
    public int getTotalOutstandingMovies() {
        return totalOutstandingMovies;
    }


    /**
     * Sets the totalOutstandingMovies value for this User.
     * 
     * @param totalOutstandingMovies
     */
    public void setTotalOutstandingMovies(int totalOutstandingMovies) {
        this.totalOutstandingMovies = totalOutstandingMovies;
    }


    /**
     * Gets the userId value for this User.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this User.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the userType value for this User.
     * 
     * @return userType
     */
    public java.lang.String getUserType() {
        return userType;
    }


    /**
     * Sets the userType value for this User.
     * 
     * @param userType
     */
    public void setUserType(java.lang.String userType) {
        this.userType = userType;
    }


    /**
     * Gets the zipCode value for this User.
     * 
     * @return zipCode
     */
    public java.lang.String getZipCode() {
        return zipCode;
    }


    /**
     * Sets the zipCode value for this User.
     * 
     * @param zipCode
     */
    public void setZipCode(java.lang.String zipCode) {
        this.zipCode = zipCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            this.balance == other.getBalance() &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.hashPassword==null && other.getHashPassword()==null) || 
             (this.hashPassword!=null &&
              this.hashPassword.equals(other.getHashPassword()))) &&
            ((this.issuedMovies==null && other.getIssuedMovies()==null) || 
             (this.issuedMovies!=null &&
              this.issuedMovies.equals(other.getIssuedMovies()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            ((this.membershipNo==null && other.getMembershipNo()==null) || 
             (this.membershipNo!=null &&
              this.membershipNo.equals(other.getMembershipNo()))) &&
            this.monthlySubscriptionFee == other.getMonthlySubscriptionFee() &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            this.total == other.getTotal() &&
            this.totalOutstandingMovies == other.getTotalOutstandingMovies() &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.userType==null && other.getUserType()==null) || 
             (this.userType!=null &&
              this.userType.equals(other.getUserType()))) &&
            ((this.zipCode==null && other.getZipCode()==null) || 
             (this.zipCode!=null &&
              this.zipCode.equals(other.getZipCode())));
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
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        _hashCode += new Float(getBalance()).hashCode();
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getHashPassword() != null) {
            _hashCode += getHashPassword().hashCode();
        }
        if (getIssuedMovies() != null) {
            _hashCode += getIssuedMovies().hashCode();
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        if (getMembershipNo() != null) {
            _hashCode += getMembershipNo().hashCode();
        }
        _hashCode += new Float(getMonthlySubscriptionFee()).hashCode();
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        _hashCode += new Float(getTotal()).hashCode();
        _hashCode += getTotalOutstandingMovies();
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getUserType() != null) {
            _hashCode += getUserType().hashCode();
        }
        if (getZipCode() != null) {
            _hashCode += getZipCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(User.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://valueobj.video.sy", "User"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "balance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "city"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "firstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hashPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "hashPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issuedMovies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "issuedMovies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "lastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("membershipNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "membershipNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monthlySubscriptionFee");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "monthlySubscriptionFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalOutstandingMovies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "totalOutstandingMovies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "userType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zipCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://valueobj.video.sy", "zipCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
