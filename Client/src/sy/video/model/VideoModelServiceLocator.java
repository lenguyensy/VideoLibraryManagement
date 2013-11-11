/**
 * VideoModelServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sy.video.model;

public class VideoModelServiceLocator extends org.apache.axis.client.Service implements sy.video.model.VideoModelService {

    public VideoModelServiceLocator() {
    }


    public VideoModelServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public VideoModelServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for VideoModel
    //private java.lang.String VideoModel_address = "http://localhost:8080/VideoLibraryServer/services/VideoModel";
    private java.lang.String VideoModel_address = Config.getMovieModelEndPoint();
    
    public java.lang.String getVideoModelAddress() {
        return VideoModel_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String VideoModelWSDDServiceName = "VideoModel";

    public java.lang.String getVideoModelWSDDServiceName() {
        return VideoModelWSDDServiceName;
    }

    public void setVideoModelWSDDServiceName(java.lang.String name) {
        VideoModelWSDDServiceName = name;
    }

    public sy.video.model.VideoModel getVideoModel() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(VideoModel_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getVideoModel(endpoint);
    }

    public sy.video.model.VideoModel getVideoModel(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            sy.video.model.VideoModelSoapBindingStub _stub = new sy.video.model.VideoModelSoapBindingStub(portAddress, this);
            _stub.setPortName(getVideoModelWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setVideoModelEndpointAddress(java.lang.String address) {
        VideoModel_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (sy.video.model.VideoModel.class.isAssignableFrom(serviceEndpointInterface)) {
                sy.video.model.VideoModelSoapBindingStub _stub = new sy.video.model.VideoModelSoapBindingStub(new java.net.URL(VideoModel_address), this);
                _stub.setPortName(getVideoModelWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("VideoModel".equals(inputPortName)) {
            return getVideoModel();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://model.video.sy", "VideoModelService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://model.video.sy", "VideoModel"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("VideoModel".equals(portName)) {
            setVideoModelEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
