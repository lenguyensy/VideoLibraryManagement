/**
 * VideoModelService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sy.video.model;

public interface VideoModelService extends javax.xml.rpc.Service {
    public java.lang.String getVideoModelAddress();

    public sy.video.model.VideoModel getVideoModel() throws javax.xml.rpc.ServiceException;

    public sy.video.model.VideoModel getVideoModel(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
