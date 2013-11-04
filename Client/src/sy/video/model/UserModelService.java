/**
 * UserModelService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sy.video.model;

public interface UserModelService extends javax.xml.rpc.Service {
    public java.lang.String getUserModelAddress();

    public sy.video.model.UserModel getUserModel() throws javax.xml.rpc.ServiceException;

    public sy.video.model.UserModel getUserModel(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
